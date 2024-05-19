# static关键字

>在类中声明的实例方法，在类的外面必须先创建对象，才能调用。但有些方法的调用者和当前类的对象无关，这样的方法通常被声明为`类方法`，由于不需要创建对象就可以调用类方法，从而简化了方法的调用。
>
>这里的类变量、类方法，只需要用`static`修饰即可。也被成为静态变量、静态方法。

## static关键字的使用

* static通常译为`静态的`

* static使用范围

  static通常用来修饰：属性、方法、代码块、内部类。 

````java
class Chinese{
    String name;
    int age;
    static String nation; //民族
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
}
public class StaticTest{
    public static void main(String []args){
        Chinese c1 = new Chinese("张三",40);
        Chinese c2 = new Chinese("李四",30);
        c1.nation = "China";
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c1.nation); 
        System.out.println(c2.nation); 
    }
}
//c1.nation : China
//c2.nation : China
//只赋值c1的nation,所有Chinese类的nation属性都是China
//或通过任意途径赋值，其所有Chinese类中nation都会受到改变
````

## 静态变量（类变量）与实例变量

>* 变量个数
>
>   静态变量:在内存空间中只有一份，被类的多个对象所共享
>
>   实例变量:类的每一个实例（或对象）都保存着一份实例变量
>
>* 内存位置
>
>   静态变量:JDK6及以前，存放在方法区中。JDK7及以后，存放在堆空间。
>
>   实例变量:存放在堆空间的实例变量。
>
>* 加载时机
>
>  静态变量:随着类加载而加载。
>
>  实例变量:随着对象的创建而加载。
>
>* 调用者:
>
>  静态变量：可以被类直接使用，也可以使用对象调用。
>
>  实例变量：只能使用对象调用。
>
>* 判断是否可以调用
>
>  |      | 类变量 | 实例变量 |
>  | :--- | ------ | -------- |
>  | 类   | YES    | NO       |
>  | 对象 | YES    | YES      |
>
>* 消亡时机
>
>  静态变量：随着类得到卸载而消亡。
>
>  实例变量：随着对象的消亡而消亡。
>
>|          | 实例变量             | 静态变量                         |
>| -------- | -------------------- | -------------------------------- |
>| 变量个数 | 每一个对象都存在     | 只存在一个                       |
>| 内存位置 | 堆空间中             | JDK6前(方法区)、JDK7后（堆空间） |
>| 加载时机 | 对象创建时           | 类的创建时                       |
>| 调用对象 | 每一个对象都可以调用 | 类和每一个对象                   |
>| 消亡时机 | 随着对象的消亡       | 随着类的消亡                     |

## static修饰方法

> 随着类的加载而加载
>
> 可以通过`类.静态方法`的方式调用静态方法

````java
public class ChineseTest{
    public static void main(String []args){
        Chinese.show();//没有创建实体对象，但也可以直接通过类来调用
    }
}
class Chinese{
    String name;
    int age;
    public static void show(){
        System.out.println("CHN");
    }
}
````

>是否可以在静态方法中，调用静态属性、静态方法、或非静态的方法、属性？
>
>在类的非静态方法中，可以调用当前类中的静态结构或非静态结构（属性、方法）

* static修饰的方法中，不能使用`this`或`super`

## 实际开发

>Q:开发中，什么时候需要将属性声明为静态?
>
>* 判断当前类的多个实例是否能共享此成员变量，且此成员变量的值是相同的
>* 开发中，常将一些常量声明为静态的。例如,Math中的PI
>
>Q:开发中，什么时候需要将方法声明为静态的?
>
>* 方法内操作的变量如果都是静态变量，则此方法建议声明为静态方法
>* 开发中，常常将工具类中的方法，声明为静态方法。例如,`Arrays类、Math类`

## 易错点

````java
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
//输出:
// 1
// hello
//count属性和hello()方法都是静态的，不依靠指针调用
````

