package com.inspiring.solutions.notifications.server.utils;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class NotificationMessages {

//    private static final Logger LOG = LogManager.getLogger(NotificationMessages.class);

    private final static Properties properties = new Properties();
    private final static String fileName = "properties/messages.properties";

    static{

        try {
            InputStream stream = ClassLoader.getSystemResource(fileName).openStream();
            properties.load(stream);
        } catch (IOException e) {
//            LOG.error("Could not load indexer properties", e);
        }
    }

    public static Properties getProps(){
        return properties;
    }

}

