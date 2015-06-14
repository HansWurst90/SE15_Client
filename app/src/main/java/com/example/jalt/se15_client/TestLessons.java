package com.example.jalt.se15_client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.CourseTO;
import common.HomeworkTO;
import common.LessonTO;
import common.RoomTO;
import common.SubjectTO;
import common.TeacherTO;

/**
 * Created by ErfiMac on 10.06.15.
 */
public class TestLessons {

    static List<LessonTO> getLessons(){
        List<LessonTO> lessons = new ArrayList<LessonTO>();

         Date date;
         String subject;
         TeacherTO teacher;
         RoomTO room;
         LessonTO lesson1 = new LessonTO();
         LessonTO lesson2 = new LessonTO();
         LessonTO lesson3 = new LessonTO();
         LessonTO lesson4 = new LessonTO();
         LessonTO lesson5 = new LessonTO();
         LessonTO lesson6 = new LessonTO();

        //Lesson 1
        lesson1.setDate(new Date());
            SubjectTO subject1 = new SubjectTO();
            subject1.setDescription("English");
            subject1.setSubjectID(1);
        lesson1.setSubject(subject1);
            TeacherTO teacher1 = new TeacherTO();
            teacher1.setName("Mußenbrock");
            teacher1.setGender('m');
        lesson1.setTeacher(teacher1);
        lesson1.setLessonHour(1);
            RoomTO room1 = new RoomTO();
            room1.setRoomID("C501");
        lesson1.setRoom(room1);
        lesson1.setCourse(new CourseTO());
            List<HomeworkTO> homeworks1 = new ArrayList<HomeworkTO>();
            HomeworkTO homework1 = new HomeworkTO();
            HomeworkTO homework11 = new HomeworkTO();
            HomeworkTO homework12 = new HomeworkTO();
            homework1.setDescription("- Learn Voc");
            homework11.setDescription("- Read Page 326");
            homework12.setDescription("- Wirte a letter to Mr Mußenbrock");
            homeworks1.add(homework1);
            homeworks1.add(homework11);
            homeworks1.add(homework12);
        lesson1.setHomeworks(homeworks1);
        lesson1.setLessonID(1);

        //Lesson 2
        lesson2.setDate(new Date());
            SubjectTO subject2 = new SubjectTO();
            subject2.setDescription("Deutsch");
            subject2.setSubjectID(2);
        lesson2.setSubject(subject2);
        TeacherTO teacher2 = new TeacherTO();
            teacher2.setName("Prischep");
            teacher2.setGender('m');
        lesson2.setTeacher(teacher2);
        lesson2.setLessonHour(2);
            RoomTO room2 = new RoomTO();
            room2.setRoomID("A004");
        lesson2.setRoom(room2);
        lesson2.setCourse(new CourseTO());
            List<HomeworkTO> homeworks2 = new ArrayList<HomeworkTO>();
            HomeworkTO homework2 = new HomeworkTO();
            homework2.setDescription("Aufsatz verfassen");
            homeworks2.add(homework2);
        lesson2.setHomeworks(homeworks2);
        lesson2.setLessonID(2);

        //Lesson 3
        lesson3.setDate(new Date());
            SubjectTO subject3 = new SubjectTO();
            subject3.setDescription("Mathe");
            subject3.setSubjectID(3);
        lesson3.setSubject(subject3);
            TeacherTO teacher3 = new TeacherTO();
            teacher3.setName("Erfkämper");
            teacher3.setGender('m');
        lesson3.setTeacher(teacher3);
        lesson3.setLessonHour(3);
            RoomTO room3 = new RoomTO();
            room3.setRoomID("D422");
        lesson3.setRoom(room3);
        lesson3.setCourse(new CourseTO());
        List<HomeworkTO> homeworks3 = new ArrayList<HomeworkTO>();
            HomeworkTO homework3 = new HomeworkTO();
            homework3.setDescription("Satz des Phythagoras nachweisen");
            homeworks3.add(homework3);
        lesson3.setHomeworks(homeworks3);
        lesson3.setLessonID(3);

        //Lesson 4
        lesson4.setDate(new Date());
            SubjectTO subject4 = new SubjectTO();
            subject4.setDescription("Sport");
            subject4.setSubjectID(4);
        lesson4.setSubject(subject4);
            TeacherTO teacher4 = new TeacherTO();
            teacher4.setName("Riegel");
            teacher4.setGender('w');
        lesson4.setTeacher(teacher4);
        lesson4.setLessonHour(4);
            RoomTO room4 = new RoomTO();
            room4.setRoomID("B201");
        lesson4.setRoom(room4);
        lesson4.setCourse(new CourseTO());
            List<HomeworkTO> homeworks4 = new ArrayList<HomeworkTO>();
            HomeworkTO homework4 = new HomeworkTO();
            homework4.setDescription("Handstand üben");
            homeworks4.add(homework4);
        lesson4.setHomeworks(homeworks4);
        lesson4.setLessonID(4);

        //Lesson 5
        lesson5.setDate(new Date());
            SubjectTO subject5 = new SubjectTO();
            subject5.setDescription("Physik");
            subject5.setSubjectID(5);
        lesson5.setSubject(subject5);
            TeacherTO teacher5 = new TeacherTO();
            teacher5.setName("Einstein");
            teacher5.setGender('m');
        lesson5.setTeacher(teacher5);
        lesson5.setLessonHour(5);
            RoomTO room5 = new RoomTO();
            room5.setRoomID("D601");
        lesson5.setRoom(room5);
        lesson5.setCourse(new CourseTO());
            List<HomeworkTO> homeworks5 = new ArrayList<HomeworkTO>();
            HomeworkTO homework5 = new HomeworkTO();
            homework5.setDescription("Aufsatz über die Relativitätstheorie verfassen");
            homeworks5.add(homework5);
        lesson5.setHomeworks(homeworks5);
        lesson5.setLessonID(5);

        //Lesson 6
        lesson6.setDate(new Date());
            SubjectTO subject6 = new SubjectTO();
            subject6.setDescription("Info");
            subject6.setSubjectID(6);
        lesson6.setSubject(subject6);
            TeacherTO teacher6 = new TeacherTO();
            teacher6.setName("von Neumann");
            teacher6.setGender('m');
        lesson6.setTeacher(teacher6);
        lesson6.setLessonHour(6);
            RoomTO room6 = new RoomTO();
            room6.setRoomID("C333");
        lesson6.setRoom(room6);
        lesson6.setCourse(new CourseTO());
            List<HomeworkTO> homeworks6 = new ArrayList<HomeworkTO>();
            HomeworkTO homework6 = new HomeworkTO();
            homework6.setDescription("App programmieren");
            homeworks6.add(homework6);
        lesson6.setHomeworks(homeworks6);
        lesson6.setLessonID(6);

        lessons.add(lesson1);
        lessons.add(lesson2);
        lessons.add(lesson3);
        lessons.add(lesson4);
        lessons.add(lesson5);
        lessons.add(lesson6);

        return lessons;
    }

    static List<LessonTO> getLessons1(){
        List<LessonTO> lessons = new ArrayList<LessonTO>();
        lessons.add(createLesson("Mathematik", 1, "Mußenbrock", 'm', 1, "A123", 1));
        lessons.add(createLesson("Deutsch", 2, "Mußenbrock", 'm', 2, "A123", 2));

        lessons.add(createLesson("Spanisch", 4, "Mußenbrock", 'm', 4, "A123", 3));
        lessons.add(createLesson("Biologie", 8, "Mußenbrock", 'm', 5, "A123", 4));
        lessons.add(createLesson("Informatik", 6, "Mußenbrock", 'm', 6, "A123", 5));
        return lessons;
    }

    static List<LessonTO> getLessons2(){
        List<LessonTO> lessons = new ArrayList<LessonTO>();
        lessons.add(createLesson("Englisch", 3, "Mußenbrock", 'm', 1, "A123", 6));
        lessons.add(createLesson("Mathematik", 1, "Erfkämper", 'm', 2, "A123", 7));
        lessons.add(createLesson("Chemie", 7, "Mußenbrock", 'm', 3, "A123", 8));

        lessons.add(createLesson("Physik", 9, "Mußenbrock", 'm', 5, "A123", 9));
        lessons.add(createLesson("Informatik", 6, "Mußenbrock", 'm', 6, "A123", 10));
        return lessons;
    }

    static List<LessonTO> getLessons3(){
        List<LessonTO> lessons = new ArrayList<LessonTO>();
        lessons.add(createLesson("Chemie", 7, "Mußenbrock", 'm', 1, "A123", 11));
        lessons.add(createLesson("Deutsch", 2, "Mußenbrock", 'm', 2, "A123", 12));
        lessons.add(createLesson("Biologie", 8, "Mußenbrock", 'm', 3, "A123", 13));
        lessons.add(createLesson("Sport", 5, "Mußenbrock", 'm', 4, "A123", 1));
        lessons.add(createLesson("Englisch", 3, "Mußenbrock", 'm', 5, "A123", 14));
        lessons.add(createLesson("Religion", 10, "Mußenbrock", 'm', 6, "A123", 15));
        return lessons;
    }

    static List<LessonTO> getLessons4(){
        List<LessonTO> lessons = new ArrayList<LessonTO>();


        lessons.add(createLesson("Englisch", 3, "Mußenbrock", 'm', 3, "A123", 16));
        lessons.add(createLesson("Informatik", 6, "Mußenbrock", 'm', 4, "A123", 17));
        lessons.add(createLesson("Spanisch", 4, "Mußenbrock", 'm', 5, "A123", 18));
        lessons.add(createLesson("Physik", 9, "Mußenbrock", 'm', 6, "A123", 19));
        return lessons;
    }

    static List<LessonTO> getLessons5() {
        List<LessonTO> lessons = new ArrayList<LessonTO>();
        lessons.add(createLesson("Biologie", 8, "Mußenbrock", 'm', 1, "A123", 20));
        lessons.add(createLesson("Mathematik", 1, "Erfkämper", 'm', 2, "A123", 21));
        lessons.add(createLesson("Deutsch", 2, "Erfkämper", 'm', 3, "A123", 22));
        lessons.add(createLesson("Sport", 5, "Erfkämper", 'm', 4, "A123", 23));
        lessons.add(createLesson("Religion", 10, "Mußenbrock", 'm', 5, "A123", 24));
        return lessons;
    }

    static LessonTO createLesson(String subject, int subjectId, String teacher, char gender, int hour, String room, int lessonId){
        LessonTO lesson = new LessonTO();
        lesson.setDate(new Date());
        SubjectTO subject1 = new SubjectTO();
        subject1.setDescription(subject);
        subject1.setSubjectID(subjectId);
        lesson.setSubject(subject1);
        TeacherTO teacher1 = new TeacherTO();
        teacher1.setName(teacher);
        teacher1.setGender(gender);
        lesson.setTeacher(teacher1);
        lesson.setLessonHour(hour);
        RoomTO room1 = new RoomTO();
        room1.setRoomID(room);
        lesson.setRoom(room1);
        lesson.setCourse(new CourseTO());
        List<HomeworkTO> homeworks1 = new ArrayList<HomeworkTO>();
        HomeworkTO homework1 = new HomeworkTO();
        homework1.setDescription("");
        homeworks1.add(homework1);
        lesson.setHomeworks(homeworks1);
        lesson.setLessonID(lessonId);
        return lesson;
    }
}