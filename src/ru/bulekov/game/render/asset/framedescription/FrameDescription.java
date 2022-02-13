package ru.bulekov.game.render.asset.framedescription;

import lombok.Data;

@Data
public class FrameDescription {
    private String filename;
    private Frame frame;
    private Boolean rotated;
    private Boolean trimmed;
    private SpriteSourceSize spriteSourceSize;
    private SourceSize sourceSize;
}
