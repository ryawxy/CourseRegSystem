import Course.Course;
import DataBase.DataBase;
import EventListener.EventListener;
import Faculty.*;
import Faculty.Math;
import User.User;
import User.UserType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CLI {
    private final Scanner sc = new Scanner(System.in);

    private final Logic logic;

    private final DataBase dataBase = new DataBase();

    private int studentLevel = 0;
    private int adminLevel = 0;

    private int level = 0;

    private int level2 = 0;

    private int level3 = 0;

    private int level4 = 0;

    private int level5 = 0;

    private User currentUser = null;

    private Faculty currentFaculty = null;

    private Course currentCourse = null;

    private final EventListener eventListener = new EventListener();

    private final HashMap<String , String> info = new HashMap<>();

    public CLI(Logic logic) {

        this.logic = logic;
    }

    public void init() throws IOException {

        if (currentUser == null) {
            if (level == 0) {
                System.out.println("Welcome to the course registration system!\nChoose an option.");
                System.out.println("1-Login as student");
                System.out.println("2-Sign-up as student");
                System.out.println("3-Login as admin");
                String input = sc.next();

                switch (input) {
                    case "1":

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
                String option = "";
                if (level == 1) {
                    if (level3 == 0) {
                        System.out.println("choose an option\n1-List of all courses\n2-List of registered courses");
                        option = sc.next();
                    }
                    if (level3 == 1) {
                        option = "1";
                    }


                    switch (option) {
                        case "1":
                            level3 = 1;
                            System.out.println("choose a faculty.\n1-Math\n2-Physics\n3-Art\n4-Literature");


                            level++;

                            level2++;

                            break;

                        //show registered courses
                        case "2":
                            if (currentUser.getRegisteredCourses().isEmpty()) {
                                System.out.println("No courses have been registered.");
                                studentLevel =1;
                                level = 1;
                                level3 = 0;
                                init();
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
                            currentFaculty = null;
                            adminLevel = 0;
                            level3 = 0;
                            init();
                            break;

                        default:
                            System.out.println("Invalid input.Please try again.");
                            level = 1;
                            studentLevel = 1;

                            init();
                    }
                }
            }
         //   level3 = 0;
        }
        if(currentUser!=null) {
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

                            level++;
                            break;


                        case "2":

                            for (Course course : Physics.getPhysics().getPhysicsCourses()) {
                                System.out.println((Physics.getPhysics().getPhysicsCourses().indexOf(course) + 1) + "-" + "Faculty:Physics" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Physics.getPhysics().getStorage().get(course));
                            }

                            currentFaculty = Physics.getPhysics();
                            studentLevel++;

                            level++;
                            break;

                        case "3":

                            for (Course course : Art.getArt().getArtCourses()) {
                                System.out.println((Art.getArt().getArtCourses().indexOf(course) + 1) + "-" + "Faculty:Art" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Art.getArt().getStorage().get(course));
                            }

                            currentFaculty = Art.getArt();
                            studentLevel++;

                            level++;
                            break;

                        case "4":

                            for (Course course : Literature.getLiterature().getLiteratureCourses()) {
                                System.out.println((Literature.getLiterature().getLiteratureCourses().indexOf(course) + 1) + "-" + "Faculty:Literature" + "/Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" + Literature.getLiterature().getStorage().get(course));
                            }

                            currentFaculty = Literature.getLiterature();
                            studentLevel++;

                            level++;
                            break;

                        case "back":
                            level = 1;
                            studentLevel = 1;
                            level3 = 0;
                            init();
                            break;

                        case "exit":
                            level = 0;
                            currentUser = null;
                            studentLevel = 0;
                            adminLevel = 0;
                            currentCourse = null;
                            level3 = 0;
                            init();
                            break;


                        default:
                            System.out.println("Invalid input.Please try again.");
                            studentLevel = 1;
                            level = 1;
                            init();

                    }
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
                            currentFaculty = null;
                            level3 = 1;
                            init();

                        }
                        if (chosenCourse.equals("exit")) {
                            level = 0;
                            studentLevel = 0;
                            currentUser = null;
                            currentCourse=null;
                            currentFaculty = null;
                            init();
                        }
                        Course course = dataBase.findCourse(currentFaculty, chosenCourse);
                        if(course!=null) {
                            if (dataBase.regRules(currentUser, course)) {
                                currentUser.addCourse(course, currentUser);

                                System.out.println("Course added successfully");
                            }
                            }else{
                           //     System.out.println("Invalid input.Please try again.");
                                init();
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
                        System.out.println("Course:" + currentUser.getRegisteredCourses().get(num - 1).getName() + " removed successfully.");
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

                    System.out.println("Invalid input.Please try again.");
                    init();

                }
                init();
            }
        }
        if(adminLevel == 1 && level ==1){
            assert  currentUser != null;
            if(currentUser.getUserType().equals(UserType.ADMIN)){
                System.out.println("Choose an option:\n1-Import\n2-Export\n3-Show Faculties");
                String option = sc.next();
                switch (option){

                    case "1" :
                        System.out.println("Please enter the absolute path of the file, you want to import from.");
                        String path1 = sc.next();

                        if(path1.equals("back")){
                            adminLevel = 1;
                            level = 1;
                            init();
                        }
                        if (path1.equals("exit")){
                            adminLevel=0;
                            currentUser=null;
                            currentFaculty=null;
                            studentLevel=0;
                            level=0;
                            init();
                        }
                        File file = new File(path1);
                        if(!file.exists()){
                            System.out.println("No such file exists.");
                        }else {

                            eventListener.importFile(path1);
                            System.out.println("Data imported successfully.");
                        }
                        break;

                    case "2" :

                        System.out.println("Please enter the absolute path of a file. ");
                        String path = sc.next();
                        if(path.equals("back")){
                            adminLevel = 1;
                            level = 1;
                            init();
                        }
                        if (path.equals("exit")){
                            adminLevel=0;
                            currentUser=null;
                            currentFaculty=null;
                            studentLevel=0;
                            level=0;
                            init();
                        }
                        File file1 = new File(path);

                        if(!file1.exists()){
                            System.out.println("No such file exists.");
                        }else {

                            eventListener.export(path);

                            System.out.println("Data exported successfully.");
                        }
                        break;

                    case "3" :
                     adminLevel++;
                     level++;
                     init();
                     break;

                    case "back" :
                    adminLevel = 0;
                    level = 0;
                    currentUser = null;
                    init();

                    case "exit" :
                        adminLevel = 0;
                        level = 0;
                        currentUser = null;
                        init();

                    default :
                        System.out.println("Invalid input.Please try again.");
                        init();
                }
            }
        }

        if (adminLevel == 2 && level == 2) {
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
                        level = 1;
                        adminLevel = 1;
                        studentLevel = 0;
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
        if (adminLevel == 3 && level == 3) {
            assert currentUser != null;
            if (currentUser.getUserType().equals(UserType.ADMIN)) {
                String option = "";
                if(level5 == 0) {
                    System.out.println("Choose an option:\n1-Add course\n2-Remove course\n3-Choose course\n4-Add storage to a course");
                     option = sc.next();
                }
                if(level5 ==1){
                    option ="1";
                }
                if(level5 ==4){
                    option ="4";
                }
                if(level5 ==2){
                    option ="2";
                }
                if(level5 ==3){
                    option ="3";
                }

                if(option.equals("back")){
                    adminLevel =2;
                    level = 2;
                    init();
                }
                if(option.equals("exit")){
                    adminLevel =0;
                    level = 0;
                    currentUser = null;
                    level5 = 0;
                    init();
                }

                switch (option) {

                    case "1":
                        level5 = 1;
                        if(level4 == 0) {
                            System.out.println("Please enter code of the course.");
                          String code = sc.next();
                          if (!code.equals("back") && !code.equals("exit")) {
                              info.put("code", code);
                          }
                            level4 = 1;

                            if (code.equals("back")) {
                                adminLevel = 3;
                                level = 3;
                                level5 = 0;
                                init();
                            }
                            if (code.equals("exit")) {
                                adminLevel = 0;
                                level = 0;
                                currentUser = null;
                                level5= 0;
                                init();
                            }
                        }
                        if(level4 ==1) {

                            System.out.println("Please enter name of the course.");
                             String name = sc.next();
                            if (!name.equals("back") && !name.equals("exit")) {
                                info.put("name", name);
                            }
                            level4 = 2;


                            if (name.equals("back")) {
                                adminLevel = 3;
                                level = 3;
                                level4 = 0;

                                init();
                            }
                            if (name.equals("exit")) {
                                adminLevel = 0;
                                level = 0;
                                currentUser = null;
                                level5 = 0;
                                level4 = 0;
                                init();
                            }
                        }
                        if(level4 == 2) {

                            System.out.println("Please enter type of the course.");
                            String type = sc.next();

                            if (!type.equals("back") && !type.equals("exit")) {
                                info.put("type", type);
                            }
                            level4 = 3;


                            if (type.equals("back")) {
                                adminLevel = 3;
                                level = 3;
                                level4 = 1;
                                init();
                            }
                            if (type.equals("exit")) {
                                adminLevel = 0;
                                level = 0;
                                currentUser = null;
                                level4 = 0;
                                level5 = 0;
                                init();
                            }
                        }
                        if(level4 == 3) {

                            System.out.println("Please enter teachers name of the course.");
                             String tName = sc.next();

                            if (!tName.equals("back") && !tName.equals("exit")) {
                                info.put("tName", tName);
                            }
                            level4 = 4;



                            if (tName.equals("back")) {
                                adminLevel = 3;
                                level = 3;
                                level4 = 2;
                                init();
                            }
                            if (tName.equals("exit")) {
                                adminLevel = 0;
                                level = 0;
                                currentUser = null;
                                level4 = 0;
                                level5 = 0;
                                init();
                            }
                        }
                        if(level4 == 4) {

                            System.out.println("Please enter class time of the course.");
                           String cTime = sc.next();

                            if (!cTime.equals("back") && !cTime.equals("exit")) {
                                info.put("cTime", cTime);
                            }

                            level4 = 5;

                            if (cTime.equals("back")) {
                                adminLevel = 3;
                                level = 3;
                                level4 = 3;
                                init();
                            }
                            if (cTime.equals("exit")) {
                                adminLevel = 0;
                                level = 0;
                                currentUser = null;
                                level5 = 0;
                                level4 = 0;
                                init();
                            }
                        }
                        if(level4 == 5) {

                            System.out.println("Please enter exam time of the course.");
                           String  eTime = sc.next();

                            if (!eTime.equals("back") && !eTime.equals("exit")) {
                                info.put("eTime", eTime);
                            }

                            level4 = 6;

                            if (eTime.equals("back")) {
                                adminLevel = 3;
                                level = 3;
                                level4 = 4;
                                init();
                            }
                            if (eTime.equals("exit")) {
                                adminLevel = 0;
                                level = 0;
                                level4 = 0;
                                level5 = 0;
                                currentUser = null;
                                init();
                            }
                        }
                        if(level4 ==6) {

                            System.out.println("Please enter storage of the course.");
                           String storage = sc.next();

                            if (!storage.equals("back") && !storage.equals("exit")) {

                                try{
                                    int storage2 = Integer.parseInt(storage);
                                }catch (NumberFormatException e){
                                    System.out.println("Invalid input.Please try again.");
                                    adminLevel = 3;
                                    level = 3;
                                    level4 = 6;
                                    init();
                                }
                                info.put("storage", storage);
                            }


                            level4 = 7;

                            if (storage.equals("back")) {
                                adminLevel = 3;
                                level = 3;
                                level4 = 5;
                                init();
                            }
                            if (storage.equals("exit")) {
                                adminLevel = 0;
                                level = 0;
                                level4 = 0;
                                level5 = 0;
                                currentUser = null;
                                init();
                            }
                        }
                        if(level4 ==7) {

                            System.out.println("Please enter credit of the course.");
                            String credit = sc.next();

                            if (!credit.equals("back") && !credit.equals("exit")) {
                                try{
                                    int credit2 = Integer.parseInt(credit);
                                }catch (NumberFormatException e){
                                    System.out.println("Invalid input.Please try again.");
                                    adminLevel = 3;
                                    level = 3;
                                    level4 = 7;
                                    init();
                                }
                                info.put("credit", credit);
                            }
                            level4 = 8;

                            if (credit.equals("back")) {
                                adminLevel = 3;
                                level = 3;
                                level4 = 6;
                                init();
                            }
                            if (credit.equals("exit")) {
                                adminLevel = 0;
                                level = 0;
                                level4 = 0;
                                level5 = 0;
                                currentUser = null;
                                init();
                            }
                        }

                        dataBase.addCourse(info.get("code"), info.get("type"), info.get("tName"),
                                info.get("cTime"), info.get("eTime"), info.get("storage"),
                                info.get("credit"), currentFaculty,info.get("name"));
                        System.out.println("Course:"+info.get("name")+" created successfully.");
                        level5 = 0;
                        level4 = 0;
                        break;


                    case "2" :
                        level5 = 2;
                        System.out.println("Please choose a course to remove.");
                        String chosenCourse1 = sc.next();

                        if(chosenCourse1.equals("back")){
                            adminLevel =3;
                            level = 3;
                            level5 = 0;
                            level4 = 0;
                            init();
                        }
                        if(chosenCourse1.equals("exit")){
                            adminLevel =0;
                            level = 0;
                            currentUser = null;
                            level5 = 0;
                            level4 = 0;
                            init();
                        }
                        Course course2 = dataBase.findCourse(currentFaculty , chosenCourse1);

                        if(course2==null){
                            init();
                        }else {
                            dataBase.removeCourse(currentFaculty, course2);
                        }

                        break;


                    case "3":
                        level5 = 3;
                        System.out.println("Choose a course to see the details.");
                        String chosenCourse = sc.next();

                        if(chosenCourse.equals("back")){
                            adminLevel = 3;
                            level = 3;
                            level5 = 0;
                            level4 = 0;
                            init();
                        }
                        if(chosenCourse.equals("exit")){
                            adminLevel = 0;
                            level = 0;
                            currentCourse = null;
                            currentUser = null;
                            currentFaculty = null;
                            level5 = 0;
                            level4 = 0;
                            init();
                        }
                        currentCourse = dataBase.findCourse(currentFaculty,chosenCourse);

                        if(currentCourse != null && !chosenCourse.equals("back") && !chosenCourse.equals("exit")) {
                            System.out.println("List of registered students for this course:");

                            System.out.println(dataBase.showDetails(currentFaculty, chosenCourse));
                            adminLevel++;
                            level++;
                        }
                        else {

                            init();
                        }
                            break;

                    case "4" :
                        level5 = 4;
                        System.out.println("Choose a course to add storage to.");
                        String chosenCourse2 = sc.next();

                        if(chosenCourse2.equals("back")){
                            adminLevel =3;
                            level = 3;
                            level5 = 0;
                            init();
                        }
                        if(chosenCourse2.equals("exit")){
                            adminLevel =0;
                            level = 0;
                            currentUser = null;
                            level5 = 0;
                            level4 = 0;
                            init();
                        }

                        Course course1 = dataBase.findCourse(currentFaculty,chosenCourse2);
                        if(course1 == null){
                            init();
                        }

                        System.out.println("Please enter the number of storage you want to add.");
                        level5 = 4;
                        String num = sc.next();
                        if(num.equals("back")){
                            adminLevel =3;
                            level = 3;
                            init();
                        }
                        if(num.equals("exit")){
                            adminLevel =0;
                            level = 0;
                            currentUser = null;
                            init();
                        }

                        try {
                            if(course1 != null) {
                                int add = Integer.parseInt(num);
                                dataBase.addStorage(currentFaculty, course1, add);
                                System.out.println("Storage added successfully.");
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Invalid input.Please try again");
                            init();
                        }
                        break;

                    case "back":
                        adminLevel = 2;
                        level = 2;
                        level4 = 0;
                        level5 = 0;
                        init();

                    case "exit":
                        studentLevel = 0;
                        level = 0;
                        adminLevel = 0;
                        currentUser = null;
                        currentFaculty = null;
                        currentCourse=null;
                        level5 = 0;
                        level4 = 0;
                        init();
                    default:
                        System.out.println("Invalid input. Please try again.");
                        init();
                }

            }
        }
        if(adminLevel == 4 && level == 4) {
            assert currentUser != null;
            if (currentUser.getUserType().equals(UserType.ADMIN)) {
                System.out.println("Choose an option:\n1-add student\n2-Remove student");
                String option = sc.next();

                switch (option){
                    case "1":
                        System.out.println("Enter the student number.");
                        String studentNum = sc.next();

                        if(studentNum.equals("back")){
                            adminLevel =4;
                            level = 4;
                            init();
                        }
                        if(studentNum.equals("exit")){
                            adminLevel =0;
                            level = 0;
                            currentUser = null;
                            init();
                        }
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

                        if(studentNum2.equals("back")){
                            adminLevel =4;
                            level = 4;
                            init();
                        }
                        if(studentNum2.equals("exit")){
                            adminLevel =0;
                            level = 0;
                            currentUser = null;
                            init();
                        }

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
                        level5 = 0;
                        level4 = 0;
                        init();

                    case "back":
                        adminLevel=3;
                        level=3;
                        init();

                    default :
                        System.out.println("Invalid input.Please try again.");
                }
            }
        }
        level4 = 0;
        level5 = 0;
        init();
    }
}
