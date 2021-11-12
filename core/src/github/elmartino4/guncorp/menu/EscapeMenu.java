package github.elmartino4.guncorp.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;
import github.elmartino4.guncorp.Menu;

import java.util.function.Consumer;

public class EscapeMenu extends Menu {
    BitmapFont font;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    Consumer<MenuData> menuDataConsumer;

    public EscapeMenu(Consumer<MenuData> consumer){
        menuDataConsumer = consumer;
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
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        String text = "Press ESC to return to the game\nQ to quit\nB to enter My Corporation menu\nC for Corpopedia";
        batch.begin();

        font.draw(batch, text, Gdx.graphics.getWidth() / 4F, Gdx.graphics.getHeight() / 3F * 2F,
                Gdx.graphics.getWidth()/2F, Align.center, false);

        if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) menuDataConsumer.accept(MenuData.QUIT);
        if(Gdx.input.isKeyJustPressed(Input.Keys.B)) menuDataConsumer.accept(MenuData.MY_CORP);
        if(Gdx.input.isKeyJustPressed(Input.Keys.C)) menuDataConsumer.accept(MenuData.PEDIA);

        batch.end();
    }
}
