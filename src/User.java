import Course.Course;

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
    }
    public void removeCourse(Course course){
        registeredCourses.remove(course);
    }


}
