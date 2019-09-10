package searching.symboltables;

/**
 * 抽象符號表
 *
 * @author LuGew
 */
public abstract class AbstractSymbolTable<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    protected int size;

    @Override
    public boolean delete(Key key) {
        return put(key, null);
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean deleteMin() {
        return delete(min());
    }

    @Override
    public boolean deleteMax() {
        return delete(max());
    }

    @Override
    public int size(Key low, Key high) {
        if (high.compareTo(low) < 0) {
            return 0;
        } else if (contains(high)) {
            return rank(high) - rank(low) + 1;
        } else {
            return rank(high) - rank(low);
        }
    }


    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }
}
