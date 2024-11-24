public class TestLocal {
    
    public void pupAge(int age) {
        age = age + 7;
        System.out.println("Puppy age is : " + age);
    }

    public static void main(String args[]) {
        TestLocal test = new TestLocal();
        int age=0;
        test.pupAge(age);
    }
    
}