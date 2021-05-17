package com.inspiring.solutions.notifications.server.managers;

import com.inspiring.solutions.notifications.server.languages.English_GB;
import com.inspiring.solutions.notifications.server.languages.Language;
import com.inspiring.solutions.notifications.server.listeners.DataAccessListener;
import com.inspiring.solutions.notifications.server.model.*;
import com.inspiring.solutions.notifications.server.services.FirebaseService;
import com.inspiring.solutions.notifications.server.services.notifications.NotificationsService;

import java.util.*;

public class NotificationsManager {

    private final NotificationsService notificationsService;

    public NotificationsManager(NotificationsService notificationsService) {
        this.notificationsService = notificationsService;
    }

    //TODO Add users to topics
    public void handleNotificationsByTopic(Notification notification) {
        //TODO How to create Items?
        if (notification.isToPush())
            FirebaseService.sendNotifications(notification.getTopic(), createNotification(notification, English_GB.getInstance()), notification.getId());
    }

    public void handleNotificationsMulticast(Notification notification) {
        //TODO will it change onSuccess on the next iteration?

        // 1st Step - Fetch data from each target: Tokens, Settings, TimeZone, Language
        notificationsService.fetchUsersTargeted(notification.getUserNames(), new DataAccessListener<Map<String, UserNotifications>>() {
            @Override
            public void onSuccess(Map<String, UserNotifications> targeted) {
                // 2nd Step - Create notifications for users (if the notification is to be shown)
                if (notification.isToShow())
                    notificationsService.saveUserNotifications(createItems(notification, targeted));

                if (notification.isToPush()) {
                    // 3rd Step - Remove from targeted: A - Whom will never be pushed; B - Whom will be pushed later
                    // Leaving on the users list only users will be notified in future. Verification step 4.
                    List<String> never = removeNeverTargets(notification.getType(), targeted);
                    notification.getUserNames().removeAll(never);
                    List<String> later = removeLaterTargets(notification, targeted);

                    // 4th Step - Remove targets from notification, leaving whom will be notified later.
                    // Remaining in targeted are the one to push now, so we remove them, leaven just the later ones
                    notification.getUserNames().removeAll(targeted.keySet());

                    //Verification step, users left should be the same than later size.
                    if (notification.getUserNames().size()!=later.size())
                        System.out.println("Something went wrong, users.size | later.size " + notification.getUserNames().size() + "|" +  later.size());

                    // 5th Step - Generates a map per language to send notifications on expected language
                    Map<Language, List<String>> languageMap = groupTokensByLanguage(targeted);
                    for (Language language : languageMap.keySet())
                        // 6th Step - Generate the Messages to Push (including targeted tokens) and Send
                        FirebaseService.sendNotifications(languageMap.get(language), createNotification(notification, language), notification.getId());
                }
                else
                    // Nothing else to do. clean the users to notify.
                    notification.getUserNames().clear();
            }
        });
    }

    private Map<Language, List<String>> groupTokensByLanguage(Map<String, UserNotifications> targeted) {
        Map<Language, List<String>> map = new HashMap<>();
        for (String username : targeted.keySet()) {
            Language language = Language.getLanguage(targeted.get(username).getLanguage());
            map.computeIfAbsent(language, k -> new ArrayList<>());
            map.get(language).addAll(targeted.get(username).getTokens());
        }
        return map;
    }

    private com.google.firebase.messaging.Notification createNotification(Notification notification, Language language) {
        return com.google.firebase.messaging.Notification.builder()
                .setTitle(StringsHandler.processText(language.getTitleText(notification.getType().getPreText()), notification.getData()))
                .setBody(StringsHandler.processText(language.getBodyText(notification.getType().getPreText()), notification.getData()))
                .setImage(notification.getPicture())
                .build();
    }

    private static Map<String, UserNotificationItem> createItems(Notification notification, Map<String, UserNotifications> targeted) {
        Map<String, UserNotificationItem> map = new HashMap<>();
        for (String username : targeted.keySet())
            map.put(username, new UserNotificationItem(notification.getId()));

        return map;
    }

    /*
        Filter users will never be pushed.
        Outcome: Returns a list of this users to update notification user list and removes this users from the targeted map.
     */
    private static List<String> removeNeverTargets(NotificationType type, Map<String, UserNotifications> targeted) {
        List<String> usernames = new ArrayList<>();
        for (String username : targeted.keySet())
            if (filterPushNever(type, targeted.get(username).getSettings()))
                usernames.add(username);

        usernames.forEach(targeted::remove);
        return usernames;
    }

    private static List<String> removeLaterTargets(Notification notification, Map<String, UserNotifications> targeted) {
        List<String> usernames = new ArrayList<>();

        if (notification.getPriority().equals(NotificationPriority.URGENT))
            return usernames;

        for (String username : targeted.keySet())
            if (targeted.get(username).getTimeZone()==null || filterPushLater(notification.getType(), targeted.get(username).getTimeZone()))
                usernames.add(username);

        usernames.forEach(targeted::remove);
        return usernames;
    }

    private static List<String> getTokens(Map<String, UserNotifications> targeted) {
        List<String> tokens = new ArrayList<>();
        for (String username : targeted.keySet()) {
            tokens.addAll(targeted.get(username).getTokens());
        }
        return tokens;
    }

    /*
        Based on Settings, the user might never be pushed with this notification.
     */
    private static boolean filterPushNever(NotificationType type, NotificationSett settings) {
        if (type.isInterested() && !settings.getFromInterested())
            return true;

        if (type.isRegistered() && !settings.getFromRegistered())
            return true;

        return false;
    }

    /*
        True if user not ready for the push yet due TimeZone.
     */
    private static boolean filterPushLater(NotificationType type, String timeZone) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone(timeZone));

        int hour = calendar.get(Calendar.HOUR);

        return !((hour >= 7 && hour <= 10) || (hour >= 18 && hour <= 22));
    }

}
