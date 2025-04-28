import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
public class Listinterface {
    public static void main(String[] args){
        List<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("blue");
        colors.add("green");
        colors.add("black");
        System.out.println("firs element is: " + colors.get(0));
        ListIterator<String> iteratr = colors.listIterator();
        while(iteratr.hasNext()){
            System.out.println(iteratr.next());
        }
    }
}
