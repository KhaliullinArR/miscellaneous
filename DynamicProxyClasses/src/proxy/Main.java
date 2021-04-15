package proxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Man man = new Man("Вася", 30, "Санкт-Петербург", "Россия");

        ClassLoader classLoader = man.getClass().getClassLoader();

        Class[] interfaces = man.getClass().getInterfaces();

        Person person = (Person) Proxy.newProxyInstance(classLoader, interfaces, new PersonInvocationHandler(man));

        person.introduce(man.getName());

    }
}
