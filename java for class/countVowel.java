import java.util.Scanner;
public class countVowel {
    public static void main(String[] args){
        Scanner bayisa= new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = bayisa.nextLine();
        int vo=0;
        int con=0;
        input = input.toLowerCase();
        for (int i=0;i<input.length();i++){
            char currentChar = input.charAt(i);
            if (currentChar == 'a' || currentChar == 'e' || currentChar == 'i' || 
                currentChar == 'o' || currentChar == 'u') {
                vo++;       
            }
        }
        con=input.length()-vo;
        System.out.println("the vowel numbers are: " + vo);
        System.out.println("the consonant numbers are: " + con);
        bayisa.close();
    }
    
}
