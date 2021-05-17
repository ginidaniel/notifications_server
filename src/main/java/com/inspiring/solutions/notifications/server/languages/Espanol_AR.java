package com.inspiring.solutions.notifications.server.languages;

import com.inspiring.solutions.notifications.server.utils.Constants;

import java.util.HashMap;
import java.util.Map;

public class Espanol_AR extends Language {

    public static Language singleton;
    private Map<String, String> titleMap;
    private Map<String, String> bodyMap;

    private Espanol_AR() {
        initMap();
    }

    public static Language getInstance() {
        if (singleton==null)
            singleton = new Espanol_AR();
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
        return "ES_AR";
    }

    private void initMap() {
        titleMap = new HashMap<>();
        bodyMap = new HashMap<>();

        bodyMap.put(Constants.EVENT_ACTIVITY_ADDED, "Se agregó la actividad [b]activity_name[/b] a [b]event_name[/b]");
        bodyMap.put(Constants.EVENT_ACTIVITY_UPDATED, "Se actualizó la actividad [b]activity_name[/b] en [b]event_name[/b]");
        bodyMap.put(Constants.EVENT_ACTIVITY_REMOVED, "Se eliminó la actividad [b]activity_name[/b] de [b]event_name[/b]");
        bodyMap.put(Constants.EVENT_CHANGE_STATUS, "Estado actualizado para [b]event_name[/b], [i]new_status[/i]");
        bodyMap.put(Constants.EVENT_ACTIVITY_REMOVED_ARTISTS, "Removido como artista de la actividad [b]activity_name[/b] en [b]event_name[/b]");
        bodyMap.put(Constants.EVENT_ACTIVITY_ADDED_ARTISTS, "Agregado como artista a la actividad [b]activity_name[/b] en [b]event_name[/b]");

        titleMap.put(Constants.EVENT_ACTIVITY_ADDED, "Nueva actividad");
        titleMap.put(Constants.EVENT_ACTIVITY_UPDATED, "Cambios en una actividad");
        titleMap.put(Constants.EVENT_ACTIVITY_REMOVED, "Actividad eliminada");
        titleMap.put(Constants.EVENT_CHANGE_STATUS, "Actualización en estado de registro");
        titleMap.put(Constants.EVENT_ACTIVITY_REMOVED_ARTISTS, "Etiqueta removida de una actividad");
        titleMap.put(Constants.EVENT_ACTIVITY_ADDED_ARTISTS, "Etiquetado en una actividad");
    }

}
