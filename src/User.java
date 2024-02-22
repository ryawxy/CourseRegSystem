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
        if(course.getFaculty().equals("Math")){
            Math math = new Math();
            int newStorage = math.getStorage().get(course)-1;

            math.getStorage().replace(course,newStorage);

        }
        registeredCourses.add(course);

        if(course.getFaculty().equals("Physics")){
            Physics physics = new Physics();
            physics.getStorage().replace(course,physics.getStorage().get(course)-1);
        }
        if(course.getFaculty().equals("Art")){
            Art art = new Art();
            art.getStorage().replace(course,art.getStorage().get(course)-1);
        }
        if(course.getFaculty().equals("Literature")){
            Literature literature = new Literature();
            literature.getStorage().replace(course,literature.getStorage().get(course)-1);
        }

    }
    public void removeCourse(Course course){
        registeredCourses.remove(course);
    }


}
