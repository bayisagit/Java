import java.util.Scanner;
public class DisplayBetweenNum {
    public static void main(String[] args){
        Scanner bayisa = new Scanner(System.in);
        System.out.print("enter the first number: ");
        int a = bayisa.nextInt();
        System.out.print("enter the second number: ");
        int b = bayisa.nextInt();
        while(a<=b){
            System.out.print(a + " ");
            a+=1;
        }
        bayisa.close();
    }
}
