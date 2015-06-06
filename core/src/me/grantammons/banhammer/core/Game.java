package me.grantammons.banhammer.core;

import me.grantammons.banhammer.core.monsters.Imp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grantammons on 5/31/15.
 */
public class Game {
    public Player player;
    public Map map;
    public List<Entity> entities;

    private static final int TURN_LENGTH = 100;

    public Game() {
        player = new Player();
        player.x = 1;
        player.y = 1;
        player.speed = 10;
        map = new Map();
        entities = new ArrayList<Entity>();
        entities.add(player);
        generateMonsters();
    }

    public boolean canPlayerMove(int direction) {
        return map.canMove(player, direction);
    }

    public void movePlayer(int direction) {
        player.move(direction);
    }

    public void tick() {
        //for(Entity entity : entities) {
    }

    private void generateMonsters() {
        Monster monster = new Imp();
        monster.x = 3;
        monster.y = 3;
        entities.add(monster);
    }
}
