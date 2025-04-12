class Node {
    String text;
    Node prev, next;

    public Node(String text) {
        this.text = text;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    private Node head, tail, current;
    private int length;
    private final int MAX_HISTORY = 10;

    public void addState(String newText) {
        Node newNode = new Node(newText);

        if (current != null && current.next != null) {
            truncateForwardHistory();
        }

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        current = tail;
        length++;

        if (length > MAX_HISTORY) {
            head = head.next;
            head.prev = null;
            length--;
        }
    }

    private void truncateForwardHistory() {
        Node node = current.next;
        while (node != null) {
            Node temp = node.next;
            node.prev = null;
            node.next = null;
            node = temp;
            length--;
        }
        current.next = null;
        tail = current;
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("Nothing to redo.");
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current State: \"" + current.text + "\"");
        } else {
            System.out.println("Editor is empty.");
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.addState("Hello");
        editor.addState("Hello, world");
        editor.addState("Hello, world!");
        editor.displayCurrentState();

        editor.undo();
        editor.displayCurrentState();

        editor.undo();
        editor.displayCurrentState();

        editor.redo();
        editor.displayCurrentState();

        editor.addState("New text after undo");
        editor.displayCurrentState();

        editor.redo();
    }
}
