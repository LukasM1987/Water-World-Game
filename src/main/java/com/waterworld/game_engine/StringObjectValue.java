package com.waterworld.game_engine;

public enum StringObjectValue {

    WORM ("worm"),
    RIGHT ("right"),
    LEFT ("left"),
    ENEMY ("enemy"),
    ENEMY_TWO ("enemy two"),
    BUBBLE_8X8 ("bubble8x8"),
    BUBBLE_16X16 ("bubble16x16"),
    BUBBLE_24X24 ("bubble24x24"),
    BUBBLE_36X36 ("bubble36x36"),
    UP ("up"),
    DOWN ("down"),
    ANY_DIRECTION ("any direction"),
    SEAWEED_ONE ("seaweed one"),
    SEAWEED_TWO ("seaweed two"),
    LEVEL_ONE ("level one"),
    LEVEL_TWO ("level two"),
    LEVEL_THREE ("level three"),
    LEFT_BUTTON ("left button"),
    RIGHT_BUTTON ("right button"),
    DOWN_BUTTON ("down button"),
    UP_BUTTON ("up button"),
    LEFT_INFO ("left info"),
    RIGHT_INFO ("right info"),
    DOWN_INFO ("down info"),
    UP_INFO ("up info"),
    ROCK_ONE ("rock one"),
    ROCK_TWO ("rock two"),
    DEAD_FISH ("dead fish"),
    FISH ("fish");

    private String value;

    StringObjectValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
