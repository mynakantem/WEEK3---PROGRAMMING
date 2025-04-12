import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    String priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, String priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }

    @Override
    public String toString() {
        return "TaskID: " + taskId + ", Name: " + taskName +
                ", Priority: " + priority + ", Due: " + dueDate;
    }
}

class CircularTaskScheduler {
    private Task head = null;
    private Task tail = null;
    private Task current = null;

    public void addAtBeginning(int id, String name, String priority, String date) {
        Task newTask = new Task(id, name, priority, date);
        if (head == null) {
            head = tail = current = newTask;
            tail.next = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }

    public void addAtEnd(int id, String name, String priority, String date) {
        Task newTask = new Task(id, name, priority, date);
        if (head == null) {
            head = tail = current = newTask;
            tail.next = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }
    }

    public void addAtPosition(int pos, int id, String name, String priority, String date) {
        if (pos == 0) {
            addAtBeginning(id, name, priority, date);
            return;
        }

        Task newTask = new Task(id, name, priority, date);
        Task temp = head;
        int count = 0;

        while (count < pos - 1 && temp.next != head) {
            temp = temp.next;
            count++;
        }

        newTask.next = temp.next;
        temp.next = newTask;
        if (temp == tail) {
            tail = newTask;
        }
    }

    public void removeById(int id) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        Task temp = head, prev = tail;

        do {
            if (temp.taskId == id) {
                if (temp == head && temp == tail) {
                    head = tail = current = null;
                } else {
                    if (temp == head) head = head.next;
                    if (temp == tail) tail = prev;
                    if (temp == current) current = temp.next;

                    prev.next = temp.next;
                    tail.next = head;
                }
                System.out.println("Task removed.");
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Task not found.");
    }

    public void viewAndMoveToNext() {
        if (current == null) {
            System.out.println("No tasks in the list.");
            return;
        }
        System.out.println("Current Task:\n" + current);
        current = current.next;
    }

    public void displayTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task temp = head;
        System.out.println("All Tasks:");
        do {
            System.out.println(temp);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(String priority) {
        if (head == null) {
            System.out.println("No tasks to search.");
            return;
        }
        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority.equalsIgnoreCase(priority)) {
                System.out.println(temp);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No tasks with priority: " + priority);
        }
    }
}

class TaskSchedulerMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularTaskScheduler scheduler = new CircularTaskScheduler();
        int choice;

        do {
            System.out.println("\n--- Task Scheduler ---");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task and Move to Next");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search Task by Priority");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            int id, pos;
            String name, priority, date;

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Priority: ");
                    priority = sc.nextLine();
                    System.out.print("Enter Due Date: ");
                    date = sc.nextLine();
                    scheduler.addAtBeginning(id, name, priority, date);
                    break;

                case 2:
                    System.out.print("Enter Task ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Priority: ");
                    priority = sc.nextLine();
                    System.out.print("Enter Due Date: ");
                    date = sc.nextLine();
                    scheduler.addAtEnd(id, name, priority, date);
                    break;

                case 3:
                    System.out.print("Enter Position: ");
                    pos = sc.nextInt();
                    System.out.print("Enter Task ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Priority: ");
                    priority = sc.nextLine();
                    System.out.print("Enter Due Date: ");
                    date = sc.nextLine();
                    scheduler.addAtPosition(pos, id, name, priority, date);
                    break;

                case 4:
                    System.out.print("Enter Task ID to Remove: ");
                    id = sc.nextInt();
                    scheduler.removeById(id);
                    break;

                case 5:
                    scheduler.viewAndMoveToNext();
                    break;

                case 6:
                    scheduler.displayTasks();
                    break;

                case 7:
                    System.out.print("Enter Priority to Search: ");
                    priority = sc.nextLine();
                    scheduler.searchByPriority(priority);
                    break;

                case 8:
                    System.out.println("Exiting Task Scheduler.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);

        sc.close();
    }
}
