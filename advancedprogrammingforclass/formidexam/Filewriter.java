import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
public class Filewriter {
    
    public static void main(String[] args) throws IOException{
        FileWriter filwriter = new FileWriter("filedescriptor.txt");
        filwriter.write("hello bayoko\n");
        filwriter.write("anis jira galata gooftaa yesuus kiristoos");
        System.out.println("file written successfully!");
        filwriter.close();
        FileReader fread=new FileReader("filedescriptor.txt");
        int data;
        while ((data=fread.read())!=-1){
            System.out.print((char)data);
        }
        fread.close();
    }
}
