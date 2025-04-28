import java.util.Collection;
import java.util.ArrayList;
public class MyCollection {
    public static void main(String[] args) {
        Collection<String> fruits = new ArrayList<>();
        fruits.add("banana");
        fruits.add("tomato");
        fruits.add("mango");
        System.out.println("Fruits length is: " + fruits.size());
        if(fruits.contains("banana")){
            System.out.println("the fruits contains banana");
        }
        fruits.remove("banana");
        for(String frui : fruits){
            System.out.println(frui);
        }
        System.out.println("is the fruits empy? " + fruits.isEmpty());
    }
}