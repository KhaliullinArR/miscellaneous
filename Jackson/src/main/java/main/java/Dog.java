package main.java;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

public class Dog {
    public  String name;
    @JsonDeserialize(as = ArrayList.class)
    public List<Dog> list;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
