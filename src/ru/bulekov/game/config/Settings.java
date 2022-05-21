package ru.bulekov.game.config;

import java.util.Map;

public class Settings {

    private static Map<String, Object> settings;

    public static void setValue(String key, Object value){
        settings.put(key, value);
    }

    public static Object getValue(String key){
        return settings.get(key);
    }

    public static void setValues(Map<String, Object> settingsMap) {
        settings = settingsMap;
    }
}
