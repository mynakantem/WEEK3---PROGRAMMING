import java.io.*;

class ConsoleToFileWriter {
    public static void main(String[] args) {
        String filePath = "user_input.txt";

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println("Enter text to write to the file (type 'exit' to finish):");

            String inputLine;

            while (true) {
                inputLine = bufferedReader.readLine();

                if (inputLine.equalsIgnoreCase("exit")) {
                    break;
                }

                bufferedWriter.write(inputLine);
                bufferedWriter.newLine();
            }

            bufferedReader.close();
            bufferedWriter.close();

            System.out.println("Your input has been written to " + filePath);

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
