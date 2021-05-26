package com.pointsoftango.server.notifications.utils;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConnection {

    private static FirebaseApp defaultApp = null;

    private static void connectFBApp() {

        try {

            FileInputStream serviceAccount =
                    new FileInputStream(FirebaseConnection.class.getClassLoader().getResource("pots-firebase-adminsdk.json").getFile());

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://eventango-92fe8.firebaseio.com")
                    .build();

            defaultApp = FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            e.printStackTrace();
        }


       /* FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .setDatabaseUrl("https://tangointegral-0218.firebaseio.com/")
                    .build();

            defaultApp = FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public static Firestore getFirestoreFB()  {
        if (defaultApp==null)
            connectFBApp();

        return FirestoreClient.getFirestore(defaultApp);
    }

    public static FirebaseMessaging getFirebaseMessaging()  {
        if (defaultApp==null)
            connectFBApp();

        return FirebaseMessaging.getInstance(defaultApp);
    }

    public static StorageClient getStorageClientFB()  {
        if (defaultApp==null)
            connectFBApp();

        return StorageClient.getInstance(defaultApp);
    }

    public static FirebaseDatabase getDataBaseFB()  {
        if (defaultApp==null)
            connectFBApp();

        return FirebaseDatabase.getInstance(defaultApp);
    }

    public static FirebaseAuth getAuthFB()  {
        if (defaultApp==null)
            connectFBApp();

        return FirebaseAuth.getInstance(defaultApp);
    }

}
