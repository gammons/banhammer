package me.grantammons.rogueEngine.core.entities.mapCells;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.Entity;
import me.grantammons.rogueEngine.core.entities.items.props.Prop;

import java.util.ArrayList;

public class MapCell extends Entity {
    private ArrayList<Prop> props;

    public MapCell(Notifier notifier) {
        super(notifier);
        props = new ArrayList<Prop>();
    }

    public void addProp(Prop p) {
        props.add(p);
    }

    public void removeProp(Prop p) {
        props.remove(p);
    }
}
