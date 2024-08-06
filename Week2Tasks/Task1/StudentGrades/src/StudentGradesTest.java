public class StudentGradesTest {
    public static void main(String[] args) {
        Student stud1 = new Student(1, "abc", 'A');
        Student stud2 = new Student(2, "abc", 'A');
        Student stud3 = new Student(3, "abc", 'A');
        Student stud4 = new Student(4, "abc", 'A');
        StudentGrades students = new StudentGrades();
        students.addStudent(stud1);
        students.addStudent(stud2);
        students.addStudent(stud3);
        students.addStudent(stud4);
        students.displayProducts();
        students.removeStudent(2);
        students.displayProducts();
        students.updateStudentGrade(3,'B');
        students.displayProducts();
    }
}
