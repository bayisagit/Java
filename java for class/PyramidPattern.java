import java.util.Scanner;

public class PyramidPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of lines: ");
        int numLines = scanner.nextInt();

        for (int i = 1; i <= numLines; i++) {
            // Print leading spaces
            for (int j = 1; j <= numLines - i; j++) {
                System.out.print(" ");
            }

            // Print the numbers
            for (int j = i; j >= 1; j--) {
                System.out.print(j);
            }
            for (int j = 2; j <= i; j++) {
                System.out.print(j);
            }

            System.out.println();
        }
        scanner.close();
    }
}