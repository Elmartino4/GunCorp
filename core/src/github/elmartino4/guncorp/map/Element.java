package github.elmartino4.guncorp.map;

public class Element {
    int N;
    int M;

    public Element(int N, int M){
        this.M = M;
        this.N = N;
    }

    public ElementStatistics getData(){
        return ElementStatistics.fromElement(this);
    }
}
