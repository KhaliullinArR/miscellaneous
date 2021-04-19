package humans;

public class Professor extends Human {
    private String experience, discipline;

    public Professor(String name, String experience, String discipline) {
        super(name);
        this.experience = experience;
        this.discipline = discipline;
    }

    public String getExperience() {
        return experience;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String toString() {
        return "Профессор " + getName() + ", обладающий опытом: \"" + experience + "\", выкладает дисциплину " + discipline;
    }
}