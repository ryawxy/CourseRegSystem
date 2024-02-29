package Course;

public class  CoreCourse extends Course {

    public CoreCourse(String code, String classTime, String examTime, String name, String teacher, int credit,String type,String faculty) {
        super(code, classTime, examTime, name, teacher, credit,"core",faculty);
    }
}
