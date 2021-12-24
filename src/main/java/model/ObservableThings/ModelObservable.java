package model.ObservableThings;

import java.util.ArrayList;
import java.util.List;

public class ModelObservable {

    private List<ModelObserver> observerList;

    public ModelObservable() {
        this.observerList = new ArrayList<>();
    }

    public boolean addObserver(ModelObserver o) {
        return this.observerList.add(o);
    }

    public boolean deleteObserver(ModelObserver o) {
        return this.observerList.remove(o);
    }


    public void notifyObservers() {
        for(ModelObserver o : this.observerList) {
            o.update();
        }
    }

}
