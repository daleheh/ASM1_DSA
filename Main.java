public class Main {
    public static void main(String[] args) {
        StudentManagement manager = new StudentManagement();

        // Add students
        manager.addStudent("BH01", "Tien Dat", 8.5);
        manager.addStudent("BH02", "Ngoc Bich", 6.0);
        manager.addStudent("BH03", "Minh Hoang", 4.0);
        manager.addStudent("BH04", "Bao Duong", 9.5);

        // Display all students
        System.out.println("All Students:");
        manager.displayStudents();

        // Search for a student
        System.out.println("Searching for BH02:");
        Student student = manager.searchStudent("BH02");
        if (student != null) {
            System.out.println(student);
        }

        // Edit student details
        System.out.println("Editing student BH02:");
        manager.editStudent("BH02", "Ngoc Bich", 9.2);

        // Delete a student
        System.out.println("Deleting student BH03:");
        manager.deleteStudent("BH03");

        // Sort students by marks
        System.out.println("Sorting students by marks:");
        manager.sortStudentsByMarks();

        // Display all students again
        System.out.println("All Students (After Sorting):");
        manager.displayStudents();
    }
}

