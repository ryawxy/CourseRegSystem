import Course.Course;
import Faculty.*;
import Faculty.Math;

import java.util.Objects;
import java.util.Scanner;

public class CLI {
    private final Scanner sc = new Scanner(System.in);

    private final Logic logic;

    private final DataBase dataBase = new DataBase();

    private int studentLevel = 0;
    private int adminLevel = 0;

    private int math2 = 0;

    private int physics2 = 0;

    private int art2 = 0;

    private int literature2 = 0;

    private int level = 0;

    private int level2 = 0;

    private int level3 = 0;

    private User currentUser = null;

    private Faculty currentFaculty = null;

    public CLI(Logic logic) {

        this.logic = logic;
    }

    public void init() {

        if (currentUser == null) {
            if (level == 0) {
                System.out.println("Welcome to the course registration system!\nChoose an option.");
                System.out.println("1-Login as student");
                System.out.println("2-Sign-up as student");
                System.out.println("3-Login as admin");
                String input = sc.next();

                switch (input) {
                    case "1":
                        System.out.println(dataBase.getUsers());
                        System.out.println("Please enter your student number.");
                        String studentNum = sc.next();
                        if (dataBase.getUsers().containsKey(studentNum)) {
                            System.out.println("Please enter your password");
                            String password = sc.next();
                            if (dataBase.getUsers().get(studentNum).equals(password)) {
                                System.out.println("You logged-in successfully!");
                                studentLevel++;
                                level++;
                                currentUser = new User(studentNum, UserType.STUDENT);
                            } else {
                                System.out.println("Invalid password.Please try again.");
                                init();
                            }

                        } else {
                            System.out.println("No such student number exists.Please try again or sign up.");
                            init();
                        }
                        break;

                    case "2":
                        System.out.println("Please enter your student number.");
                        String studentNumber = sc.next();
                        if(dataBase.alreadyExists(studentNumber)){
                            System.out.println("You already have an account.Please Login");
                            init();
                        }else {

                            System.out.println("Please enter a password");
                            String newPassword = sc.next();

                            System.out.println("Please re-enter the password");
                            String newPassword1 = sc.next();

                            if (newPassword.equals(newPassword1)) {
                                System.out.println("You signed up successfully!");

                                dataBase.getUsers().put(studentNumber, newPassword);
                                studentLevel++;
                                level++;
                                currentUser = new User(studentNumber, UserType.STUDENT);
                                break;
                            } else {
                                System.out.println("Invalid inputs.Please try again.");
                                init();
                            }
                        }
                    case "3":
                        System.out.println("Please enter the password.");
                        String adminPassword = sc.next();

                        User user = new User(adminPassword, UserType.ADMIN);

                        if (!adminPassword.equals(user.getAdminsPassword())) {
                            System.out.println("Password is invalid.");
                        } else {

                            System.out.println("you logged in successfully as admin.");
                            adminLevel++;
                            level++;

                            currentUser = new User(adminPassword, UserType.ADMIN);

                        }
                        break;
                    case "back":
                        init();
                    default:
                        System.out.println("Please enter a valid number.");
                        init();
                        break;
                }
            }
        }


        if (studentLevel == 1) {
            assert currentUser != null;
            if (currentUser.getUserType().equals(UserType.STUDENT)) {
                if (level == 1) {
                    System.out.println("choose an option\n1-List of all courses\n2-List of registered courses");
                    String option = sc.next();

                    switch (option) {
                        case "1":
                            System.out.println("choose a faculty.\n1-Math\n2-Physics\n3-Art\n4-Literature");


                            level++;
                            level2++;

                            break;

                            //show registered courses
                        case "2":
                            if (currentUser.getRegisteredCourses().isEmpty()) {
                                System.out.println("No courses have been registered.");
                            } else {
                                for (Course course : currentUser.getRegisteredCourses()) {
                                    if (course.getFaculty().equals("Math")) {

                                        System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" +"Faculty:Math" +"/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Math.getMath().getStorage().get(course));
                                    }
                                    if (course.getFaculty().equals("Physics")) {

                                        System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" +"Faculty:Physics"+ "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Physics.getPhysics().getStorage().get(course));
                                    }
                                    if (course.getFaculty().equals("Art")) {

                                        System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" +"Faculty:Art"+ "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Art.getArt().getStorage().get(course));
                                    }
                                    if (course.getFaculty().equals("Literature")) {

                                        System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" +"Faculty:Literature"+ "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Literature.getLiterature().getStorage().get(course));
                                    }
                                    studentLevel = 5;
                                }
                            }
                            level++;
                            break;

                        case "back":
                            currentUser = null;
                            level = 0;
                            init();

                            break;

                        case "exit":
                            currentUser = null;
                            studentLevel=0;
                            level=0;
                            init();
                            break;


                        default:
                            System.out.println("Invalid input.Please try again.");
                            init();
                    }
                }
            }
        }
        if(studentLevel==1 && currentUser.getUserType().equals(UserType.STUDENT)) {
            if (level == 2) {
                String faculty = sc.next();

                switch (faculty) {
                    case "1":


                        for (Course course : Math.getMath().getMathCourses()) {
                            System.out.println((Math.getMath().getMathCourses().indexOf(course) + 1) + "-" +"Faculty:Math"+ "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Math.getMath().getStorage().get(course));
                        }


                        currentFaculty = Math.getMath();

                        studentLevel++;
                        math2++;
                        level++;
                        break;


                    case "2":

                        for (Course course : Physics.getPhysics().getPhysicsCourses()) {
                            System.out.println((Physics.getPhysics().getPhysicsCourses().indexOf(course) + 1) + "-" +"Faculty:Physics"+ "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Physics.getPhysics().getStorage().get(course));
                        }

                        currentFaculty = Physics.getPhysics();
                        studentLevel++;
                        physics2++;
                        level++;
                        break;

                    case "3":

                        for (Course course : Art.getArt().getArtCourses()) {
                            System.out.println((Art.getArt().getArtCourses().indexOf(course) + 1) + "-" +"Faculty:Art"+ "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Art.getArt().getStorage().get(course));
                        }

                        currentFaculty = Art.getArt();
                        studentLevel++;
                        art2++;
                        level++;
                        break;

                    case "4":

                        for (Course course : Literature.getLiterature().getLiteratureCourses()) {
                            System.out.println((Literature.getLiterature().getLiteratureCourses().indexOf(course) + 1) + "-" +"Faculty:Literature"+ "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Literature.getLiterature().getStorage().get(course));
                        }

                        currentFaculty = Literature.getLiterature();
                        studentLevel++;
                        literature2++;
                        level++;
                        break;

                    case "back":
                        level--;
                        init();
                        break;

                    case "exit":
                        level = 0;
                        currentUser = null;
                        studentLevel = 0;
                        adminLevel = 0;
                        init();
                        break;


                    default:
                        System.out.println("Invalid input.Please try again.");
                        init();

                }
            }

        }





        //add-course
        if (studentLevel == 2 && currentUser.getUserType().equals(UserType.STUDENT)) {
            System.out.println("Choose a Course to register.");
            String chosenCourse = sc.next();
            if (level == 3) {
                try {

                    if (chosenCourse.equals("back")) {
                        level = 1;
                        studentLevel = 1;
                        math2--;
                        physics2--;
                        art2--;
                        literature2--;
                        currentFaculty = null;
                        init();

                    }
                    if (chosenCourse.equals("exit")) {
                        level = 0;
                        studentLevel = 0;
                        currentUser=null;
                        math2=0;
                        physics2=0;
                        art2=0;
                        literature2=0;
                        init();
                    }


                    if (currentFaculty.equals(Math.getMath())) {

                        int num = Integer.parseInt(chosenCourse) - 1;
                        if (!currentUser.getRegisteredCourses().isEmpty()) {
                            boolean valid = true;

                            for (Course course : currentUser.getRegisteredCourses()) {

                                if (course.getExamTime().equals(Math.getMath().getMathCourses().get(num).getExamTime()) || course.getClassTime().equals(Math.getMath().getMathCourses().get(num).getClassTime()) || course.getName().equals(Math.getMath().getMathCourses().get(num).getName()) || course.getStorage(course) <= 0) {

                                    valid = false;
                                }
                            }
                            Course course2 = Math.getMath().getMathCourses().get(num);

                            if (valid) {
                                currentUser.addCourse(course2);


                                System.out.println("Added successfully.");


                            } else {
                                System.out.println("Can't add this course.Please try again.");
                            }
                        }

                        if (currentUser.getRegisteredCourses().isEmpty()) {
                            Course course2 = Math.getMath().getMathCourses().get(num);

                            currentUser.addCourse(course2);

                            System.out.println("Added successfully.");

                        }

                        } else if (currentFaculty.equals(Physics.getPhysics())) {
                        int num = Integer.parseInt(chosenCourse) - 1;

                            boolean valid = true;



                            if (!currentUser.getRegisteredCourses().isEmpty()) {
                                for (Course course : currentUser.getRegisteredCourses()) {
                                    if (course.getExamTime().equals(Physics.getPhysics().getPhysicsCourses().get(num).getExamTime()) || course.getClassTime().equals(Physics.getPhysics().getPhysicsCourses().get(num).getClassTime()) || course.getName().equals(Physics.getPhysics().getPhysicsCourses().get(num).getName()) || course.getStorage(course) <= 0) {
                                        valid = false;
                                    }
                                }
                                      if(valid) {
                                          Course course2 = Physics.getPhysics().getPhysicsCourses().get(num);
                                          currentUser.addCourse(course2);

                                          System.out.println("Added successfully.");


                                    } else {
                                        System.out.println("Can't add this course.Please try again.");
                                    }
                            }
                            if (currentUser.getRegisteredCourses().isEmpty()) {
                                Course course2 = Physics.getPhysics().getPhysicsCourses().get(num);
                                currentUser.addCourse(course2);
                                System.out.println("Added successfully.");

                            }
                        } else if (currentFaculty.equals(Art.getArt())) {
                        int num = Integer.parseInt(chosenCourse) - 1;

                            boolean valid = true;

                            if (!currentUser.getRegisteredCourses().isEmpty()) {

                                for (Course course : currentUser.getRegisteredCourses()) {


                                    if (course.getExamTime().equals(Art.getArt().getArtCourses().get(num).getExamTime()) || course.getClassTime().equals(Art.getArt().getArtCourses().get(num).getClassTime()) || course.getName().equals(Art.getArt().getArtCourses().get(num).getName()) || course.getStorage(course) <= 0) {

                                        valid = false;
                                    }
                                }
                                      if(valid){
                                          Course course2 = Art.getArt().getArtCourses().get(num);
                                        currentUser.addCourse(course2);

                                        System.out.println("Added successfully.");

                                    } else {
                                        System.out.println("Can't add this course.Please try again.");
                                    }
                            }
                            if (currentUser.getRegisteredCourses().isEmpty()) {
                                Course course2 = Art.getArt().getArtCourses().get(num);
                                currentUser.addCourse(course2);
                                System.out.println("Added successfully.");

                            }

                        } else if (currentFaculty.equals(Literature.getLiterature())) {

                        int num = Integer.parseInt(chosenCourse) - 1;

                            if (!currentUser.getRegisteredCourses().isEmpty()) {
                                boolean valid = true;
                                for (Course course : currentUser.getRegisteredCourses()) {
                                    if (course.getExamTime().equals(Literature.getLiterature().getLiteratureCourses().get(num).getExamTime()) || course.getClassTime().equals(Literature.getLiterature().getLiteratureCourses().get(num).getClassTime()) || course.getName().equals(Literature.getLiterature().getLiteratureCourses().get(num).getName()) || course.getStorage(course) <= 0) {

                                        valid = false;
                                    }
                                }
                                if(valid){
                                    Course course2 = Literature.getLiterature().getLiteratureCourses().get(num);
                                        currentUser.addCourse(course2);

                                        System.out.println("Added successfully.");

                                    } else {
                                        System.out.println("Can't add this course.Please try again.");
                                    }
                            }
                            if (currentUser.getRegisteredCourses().isEmpty()) {
                                Course course2 = Literature.getLiterature().getLiteratureCourses().get(num);
                                currentUser.addCourse(course2);
                                System.out.println("Added successfully.");

                            }
                        }



                }
                catch (NumberFormatException e){
                    if (chosenCourse.equals("back")) {
                        level = 2;
                        studentLevel = 1;
                        init();
                    }

                }
                init();

            }


        }
        if(level==2 && studentLevel==5 && currentUser.getUserType().equals(UserType.STUDENT)){
            System.out.println("Choose a course to remove from registered courses.");
            String removedCourse = sc.next();
            try{
                if(removedCourse.equals("back")){
                    level=1;
                    studentLevel=1;
                    currentFaculty=null;
                    init();
                }
                if(removedCourse.equals("exit")){
                    level=0;
                    studentLevel=0;
                    currentFaculty=null;
                    currentUser=null;
                    init();
                }
                int num = Integer.parseInt(removedCourse);
                if(num<=currentUser.getRegisteredCourses().size()){
                    System.out.println("Course:"+currentUser.getRegisteredCourses().get(num-1).getName()+" removed successfully.");
                    currentUser.removeCourse(currentUser.getRegisteredCourses().get(num-1));
                    System.out.println("Updated version of registered courses:");
                    if(!currentUser.getRegisteredCourses().isEmpty()){
                    for(Course course : currentUser.getRegisteredCourses()){
                        if (course.getFaculty().equals("Math")) {

                            System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" +"Faculty:Math" +"/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Math.getMath().getStorage().get(course));
                        }
                        if (course.getFaculty().equals("Physics")) {

                            System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" +"Faculty:Physics"+ "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Physics.getPhysics().getStorage().get(course));
                        }
                        if (course.getFaculty().equals("Art")) {

                            System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" +"Faculty:Art"+ "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Art.getArt().getStorage().get(course));
                        }
                        if (course.getFaculty().equals("Literature")) {

                            System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" +"Faculty:Literature"+ "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Literature.getLiterature().getStorage().get(course));
                        }
                    }
                    }else{
                        System.out.println("No course has been registered.");
                    }
                }else{
                    System.out.println("Invalid input.Please try again.");
                }

            }catch(NumberFormatException e){
                if(removedCourse.equals("back")){
                    level=1;
                    studentLevel=1;
                    currentFaculty=null;
                    init();
                }
                if(removedCourse.equals("exit")){
                    level=0;
                    studentLevel=0;
                    currentFaculty=null;
                    currentUser=null;
                    init();
                }

            }
            init();
        }
    }
}
