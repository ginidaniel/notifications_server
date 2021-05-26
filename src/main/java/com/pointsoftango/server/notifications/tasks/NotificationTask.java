package com.pointsoftango.server.notifications.tasks;

import com.pointsoftango.server.notifications.managers.NotificationsManager;
import com.pointsoftango.server.notifications.model.Notification;
import com.pointsoftango.server.notifications.services.notifications.NotificationsService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.*;

public class NotificationTask extends TimerTask {

    static NotificationsService notificationsService = NotificationsService.getInstance();

//    private static final Logger LOG = LogManager.getLogger(NotificationTask.class);

    @Override
    public void run() {
//        LOG.info("Starting.. Fetching Pending Notifications");
        notificationsService.fetchPending(NotificationTask::handleNotifications);
    }

    private static void handleNotifications(List<Notification> notifications) {
        NotificationsManager notificationsManager = new NotificationsManager(notificationsService);

        for (Notification notification: notifications) {
            if (notification.getTopic()!=null)
                notificationsManager.handleNotificationsByTopic(notification);

            if (notification.getUserNames()!=null)
                notificationsManager.handleNotificationsMulticast(notification);
        }
    }

}
