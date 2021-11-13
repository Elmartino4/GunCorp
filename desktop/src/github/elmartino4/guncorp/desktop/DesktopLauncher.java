package github.elmartino4.guncorp.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import github.elmartino4.guncorp.ConfigChangeCallback;
import github.elmartino4.guncorp.GunCorpMain;
import github.elmartino4.guncorp.ReflectionUtil;

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
			public Object getConfig(String key) {
				return ReflectionUtil.getFieldValue(this, key);
			}

			@Override
			public void setConfig(String key, Object value) {
				ReflectionUtil.setFieldValue(this, key, value);
			}
		}), config);
	}
}
