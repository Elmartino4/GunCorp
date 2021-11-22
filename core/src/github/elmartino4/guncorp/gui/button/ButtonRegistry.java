package github.elmartino4.guncorp.gui.button;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import github.elmartino4.guncorp.GameData;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ButtonRegistry {
    static FreeTypeFontGenerator generator;
    static FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public static BitmapFont DEFAULT_FONT;

    private static final Color boxColor = new Color(0.1F, 0.1F, 0.1F, 1);
    private static final Color borderColor = new Color(0.3F, 0.3F, 0.3F, 1);
    List<Button> buttonList = new ArrayList<>();
    GameData data;

    public ButtonRegistry(GameData data) {
        this.data = data;
    }

    public static void create() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("ShareTechMono-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;

        DEFAULT_FONT = generator.generateFont(parameter);
    }

    public int addButton(String innerText, Supplier<int[]> posSupplier) {
        buttonList.add(new Button(innerText, posSupplier));
        return buttonList.size() - 1;
    }

    public void setButtonActive(int id, boolean val) {
        buttonList.get(id).isActive = val;
    }

    public void setButtonText(int id, String text) {
        buttonList.get(id).setText(text);
    }

    public void render() {
        boolean matchesAny = false;

        this.data.batch.begin();
        this.data.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (Button button : buttonList) {
            if (button.isActive) {
                int[] size = button.getSize();
                int[] pos = button.getPos();

                this.data.shapeRenderer.rect(pos[0], pos[1], size[0], size[1], borderColor, borderColor, borderColor, borderColor);

                if (size[0] < Gdx.input.getX() && Gdx.input.getX() < size[0] + pos[0]) matchesAny = true;
            }
        }

        if(matchesAny)
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);

        this.data.batch.end();
        this.data.shapeRenderer.end();
    }
}
