import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;
public class KnockKnockServerThread extends Thread {
    protected Socket socket;
    public  DataInputStream input;
    DataOutputStream dout;
    jokes joke;
    Random rand;
    int remaining_joke;
    int client_no;
    public KnockKnockServerThread(Socket clientSocket, int client_no) throws IOException,SocketException {

        this.socket = clientSocket;
        input = new DataInputStream(socket.getInputStream());
        joke=new jokes();
        dout=new DataOutputStream(socket.getOutputStream());
        rand=new Random();
        remaining_joke=30;
        this.client_no=client_no;

    }
    String msg ;
    String startjoke;
    String finaljoke;
    String who;
    public void run() {
        while(true)
        {
            if(remaining_joke==0)
            {
                try {
                    System.out.println("server to client "+client_no+": I have no more jokes to tell");
                    dout.writeUTF("I have no more jokes to tell");
                    dout.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            try {

                System.out.println("server to client "+client_no+": Knock Knock!");
                dout.writeUTF("Knock Knock!");
                dout.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                msg = input.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("client "+client_no+": "+msg);
            msg=msg.toUpperCase();
            if(msg.equals("WHO'S THERE?"))
            {
                int joke_number=rand.nextInt(remaining_joke);
                startjoke=joke.jokeskeys.get(joke_number);
                System.out.println("server to client "+client_no+": "+startjoke);
                try {

                    dout.writeUTF(startjoke);
                    dout.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("server to client "+client_no+": "+"You are supposed to say \"Who's there?\". Let's try again.");
                try {
                    dout.writeUTF("You are supposed to say \"Who's there?\". Let's try again.");
                    dout.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                continue;
            }
            try {
                msg=input.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("client "+client_no+": "+msg);
            who=startjoke+" who?";
            who=who.toUpperCase();
            msg=msg.toUpperCase();
            if(msg.equals(who))
            {
                finaljoke=joke.jokesMap.get(startjoke.toUpperCase());
                try {
                    System.out.println("Server to client "+client_no+": "+finaljoke);
                    dout.writeUTF(finaljoke);
                    dout.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                joke.jokeskeys.remove(startjoke);
                remaining_joke--;
            }
            else
            {
                System.out.println("Server to client "+client_no+": "+"You are supposed to say "+startjoke+" \"Who?\". Let's try again.");
                try {
                    dout.writeUTF("You are supposed to say "+startjoke+" \"Who?\". Let's try again.");
                    dout.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                continue;
            }
            System.out.println("server to client "+client_no+": "+"Would you like to listen another?(Y/N)");
            try {
                dout.writeUTF("Would you like to listen another?(Y/N)");
                dout.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                msg=input.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("client "+client_no+": "+msg);
            msg=msg.toUpperCase();
            if(msg.equals("Y"))
                continue;
            else{
                try {
                    dout.writeUTF("stop");
                    dout.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

        }
        System.out.println("client "+client_no+ " terminated");
        try {
            input.close();
            dout.close();

            socket.close();
            KnockKnockServer.remaining_client--;
            if(KnockKnockServer.remaining_client==0)
                KnockKnockServer.terminate();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}