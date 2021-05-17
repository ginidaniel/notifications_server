package com.inspiring.solutions.notifications.server.services.notifications;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;

import com.inspiring.solutions.notifications.server.listeners.DataAccessListener;
import com.inspiring.solutions.notifications.server.model.Notification;
import com.inspiring.solutions.notifications.server.model.UserNotificationItem;
import com.inspiring.solutions.notifications.server.model.UserNotifications;
import com.inspiring.solutions.notifications.server.utils.FirebaseConnection;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class NotificationsService {

    private static NotificationsService singleton;

    private NotificationsService() {
    }

    public void fetchPending(DataAccessListener<List<Notification>> listener) {

        List<Notification> notifications = new ArrayList<>();

        //asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = FirebaseConnection.getFirestoreFB().collection("notifications").whereEqualTo("processed", false).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;

        try {
            documents = future.get().getDocuments();
            for (DocumentSnapshot document : documents) {
                Notification notification = document.toObject(Notification.class);
                notifications.add(notification);
            }

            listener.onSuccess(notifications);
            System.out.println("# Notifications to process => " + notifications.size());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static NotificationsService getInstance() {
        if (singleton == null)
            singleton = new NotificationsService();

        return singleton;
    }

    public void fetchUsersTargeted(List<String> users, DataAccessListener<Map<String, UserNotifications>> listener) {
        Map<String, UserNotifications> notifications = new HashMap<>();

        if (users==null || users.size()==0) {
            listener.onSuccess(notifications);
            return;
        }

        ApiFuture<QuerySnapshot> future = FirebaseConnection.getFirestoreFB().collection("user_notifications").whereIn("username", users).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();
            for (DocumentSnapshot document : documents) {
                UserNotifications notification = document.toObject(UserNotifications.class);
                notifications.put(notification.getUsername(), notification);
            }

            listener.onSuccess(notifications);
            System.out.println("# User Notifications => " + notifications.size());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void saveUserNotifications(Map<String, UserNotificationItem> items) {
        try {
            WriteBatch batch = FirebaseConnection.getFirestoreFB().batch();
            for (String username: items.keySet()) {
                DocumentReference documentReference = FirebaseConnection.getFirestoreFB().collection("user_notifications")
                        .document(username).collection("notifications").document(items.get(username).getId());
                batch.set(documentReference, items.get(username));
            }
            batch.commit().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            //TODO LOG this error
        }
    }

    public void updateNotification(Notification notification) {
        if (notification.getUserNames().size()!=0)
            notification.setToShow(false); // To not create more Items on the users list as it has been created.
        else
            notification.setProcessed(true); // So the Notification Service won't pick it next time. All users were notified.

        notification.setUpdated(Timestamp.now());
        ApiFuture<WriteResult> result = FirebaseConnection.getFirestoreFB().collection("notifications")
                                            .document(notification.getId()).set(notification);
        try {
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            //TODO LOG this error
        }
    }
}
