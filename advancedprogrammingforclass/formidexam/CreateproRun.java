import java.lang.Runtime;
import java.lang.Process;
import java.io.IOException;

public class CreateproRun {
    public static void main(String[] args) throws IOException, InterruptedException{
        Runtime prui=Runtime.getRuntime();
        Process prs=prui.exec("notepad.exe test.txt");
        int exitcode=prs.waitFor();
        System.out.println("process exited with the code " + exitcode);
    }

}
