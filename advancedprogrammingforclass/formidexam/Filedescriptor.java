import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;



public class Filedescriptor{
    public static void main(String[] args) throws IOException {
        File f = new File ("filedescriptor.txt");
        FileOutputStream f1 = new FileOutputStream(f);
        FileDescriptor f2 = f1.getFD();
        FileOutputStream f3 = new FileOutputStream(f2);
        f3.write("hello bayo".getBytes());
        f3.close();
        f1.close();
        File f4 = new File("filedescriptor.txt");
        FileInputStream f5 = new FileInputStream(f4);
        FileDescriptor f6 = f5.getFD();
        FileInputStream f7 = new FileInputStream(f6);
        int data;
        while ((data=f7.read())!=-1){
            System.out.print((char) data);
        }
        f5.close();
        f7.close();
    }
}