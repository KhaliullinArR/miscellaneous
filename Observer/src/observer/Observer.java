package observer;

import java.util.ArrayList;
import java.util.Iterator;

public interface Observer {

    void objectCreated(Object obj);
    void objectModified(Object obj);
}

class EmptyObserver implements Observer {
    @Override
    public void objectCreated(Object obj) {

    }

    @Override
    public void objectModified(Object obj) {

    }
}
class Observers<T extends Observer> extends ArrayList<T> {
    public void notifyObjectCreated(Object obj) {
        for (T t : this) {
            t.objectCreated(obj);
        }
    }

    public void notifyObjectModified(Object obj) {
        for (T t : this) {
            t.objectModified(obj);
        }
    }

}

