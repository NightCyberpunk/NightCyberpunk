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
````

## 静态变量与实例变量

>* 个数
>
>  静态变量:在内存空间中只有一份，被类的多个对象所共享
>
>  实例变量：类的每一个实例（或对象）都保存着一份实例变量
>
>* 内存位置
>
>  静态变量:
>
>  实例变量：

