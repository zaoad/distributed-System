import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class KnockKnockServer {

    static final int PORT = 5555;
    static ServerSocket serverSocket;
    static Socket socket;
    static int remaining_client=0;
    public static void terminate() throws IOException {
        serverSocket.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("server terminate");
        System.exit(0);
    }
    public static void main(String args[]) throws IOException {
        System.out.println("server Started");
        int connected_client=1;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("problem in creating serversocket");

        }

        while (true) {

            try {
                socket = serverSocket.accept();
                //System.out.println("running");
                remaining_client++;
                System.out.println("new client no "+connected_client+" connected");
            } catch (IOException e) {
                //System.out.println("I/O error: " + e);
            }
            // new thread for a client
            try {
                new KnockKnockServerThread(socket,connected_client++).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
