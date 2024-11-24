public class StringExample {
    public static void main(String[] args) {
        String text1 = "Hello", text2 = "World";
        String text3 = "I Love Java and Java loves me.";
        
        System.out.println(text1.substring(1, 3)); // Output: "el"
        System.out.println(text2.length());        // Output: 5
        System.out.println(text3.indexOf("I"));    // Output: 0
        System.out.println(text1 + " " + text2);   // Output: "Hello World"
    }
}