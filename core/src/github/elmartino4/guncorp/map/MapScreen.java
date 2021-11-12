package github.elmartino4.guncorp.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import github.elmartino4.guncorp.Menu;
import github.elmartino4.guncorp.ScreenAdapter;
import github.elmartino4.guncorp.menu.AreaMenu;
import github.elmartino4.guncorp.menu.EscapeMenu;
import github.elmartino4.guncorp.menu.MenuData;

import java.util.function.Consumer;

public class MapScreen extends ScreenAdapter {
    private static final float ACCELERATION = 20F;
    private static final float MIN_VELOCITY = 2.5F;
    private static final int EDGE = 160;
    private static final int GRID = 48;
    private static final float MAP_SIZE = 40;
    private final Menu[] MENUS;

    private int currentMenu = -1;
    private final float[] pos = new float[2];
    private final float[] velocity = new float[2];
    public final MapData mapData = new MapData(42069);
    public Consumer<MenuData> menuDataConsumer;

    public MapScreen(Consumer<MenuData> consumer) {
        menuDataConsumer = consumer;
        MENUS = new Menu[]{
                new EscapeMenu(consumer),
                new AreaMenu(this::onAreaMenu)
        };
    }

    @Override
    public void create() {
        for (Menu menu : MENUS) {
            menu.create();
        }
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        if (currentMenu == -1) {
            if (Gdx.input.getX() < EDGE || Gdx.input.isKeyPressed(Input.Keys.LEFT)
                    || Gdx.input.isKeyPressed(Input.Keys.A)) {
                if (velocity[0] == 0) velocity[0] = -MIN_VELOCITY;
                velocity[0] -= ACCELERATION * Gdx.graphics.getDeltaTime();
            } else if (Gdx.input.getX() > Gdx.graphics.getWidth() - EDGE || Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                    || Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (velocity[0] == 0) velocity[0] = MIN_VELOCITY;
                velocity[0] += ACCELERATION * Gdx.graphics.getDeltaTime();
            } else {
                velocity[0] = 0;
            }

            if (Gdx.input.getY() > Gdx.graphics.getHeight() - EDGE || Gdx.input.isKeyPressed(Input.Keys.DOWN)
                    || Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (velocity[1] == 0) velocity[1] = -MIN_VELOCITY;
                velocity[1] -= ACCELERATION * Gdx.graphics.getDeltaTime();
            } else if (Gdx.input.getY() < EDGE || Gdx.input.isKeyPressed(Input.Keys.UP)
                    || Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (velocity[1] == 0) velocity[1] = MIN_VELOCITY;
                velocity[1] += ACCELERATION * Gdx.graphics.getDeltaTime();
            } else {
                velocity[1] = 0;
            }

            if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
                currentMenu = 1;
                ((AreaMenu)MENUS[1]).setMenuLocation(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
                    // idk why it just is
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            currentMenu = (currentMenu != -1) ? -1 : 0;

        pos[0] += velocity[0] * Gdx.graphics.getDeltaTime();

        pos[1] += velocity[1] * Gdx.graphics.getDeltaTime();

        pos[0] = Math.max(Math.min(pos[0], MAP_SIZE), -MAP_SIZE);

        pos[1] = Math.max(Math.min(pos[1], MAP_SIZE), -MAP_SIZE);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int x = -1; x <= Gdx.graphics.getWidth() / GRID + 1; x++) {
            for (int y = -1; y <= Gdx.graphics.getHeight() / GRID + 1; y++) {
                Color color = mapData.getColor(x + (int)pos[0], y + (int)pos[1]);
                shapeRenderer.rect((x - pos[0] % 1) * GRID,
                        (y - pos[1] % 1) * GRID,
                        GRID, GRID, color, color, color, color);
            }
        }

        shapeRenderer.end();

        if (currentMenu != -1) {
            MENUS[currentMenu].render(batch, shapeRenderer);
        }
    }

    @Override
    public String getDebugText() {
        String out = String.format("Pos: %.1f, %.1f", pos[0], pos[1]);

        if (currentMenu != -1) {
            out += "\n" + MENUS[currentMenu].getDebugText();
        }

        return out;
    }

    private void onAreaMenu (MenuData menuData) {

    }
}
