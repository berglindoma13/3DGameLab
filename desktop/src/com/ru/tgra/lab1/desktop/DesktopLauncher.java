package com.ru.tgra.lab1.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ru.tgra.berglindoma13_Haukura14.LabFirst3DGame;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

		config.title = "Lab1"; // or whatever you like
		config.width = 768; //(int)size.getWidth();  //experiment with
		config.height = 769; //(int)size.getHeight();  //the window size
		//config.x = 150;
		//config.y = 50;
		//config.fullscreen = true;

		new LwjglApplication(new LabFirst3DGame(), config);
	}
}
