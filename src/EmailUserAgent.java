import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class EmailUserAgent extends Thread{

    private ServerSocket socket = null;

    @Override
    public void run() {
        try {
            //mailService的端口号
            socket = new ServerSocket(60541);
            System.out.println("Email User Agent启动成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                Socket st = socket.accept();
                System.out.println("Email User Agent被访问！IP:" + st.getInetAddress());
                new EmailThread(st).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}