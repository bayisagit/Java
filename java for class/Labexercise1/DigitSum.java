import java.util.Scanner;
public class DigitSum{
    public static void main(String[] args){
        Scanner bayisa = new Scanner(System.in);
        System.out.print("enter the number: ");
        int a = bayisa.nextInt();
        int sum = 0;
        while (a>0){
            int digit=a%10;
            sum += digit;
            a/=10;
        }
        System.out.println("the sum of the digits is: "+sum);
        bayisa.close();

    }
    
}
