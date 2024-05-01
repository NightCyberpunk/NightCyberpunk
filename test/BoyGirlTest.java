package test;

public class BoyGirlTest {
    public static void main(String []args){
        Boy boy1 = new Boy("Jack",24);
        Girl girl1 = new Girl("Tom",20);
        girl1.marry(boy1);
        boy1.shout();
    }
}
