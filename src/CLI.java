import Course.Course;
import DataBase.DataBase;
import Faculty.*;
import Faculty.Math;
import User.User;
import User.UserType;

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

    private final int level3 = 0;

    private User currentUser = null;

    private Faculty currentFaculty = null;

    private Course currentCourse = null;

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
                        if(studentNum.equals("back") || studentNum.equals("exit")){
                            init();
                        }
                        if (dataBase.getUsers().containsKey(studentNum)) {
                            System.out.println("Please enter your password");
                            String password = sc.next();
                            if(password.equals("back") || password.equals("exit")){
                                init();
                            }
                            if (dataBase.getUsers().get(studentNum).equals(password)) {
                                System.out.println("You logged-in successfully!");
                                studentLevel++;
                                level++;

                                for (User user : DataBase.getRegisteredUsers()) {
                                    if (user.getUsername().equals(studentNum)) {
                                        currentUser = user;
                                        break;
                                    }
                                }

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
                        if(studentNumber.equals("back") || studentNumber.equals("exit")){
                            init();
                        }
                        if (dataBase.alreadyExists(studentNumber)) {
                            System.out.println("You already have an account.Please Login");
                            init();
                        } else {

                            System.out.println("Please enter a password");
                            String newPassword = sc.next();

                            if(newPassword.equals("back") || newPassword.equals("exit")){
                                init();
                            }

                            System.out.println("Please re-enter the password");
                            String newPassword1 = sc.next();

                            if(newPassword1.equals("back") || newPassword1.equals("exit")){
                                init();
                            }



                            if (newPassword.equals(newPassword1)) {
                                System.out.println("You signed up successfully!");

                                dataBase.getUsers().put(studentNumber, newPassword);
                                User user2 = new User(studentNumber,UserType.STUDENT);
                                DataBase.getRegisteredUsers().add(user2);

                                studentLevel++;
                                level++;
                                currentUser = user2;
                                break;
                            } else {
                                System.out.println("Invalid inputs.Please try again.");
                                init();
                            }
                        }
                    case "3":
                        System.out.println("Please enter the password.");
                        String adminPassword = sc.next();

                        if(adminPassword.equals("back") || adminPassword.equals("exit")){
                            studentLevel=0;
                            level=0;
                            init();
                        }

                        User user = new User(adminPassword, UserType.ADMIN);

                        if (!adminPassword.equals(user.getAdminsPassword())) {
                            System.out.println("Password is invalid.Please try again.");
                            init();
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

                                        System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Faculty:Math" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Math.getMath().getStorage().get(course));
                                    }
                                    if (course.getFaculty().equals("Physics")) {

                                        System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Faculty:Physics" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Physics.getPhysics().getStorage().get(course));
                                    }
                                    if (course.getFaculty().equals("Art")) {

                                        System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Faculty:Art" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Art.getArt().getStorage().get(course));
                                    }
                                    if (course.getFaculty().equals("Literature")) {

                                        System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Faculty:Literature" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Literature.getLiterature().getStorage().get(course));
                                    }
                                    studentLevel = 5;
                                }
                            }
                            level++;
                            break;

                        case "back":
                            currentUser = null;
                            level = 0;
                            studentLevel = 0;

                            init();

                            break;

                        case "exit":
                            currentUser = null;
                            studentLevel = 0;
                            level = 0;
                            currentCourse = null;
                            init();
                            break;


                        default:
                            System.out.println("Invalid input.Please try again.");
                            init();
                    }
                }
            }
        }
        if (studentLevel == 1 && currentUser.getUserType().equals(UserType.STUDENT)) {
            if (level == 2) {
                String faculty = sc.next();

                switch (faculty) {
                    case "1":


                        for (Course course : Math.getMath().getMathCourses()) {
                            System.out.println((Math.getMath().getMathCourses().indexOf(course) + 1) + "-" + "Faculty:Math" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Math.getMath().getStorage().get(course));
                        }


                        currentFaculty = Math.getMath();

                        studentLevel++;
                        math2++;
                        level++;
                        break;


                    case "2":

                        for (Course course : Physics.getPhysics().getPhysicsCourses()) {
                            System.out.println((Physics.getPhysics().getPhysicsCourses().indexOf(course) + 1) + "-" + "Faculty:Physics" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Physics.getPhysics().getStorage().get(course));
                        }

                        currentFaculty = Physics.getPhysics();
                        studentLevel++;
                        physics2++;
                        level++;
                        break;

                    case "3":

                        for (Course course : Art.getArt().getArtCourses()) {
                            System.out.println((Art.getArt().getArtCourses().indexOf(course) + 1) + "-" + "Faculty:Art" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Art.getArt().getStorage().get(course));
                        }

                        currentFaculty = Art.getArt();
                        studentLevel++;
                        art2++;
                        level++;
                        break;

                    case "4":

                        for (Course course : Literature.getLiterature().getLiteratureCourses()) {
                            System.out.println((Literature.getLiterature().getLiteratureCourses().indexOf(course) + 1) + "-" + "Faculty:Literature" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Literature.getLiterature().getStorage().get(course));
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
                        currentCourse = null;
                        init();
                        break;


                    default:
                        System.out.println("Invalid input.Please try again.");
                        init();

                }
            }

        }


        //add-course
        if (studentLevel == 2) {
            assert currentUser != null;
            if (currentUser.getUserType().equals(UserType.STUDENT)) {
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
                            currentUser = null;
                            math2 = 0;
                            physics2 = 0;
                            art2 = 0;
                            literature2 = 0;
                            currentCourse=null;
                            currentFaculty = null;
                            init();
                        }
                        Course course = dataBase.findCourse(currentFaculty, chosenCourse);
                        if (dataBase.regRules(currentUser, course)) {
                            currentUser.addCourse(course, currentUser);

                            System.out.println("Course added successfully");
                        }


                    } catch (NumberFormatException e) {
                        if (chosenCourse.equals("back")) {
                            level = 2;
                            studentLevel = 1;
                            init();
                        }

                    }

                    init();

                }


            }
        }
        if (level == 2 && studentLevel == 5) {
            assert currentUser != null;
            if (currentUser.getUserType().equals(UserType.STUDENT)) {
                System.out.println("Choose a course to remove from registered courses.");
                String removedCourse = sc.next();
                try {
                    if (removedCourse.equals("back")) {
                        level = 1;
                        studentLevel = 1;
                        currentFaculty = null;
                        init();
                    }
                    if (removedCourse.equals("exit")) {
                        level = 0;
                        studentLevel = 0;
                        currentFaculty = null;
                        currentUser = null;
                        currentCourse=null;
                        init();
                    }
                    int num = Integer.parseInt(removedCourse);
                    if (num <= currentUser.getRegisteredCourses().size()) {
                        System.out.println("Course.Course:" + currentUser.getRegisteredCourses().get(num - 1).getName() + " removed successfully.");
                        currentUser.removeCourse(currentUser.getRegisteredCourses().get(num - 1), currentUser);
                        System.out.println("Updated version of registered courses:");
                        if (!currentUser.getRegisteredCourses().isEmpty()) {
                            for (Course course : currentUser.getRegisteredCourses()) {
                                if (course.getFaculty().equals("Math")) {

                                    System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Faculty:Math" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Math.getMath().getStorage().get(course));
                                }
                                if (course.getFaculty().equals("Physics")) {

                                    System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Faculty:Physics" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Physics.getPhysics().getStorage().get(course));
                                }
                                if (course.getFaculty().equals("Art")) {

                                    System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Faculty:Art" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Art.getArt().getStorage().get(course));
                                }
                                if (course.getFaculty().equals("Literature")) {

                                    System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Faculty:Literature" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Literature.getLiterature().getStorage().get(course));
                                }
                            }
                        } else {
                            System.out.println("No course has been registered.");
                        }
                    } else {
                        System.out.println("Invalid input.Please try again.");
                    }

                } catch (NumberFormatException e) {
                    if (removedCourse.equals("back")) {
                        level = 1;
                        studentLevel = 1;
                        currentFaculty = null;
                        init();
                    }
                    if (removedCourse.equals("exit")) {
                        level = 0;
                        studentLevel = 0;
                        currentFaculty = null;
                        currentUser = null;
                        currentCourse = null;
                        init();
                    }

                }
                init();
            }
        }
        if (adminLevel == 1 && level == 1) {
            assert currentUser != null;
            if (currentUser.getUserType().equals(UserType.ADMIN)) {
                System.out.println("Choose a faculty\n1-Math\n2-Physics\n3-Art\n4-Literature");

                String faculty = sc.next();

                switch (faculty) {
                    case "1":

                        for (Course course : Math.getMath().getMathCourses()) {
                            System.out.println((Math.getMath().getMathCourses().indexOf(course) + 1) + "-" + "Faculty:Math" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Math.getMath().getStorage().get(course));
                        }

                        currentFaculty = Math.getMath();
                        adminLevel++;
                        level++;
                        break;


                    case "2":

                        for (Course course : Physics.getPhysics().getPhysicsCourses()) {
                            System.out.println((Physics.getPhysics().getPhysicsCourses().indexOf(course) + 1) + "-" + "Faculty:Physics" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Physics.getPhysics().getStorage().get(course));
                        }

                        currentFaculty = Physics.getPhysics();
                        adminLevel++;
                        level++;
                        break;

                    case "3":

                        for (Course course : Art.getArt().getArtCourses()) {
                            System.out.println((Art.getArt().getArtCourses().indexOf(course) + 1) + "-" + "Faculty:Art" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Art.getArt().getStorage().get(course));
                        }

                        currentFaculty = Art.getArt();
                        adminLevel++;
                        level++;
                        break;

                    case "4":

                        for (Course course : Literature.getLiterature().getLiteratureCourses()) {
                            System.out.println((Literature.getLiterature().getLiteratureCourses().indexOf(course) + 1) + "-" + "Faculty:Literature" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Literature.getLiterature().getStorage().get(course));
                        }

                        currentFaculty = Literature.getLiterature();
                        adminLevel++;
                        level++;
                        break;

                    case "back":
                        level = 0;
                        adminLevel = 0;
                        studentLevel = 0;
                        currentUser = null;
                        init();
                        break;

                    case "exit":
                        level = 0;
                        currentUser = null;
                        studentLevel = 0;
                        adminLevel = 0;
                        currentCourse = null;
                        currentFaculty = null;
                        init();
                        break;


                    default:
                        System.out.println("Invalid input.Please try again.");
                        init();


                }
            }
        }
        if (adminLevel == 2 && level == 2) {
            assert currentUser != null;
            if (currentUser.getUserType().equals(UserType.ADMIN)) {
                System.out.println("Choose an option:\n1-Add course\n2-Remove course\n3-Choose course\n4-Add storage to a course");
                String option = sc.next();
                switch (option) {
                    case "1":
                        System.out.println("Please enter code of the course.");
                        String code = sc.next();

                        System.out.println("Please enter name of the course.");
                        String name = sc.next();

                        System.out.println("Please enter type of the course.");
                        String type = sc.next();

                        System.out.println("Please enter teachers name of the course.");
                        String tName = sc.next();

                        System.out.println("Please enter class time of the course.");
                        String cTime = sc.next();

                        System.out.println("Please enter exam time of the course.");
                        String eTime = sc.next();

                        System.out.println("Please enter storage of the course.");
                        int storage = sc.nextInt();

                        System.out.println("Please enter credit of the course.");
                        int credit = sc.nextInt();

                        dataBase.addCourse(code, type, tName, cTime, eTime, storage, credit, currentFaculty, name);
                        System.out.println("Course:"+name+"created successfully.");
                        break;


                    case "2" :
                        System.out.println("Please choose a course to remove.");
                        String chosenCourse1 = sc.next();
                        Course course = Math.getMath().getMathCourses().get(Integer.parseInt(chosenCourse1)-1);
                        dataBase.removeCourse(Math.getMath(),course);
                        System.out.println("Course removed successfully.");
                        break;


                    case "3":
                        System.out.println("Choose a course to see the details.");
                        String chosenCourse = sc.next();
                        currentCourse = dataBase.findCourse(currentFaculty,chosenCourse);
                        if(chosenCourse.equals("back")){
                            adminLevel = 1;
                            level = 1;
                            init();
                        }
                        if(chosenCourse.equals("exit")){
                            adminLevel = 0;
                            level = 0;
                            currentCourse = null;
                            currentUser = null;
                            currentFaculty = null;
                            init();
                        }


                        System.out.println("List of registered students for this course:");

                        System.out.println(dataBase.showDetails(currentFaculty, chosenCourse));
                        adminLevel++;
                        level++;
                        break;

                    case "4" :
                        System.out.println("Choose a course to add storage to.");
                        String chosenCourse2 = sc.next();
                        Course course1 = dataBase.findCourse(currentFaculty,chosenCourse2);
                        System.out.println("please enter the number of storage you want to add.");
                        String num = sc.next();
                        int add = Integer.parseInt(num);
                        dataBase.addStorage(currentFaculty,course1,add);
                        System.out.println("Storage added successfully.");
                        break;

                    case "back":
                        adminLevel = 1;
                        level = 1;
                        init();

                    case "exit":
                        studentLevel = 0;
                        level = 0;
                        adminLevel = 0;
                        currentUser = null;
                        currentFaculty = null;
                        currentCourse=null;
                        init();
                    default:
                        System.out.println("Invalid input. Please try again.");
                        init();
                }

            }
        }
        if(adminLevel == 3 && level == 3) {
            assert currentUser != null;
            if (currentUser.getUserType().equals(UserType.ADMIN)) {
                System.out.println("Choose an option:\n1-add student\n2-Remove student");
                String option = sc.next();

                switch (option){
                    case "1":
                        System.out.println("Enter the student number.");
                        String studentNum = sc.next();
                        if(dataBase.getUsers().containsKey(studentNum)) {
                            User user = dataBase.findStudent(studentNum);
                            if(dataBase.regRules(user,currentCourse)) {
                                dataBase.addStudent(user, currentCourse);
                                System.out.println("Chosen student added to this course successfully.");
                                break;
                            }
                        }else{
                            System.out.println("No such student exist.");
                        }
                        break;


                    case "2" :
                        System.out.println("Enter the student number.");
                        String studentNum2 = sc.next();
                      User user2 = dataBase.findStudent(studentNum2);
                      dataBase.removeStudent(user2,currentCourse);
                      break;


                    case "exit":
                        adminLevel=0;
                        currentUser=null;
                        level=0;
                        studentLevel=0;
                        currentCourse=null;
                        currentFaculty = null;
                        init();

                    case "back":
                        adminLevel=2;
                        level=2;
                        init();

                }
            }
        }
        init();
    }
}
