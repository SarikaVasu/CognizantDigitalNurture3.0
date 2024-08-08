class Task extends Thread {
    private final String taskname;
    private final Task dependentTask;
    public Task(String taskname, Task dependentTask) {
        this.taskname = taskname;
        this.dependentTask = dependentTask;
    }
    @Override
    public void run() {
        try {
            if(dependentTask != null) {
                System.out.println(taskname + ", waiting for: " + dependentTask.taskname + "to complete.");
                dependentTask.join();
            }
            System.out.println(taskname + "is strating");
            for(int i=0; i<5; i++) {
                System.out.println(taskname + " is processing: " + (i+1));
                Thread.sleep(500);
                Thread.yield();
            }
            System.out.println(taskname + "completed");
        } catch(InterruptedException err) {
            err.printStackTrace();
        }
    }
}
public class TaskScheduler {
    public static void main(String[] args) {
        Task task1 = new Task("T1", null);
        Task task2 = new Task("T2", task1);
        Task task3 = new Task("T3", task2);
        task1.start();
        task2.start();
        task3.start();
        try {
            task1.join();
            task2.join();
            task2.join();
        } catch(InterruptedException err) {
            err.printStackTrace();
        }
        System.out.println("Completed");
    }
}
