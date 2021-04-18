package main.java;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException
    {
        //создание объекта для сериализации в JSON
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 4;

        //писать результат сериализации будем во Writer(StringWriter)
        StringWriter writer = new StringWriter();

        //это объект Jackson, который выполняет сериализацию
        ObjectMapper mapper = new ObjectMapper();

        // сама сериализация: 1-куда, 2-что
        mapper.writeValue(writer, cat);

        //преобразовываем все записанное во StringWriter в строку
        String result = writer.toString();
        System.out.println(result);

        String jsonString = "{ \"name\":\"Murka\", \"age\":5, \"weight\":4}";
        StringReader reader = new StringReader(jsonString);

        ObjectMapper mapper1 = new ObjectMapper();

        Cat cat1 = mapper.readValue(reader, Cat.class);
        System.out.println(cat1);

        String jsonDog = "{\"name\":\"Petya\",\"list\":[{\"name\":\"Timka\"},{\"name\":\"Killer\"}]}";

        ObjectMapper objectMapper = new ObjectMapper();
        Dog dog = objectMapper.readValue(jsonDog, Dog.class);

        System.out.println(dog);

        Bike bike = new Bike();
        bike.name = "Hyyaik";
        bike.age = 54;
        bike.owner = "Batman";

        Car car = new Car();

        car.name = "car-hyur";
        car.age = 54;

        House house = new House();

        house.vehicles.add(bike);
        house.vehicles.add(car);

        StringWriter stringWriter = new StringWriter();
        ObjectMapper vehicleMapper = new ObjectMapper();

        vehicleMapper.writeValue(stringWriter, house);
        System.out.println(stringWriter.toString());




    }
}
