package github.elmartino4.guncorp.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import github.elmartino4.guncorp.GunCorpMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "GunCorp";
		config.width = 1500;
		config.height = 800;
		new LwjglApplication(new GunCorpMain(), config);
	}
}
