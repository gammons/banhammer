package me.grantammons.rogueEngine.core.entities.items.Lights;

import me.grantammons.rogueEngine.core.Notifier;
import me.grantammons.rogueEngine.core.entities.items.props.Prop;

public class Light extends Prop {
    protected int brightness = 128;
    protected boolean flickering = false;

    public Light(Notifier notifier) {
        super(notifier);
    }

    public enum LightTypes {Torch, Spotlight};

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public boolean isFlickering() {
        return flickering;
    }

    public void setFlickering(boolean flickering) {
        this.flickering = flickering;
    }

}
