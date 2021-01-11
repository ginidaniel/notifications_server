package services;

import com.google.firebase.messaging.*;

import java.util.List;

public class FirebaseService {

    public static void sendNotifications(String notificationId, List<String> tokens) {
        if (tokens.size()==0)
            return;

        try {
            if (tokens.size()==1) {
                Message message = Message.builder()
                        .putData("id", notificationId)
                        .setToken(tokens.get(0))
                        .build();

                String response = FirebaseMessaging.getInstance().send(message);
                System.out.println("Successfully sent message: " + response);
            }
            else {
                MulticastMessage message = MulticastMessage.builder()
                        .putData("id", notificationId)
                        .addAllTokens(tokens)
                        .build();

                BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
                System.out.println("Successfully sent messages: " + response);
            }
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.out.println("Unsuccessfully sent messages for: " + notificationId);
        }

    }
}
