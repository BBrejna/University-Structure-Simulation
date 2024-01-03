package tools;

import java.util.ArrayList;

public abstract class ObserverSubject<T> {
    protected ArrayList<Observer<T>> observers = new ArrayList<>();

    public void notifyObservers(T t) {
        ArrayList<Observer<T>> tmpObservers = new ArrayList<>(observers);
        for (Observer<T> observer : tmpObservers) observer.updateState(t);
    }

    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
    }

}
