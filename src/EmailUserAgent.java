import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class EmailUserAgent extends Thread{

    private ServerSocket socket = null;

    @Override
    public void run() {
        try {
            socket = new ServerSocket(1010);//mailService的端口号
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                Socket st = socket.accept();
                System.out.println("EmailUserAgent被访问！IP:" + st.getInetAddress());
                new EmailThread(st).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}