package Faculty;

import Course.Course;

import java.util.AbstractList;
import java.util.AbstractMap;

public abstract class Faculty {
    public AbstractList<Course> courses;

    public AbstractMap<Course,Integer> storage;

    public Faculty() {
        this.courses = courses;
        this.storage = storage;
    }
    public abstract void initialCourses();
}
