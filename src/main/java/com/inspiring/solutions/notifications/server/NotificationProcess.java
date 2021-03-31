package com.inspiring.solutions.notifications.server;


import com.inspiring.solutions.notifications.server.tasks.NotificationTask;

import java.util.*;

public class NotificationProcess {

    public static void main (String[] args) {
        System.out.println("Initializing.. Notifications starts");

        Timer timer = new Timer();
        NotificationTask nTask = new NotificationTask();
        // This task is scheduled to run every 10 seconds

        //  0/10 * * * ?   - Every 10 minutes (600000 ms)
        timer.scheduleAtFixedRate(nTask, 0, 600000);
    }

}
