package ru.bulekov.game.config;

import java.util.Map;

public class Settings {

    private static Settings instance;

    private Map<String, Object> settings;

    private Settings() {
    }

    public static Settings getInstance() {
        if (instance == null) {
            synchronized (Settings.class) {
                if (instance == null)
                    instance = new Settings();
            }
        }
        return instance;
    }

    public void setValue(String key, Object value) {
        settings.put(key, value);
    }

    public Object getValue(String key) {
        return settings.get(key);
    }

    public void setValues(Map<String, Object> settingsMap) {
        settings = settingsMap;
    }
}
