package github.elmartino4.guncorp.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import github.elmartino4.guncorp.ConfigChangeCallback;
import github.elmartino4.guncorp.GunCorpMain;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// UserConfig.generate();
		config.title = "GunCorp";
		config.width = 1500;
		config.height = 800;
		config.foregroundFPS = 0;
		config.vSyncEnabled = false;
		new LwjglApplication(new GunCorpMain(new ConfigChangeCallback() {
			@Override
			public void setForegroundFPS(int foregroundFPS) {
				config.foregroundFPS = foregroundFPS;
			}

			@Override
			public void setBackgroundFPS(int backgroundFPS) {
				config.backgroundFPS = backgroundFPS;
			}
		}), config);
	}
}
