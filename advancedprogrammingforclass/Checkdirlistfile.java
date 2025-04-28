import java.io.File;
public class Checkdirlistfile {

    public static void main(String[] args) {
        String name="lab2";
        File f = new File(name);
        if (f.isDirectory()){
            String f1[]=f.list();
            for (int i=0;i<f1.length;i++){
                File f2 = new File(name+"/"+f1[i]);
                if (f2.isDirectory()){
                    System.out.println(f1[i]+" is a directory");
                }
                else{
                    System.out.println(f1[i]+" is a file!");
                }
            }
        }
    }
}