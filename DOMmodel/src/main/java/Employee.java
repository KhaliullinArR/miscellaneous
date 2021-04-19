public class Employee {
    private String name;
    private String jod;

    public Employee(String name, String jod) {
        this.name = name;
        this.jod = jod;
    }

    public String getName() {
        return name;
    }

    public String getJod() {
        return jod;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", jod='" + jod + '\'' +
                '}';
    }
}
