
import humans.Human;
import humans.Member;
import humans.Professor;
import humans.Student;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class DOMExample {

    private static ArrayList<Employee> arrayList = new ArrayList<>();

    private static ArrayList<Human> humans = new ArrayList<>();

    private static final String PROFESSOR = "professor";
    private static final String MEMBER = "member";
    private static final String STUDENT = "student";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document information = documentBuilder.parse(new File("src/main/resources/Employees"));

        NodeList employees = information.getElementsByTagName("employee");

        for (int i = 0; i < employees.getLength(); i++) {
            Node employee = employees.item(i);

            NamedNodeMap namedNodeMap = employee.getAttributes();

            arrayList.add(new Employee(namedNodeMap.getNamedItem("name").getNodeValue(), namedNodeMap.getNamedItem("job").getNodeValue()));

        }
        arrayList.forEach(System.out::println);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Document otherDocument = documentBuilder.parse(new File("src/main/resources/Sql"));

        String element = reader.readLine();

        NodeList matchedElements = otherDocument.getElementsByTagName(element);

        if (matchedElements.getLength() == 0) {
            System.out.println("?????? " + element + " ???? ?????? ???????????? ?? ??????????.");
        } else {
            Node foundedElement = matchedElements.item(0);

            System.out.println("?????????????? ?????? ????????????!");


            if (foundedElement.hasChildNodes())
                printInfoAboutAllChildNodes(foundedElement.getChildNodes());
        }


        Document humansInfo = documentBuilder.parse(new File("src/main/resources/Students"));

        collectInfo(humansInfo, PROFESSOR);
        collectInfo(humansInfo, STUDENT);
        collectInfo(humansInfo, MEMBER);

        humans.forEach(System.out::println);

    }

    private static void collectInfo(Document document, final String element) {
        NodeList nodeList = document.getElementsByTagName(element);

        for (int i = 0; i < nodeList.getLength(); i++) {
            NamedNodeMap attributes = nodeList.item(i).getAttributes();

            String name = attributes.getNamedItem("name").getNodeValue();

            switch (element) {
                case PROFESSOR: {
                    String experience = attributes.getNamedItem("experience").getNodeValue();
                    String discipline = attributes.getNamedItem("discipline").getNodeValue();

                    humans.add(new Professor(name, experience, discipline));
                } break;
                case STUDENT: {
                    String course = attributes.getNamedItem("course").getNodeValue();
                    String specialization = attributes.getNamedItem("specialization").getNodeValue();

                    humans.add(new Student(name, course, specialization));
                } break;
                case MEMBER: {
                    String position = attributes.getNamedItem("position").getNodeValue();

                    humans.add(new Member(name, position));
                } break;
            }

        }
    }

    private static void printInfoAboutAllChildNodes(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);

            // ?? ?????????????????? ???????? ?????? ???????? ?????????? - ???????????? ???????????????? ?????? ?????????????????? ????????????????????. ???????????? ?????????? ?????????????????????? ?????? ???????????????? ????????????????.
            if (node.getNodeType() == Node.TEXT_NODE) {
                // ???????????????????? ????????????????????, ?????? ?????? ?????????????? ?? ???????????????? ?????????????? ?????? ???? ??????????. ?????? ???? ????????????????????.
                String textInformation = node.getNodeValue().replace("\n", "").trim();

                if (!textInformation.isEmpty())
                    System.out.println("???????????? ???????????????? ???????????? ??????????: " + node.getNodeValue());
            }
            // ???????? ?????? ???? ??????????, ?? ??????????????, ???? ???????????????????????? ?????? ?????? ??????????????.
            else {
                System.out.println("???????????? ??????????????: " + node.getNodeName() + ", ?????? ????????????????:");

                // ?????????????????? ??????????????????
                NamedNodeMap attributes = node.getAttributes();

                // ?????????? ???????????????????? ?????? ?????? ????????????????
                for (int k = 0; k < attributes.getLength(); k++)
                    System.out.println("?????? ????????????????: " + attributes.item(k).getNodeName() + ", ?????? ????????????????: " + attributes.item(k).getNodeValue());
            }

            // ???????? ?? ?????????????? ???????????????? ?????? ???????????????? ????????, ???? ?????????????? ?????? ???????????????????? ?????? ?????? ?????? ????????.
            if (node.hasChildNodes())
                printInfoAboutAllChildNodes(node.getChildNodes());
        }
    }
}