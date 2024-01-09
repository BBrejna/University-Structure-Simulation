package tools;

import uni.Course;

import java.io.Serializable;
import java.util.*;

public class MyHashSet<T> extends HashSet<T> implements Serializable {
    public MyHashSet() {
        super();
    }
    public MyHashSet(MyHashSet<T> newHashSet) {
        super(newHashSet);
    }
    public MyHashSet(HashSet<T> newHashSet) {
        super(newHashSet);
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        Iterator<T> iter = iterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }
        return iter.next();
    }

    public ArrayList<T> sort(Comparator<T> comparator) {
        ArrayList<T> tmp = new ArrayList<>(this);
        tmp.sort(comparator);
        return tmp;
    }

    public boolean contains(Object inElem) {
        for (T elem : this) {
            if (elem.hashCode() == inElem.hashCode()) {
                return true;
            }
            if (elem.equals(inElem)) {
                return true;
            }
        }
        return false;
    }
}
