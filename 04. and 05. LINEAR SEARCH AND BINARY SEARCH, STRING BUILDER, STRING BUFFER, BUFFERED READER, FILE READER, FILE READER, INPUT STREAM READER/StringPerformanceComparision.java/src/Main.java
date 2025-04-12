class StringPerformanceComparison {
    public static void main(String[] args) {
        int iterations = 1_000_000;
        String word = "hello";

        long startTimeBuilder = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            stringBuilder.append(word);
        }
        long endTimeBuilder = System.nanoTime();
        long durationBuilder = (endTimeBuilder - startTimeBuilder) / 1_000_000;

        long startTimeBuffer = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            stringBuffer.append(word);
        }
        long endTimeBuffer = System.nanoTime();
        long durationBuffer = (endTimeBuffer - startTimeBuffer) / 1_000_000;

        System.out.println("Time taken by StringBuilder: " + durationBuilder + " ms");
        System.out.println("Time taken by StringBuffer : " + durationBuffer + " ms");
    }
}
