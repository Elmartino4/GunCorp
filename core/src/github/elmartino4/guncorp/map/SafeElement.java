package github.elmartino4.guncorp.map;

import github.elmartino4.guncorp.infogen.ProceduralElementName;

public class SafeElement {
    int N;
    int M;

    public SafeElement(int N, int M) {
        this.M = M;
        this.N = N;
    }

    public ElementStatistics getData(long seed) {
        return ElementStatistics.fromElement(seed, this);
    }

    @Override
    public String toString() {
        return "Element{" + N + "," + M + "}";
    }

    public String getName(ProceduralElementName nameGen) {
        try {
            return nameGen.getName(M, N).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fishy";
    }
}
