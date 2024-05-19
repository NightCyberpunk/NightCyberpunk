package getThingTest;
//用于测试赋值的过程
public class FieldTest {
    public static void main(String[] args) {
        Order o1 = new Order();
        System.out.println(o1.orderId);
    }
}
class Order{
    int orderId = 1;
    {
        orderId = 2;
    }

    public Order(){
        orderId = 3;
    }
}
