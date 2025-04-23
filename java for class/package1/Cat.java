package package1;

public class Cat extends Animal {
    public void makeVoice(){
        System.out.println("the act meows.");
    }
    public void displayName(){
        System.out.println("the cat's name is "+ name);
    }
    public double calcHeight(){
        return 30/age;
    }
    
}
