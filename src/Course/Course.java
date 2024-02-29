package Course;

import Faculty.Art;
import Faculty.Literature;
import Faculty.Math;
import Faculty.Physics;

import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Course {


private String code;

private String classTime;

private String examTime;

private String name;

private String teacher;

//private int storage;

private int credit;

private String type;

private String faculty;

private final static AbstractMap<String,Integer> CoursesStorage = new HashMap<>();

private final static AbstractList<Course> courses = new ArrayList<Course>();

    public Course( String code, String classTime, String examTime, String name, String teacher, int credit,String type,String faculty) {
        this.code = code;
        this.classTime = classTime;
        this.examTime = examTime;
        this.name = name;
        this.teacher = teacher;
   //     this.storage = storage;
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

//    public int getStorage() {
//        return storage;
//    }

    public int getCredit() {
        return credit;
    }

    public AbstractMap<String, Integer> getCoursesStorage() {
        return CoursesStorage;
    }

    public String getType() {
        return type;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getStorage(Course course){
        if(course.getFaculty().equals("Math")){
            return Math.getMath().getStorage().get(course);
        }
        else if(course.getFaculty().equals("Physics")){
            return Physics.getPhysics().getStorage().get(course);
        }
        else if(course.getFaculty().equals("Art")){
            return Art.getArt().getStorage().get(course);
        }
        else if(course.getFaculty().equals("Literature")){
            return Literature.getLiterature().getStorage().get(course);
        }
        return 0;
    }
}
