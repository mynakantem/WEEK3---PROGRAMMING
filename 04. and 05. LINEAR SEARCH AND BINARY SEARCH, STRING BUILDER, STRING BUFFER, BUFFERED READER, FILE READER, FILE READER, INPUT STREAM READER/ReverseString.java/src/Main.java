import java.util.Scanner;

class ReverseString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();


        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.reverse();
        String reversed = sb.toString();

        System.out.println("Reversed String: " + reversed);

        scanner.close();
    }
}