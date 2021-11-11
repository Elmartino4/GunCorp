package github.elmartino4.guncorp.map;

import github.elmartino4.speshanimals.OpenSimplexNoise;

public class ElementStatistics {
    public static long SEED;
    public static final float DIST = 1.8F;
    public final int M;
    public final int N;
    public final float coldReactionThreshold;
    public final float hotReactionThreshold;
    public final float boilingPoint;
    public final float meltingPoint;
    public final float decayRate;
    public final float preferredDensity;

    protected ElementStatistics(int M, int N, float coldReactionThreshold, float hotReactionThreshold,
                                float boilingPoint, float meltingPoint, float decayRate, float preferredDensity){
        this.M = M;
        this.N = N;
        this.coldReactionThreshold = coldReactionThreshold;
        this.hotReactionThreshold = hotReactionThreshold;
        this.boilingPoint = boilingPoint;
        this.meltingPoint = meltingPoint;
        this.decayRate = decayRate;
        this.preferredDensity = preferredDensity;
    }

    public static ElementStatistics fromElement(Element element){
        OpenSimplexNoise noise = new OpenSimplexNoise(SEED);

        int M = element.M;
        int N = element.N;
        float coldReactionThreshold = 21;
        float hotReactionThreshold = 21;
        float boilingPoint = 40F / (float)(noise.eval(M / 6F, N / 6F, 0 * DIST) + 1) - 20F;
        float meltingPoint = 40F / (float)(noise.eval(M / 6F, N / 6F, 0 * DIST) + 1) +
                40F / (float)(noise.eval(M / 6F, N / 6F, 1 * DIST) + 1) - 40F;
        float decayRate = -1;

        if(0.9 - 0.9 / (0.05 * M * N + 1) < noise.eval(M / 6F, N / 6F, 2 * DIST)){
            decayRate = (float)Math.pow(noise.eval(M / 6F, N / 6F, 3 * DIST), 4);
        }
        float preferredDensity = 2;

        return new ElementStatistics(M, N, coldReactionThreshold, hotReactionThreshold, boilingPoint, meltingPoint, decayRate, preferredDensity);
    }
}
