import java.util.NavigableSet;
import java.util.TreeSet;
public class Navigableset {
    public static void main(String[] args) {
        NavigableSet<Integer> nums = new TreeSet<>();
        nums.add(20);
        nums.add(30);
        nums.add(10);
        nums.add(40);
        System.out.println("navigable set: " + nums);
        System.out.println("lower than 30: " + nums.lower(30));
        System.out.println("higher than 40: " + nums.higher(30));
        System.out.println("ceiling of 30: " + nums.ceiling(30));
        System.out.println("floor of 30: " + nums.floor(30));
    }
}
