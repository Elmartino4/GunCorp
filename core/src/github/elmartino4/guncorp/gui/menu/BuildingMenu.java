package github.elmartino4.guncorp.gui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import github.elmartino4.guncorp.GameData;
import github.elmartino4.guncorp.gui.screen.MapScreen;

public class BuildingMenu extends AbstractMenu {
    public ContextMenuData contextMenuData = new ContextMenuData();
    public int[] mousePos;
    private static final int OFFSET = 30;
    public static final Color BORDER = new Color(0.08F, 0.08F, 0.08F, 1);
    public static final Color INNER = new Color(0.3F, 0.3F, 0.3F, 1);

    BitmapFont font;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public BuildingMenu(GameData gameData) {
        super(gameData);
    }

    @Override
    public void create() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("ShareTechMono-Regular.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;

        font = generator.generateFont(parameter);
    }

    @Override
    public void render() {
        int[] size = contextMenuData.getSize();

        super.data.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        super.data.shapeRenderer.rect(OFFSET, Gdx.graphics.getHeight() - OFFSET - size[1], size[0], size[1],
                INNER, INNER, INNER, INNER);
        super.data.shapeRenderer.rect(OFFSET + 2, Gdx.graphics.getHeight() - OFFSET - size[1] - 2,
                size[0] - 4, size[1] + 4, BORDER, BORDER, BORDER, BORDER);

        super.data.shapeRenderer.end();

        /*super.data.batch.begin();

        int posY = y + contextMenuData.getSize()[1];
        for (ContextMenuData.ContextSubSection section : contextMenuData.data) {
            font.draw(super.data.batch, section.text, x + 7, posY - 7);
            posY -= contextMenuData.height;
        }

        super.data.batch.end();*/
    }

    @Override
    public void dispose() {
        font.dispose();
        generator.dispose();
    }
}
