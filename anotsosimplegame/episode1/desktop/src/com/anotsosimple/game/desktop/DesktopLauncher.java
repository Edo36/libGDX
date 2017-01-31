package com.anotsosimple.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.anotsosimple.game.ANotSoSimpleGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new ANotSoSimpleGame(), config);

		config.width = 1024;
		config.height = 512;
		config.title = "A Not So Simple Game";
	}
}
