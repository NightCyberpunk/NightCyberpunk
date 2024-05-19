package StaticTest;

class Chinese{
    String name;
    int age;
    static String nation; //静态变量
    public Chinese(String name,int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Chinese{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void show(){
        System.out.println("我是中国人\n");
        
    }
}
public class StaticExpTest {
    public static void main(String[] args) {
        Chinese.show();//没有创建c1,c2这种对象，仍然可以调用类Chinese中的方法
        Chinese c1 = new Chinese("姚明",30);
        Chinese c2 = new Chinese("刘翔",31);
        c1.nation = "China";
        c2.nation = "CHN";
        System.out.println(c1.nation);
        System.out.println(Chinese.nation);
    }
}
