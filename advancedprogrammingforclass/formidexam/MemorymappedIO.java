import java.nio.channels.FileChannel;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.io.IOException;
public class MemorymappedIO {
    public static void main(String[] args) throws IOException{
        RandomAccessFile fil=new RandomAccessFile("memorymapped.txt","rw");
        FileChannel fiil = fil.getChannel();
        MappedByteBuffer buff=fiil.map(FileChannel.MapMode.READ_WRITE, 0, 100);
        buff.putChar('H');
        buff.putChar('e');
        buff.putChar('l');
        buff.putChar('l');
        buff.putChar('o');
        buff.force();
        buff.position(0);
        while (buff.hasRemaining()){
            System.out.println(buff.getChar());
        }
        fil.close();
        fiil.close();
    }
}
