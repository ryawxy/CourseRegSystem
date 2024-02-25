package User;

import Course.Course;
import DataBase.DataBase;
import Faculty.Art;
import Faculty.Literature;
import Faculty.Math;
import Faculty.Physics;

import java.util.ArrayList;

public class User {
    private final String username;

    private final UserType userType;


   private final ArrayList<Course> registeredCourses = new ArrayList<Course>();

    public User(String username, UserType userType) {
        this.username = username;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getAdminsPassword() {
        return "admin123";
    }

    public  ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void addCourse(Course course,User user){
        int index =  DataBase.getRegisteredUsers().indexOf(user);

        registeredCourses.add(course);

        course.getRegisteredUsers().add(user);
        if(course.getFaculty().equals("Math")){

            int newStorage = Math.getStorage().get(course)-1;

            Math.getStorage().replace(course,newStorage);

        }
        if(course.getFaculty().equals("Physics")){
            int newStorage = Physics.getStorage().get(course)-1;

            Physics.getStorage().replace(course,newStorage);
        }
        if(course.getFaculty().equals("Art")){
            int newStorage = Art.getStorage().get(course)-1;

            Art.getStorage().replace(course,newStorage);
        }
        if(course.getFaculty().equals("Literature")){
            int newStorage = Literature.getStorage().get(course)-1;

            Literature.getStorage().replace(course,newStorage);
        }

    }
    public void removeCourse(Course course, User user){
        registeredCourses.remove(course);
        course.getRegisteredUsers().remove(user);
        if(course.getFaculty().equals("Math")){

            int newStorage = Math.getStorage().get(course)+1;

            Math.getStorage().replace(course,newStorage);

        }
        if(course.getFaculty().equals("Physics")){
            int newStorage = Physics.getStorage().get(course)+1;

            Physics.getStorage().replace(course,newStorage);
        }
        if(course.getFaculty().equals("Art")){
            int newStorage = Art.getStorage().get(course)+1;

            Art.getStorage().replace(course,newStorage);
        }
        if(course.getFaculty().equals("Literature")){
            int newStorage = Literature.getStorage().get(course)+1;

            Literature.getStorage().replace(course,newStorage);
        }

    }


}
