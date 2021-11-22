package github.elmartino4.guncorp.gui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import github.elmartino4.guncorp.GameData;
import github.elmartino4.guncorp.corporation.CorpopediaEntry;

public class CorpopediaScreen extends AbstractScreen {
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont titleFont;
    BitmapFont bodyFont;
    CorpopediaEntry entry;

    Color boxColor = new Color(0.1F, 0.1F, 0.1F, 1);
    Color borderColor = new Color(0.3F, 0.3F, 0.3F, 1);

    public CorpopediaScreen(GameData data) {
        super(data);
        setEntry(69);
        entry.loadValues();
    }

    public void setEntry(long entryId) {
        entry = new CorpopediaEntry(entryId);
    }

    @Override
    public void create() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("ShareTechMono-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 70;

        titleFont = generator.generateFont(parameter);

        parameter.size = 34;

        bodyFont = generator.generateFont(parameter);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.12F, 0.12F, 0.12F, 1);

        super.data.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        super.data.shapeRenderer.rect(Gdx.graphics.getWidth() - 422, Gdx.graphics.getHeight() - 72, 404, 54,
                borderColor, borderColor, borderColor, borderColor);

        super.data.shapeRenderer.rect(Gdx.graphics.getWidth() - 420, Gdx.graphics.getHeight() - 70, 400, 50,
                boxColor, boxColor, boxColor, boxColor);

        super.data.shapeRenderer.rect(70, 0, Gdx.graphics.getWidth() - 140, Gdx.graphics.getHeight() - 90,
                borderColor, borderColor, borderColor, borderColor);

        super.data.shapeRenderer.end();

        super.data.batch.begin();

        titleFont.draw(super.data.batch, entry.title, 20, Gdx.graphics.getHeight() - 20);
        bodyFont.draw(super.data.batch, entry.innerMD, 90, Gdx.graphics.getHeight() - 100);

        super.data.batch.end();
    }
}
