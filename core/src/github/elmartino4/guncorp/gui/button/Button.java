package github.elmartino4.guncorp.gui.button;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Button {
    private String text;
    public boolean isActive;
    private int[] size;
    private final Supplier<int[]> posSupplier;

    public Button(String text, Supplier<int[]> posSupplier) {
        this.text = text;
        isActive = true;
        this.posSupplier = posSupplier;
    }

    public int[] getSize() {
        if (size != null) {
            GlyphLayout layout = new GlyphLayout(ButtonRegistry.DEFAULT_FONT, text);
            size = new int[]{(int) layout.width, (int) layout.height};
        }


        return size;
    }

    public int[] getPos() {
        return posSupplier.get();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        size = null;
        this.text = text;
    }
}
