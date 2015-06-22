package de.jalt.studeasy.client;

import android.app.Application;
import de.jalt.studeasy.common.IStudeasyScheduleService;

/**
 *Applikation, die den Service zur Verfügung stellt
 * @author Jan Mußenbrock und Lukas Erfkämper
 */
public class StudeasyScheduleApplication extends Application
{
    private IStudeasyScheduleService studeasyScheduleService;

    /**
     * Initialisieren des Services
     */
    public StudeasyScheduleApplication() {
        this.studeasyScheduleService = new StudeasyScheduleServiceImpl() {
        };
    }

    /**
     * Zurverfügungstellung des Services
     * @return studeasyScheduleService-Instanz
     */
    public IStudeasyScheduleService getStudeasyScheduleService() {
        return this.studeasyScheduleService;
    }
}







