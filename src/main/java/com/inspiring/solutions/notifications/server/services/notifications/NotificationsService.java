package com.inspiring.solutions.notifications.server.services.notifications;

import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;

import com.inspiring.solutions.notifications.server.beans.EventId;
import com.inspiring.solutions.notifications.server.listeners.DataAccessListener;
import com.inspiring.solutions.notifications.server.managers.DataSource;
import com.inspiring.solutions.notifications.server.model.*;
import com.inspiring.solutions.notifications.server.utils.FirebaseConnection;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class NotificationsService {

    private static NotificationsService singleton;

    private static DataSource dataSource;

    private NotificationsService() {
        dataSource = DataSource.getInstance();
    }

    public static NotificationsService getInstance() {
        if (singleton == null)
            singleton = new NotificationsService();

        return singleton;
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

    public void fetchUsersTargeted(List<String> users, DataAccessListener<Map<String, UserNotifications>> listener) {
        listener.onSuccess(dataSource.fetchUserNotifications(users));
    }

    public void fetchUsersTargetedByTopic(Notification notification, DataAccessListener<Map<String, UserNotifications>> listener) {
        //TODO At the moment, it is assumed topic is event_id
        NotificationType type = notification.getType();
        EventId eventId = getEventId(notification.getTopic());

        if (eventId==null)
            listener.onSuccess(new HashMap<>());

        if (type.isInterested()) {
            List<Interested> interesteds = dataSource.fetchInterestedByEvent(eventId);
            fetchUsersTargeted(interesteds.stream().map(Interested::getUsername).collect(Collectors.toList()), listener);
        }
    }

    private EventId getEventId(String topic) {
        if (topic==null || topic.isEmpty())
            return null;

        String[] ids = topic.split("@");
        if (ids.length==1)
            //International eventId
            return new EventId(ids[0], null, null);

        if (ids.length==3)
            //Local  eventId@region@country
            return new EventId(ids[0], ids[2], ids[1]);

        //Invalid ID
        return null;
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
