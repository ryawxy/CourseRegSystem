package Faculty;

import Course.Course;
import Course.CoreCourse;
import Course.GeneralCourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Physics extends Faculty {
    private static final List<Course> physicsCourse = new ArrayList<>();

    private static final HashMap<Course,Integer> storage = new HashMap<>();

    private static final Physics physics = new Physics();

    public Physics() {
        initialCourses();
    }

    @Override
    public void initialCourses() {
        Course course1 = new GeneralCourse("1243","10:30Mon","03.24","Basic Physics","F.P",3,"general","Physics");
        Course course2 = new CoreCourse("2124","16:00Sat","03.18","Quantum Mechanics","L.T",3,"core","Physics");
        Course course3 = new CoreCourse("4234","17:00Wed","03.14","Biological Physics","L.C",3,"core","Physics");
        Course course4 = new CoreCourse("4567","9:00Mon","03.17","Astronomy","J.H",4,"core","Physics");
        physicsCourse.add(course1);
        storage.put(course1,200);
        physicsCourse.add(course2);
        storage.put(course2,60);
        physicsCourse.add(course3);
        storage.put(course3,70);
        physicsCourse.add(course4);
        storage.put(course4,60);
    }
    public List<Course> getPhysicsCourses(){
        return physicsCourse;
    }

    public HashMap<Course, Integer> getStorage() {
        return storage;
    }

    public static Physics getPhysics(){
        return physics;
    }
}

