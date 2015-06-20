package me.grantammons.rogueEngine.core;

import java.util.ArrayList;
import java.util.Collections;

public class Notifier {
    private ArrayList<String> notifications;

    public Notifier() {
        notifications = new ArrayList<String>();
    }

    public void notify(String s) {
        notifications.add(s);
    }

    public void dump() {
        for(String s : notifications) {
            System.out.println(s);
        }
    }

    public ArrayList<String> recent() {
        ArrayList<String> ret = new ArrayList<String>();

        for(int i = 0; i < notifications.size(); i++) {
            if ((i == notifications.size()) || i == 3)
                break;
            ret.add(notifications.get(notifications.size() - (i + 1)));
        }
        Collections.reverse(ret);
        return ret;
    }

}
