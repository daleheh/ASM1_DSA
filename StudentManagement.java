import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentManagement {
    private StudentStack studentStack;

    public StudentManagement() {
        studentStack = new StudentStack();
    }

    // Add a new student
    public void addStudent(String id, String name, double marks) {
        Student student = new Student(id, name, marks);
        studentStack.push(student);
    }

    // Edit a student
    public void editStudent(String id, String newName, double newMarks) {
        ArrayList<Student> students = new ArrayList<>();
        Student studentToEdit = null;

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                studentToEdit = new Student(id, newName, newMarks);
                students.add(studentToEdit);
            } else {
                students.add(student);
            }
        }

        if (studentToEdit != null) {
            System.out.println("Student updated: " + studentToEdit);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }

        // Push back all students into the stack
        for (int i = students.size() - 1; i >= 0; i--) {
            studentStack.push(students.get(i));
        }
    }

    // Delete a student by ID
    public void deleteStudent(String id) {
        ArrayList<Student> students = new ArrayList<>();
        boolean found = false;

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (!student.getId().equals(id)) {
                students.add(student);
            } else {
                found = true;
                System.out.println("Student deleted: " + student);
            }
        }

        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }

        // Push back all students into the stack
        for (int i = students.size() - 1; i >= 0; i--) {
            studentStack.push(students.get(i));
        }
    }

    // Search for a student by ID
    public Student searchStudent(String id) {
        ArrayList<Student> students = new ArrayList<>();
        Student foundStudent = null;

        while (!studentStack.isEmpty()) {
            Student student = studentStack.pop();
            if (student.getId().equals(id)) {
                foundStudent = student;
            }
            students.add(student);
        }

        // Push back all students into the stack
        for (int i = students.size() - 1; i >= 0; i--) {
            studentStack.push(students.get(i));
        }

        if (foundStudent == null) {
            System.out.println("Student with ID " + id + " not found.");
        }

        return foundStudent;
    }

    // Sort students by marks
    public void sortStudentsByMarks() {
        ArrayList<Student> students = new ArrayList<>();

        while (!studentStack.isEmpty()) {
            students.add(studentStack.pop());
        }

        Collections.sort(students, Comparator.comparingDouble(Student::getMarks));

        // Push back all students into the stack in sorted order
        for (int i = students.size() - 1; i >= 0; i--) {
            studentStack.push(students.get(i));
        }

        System.out.println("Students sorted by marks.");
    }

    // Display all students
    public void displayStudents() {
        studentStack.displayStack();
    }
}
