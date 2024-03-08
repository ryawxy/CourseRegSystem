package DataBase;

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

    }
    private static final HashMap<String, String> users = new HashMap<>();


    private static final List<User> registeredUsers = new ArrayList<>();

    public void initialUsers() {
        users.put("402131241", "1234");
        registeredUsers.add(new User("402131241", UserType.STUDENT));
    }

    public HashMap<String, String> getUsers() {
        return users;
    }

    public boolean alreadyExists(String studentNumber) {
        return users.containsKey(studentNumber);
    }

    public void addCourse(String code, String type, String tName, String cTime, String eTime, String storage, String credit, Faculty faculty, String name) {
        try {
            if (faculty.equals(Math.getMath())) {
                Course course = new Course(code, cTime, eTime, name, tName, Integer.parseInt(credit), type, "Math");
                Math.getMath().getMathCourses().add(course);
                Math.getStorage().put(course, Integer.parseInt(storage));


            } else if (faculty.equals(Physics.getPhysics())) {
                Course course = new Course(code, cTime, eTime, name, tName, Integer.parseInt(credit), type, "Physics");
                Physics.getPhysics().getPhysicsCourses().add(course);
                Physics.getStorage().put(course, Integer.parseInt(storage));


            } else if (faculty.equals(Art.getArt())) {
                Course course = new Course(code, cTime, eTime, name, tName, Integer.parseInt(credit), type, "Art");
                Art.getArt().getArtCourses().add(course);
                Art.getStorage().put(course, Integer.parseInt(storage));


            } else if (faculty.equals(Literature.getLiterature())) {
                Course course = new Course(code, cTime, eTime, name, tName, Integer.parseInt(credit), type, "Literature");
                Literature.getLiterature().getLiteratureCourses().add(course);
                Literature.getStorage().put(course, Integer.parseInt(storage));


            }

        } catch (NumberFormatException e) {

        }
    }

    public String showDetails(Faculty faculty, String index) {
        if (faculty.equals(Math.getMath())) {


            List<User> users = new ArrayList<>();
            users = Math.getMath().getMathCourses().get((Integer.parseInt(index)) - 1).getRegisteredUsers();
            StringBuilder reg = new StringBuilder();

            if (users.isEmpty()) {
                return "No student has registered this course.";
            } else {

                for (User students : users) {

                    reg.append(students.getUsername()).append("\n");

                }
                return reg.toString();
            }
        }
        if (faculty.equals(Physics.getPhysics())) {

            if (faculty.equals(Physics.getPhysics())) {


                List<User> users = new ArrayList<>();
                users = Physics.getPhysics().getPhysicsCourses().get((Integer.parseInt(index)) - 1).getRegisteredUsers();
                StringBuilder reg = new StringBuilder();

                if (users.isEmpty()) {
                    return "No student has registered this course.";
                } else {

                    for (User students : users) {

                        reg.append(students.getUsername()).append("\n");

                    }
                    return reg.toString();
                }
            }
        }
        if (faculty.equals(Art.getArt())) {

            if (faculty.equals(Art.getArt())) {


                List<User> users = new ArrayList<>();
                users = Art.getArt().getArtCourses().get((Integer.parseInt(index)) - 1).getRegisteredUsers();
                StringBuilder reg = new StringBuilder();

                if (users.isEmpty()) {
                    return "No student has registered this course.";
                } else {

                    for (User students : users) {

                        reg.append(students.getUsername()).append("\n");

                    }
                    return reg.toString();
                }
            }
        }
        if (faculty.equals(Literature.getLiterature())) {

            if (faculty.equals(Literature.getLiterature())) {


                List<User> users = new ArrayList<>();
                users = Literature.getLiterature().getLiteratureCourses().get((Integer.parseInt(index)) - 1).getRegisteredUsers();
                StringBuilder reg = new StringBuilder();

                if (users.isEmpty()) {
                    return "No student has registered this course.";
                } else {

                    for (User students : users) {

                        reg.append(students.getUsername()).append("\n");

                    }
                    return reg.toString();
                }
            }
        }
        return null;
    }

    public static List<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public void removeCourse(Faculty faculty, Course course) {

        try {

            if (course != null) {
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
                System.out.println("Course removed successfully.");
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Invalid input.Please try again.");
        }
    }

    public void addStorage(Faculty faculty, Course course, int number) {
        if (faculty.equals(Math.getMath())) {
            int prevStorage = Math.getStorage().get(course);
            Math.getStorage().replace(course, prevStorage + number);

        } else if (faculty.equals(Physics.getPhysics())) {
            int prevStorage = Physics.getStorage().get(course);
            Physics.getStorage().replace(course, prevStorage + number);

        } else if (faculty.equals(Art.getArt())) {
            int prevStorage = Art.getStorage().get(course);
            Art.getStorage().replace(course, prevStorage + number);

        } else if (faculty.equals(Literature.getLiterature())) {
            int prevStorage = Literature.getStorage().get(course);
            Literature.getStorage().replace(course, prevStorage + number);
        }

    }

    public Course findCourse(Faculty faculty, String number) {
        try {
            try {


                try {
                    if (faculty.equals(Math.getMath())) {
                        if (Integer.parseInt(number) <= Math.getMath().getMathCourses().size() && Integer.parseInt(number)>0) {
                            return Math.getMath().getMathCourses().get(Integer.parseInt(number) - 1);
                        } else {
                            System.out.println("Invalid input.Please try again.");
                        }

                    } else if (faculty.equals(Physics.getPhysics())) {
                        if (Integer.parseInt(number) <= Physics.getPhysics().getPhysicsCourses().size() && Integer.parseInt(number)>0) {
                            return Physics.getPhysics().getPhysicsCourses().get(Integer.parseInt(number) - 1);
                        } else {
                            System.out.println("Invalid input.Please try again.");
                        }
                    } else if (faculty.equals(Art.getArt())) {
                        if (Integer.parseInt(number) <= Art.getArt().getArtCourses().size() && Integer.parseInt(number)>0) {
                            return Art.getArt().getArtCourses().get(Integer.parseInt(number) - 1);
                        } else {
                            System.out.println("Invalid input.Please try again.");
                        }
                    } else if (faculty.equals(Literature.getLiterature())) {
                        if (Integer.parseInt(number) <= Literature.getLiterature().getLiteratureCourses().size() && Integer.parseInt(number)>0) {
                            return Literature.getLiterature().getLiteratureCourses().get(Integer.parseInt(number) - 1);
                        } else {
                            System.out.println("Invalid input.Please try again.");
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.Please try again.");

                }
            } catch (ArrayIndexOutOfBoundsException e) {
           //     System.out.println("Invalid input.Please try again.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        //    System.out.println("Invalid input.Please try again.");
        }
        return null;
    }

    public void addStudent(User user, Course course) {
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
        } catch (NumberFormatException e) {

        }


    }

    public void removeStudent(User user, Course course) {

        boolean valid = getRegisteredUsers().contains(user);

        if (valid) {
            if (user.getRegisteredCourses().contains(course)) {
                user.removeCourse(course, user);
                System.out.println("Chosen student removed from this course successfully.");
            } else {
                System.out.println("Chosen student doesn't have this course.");
            }
        }
        if(course.getRegisteredUsers().isEmpty()){
            System.out.println("No student has registered this course.");
        }

    }

    public User findStudent(String studentNum) {
        for (User user : getRegisteredUsers()) {
            if (user.getUsername().equals(studentNum)) {
                return user;
            }
        }
        if (!getUsers().containsKey(studentNum)) {
            System.out.println("No such student exist");
        }
        return null;
    }

    public boolean regRules(User user, Course course) {
        try {
            int sum = 0;
            boolean valid = true;
            boolean valid2 = true;

            boolean valid3 = course.getStorage(course) > 0;
            boolean valid4 = true;
            boolean valid5 = true;
            boolean valid6 = true;
            for (Course course1 : user.getRegisteredCourses()) {
                if (course1.equals(course)) {
                    System.out.println("Already registered this course.");
                    valid6 = false;
                }
            }

            if (valid6) {
                for (Course course1 : user.getRegisteredCourses()) {
                    sum += course1.getCredit();
                }
                if (sum + course.getCredit() > 20) {
                    valid = false;
                    System.out.println("Cant have more courses.");
                }


                for (Course course1 : user.getRegisteredCourses()) {

                    if (course1.getClassTime().equals(course.getClassTime())) {
                        valid2 = false;
                        System.out.println("Cant add this course.Another class on this time. ");
                        break;
                    }
                }

                if (!valid3) {
                    System.out.println("There is no storage for this course.");
                }

                int sum2 = 0;
                for (Course course1 : user.getRegisteredCourses()) {

                    if (course1.getType().equals("general")) {
                        sum2 += course1.getCredit();
                    }
                }
                if (course.getType().equals("general")) {
                    if (sum2 + course.getCredit() > 5) {
                        valid4 = false;
                        System.out.println("Reached the limit.Cant have more general courses.");
                    }
                }


                for (Course course1 : user.getRegisteredCourses()) {
                    if (!course.equals(course1))
                        if (course1.getExamTime().equals(course.getExamTime())) {
                            valid5 = false;
                            System.out.println("Cant add this course.Another exam on this time. ");
                            break;
                        }
                }
            }

            return valid && valid2 && valid3 && valid4 && valid5 && valid6;
        } catch (NullPointerException e) {
            System.out.println("Invalid input.Please try again.");
        }
        return false;
    }
    public Course find(String name){
        for(Course course : Math.getMath().getMathCourses()){
            if(course.getName().equals(name)){
                return course;
            }
        } for(Course course : Physics.getPhysics().getPhysicsCourses()){
            if(course.getName().equals(name)){
                return course;
            }
        } for(Course course : Art.getArt().getArtCourses()){
            if(course.getName().equals(name)){
                return course;
            }
        } for(Course course : Literature.getLiterature().getLiteratureCourses()){
            if(course.getName().equals(name)){
                return course;
            }
        }
        return null;
    }
}
