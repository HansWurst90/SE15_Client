package com.example.jalt.se15_client;

import android.app.Application;
import common.IStudeasyScheduleService;

/**
 *
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class StudeasyScheduleApplication extends Application
{
    private IStudeasyScheduleService studeasyScheduleService;

    public StudeasyScheduleApplication() {
        this.studeasyScheduleService = new StudeasyScheduleServiceImpl() {
        };
    }

    public IStudeasyScheduleService getStudeasyScheduleService() {
        return this.studeasyScheduleService;
    }
}







