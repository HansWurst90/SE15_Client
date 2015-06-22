package de.jalt.studeasy.client;

/**
 * Auslagerung der Auflösung des Gender-Chars für die Anrede der Lehrer
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
