import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    int age;
    char grade;
    Student next;

    public Student(int rollNo, String name, int age, char grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
    }
}

class StudentLinkedList {
    private Student head;

    public void addAtBeginning(int rollNo, String name, int age, char grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    public void addAtEnd(int rollNo, String name, int age, char grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    public void addAtPosition(int index, int rollNo, String name, int age, char grade) {
        if (index == 0) {
            addAtBeginning(rollNo, name, age, grade);
            return;
        }
        Student newStudent = new Student(rollNo, name, age, grade);
        Student temp = head;
        for (int i = 0; temp != null && i < index - 1; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of bounds.");
            return;
        }
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    public void deleteByRollNumber(int rollNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.rollNo == rollNo) {
            head = head.next;
            System.out.println("Student deleted.");
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNo != rollNo) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Student not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Student deleted.");
        }
    }

    public void searchByRollNumber(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Student Found: " + temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll Number " + rollNo + " not found.");
    }

    public void updateGrade(int rollNo, char newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = newGrade;
                System.out.println("Grade updated successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll Number " + rollNo + " not found.");
    }

    public void displayStudents() {
        Student temp = head;
        if (temp == null) {
            System.out.println("No student records available.");
            return;
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class StudentRecordManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentLinkedList record = new StudentLinkedList();

        int choice;
        do {
            System.out.println("\n--- Student Record Management ---");
            System.out.println("1. Add Student at Beginning");
            System.out.println("2. Add Student at End");
            System.out.println("3. Add Student at Specific Position");
            System.out.println("4. Delete Student by Roll Number");
            System.out.println("5. Search Student by Roll Number");
            System.out.println("6. Update Grade by Roll Number");
            System.out.println("7. Display All Students");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            int rollNo, age, position;
            String name;
            char grade;

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    rollNo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    age = sc.nextInt();
                    System.out.print("Enter Grade: ");
                    grade = sc.next().charAt(0);
                    record.addAtBeginning(rollNo, name, age, grade);
                    break;

                case 2:
                    System.out.print("Enter Roll No: ");
                    rollNo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    age = sc.nextInt();
                    System.out.print("Enter Grade: ");
                    grade = sc.next().charAt(0);
                    record.addAtEnd(rollNo, name, age, grade);
                    break;

                case 3:
                    System.out.print("Enter Position (0-based index): ");
                    position = sc.nextInt();
                    System.out.print("Enter Roll No: ");
                    rollNo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    age = sc.nextInt();
                    System.out.print("Enter Grade: ");
                    grade = sc.next().charAt(0);
                    record.addAtPosition(position, rollNo, name, age, grade);
                    break;

                case 4:
                    System.out.print("Enter Roll No to Delete: ");
                    rollNo = sc.nextInt();
                    record.deleteByRollNumber(rollNo);
                    break;

                case 5:
                    System.out.print("Enter Roll No to Search: ");
                    rollNo = sc.nextInt();
                    record.searchByRollNumber(rollNo);
                    break;

                case 6:
                    System.out.print("Enter Roll No to Update Grade: ");
                    rollNo = sc.nextInt();
                    System.out.print("Enter New Grade: ");
                    grade = sc.next().charAt(0);
                    record.updateGrade(rollNo, grade);
                    break;

                case 7:
                    System.out.println("\nStudent Records:");
                    record.displayStudents();
                    break;

                case 8:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 8);

        sc.close();
    }
}
