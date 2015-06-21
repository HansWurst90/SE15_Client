package com.example.jalt.se15_client;

/**
 *
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class HourChooser {

    static String[] getTimesbyHour(int hour){
        //Abhängig der Stunde werdne die Star- und Endzeiten gewählt
        String[] times = new String[2];
        switch (hour) {
            default:
                times[0]= "Error";
                times[1] = "Error";
                break;
            case 1:
                times[0] = "08:30";
                times[1] = "09:15";
                break;
            case 2:
                times[0] = "09:15";
                times[1] = "10:00";
                break;
            case 3:
                times[0] = "10:15";
                times[1] = "11:00";
                break;
            case 4:
                times[0] = "11:00";
                times[1] = "11:45";
                break;
            case 5:
                times[0] = "12:00";
                times[1] = "12:45";
                break;
            case 6:
                times[0] = "12:45";
                times[1] = "13:30";
                break;
        }
        return times;
    }
}
