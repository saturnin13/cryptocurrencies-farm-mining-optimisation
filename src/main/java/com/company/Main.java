package com.company;

import com.company.RecurrentJobs.WorkersTableCleaning;
import com.company.Server.HttpRequestHandling;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Timer;

import static com.company.Variables.WORKER_TABLE_DATA_TIMEOUT;

// TODO: learn about Spring and add to the app
// TODO: add dependencies to maven
// TODO: set heroku
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        int portValue = 8000;
        if(args.length != 0) {
            String portOption = args[0];
            if(portOption != null && portOption.equals("--port")) {
                portValue = Integer.parseInt(args[1]);
            } else {
                logger.warn("No port given in the command line parameters, using default of " + portValue);
            }
        } else {
            logger.warn("No parameter given in the command line parameters, using default with port " + portValue);
        }

        ScheduleRecurrentJobs();
        HttpRequestHandling.startServer(portValue);
    }

    private static void ScheduleRecurrentJobs() {
        WorkersTableCleaning workersTableCleaning = new WorkersTableCleaning();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(workersTableCleaning,0 , WORKER_TABLE_DATA_TIMEOUT / 2);
    }
}