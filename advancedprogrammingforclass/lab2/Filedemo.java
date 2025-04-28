import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
public class Filedemo {
    static void p(String s){
        System.out.println(s);
    }
    public static void main(String[] args) {
        File f1 = new File("C:/Users/hp/OneDrive/Desktop/JAVA/advancedprogrammingforclass/lab2/dir/Testfile.txt");
        p("file name: " + f1.getName());
        p("path: " + f1.getPath());
        p("parent: " + f1.getParent());
        p(f1.exists() ? "exists" : "does not exist");
        p(f1.canWrite() ? "is writeable" : "is not writable");
        p(f1.canRead() ? "is readable" : " is not readable");
        p("is " + (f1.isDirectory() ? "directory" : "not " + " a directory"));
        p(f1.isFile() ? "is normal file" : "might be a named pipe");
        p(f1.isAbsolute() ? "is absolute" : "is not absolute");
        long lastdats=f1.lastModified();
        Date lastd=new Date(lastdats);
        SimpleDateFormat last = new SimpleDateFormat("yyyy-MM-dd HH : mm : SS");
        p("file last modified: "+ last.format(lastd));
        p("file size: " + f1.length() + "bytes");
    }
}
