package com.pointsoftango.server.notifications.services;

import com.google.firebase.messaging.*;

import java.util.List;

public class FirebaseService {

    public static void sendNotifications(List<String> tokens, Notification notification, String id) {
        if (tokens.size()==0)
            return;
        try {
            MulticastMessage message = MulticastMessage.builder()
                    .putData("id", id)
                    .addAllTokens(tokens)
                    .setNotification(notification)
                    .build();

            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            System.out.println("Successfully sent messages: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.out.println("Unsuccessfully sent messages for: " + id);
        }
    }

    public static void sendNotifications(String topic, Notification notification, String id) {
        try {
            Message message = Message.builder()
                    .putData("id", id)
                    .setTopic(topic)
                    .setNotification(notification)
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent messages: " + response);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.out.println("Unsuccessfully sent messages for: " + id);
        }
    }

//    public static void sendNotification(String token, Notification notification, String id) {
//        try {
//            Message message = Message.builder()
//                    .putData("id", id)
//                    .setToken(token)
//                    .setNotification(notification)
//                    .build();
//
//            String response = FirebaseMessaging.getInstance().send(message);
//            System.out.println("Successfully sent message: " + response);
//        } catch (FirebaseMessagingException e) {
//            e.printStackTrace();
//            System.out.println("Unsuccessfully sent messages for: " + id);
//        }
//    }

}
