package com.pointsoftango.server.notifications.managers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringsHandler {

    public static String processHTMLText(String text, Map<String, String> data) {
        String notificationText =  text.replace("[i]", "<i>")
                .replace("[/i]", "</i>")
                .replace("[b]", "<b>")
                .replace("[/b]", "</b>")
                .replace("[u]", "<u>")
                .replace("[/b]", "</u>");
        for (String key : data.keySet())
            notificationText = notificationText.replace(key, data.get(key));
        return notificationText;
    }

    public static String processHTMLText(String text) {
        String notificationText =  text.replace("[i]", "<i>")
                .replace("[/i]", "</i>")
                .replace("[b]", "<b>")
                .replace("[/b]", "</b>")
                .replace("[u]", "<u>")
                .replace("[/b]", "</u>");

        return notificationText.replaceAll("ENTER", "\n");
    }

    public static String processText(String text, Map<String, String> data) {
        String notificationText =  text.replace("[i]", "")
                .replace("[/i]", "")
                .replace("[b]", "")
                .replace("[/b]", "")
                .replace("[u]", "")
                .replace("[/b]", "");
        for (String key : data.keySet())
            notificationText = notificationText.replace(key, data.get(key));
        return notificationText;
    }

    public static List<String> subtract(List<String> list, List<String> subtract) {
        return list.stream().filter(artist -> !subtract.contains(list)).collect(Collectors.toList());
    }

}

