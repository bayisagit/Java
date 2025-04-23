package package1;
import java.lang.ArithmeticException;

public class AnimalDemo {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.name="whiskers";
        cat.age=5;
        cat.makeVoice();
        cat.displayName();
        System.out.println("cat height:" + cat.calcHeight());
        // Exception handling in Cat class
        System.out.println("\nException handling in Cat class:");
        try {
            cat.age = 0;
            System.out.println("Cat height: " + cat.calcHeight());
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Polymorphism using the given classes
        System.out.println("\nPolymorphism using the given classes:");
        Animal animal = new Animal();
        animal.makeVoice();
        animal = new Dog();
        animal.makeVoice();
        animal = new Cat();
        animal.makeVoice();
    }

}
