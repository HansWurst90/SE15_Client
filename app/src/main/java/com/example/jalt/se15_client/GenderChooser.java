package com.example.jalt.se15_client;

/**
 * Created by Jan on 10.06.15.
 * Auslagerung der Auswahl der Anrede anhand des Geschlechts.
 *  @author Mußenbrock
 */
public class GenderChooser {

    static String getTitleByGender(char gender){
    String title = null;
    switch (gender) {
        default:
            title = "Error";
            break;
        case 'm':
            title = "Herr";
            break;
        case 'w':
            title = "Frau";
            break;
    }
        return title;
    }
}
