package ru.bulekov.game.gameobject;

import lombok.Data;

@Data
public class Controller {
    private boolean goingRight;
    private boolean goingLeft;
    private boolean jump;
}
