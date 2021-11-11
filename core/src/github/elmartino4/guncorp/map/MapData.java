package github.elmartino4.guncorp.map;

import com.badlogic.gdx.graphics.Color;

import java.util.*;

import github.elmartino4.speshanimals.OpenSimplexNoise;

public class MapData {
    public final long SEED;
    public static final float SCALE = 8F;
    public final List<OpenSimplexNoise> minerals = new ArrayList<>();
    public final List<Color> mineralColours = new ArrayList<>();
    public final Random random;

    public MapData(long seed){
        SEED = seed;
        random = new Random(SEED);
        ElementStatistics.SEED = seed;

        for (int i = 0; i < random.nextFloat() * 6F + 4F; i++) {
            minerals.add(new OpenSimplexNoise(SEED * 10 + i));
            mineralColours.add(new Color(random.nextFloat() * 0.5F, random.nextFloat() * 0.4F , 0, 1.0F));
        }
    }

    public Map<Element, Float> getData(){
        return new HashMap<>();
    }

    public Color getColor(int x, int y){
        int index = 0;
        double val = minerals.get(0).eval(x / SCALE, y / SCALE);

        for (int i = 1; i < minerals.size(); i++) {
            double nextVal = minerals.get(i).eval(x / SCALE, y / SCALE);
            if (nextVal > val){
                index = i;
                val = nextVal;
            }
        }

        return mineralColours.get(index);
    }
}
