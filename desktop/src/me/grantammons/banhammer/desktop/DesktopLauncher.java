package me.grantammons.banhammer.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.grantammons.rogueEngine.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 375 * 2;
		config.width = 667 * 2;
		new LwjglApplication(new MainGame(), config);
	}
}
