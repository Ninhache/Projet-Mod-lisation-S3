package model.observers;

public interface Observer {

    void update(Observable o);

    void update(Observable o, Object arg);


}

