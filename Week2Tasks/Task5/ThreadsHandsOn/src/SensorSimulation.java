import java.util.Random;

class Sensor extends Thread {
    private final Random random = new Random();
    private final String name;
    public Sensor(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        for(int i=0; i<5; i++) {
            int data = random.nextInt(100);
            System.out.println(name + " data: " + data);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        }
    }
}
public class SensorSimulation {
    public static void main(String[] args) {
        Sensor sensor1 = new Sensor("S1");
        Sensor sensor2 = new Sensor("S2");
        Sensor sensor3 = new Sensor("S3");
        sensor1.start();;
        sensor2.start();
        sensor3.start();
        try {
            sensor1.join();
            sensor2.join();
            sensor3.join();
        } catch (InterruptedException err) {
            err.printStackTrace();
        }
        System.out.println("Completed");
    }
}
