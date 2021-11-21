package github.elmartino4.guncorp.save;

import java.io.Serializable;

public class SNBTElement implements Serializable {
    Object element;

    public SNBTElement(Object element) {
        this.element = element;
    }

    public <T> T getValue() {
        return (T)element;
    }
}
