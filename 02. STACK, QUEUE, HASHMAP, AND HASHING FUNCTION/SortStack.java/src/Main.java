import java.util.Stack;

class SortStack {
    public static void sortStack(Stack<Integer> stack) {
        if(!stack.isEmpty()) {
            int top = stack.pop();
            sortStack(stack);
            insertInSortedOrder(stack, top);
        }
    }

    public static void insertInSortedOrder(Stack<Integer> stack, int element) {
        if(stack.isEmpty() || element < stack.peek()) {
            stack.push(element);
            return;
        }

        int top = stack.pop();
        insertInSortedOrder(stack, element);
        stack.push(top);
    }
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(30);
        stack.push(5);
        stack.push(20);
        stack.push(10);
        stack.push(100);

        System.out.println("Original Stack: " + stack);
        sortStack(stack);

        System.out.println("Sorted Stack: " + stack);
    }
}