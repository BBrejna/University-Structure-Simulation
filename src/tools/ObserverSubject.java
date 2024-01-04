package tools;

import uni.Person;
import uni.Student;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class ObserverSubject<T> implements Serializable {
    protected ArrayList<Observer<T>> observers = new ArrayList<>();

    public void notifyObservers(T t) {
        ArrayList<Observer<T>> tmpObservers = new ArrayList<>(observers);
        for (Observer<T> observer : tmpObservers) {
            for (Person person : ArrayListsHolder.getInstance().getPeople()) {
                if (person instanceof Student && observer.equals(person)) {
                    Observer<T> tmpObserver = (Observer<T>) person;
                    tmpObserver.updateState(t);
                }
            }
        }
    }

    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
    }

}
