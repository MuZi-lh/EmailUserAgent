import java.io.*;
import java.net.Socket;

public class WebThread extends Thread {
 //   private static String ROOT = "";
    private InputStream inputStream;
    private OutputStream outputStream;

    WebThread(Socket sk) {
//        String defaultDir = ReadConfigUtils.configList.getOrDefault("defaultWebDir","/webPage");
//        String defaultDir = "/webPage";
//        ROOT = System.getProperty("user.dir") + defaultDir;
        try {
            this.inputStream = sk.getInputStream();
            this.outputStream = sk.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String filePath = read();
        response(filePath);
    }

    private void response(String filePath) {
        if(filePath.equals("/")){
//            filePath = ReadConfigUtils.configList.getOrDefault("defaultPage", "/index.html");
            filePath = "/index.html";
        }
        try {
//            File file = new File(ROOT + filePath);
        	File file = new File(System.getProperty("user.dir")+filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = reader.readLine()) != null) {
                sb.append(readLine).append("\r\n");
            }
            String result = "HTTP/1.1 200 ok \n" +
                    "Content-Type: text/html \n" +
                    "\n" + sb.toString();
            this.outputStream.write(result.getBytes());
            outputStream.flush();
            outputStream.close();
            reader.close();
        } catch (FileNotFoundException e) {
            response("/error.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String read() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.inputStream));
        String readLine = null;
        String[] split = null;
        try {
            readLine = reader.readLine();
            split = readLine.split(" ");
            if (split.length != 3)
                return null;
            System.out.println(String.format("WebServer�յ���http�����ײ���%s!",readLine));
            return split[1];
        } catch (Exception e) {
            //todo:����������ʵ�ʱ�򣬻���ֺܶ಻��Ҫ�ķ��ʣ�����������������صĴ�����Ϣ
        }
        return null;
    }
}
