import java.util.Scanner;

class Item {
    String name;
    String id;
    int quantity;
    double price;
    Item next;

    public Item(String name, String id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Item ID: " + id + ", Name: " + name + ", Quantity: " + quantity + ", Price: " + price;
    }
}

class Inventory {
    private Item head = null;

    public void addAtBeginning(String name, String id, int qty, double price) {
        Item newItem = new Item(name, id, qty, price);
        newItem.next = head;
        head = newItem;
    }

    public void addAtEnd(String name, String id, int qty, double price) {
        Item newItem = new Item(name, id, qty, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newItem;
    }

    public void addAtPosition(int pos, String name, String id, int qty, double price) {
        if (pos == 0) {
            addAtBeginning(name, id, qty, price);
            return;
        }
        Item newItem = new Item(name, id, qty, price);
        Item temp = head;
        for (int i = 1; temp != null && i < pos; i++)
            temp = temp.next;

        if (temp == null) {
            System.out.println("Position out of bounds.");
            return;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }

    public void removeById(String id) {
        if (head == null) return;

        if (head.id.equals(id)) {
            head = head.next;
            System.out.println("Item removed.");
            return;
        }

        Item temp = head;
        while (temp.next != null && !temp.next.id.equals(id))
            temp = temp.next;

        if (temp.next == null) {
            System.out.println("Item not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Item removed.");
        }
    }

    public void updateQuantity(String id, int newQty) {
        Item temp = head;
        while (temp != null) {
            if (temp.id.equals(id)) {
                temp.quantity = newQty;
                System.out.println("Quantity updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    public void search(String keyword) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.id.equalsIgnoreCase(keyword) || temp.name.equalsIgnoreCase(keyword)) {
                System.out.println(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found)
            System.out.println("No matching item found.");
    }

    public void totalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: ₹" + total);
    }

    public void sort(String criteria, boolean ascending) {
        head = mergeSort(head, criteria, ascending);
        System.out.println("Inventory sorted by " + criteria + " (" + (ascending ? "asc" : "desc") + ").");
    }

    private Item mergeSort(Item head, String criteria, boolean ascending) {
        if (head == null || head.next == null) return head;

        Item mid = getMiddle(head);
        Item nextOfMid = mid.next;
        mid.next = null;

        Item left = mergeSort(head, criteria, ascending);
        Item right = mergeSort(nextOfMid, criteria, ascending);

        return merge(left, right, criteria, ascending);
    }

    private Item merge(Item a, Item b, String criteria, boolean asc) {
        if (a == null) return b;
        if (b == null) return a;

        Item result;
        int compare;

        if (criteria.equalsIgnoreCase("name"))
            compare = a.name.compareToIgnoreCase(b.name);
        else
            compare = Double.compare(a.price, b.price);

        if ((asc && compare <= 0) || (!asc && compare > 0)) {
            result = a;
            result.next = merge(a.next, b, criteria, asc);
        } else {
            result = b;
            result.next = merge(a, b.next, criteria, asc);
        }
        return result;
    }

    private Item getMiddle(Item head) {
        if (head == null) return head;

        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public void display() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        Item temp = head;
        System.out.println("Inventory List:");
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class InventoryManagementMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();
        int choice;

        do {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity");
            System.out.println("6. Search Item by ID or Name");
            System.out.println("7. Display Inventory");
            System.out.println("8. Calculate Total Inventory Value");
            System.out.println("9. Sort Inventory");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            String name, id, keyword, criteria;
            int qty, pos;
            double price;
            boolean asc;

            switch (choice) {
                case 1:
                    System.out.print("Enter Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Item ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    price = sc.nextDouble();
                    inventory.addAtBeginning(name, id, qty, price);
                    break;

                case 2:
                    System.out.print("Enter Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Item ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    price = sc.nextDouble();
                    inventory.addAtEnd(name, id, qty, price);
                    break;

                case 3:
                    System.out.print("Enter Position: ");
                    pos = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Item ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    price = sc.nextDouble();
                    inventory.addAtPosition(pos, name, id, qty, price);
                    break;

                case 4:
                    System.out.print("Enter Item ID to Remove: ");
                    id = sc.nextLine();
                    inventory.removeById(id);
                    break;

                case 5:
                    System.out.print("Enter Item ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter New Quantity: ");
                    qty = sc.nextInt();
                    inventory.updateQuantity(id, qty);
                    break;

                case 6:
                    System.out.print("Enter Item ID or Name to Search: ");
                    keyword = sc.nextLine();
                    inventory.search(keyword);
                    break;

                case 7:
                    inventory.display();
                    break;

                case 8:
                    inventory.totalValue();
                    break;

                case 9:
                    System.out.print("Sort by (name/price): ");
                    criteria = sc.nextLine();
                    System.out.print("Ascending? (true/false): ");
                    asc = sc.nextBoolean();
                    inventory.sort(criteria, asc);
                    break;

                case 10:
                    System.out.println("Exiting system.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 10);
        sc.close();
    }
}
