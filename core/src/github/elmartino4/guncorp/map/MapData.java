package github.elmartino4.guncorp.map;

import com.badlogic.gdx.graphics.Color;

public class MapData {
    public final long SEED;

    public MapData(long seed){
        SEED = seed;
    }

    public Color getColor(int x, int y){
        return new Color((x / 100F) % 1, (y / 100F) % 1, 1, 1);
    }
}
