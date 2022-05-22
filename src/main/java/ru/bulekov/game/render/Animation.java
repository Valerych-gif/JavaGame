package ru.bulekov.game.render;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.bulekov.game.asset.ImagesPack;
import ru.bulekov.game.asset.framedescription.FrameDescription;
import ru.bulekov.game.config.Settings;
import ru.bulekov.game.gameobject.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class Animation {

    private final String ASSET_PATH = (String) Settings.getInstance().getValue("asset_path");

    private BufferedImage image;
    private List<BufferedImage> frames;
    private long framesPerSecond;

    private GameObject gameObject;
    private AnimationSettings animationSettings;

    public Animation(GameObject gameObject, AnimationSettings animationSettings) {
        this.gameObject = gameObject;
        this.animationSettings = animationSettings;
        this.framesPerSecond = animationSettings.getFramesPerSecond();
        this.frames = new ArrayList<>();
        getAnimationFromFile();
        putFramesIntoList(animationSettings.getFrameWidth());
    }

    private void getAnimationFromFile() {

        Path animationDescriptionPath = Path.of(ASSET_PATH, gameObject.getGameObjectId(), "animations", animationSettings.getFileName());
        ImagesPack imagesPack = gameObject.getAssetsHandler().loadImagesPack(animationDescriptionPath);

        File imageFile = Path.of(ASSET_PATH, gameObject.getGameObjectId(), "animations", imagesPack.getMeta().getImage()).toFile();
        BufferedImage atlas = null;
        try {
            atlas = ImageIO.read(imageFile);
        } catch (IOException e) {
            log.error("Can't load image from file {}", imageFile.getAbsolutePath());
        }

        FrameDescription frame = imagesPack.getFrames().stream()
                .filter(frameDescription -> frameDescription.getFilename().equals(animationSettings.getAnimationName() + ".png"))
                .findFirst().orElse(null);

        if (atlas == null) {
            log.error("Atlas {} is null", animationSettings.getAnimationName());
            System.exit(1);
        }

        if (frame == null) {
            log.error("Frame in atlas {} is null", animationSettings.getAnimationName());
            System.exit(1);
        }

        image = atlas.getSubimage(
                frame.getFrame().getX(),
                frame.getFrame().getY(),
                frame.getFrame().getW(),
                frame.getFrame().getH()
        );

    }

    private void putFramesIntoList(int frameWidth) {
        int animationWidth = image.getWidth();
        int framesNumber = animationWidth / frameWidth;
        int frameHeight = image.getHeight();
        for (int i = 0; i < framesNumber; i++) {
            frames.add(image.getSubimage(i * frameWidth, 0, frameWidth, frameHeight));
        }
    }

}
