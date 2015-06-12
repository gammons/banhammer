package me.grantammons.banhammer.core
import me.grantammons.banhammer.core.entities.Entity
import me.grantammons.banhammer.core.entities.Imp
import me.grantammons.banhammer.core.entities.Monster
import me.grantammons.banhammer.core.entities.Player
import me.grantammons.banhammer.core.utils.Scheduler

class Game {
    Player player
    Map map
    ArrayList<Entity> entities

    private Scheduler scheduler

    Game() {
        player = new Player()
        player.setX(1)
        player.setY(1)

        map = new Map()
        entities = []
        entities << player
        scheduler = new Scheduler()
        generateMonsters()
    }

    def tick() {
        processPlayerTurn()
        processEntityTurns()
    }

    def processPlayerTurn() {
        Entity e = scheduler.currentEntity()
        if (e == null) e = scheduler.nextEntity() // special case for first turn
        e.takeTurn(map, entities)
    }

    def processEntityTurns() {
        Entity e
        e = scheduler.nextEntity()
        while (!e.equals(player)) {
            e.calculateMove(map)
            e.takeTurn(map, entities)
            e = scheduler.nextEntity()
        }
    }

    private generateMonsters() {
        Monster monster = new Imp()
        monster.setX(3)
        monster.setY(3)
        entities.add(monster)

        for(Entity e : entities) {
            scheduler.addEntity(e)
        }
    }

    boolean canMoveTo(Entity entity, int intendedDirection) {
        map.canMove(entity, intendedDirection)
    }
}
