import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class StudentGrades {
    private LinkedHashMap<Integer, Student> students = new LinkedHashMap<>();
    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }
    public Student removeStudent(int studentId) {
        if(students.containsKey(studentId)) {
            return students.remove(studentId);
        }
        return null;
    }
    public void updateStudentGrade(int studentId, char newGrade) {
        if(students.containsKey(studentId)) {
            students.get(studentId).updateGrade(newGrade);
        }
    }
    public void displayProducts() {
        if(!students.isEmpty()) {
            for(Map.Entry<Integer, Student> studentEntry : students.entrySet()) {
                System.out.println(studentEntry.getValue());
            }
        }
    }
}
