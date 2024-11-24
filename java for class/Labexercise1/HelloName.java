import java.util.*;

public class HelloName {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = input.nextLine();
        System.out.println("Hello there " + name + ", nice to meet you!");
        input.close();
    }
}