package ru.bulekov.game.asset;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ru.bulekov.game.config.Settings;
import ru.bulekov.game.core.Game;
import ru.bulekov.game.gameobject.ObjectDescription;
import ru.bulekov.game.render.Animation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AssetsHandler {

    private Settings settings;

    private final Map<String, ObjectDescription> objects = new HashMap<>();

    public ImagesPack loadImagesPack(String fileName) {
        File imagePackFile = Path.of(String.valueOf(settings.getValue("asset_path")), fileName).toFile();
        ImagesPack imagesPack = new ImagesPack();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            imagesPack = objectMapper.readValue(imagePackFile, ImagesPack.class);
        } catch (IOException e) {
            log.error("Can't load image pack from file {}", imagePackFile.getAbsolutePath());
            System.exit(1);
        }
        return imagesPack;
    }

    public ObjectDescription getObject(String objectName) {
        if (objects.get(objectName) == null) {
            loadObject(objectName);
        }
        return objects.get(objectName);
    }

    private void loadObject(String objectName) {
        String objectDescriptionsDir = (String) settings.getValue("asset_path");
        File dir = new File(objectDescriptionsDir);
        File[] files = dir.listFiles();

        if (files == null || files.length == 0) {
            log.error("Directory {} hasn't any directories or files", dir.getAbsolutePath());
            System.exit(1);
        }

        for (File file : files) {
            String fileName = file.getName();
            if (file.isDirectory() && objectName.equalsIgnoreCase(fileName)) {
                loadObjectFromFile(objectName, file);
            }
        }

    }

    private void loadObjectFromFile(String objectName, File file) {
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get(file.getAbsolutePath(), "description.json");
        File fullFilePath = path.toFile();
        try {
            ObjectDescription description = mapper.readValue(fullFilePath, new TypeReference<>() {
            });
            objects.put(objectName, description);
        } catch (IOException e) {
            log.error("Can't load object from file {}", fullFilePath.getAbsolutePath());

            System.exit(1);
        }
    }

    public Animation getAnimation() {
        return null;
    }

    public void init(Game game) {
        this.settings = game.getSettings();
    }
}
