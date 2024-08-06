import java.util.Map;

public class Student {
    private int id;
    private String name;
    private char grade;
    public Student(int id, String name, char grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    public int getId() {
        return this.id;
    }
    public void updateGrade(char grade) {
        this.grade = grade;
    }
    @Override
    public String toString() {
        return "StudentId: " + id + "\tName: " + name + "\tGrade: " + grade;
    }
}
