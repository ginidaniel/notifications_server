package com.inspiring.solutions.notifications.server.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.inspiring.solutions.notifications.server.model.Event;
import com.inspiring.solutions.notifications.server.utils.FirebaseConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class EventsService {

    private static EventsService singleton;

    List<Event> events;

    private EventsService() {
        events = new ArrayList<>();

        //asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = FirebaseConnection.getFirestoreFB().collection("students").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = future.get().getDocuments();

            for (DocumentSnapshot document : documents) {
                Event event = document.toObject(Event.class);
                events.add(event);
            }

            System.out.println("# Students => " + events.size());

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static EventsService getInstance() {
        if (singleton == null)
            singleton = new EventsService();

        return singleton;
    }

    public List<Event> getAll() {
        return events;
    }

}
