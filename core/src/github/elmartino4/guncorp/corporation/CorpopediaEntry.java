package github.elmartino4.guncorp.corporation;

public class CorpopediaEntry {
    long entryId;
    public String innerMD;
    public String title;

    public CorpopediaEntry(long entryId) {
        this.entryId = entryId;
    }

    public void loadValues() {
        innerMD = "abc\ndef\nghi";
        title = "Corp" + entryId;
    }

    public static CorpopediaEntry search(String query){
        return new CorpopediaEntry(0);
    }
}
