import java.io.*;

class CompareBuilderBufferReader {
    public static void main(String[] args) {
        String word = "hello";
        int times = 1_000_000;

        System.out.println("Comparing StringBuilder vs StringBuffer...");

        long startBuilder = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(word);
        }
        long endBuilder = System.nanoTime();
        long durationBuilder = (endBuilder - startBuilder) / 1_000_000;
        System.out.println("StringBuilder time: " + durationBuilder + " ms");

        long startBuffer = System.nanoTime();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < times; i++) {
            sf.append(word);
        }
        long endBuffer = System.nanoTime();
        long durationBuffer = (endBuffer - startBuffer) / 1_000_000;
        System.out.println("StringBuffer time: " + durationBuffer + " ms");

        String filePath = "largefile.txt";

        System.out.println("\nReading file using FileReader...");
        try {
            long startReadFR = System.nanoTime();
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int wordCountFR = 0;
            String lineFR;
            while ((lineFR = bufferedReader.readLine()) != null) {
                String[] words = lineFR.trim().split("\\s+");
                wordCountFR += words.length;
            }
            bufferedReader.close();
            long endReadFR = System.nanoTime();
            long durationFR = (endReadFR - startReadFR) / 1_000_000;
            System.out.println("FileReader word count: " + wordCountFR);
            System.out.println("FileReader time: " + durationFR + " ms");
        } catch (IOException e) {
            System.out.println("FileReader Error: " + e.getMessage());
        }

        System.out.println("\nReading file using InputStreamReader...");
        try {
            long startReadISR = System.nanoTime();
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(isr);
            int wordCountISR = 0;
            String lineISR;
            while ((lineISR = bufferedReader.readLine()) != null) {
                String[] words = lineISR.trim().split("\\s+");
                wordCountISR += words.length;
            }
            bufferedReader.close();
            long endReadISR = System.nanoTime();
            long durationISR = (endReadISR - startReadISR) / 1_000_000;
            System.out.println("InputStreamReader word count: " + wordCountISR);
            System.out.println("InputStreamReader time: " + durationISR + " ms");
        } catch (IOException e) {
            System.out.println("InputStreamReader Error: " + e.getMessage());
        }
    }
}