import java.lang.ProcessBuilder;
import java.lang.Process;
import java.io.IOException;
public class Createprocess {
    
    public static void main(String[] args) throws IOException, InterruptedException{
        ProcessBuilder prbuild = new ProcessBuilder("notepad.exe","test.txt");
        Process pr = prbuild.start();
        int exitcode = pr.waitFor();
        System.out.println("process exited with the code "+ exitcode);
    }
}
