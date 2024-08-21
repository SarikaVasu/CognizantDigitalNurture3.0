class Student {
    private String name;
    private int id;
    private String grade;
    public Student(String name, int id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getName() {
        return this.name;
    }
    public int getId() {
        return this.id;
    }
    public String getGrade() {
        return this.grade;
    }
}
class StudentView {
    public void displayStudentDetails(String name, int id, String grade) {
        System.out.println("Name: " + name + "\tId: " + id + "\tGrade: " + grade);
    }
}
class StudentController {
    private Student model;
    private StudentView view;
    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }
    public void updateStudent(String name, int id, String grade) {
        model.setName(name);
        model.setId(id);
        model.setGrade(grade);
    }
    public void displayStudent() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}
public class MVCPatternExample {
    public static void main(String[]args) {
        Student student1 = new Student("A", 123, "A");
        StudentView view = new StudentView();
        StudentController stdController = new StudentController(student1, view);
        stdController.displayStudent();
        stdController.updateStudent("AA", student1.getId(), student1.getGrade());
        stdController.displayStudent();
    }
}