package github.elmartino4.guncorp.gui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import github.elmartino4.guncorp.GameData;

public class EscapeMenu extends AbstractMenu {
    BitmapFont font;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public EscapeMenu(GameData gameData) {
        super(gameData);
    }

    @Override
    public void create() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("ShareTechMono-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        parameter.borderColor = new Color(0, 0, 0, 1);
        parameter.borderWidth = 2;

        font = generator.generateFont(parameter);
    }

    @Override
    public void render() {
        String text = "Press ESC to return to the game\nQ to quit\nM to go to main menu\nB to enter My Corporation menu\nC for Corpopedia";
        super.data.batch.begin();

        font.draw(super.data.batch, text, Gdx.graphics.getWidth() / 4F, Gdx.graphics.getHeight() / 3F * 2F,
                Gdx.graphics.getWidth() / 2F, Align.center, false);

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) super.data.menuDataConsumer.accept(MenuData.QUIT);
        if (Gdx.input.isKeyJustPressed(Input.Keys.B)) super.data.menuDataConsumer.accept(MenuData.MY_CORP);
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) super.data.menuDataConsumer.accept(MenuData.PEDIA);
        if (Gdx.input.isKeyJustPressed(Input.Keys.M)) super.data.menuDataConsumer.accept(MenuData.MAP);

        super.data.batch.end();
    }

    @Override
    public void dispose() {
        generator.dispose();
        font.dispose();
    }
}
