package github.elmartino4.guncorp.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import github.elmartino4.guncorp.GameData;
import github.elmartino4.guncorp.map.MapData;
import github.elmartino4.guncorp.map.SafeElement;
import github.elmartino4.guncorp.menu.AreaMenu;
import github.elmartino4.guncorp.menu.ContextMenuData;

import java.util.Map;

public class MapScreen extends AbstractScreen {
    private static final float ACCELERATION = 20F;
    private static final float MIN_VELOCITY = 2.5F;
    private static final int EDGE = 160;
    private static final int GRID = 48;
    private static final float MAP_SIZE = 40;

    private final float[] pos = new float[2];
    private final float[] velocity = new float[2];
    public final MapData mapData = new MapData(4269);

    public MapScreen(GameData gameData) {
        super(gameData);
    }

    @Override
    public void render() {
        if (super.data.getCurrentMenu() == -1) {
            if (Gdx.input.getX() < EDGE || Gdx.input.isKeyPressed(Input.Keys.LEFT)
                    || Gdx.input.isKeyPressed(Input.Keys.A)) {
                if (velocity[0] > -MIN_VELOCITY)
                    velocity[0] = -MIN_VELOCITY;
                velocity[0] -= ACCELERATION * Gdx.graphics.getDeltaTime();
            } else if (Gdx.input.getX() > Gdx.graphics.getWidth() - EDGE || Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                    || Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (velocity[0] < MIN_VELOCITY)
                    velocity[0] = MIN_VELOCITY;
                velocity[0] += ACCELERATION * Gdx.graphics.getDeltaTime();
            } else {
                velocity[0] = 0;
            }

            if (Gdx.input.getY() > Gdx.graphics.getHeight() - EDGE || Gdx.input.isKeyPressed(Input.Keys.DOWN)
                    || Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (velocity[1] > -MIN_VELOCITY)
                    velocity[1] = -MIN_VELOCITY;
                velocity[1] -= ACCELERATION * Gdx.graphics.getDeltaTime();
            } else if (Gdx.input.getY() < EDGE || Gdx.input.isKeyPressed(Input.Keys.UP)
                    || Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (velocity[1] < MIN_VELOCITY)
                    velocity[1] = MIN_VELOCITY;
                velocity[1] += ACCELERATION * Gdx.graphics.getDeltaTime();
            } else {
                velocity[1] = 0;
            }

            if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
                setMenu(1);
            }
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && super.data.getCurrentMenu() == 1) {
            setMenu(-1);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            setMenu((super.data.getCurrentMenu() != -1) ? -1 : 0);
        }

        pos[0] += velocity[0] * Gdx.graphics.getDeltaTime();

        pos[1] += velocity[1] * Gdx.graphics.getDeltaTime();

        pos[0] = Math.max(Math.min(pos[0], MAP_SIZE), -MAP_SIZE);

        pos[1] = Math.max(Math.min(pos[1], MAP_SIZE), -MAP_SIZE);

        super.data.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int x = -1; x <= Gdx.graphics.getWidth() / GRID + 1; x++) {
            for (int y = -1; y <= Gdx.graphics.getHeight() / GRID + 1; y++) {
                Color color = mapData.getColor(x + (int) pos[0], y + (int) pos[1]);
                super.data.shapeRenderer.rect((x - pos[0] % 1) * GRID, (y - pos[1] % 1) * GRID, GRID, GRID, color, color, color,
                        color);
            }
        }

        super.data.shapeRenderer.end();
    }

    @Override
    public String getDebugText() {
        String out = String.format("Pos: %.1f, %.1f", pos[0], pos[1]);
        return out;
    }

    private void setMenu(int menu) {
        super.data.setCurrentMenu(menu);

        if (menu == 1) {
            ContextMenuData data = ((AreaMenu) super.data.menus[menu]).contextMenuData;

            int[] gridPos = mouseToGrid();
            String name = String.format("Mineral #%d", mapData.getMineralAt(gridPos[0], gridPos[1]) + 1);

            data.clear();
            data.add(new ContextMenuData.SimpleSubSection(name));

            for (Map.Entry<SafeElement, Float> entry : mapData.getData(gridPos[0], gridPos[1]).entrySet()) {
                data.add(new ContextMenuData.SimpleSubSection(entry.toString()));
            }
        }

        if (menu != -1) {
            velocity[0] = 0;
            velocity[1] = 0;
        }
    }

    private int[] mouseToGrid() {
        return new int[]{
                (Gdx.input.getX() + (int) ((pos[0] * GRID) % GRID)) / GRID + (int) pos[0],
                (Gdx.graphics.getHeight() - Gdx.input.getY() + (int) ((pos[1] * GRID) % GRID)) / GRID + (int) pos[1]
        };
    }
}
