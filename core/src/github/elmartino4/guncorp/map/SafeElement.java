package github.elmartino4.guncorp.map;

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
        return N + ", " + M;
    }
}
