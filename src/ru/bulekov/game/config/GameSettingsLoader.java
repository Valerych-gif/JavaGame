package ru.bulekov.game.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameSettingsLoader {

    private GameSettingsLoader() {
    }

    public static Settings loadSettings() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map <String, Object> settingsMap = new HashMap<>();
        try {
            settingsMap = objectMapper.readValue(new File(GameConstants.SETTINGS_FILE), new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        Settings settings = new Settings();
        settings.setValues(settingsMap);
        return settings;
    }
}
