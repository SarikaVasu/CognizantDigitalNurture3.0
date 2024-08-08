class ThreadClass extends Thread{
    @Override
    public void run() {
        for(int i=0; i<=5; i++) {
            System.out.println(i+1);
            try {
                Thread.sleep(500); //500 millisec
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        }
    }
}
public class ThreadStateLogger {
    //instance
    public static void main(String[] args) {
        ThreadClass thread = new ThreadClass();
        System.out.println("Thread states: ");
        System.out.println("Before starting: " + thread.getState());
        thread.start();
        System.out.println("After starting: " + thread.getState());
        while(thread.isAlive()) {
            System.out.println("During execution: " + thread.getState());
            try {
                Thread.sleep(500);
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        }
        System.out.println("After completion: " + thread.getState());
    }
}
