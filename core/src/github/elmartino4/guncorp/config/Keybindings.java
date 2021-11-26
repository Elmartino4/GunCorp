package github.elmartino4.guncorp.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;

public class Keybindings {
    public static Preferences kb;

    public static void generate() {
        kb = Gdx.app.getPreferences("GunCorpKeybindings.xml");
        if (kb.get().size() == 0) {
            kb.putString("Up", "W, Up");
            kb.putString("Left", "A, Left");
            kb.putString("Right", "D, Right");
            kb.putString("Down", "S, Down");
            kb.putString("Toggle debug", "F3");
            kb.putString("Exit menu", "Escape");
            kb.putString("Enable right click menu", "mousebutton_RIGHT");
            kb.putString("Enable left click menu", "mousebutton_LEFT");
            kb.putString("Exit context menus", "mousebutton_LEFT");
            kb.putBoolean("Autoscrolling mouse", true);
        }
        kb.flush();
    }

    public static boolean isKeyJustPressed(String name) {
        return isKeyPressedGeneric(name, false);
    }

    public static boolean isKeyPressed(String name) {
        return isKeyPressedGeneric(name, true);
    }

    private static boolean isKeyPressedGeneric(String name, boolean isRepeating) {
        if (!kb.contains(name)) {
            throw new RuntimeException("Programming error: Action type \"" + name
                    + "\" does not exist. If you are a user try deleting your ~/.prefs/GunCorpKeybindings.xml");
        }

        for (String key : kb.getString(name).replaceAll("\\s", "").split(",")) {
            if (key.startsWith("mousebutton_")) {
                int keycode;
                try {
                    keycode = ReflectionUtil.<Integer>getStaticFieldValue(Input.Buttons.class,
                            key.replace("mousebutton_", ""));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                if (isRepeating ? Gdx.input.isButtonPressed(keycode) : Gdx.input.isButtonJustPressed(keycode)) {
                    return true;
                }
            } else {
                int keycode = Input.Keys.valueOf(key);

                if (keycode == -1) {
                    throw new RuntimeException("Keybinding error: Key \"" + key
                            + "\" does not exist as field in Gdx.Input.Keys https://libgdx.badlogicgames.com/ci/nightlies/docs/api/com/badlogic/gdx/Input.Keys.html");
                }

                if (isRepeating ? Gdx.input.isKeyPressed(keycode) : Gdx.input.isKeyJustPressed(keycode)) {
                    return true;
                }
            }
        }
        return false;
    }
}
