package github.elmartino4.guncorp.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class UserConfig {
    static public Preferences prefs;
    static public ConfigChangeCallback configChangeCallback;

    static public void generate() {
        prefs = Gdx.app.getPreferences("GunCorpConfig.xml");

        if (prefs.contains("width") || prefs.contains("height")) {
            Gdx.graphics.setWindowedMode(prefs.getInteger("width", (int) configChangeCallback.getConfig("width")),
                    prefs.getInteger("height", (int) configChangeCallback.getConfig("height")));
        } else {
            prefs.putInteger("width", 1500);
            prefs.putInteger("height", 800);
        }

        if (prefs.contains("foregroundFPS")) {
            configChangeCallback.setConfig("foregroundFPS", prefs.getInteger("foregroundFPS"));
        } else {
            prefs.putInteger("foregroundFPS", 0);
        }

        if (prefs.contains("vSyncEnabled")) {
            Gdx.graphics.setVSync(prefs.getBoolean("vSyncEnabled"));
        } else {
            prefs.putBoolean("vSyncEnabled", false);
        }

        prefs.flush();
    }
}