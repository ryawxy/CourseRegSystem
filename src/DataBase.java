import Course.Course;
import Faculty.Faculty;
import Faculty.Math;
import Faculty.Physics;
import Faculty.Art;
import Faculty.Literature;
import User.User;
import User.UserType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBase {
    public DataBase() {
        initialUsers();
    }

    private static final HashMap<String, String> users = new HashMap<>();


    private static final List<User> registeredUsers = new ArrayList<>();

    private User LoggedInUser;

    public void initialUsers() {
        users.put("402131241", "1234");
        registeredUsers.add(new User("402131241", UserType.STUDENT));
    }

    public HashMap<String, String> getUsers() {
        return users;
    }

    public User getLoggedInUser() {
        return LoggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        LoggedInUser = loggedInUser;
    }

    public boolean alreadyExists(String studentNumber) {
        return users.containsKey(studentNumber);
    }

    public void addCourse(String code, String type, String tName, String cTime, String eTime, int storage, int credit, Faculty faculty, String name) {
        if (faculty.equals(Math.getMath())) {
            Course course = new Course(code, cTime, eTime, name, tName, credit, type, "Math");
            Math.getMath().getMathCourses().add(course);
            Math.getMath().getStorage().put(course, storage);


        } else if (faculty.equals(Physics.getPhysics())) {
            Course course = new Course(code, cTime, eTime, name, tName, credit, type, "Physics");
            Physics.getPhysics().getPhysicsCourses().add(course);
            Physics.getPhysics().getStorage().put(course, storage);


        } else if (faculty.equals(Art.getArt())) {
            Course course = new Course(code, cTime, eTime, name, tName, credit, type, "Art");
            Art.getArt().getArtCourses().add(course);
            Art.getArt().getStorage().put(course, storage);


        } else if (faculty.equals(Literature.getLiterature())) {
            Course course = new Course(code, cTime, eTime, name, tName, credit, type, "Literature");
            Literature.getLiterature().getLiteratureCourses().add(course);
            Literature.getLiterature().getStorage().put(course, storage);


        }

    }

    public String showDetails(Faculty faculty, String index) {
        if (faculty.equals(Math.getMath())) {


            List<User> users = new ArrayList<>();
            users = Math.getMath().getMathCourses().get((Integer.parseInt(index)) - 1).getRegisteredUsers();
            StringBuilder reg = new StringBuilder();

            if (users.isEmpty()) {
                return "No student registered this course.";
            } else {

                for (User students : users) {

                    reg.append(students.getUsername()).append("\n");

                }
                return reg.toString();
            }
        }
        if (faculty.equals(Physics.getPhysics())) {


            List<User> users = new ArrayList<>();
            users = Physics.getPhysics().getPhysicsCourses().get((Integer.parseInt(index)) - 1).getRegisteredUsers();
            StringBuilder reg = new StringBuilder();

            for (User students : users) {

                reg.append(students.getUsername()).append("\n");

            }
            return reg.toString();
        }
        if (faculty.equals(Art.getArt())) {


            List<User> users = new ArrayList<>();
            users = Art.getArt().getArtCourses().get((Integer.parseInt(index)) - 1).getRegisteredUsers();

            StringBuilder reg = new StringBuilder();


            for (User students : users) {

                reg.append(students.getUsername()).append("\n");

            }
            return reg.toString();
        }
        if (faculty.equals(Literature.getLiterature())) {


            List<User> users = new ArrayList<>();
            users = Literature.getLiterature().getLiteratureCourses().get((Integer.parseInt(index)) - 1).getRegisteredUsers();
            StringBuilder reg = new StringBuilder();

            for (User students : users) {

                reg.append(students.getUsername()).append("\n");

            }
            return reg.toString();
        }
        return null;
    }

    public static List<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public void removeCourse(Faculty faculty, Course course) {
        if (faculty.equals(Math.getMath())) {
            Math.getMath().getMathCourses().remove(course);
            course.getRegisteredUsers().clear();

            for (User user : registeredUsers) {
                user.getRegisteredCourses().remove(course);
            }

        } else if (faculty.equals(Physics.getPhysics())) {
            Physics.getPhysics().getPhysicsCourses().remove(course);
            course.getRegisteredUsers().clear();

            for (User user : registeredUsers) {
                user.getRegisteredCourses().remove(course);
            }

        } else if (faculty.equals(Art.getArt())) {
            Art.getArt().getArtCourses().remove(course);
            course.getRegisteredUsers().clear();

            for (User user : registeredUsers) {
                user.getRegisteredCourses().remove(course);
            }

        } else if (faculty.equals(Literature.getLiterature())) {
            Literature.getLiterature().getLiteratureCourses().remove(course);
            course.getRegisteredUsers().clear();

            for (User user : registeredUsers) {
                user.getRegisteredCourses().remove(course);
            }

        }
    }
    public void addStorage(Faculty faculty , Course course,int number){
        if(faculty.equals(Math.getMath())){
            int prevStorage = Math.getStorage().get(course);
          Math.getStorage().replace(course, prevStorage+number);

        } else if(faculty.equals(Physics.getPhysics())){
            int prevStorage = Physics.getStorage().get(course);
            Physics.getStorage().replace(course, prevStorage+number);

        }else if(faculty.equals(Art.getArt())){
            int prevStorage = Art.getStorage().get(course);
            Art.getStorage().replace(course, prevStorage+number);

        }else if(faculty.equals(Literature.getLiterature())){
            int prevStorage = Literature.getStorage().get(course);
            Literature.getStorage().replace(course, prevStorage+number);
        }

    }
    public Course findCourse(Faculty faculty, String number){
        try {


            if (faculty.equals(Math.getMath())) {
                return Math.getMath().getMathCourses().get(Integer.parseInt(number) - 1);

            } else if (faculty.equals(Physics.getPhysics())) {
                return Physics.getPhysics().getPhysicsCourses().get(Integer.parseInt(number) - 1);

            } else if (faculty.equals(Art.getArt())) {
                return Art.getArt().getArtCourses().get(Integer.parseInt(number) - 1);

            } else if (faculty.equals(Literature.getLiterature())) {
                return Math.getMath().getMathCourses().get(Integer.parseInt(number) - 1);

            }
        }catch (NumberFormatException e){

        }
        return null;
    }
    public void addStudent(User user , Course course){
        try {


            boolean valid = true;

            if (!user.getRegisteredCourses().isEmpty()) {
                for (Course course1 : user.getRegisteredCourses()) {
                    if (course.getExamTime().equals(course1.getExamTime()) || course.getClassTime().equals(course1.getClassTime()) || course.getStorage(course) <= 0) {
                        valid = false;
                    }
                }
                if (valid) {
                    user.addCourse(course, user);
                }
            } else {
                user.addCourse(course, user);
            }
        }catch (NumberFormatException e){

        }


    }

    public void removeStudent(User user, Course course){

        boolean valid = getRegisteredUsers().contains(user);

        if(valid){
          if(user.getRegisteredCourses().contains(course)){
              user.removeCourse(course, user);
              System.out.println("Chosen student removed from this course successfully.");
          }else{
              System.out.println("Chosen student doesn't have this course.");
          }
      }

    }







    public User findStudent(String studentNum){
        for(User user : getRegisteredUsers()){
            if (user.getUsername().equals(studentNum)){
                return user;
            }
        }
        if(!getUsers().containsKey(studentNum)){
            System.out.println("No such student exist");
        }
        return null;
    }


}
