import java.util.Scanner;
public class TwodimenArray {
    public static void main(String arg[]){
        Scanner n = new Scanner(System.in);
        System.out.println("enter the number of row: ");
        int rows=n.nextInt();
        System.out.println("enter the number of column: ");
        int clmn=n.nextInt();
        int nums[][]=new int [rows][clmn];
        for(int i=0;i<rows;i++){
            for(int j=0;j<clmn;j++){
                System.out.println("enter the number with row " + (i+1) + " column " + (j+1) + ": ");
                nums[i][j]=n.nextInt();
            }
        }
        System.out.println("The Entered Numbers are: ");
        for(int i=0;i<rows;i++){
            for(int j=0;j<clmn;j++){
                System.out.print(nums[i][j]+ " ");
            }
            System.out.println();
        }
        n.close();
     

    }
    
}
