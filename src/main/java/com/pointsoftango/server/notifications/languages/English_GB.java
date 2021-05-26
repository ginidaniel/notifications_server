package com.pointsoftango.server.notifications.languages;

import com.pointsoftango.server.notifications.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class English_GB extends Language {

    public static Language singleton;
    private Map<String, String> titleMap;
    private Map<String, String> bodyMap;


    private English_GB() {
        initMap();
    }

    public static Language getInstance() {
        if (singleton==null)
            singleton = new English_GB();
        return singleton;
    }

    @Override
    public String getBodyText(String key) {
        return bodyMap.get(key);
    }
    @Override
    public String getTitleText(String key) {
        return titleMap.get(key);
    }

    @Override
    public String myLanguage() {
        return "EN_GB";
    }

    private void initMap() {
        titleMap = new HashMap<>();
        bodyMap = new HashMap<>();

        bodyMap.put(Constants.EVENT_ACTIVITY_ADDED, "The activity [b]activity_name[/b] has been added to [b]event_name[/b]");
        bodyMap.put(Constants.EVENT_ACTIVITY_UPDATED, "The activity [b]activity_name[/b] has been updated in [b]event_name[/b]");
        bodyMap.put(Constants.EVENT_ACTIVITY_REMOVED, "The activity [b]activity_name[/b] has been removed from [b]event_name[/b]");
        bodyMap.put(Constants.EVENT_CHANGE_STATUS, "Your new status [i]new_status[/i] at [b]event_name[/b]");
        bodyMap.put(Constants.EVENT_ACTIVITY_REMOVED_ARTISTS, "You have been removed from [b]activity_name[/b] in [b]event_name[/b]");
        bodyMap.put(Constants.EVENT_ACTIVITY_ADDED_ARTISTS, "You have been added to [b]activity_name[/b] in [b]event_name[/b]");

        titleMap.put(Constants.EVENT_ACTIVITY_ADDED, "Activity added");
        titleMap.put(Constants.EVENT_ACTIVITY_UPDATED, "Activity updated");
        titleMap.put(Constants.EVENT_ACTIVITY_REMOVED, "Activity removed");
        titleMap.put(Constants.EVENT_CHANGE_STATUS, "Registration status update");
        titleMap.put(Constants.EVENT_ACTIVITY_REMOVED_ARTISTS, "Removed from activity");
        titleMap.put(Constants.EVENT_ACTIVITY_ADDED_ARTISTS, "Added to activity");
    }

}
