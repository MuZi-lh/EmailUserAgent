import java.io.IOException;

public class EmailUserAgentServer {

    public static void main(String[] args) throws IOException {
        System.out.println("Email User Agent Server正在启动！");
        try {
            // 启动邮件代理系统
        	EmailUserAgent emailUserAgent = new EmailUserAgent();
            emailUserAgent.start();
        } catch (Exception e) {
            System.out.println("启动EmailUserAgent时出现问题!");
        }
    }
}
