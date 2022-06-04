package ru.bulekov.game.asset.framedescription;

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
