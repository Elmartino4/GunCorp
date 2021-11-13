package github.elmartino4.guncorp;

public interface ConfigChangeCallback {
    <T> T getConfig(String key);

    void setConfig(String key, Object value);
}