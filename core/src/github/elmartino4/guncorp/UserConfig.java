package github.elmartino4.guncorp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class UserConfig {
    static public Preferences prefs;
    static public ConfigChangeCallback configChangeCallback;

    static public void generate() {
        prefs = Gdx.app.getPreferences("github.elmartino4.guncorp.UserConfig");

        if (prefs.contains("width") || prefs.contains("height")) {
            Gdx.graphics.setWindowedMode(prefs.getInteger("width", (int) configChangeCallback.getConfig("width")),
                    prefs.getInteger("height", (int) configChangeCallback.getConfig("height")));
        }

        if (prefs.contains("foregroundFPS")) {
            configChangeCallback.setConfig("foregroundFPS", prefs.getInteger("foregroundFPS"));
        }

        if (prefs.contains("vSyncEnabled")) {
            Gdx.graphics.setVSync(prefs.getBoolean("vSyncEnabled"));
        }
    }
}