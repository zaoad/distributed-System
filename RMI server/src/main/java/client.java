
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zaoad
 */
public class client {
    public static void main(String args[]) throws RemoteException, NotBoundException, StudentNotFoundException, CourseNotFoundException, EntryAlreadyExistException, EntrynotFoundException, IOException
    {
        client c=new client();
        c.connectRemote();
    }
    public static void printlist(String s)
    {
        //System.out.println("s "+s);
            String words[]=s.split("#");
            System.out.println("Student name                |     Student id");
            System.out.println(words[1]+"          |    "+words[2]);
            System.out.println("---------------------------------------");
            System.out.println("total registered course:" +words[0]+"\n");
            System.out.println("---------------------------------------");
            System.out.println("Course name                |     Course id");
            for(int i=3;i<words.length;i+=2)
            {
                
               System.out.println(words[i]+"          |    "+words[i+1]);
            }
    }
    private void connectRemote() throws RemoteException, NotBoundException, StudentNotFoundException, CourseNotFoundException, EntryAlreadyExistException, EntrynotFoundException, IOException {
        try
        {
            Registry reg=LocateRegistry.getRegistry("localhost", 4444);
            adder ad=(adder) reg.lookup("hi server");
           
            Scanner sc=new Scanner(System.in);
            
            while(true){
                System.out.println("Type 1 to register");
                System.out.println("Type 2 to unregister");
                System.out.println("Type 3 to Get registration list");
                System.out.println("Type any button to exit");
                String n=sc.nextLine();
                String sid,courseId;
                if(n.equals("1")){
                    System.out.println("Enter roll and course id for registration");
                    sid=sc.nextLine();
                    courseId=sc.nextLine();
                    System.out.println(ad.register(sid, courseId));
                }
                else if(n.equals("2")){
                    System.out.println("Enter roll and course id for unregistration");
                    sid=sc.nextLine();
                    courseId=sc.nextLine();
                     System.out.println(ad.unregister(sid, courseId));
            
                }
                else if(n.equals("3")){
                    System.out.println("Enter roll to get registration list");
                    sid=sc.nextLine();
                    String s=ad.getRegistrationList(sid);
                    printlist(s);
           
                    
                
                }
                else 
                    break;
                    
            }
            //System.out.println(ad.register("58", "10"));
            //String s=ad.getRegistrationList("01");
           // System.out.println(ad.unregister("01", "10"));
            
        }
        catch(NotBoundException | RemoteException e)
        {
            System.out.println("Exception : "+e);
        }
    }
    
}
 