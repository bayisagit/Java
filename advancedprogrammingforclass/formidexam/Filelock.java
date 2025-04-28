import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.nio.channels.FileChannel;
import java.io.IOException;
public class Filelock {
    
    public static void main(String[] args) throws IOException{
        RandomAccessFile fos = new RandomAccessFile("filedescriptor.txt","rw");
        FileChannel channel = fos.getChannel();
        FileLock foslock = channel.lock();
        System.out.println("file is locked");
        foslock.release();
        System.out.println("file locked is released");
        channel.close();
        fos.close();
        
    }
}
