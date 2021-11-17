package github.elmartino4.guncorp.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;

public class Keybindings {
    public static Preferences kb;

    public static void generate() {
        kb = Gdx.app.getPreferences("GunCorpkb.xml");
        if (kb.get().size() == 0) {
            kb.putString("Up", "w, up");
            kb.putString("Left", "a, left");
            kb.putString("Right", "d, right");
            kb.putString("Down", "s, down");
            kb.putString("Toggle debug", "F3");
            kb.putString("Exit menu", "Escape");
            kb.putString("Go to left menu", "mousebutton_RIGHT");
            kb.putString("Go to right menu", "mousebutton_LEFT");
            kb.putBoolean("Autoscrolling mouse", true);
        }
    }

    public static boolean isKeyJustPressed(String name) {
        return isKeyPressedGeneric(name, false);
    }

    public static boolean isKeyPressed(String name) {
        return isKeyPressedGeneric(name, true);
    }

    private static boolean isKeyPressedGeneric(String name, boolean isRepeating) {
        for (String key : kb.getString(name).strip().split(",")) {
            if (key.startsWith("mousebutton_")) {
                int keycode;
                try {
                    keycode = ReflectionUtil.getFieldValue(Input.Buttons.class, key.replace("mousebutton_", ""));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (isRepeating ? Gdx.input.isButtonPressed(keycode) : Gdx.input.isKeyJustPressed(keycode)) {
                    return true;
                }
            } else {
                int keycode = Input.Keys.valueOf(key);
                if (isRepeating ? Gdx.input.isKeyPressed(keycode) : Gdx.input.isKeyJustPressed(keycode)) {
                    return true;
                }
            }
        }
        return false;
    }
}
