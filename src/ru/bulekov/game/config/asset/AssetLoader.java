package ru.bulekov.game.config.asset;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.bulekov.game.config.Settings;
import ru.bulekov.game.gameobject.ObjectDescription;
import ru.bulekov.game.gameobject.player.PlayerDescription;
import ru.bulekov.game.render.Animation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssetLoader {

    private static final Map<String, ObjectDescription> objects = new HashMap<>();
    private static Logger logger = Logger.getLogger("Simple Logger");

    public static ImagesPack loadImagesPack(String fileName) {
        String file = Settings.getValue("asset_path") + "/" + fileName;
        ImagesPack imagesPack = new ImagesPack();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            imagesPack = objectMapper.readValue(new File(file), ImagesPack.class);
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        return imagesPack;
    }

    public static ObjectDescription getObject(String objectName, ObjectDescription objectDescription) {
        if (objects.get(objectName)==null){
            loadObject(objectName, objectDescription);
        }
        return objects.get(objectName);
    }

    private static void loadObject(String objectName, ObjectDescription objectDescription) {
        String objectDescriptionsDir = (String) Settings.getValue("asset_path");
        File dir = new File(objectDescriptionsDir);
        File[] files = dir.listFiles();

        if (files == null || files.length == 0){
            logger.log(Level.SEVERE, "Directory " + objectDescriptionsDir + " hasn't any directories or files");
            System.exit(1);
            return;
        }

        for (File file: files){
            String fileName = file.getName();
            if (file.isDirectory()&& objectName.equalsIgnoreCase(fileName)){
                loadObjectFromFile(objectName, file, objectDescription);
            }
        }

    }

    private static void loadObjectFromFile(String objectName, File file, ObjectDescription objectDescription) {
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get(file.getAbsolutePath(), "description.json");
        File fullFilePath = path.toFile();
        try {
            ObjectDescription description = mapper.readValue(fullFilePath, objectDescription.getClass());
            objects.put(objectName, description);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't read object " + objectDescription + " from file " + fullFilePath);
            System.exit(1);
        }
    }

    public static Animation getAnimation() {
        return null;
    }
}
