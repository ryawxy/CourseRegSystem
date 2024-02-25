package Faculty;

import Course.CoreCourse;

import Course.Course;
import Course.GeneralCourse;

import java.util.*;

public class Math extends Faculty{

    private static final List<Course> mathCourse = new ArrayList<>();

    private static final HashMap<Course,Integer> storage = new HashMap<>();
    private static final Math math = new Math();

    public Math() {
        initialCourses();
    }

    @Override
    public void initialCourses() {
        Course course1 = new GeneralCourse("2401","10:30Mon","03.24","Calculus I","A.B",4,"general","Math");
        Course course2 = new CoreCourse("4502","15:00Sat","03.19","Linear Algebra","K.P",3,"core","Math");
        Course course3 = new CoreCourse("2312","16:00Wed","03.15","Topology I","L.C",4,"core","Math");
        Course course4 = new CoreCourse("7237","9:00Mon","03.17","Complex Analysis I","C.S",4,"core","Math");
      mathCourse.add(course1);
      storage.put(course1,200);
        mathCourse.add(course2);
        storage.put(course2,60);
        mathCourse.add(course3);
        storage.put(course3,50);
        mathCourse.add(course4);
        storage.put(course4,50);
    }
    public List<Course> getMathCourses(){
        return mathCourse;
    }

    public static HashMap<Course, Integer> getStorage() {
        return storage;
    }

    public static Math getMath(){
        return math;
    }
}

