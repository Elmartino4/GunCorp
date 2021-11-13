package github.elmartino4.guncorp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ContextMenuData {
    public List<ContextSubSection> data = new ArrayList<>();
    private int width = 300;
    public int height = 40;

    public int[] getSize () {
        return new int[]{width, height * data.size() - 10};
    }

    public void add (ContextSubSection section) {
        data.add(section);
    }

    public void clear () {
        data = new ArrayList<>();
    }

    public void isOnButton () {

    }

    public void onClick(Consumer<MenuData> click){

    }

    public abstract static class ContextSubSection {
        public final String text;

        public ContextSubSection (String text) {
            this.text = text;
        }
    }

    public static class ButtonSubSection extends ContextSubSection {

        public ButtonSubSection(String text) {
            super(text);
        }
    }

    public static class SimpleSubSection extends ContextSubSection {

        public SimpleSubSection(String text) {
            super(text);
        }
    }
}

