import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionTest {
    public static void main(String[] args) {
        String[] colors = {"manenta", "red", "white", "blue", "cyan"};
        List<String> list = new ArrayList<String>();
        for (String color : colors)
            list.add(color);

        String[] removeColors = {"red", "white", "blue"};
        List<String> removelist = new ArrayList<String>();
        for (String color : removeColors)
            removelist.add(color);

        System.out.println("arraylist: ");
        for (int count = 0; count < list.size(); count++)
            System.out.printf("%s ", list.get(count)); // added space for readability

        removeColors(list, removelist);

        System.out.println("\n\nArraylist after calling removeColors:");
        for (String color : list)
            System.out.printf("%s ", color); // added space
    }

    private static void removeColors(Collection<String> collection1, Collection<String> collection2) {
        Iterator<String> iterator = collection1.iterator();
        while (iterator.hasNext()) {
            if (collection2.contains(iterator.next())) {
                iterator.remove();
            }
        }
    }
}