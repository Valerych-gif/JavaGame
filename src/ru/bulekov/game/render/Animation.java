package ru.bulekov.game.render;

import lombok.Data;
import ru.bulekov.game.config.Settings;
import ru.bulekov.game.config.asset.AssetLoader;
import ru.bulekov.game.config.asset.ImagesPack;
import ru.bulekov.game.config.asset.framedescription.FrameDescription;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class Animation {

    private final String ASSET_PATH = (String) Settings.getValue("asset_path");

    private BufferedImage image;
    private String fileName;
    private List<BufferedImage> frames;
    private long framesPerSecond;

    public Animation(String animationName, String fileName, int framesNumber, long framesPerSecond) {
        this.fileName = fileName;
        this.frames = new ArrayList<>();
        this.framesPerSecond = framesPerSecond;
        ImagesPack imagesPack = AssetLoader.loadImagesPack(fileName);
        getImage(animationName, imagesPack);
        putFramesIntoList(framesNumber);
    }

    private void putFramesIntoList(int framesNumber) {
        int animationWidth = image.getWidth();
        int frameWidth = animationWidth/ framesNumber;
        int frameHeight = image.getHeight();
        for (int i = 0; i < framesNumber; i++) {
            frames.add(image.getSubimage(i * frameWidth, 0, frameWidth, frameHeight));
        }
    }

    private void getImage(String animationName, ImagesPack imagesPack) {
        try {
            File imageFile = new File(ASSET_PATH + imagesPack.getMeta().getImage());
            System.out.println(imageFile.getAbsolutePath());
            BufferedImage atlas = ImageIO.read(imageFile);

            FrameDescription frame = imagesPack.getFrames().stream()
                    .filter(frameDescription -> frameDescription.getFilename().equals(animationName))
                    .findFirst().orElse(null);
            assert frame != null;
            image = atlas.getSubimage(
                    frame.getFrame().getX(),
                    frame.getFrame().getY(),
                    frame.getFrame().getW(),
                    frame.getFrame().getH()
            );
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
