package main.java;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=Cat.class, name="cat"),
        @JsonSubTypes.Type(value=Dog.class, name="dog")
})
 class Vehicle {
    public String name;
}

class House{
    public ArrayList<Vehicle> vehicles = new ArrayList<>();
}

class Bike  extends Vehicle{
    public int age;
    public String owner;
}

class Car  extends  Vehicle{
    public int age;
}