package me.grantammons.banhammer.core;

import java.util.ArrayList;

public class Notifier {
    private ArrayList<String> notifications;

    public Notifier() {
        notifications = new ArrayList<String>();
    }

    public void notify(String s) {
        notifications.add(s);
    }


}
