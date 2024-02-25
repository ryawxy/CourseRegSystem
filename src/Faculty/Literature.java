package Faculty;

import Course.CoreCourse;

import Course.Course;
import Course.GeneralCourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Literature extends Faculty{
    private static final List<Course> artCourse = new ArrayList<>();

    private static final HashMap<Course,Integer> storage = new HashMap<>();

    private static final Literature literature = new Literature();


    public Literature() {
        initialCourses();
    }

    @Override
    public void initialCourses() {
        Course course1 = new GeneralCourse("1361","10:20Wed","02.22","English Literature","F.B",3,"general","Literature");
        Course course2 = new CoreCourse("2323","12:00Sun","02.10","Creative Writing","H.M",3,"core","Literature");
        Course course3 = new CoreCourse("4231","8:00Wed","03.09","Philosophy","A.C",4,"core","Literature");
        Course course4 = new CoreCourse("2121","16:00Sat","03.04","Modern Poetry","S.R",3,"core","Literature");
        artCourse.add(course1);
        storage.put(course1,150);
        artCourse.add(course2);
        storage.put(course2,30);
        artCourse.add(course3);
        storage.put(course3,50);
        artCourse.add(course4);
        storage.put(course4,50);
    }
    public List<Course> getLiteratureCourses(){
        return artCourse;
    }

    public static HashMap<Course, Integer> getStorage() {
        return storage;
    }

    public static Literature getLiterature(){
        return literature;
    }
}
