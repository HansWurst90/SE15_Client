package com.example.jalt.se15_client;
import common.*;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 20.06.15.
 */
public class Soap2Object {

    static LessonResponse soap2lesson(SoapObject input){
        SoapObject SoapLesson = (SoapObject) input.getProperty("lesson");
        //Teacher abholen und "entpacken"
        SoapObject SoapTeacher = (SoapObject) SoapLesson.getProperty("teacher");
        String teacherLastname = SoapTeacher.getPropertySafelyAsString("name");
        int teacherGender = Integer.parseInt(SoapTeacher.getPrimitivePropertySafelyAsString("gender"));
        PersonTO teacher = new PersonTO();
        teacher.setName(teacherLastname);
        teacher.setGender((char) teacherGender);
        // Subject abholen und "entpacken
        SoapObject SoapSubject = (SoapObject) SoapLesson.getProperty("subject");
        int subjectID = Integer.parseInt(SoapSubject.getPrimitivePropertySafelyAsString("subjectID"));
        String subjectDescription = SoapSubject.getPrimitivePropertySafelyAsString("description");
        SubjectTO subject = new SubjectTO();
        subject.setSubjectID(subjectID);
        subject.setDescription(subjectDescription);
        // Lesson abholen und "entpacken
        String room = SoapLesson.getPrimitivePropertySafelyAsString("room");
        String date = SoapLesson.getPrimitivePropertySafelyAsString("date");
        int lessonHour = Integer.parseInt(SoapLesson.getPrimitivePropertySafelyAsString("lessonHour"));
        int lessonID = Integer.parseInt(SoapLesson.getPrimitivePropertySafelyAsString("lessonID"));
        // Hausaufgaben abholen
       List<HomeworkTO> homeworkTOList = new ArrayList<>();
        for (int i = 0; i < SoapLesson.getPropertyCount(); i++) {
            PropertyInfo info = new PropertyInfo();
            SoapLesson.getPropertyInfo(i, info);
            if(info.getName().equals("homeworks")) {
                homeworkTOList.add(soap2homework((SoapObject) SoapLesson.getProperty(i)));
            }
        }
        LessonTO lesson = new LessonTO();
        lesson.setLessonID(lessonID);
        lesson.setLessonHour(lessonHour);
        lesson.setDate(date);
        lesson.setRoom(room);
        lesson.setSubject(subject);
        lesson.setTeacher(teacher);
        LessonResponse output = new LessonResponse();
        output.setLesson(lesson);
        return output;
    }

    static HomeworkTO soap2homework(SoapObject input) {
        int homeworkID = Integer.parseInt(input.getPropertyAsString("homeworkID"));
        String description = input.getPropertyAsString("description");
        HomeworkTO homeworkTO = new HomeworkTO();
        homeworkTO.setHomeworkID(homeworkID);
        homeworkTO.setDescription(description);
        return homeworkTO;
    }
}
