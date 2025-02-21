package domain;

public class Student {
    private String name;
    private String gradyear;
    private String title;
    private String percentage;
    private String coordinator;

    public Student(String name, String gradyear, String title, String percentage, String coordinator) {
        this.name = name;
        this.gradyear = gradyear;
        this.title = title;
        this.percentage = percentage;
        this.coordinator = coordinator;
    }



    public String getName() {
        return name;
    }

    public String getYear() {
        return gradyear;
    }

    public String getTitle() {
        return title;
    }



    @Override
    public String toString() {
        return name + " | " + gradyear + " | " + title + " | " + percentage + "% | " + coordinator;
    }
}
