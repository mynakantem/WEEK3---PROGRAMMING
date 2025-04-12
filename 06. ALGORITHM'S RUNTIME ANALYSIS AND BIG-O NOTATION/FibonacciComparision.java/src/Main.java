class FibonacciComparison {

    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void testFibonacci(int n) {
        System.out.println("🔢 Fibonacci of n = " + n);

        if (n <= 30) {
            long startRec = System.nanoTime();
            int resultRec = fibonacciRecursive(n);
            long endRec = System.nanoTime();
            System.out.printf("Recursive Result: %d | Time: %.4f ms%n", resultRec, (endRec - startRec) / 1_000_000.0);
        } else {
            System.out.println("Recursive: ❌ Skipped (Too slow)");
        }

        long startItr = System.nanoTime();
        int resultItr = fibonacciIterative(n);
        long endItr = System.nanoTime();
        System.out.printf("Iterative Result: %d | Time: %.4f ms%n", resultItr, (endItr - startItr) / 1_000_000.0);

        System.out.println("--------------------------------------");
    }

    public static void main(String[] args) {
        int[] testValues = {10, 30, 50};

        for (int n : testValues) {
            testFibonacci(n);
        }
    }
}
