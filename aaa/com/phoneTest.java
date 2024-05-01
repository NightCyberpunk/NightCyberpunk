package aaa.com;

public class phoneTest {
    public static void main(String[] args) {
        phone p1 = new phone();
        p1.number = 20.3;
        System.out.println(p1.name);  //为赋值为null
        p1.SendMessage("有内鬼，终止交易");
    }
}
