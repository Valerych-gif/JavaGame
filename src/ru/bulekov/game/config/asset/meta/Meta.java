package ru.bulekov.game.config.asset.meta;

import lombok.Data;

@Data
public class Meta {
    private String app;
    private String version;
    private String image;
    private String format;
    private Size size;
    private String scale;
    private String smartupdate;
}
