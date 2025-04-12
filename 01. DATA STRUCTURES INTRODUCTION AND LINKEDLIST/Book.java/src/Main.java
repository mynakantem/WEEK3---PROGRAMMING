import java.util.Scanner;

class Book {
    String title, author, genre, bookId;
    boolean isAvailable;
    Book next, prev;

    public Book(String title, String author, String genre, String bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = this.prev = null;
    }

    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author +
                ", Genre: " + genre + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

class Library {
    private Book head = null;
    private Book tail = null;

    // Add at beginning
    public void addAtBeginning(Book book) {
        if (head == null) {
            head = tail = book;
        } else {
            book.next = head;
            head.prev = book;
            head = book;
        }
    }

    // Add at end
    public void addAtEnd(Book book) {
        if (head == null) {
            head = tail = book;
        } else {
            tail.next = book;
            book.prev = tail;
            tail = book;
        }
    }

    // Add at specific position
    public void addAtPosition(Book book, int position) {
        if (position <= 0) {
            addAtBeginning(book);
            return;
        }

        Book temp = head;
        int index = 0;
        while (temp != null && index < position - 1) {
            temp = temp.next;
            index++;
        }

        if (temp == null) {
            addAtEnd(book);
        } else {
            book.next = temp.next;
            if (temp.next != null)
                temp.next.prev = book;
            book.prev = temp;
            temp.next = book;
            if (book.next == null)
                tail = book;
        }
    }

    // Remove by Book ID
    public void removeByBookId(String bookId) {
        Book temp = head;

        while (temp != null) {
            if (temp.bookId.equalsIgnoreCase(bookId)) {
                if (temp.prev != null)
                    temp.prev.next = temp.next;
                else
                    head = temp.next;

                if (temp.next != null)
                    temp.next.prev = temp.prev;
                else
                    tail = temp.prev;

                System.out.println("Book removed successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    // Search by Title or Author
    public void search(String keyword) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(keyword) || temp.author.equalsIgnoreCase(keyword)) {
                System.out.println(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No matching book found.");
    }

    // Update availability status
    public void updateAvailability(String bookId, boolean status) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId.equalsIgnoreCase(bookId)) {
                temp.isAvailable = status;
                System.out.println("Availability updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    // Display forward
    public void displayForward() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }
        System.out.println("\nBooks in Library (Forward):");
        Book temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // Display reverse
    public void displayReverse() {
        if (tail == null) {
            System.out.println("Library is empty.");
            return;
        }
        System.out.println("\nBooks in Library (Reverse):");
        Book temp = tail;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.prev;
        }
    }

    // Count total books
    public void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total number of books: " + count);
    }
}

class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;

        do {
            System.out.println("\n--- Library Management Menu ---");
            System.out.println("1. Add Book at Beginning");
            System.out.println("2. Add Book at End");
            System.out.println("3. Add Book at Position");
            System.out.println("4. Remove Book by ID");
            System.out.println("5. Search Book by Title or Author");
            System.out.println("6. Update Book Availability");
            System.out.println("7. Display Books (Forward)");
            System.out.println("8. Display Books (Reverse)");
            System.out.println("9. Count Total Books");
            System.out.println("10. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            sc.nextLine();

            String title, author, genre, bookId;
            boolean status;
            int position;

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Book Title: ");
                    title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    author = sc.nextLine();
                    System.out.print("Enter Genre: ");
                    genre = sc.nextLine();
                    System.out.print("Enter Book ID: ");
                    bookId = sc.nextLine();
                    System.out.print("Is Available (true/false): ");
                    status = sc.nextBoolean();
                    Book newBook = new Book(title, author, genre, bookId, status);
                    if (choice == 1)
                        library.addAtBeginning(newBook);
                    else if (choice == 2)
                        library.addAtEnd(newBook);
                    else {
                        System.out.print("Enter Position: ");
                        position = sc.nextInt();
                        library.addAtPosition(newBook, position);
                    }
                    break;

                case 4:
                    System.out.print("Enter Book ID to Remove: ");
                    bookId = sc.nextLine();
                    library.removeByBookId(bookId);
                    break;

                case 5:
                    System.out.print("Enter Title or Author to Search: ");
                    String keyword = sc.nextLine();
                    library.search(keyword);
                    break;

                case 6:
                    System.out.print("Enter Book ID: ");
                    bookId = sc.nextLine();
                    System.out.print("Set Availability (true/false): ");
                    status = sc.nextBoolean();
                    library.updateAvailability(bookId, status);
                    break;

                case 7:
                    library.displayForward();
                    break;

                case 8:
                    library.displayReverse();
                    break;

                case 9:
                    library.countBooks();
                    break;

                case 10:
                    System.out.println("Exiting Library System.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 10);
        sc.close();
    }
}
