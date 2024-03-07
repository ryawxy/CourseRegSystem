package EventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import Course.Course;
import DataBase.DataBase;
import Faculty.Math;
import Faculty.Physics;
import Faculty.Art;
import Faculty.Literature;
import User.User;
import User.UserType;

public class EventListener {

DataBase dataBase = new DataBase();
    public void export(String string) throws IOException {

        File file = new File(string);

        PrintWriter writer = new PrintWriter(file);
        writer.println(Math.getMath().getMathCourses().size());
        writer.println(Physics.getPhysics().getPhysicsCourses().size());
        writer.println(Art.getArt().getArtCourses().size());
        writer.println(Literature.getLiterature().getLiteratureCourses().size());



        for (Course course : Math.getMath().getMathCourses()) {
            writer.println(course.getCode());
            writer.println(course.getClassTime());
            writer.println(course.getExamTime());
            writer.println(course.getName());
            writer.println(course.getTeacher());
            writer.println(course.getCredit());
            writer.println(course.getType());
            writer.println(course.getFaculty());
            writer.println(course.getStorage(course));


            StringBuilder str = new StringBuilder();

                for (int j = 0; j < course.getRegisteredUsers().size(); j++) {

                    str.append(course.getRegisteredUsers().get(j).getUsername()).append("/");


                }
                if(str.isEmpty()){
                    writer.println("n");
                }else {

                    writer.println(str);
                }
            str= new StringBuilder();
        }



        for (Course course : Physics.getPhysics().getPhysicsCourses()) {

            StringBuilder str = new StringBuilder();

            writer.println(course.getCode());
            writer.println(course.getClassTime());
            writer.println(course.getExamTime());
            writer.println(course.getName());
            writer.println(course.getTeacher());
            writer.println(course.getCredit());
            writer.println(course.getType());
            writer.println(course.getFaculty());
            writer.println(course.getStorage(course));



                for (int j = 0; j < course.getRegisteredUsers().size(); j++) {

                    str.append(course.getRegisteredUsers().get(j).getUsername()).append("/");

                }

            if(str.isEmpty()){
                writer.println("n");
            }else {

                writer.println(str);
            }
            str = new StringBuilder();
        }

        for (Course course : Art.getArt().getArtCourses()) {

            StringBuilder str = new StringBuilder();

            writer.println(course.getCode());
            writer.println(course.getClassTime());
            writer.println(course.getExamTime());
            writer.println(course.getName());
            writer.println(course.getTeacher());
            writer.println(course.getCredit());
            writer.println(course.getType());
            writer.println(course.getFaculty());
            writer.println(course.getStorage(course));



                for (int j = 0; j < course.getRegisteredUsers().size(); j++) {
                    str.append(course.getRegisteredUsers().get(j).getUsername()).append("/");
                }

            if(str.isEmpty()){
                writer.println("n");
            }else {

                writer.println(str);
            }
            str = new StringBuilder();
        }

        for (Course course : Literature.getLiterature().getLiteratureCourses()) {

            StringBuilder str = new StringBuilder();

            writer.println(course.getCode());
            writer.println(course.getClassTime());
            writer.println(course.getExamTime());
            writer.println(course.getName());
            writer.println(course.getTeacher());
            writer.println(course.getCredit());
            writer.println(course.getType());
            writer.println(course.getFaculty());
            writer.println(course.getStorage(course));



                for (int j = 0; j < course.getRegisteredUsers().size(); j++) {
                    str.append(course.getRegisteredUsers().get(j).getUsername()).append("/");
                }




            if(str.isEmpty()){
                writer.println("n");
            }else {

                writer.println(str);
            }


        }
       StringBuilder str = new StringBuilder();
        for(User user : DataBase.getRegisteredUsers()){
            writer.println(user.getUsername()+":"+dataBase.getUsers().get(user.getUsername()));
            for(int i = 0;i<user.getRegisteredCourses().size();i++){
                str.append(user.getRegisteredCourses().get(i).getName()).append("/");
            }
            if(str.isEmpty()){
                writer.println("n");
            }else {
                writer.println(str);
            }
            str = new StringBuilder();
        }



        writer.flush();
        writer.close();

    }

    public void importFile(String path) throws FileNotFoundException {

        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);

            int lineNumber = 1;
            int level = 1;
            int level1 = 1;
            int level2 = 0;
            String code = "";
            String cTime = "";
            String eTime = "";
            String name = "";
            String teacher = "";
            String credit = "";
            String type = "";
            String faculty = "";
            String storage = "";
            String user1 = "";
            int math = 0;
            int physics = 0;
            int art = 0;
            int literature = 0;
            int stop = 0;
            int [] faculty1 = {math , physics , art , literature};
            String[] userNames = (String[]) new ArrayList<>().toArray(new String[0]);

            int i = 0;
            if (file.exists()) {
                if (file.length() != 0) {
                    Math.getMath().getMathCourses().clear();
                    Physics.getPhysics().getPhysicsCourses().clear();
                    Art.getArt().getArtCourses().clear();
                    Literature.getLiterature().getLiteratureCourses().clear();
                    DataBase.getRegisteredUsers().clear();
                    dataBase.getUsers().clear();

                    while (sc.hasNextLine() && lineNumber <= 4) {
                        String s = sc.nextLine();
                        if (level1 == 1) {
                            math = Integer.parseInt(s);
                            faculty1[0] = math;
                        } else if (level1 == 2) {
                            physics = Integer.parseInt(s);
                            faculty1[1] = physics;
                        } else if (level1 == 3) {
                            art = Integer.parseInt(s);
                            faculty1[2] = art;
                        } else if (level1 == 4) {
                            literature = Integer.parseInt(s);
                            faculty1[3] = literature;
                        }
                        level1++;
                        lineNumber++;
                        if (level1 == 5) {
                            lineNumber = 1;
                            break;
                        }
                    }
                    int line = 10 * (math + physics + art + literature);
                    if (faculty1[i] != 0) {


                        while (sc.hasNextLine() && lineNumber <= faculty1[i] * 10 && stop < line) {

                            String s = sc.nextLine();
                            if (level % 10 == 1) {
                                code = s;
                            } else if (level % 10 == 2) {
                                cTime = s;
                            } else if (level % 10 == 3) {
                                eTime = s;
                            } else if (level % 10 == 4) {
                                name = s;
                            } else if (level % 10 == 5) {
                                teacher = s;
                            } else if (level % 10 == 6) {
                                credit = s;
                            } else if (level % 10 == 7) {
                                type = s;
                            } else if (level % 10 == 8) {
                                faculty = s;
                            } else if (level % 10 == 9) {
                                storage = s;
                            } else if (level % 10 == 0) {
                                if (!Objects.equals(s, "n")) {
                                    userNames = s.split("/");
                                } else {
                                    user1 = "n";
                                }

                            }
                            if (level % 10 == 0) {
                                Course course = new Course(code, cTime, eTime, name, teacher, Integer.parseInt(credit), type, faculty);
                                level2++;
                                if (i == 0) {
                                    Math.getMath().getMathCourses().add(course);
                                    Math.getStorage().put(course, Integer.parseInt(storage));

                                    if (!user1.equals("n")) {
                                        boolean valid = true;
                                        for (String users : userNames) {
                                            valid = true;
                                            for (User user2 : DataBase.getRegisteredUsers()) {
                                                if (user2.getUsername().equals(users)) {
                                                    valid = false;
                                                    course.getRegisteredUsers().add(user2);
                                                }

                                            }

                                            if (valid) {
                                                    User user = new User(users, UserType.STUDENT);
                                                    course.getRegisteredUsers().add(user);
                                                    DataBase.getRegisteredUsers().add(user);
                                                }
                                            }

                                        userNames = (String[]) new ArrayList<>().toArray(new String[0]);
                                    } else {
                                        course.getRegisteredUsers().clear();
                                        user1 = "";
                                    }

                                } else if (i == 1) {
                                    Physics.getPhysics().getPhysicsCourses().add(course);
                                    Physics.getStorage().put(course, Integer.parseInt(storage));


                                    if (!user1.equals("n")) {
                                        boolean valid = true;
                                        for (String users : userNames) {
                                            valid = true;
                                            for (User user2 : DataBase.getRegisteredUsers()) {
                                                if (user2.getUsername().equals(users)) {
                                                    valid = false;
                                                    course.getRegisteredUsers().add(user2);
                                                }

                                            }


                                            if (valid) {

                                                    User user = new User(users, UserType.STUDENT);
                                                    course.getRegisteredUsers().add(user);
                                                    DataBase.getRegisteredUsers().add(user);
                                                }
                                            }

                                        userNames = (String[]) new ArrayList<>().toArray(new String[0]);
                                    } else {
                                        course.getRegisteredUsers().clear();
                                        user1 = "";
                                    }
                                } else if (i == 2) {
                                    Art.getArt().getArtCourses().add(course);
                                    Art.getStorage().put(course, Integer.parseInt(storage));

                                    if (!user1.equals("n")) {
                                        boolean valid = true;
                                        for (String users : userNames) {
                                            valid = true;
                                            for (User user2 : DataBase.getRegisteredUsers()) {
                                                if (user2.getUsername().equals(users)) {
                                                    valid = false;
                                                    course.getRegisteredUsers().add(user2);
                                                }

                                            }


                                            if (valid) {

                                                    User user = new User(users, UserType.STUDENT);
                                                    course.getRegisteredUsers().add(user);
                                                    DataBase.getRegisteredUsers().add(user);
                                                }
                                            }

                                        userNames = (String[]) new ArrayList<>().toArray(new String[0]);
                                    } else {
                                        course.getRegisteredUsers().clear();
                                        user1 = "";
                                    }
                                } else if (i == 3) {
                                    Literature.getLiterature().getLiteratureCourses().add(course);
                                    Literature.getStorage().put(course, Integer.parseInt(storage));

                                    if (!user1.equals("n")) {
                                        boolean valid = true;
                                        for (String users : userNames) {
                                            valid = true;
                                            for (User user2 : DataBase.getRegisteredUsers()) {
                                                if (user2.getUsername().equals(users)) {
                                                    valid = false;
                                                    course.getRegisteredUsers().add(user2);
                                                }

                                            }

                                            if (valid) {

                                                    User user = new User(users, UserType.STUDENT);
                                                    course.getRegisteredUsers().add(user);
                                                    DataBase.getRegisteredUsers().add(user);
                                                }
                                            }

                                        userNames = (String[]) new ArrayList<>().toArray(new String[0]);
                                    } else {
                                        course.getRegisteredUsers().clear();
                                        user1 = "";
                                    }
                                }

                                level = 0;
                            }
                            level++;
                            stop++;
                            if (level2 == faculty1[i]) {
                                if (i <= 2) {
                                    i++;
                                }
                                lineNumber = 1;
                                level2 = 0;
                            }


                        }

                    }
                    level = 1;
                    lineNumber = 1;

                    String[] userPassword = new String[2];
                    String course2 = "";
                    String[] course = (String[]) new ArrayList<>().toArray(new String[0]);

                    while (sc.hasNextLine() && lineNumber <= 2) {
                        String s = sc.nextLine();


                        if (level == 1) {
                            userPassword = s.split(":");

                        } else if (level == 2) {
                            if (!s.equals("n")) {
                                course = s.split("/");
                            } else {
                                course2 = "n";
                            }
                        }


                        if (level == 2) {
                            dataBase.getUsers().put(userPassword[0], userPassword[1]);

                            if (!course2.equals("n")) {
                                for (String string : course) {
                                    Course course1 = dataBase.find(string);
                                    for (User user2 : course1.getRegisteredUsers()) {
                                        if (user2.getUsername().equals(userPassword[0])) {
                                            user2.getRegisteredCourses().add(course1);


                                        }
                                    }
                                }
                            } else {
                                User user = new User(userPassword[0], UserType.STUDENT);
                                DataBase.getRegisteredUsers().add(user);
                            }
                            level = 1;
                            lineNumber = 1;
                        } else {
                            level++;
                            lineNumber++;
                        }


                    }


                }
            }
        }catch (NumberFormatException e){

        }
    }
}
