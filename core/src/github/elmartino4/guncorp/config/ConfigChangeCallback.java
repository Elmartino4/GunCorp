package github.elmartino4.guncorp.config;

public interface ConfigChangeCallback {
    <T> T getConfig(String key);

    void setConfig(String key, Object value);
}