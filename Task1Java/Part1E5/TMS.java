class TaskNode {
    int taskId;
    String taskName;
    String status;
    TaskNode next;
    TaskNode(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this .taskName = taskName;
        this.status = status;
    }
    public void printTask() {
        System.out.print(this.taskId + " " + this.taskName + " " + this.status);
    }
}
public class TMS {
    TaskNode head;
    TaskNode tail;
    public void addTask(int taskId, String taskName, String status) {
        TaskNode newNode = new TaskNode(taskId, taskName, status);
        if(head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
    }
    public TaskNode searchNode(int taskId) {
        if(head == null) {
            return null;
        }
        TaskNode temp = head;
        while(temp != null) {
            if(temp.taskId == taskId) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
    public TaskNode deleteTask(int taskId) {
        if(head == null) {
            return null;
        }
        TaskNode temp = head;
        TaskNode prev = null;
        while(temp != null) {
            if(temp.taskId == taskId) {
                prev.next = temp.next;
                return temp;
            }
            prev = temp;
            temp = temp.next;
        }
        return null;
    }
    public void printTasks() {
        if(head == null) {
            System.out.println("Empty tasks");
        }
        TaskNode temp = head;
        while(temp.next != null) {
            temp.printTask();
            System.out.print("->");
            temp = temp.next;
        }
        temp.printTask();
        System.out.println();
    }
    public static void main(String[] args) {
        TMS tms = new TMS();
        tms.addTask(1, "Task1", "Incomp");
        tms.printTasks();
        tms.addTask(2, "Task2", "Comp");
        tms.addTask(4, "Task3", "Incomp");
        tms.addTask(7, "Task4", "Incomp");
        tms.addTask(10, "Task5", "Comp");
        tms.printTasks();
        TaskNode node1 = tms.searchNode(7);
        node1.printTask();
        System.out.println();
        TaskNode node2 = tms.deleteTask(7);
        node2.printTask();
        System.out.println();
        tms.printTasks();
    }
}