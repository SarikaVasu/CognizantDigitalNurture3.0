import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class DataQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;
    public DataQueue(int capacity) {
        this.capacity = capacity;
    }
    public synchronized void add(int data) throws InterruptedException {
        while(queue.size() == capacity) {
            wait();
        }
        queue.add(data);
        System.out.println("Produced: " + data);
        notifyAll();
    }
    public synchronized int remove() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int data = queue.remove();
        System.out.println("Consumed: " + data);
        notifyAll();
        return data;
    }
}
class Producer extends Thread {
    private final DataQueue queue;
    private final Random random = new Random();
    public  Producer(DataQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while(true) {
                int data = random.nextInt(100);
                queue.add(data);
                Thread.sleep(500);
            }
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
    }
}
class Consumer extends Thread {
    private final DataQueue queue;
    public  Consumer(DataQueue queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while(true) {
                queue.remove();
                Thread.sleep(500);
            }
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
    }
}
public class ProducerConsumer {
    public static void main(String[] args) {
        DataQueue queue = new DataQueue(5);
        Thread producer1 = new Producer(queue);
        Thread producer2 = new Producer(queue);
        Thread consumer1 = new Consumer(queue);
        Thread consumer2 = new Consumer(queue);
        producer1.start();;
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}
