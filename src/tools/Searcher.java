package tools;

import java.util.ArrayList;

public interface Searcher<T, E> {
    public ArrayList<T> search(ArrayList<E> objects, String mode, String keyWord);
}
