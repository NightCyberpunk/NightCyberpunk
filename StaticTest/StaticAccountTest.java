package StaticTest;
//编写一个类 实现银行账户的概念，包含的属性有账号、密码、存款余额、利率、最小余额、定义封装这些属性的方法
//账号要自动生成

public class StaticAccountTest {
    public static void main(String[] args) {
        Account acc1 = new Account();
        System.out.println(acc1);
        Account acc2 = new Account("123456",33);
        System.out.println(acc2);
    }
}
class Account{
    private int id;
    private String password;
    private double balance;
    private static double intereRate;
    private static double minBalance = 1.0;

    private static int init =100;   //用于自动生成id的基数

    public Account() {
        this.id = init;
        init++;
        password ="123456";
    }

    public Account(String password, double balance) {
        this.password = password;
        this.balance = balance;
        this.id = init;
        init++;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static double getIntereRate() {
        return intereRate;
    }

    public static void setIntereRate(double intereRate) {
        Account.intereRate = intereRate;
    }

    public static double getMinBalance() {
        return minBalance;
    }

    public static void setMinBalance(double minBalance) {
        Account.minBalance = minBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
