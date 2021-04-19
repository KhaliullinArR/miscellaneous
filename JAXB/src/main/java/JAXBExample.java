import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JAXBExample {
    public static void main(String[] args) throws JAXBException {
        Cat cat = new Cat();
        cat.name = "Murka";
        cat.age = 5;
        cat.weight = 4;

        /*StringWriter stringWriter = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(Cat.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(cat, stringWriter);
        System.out.println(stringWriter.toString());*/


       /* String xmldata = "<cat att = \"3\"><name>Kiril</name><size>50</size></cat>";
        StringReader stringReader = new StringReader(xmldata);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        Cat cat1 = (Cat) unmarshaller.unmarshal(stringReader);
        System.out.println(cat1);*/

        Zoo zoo = new Zoo();
        zoo.animals.add(cat);
        zoo.animals.add(new Cat());

        JAXBContext d = JAXBContext.newInstance(Cat.class, Zoo.class);
        Marshaller zooMarshaller = d.createMarshaller();

        StringWriter zooWriter = new StringWriter();
        zooMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        zooMarshaller.marshal(zoo, zooWriter);


        System.out.println(zooWriter.toString());
    }
}
