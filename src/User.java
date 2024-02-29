import Course.Course;
import Faculty.Art;
import Faculty.Literature;
import Faculty.Math;
import Faculty.Physics;

import java.util.ArrayList;

public class User {
    private final String username;

    private final  UserType userType;

   private static final ArrayList<Course> registeredCourses = new ArrayList<Course>();

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

    public void addCourse(Course course){
        registeredCourses.add(course);
        if(course.getFaculty().equals("Math")){

            int newStorage = Math.getMath().getStorage().get(course)-1;

            Math.getMath().getStorage().replace(course,newStorage);

        }
        if(course.getFaculty().equals("Physics")){
            int newStorage = Physics.getPhysics().getStorage().get(course)-1;

            Physics.getPhysics().getStorage().replace(course,newStorage);
        }
        if(course.getFaculty().equals("Art")){
            int newStorage = Art.getArt().getStorage().get(course)-1;

            Art.getArt().getStorage().replace(course,newStorage);
        }
        if(course.getFaculty().equals("Literature")){
            int newStorage = Literature.getLiterature().getStorage().get(course)-1;

            Literature.getLiterature().getStorage().replace(course,newStorage);
        }

    }
    public void removeCourse(Course course){
        registeredCourses.remove(course);
    }


}
