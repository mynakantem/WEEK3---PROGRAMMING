import java.util.*;

class Process {
    String pid;
    int burstTime;
    int priority;
    int remainingTime;
    int waitingTime = 0;
    int turnaroundTime = 0;
    Process next;

    public Process(String pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.next = null;
    }

    public String toString() {
        return "PID: " + pid + ", BT: " + burstTime + ", RT: " + remainingTime + ", Priority: " + priority;
    }
}

class RoundRobinScheduler {
    private Process head = null;
    private Process tail = null;
    private int time = 0;
    private int quantum;

    public RoundRobinScheduler(int quantum) {
        this.quantum = quantum;
    }

    public void addProcess(String pid, int burstTime, int priority) {
        Process newProcess = new Process(pid, burstTime, priority);
        if (head == null) {
            head = tail = newProcess;
            tail.next = head; // Circular
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
    }

    public void removeProcess(String pid) {
        if (head == null) return;

        Process current = head, prev = tail;

        do {
            if (current.pid.equals(pid)) {
                if (current == head && current == tail) {
                    head = tail = null;
                } else {
                    prev.next = current.next;
                    if (current == head) head = current.next;
                    if (current == tail) tail = prev;
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    public void displayQueue() {
        if (head == null) {
            System.out.println("No processes in queue.");
            return;
        }
        System.out.println("\nCurrent Queue:");
        Process temp = head;
        do {
            System.out.println(temp);
            temp = temp.next;
        } while (temp != head);
    }

    public void schedule() {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        List<Process> completed = new ArrayList<>();
        Process current = head;

        System.out.println("\n🔁 Starting Round Robin Scheduling with Time Quantum = " + quantum);

        while (head != null) {
            displayQueue();

            int execTime = Math.min(current.remainingTime, quantum);
            System.out.println("Executing " + current.pid + " for " + execTime + " units.");
            time += execTime;
            current.remainingTime -= execTime;

            Process temp = head;
            do {
                if (temp != current && !completed.contains(temp)) {
                    temp.waitingTime += execTime;
                }
                temp = temp.next;
            } while (temp != head);

            if (current.remainingTime == 0) {
                current.turnaroundTime = time;
                completed.add(current);
                removeProcess(current.pid);
                current = head;
            } else {
                current = current.next;
            }
        }

        double totalWaiting = 0, totalTurnaround = 0;
        System.out.println("\n✅ All processes executed. Final Results:");
        for (Process p : completed) {
            totalWaiting += p.waitingTime;
            totalTurnaround += p.turnaroundTime;
            System.out.println("PID: " + p.pid + " | Waiting Time: " + p.waitingTime + " | Turnaround Time: " + p.turnaroundTime);
        }

        int n = completed.size();
        System.out.printf("\n📊 Average Waiting Time: %.2f\n", totalWaiting / n);
        System.out.printf("📊 Average Turnaround Time: %.2f\n", totalTurnaround / n);
    }
}

class RoundRobinScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Time Quantum: ");
        int quantum = sc.nextInt();

        RoundRobinScheduler scheduler = new RoundRobinScheduler(quantum);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Process " + (i + 1));
            System.out.print("Process ID: ");
            String pid = sc.next();
            System.out.print("Burst Time: ");
            int bt = sc.nextInt();
            System.out.print("Priority: ");
            int pr = sc.nextInt();

            scheduler.addProcess(pid, bt, pr);
        }

        scheduler.schedule();
        sc.close();
    }
}
