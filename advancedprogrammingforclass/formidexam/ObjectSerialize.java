import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

class Student implements Serializable{
    int id;
    String name;
    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }
}
public class ObjectSerialize {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student s1 = new Student(12, "bayisa");
        FileOutputStream fout = new FileOutputStream("student.txt");
        ObjectOutputStream outs = new ObjectOutputStream(fout);
        outs.writeObject(s1);
        outs.close();
        fout.close();
        System.out.println("object is serializable");
        FileInputStream fin = new FileInputStream("student.txt");
        ObjectInputStream ins = new ObjectInputStream(fin);
        Student s2 = (Student) ins.readObject();
        ins.close();
        fin.close();
        System.out.println("object has been deserialized");
        System.out.println("student name is: " + s2.id);
        System.out.println("student name is: " + s2.name);

    }
}
