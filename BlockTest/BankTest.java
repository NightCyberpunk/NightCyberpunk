package BlockTest;

//饿汉式单例模式


//public class BankTest {
//    public static void main(String[] args) {
//        Bank bank1 = Bank.getInstance();
//        Bank bank2 = Bank.getInstance();
//        System.out.println(bank1 == bank2); //两个类进行==比较，判断的是其地址是否相同
//    }
//}
//class Bank{
//    //第一步 类的构造器私有化
//    private Bank(){ //构造器私有化 无法调用
//
//    }
//    //第二步，在类的内部创建当前类的实例对象
//    //第四步 将属性也声明为static
//    private static Bank instance = new Bank();
//    //第三步 使用getXxx()的方法获取当前类的实例，必须声明为static
//
//    public static Bank getInstance(){
//        return instance;
//    }
//}


public class BankTest {
    public static void main(String[] args) {
        Bank b1 = Bank.getInstance();
        Bank b2 = Bank.getInstance();
        System.out.println(b1 == b2);
    }
}
class Bank{
    private Bank(){

    }

    private static Bank instance = new Bank();

    public static Bank getInstance(){
        return instance;
    }

}