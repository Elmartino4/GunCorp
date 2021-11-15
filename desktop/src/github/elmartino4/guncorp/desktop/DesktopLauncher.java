package github.elmartino4.guncorp.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import github.elmartino4.guncorp.config.ConfigChangeCallback;
import github.elmartino4.guncorp.GunCorpMain;
import github.elmartino4.guncorp.config.ReflectionUtil;

public class DesktopLauncher {
	public static void main(String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		// UserConfig.generate();
		config.setTitle("GunCorp");
		config.setWindowedMode(1500, 800);
		config.setWindowSizeLimits(1150, 430, Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);
		config.setForegroundFPS(0);
		config.useVsync(false);

		new Lwjgl3Application(new GunCorpMain(new ConfigChangeCallback() {
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
