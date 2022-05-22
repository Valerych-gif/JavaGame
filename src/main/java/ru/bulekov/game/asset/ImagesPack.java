package ru.bulekov.game.asset;

import lombok.Data;
import ru.bulekov.game.asset.framedescription.FrameDescription;
import ru.bulekov.game.asset.meta.Meta;

import java.util.List;

@Data
public class ImagesPack {
    private List<FrameDescription> frames;
    private Meta meta;
}
