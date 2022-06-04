package ru.bulekov.game.render;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimationSettings {
    private String animationName;
    private String fileName;
    private int frameWidth;
    private long framesPerSecond;
}
