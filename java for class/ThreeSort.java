import java.util.Scanner;
public class ThreeSort {
    public static void main(String[] args){
        Scanner bayisa = new Scanner(System.in);
        System.out.println("the first Element: ");
        int a = bayisa.nextInt();
        System.out.println("the second Element: ");
        int b = bayisa.nextInt();
        System.out.println("the Third Element: ");
        int c = bayisa.nextInt();
        int largest=Math.max(Math.max(a,b),c);
        int minimum=Math.min(Math.min(a,b),c);
        int med=a+b+c-largest-minimum;
        System.out.println("sorted numbers are: "+ minimum + " " + med + " " + largest);
        bayisa.close();
    }
}
