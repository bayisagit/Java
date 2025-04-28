import java.util.SortedSet;
import java.util.TreeSet;
public class Sortedsetinterface {
    public static void main(String[] args) {
        SortedSet<Integer> nums = new TreeSet<>();
        nums.add(40);
        nums.add(20);
        nums.add(30);
        nums.add(10);
        System.out.println("sorted element is: " + nums);
        System.out.println("first element is: " + nums.first());
        System.out.println("last elemeent is: " + nums.last());
        System.out.println("less than 30 are: " + nums.headSet(30));
        System.out.println("greater than 10 are: " + nums.tailSet(10));

    }
}
