import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WebServer {

    private static ServerSocket socket = null;

    public static void main(String[] args) throws IOException {
        System.out.println("WebServer����������");
        try {
        	EmailUserAgent emailUserAgent = new EmailUserAgent();
            emailUserAgent.start();
        } catch (Exception e) {
            System.out.println("����EmailUserAgentʱ��������!");
        }
        System.out.println("WebServer�Ѿ��������!");
        int port=8080;
        socket = new ServerSocket(port);
        while (true) {
            Socket sk = socket.accept();
            System.out.println(String.format("WebServer���յ�����IP:%s��HTTP����!", sk.getInetAddress()));
            WebThread webThread = new WebThread(sk);
            webThread.start();
        }
    }

    private static List<String> getFiles(String path) {
        List<String> list = new ArrayList<>();
        File file = new File(path);
        String name = null;
        File[] files = file.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            name = files[i].getPath().split("\\\\")[files[i].getPath().split("\\\\").length - 1];
            if (name.split("[.]")[name.split("[.]").length - 1].equals("java")) {
                list.add(name.split("[.]")[0]);
            }
        }
        return list;
    }
}
