package tools;

import java.util.ArrayList;

public interface Searcher<T, E> {
    public MyHashSet<T> search(MyHashSet<E> objects, String mode, String keyWord);
}
