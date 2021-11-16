package github.elmartino4.guncorp.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class UserConfig {
    public static Preferences prefs;
    public static ConfigChangeCallback configChangeCallback;
    public static final int DEFAULT_WIDTH = 1500;
    public static final int DEFAULT_HEIGHT = 800;

    public static void generate() {
        prefs = Gdx.app.getPreferences("GunCorpConfig.xml");

        if (prefs.contains("width") || prefs.contains("height")) {
            Gdx.graphics.setWindowedMode(prefs.getInteger("width", DEFAULT_WIDTH),
                    prefs.getInteger("height", DEFAULT_HEIGHT));
        } else {
            prefs.putInteger("width", DEFAULT_WIDTH);
            prefs.putInteger("height", DEFAULT_HEIGHT);
        }

        if (prefs.contains("foregroundFPS")) {
            configChangeCallback.setConfig("foregroundFPS", prefs.getInteger("foregroundFPS"));
        } else {
            prefs.putInteger("foregroundFPS", configChangeCallback.getConfig("foregroundFPS"));
        }

        if (prefs.contains("vSyncEnabled")) {
            Gdx.graphics.setVSync(prefs.getBoolean("vSyncEnabled"));
        } else {
            prefs.putBoolean("vSyncEnabled", configChangeCallback.getConfig("vSyncEnabled"));
        }

        prefs.flush();
    }
}