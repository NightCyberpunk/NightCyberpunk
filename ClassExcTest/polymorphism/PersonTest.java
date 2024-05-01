package ClassExcTest.polymorphism;

public class PersonTest {
    public static void main(String[] args) {
        Person p1 = new Man();
        //不能调用子类中特有的方法
        Man m1 = (Man) p1;//向下强制转型
        System.out.println(m1.isSmoking);
        System.out.println(p1);
        System.out.println(m1);
        System.out.println(p1 == m1);
    }
}
