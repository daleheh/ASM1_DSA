public class ExampleOfQueue {
    public class Queue {
        private int[] queue;
        private int front, rear, size;

        public Queue(int capacity) {
            queue = new int[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }

        public void enqueue(int value) {
            if (size == queue.length) {
                System.out.println("Queue is full!");
                return;
            }
            rear = (rear + 1) % queue.length;
            queue[rear] = value;
            size++;
        }

        public int dequeue() {
            if (size == 0) {
                System.out.println("Queue is empty!");
                return -1;
            }
            int value = queue[front];
            front = (front + 1) % queue.length;
            size--;
            return value;
        }

        public void display() {
            System.out.println("Queue contents:");
            for (int i = 0; i < size; i++) {
                System.out.print(queue[(front + i) % queue.length] + " ");
            }
            System.out.println();
        }
    }

    // Sử dụng Queue
    public class QueueDemo {
        public void main(String[] args) {
            Queue queue = new Queue(5);

            queue.enqueue(10);
            queue.enqueue(20);
            queue.enqueue(30);

            queue.display();

            queue.dequeue();
            queue.display();
        }
    }


}
