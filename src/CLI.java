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

   private User currentUser = null;

   private Faculty currentFaculty = null;

    public CLI(Logic logic) {

        this.logic = logic;
    }

    public void init() {

        if(currentUser == null || sc.next().equals("back to first page.")) {
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

                    System.out.println("Please enter a password");
                    String newPassword = sc.next();

                    System.out.println("Please re-enter the password");
                    String newPassword1 = sc.next();

                    if (newPassword.equals(newPassword1)) {
                        System.out.println("You signed up successfully!");

                        dataBase.getUsers().put(studentNumber, newPassword);
                        studentLevel++;
                        currentUser = new User(studentNumber, UserType.STUDENT);
                        break;
                    } else {
                        System.out.println("Invalid inputs.Please try again.");
                        init();
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

                        currentUser = new User(adminPassword, UserType.ADMIN);

                    }
                    break;
                default:
                    System.out.println("Please enter a valid number.");
                    break;
            }
        }

        if (studentLevel==1 && currentUser.getUserType().equals(UserType.STUDENT)){
            System.out.println("choose an option\n1-List of all courses\n2-List of registered courses");
            String option = sc.next();

            switch (option){
                case "1" :
                    System.out.println("choose a faculty.\n1-Math\n2-Physics\n3-Art\n4-Literature");
                    String faculty = sc.next();

                    switch (faculty){
                        case "1" :
                            Math math = new Math();
                            for(Course course : math.getMathCourses()){
                                System.out.println((math.getMathCourses().indexOf(course)+1)+"-"+"Subject:"+course.getName()+"/Code:"+course.getCode()+"/Type:"+course.getType()+"/Class Time:"+course.getClassTime()+"/Credit:"+course.getCredit()+"/Teachers Name:"+course.getTeacher()+"/Exam Time:"+course.getExamTime()+"/Storage:"+math.getStorage().get(course));
                            }
                            Math math1 = new Math();
                            currentFaculty = math1;
                            studentLevel++;
                            math2++;
                            break;

                        case  "2" :
                            Physics physics = new Physics();
                            for(Course course : physics.getPhysicsCourses()){
                                System.out.println((physics.getPhysicsCourses().indexOf(course)+1)+"-"+"Subject:"+course.getName()+"/Code:"+course.getCode()+"/Type:"+course.getType()+"/Class Time:"+course.getClassTime()+"/Credit:"+course.getCredit()+"/Teachers Name:"+course.getTeacher()+"/Exam Time:"+course.getExamTime()+"/Storage:"+physics.getStorage().get(course));
                            }
                            Physics physics1 = new Physics();
                            currentFaculty = physics1;
                            studentLevel++;
                            physics2++;
                            break;

                        case "3" :
                            Art art = new Art();
                            for(Course course : art.getArtCourses()){
                                System.out.println((art.getArtCourses().indexOf(course)+1)+"-"+"Subject:"+course.getName()+"/Code:"+course.getCode()+"/Type:"+course.getType()+"/Class Time:"+course.getClassTime()+"/Credit:"+course.getCredit()+"/Teachers Name:"+course.getTeacher()+"/Exam Time:"+course.getExamTime()+"/Storage:"+art.getStorage().get(course));
                            }
                            Art art1 = new Art();
                            currentFaculty = art1;
                            studentLevel++;
                            art2++;
                            break;

                        case "4" :
                            Literature literature = new Literature();
                            for(Course course : literature.getLiteratureCourses()){
                                System.out.println((literature.getLiteratureCourses().indexOf(course)+1)+"-"+"Subject:"+course.getName()+"/Code:"+course.getCode()+"/Type:"+course.getType()+"/Class Time:"+course.getClassTime()+"/Credit:"+course.getCredit()+"/Teachers Name:"+course.getTeacher()+"/Exam Time:"+course.getExamTime()+"/Storage:"+literature.getStorage().get(course));
                            }
                            Literature literature1 = new Literature();
                            currentFaculty = literature1;
                            studentLevel++;
                            literature2++;
                            break;

                        default:
                            System.out.println("Invalid input.Please try again.");
                            init();
                    }
                    break;
                    //remove-course
                case "2" :
                        if(currentUser.getRegisteredCourses().isEmpty()){
                            System.out.println("No courses have been registered.");
                        }else{
                            for (Course course : currentUser.getRegisteredCourses()) {
                                if (course.getFaculty().equals("Math")) {
                                    Math math = new Math();
                                    System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" +math.getStorage().get(course));
                                }
                                if (course.getFaculty().equals("Physics")) {
                                    Physics physics = new Physics();
                                    System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" +physics.getStorage().get(course));
                                }
                                if (course.getFaculty().equals("Art")) {
                                    Art art = new Art();
                                    System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" +art.getStorage().get(course));
                                }
                                if (course.getFaculty().equals("Literature")) {
                                    Literature literature = new Literature();
                                    System.out.println((currentUser.getRegisteredCourses().indexOf(course) + 1) + "-" + "Subject:" + course.getName() + "/Code:" + course.getCode() + "/Type:" + course.getType() + "/Class Time:" + course.getClassTime() + "/Credit:" + course.getCredit() + "/Teachers Name:" + course.getTeacher() + "/Exam Time:" + course.getExamTime() + "/Storage:" +literature.getStorage().get(course));
                                }
                            }
                        }
                        break;




                default:
                    System.out.println("Invalid input.Please try again.");
                    init();

            }
        }
        //add-course
        if(studentLevel==2 && currentUser.getUserType().equals(UserType.STUDENT)){
            System.out.println("Choose a Course to register.");
            String chosenCourse = sc.next();
            int num = Integer.parseInt(chosenCourse)-1;
            if(math2==1) {
                Math math = new Math();
                if(!currentUser.getRegisteredCourses().isEmpty()) {
                    for (Course course : currentUser.getRegisteredCourses())
                        if (!course.getExamTime().equals(math.getMathCourses().get(num).getExamTime()) && !course.getClassTime().equals(math.getMathCourses().get(num).getClassTime()) && !course.getName().equals(math.getMathCourses().get(num).getName())&& math.getStorage().get(course)>0) {
                            currentUser.addCourse(math.getMathCourses().get(num));

                            System.out.println("Added successfully.");
                        }else{
                            System.out.println("Can't add this course.Please try again.");
                        }
                }
                if(currentUser.getRegisteredCourses().isEmpty()){
                    currentUser.addCourse(math.getMathCourses().get(num));
                    System.out.println("Added successfully.");
                }
            } else if (physics2==1) {
                Physics physics = new Physics();
                if(!currentUser.getRegisteredCourses().isEmpty()) {
                    for (Course course : currentUser.getRegisteredCourses())
                        if (!course.getExamTime().equals(physics.getPhysicsCourses().get(num).getExamTime()) && !course.getClassTime().equals(physics.getPhysicsCourses().get(num).getClassTime()) && !course.getName().equals(physics.getPhysicsCourses().get(num).getName()) && physics.getStorage().get(course) > 0) {
                            currentUser.addCourse(physics.getPhysicsCourses().get(num));

                            System.out.println("Added successfully.");
                        } else {
                            System.out.println("Can't add this course.Please try again.");
                        }
                }
                if(currentUser.getRegisteredCourses().isEmpty()){
                    currentUser.addCourse(physics.getPhysicsCourses().get(num));
                    System.out.println("Added successfully.");
                }
            } else if (art2==1) {
                Art art = new Art();
                if(!currentUser.getRegisteredCourses().isEmpty()) {
                    for (Course course : currentUser.getRegisteredCourses())
                        if (!course.getExamTime().equals(art.getArtCourses().get(num).getExamTime()) && !course.getClassTime().equals(art.getArtCourses().get(num).getClassTime()) && !course.getName().equals(art.getArtCourses().get(num).getName()) && art.getStorage().get(course) > 0) {
                            currentUser.addCourse(art.getArtCourses().get(num));

                            System.out.println("Added successfully.");
                        } else {
                            System.out.println("Can't add this course.Please try again.");
                        }
                }
                if(currentUser.getRegisteredCourses().isEmpty()){
                    currentUser.addCourse(art.getArtCourses().get(num));
                    System.out.println("Added successfully.");
                }

            } else if (literature2==1) {
                Literature literature = new Literature();
                if(!currentUser.getRegisteredCourses().isEmpty()) {
                    for (Course course : currentUser.getRegisteredCourses())
                        if (!course.getExamTime().equals(literature.getLiteratureCourses().get(num).getExamTime()) && !course.getClassTime().equals(literature.getLiteratureCourses().get(num).getClassTime()) && !course.getName().equals(literature.getLiteratureCourses().get(num).getName()) && literature.getStorage().get(course) > 0) {
                            currentUser.addCourse(literature.getLiteratureCourses().get(num));

                            System.out.println("Added successfully.");
                        } else {
                            System.out.println("Can't add this course.Please try again.");
                        }
                }
                if(currentUser.getRegisteredCourses().isEmpty()){
                    currentUser.addCourse(literature.getLiteratureCourses().get(num));
                    System.out.println("Added successfully.");
                }
            }
            init();

        }

    }
}
