import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        StudentManagement manager = new StudentManagement();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n==== Student Management System ====");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Quick Sort Students by Marks");
            System.out.println("6. Merge Sort Students by Marks");
            System.out.println("7. Compare Sorting Algorithms");
            System.out.println("8. Display All Students");
            System.out.println("9. Generate Random Students");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = scanner.nextDouble();
                    manager.addStudent(id, name, marks);
                    System.out.println("Student added!");
                    break;
                case 2:
                    System.out.print("Enter ID to edit: ");
                    String editId = scanner.nextLine();
                    System.out.print("Enter new Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new Marks: ");
                    double newMarks = scanner.nextDouble();
                    manager.editStudent(editId, newName, newMarks);
                    break;
                case 3:
                    System.out.print("Enter ID to delete: ");
                    String deleteId = scanner.nextLine();
                    manager.deleteStudent(deleteId);
                    break;
                case 4:
                    System.out.print("Enter ID to search: ");
                    String searchId = scanner.nextLine();
                    Student student = manager.searchStudent(searchId);
                    if (student != null) {
                        System.out.println("Found: " + student);
                    }
                    break;
                case 5:
                    manager.quickSortStudentsByMarks();
                    System.out.println("Students sorted using Quick Sort.");
                    break;
                case 6:
                    manager.mergeSortStudentsByMarks();
                    System.out.println("Students sorted using Merge Sort.");
                    break;
                case 7:
                    compareSortingAlgorithms(manager);
                    break;
                case 8:
                    manager.displayStudents();
                    break;
                case 9:
                    System.out.print("Enter number of students to generate: ");
                    int count = scanner.nextInt();
                    generateRandomStudents(manager, count);
                    System.out.println(count + " random students generated!");
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void generateRandomStudents(StudentManagement manager, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String id = "ID" + String.format("%04d", i + 1); // ID0001, ID0002, ...
            String name = "Student" + (i + 1);
            double marks = Math.round((1.0 + random.nextDouble() * 9.0) * 100.0) / 100.0; // Random marks between 1.0 and 10.0
            manager.addStudent(id, name, marks);
        }
    }

    private static void compareSortingAlgorithms(StudentManagement manager) {
        System.out.println("\nComparing Quick Sort and Merge Sort:");

        // Clone original student list for testing both algorithms
        StudentManagement quickSortManager = cloneManager(manager);
        StudentManagement mergeSortManager = cloneManager(manager);

        // Test Quick Sort
        long quickStart = System.nanoTime();
        quickSortManager.quickSortStudentsByMarks();
        long quickEnd = System.nanoTime();
        long quickTime = quickEnd - quickStart;

        // Test Merge Sort
        long mergeStart = System.nanoTime();
        mergeSortManager.mergeSortStudentsByMarks();
        long mergeEnd = System.nanoTime();
        long mergeTime = mergeEnd - mergeStart;

        // Print results
        System.out.println("Quick Sort execution time: " + quickTime + " nanoseconds");
        System.out.println("Merge Sort execution time: " + mergeTime + " nanoseconds");

        // Determine and print which algorithm is faster
        if (quickTime < mergeTime) {
            System.out.println("Quick Sort is faster by " + (mergeTime - quickTime) + " nanoseconds.");
        } else if (quickTime > mergeTime) {
            System.out.println("Merge Sort is faster by " + (quickTime - mergeTime) + " nanoseconds.");
        } else {
            System.out.println("Both algorithms have the same execution time.");
        }
    }



    private static StudentManagement cloneManager(StudentManagement manager) {
        StudentManagement clone = new StudentManagement();
        for (Student student : manager.getAllStudents()) {
            clone.addStudent(student.getId(), student.getName(), student.getMarks());
        }
        return clone;
    }
}
