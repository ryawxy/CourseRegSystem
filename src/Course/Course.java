package Course;

import Faculty.Art;
import Faculty.Literature;
import Faculty.Math;
import Faculty.Physics;
import User.User;

import java.util.ArrayList;
import java.util.List;


public  class Course {

private final String code;

private final String classTime;

private final String examTime;

private final String name;

private final String teacher;

private final int credit;

private final String type;

private final String faculty;

private final List<User> registeredUsers = new ArrayList<>();

    public Course( String code, String classTime, String examTime, String name, String teacher, int credit,String type,String faculty) {
        this.code = code;
        this.classTime = classTime;
        this.examTime = examTime;
        this.name = name;
        this.teacher = teacher;
        this.credit = credit;
        this.type = type;
        this.faculty = faculty;
    }

    public String getCode() {
        return code;
    }

    public String getClassTime() {
        return classTime;
    }

    public String getExamTime() {
        return examTime;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }


    public int getCredit() {
        return credit;
    }

    public String getType() {
        return type;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getStorage(Course course){
        return switch (course.getFaculty()) {
            case "Math" -> Math.getMath().getStorage().get(course);
            case "Physics" -> Physics.getPhysics().getStorage().get(course);
            case "Art" -> Art.getArt().getStorage().get(course);
            case "Literature" -> Literature.getLiterature().getStorage().get(course);
            default -> 0;
        };
    }
    public List<User> getRegisteredUsers(){
        return registeredUsers;
    }


}
