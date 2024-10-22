public class StudentStack {
    private Node top;

    public StudentStack() {
        top = null;
    }

    // Push a student onto the stack
    public void push(Student student) {
        Node newNode = new Node(student);
        newNode.next = top;
        top = newNode;
        System.out.println("Student pushed: " + student);
    }

    // Pop a student from the stack
    public Student pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        } else {
            Student poppedStudent = top.data;
            top = top.next;
            return poppedStudent;
        }
    }

    // Peek at the top element without removing it
    public Student peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        } else {
            return top.data;
        }
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Display all students in the stack
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            Node currentNode = top;
            while (currentNode != null) {
                System.out.println(currentNode.data);
                currentNode = currentNode.next;
            }
        }
    }
}
