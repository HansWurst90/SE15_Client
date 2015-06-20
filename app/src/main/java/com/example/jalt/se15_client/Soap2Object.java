package com.example.jalt.se15_client;
import common.*;
import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
/**
 * Created by Jan on 20.06.15.
 */
public class Soap2Object {

    static LessonResponse soap2lesson(SoapObject input){
        //Teacher abholen und "entpacken"
        SoapObject SoapTeacher = (SoapObject) input.getProperty("teacher");
        String teacherLastname = SoapTeacher.getPropertySafelyAsString("name");
        String teacherGender = SoapTeacher.getPrimitivePropertySafelyAsString("gender");
        PersonTO teacher = new PersonTO();
        teacher.setName(teacherLastname);
        teacher.setGender(teacherGender.charAt(0));
        // Subject abholen und "entpacken
        SoapObject SoapSubject = (SoapObject) input.getProperty("subject");
        int subjectID = Integer.parseInt(SoapSubject.getPrimitivePropertySafelyAsString("subjectID"));
        String subjectDescription = SoapSubject.getPrimitivePropertySafelyAsString("subjectDescription");
        SubjectTO subject = new SubjectTO();
        subject.setSubjectID(subjectID);
        subject.setDescription(subjectDescription);
        // Lesson abholen und "entpacken
        String room = input.getPrimitivePropertySafelyAsString("room");
        String date = input.getPrimitivePropertySafelyAsString("date");
        int lessonHour = Integer.parseInt(input.getPrimitivePropertySafelyAsString("LessonHour"));
        int lessonID = Integer.parseInt(input.getPrimitivePropertySafelyAsString("lessonID"));
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
}
