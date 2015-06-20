package com.example.jalt.se15_client;

/**
 * Created by ErfiMac on 14.06.15.
 * Auslagerung der Farbwahl mit Umrandung anhand der Fach-Nr.
 * @author Lukas Erfkämper
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
