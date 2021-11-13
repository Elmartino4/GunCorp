package github.elmartino4.guncorp.map;

import github.elmartino4.guncorp.OpenSimplexNoise;

import java.util.*;

public class MineralData {
    public OpenSimplexNoise noise;
    public List<OpenSimplexNoise> elementDistributionList = new ArrayList<>();
    public List<SafeElement> elementList = new ArrayList<>();

    public MineralData(long noiseSeed, Random random) {
        noise = new OpenSimplexNoise(noiseSeed);
        for (int i = 0; i < random.nextFloat() * 4F + 2F; i++) {
            elementList.add(new SafeElement(random.nextInt(5) + 1, random.nextInt(5) + 1));
            elementDistributionList.add(new OpenSimplexNoise(noiseSeed * 10 + i));
        }
    }

    public Map<SafeElement, Float> getData(float x, float y) {
        Map<SafeElement, Float> out = new HashMap<>();

        float total = 0;

        for (OpenSimplexNoise elementNoise : elementDistributionList) {
            total += elementNoise.eval(x, y) + 1;
        }

        for (int i = 0; i < elementList.size(); i++) {
            out.put(elementList.get(i), (float) (elementDistributionList.get(i).eval(x, y) + 1) / total);
        }

        return out;
    }
}
