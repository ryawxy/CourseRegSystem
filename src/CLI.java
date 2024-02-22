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
        if(currentUser == null) {
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

                case "2" :



                default:
                    System.out.println("Invalid input.Please try again.");
                    init();

            }
        }


    }
}
