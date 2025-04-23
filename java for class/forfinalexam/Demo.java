class BayisaExeption extends Exception{
    public BayisaExeption(String string){
        super(string);
    }
}
public class Demo {
    public static void main(String[] args){
        int i=20;
        int j=0;
        int nums[]=new int[5];
        try{
            j=18/i;
            if (j==0){
                throw new BayisaExeption(string:"i dont want to display zero!");
            }
            System.out.println(nums[5]);
        }
        catch(BayisaExeption de){
            System.out.println("do not enter the zero"+de);
        }
        catch(ArrayIndexOutOfBoundsException de){
            System.out.println("stay on limit!" +de);
        }
        catch(Exception de){
            System.out.println("something went wrong!"+de);
        }
        System.out.println(j);
        System.out.println("bye");
    }
}
