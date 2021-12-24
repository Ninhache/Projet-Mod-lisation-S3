package model.ObservableThings;

public interface ModelObserver {

    void update();

    void update(ModelObservable o, Object arg);


}

