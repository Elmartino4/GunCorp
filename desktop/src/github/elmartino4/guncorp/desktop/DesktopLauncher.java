package github.elmartino4.guncorp.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import github.elmartino4.guncorp.config.ConfigChangeCallback;
import github.elmartino4.guncorp.GunCorpMain;
import github.elmartino4.guncorp.config.ReflectionUtil;

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
			public <T> T getConfig(String key) {
				try {
					return (T) ReflectionUtil.<T>getFieldValue(config, key);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					throw new Error(e);
				}
			}

			@Override
			public void setConfig(String key, Object value) {
				try {
					ReflectionUtil.setFieldValue(config, key, value);
				} catch (NoSuchFieldException | IllegalAccessException e) {
					throw new Error(e);
				}
			}
		}), config);
	}
}
