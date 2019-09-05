package tree.util;

/**
 * 樹接口
 *
 * @author LuGew
 */
public interface Tree<T extends Comparable<T>> extends Display {
    T get(T element);

    boolean put(T element);
}