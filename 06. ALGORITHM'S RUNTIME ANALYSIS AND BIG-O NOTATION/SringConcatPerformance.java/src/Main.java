class StringConcatPerformance {

    public static void testStringConcat(int N) {
        long start = System.nanoTime();
        String s = "";
        for (int i = 0; i < N; i++) {
            s += "a";
        }
        long end = System.nanoTime();
        System.out.printf("String (O(N^2)) Time for %,d ops: %.2f ms%n", N, (end - start) / 1_000_000.0);
    }

    public static void testStringBuilderConcat(int N) {
        long start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append("a");
        }
        long end = System.nanoTime();
        System.out.printf("StringBuilder (O(N)) Time for %,d ops: %.2f ms%n", N, (end - start) / 1_000_000.0);
    }

    public static void testStringBufferConcat(int N) {
        long start = System.nanoTime();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < N; i++) {
            sbuf.append("a");
        }
        long end = System.nanoTime();
        System.out.printf("StringBuffer (O(N)) Time for %,d ops: %.2f ms%n", N, (end - start) / 1_000_000.0);
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 1_000_000};

        for (int size : sizes) {
            System.out.println("--------------------------------------------------");
            System.out.printf("🔢 Testing with %,d operations:%n", size);

            if (size <= 10000) {
                testStringConcat(size);
            } else {
                System.out.printf("String (O(N^2)) Time for %,d ops: Skipped (Too slow)%n", size);
            }

            testStringBuilderConcat(size);
            testStringBufferConcat(size);
        }
    }
}
