package SingletonTest;


////懒汉式
//
//
//public class GirlFriendTest {
//    public static void main(String[] args) {
//
//    }
//}
//class GirlFriend{
//    //第一步 私有化构造器
//    private GirlFriend(){
//
//    }
//
//    //第二步 声明当前类的实例
//    private static GirlFriend instance = null;
//
//    //第三步 通过getXxx()方法 获取当前类的实例
//    //若为创建对象，则在方法内部进行创建
//    //但此getInstance()方法只能通过对象进行调用，所以声明为static方法，此时 可以直接通过类来调用
//    //声明了方法为static后，静态方法只能调用静态的变量，所以给变量instance也声明为static
//    public static GirlFriend getInstance(){
//        if(instance == null){
//            instance = new GirlFriend();
//        }
//        return instance;
//    }
//    //静态方法中只能调用静态的类
//}

public class GirlFriendTest {
    public static void main(String[] args) {
        GirlFirend g1 = GirlFirend.getInstance();
        GirlFirend g2 = GirlFirend.getInstance();
        System.out.println(g1 == g2);
    }
}

class GirlFirend{
    private GirlFirend(){

    }
    private  static GirlFirend instance =null;

    public static GirlFirend getInstance(){
        if(instance == null){
            instance = new GirlFirend();
        }
        return instance;
    }
}
