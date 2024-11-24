import java.util.Scanner;  

public class Age {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("How old are you? "); 
        int age = console.nextInt();
        System.out.println("You'll be 30 in " + (30 - age) + " years.");
        console.close(); 
    }
}