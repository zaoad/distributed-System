/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author zaoad
 */
public interface adder extends Remote{
    
    public boolean register(String studentId, String courseId ) throws RemoteException,StudentNotFoundException,CourseNotFoundException,EntryAlreadyExistException;
    public String getRegistrationList(String student_id)throws RemoteException,StudentNotFoundException;
    public boolean unregister(String studentId, String courseId) throws RemoteException,StudentNotFoundException,CourseNotFoundException,EntryAlreadyExistException, EntrynotFoundException, IOException;

    /*public boolean unregister(String studentId, String courseId);
    public String getRegistrationList(String StudentId);
    public boolean registerStudent(String name, String Id);
    public boolean registerCourse(String name, String Id);
    public boolean registerPreCourse(String Id, String preId);*/
}
