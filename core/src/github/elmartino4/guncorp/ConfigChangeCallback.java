package github.elmartino4.guncorp;

public interface ConfigChangeCallback {
    Object getConfig(String key);

    void setConfig(String key, Object value);
}