package de.jalt.studeasy.client;

import com.jalt.studeasy.client.R;

/**
 * Auslagerung der Wahl der Feldfarbe mit Rahmen für die Mainactivity im Querformat.
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class BorderChooser {
    static int getBorderFromId(int subjectId){
            int color;
            switch (subjectId) {
                default:
                    color = R.drawable.border;
                    break;
                case 1:
                    color = R.drawable.borderred;
                    break;
                case 2:
                    color = R.drawable.borderorange;
                    break;
                case 3:
                    color = R.drawable.borderpurple;
                    break;
                case 4:
                    color = R.drawable.borderindigo;
                    break;
                case 5:
                    color = R.drawable.borderlightblue;
                    break;
                case 6:
                    color = R.drawable.borderteal;
                    break;
                case 7:
                    color = R.drawable.borderlightgreen;
                    break;
                case 8:
                    color = R.drawable.borderyellow;
                    break;
                case 9:
                    color = R.drawable.borderbrown;
                    break;
                case 10:
                    color = R.drawable.bordergreen;
                    break;  }
            return color;
        }

    }
