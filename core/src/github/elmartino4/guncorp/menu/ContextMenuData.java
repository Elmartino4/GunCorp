package github.elmartino4.guncorp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ContextMenuData {
    private List<ContextSubSection> Data = new ArrayList<>();
    private int width = 100;
    private int height = 20;

    public int[] getSize () {
        return new int[]{width, height * Data.size() + 20};
    }

    public void add (ContextSubSection section) {
        Data.add(section);
    }

    public void isOnButton () {

    }

    public void onClick(Consumer<MenuData> click){

    }

    public abstract static class ContextSubSection {

    }

    public static class ButtonSubSection extends ContextSubSection {

    }

    public static class SimpleSubSection extends ContextSubSection {

    }
}

