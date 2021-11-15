package github.elmartino4.guncorp.map;

import com.badlogic.gdx.graphics.Color;

import java.util.*;

public class MapData {
    public final long SEED;
    public static final float MINERAL_SCALE = 16F;
    public static final float ELEMENT_SCALE = 64F;
    protected final List<MineralData> minerals = new ArrayList<>();
    protected final List<Color> mineralColours = new ArrayList<>();
    protected final Random random;

    public MapData(long seed) {
        SEED = seed;
        random = new Random(SEED);
        ElementStatistics.SEED = seed;

        for (int i = 0; i < random.nextFloat() * 6F + 4F; i++) {
            minerals.add(new MineralData(SEED * 10 + i, random));
            java.awt.Color colour = java.awt.Color.getHSBColor((random.nextFloat() * 0.18F + 0.955F) % 1F,
                    random.nextFloat() * 0.7F + 0.3F,
                    random.nextFloat() * 0.7F);
            mineralColours.add(new Color(colour.getRed() / 256F,
                    colour.getGreen() / 256F,
                    colour.getBlue() / 256F,
                    1));
        }
    }

    public Map<SafeElement, Float> getData(int x, int y) {
        return minerals.get(getMineralAt(x, y)).getData(x / ELEMENT_SCALE, y / ELEMENT_SCALE);
    }

    public Color getColor(int x, int y) {
        return mineralColours.get(getMineralAt(x, y));
    }

    public int getMineralAt(int x, int y) {
        int index = 0;
        double val = minerals.get(0).noise.eval(x / MINERAL_SCALE, y / MINERAL_SCALE);

        for (int i = 1; i < minerals.size(); i++) {
            double nextVal = minerals.get(i).noise.eval(x / MINERAL_SCALE, y / MINERAL_SCALE);
            if (nextVal > val) {
                index = i;
                val = nextVal;
            }
        }

        return index;
    }
}
