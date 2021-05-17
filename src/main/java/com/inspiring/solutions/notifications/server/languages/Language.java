package com.inspiring.solutions.notifications.server.languages;

public abstract class Language {

    public static Language getLanguage(String language) {
        if (language==null)
            return English_GB.getInstance();

        switch (language.toUpperCase()) {
            case "EN_GB" : return English_GB.getInstance();
            case "ES_AR" : return Espanol_AR.getInstance();
            default: return English_GB.getInstance();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Language) {
            String language = ((Language) obj).myLanguage();
            return myLanguage().equals(language);
        }
        return false;
    }

    public abstract String getBodyText(String key);
    public abstract String getTitleText(String key);

    public abstract String myLanguage();

}
