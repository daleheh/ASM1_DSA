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

     //MergeSort
    public void mergeSortStudentsByMarks() {
        ArrayList<Student> students = new ArrayList<>();

        // Lấy tất cả sinh viên từ stack sang danh sách
        while (!studentStack.isEmpty()) {
            students.add(studentStack.pop());
        }

        // Sắp xếp danh sách bằng Merge Sort
        students = mergeSort(students);

        // Đẩy lại sinh viên vào stack
        for (int i = students.size() - 1; i >= 0; i--) {
            studentStack.push(students.get(i));
        }
    }

    private ArrayList<Student> mergeSort(ArrayList<Student> students) {
        if (students.size() <= 1) {
            return students; // Nếu chỉ có 1 phần tử, danh sách đã được sắp xếp
        }

        // Chia danh sách thành hai nửa
        int mid = students.size() / 2;
        ArrayList<Student> left = new ArrayList<>(students.subList(0, mid));
        ArrayList<Student> right = new ArrayList<>(students.subList(mid, students.size()));

        // Gọi đệ quy để sắp xếp từng nửa
        left = mergeSort(left);
        right = mergeSort(right);

        // Trộn hai danh sách đã sắp xếp
        return merge(left, right);
    }

    private ArrayList<Student> merge(ArrayList<Student> left, ArrayList<Student> right) {
        ArrayList<Student> merged = new ArrayList<>();
        int i = 0, j = 0;

        // Trộn hai danh sách dựa trên điểm số
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getMarks() <= right.get(j).getMarks()) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }

        // Thêm các phần tử còn lại từ cả hai danh sách
        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }

        return merged;
    }

    //quicksort


    private void quickSort(ArrayList<Student> students, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(students, low, high);
            quickSort(students, low, pivotIndex - 1);
            quickSort(students, pivotIndex + 1, high);
        }
    }

    private int partition(ArrayList<Student> students, int low, int high) {
        double pivot = students.get(high).getMarks();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (students.get(j).getMarks() < pivot) {
                i++;
                // Swap
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }

        // Swap pivot
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1;
    }

    public void quickSortStudentsByMarks() {
        ArrayList<Student> students = new ArrayList<>();

        // Pop all students from stack into a list
        while (!studentStack.isEmpty()) {
            students.add(studentStack.pop());
        }

        // Quick Sort
        quickSort(students, 0, students.size() - 1);

        // Push back students into the stack
        for (int i = students.size() - 1; i >= 0; i--) {
            studentStack.push(students.get(i));
        }
    }

    // Display all students
    public void displayStudents() {
        ArrayList<Student> students = new ArrayList<>();

        // Lấy toàn bộ dữ liệu từ stack sang danh sách
        while (!studentStack.isEmpty()) {
            students.add(studentStack.pop());
        }

        // Đẩy lại dữ liệu vào stack
        for (int i = students.size() - 1; i >= 0; i--) {
            studentStack.push(students.get(i));
        }

    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();

        while (!studentStack.isEmpty()) {
            students.add(studentStack.pop());
        }

        for (int i = students.size() - 1; i >= 0; i--) {
            studentStack.push(students.get(i));
        }

        return students;
    }
}

