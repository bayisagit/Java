import java.util.Scanner;
public class evencheck {
    public static void main(String[] arg){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number=scanner.nextInt();
        if (number%2 == 0){
            System.out.println(number + " is an even number!");
        }
        else{
            System.out.println(number + " is odd number!");
        }
        scanner.close();

    }
    
}
