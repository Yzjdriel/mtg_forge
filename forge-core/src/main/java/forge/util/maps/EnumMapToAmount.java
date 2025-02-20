package forge.util.maps;

import java.util.EnumMap;
import java.util.Map;


/** 
 * TODO: Write javadoc for this type.
 *
 */
public class EnumMapToAmount<T extends Enum<T>> extends EnumMap<T, Integer> implements MapToAmount<T> {
    private static final long serialVersionUID = -4749796492075359368L;

    public EnumMapToAmount(Class<T> keyType) {
        super(keyType);
    }

    public EnumMapToAmount(EnumMap<T, ? extends Integer> m) {
        super(m);
    }

    public EnumMapToAmount(Map<T, ? extends Integer> m) {
        super(m);
    }

    @Override
    public void add(T item) {
        add(item, 1);
    }

    @Override
    public void add(T item, int amount) {
        if (amount <= 0) { return; } // throw an exception maybe?
        Integer cur = get(item);
        int newVal = cur == null ? amount : amount + cur;
        put(item, newVal);
    }

    @Override
    public void addAll(Iterable<T> items) {
        for (T i : items) {
            add(i, 1);
        }
    }

    @Override
    public boolean subtract(T item) {
        return subtract(item, 1);
    }

    @Override
    public boolean subtract(T item, int amount) {
        Integer cur = get(item);
        if (cur == null) { return false; }
        int newVal = cur - amount;
        if (newVal > 0) {
            put(item, newVal);
        }
        else {
            remove(item);
        }
        return true;
    }

    @Override
    public void subtractAll(Iterable<T> items) {
        for (T i : items) {
            subtract(i);
        }
    }

    @Override
    public int countAll() {
        int c = 0;
        for (java.util.Map.Entry<T, Integer> kv : this.entrySet()) {
            c += kv.getValue();
        }
        return c;
    }

    @Override
    public int count(T item) {
        Integer cur = get(item);
        return cur == null ? 0 : cur;
    }
}
