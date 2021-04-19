
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SAXExample {

    private static boolean isFound;
    private static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMlHandler xMlHandler = new XMlHandler();
        parser.parse(new File("src/main/resources/Employees"), xMlHandler);

        parser.parse(new File("src/main/resources/AdvancedEmployee"), new AdvancedXMLHandler());

        for (Employee employee : employees) {
            System.out.println(String.format("Имя сотрудника: %s, его должность: %s", employee.getName(), employee.getJob()));;
        }

        parser.parse(new File("src/main/resources/Root"), new SearchingXMlHandler("root"));

        if (!isFound) System.out.println("Not found.");

    }


    private static class SearchingXMlHandler extends DefaultHandler {
        private String element;
        private boolean isEntered;

        public SearchingXMlHandler(String element) {
            this.element = element;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if(isEntered){
                System.out.println(String.format("Найден элемент <%s>, его атрибуты:", qName));

                int length = attributes.getLength();
                for (int i = 0; i < length; i++) {
                    System.out.println(String.format("Имя атрибута: %s, его значение: %s", attributes.getQName(i), attributes.getValue(i)));
                }
            }

            if (qName.equals(element)) {
                isEntered = true;
                isFound = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals(element))
                isEntered = false;
        }
    }


    private static class AdvancedXMLHandler extends DefaultHandler {
        private String name, job, lastElementName;


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((name != null && !name.isEmpty()) && (job != null && !job.isEmpty())) {
                employees.add(new Employee(name, job));
                name = null;
                job = null;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("name")) name = information;
                if (lastElementName.equals("job")) job = information;
            }
        }
    }



    private static class XMlHandler extends DefaultHandler {
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("employee")) {
                String name = attributes.getValue("name");
                String job = attributes.getValue("job");
                employees.add(new Employee(name, job));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
            super.ignorableWhitespace(ch, start, length);
        }
    }

}
