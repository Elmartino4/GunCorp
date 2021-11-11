package github.elmartino4.guncorp.map;

import com.badlogic.gdx.graphics.Color;

import java.util.*;

import github.elmartino4.guncorp.OpenSimplexNoise;

public class MapData {
    public final long SEED;
    public static final float SCALE = 8F;
    protected final List<OpenSimplexNoise> minerals = new ArrayList<>();
    protected final List<Color> mineralColours = new ArrayList<>();
    protected final Random random;

    public MapData (long seed) {
        SEED = seed;
        random = new Random(SEED);
        ElementStatistics.SEED = seed;

        for (int i = 0; i < random.nextFloat() * 6F + 4F; i++) {
            minerals.add(new OpenSimplexNoise(SEED * 10 + i));
            java.awt.Color colour = java.awt.Color.getHSBColor((random.nextFloat() * 0.18F + 0.955F) % 1F,
                    random.nextFloat() * 0.7F + 0.3F,
                    random.nextFloat() * 0.7F);
            mineralColours.add(new Color(colour.getRed() / 256F,
                    colour.getGreen() / 256F,
                    colour.getBlue() / 256F,
                    1));
        }
    }

    public Map<Element, Float> getData(){
        return new HashMap<>();
    }

    public Color getColor (int x, int y) {
        return mineralColours.get(getMineralAt(x, y));
    }

    protected int getMineralAt (int x, int y) {
        int index = 0;
        double val = minerals.get(0).eval(x / SCALE, y / SCALE);

        for (int i = 1; i < minerals.size(); i++) {
            double nextVal = minerals.get(i).eval(x / SCALE, y / SCALE);
            if (nextVal > val){
                index = i;
                val = nextVal;
            }
        }

        return index;
    }
}
