import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import studeasy.common.ILesson;
import studeasy.entities.Course;
import studeasy.entities.Homework;
import studeasy.entities.Lesson;
import studeasy.entities.Room;
import studeasy.entities.Subject;
import studeasy.entities.Teacher;

/**
 * Created by ErfiMac on 10.06.15.
 */
public class TestLessons {
    private Date date;
    private String subject;
    private Teacher teacher;
    private Room room;
    private Lesson lesson1 = new Lesson();
    private Lesson lesson2 = new Lesson();
    private Lesson lesson3 = new Lesson();
    private Lesson lesson4 = new Lesson();
    private Lesson lesson5 = new Lesson();
    private Lesson lesson6 = new Lesson();

    private List<Lesson> lessons = new ArrayList<Lesson>();

    public TestLessons()
    {
        create();
        fill();
    }

    private void create()
    {
        //Lesson 1
        lesson1.setDate(new Date());
            Subject subject1 = new Subject();
            subject1.setDescription("English");
            subject1.setSubjectID(1);
        lesson1.setSubject(subject1);
            Teacher teacher1 = new Teacher();
            teacher1.setName("Mußenbrock");
            teacher1.setGender('m');
        lesson1.setTeacher(teacher);
        lesson1.setLessonHour(1);
            Room room1 = new Room();
            room1.setRoomID("C501");
        lesson1.setRoom(room);
        lesson1.setCourse(new Course());
            Homework homework1 = new Homework();
            homework1.setDescription("English Homework");
        lesson1.setHomework(homework1);

        //Lesson 2
        lesson2.setDate(new Date());
            Subject subject2 = new Subject();
            subject2.setDescription("Deutsch");
            subject2.setSubjectID(2);
        lesson2.setSubject(subject2);
            Teacher teacher2 = new Teacher();
            teacher2.setName("Prischep");
            teacher2.setGender('m');
        lesson2.setTeacher(teacher);
        lesson2.setLessonHour(2);
            Room room2 = new Room();
            room2.setRoomID("A004");
        lesson2.setRoom(room);
        lesson2.setCourse(new Course());
            Homework homework2 = new Homework();
            homework2.setDescription("Aufsatz verfassen");
        lesson2.setHomework(homework2);

        //Lesson 3
        lesson3.setDate(new Date());
            Subject subject3 = new Subject();
            subject3.setDescription("Mathematik");
            subject3.setSubjectID(3);
        lesson3.setSubject(subject3);
            Teacher teacher3 = new Teacher();
            teacher3.setName("Erfkämper");
            teacher3.setGender('m');
        lesson3.setTeacher(teacher);
        lesson3.setLessonHour(3);
            Room room3 = new Room();
            room3.setRoomID("D422");
        lesson3.setRoom(room);
        lesson3.setCourse(new Course());
            Homework homework3 = new Homework();
            homework3.setDescription("Satz des Phythagoras nachweisen");
        lesson3.setHomework(homework3);

        //Lesson 4
        lesson4.setDate(new Date());
            Subject subject4 = new Subject();
            subject4.setDescription("Sport");
            subject4.setSubjectID(4);
        lesson4.setSubject(subject4);
            Teacher teacher4 = new Teacher();
            teacher4.setName("Riegel");
            teacher4.setGender('w');
        lesson4.setTeacher(teacher);
        lesson4.setLessonHour(4);
            Room room4 = new Room();
            room4.setRoomID("B201");
        lesson4.setRoom(room);
        lesson4.setCourse(new Course());
            Homework homework4 = new Homework();
            homework4.setDescription("Handstand üben");
        lesson4.setHomework(homework4);

        //Lesson 5
        lesson5.setDate(new Date());
            Subject subject5 = new Subject();
            subject5.setDescription("Physik");
            subject5.setSubjectID(5);
        lesson5.setSubject(subject5);
            Teacher teacher5 = new Teacher();
            teacher5.setName("Einstein");
            teacher5.setGender('m');
        lesson5.setTeacher(teacher);
        lesson5.setLessonHour(5);
            Room room5 = new Room();
            room5.setRoomID("D601");
        lesson5.setRoom(room);
        lesson5.setCourse(new Course());
            Homework homework5 = new Homework();
            homework5.setDescription("Aufsatz über die Relativitätstheorie verfassen");
        lesson5.setHomework(homework5);

        //Lesson 6
        lesson6.setDate(new Date());
            Subject subject6 = new Subject();
            subject6.setDescription("Informatik");
            subject6.setSubjectID(6);
        lesson6.setSubject(subject6);
            Teacher teacher6 = new Teacher();
            teacher6.setName("von Neumann");
            teacher6.setGender('m');
        lesson6.setTeacher(teacher);
        lesson6.setLessonHour(6);
            Room room6 = new Room();
            room6.setRoomID("C333");
        lesson6.setRoom(room);
        lesson6.setCourse(new Course());
            Homework homework6 = new Homework();
            homework6.setDescription("App programmieren");
        lesson6.setHomework(homework6);
    }

    private void fill()
    {
        lessons.add(lesson1);
        lessons.add(lesson2);
        lessons.add(lesson3);
        lessons.add(lesson4);
        lessons.add(lesson5);
        lessons.add(lesson6);

    }
}
