class TicketNode {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    TicketNode next;

    public TicketNode(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private TicketNode head = null;
    private TicketNode tail = null;

    public void addTicket(int id, String customer, String movie, String seat, String time) {
        TicketNode newNode = new TicketNode(id, customer, movie, seat, time);

        if (head == null) {
            head = tail = newNode;
            tail.next = head;  // circular link
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;  // keep circular
        }

        System.out.println("✅ Ticket added: " + id);
    }

    public void removeTicket(int id) {
        if (head == null) {
            System.out.println("❌ No tickets to remove.");
            return;
        }

        TicketNode current = head, prev = null;

        do {
            if (current.ticketId == id) {
                if (current == head && current == tail) {  // Only one ticket
                    head = tail = null;
                } else if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else if (current == tail) {
                    prev.next = head;
                    tail = prev;
                } else {
                    prev.next = current.next;
                }

                System.out.println("🗑️ Ticket removed: " + id);
                return;
            }

            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("❗Ticket ID not found.");
    }

    public void displayTickets() {
        if (head == null) {
            System.out.println("📭 No tickets booked.");
            return;
        }

        TicketNode temp = head;
        System.out.println("🎟️ Current Booked Tickets:");
        do {
            System.out.println("ID: " + temp.ticketId + ", Name: " + temp.customerName +
                    ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchTicket(String keyword) {
        if (head == null) {
            System.out.println("📭 No tickets to search.");
            return;
        }

        boolean found = false;
        TicketNode temp = head;

        do {
            if (temp.customerName.equalsIgnoreCase(keyword) || temp.movieName.equalsIgnoreCase(keyword)) {
                System.out.println("🔍 Found: ID " + temp.ticketId + ", " + temp.customerName + " - " + temp.movieName);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("❌ No match found for: " + keyword);
        }
    }

    public int totalTickets() {
        if (head == null) return 0;

        int count = 0;
        TicketNode temp = head;

        do {
            count++;
            temp = temp.next;
        } while (temp != head);

        return count;
    }

    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        system.addTicket(101, "Anjali", "Dune 2", "A1", "10:00 AM");
        system.addTicket(102, "Rahul", "Kung Fu Panda", "B2", "11:30 AM");
        system.addTicket(103, "Anjali", "Avatar 3", "C3", "1:00 PM");

        system.displayTickets();
        System.out.println("🎫 Total tickets: " + system.totalTickets());

        system.searchTicket("Anjali");
        system.searchTicket("Dune 2");

        system.removeTicket(102);
        system.displayTickets();
        System.out.println("🎫 Total tickets after removal: " + system.totalTickets());
    }
}
