import net.sf.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailThread extends Thread {
    private InputStream in = null;
    private OutputStream out = null;
    private Map<String, String> params = null;
    //用来保存对应的smtp的地址和对应的认证信息等
    private Map<String, List<String>> serverAddressInfo = null;

    EmailThread(Socket socket) {
        try {
            this.in = socket.getInputStream();
            this.out = socket.getOutputStream();
            this.params = new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        boolean state = false;
        String apiInfo = readApiInfo(this.in);
        System.out.println(apiInfo);
        params.clear();
        if (!apiInfo.contains("?")) {
            System.out.println("EmailUserAgent请求中未带有参数！");
            reportError();
            return;
        }
        for (String item : apiInfo.split("\\?")[1].split("&")) {
            params.put(item.split("=")[0], item.split("=")[1]);
        }
        for (String k : params.keySet()) {
            System.out.println(k+" : "+params.get(k));
        }
        String serverName = params.get("username").split("@")[1];//servername形同163.com
        params.put("username", params.get("username").split("@")[0]);//username形同13258159736
        EmailService emailService = new EmailService("smtp." + serverName);//smtp.163.com
        state = emailService.login(params.get("username"), params.get("password"));
        if (!state) {
            System.out.println("账号或密码不正确！");
            reportError();
            return;
        }
        if (!params.containsKey("type")) {
            reportError();
            return;
        }
        if (params.get("type").equals("send")) {
            state = emailService.sendMail(params);
            if (state) {
                String result = "HTTP/1.1 200 ok \n" +
                        "Content-Type: text/html \n" +
                        "Access-Control-Allow-Origin: *\n";
                try {
                    this.out.write(result.getBytes());
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (params.get("type").equals("query")) {
            // todo:这里是查询邮件的业务逻辑
            String mailJson = emailService.queryMail(params);
            String result = "HTTP/1.1 200 ok \n" +
                    "Content-Type: application/json;charset=UTF-8 \n" +
                    "Access-Control-Allow-Origin: *\n" +
                    "\n" + mailJson;
            try {
                out.write(result.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (params.get("type").equals("delete")){
            JSONObject json = new JSONObject();
            state = emailService.deleteMail(params);
            json.put("deleteState",state);
            String result = "HTTP/1.1 200 ok \n" +
                    "Content-Type: application/json;charset=UTF-8 \n" +
                    "Access-Control-Allow-Origin: *\n" +
                    "\n" + json.toString();
            try {
                out.write(result.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 报错的HTTP响应
    private void reportError() {
        String result = "HTTP/1.1 400 ok \n" +
                "Content-Type: text/html \n" +
                "Access-Control-Allow-Origin: *\n";
        try {
            this.out.write(result.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 读取URI，包括路径和params
    public static String readApiInfo(InputStream in){
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String readLine = null;
        String[] split = null;
        try {
            readLine = reader.readLine();
            split = readLine.split(" ");
            if (split.length != 3)
                return null;
            return split[1];
        } catch (Exception e) {
            //todo:传过来的格式不符合要iu
        }
        return null;
    }
}
