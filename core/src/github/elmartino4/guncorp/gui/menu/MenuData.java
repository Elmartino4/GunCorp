package github.elmartino4.guncorp.gui.menu;

import org.json.JSONObject;

import java.util.Objects;

public class MenuData {
    public Redirect redirect;
    public JSONObject jsonObject;

    public static MenuData QUIT = new MenuData(Redirect.QUIT);
    public static MenuData PEDIA = new MenuData(Redirect.PEDIA);
    public static MenuData MY_CORP = new MenuData(Redirect.MY_CORP);
    public static MenuData MAP = new MenuData(Redirect.MAP);

    protected MenuData(Redirect redirect){
        this.redirect = redirect;
    }

    public MenuData(Redirect redirect, JSONObject jsonObject){
        this.redirect = redirect;
        this.jsonObject = jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuData menuData = (MenuData) o;
        return redirect == menuData.redirect;
    }

    @Override
    public int hashCode() {
        return Objects.hash(redirect);
    }

    public static enum Redirect {
        QUIT, PEDIA, MY_CORP, MAP
    }
}
