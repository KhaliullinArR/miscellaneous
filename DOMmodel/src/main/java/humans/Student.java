package humans;

public class Student extends Human {
    private String course, specialization;

    public Student(String name, String course, String specialization) {
        super(name);
        this.course = course;
        this.specialization = specialization;
    }

    public String getCourse() {
        return course;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String toString() {
        return "Голодный студент " + getName() + " " + course + "-го курса, обучающийся по специальности " + specialization;
    }
}