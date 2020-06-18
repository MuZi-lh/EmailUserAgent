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
    //���������Ӧ��smtp�ĵ�ַ�Ͷ�Ӧ����֤��Ϣ��
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
//        System.out.println(apiInfo);
        params.clear();
        if (!apiInfo.contains("?")) {
            System.out.println("EmailUserAgent������δ���в�����");
            reportError();
            return;
        }
        for (String item : apiInfo.split("\\?")[1].split("&")) {
            params.put(item.split("=")[0], item.split("=")[1]);
        }
        String serverName = params.get("username").split("@")[1];//servername��ͬ163.com
        params.put("username", params.get("username").split("@")[0]);//username��ͬ13258159736
        EmailService emailService = new EmailService("smtp." + serverName);//smtp.163.com
        state = emailService.login(params.get("username"), params.get("password"));
        if (!state) {
            System.out.println("�˺Ż����벻��ȷ��");
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
            //todo:�����ǲ�ѯ�ʼ���ҵ���߼�
//            System.out.println("pop login");
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
    public static String readApiInfo(InputStream in){
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String readLine = null;
        String[] split = null;
        try {
            readLine = reader.readLine();
//            System.out.println(readLine);
            split = readLine.split(" ");
            if (split.length != 3)
                return null;
            return split[1];
        } catch (Exception e) {
            //todo:�������ĸ�ʽ������Ҫiu
        }
        return null;
    }
}
