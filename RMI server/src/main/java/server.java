
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zaoad
 */
public class server extends UnicastRemoteObject implements adder {
    final int maximumnumberofcoursetaken=5;
    final int maximumnumberofstudentinacourse=30;
    static List<Student> students;
    static List<course> courses;
    static Map<String, String>pre_requisite;
    static Map<String, String>coRequisite;
    
    public server() throws RemoteException{
        super();
    }

    
    public static void makestudentList() throws FileNotFoundException, IOException
    {
       
       FileReader f1=new FileReader("students.txt");
       BufferedReader br=new BufferedReader(f1);
       String s;
       while((s=br.readLine())!=null)
       {
            //System.out.println(s);
            if(s.contains("#"))
            {
                String words[]=s.split("#");
                String name=words[0];
                String Id=words[1];
                Student stu=new Student(Id,name);
                students.add(stu);
            }
       }
       br.close();
       f1.close();
       return ;
    }
    public static void findCoursesList() throws FileNotFoundException, IOException
    {
       FileReader f1=new FileReader("Courses.txt");
       BufferedReader br=new BufferedReader(f1);
       String s;
       while((s=br.readLine())!=null)
       {
            //System.out.println(s);
            if(s.contains("#"))
            {
                String words[]=s.split("#");
                String name=words[0];
                String Id=words[1];
                
                String capacityString=words[2];
                //System.out.println(capacityString);
                int capacity=Integer.parseInt(capacityString);
                course co=new course(Id,name,capacity);
                courses.add(co);
            }
       }
       br.close();
       f1.close();
       return;
    }
    public static void makePreRequisite() throws FileNotFoundException, IOException
    {
       FileReader f1=new FileReader("prerequisitecourses.txt");
       BufferedReader br=new BufferedReader(f1);
       String s;
       while((s=br.readLine())!=null)
       {
            //System.out.println(s);
            if(s.contains("#"))
            {
                String words[]=s.split("#");
                String course=words[0];
                String precourse=words[1];
                pre_requisite.put(course, precourse);
            }
       }
       br.close();
       f1.close();
       return ;
    }
    public static void makeCoRequisite() throws FileNotFoundException, IOException
    {
       FileReader f1=new FileReader("coRequisiteCourse.txt");
       BufferedReader br=new BufferedReader(f1);
       String s;
       while((s=br.readLine())!=null)
       {
            //System.out.println(s);
            if(s.contains("#"))
            {
                String words[]=s.split("#");
                String course=words[0];
                String cocourse=words[1];
                coRequisite.put(course, cocourse);
            }
       }
       br.close();
       f1.close();
       return ;
    }
    public String findStudentId(String studentId) 
    {

       for(Student stu:students)
       {
           //System.out.println(stu.getId() +stu.getName());
           if(stu.getId().equals(studentId))
           {
               //System.out.println("gece");
               return stu.getName();
           }
       }
       return null;
    }
    
    public String findCourse(String course_id) 
    {
        for(course co:courses)
       {
           if(co.getId().equals(course_id))
           {
               return co.getName();
           }
       }
       return null;
        
    }
    public int findCourseIndex(String course_id)
    {
       int i;
       int len=courses.size();
       for(i=0;i<len;i++)
       {
           //System.out.println("hai hai"+i+" "+courses.size());
           course co=courses.get(i);
           if(co.getId().equals(course_id))
           {
               //System.out.println("return value "+i);
               return i;
           }
       }
       return -1;
    }
    public boolean findIsEntryExist(String key, String value,String filename) throws FileNotFoundException, IOException 
    {
       FileReader f1=new FileReader(filename);
       BufferedReader br=new BufferedReader(f1);
       String s;
       //System.out.println("checking");
       while((s=br.readLine())!=null)
       {
            //System.out.println(s);
            if(s.contains("#"))
            {
                String words[]=s.split("#");
                String keytmp=words[0];
                String valuetmp=words[1];
                if(keytmp.equals(key)&&valuetmp.equals(value))
                {
                    br.close();
                    f1.close();
                    return true;
                }
            }
       }
       br.close();
       f1.close();
       return false;
        
    }
    public boolean isPrerequisiteCourseTaken(String student_id,String course_id) throws FileNotFoundException, IOException 
    {
       //System.out.println("checking prerequisite");
       if(!pre_requisite.containsKey(course_id))
       {
           return true;
       }
       String pre_course_id=pre_requisite.get(course_id);
       String file_name="alreadytaken"+student_id+".txt";
       //System.out.println(file_name);
       FileReader f1=new FileReader(file_name);
       BufferedReader br=new BufferedReader(f1);
       String s;
       while((s=br.readLine())!=null)
       {
            //System.out.println(s);
            if(s.equals(pre_course_id))
            {
                br.close();
                f1.close();
                return true;
                
            }

       }
       br.close();
       f1.close();
       return false;
    }
    public boolean isMaximumCourseTaken(String studentId) throws TakenTooManyCourseException, FileNotFoundException, IOException
    {
        
       FileReader f1=new FileReader("studenttocourses.txt");
       BufferedReader br=new BufferedReader(f1);
       String s;
       int totalcourse=0;
       while((s=br.readLine())!=null)
       {
            //System.out.println("ismax "+s);
            if(s.contains("#"))
            {
                
                String words[]=s.split("#");
                String keytmp=words[0];
                String valuetmp=words[1];
                if(keytmp.equals(studentId))
                {
                    totalcourse++;
                }
            }
       }
       br.close();
       f1.close();
       //System.out.println("total course "+totalcourse);
       if(totalcourse<maximumnumberofcoursetaken)
       {
           return false;
           
       }
       else
           return true;
        
    }
    public boolean isNoVacantInCourses(String course_id) throws CourseIsFullException, FileNotFoundException, IOException
    {
       FileReader f1=new FileReader("studenttocourses.txt");
       BufferedReader br=new BufferedReader(f1);
       String s;
       int totalstudent=0;
       while((s=br.readLine())!=null)
       {
            if(s.contains("#"))
            {
                //System.out.println(s);
                String words[]=s.split("#");
                String keytmp=words[0];
                String valuetmp=words[1];
                if(valuetmp.equals(course_id))
                {
                    totalstudent++;
                }
            }
       }
       br.close();
       f1.close();
       int course_index=findCourseIndex(course_id);
       if(course_index==-1)
           return false;
       int capacity=courses.get(course_index).getCapacity();
       if(totalstudent<capacity)
       {
           return false;   
       }
       else
           return true;
        
    }
     @Override
    public synchronized boolean register(String studentId, String courseId) throws RemoteException,StudentNotFoundException,CourseNotFoundException,EntryAlreadyExistException{
        System.out.println("Function call for Register:");
        System.out.println("student id: "+studentId+" Courseid: "+ courseId );
        String studentName,courseName;
        studentName=findStudentId(studentId);
        courseName=findCourse(courseId);
        try {
            if(studentName==null)
            {
                throw new StudentNotFoundException(studentName+" ( id: "+studentId+" )student doesn't exist");
            }
        } catch(StudentNotFoundException ex)
        {
            System.out.println(ex);
            return false;
        }
        try {
            if(courseName==null)
            {
                throw new CourseNotFoundException(courseName+"( id: "+courseId+ ")course is not found");
                
            }
        } 
        catch(CourseNotFoundException ex)
        {
            System.out.println(ex);
            return false;
        }
        try {
            if(findIsEntryExist(studentId,courseId,"studenttocourses.txt"))
            {
               throw new EntryAlreadyExistException("course is already Taken");
               
            }
        }catch(EntryAlreadyExistException ex)
        {
            System.out.println(ex);
            return false;
            
        } catch (IOException ex) {
            System.out.println("file not Found");
             return false;
        }
        try{
            if(!isPrerequisiteCourseTaken(studentId,courseId))
            {
                throw new PrerequisiteCourseNotTakenException("prerequisite course not taken");
            }
        }
        catch(PrerequisiteCourseNotTakenException e)
        {
             System.out.println(e);
             return false;
        } catch (IOException ex) {
            System.out.println("pre file not Found");
             return false;
        }
        try {
            if(isMaximumCourseTaken(studentId))
            {
                throw new TakenTooManyCourseException("to many course taken already");
            }
        } catch (TakenTooManyCourseException ex) {
            System.out.println(ex);
            return false;
        } catch (IOException ex) {
            System.out.println("is max file not Found");
             return false;
        }
        try {
            if(isNoVacantInCourses(courseId))
            {
                throw new CourseIsFullException(courseName+" is full");
            }
        } catch (CourseIsFullException ex) {
            System.out.println(ex);
            return false;
            
        } catch (IOException ex) {
            System.out.println("no vacant file not Found");
            return false;
        }
        try{
        FileWriter f=new FileWriter("studenttocourses.txt",true);
        String s=studentId+"#"+courseId+"\n";
        f.write(s);
        f.close();
        System.out.println("Succesfull: "+studentName+"(id: "+studentId+")has taken course" +courseName+"( id: "+courseId+")");
        if(coRequisite.containsKey(courseId))
        {
            String co_course_id=coRequisite.get(courseId);
            System.out.println("CoRequistic Course Exist: "+co_course_id);
            System.out.println("Registering course "+co_course_id);
            coregister(studentId,co_course_id);
        }
        return true;
        }catch (IOException ex) {
            System.out.println(ex);
        }
        
        return false;
    }
    public boolean coregister(String studentId, String courseId) throws RemoteException,StudentNotFoundException,CourseNotFoundException,EntryAlreadyExistException{
        System.out.println("Function call for corequisite course register:");
        System.out.println("student id: "+studentId+" Courseid: "+ courseId );
        String studentName,courseName;
        studentName=findStudentId(studentId);
        courseName=findCourse(courseId);
        try {
            if(studentName==null)
            {
                throw new StudentNotFoundException(studentName+" ( id: "+studentId+" )student doesn't exist");
            }
        } catch(StudentNotFoundException ex)
        {
            System.out.println(ex);
            return false;
        }
        try {
            if(courseName==null)
            {
                throw new CourseNotFoundException(courseName+"( id: "+courseId+ ")course is not found");
                
            }
        } 
        catch(CourseNotFoundException ex)
        {
            System.out.println(ex);
            return false;
        }
        try {
            if(findIsEntryExist(studentId,courseId,"studenttocourses.txt"))
            {
               throw new EntryAlreadyExistException("course is already Taken");
               
            }
        }catch(EntryAlreadyExistException ex)
        {
            System.out.println(ex);
            return false;
            
        } catch (IOException ex) {
            System.out.println("file not Found");
             return false;
        }
        try{
            if(!isPrerequisiteCourseTaken(studentId,courseId))
            {
                throw new PrerequisiteCourseNotTakenException("prerequisite course not taken");
            }
        }
        catch(PrerequisiteCourseNotTakenException e)
        {
             System.out.println(e);
             return false;
        } catch (IOException ex) {
            System.out.println("pre file not Found");
             return false;
        }
        try {
            if(isMaximumCourseTaken(studentId))
            {
                throw new TakenTooManyCourseException("to many course taken already");
            }
        } catch (TakenTooManyCourseException ex) {
            System.out.println(ex);
            return false;
        } catch (IOException ex) {
            System.out.println("is max file not Found");
             return false;
        }
        try {
            if(isNoVacantInCourses(courseId))
            {
                throw new CourseIsFullException(courseName+" is full");
            }
        } catch (CourseIsFullException ex) {
            System.out.println(ex);
            return false;
            
        } catch (IOException ex) {
            System.out.println("no vacant file not Found");
            return false;
        }
        try{
            FileWriter f=new FileWriter("studenttocourses.txt",true);
            String s=studentId+"#"+courseId+"\n";
            f.write(s);
            f.close();
            System.out.println("Succesfull: "+studentName+"(id: "+studentId+")has taken course" +courseName+"( id: "+courseId+")");
            return true;
        }catch (IOException ex) {
            System.out.println(ex);
        }
        
        return false;
    }
    public void removefromregistration(String studentId,String courseId) throws FileNotFoundException, IOException
    {
       FileReader f1=new FileReader("studenttocourses.txt");
       BufferedReader br=new BufferedReader(f1);
       String s;
       List<String>entries=new ArrayList<String>();
       //System.out.println("checking");
       while((s=br.readLine())!=null)
       {
            //System.out.println(s);
            if(s.contains("#"))
            {
                String words[]=s.split("#");
                String keytmp=words[0];
                String valuetmp=words[1];
                if(!(keytmp.equals(studentId)&&valuetmp.equals(courseId)))
                {
                    entries.add(s);
                }
            }
       }
       br.close();
       f1.close();
       FileWriter f=new FileWriter("studenttocourses.txt");
       for(String entry:entries)
       {
           if(entry!=null)
           {
               f.write(entry+"\n");
           }
       }
       f.close();
       return ;
    }
    @Override
    public synchronized boolean unregister(String studentId, String courseId) throws RemoteException,StudentNotFoundException,CourseNotFoundException,EntryAlreadyExistException, EntrynotFoundException, IOException{
        System.out.println("Function call for Unegister:");
        System.out.println("student id: "+studentId+" Courseid: "+ courseId );
        String studentName,courseName;
        studentName=findStudentId(studentId);
        courseName=findCourse(courseId);
        try {
            if(studentName==null)
            {
                throw new StudentNotFoundException(studentName+" ( id: "+studentId+" )student doesn't exist");
            }
        } catch(StudentNotFoundException ex)
        {
            System.out.println(ex);
            return false;
        }
        try {
            if(courseName==null)
            {
                throw new CourseNotFoundException(courseName+"( id: "+courseId+ ")course is not found");
                
            }
        } 
        catch(CourseNotFoundException ex)
        {
            System.out.println(ex);
            return false;
        }
        try {
            if(!findIsEntryExist(studentId,courseId,"studenttocourses.txt"))
            {
               throw new EntrynotFoundException("course is not reggistered");
               
            }
        }catch(EntrynotFoundException ex)
        {
            System.out.println(ex);
            return false;
            
        } catch (IOException ex) {
            System.out.println("file not Found");
             return false;
        }
        removefromregistration(studentId,courseId);
        System.out.println("Succesfully unregistered "+studentName+"(id: "+studentId+")has unregisterd course" +courseName+"( id: "+courseId+")");
        if(coRequisite.containsKey(courseId))
        {
            String co_course_id=coRequisite.get(courseId);
            System.out.println("CoRequistic Course Exist: "+co_course_id);
            System.out.println("Unregistering course "+co_course_id);
            counregister(studentId,co_course_id);
        }
        return true;
    }
    public boolean counregister(String studentId, String courseId) throws RemoteException,StudentNotFoundException,CourseNotFoundException,EntryAlreadyExistException, EntrynotFoundException, IOException{
        System.out.println("Function call for corequisite course unregister:");
        System.out.println("student id: "+studentId+" Courseid: "+ courseId );
        String studentName,courseName;
        studentName=findStudentId(studentId);
        courseName=findCourse(courseId);
        try {
            if(studentName==null)
            {
                throw new StudentNotFoundException(studentName+" ( id: "+studentId+" )student doesn't exist");
            }
        } catch(StudentNotFoundException ex)
        {
            System.out.println(ex);
            return false;
        }
        try {
            if(courseName==null)
            {
                throw new CourseNotFoundException(courseName+"( id: "+courseId+ ")course is not found");
                
            }
        } 
        catch(CourseNotFoundException ex)
        {
            System.out.println(ex);
            return false;
        }
        try {
            if(!findIsEntryExist(studentId,courseId,"studenttocourses.txt"))
            {
               throw new EntrynotFoundException("course is not reggistered");
               
            }
        }catch(EntrynotFoundException ex)
        {
            System.out.println(ex);
            return false;
            
        } catch (IOException ex) {
            System.out.println("file not Found");
             return false;
        }
        removefromregistration(studentId,courseId);
        System.out.println("Succesfully unregistered "+studentName+"(id: "+studentId+")has unregisterd course" +courseName+"( id: "+courseId+")");
        return true;
    }
    @Override
    public synchronized String  getRegistrationList(String student_id) throws RemoteException,StudentNotFoundException
    {
        System.out.println("Function call for getRegistrationList:");
        System.out.println("student id: "+student_id);
        String student_name;
        student_name=findStudentId(student_id);
        try
        {
            if(student_name==null)
            {
                throw new StudentNotFoundException("student not exist");
            }
        }
        catch(StudentNotFoundException ex)
        {
            System.out.println(ex);
            return null;
        }
        String str=student_id+"#"+student_name;
         
       FileReader f1;
       BufferedReader br;
        try {
            f1 = new FileReader("studenttocourses.txt");
            br=new BufferedReader(f1); 
            int totalcourse=0;
            String s;
            while((s=br.readLine())!=null)
            {
                //System.out.println("ismax "+s);
                if(s.contains("#"))
                {

                    String words[]=s.split("#");
                    String keytmp=words[0];
                    String valuetmp=words[1];
                    if(keytmp.equals(student_id))
                    {
                        totalcourse++;
                        String course_name=findCourse(valuetmp);
                        str=str+"#"+course_name+"#"+valuetmp;
                    }
                }
            }
            str=totalcourse+"#"+str;
            br.close();
            f1.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
            return null;
        }
        //System.out.println("returning"+str);
        return str;
    }
    public static void main(String args[]) throws RemoteException, IOException
    {
        students=new ArrayList<Student>();
        courses=new ArrayList<course>();
        pre_requisite=new HashMap<String, String>();
        coRequisite=new HashMap<String, String>();
        makestudentList();
        findCoursesList();
        makePreRequisite();
        makeCoRequisite();
        /*
        for(Student stu:students)
        {
            System.out.println(stu.getName()+" "+stu.getId());
        }
        
        for(course co:courses)
        {
            System.out.println(co.getName()+" "+co.getId()+" "+co.getCapacity());
        }
        */
        try
        {
        Registry reg=LocateRegistry.createRegistry(4444);
        reg.rebind("hi server", new server());
        System.out.println("server is ready");
        }
        catch(RemoteException e)
        {
            System.err.println("Exception :"+ e);
        }
    }
    
    
}
