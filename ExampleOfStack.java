public class ExampleOfStack {
    public class Stack {
        private int[] stack;
        private int top;

        public Stack(int capacity) {
            stack = new int[capacity];
            top = -1;
        }

        public void push(int value) {
            if (top == stack.length - 1) {
                System.out.println("Stack is full!");
                return;
            }
            stack[++top] = value;
        }

        public int pop() {
            if (top == -1) {
                System.out.println("Stack is empty!");
                return -1;
            }
            return stack[top--];
        }

        public void display() {
            System.out.println("Stack contents:");
            for (int i = top; i >= 0; i--) {
                System.out.print(stack[i] + " ");
            }
            System.out.println();
        }
    }

    // Sử dụng Stack
    public class StackDemo {
        public void main(String[] args) {
            Stack stack = new Stack(5);

            stack.push(10);
            stack.push(20);
            stack.push(30);

            stack.display();

            stack.pop();
            stack.display();
        }
    }
}
