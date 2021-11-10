package github.elmartino4.guncorp.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import github.elmartino4.guncorp.Menu;
import github.elmartino4.guncorp.ScreenAdapter;
import github.elmartino4.guncorp.menus.EscapeMenu;
import github.elmartino4.guncorp.menus.MenuData;

import java.util.Arrays;
import java.util.function.Consumer;

public class MapScreen extends ScreenAdapter {
    private static final float ACCELERATION = 8;
    private static final int EDGE = 60;
    private static final int GRID = 48;
    private final Menu[] MENUS;

    private int currentMenu = -1;
    private final int[] pos = new int[2];
    private final float[] velocity = new float[2];
    public final MapData mapData = new MapData(69420);
    public Consumer<MenuData> menuDataConsumer;

    public MapScreen(Consumer<MenuData> consumer){
        menuDataConsumer = consumer;
        MENUS = new Menu[]{
                new EscapeMenu(consumer)
        };
    }

    @Override
    public void create() {
        for (Menu menu : MENUS){
            menu.create();
        }
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        if(currentMenu == -1){
            if(Gdx.input.getX() < EDGE || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                velocity[0] -= Gdx.graphics.getDeltaTime() * ACCELERATION;
            }else if(Gdx.input.getX() > Gdx.graphics.getWidth() - EDGE || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                velocity[0] += Gdx.graphics.getDeltaTime() * ACCELERATION;
            }else{
                velocity[0] = 0;
            }

            if(Gdx.input.getY() < EDGE || Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                velocity[1] -= Gdx.graphics.getDeltaTime() * ACCELERATION;
            }else if(Gdx.input.getY() > Gdx.graphics.getHeight() - EDGE || Gdx.input.isKeyPressed(Input.Keys.UP)){
                velocity[1] += Gdx.graphics.getDeltaTime() * ACCELERATION;
            }else{
                velocity[1] = 0;
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            currentMenu = (currentMenu != -1) ? -1 : 0;


        pos[0] += velocity[0];
        pos[1] += velocity[1];

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (int x = 0; x <= Gdx.graphics.getWidth() / GRID + 1; x++) {
            for (int y = 0; y <= Gdx.graphics.getHeight() / GRID + 1; y++) {
                Color color = mapData.getColor(x + pos[0], y + pos[1]);
                shapeRenderer.rect(x * GRID, y * GRID, GRID, GRID, color, color, color, color);
            }
        }

        shapeRenderer.end();

        if(currentMenu != -1){
            MENUS[currentMenu].render(batch, shapeRenderer);
        }
    }

    @Override
    public String getDebugText() {
        String out = Gdx.input.isTouched() + "\nPos: " + Arrays.toString(pos);
        if(currentMenu != -1){
            out += "\n" + MENUS[currentMenu].getDebugText();
        }

        return out;
    }
}
