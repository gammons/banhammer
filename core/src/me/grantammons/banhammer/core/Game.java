package me.grantammons.banhammer.core;

/**
 * Created by grantammons on 5/31/15.
 */
public class Game {
    public Player player;
    public Map map;

    public Game() {
        player = new Player();
        player.x = 1;
        player.y = 1;
        map = new Map();
    }

    public void movePlayerLeft() {
        if (map.canMoveLeft(player))
            player.moveLeft();
    }

    public void movePlayerRight() {
        if (map.canMoveRight(player))
            player.moveRight();

    }

    public void movePlayerUp() {
        if (map.canMoveUp(player))
            player.moveUp();

    }

    public void movePlayerDown() {
        if (map.canMoveDown(player))
            player.moveDown();

    }
}
