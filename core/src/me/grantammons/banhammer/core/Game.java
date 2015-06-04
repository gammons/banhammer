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

    public boolean canPlayerMove(int direction) {
        return map.canMove(player, direction);
    }

    public void movePlayer(int direction) {
        player.move(direction);
    }
}
