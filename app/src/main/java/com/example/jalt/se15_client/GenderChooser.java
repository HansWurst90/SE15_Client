package com.example.jalt.se15_client;

/**
 * Created by Jan on 10.06.15.
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
