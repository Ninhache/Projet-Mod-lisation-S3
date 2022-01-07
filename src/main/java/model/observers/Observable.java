package model.observers;

import java.util.ArrayList;
import java.util.List;

public class Observable {

    private List<Observer> observerList;

    public Observable() {
        this.observerList = new ArrayList<>();
    }

    public boolean addObserver(Observer o) {
        return this.observerList.add(o);
    }

    public boolean deleteObserver(Observer o) {
        return this.observerList.remove(o);
    }


    public void notifyObservers() {
        for(Observer o : this.observerList) {
            o.update(this);
        }
    }
    public void notifyObservers(Object data) {
        for(Observer o : this.observerList) {
            o.update(this, data);
        }
    }

}
