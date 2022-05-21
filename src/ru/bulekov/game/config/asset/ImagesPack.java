package ru.bulekov.game.config.asset;

import lombok.Data;
import ru.bulekov.game.config.asset.framedescription.FrameDescription;
import ru.bulekov.game.config.asset.meta.Meta;

import java.util.List;

@Data
public class ImagesPack {
    private List<FrameDescription> frames;
    private Meta meta;
}
