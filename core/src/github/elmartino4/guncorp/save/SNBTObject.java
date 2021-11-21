package github.elmartino4.guncorp.save;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SNBTObject implements Serializable {
    private final Map<Long, SNBTElement> map = new HashMap<>();
    private Map<String, Long> keys = new HashMap<>();
    long latestKey = Long.MIN_VALUE;

    public SNBTObject() {

    }

    protected SNBTObject(Map<String, Long> keys) {
        this.keys = keys;
    }

    public void put(String key, Object value) {
        if (!keys.containsKey(key)) keys.put(key, ++latestKey);
        long keyIndex = keys.get(key);

        map.put(keyIndex, new SNBTElement(value));
    }

    public boolean contains(String key) {
        if (!keys.containsKey(key)) return false;

        long keyIndex = keys.get(key);

        return map.containsKey(keyIndex);
    }

    public <T> T get(String key) {
        if (!keys.containsKey(key)) {
            throw new RuntimeException("Key Isn't Indexed");
        }

        long keyIndex = keys.get(key);

        if (!map.containsKey(keyIndex)) {
            throw new RuntimeException("Key Isn't Found");
        }

        return (T) map.get(keyIndex);
    }

    public SNBTObject putSubSNBTObject(String key) {
        if (!keys.containsKey(key)) keys.put(key, ++latestKey);
        long keyIndex = keys.get(key);

        return map.put(keyIndex, new SNBTElement(new SNBTObject(keys))).getValue();
    }

    public SNBTObject getSubSNBTObject(String key) {
        if (!keys.containsKey(key)) {
            throw new RuntimeException("Key Isn't Indexed");
        }

        long keyIndex = keys.get(key);

        if (!map.containsKey(keyIndex)) {
            throw new RuntimeException("Key Isn't Found");
        }

        SNBTElement element = map.get(keyIndex);

        if (!(element.getValue() instanceof SNBTObject)) {
            throw new RuntimeException("Key Doesn't Point to SNBTObject");
        }

        return element.getValue();
    }
}
