import java.util.Scanner;
public class reverseInteger {
    public static void main(String[] args){
        Scanner bayisa = new Scanner(System.in);
        System.out.print("enter the digits: ");
        int nu=bayisa.nextInt();
        int digits=0;
        int digit=0;
        while (nu>0){
            digit=nu%10;
            digits = (digits*10)+digit;
            nu/=10;
        }
        System.out.print(digits);
        bayisa.close();


    }
}
