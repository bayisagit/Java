import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.IOException;
public class Pipe {
    public static void main(String[] args) throws IOException{
        PipedOutputStream writ = new PipedOutputStream();
        PipedInputStream rad = new PipedInputStream(writ);
        writ.write("hello from writer! ".getBytes());
        writ.close();
        int data;
        while((data=rad.read())!=-1){
            System.out.print((char) data);
        }
        rad.close();
    }
}
