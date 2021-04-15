package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        int number  = myClass.getNumber();
        String name = null;
        System.out.println(number + name);
        try{

            Field field = myClass.getClass().getDeclaredField("name");
            field.setAccessible(true);
            name = (String) field.get(myClass);
            field.set(myClass, "new value");
        }catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
        System.out.println(number+name);
        printData(myClass);

        //Create an instance of MyClass class with Reflection API

        MyClass otherClass = null;
        try {
            Class clazz = Class.forName(MyClass.class.getName());
            otherClass = (MyClass)clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(otherClass);

        //Create an instance of MyClass(with arguments for constructor) class with Reflection API

        MyClassWithArgs myClassWithArgs = null;

        try {
            Class clasz = Class.forName(MyClassWithArgs.class.getName());
            Class[] params = {String.class, int.class};
            myClassWithArgs = (MyClassWithArgs) clasz.getConstructor(params).newInstance(1,"default2");

        } catch (ClassNotFoundException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(myClassWithArgs.getId());
        System.out.println(myClassWithArgs.getName());

    }

    public static void printData(Object myClass) {
        try {
            Method method = myClass.getClass().getDeclaredMethod("printData");
            method.setAccessible(true);
            method.invoke(myClass);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
