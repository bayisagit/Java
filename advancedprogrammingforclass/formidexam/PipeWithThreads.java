import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.IOException;

class WriterThread extends Thread {
    private PipedOutputStream output;

    public WriterThread(PipedOutputStream output) {
        this.output = output;
    }

    public void run() {
        try {
            output.write("Hello from WriterThread!".getBytes());
            output.close();
        } catch (IOException e) {
            System.out.println("Writer error: " + e.getMessage());
        }
    }
}

class ReaderThread extends Thread {
    private PipedInputStream input;

    public ReaderThread(PipedInputStream input) {
        this.input = input;
    }

    public void run() {
        try {
            int data;
            while ((data = input.read()) != -1) {
                System.out.print((char) data);
            }
            input.close();
        } catch (IOException e) {
            System.out.println("Reader error: " + e.getMessage());
        }
    }
}

public class PipeWithThreads {
    public static void main(String[] args) throws IOException {
        PipedOutputStream output = new PipedOutputStream();
        PipedInputStream input = new PipedInputStream(output); // Connect them!

        WriterThread writer = new WriterThread(output);
        ReaderThread reader = new ReaderThread(input);

        writer.start(); // Start the writer thread
        reader.start(); // Start the reader thread
    }
}
