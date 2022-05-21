package ru.bulekov.game.render;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ru.bulekov.game.config.Settings;
import ru.bulekov.game.asset.AssetsHandler;
import ru.bulekov.game.asset.ImagesPack;
import ru.bulekov.game.asset.framedescription.FrameDescription;

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
    private String fileName;
    private List<BufferedImage> frames;
    private long framesPerSecond;

    public Animation(AssetsHandler assetsHandler, AnimationSettings animationSettings) {
        this.fileName = animationSettings.getFileName();
        this.frames = new ArrayList<>();
        this.framesPerSecond = animationSettings.getFramesPerSecond();
        ImagesPack imagesPack = assetsHandler.loadImagesPack(fileName);
        String parentDirectory = new File(fileName).getParent();
        getImage(animationSettings.getAnimationName(), parentDirectory, imagesPack);
        putFramesIntoList(animationSettings.getFramesNumber());
    }

    private void putFramesIntoList(int framesNumber) {
        int animationWidth = image.getWidth();
        int frameWidth = animationWidth / framesNumber;
        int frameHeight = image.getHeight();
        for (int i = 0; i < framesNumber; i++) {
            frames.add(image.getSubimage(i * frameWidth, 0, frameWidth, frameHeight));
        }
    }

    private void getImage(String animationName, String imageDirectory, ImagesPack imagesPack) {

        File imageFile = Path.of(ASSET_PATH, imageDirectory,  imagesPack.getMeta().getImage()).toFile();
        BufferedImage atlas = null;
        try {
            atlas = ImageIO.read(imageFile);
        } catch (IOException e) {
            log.error("Can't load image from file {}", imageFile.getAbsolutePath());
        }

        FrameDescription frame = imagesPack.getFrames().stream()
                .filter(frameDescription -> frameDescription.getFilename().equals(animationName))
                .findFirst().orElse(null);

        if (atlas == null) {
            log.error("Atlas {} is null", animationName);
            System.exit(1);
        }

        if (frame == null) {
            log.error("Frame in atlas {} is null", animationName);
            System.exit(1);
        }

        image = atlas.getSubimage(
                frame.getFrame().getX(),
                frame.getFrame().getY(),
                frame.getFrame().getW(),
                frame.getFrame().getH()
        );

    }

}
