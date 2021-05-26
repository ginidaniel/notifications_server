package com.pointsoftango.server.notifications;


import com.pointsoftango.server.notifications.tasks.NotificationTask;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.*;

public class NotificationProcess {

 //   private static final Logger LOG = LogManager.getLogger(NotificationProcess.class);

    public static void main (String[] args) {

 //       LOG.info("Initializing.. Notifications starts");

        Timer timer = new Timer();
        NotificationTask nTask = new NotificationTask();
        // This task is scheduled to run every 10 seconds

        //  0/10 * * * ?   - Every 10 minutes (600000 ms)
        timer.scheduleAtFixedRate(nTask, 0, 600000);
    }

}
