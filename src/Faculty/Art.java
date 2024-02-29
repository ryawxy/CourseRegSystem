package Faculty;

import Course.CoreCourse;
import Course.Course;
import Course.GeneralCourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Art extends Faculty {
    private static final List<Course> artCourse = new ArrayList<>();

    private static final HashMap<Course,Integer> storage = new HashMap<>();


    public Art() {
        initialCourses();
    }

    @Override
    public void initialCourses() {
        Course course1 = new GeneralCourse("1322","10:20Sun","02.24","History","F.B",2,"general","Art");
        Course course2 = new CoreCourse("2124","12:00Sat","02.15","Fine Arts","H.P",4,"core","Art");
        Course course3 = new CoreCourse("4231","19:00Wed","03.13","Graphic Design","A.A",3,"core","Art");
        Course course4 = new CoreCourse("2112","9:00Sat","03.27","Performing Arts","J.P",4,"core","Art");
        artCourse.add(course1);
        storage.put(course1,150);
        artCourse.add(course2);
        storage.put(course2,40);
        artCourse.add(course3);
        storage.put(course3,80);
        artCourse.add(course4);
        storage.put(course4,70);
    }
    public List<Course> getArtCourses(){
        return artCourse;
    }

    public HashMap<Course, Integer> getStorage() {
        return storage;
    }
}
