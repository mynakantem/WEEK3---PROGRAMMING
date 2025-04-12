import java.io.*;

class FileReadingPerformance {

    public static void testFileReader(String filePath) throws IOException {
        long start = System.nanoTime();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.read() != -1) {
            }
        }

        long end = System.nanoTime();
        System.out.printf("FileReader Time: %.2f seconds%n", (end - start) / 1_000_000_000.0);
    }

    public static void testInputStreamReader(String filePath) throws IOException {
        long start = System.nanoTime();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath)))) {
            while (reader.read() != -1) {
            }
        }

        long end = System.nanoTime();
        System.out.printf("InputStreamReader Time: %.2f seconds%n", (end - start) / 1_000_000_000.0);
    }

    public static void main(String[] args) throws IOException {
        String filePath = "path_to_your_large_file.txt";

        System.out.println("Reading 500MB file...");
        System.out.println("-------------------------------------");

        testFileReader(filePath);
        testInputStreamReader(filePath);
    }
}
