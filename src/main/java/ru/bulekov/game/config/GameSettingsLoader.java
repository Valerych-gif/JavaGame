package ru.bulekov.game.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GameSettingsLoader {

    public Settings loadSettings() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> settingsMap = new HashMap<>();
        File file = new File(GameConstants.SETTINGS_FILE);
        try {
            settingsMap = objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            log.error("Can't load settings from file {}", file.getAbsolutePath(), e);
        }

        Settings settings = Settings.getInstance();
        settings.setValues(settingsMap);
        return settings;
    }
}
