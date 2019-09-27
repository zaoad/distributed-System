

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class KnockKnockClient{
    public static void main(String[] args) throws IOException {
        Socket echoSocket = new Socket("localhost", 5555);
        System.out.println("serverconnected");
        DataInputStream din=new DataInputStream(echoSocket.getInputStream());
        DataOutputStream dout=new DataOutputStream(echoSocket.getOutputStream());
        //FileReader fr=new FileReader("input.txt");
        String instring=null;
        String outstring=null;
        Scanner fsc=new Scanner(System.in);
        String startjoke;
        int correctincoming=0;
        while (true) {

            instring = din.readUTF();
            if(instring.equals("stop"))
                break;
            System.out.println("Server: " + instring);
            if(instring.equals("I have no more jokes to tell"))
                break;
            if(instring.contains("You are supposed to say"))
            {
                correctincoming=0;
                continue;
            }
            if(correctincoming==2)
            {
                correctincoming=0;
                continue;
            }
            if(instring.equals("Would you like to listen another?(Y/N)"))
            {
                correctincoming--;
            }
            correctincoming++;

            System.out.print("Client: ");
            outstring = fsc.nextLine();
            dout.writeUTF(outstring);
            dout.flush();
        }
        System.out.println("terminate");
        din.close();
        dout.close();
        echoSocket.close();
    }
}
