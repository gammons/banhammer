package me.grantammons.rogueEngine.core;

import me.grantammons.rogueEngine.core.items.Item;

import java.util.ArrayList;

public class Sack {
    private ArrayList<Item> items;

    public Sack() {
        items = new ArrayList<Item>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int weight() {
        int total = 0;
        for (Item i : items) { total += i.getWeight(); }
        return total;
    }

}
