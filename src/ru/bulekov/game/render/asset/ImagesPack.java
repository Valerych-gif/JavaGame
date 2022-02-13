package ru.bulekov.game.render.asset;

import lombok.Data;
import ru.bulekov.game.render.asset.framedescription.FrameDescription;
import ru.bulekov.game.render.asset.meta.Meta;

import java.util.List;

@Data
public class ImagesPack {
    private List<FrameDescription> frames;
    private Meta meta;
}
