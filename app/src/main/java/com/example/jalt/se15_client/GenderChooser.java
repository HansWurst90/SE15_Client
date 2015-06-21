package com.example.jalt.se15_client;

/**
 *
 * @author Jan Mußenbrock und Lukas Erfkämper
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
