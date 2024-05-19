package StaticTest;

public class StaticNullTest {
    public static void main(String[] args) {
        Order o1 = null;
        System.out.println(o1.count);
        o1.hello();
    }
}
class Order{
    public static int count = 1;
    public static void hello(){
        System.out.println("hello");
    }
}
