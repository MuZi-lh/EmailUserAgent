import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class EmailUserAgent extends Thread{

    private ServerSocket socket = null;

    @Override
    public void run() {
        try {
            //mailService�Ķ˿ں�
            socket = new ServerSocket(60541);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                Socket st = socket.accept();
                System.out.println("EmailUserAgent�����ʣ�IP:" + st.getInetAddress());
                new EmailThread(st).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}