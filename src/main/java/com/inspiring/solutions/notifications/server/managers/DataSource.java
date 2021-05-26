package com.inspiring.solutions.notifications.server.managers;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.inspiring.solutions.notifications.server.beans.EventId;
import com.inspiring.solutions.notifications.server.model.Interested;
import com.inspiring.solutions.notifications.server.model.UserNotifications;
import com.inspiring.solutions.notifications.server.services.EventsService;
import com.inspiring.solutions.notifications.server.utils.FirebaseConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DataSource {

    private static DataSource singleton;

    public static DataSource getInstance() {
        if (singleton == null)
            singleton = new DataSource();

        return singleton;
    }

    public List<Interested> fetchInterestedByEvent(EventId eventId) {
        List<Interested> interesteds = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = getInterestedCollection(eventId.getCountry(), eventId.getRegion()).whereEqualTo("event", eventId.getEventId()).get();
        try {
            // future.get() blocks on response
            List<QueryDocumentSnapshot>  documents = future.get().getDocuments();
            for (DocumentSnapshot document : documents)
                interesteds.add(document.toObject(Interested.class));

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return interesteds;
    }

    public Map<String, UserNotifications> fetchUserNotifications(List<String> users) {
        Map<String, UserNotifications> notifications = new HashMap<>();

        ApiFuture<QuerySnapshot> future = getUserNotificationCollection().whereIn("username", users).get();
        try {
             // future.get() blocks on response
             List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (DocumentSnapshot document : documents) {
                UserNotifications notification = document.toObject(UserNotifications.class);
                notifications.put(notification.getUsername(), notification);
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return notifications;
    }

    public static CollectionReference getUserNotificationCollection() {
        return FirebaseConnection.getFirestoreFB().collection("user_notifications");
    }

    public static CollectionReference getInterestedCollection(String country, String region) {
        if (country!=null && region!=null)
            return FirebaseConnection.getFirestoreFB().collection("milongas").document(country).collection(region+"_int");

        return FirebaseConnection.getFirestoreFB().collection("interested");
    }
}
