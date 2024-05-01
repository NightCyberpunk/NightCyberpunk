# equals()和toString()------Object类的使用

## Object的说明

>* 任何一个Java(除Object类)都直接或间接的继承于Object类。类 `java.lang.Object`是类层次结构的根类，即所有其它类的父类。每个类都使用 `Object` 作为超类。
>* Object类称为Java类的根父类
>* Object类中声明的结构（属性、方法等）都具有通用性
>  * Object类中没有声明属性
>  * Object类提供了一个空参的构造器
>  * Object类中声明的方法

## Object类的方法

>重点方法:`equals()`、`toString()`方法
>
>了解方法:`clone()`、`finalize()`
>
>​				`finalize()`:当GC要回收对象时，调用此方法。子类重写此方法，可在释放对象前进行某些操作
>
>​										!finalize()可能导致内部出现循环引用，导致对象不能被回收
>
>​				`clone()`:创建一个新的对象，与原始对象具有相同的值，但这是两个不同的对象，具有不同的内存地址
>
>暂时不关注:`getClass()`、`hashCode()`、`notify()`、`notifyAll()`、`wati()`、`wait(XX)`、`wait(xx,yy)`

### equal()方法

>当进行比较时，
>
>* 基本类型比较值:只要两个变量的值相等，即为true。
>
>  ````java
>  int a=5; 
>  if(a==6){…}
>  ````
>
>* 引用类型比较引用(是否指向同一个对象)：只有指向同一个对象时，==才返回true。
>
>  ```java
>  Person p1=new Person();  	    
>  Person p2=new Person();
>  if (p1==p2){…}
>  ```
>
>  - 用“==”进行比较时，符号两边的`数据类型必须兼容`(可自动转换的基本数据类型除外)，否则编译出错

#### 适用性

任何引用数据类型都可以使用equal()方法

>### equal()的源代码
>
>自定义的类在没有重写Object中equals()方法的情况下，调用的就是Object类中声明的equals()
>
>比较两个对象的引用地址是否相同。（或比较两个对象是否指向了堆空间中的同一个对象实体）
>
>````java
>public boolean equals(Object obj) {
>   return (this == obj);
>}
>````
>
>### 实例
>
>````java
>class User{
>    String name;
>    int age;
>    public User(String name,int age){
>        this.name = name;
>        this.age = age;
>    }
>}
>public class UserTest{
>    public static void main(String []args){
>        User u1 = new User("Tom",12);
>        User u2 = new User("Tom",12);
>        System.out.println(u1.equals(u2));//false
>        
>        String str1 = new String("hello");
>        String str2 = new String("hello");
>        System.out.println(str1 == str2);//false
>        System.out.println(str1.equals(str2));//true
>    }
>}
>````
>
>### equals方法判断过程
>
>* 首先判断地址是否相同
>  * 地址相同，返回`false`
>  * 地址不同，进入下一个判断
>* 判断是否为String类型`anObject instanceof String`。否，返回`false`
>  * 若是String类型，判断长度是否相同。否，返回`false`
>    * 若长度相同，比较元素是否相同。否，返回`false`
>
>* 对于`String` `File` `Date`和包装类等，它们都重写了`Object`类中的`equals()`方法，用于比较两个对象的实体内容是否相等。
>* 而，其他引用类型的数据，只有当堆空间指向同一个实体对象（或同一个引用地址）时，才能返回true值

### equals方法的重写

>在实际开发中，针对于自定义的类，常常会调用equals（）方法进行判断，但Object中提供的equals()方法只能判断其引用地址是否相同，所以，如果要判断两个对象的属性是否相等，就需要重写Object类中的equals（）方法
>
>#### 重写方式
>
>* 手动重写
>
>  ````java
>  public boolean equals(Object obj){
>      if(this == obj){
>          return true;
>      }
>      if(obj instanceof User){
>          User user =(User)obj;//强制转换
>          return this.age == user.age && this.name.equals(user.name);
>      }
>      return false;
>  }
>  ````
>
>* 系统重写
>
>  >ALT + INSERT
>
>  ````java
>    @Override
>      public boolean equals(Object o) {
>          if (this == o) return true;
>          if (o == null || getClass() != o.getClass()) return false;
>          User user = (User) o;
>          return age == user.age && Objects.equals(name, user.name);
>      }
>  ````
>
>  

### equals方法的重点

>区分 == 和 equals( )
>
>###### ==（运算符）
>
>* 适用范围:基本数据类型、引用数据类型
>
>  * 对于基本数据类型，判断数据值是否相等
>
>  ````jAVA
>  int i1 = 10;
>  int i2 = 10;
>  System.out.println(i1 == i2);//true
>  char c1 = 'A';
>  int i3 = 65;
>  System.out.println(c1 == i3); //
>  ````
>
>  * 对于引用数据类型,判断是否指向同一个对象实体
>
>  ````java
>  String str1 = "XPU";
>  String str2 = "XPTU";
>  String str3 = "XPU";
>  System.out.pritnln(str1 == str2); //false
>  System.out.pritnln(str1 == str3); //true
>  ````
>
>###### equals()方法
>
>* 适用范围：引用数据类型
>* 具体使用：对于类来说，重写equals（）方法和不重写equals（）是有很大的区别

### toString()方法

>##### toString()方法的源代码
>
>````java
>public String toString() {
>        return getClass().getName() + "@" + Integer.toHexString(hashCode());
> }
>````
>
>

当使用`System.out.println（）`打印对象引用变量时，就调用了对象的toString()方法

>##### 子类的使用情况说明：
>
>* 自定义的类
>
>  自定义的类，在没有重写Object类的toString()的情况下，默认返回的就是当前对象的地址值。
>
>* String、FIle、Date或包装类
>
>  String、File、Date或包装类，他们都重写了Object类的toString()方法，在调用toString()方法时，返回当前对象的实体内容。
>
>````java
>class Person{
>    String name;
>    public Person(String name){
>        this.name = name;
>    }
>}
>public class Test{
>    public static void main(String[] args) {
>        Person p1 = new Person("TOM");
>        System.out.println(p1); //Person@2f4d3709
>        System.out.println(p1.toString()); //Person@2f4d3709
>
>        String str1 = "abc";
>        System.out.println(str1);  //abc
>        System.out.println(str1.toString());	//abc
>    }
>}
>````
>
>* 习惯上，开发中对于自定义的类在调用toString()方法时，也希望显示其对象的试题内容，而非地址值，这时候，就需要重写Object类中的toString()方法
>
>````java
>//手动手写
>public String toString(){
>    return "Person{"+"name ="+name+", age="+age+"}";
>}
>
>//自动生成
>//ALT +Ins
>    @Override
>    public String toString() {
>        return "Person{" +
>                "name='" + name + '\'' +
>                ", age=" + age +
>                '}';
>    }
>//往往都会将toString()方法重写为输出其引用数据类型中的数据内容，而不是其引用地址
>````
>
>