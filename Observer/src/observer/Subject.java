package observer;



public class Subject {


    public static void main(String[] args) {
        Subject subject = new Subject(new Object());
    }

    Observers observers = new Observers();


    {
        observers.add(new EmptyObserver() {

            @Override
            public void objectCreated(Object obj) {
                System.out.println("Object created");
            }

            @Override
            public void objectModified(Object obj) {
                System.out.println("Object modified");
            }
        });
    }


    public Subject(Object field) {
        this.field = field;
        observers.notifyObjectCreated(this);
    }

    private Object field;

    public void setField(Object field) {
        this.field = field;
        observers.notifyObjectModified(this);
    }


}
