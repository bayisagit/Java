import java.util.Scanner;

public class twoSort {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter the 1st number: ");
        int a = console.nextInt();
        System.out.print("Enter the 2nd number: ");
        int b = console.nextInt();

        if (b < a) {
            int temp = a;
            a = b;
            b = temp;
        }

        System.out.println(a);
        System.out.println(b);
        console.close();
    }
}