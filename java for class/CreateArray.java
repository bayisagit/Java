import java.util.Scanner;
public class CreateArray {
    public static double volume(int radius){
        return (4/3)*Math.PI*Math.pow(radius,3);
    }
    public static void main(String[] args) {
        Scanner radi=new Scanner(System.in);
        System.out.println("enter the radius");
        int radius=radi.nextInt();
        double res= volume(radius);
        System.out.println("the volume is "+res);
        radi.close();
        
    }
}
