package tools;

public interface Observer<T> {
    void updateState(T t);
}
