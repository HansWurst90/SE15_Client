package com.example.jalt.se15_client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import common.HomeworkTO;
import common.LessonTO;
import common.PersonTO;
import common.SubjectTO;

/**
 *
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class TestLessons {

    static List<LessonTO> lessons;

    static List<LessonTO> getLessons1(){
        List<LessonTO> lessons = new ArrayList<LessonTO>();
        lessons.add(createLesson("Mathe", 1, "Erfkämper", 'm', 1, "A123", 1));
        lessons.add(createLesson("Deutsch", 2, "Prischep", 'm', 2, "A123", 2));

        lessons.add(createLesson("Spanisch", 4, "Goy Ramos", 'm', 4, "A123", 3));
        lessons.add(createLesson("Biologie", 8, "Darwin", 'm', 5, "A123", 4));
        lessons.add(createLesson("Info", 6, "Turing", 'm', 6, "A123", 5));
        return lessons;
    }

    static List<LessonTO> getLessons2(){
        List<LessonTO> lessons = new ArrayList<LessonTO>();
        lessons.add(createLesson("Englisch", 3, "Mußenbrock", 'm', 1, "A123", 6));
        lessons.add(createLesson("Mathe", 1, "Erfkämper", 'm', 2, "A123", 7));
        lessons.add(createLesson("Chemie", 7, "Curie", 'w', 3, "A123", 8));

        lessons.add(createLesson("Physik", 9, "Einstein", 'm', 5, "A123", 9));
        lessons.add(createLesson("Info", 6, "Turing", 'm', 6, "A123", 10));
        return lessons;
    }

    static List<LessonTO> getLessons3(){
        List<LessonTO> lessons = new ArrayList<LessonTO>();
        lessons.add(createLesson("Chemie", 7, "Curie", 'w', 1, "A123", 11));
        lessons.add(createLesson("Deutsch", 2, "Prischep", 'm', 2, "A123", 12));
        lessons.add(createLesson("Biologie", 8, "Darwin", 'm', 3, "A123", 13));
        lessons.add(createLesson("Sport", 5, "Riegel", 'm', 4, "A123", 14));
        lessons.add(createLesson("Englisch", 3, "Mußenbrock", 'm', 5, "A123", 15));
        lessons.add(createLesson("Religion", 10, "Ratzinger", 'm', 6, "A123", 16));
        return lessons;
    }

    static List<LessonTO> getLessons4(){
        List<LessonTO> lessons = new ArrayList<LessonTO>();


        lessons.add(createLesson("Englisch", 3, "Mußenbrock", 'm', 3, "A123", 17));
        lessons.add(createLesson("Info", 6, "Turing", 'm', 4, "A123", 18));
        lessons.add(createLesson("Spanisch", 4, "Goy Ramos", 'm', 5, "A123", 19));
        lessons.add(createLesson("Physik", 9, "Mußenbrock", 'm', 6, "A123", 20));
        return lessons;
    }

    static List<LessonTO> getLessons5() {
        List<LessonTO> lessons = new ArrayList<LessonTO>();
        lessons.add(createLesson("Biologie", 8, "Darwin", 'm', 1, "A123", 21));
        lessons.add(createLesson("Mathe", 1, "Erfkämper", 'm', 2, "A123", 22));
        lessons.add(createLesson("Deutsch", 2, "Prischep", 'm', 3, "A123", 23));
        lessons.add(createLesson("Sport", 5, "Erfkämper", 'm', 4, "A123", 24));
        lessons.add(createLesson("Religion", 10, "Ratzinger", 'm', 5, "A123", 25));
        return lessons;
    }

    static LessonTO createLesson(String subject, int subjectId, String teacher, char gender, int hour, String room, int lessonId){
        LessonTO lesson = new LessonTO();
        lesson.setDate("20062015");
        SubjectTO subject1 = new SubjectTO();
        subject1.setDescription(subject);
        subject1.setSubjectID(subjectId);
        lesson.setSubject(subject1);
        PersonTO teacher1 = new PersonTO();
        teacher1.setName(teacher);
        teacher1.setGender(gender);
        lesson.setTeacher(teacher1);
        lesson.setLessonHour(hour);
        String room1 = room;
        lesson.setRoom(room1);
        List<HomeworkTO> homeworks1 = new ArrayList<HomeworkTO>();
        HomeworkTO homework1 = new HomeworkTO();
        homework1.setDescription("Testafddsfdsafdsafdsfdsjkdsöflkndsflhsödsjföldskjfösdjkflksödjfödklsjfdsöjfdskfjdskölfjsdöfjdslköfjdsöklfjsdölfjkdsöklfjadsölkfjdlsökfjdskölfjölskajfsdölkjfaölksdfjsdlökfjslödkjeiurklfjsdölkfsdiö");
        homeworks1.add(homework1);
        lesson.setHomeworks(homeworks1);
        lesson.setLessonID(lessonId);
        return lesson;
    }
    static LessonTO getLessonById(int id)
    {
        return lessons.get(id);
    }
}