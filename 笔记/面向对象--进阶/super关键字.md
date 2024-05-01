# super关键字

>子类继承父类以后，对父类的方法进行了重写，那么在子类中，是否还可以调用原父类中的方法?
>
>子类继承父类以后，发现子类和父类中定义了同名的属性，是否可以在子类中区分两个同名的属性呢？
>
>`super`关键字

## super的理解

在Java类中使用super来调用父类中的指定操作：

- super可用于访问父类中定义的属性

- super可用于调用父类中定义的成员方法
- super可用于在子类构造器中调用父类的构造器

注意：

- 尤其当子父类出现同名成员时，可以用super表明调用的是父类中的成员
- super的追溯不仅限于直接父类
- super和this的用法相像，this代表本类对象的引用，super代表父类的内存空间的标识

## super调用属性和方法

>`super.属性`表示父类中的属性
>
>````java
>class Person{
>    int id; //身份证号
>}
>
>class Student extends Person{
>    int id; //学号
>
>    public void show(){
>        System.out.println(id);
>        System.out.println(this.id);
>        System.out.println(super.id);
>    }
>}
>
>public class Main {
>    public static void main(String[] args) {
>        Student s1 = new Student();
>        s1.id = 123456;
>        s1.show();
>    }
>}
>//输出结果:
>//123456
>//123456
>//0
>````
>
>`super.方法`表示父类中的方法
>
>````java
>class Person{
>String name;
>private int age;
>
>public void eat(){
>   System.out.println("Person.eat");
>}
>
>public void sleep(){
>   System.out.println("Person.sleep");
>}
>}
>
>class Student extends Person{
>public void eat(){
>   System.out.println("Student.eat");
>}
>
>public void sleep(){
>   System.out.println("Student.sleep");
>}
>
>public void show(){
>   eat();	//子类中的eat()
>   this.eat();	//子类中的eat()
>   super.eat();	//父类中的eat()
>}
>}
>
>public class Main {
>public static void main(String[] args) {
>   Student s1 = new Student();
>   s1.show();
>}
>}
>//(this.)方法中this一般省略
>//输出结果
>//Student.eat
>//Student.eat
>//Person.eat
>````
>

>**super调用方法**总结：
>
>* **方法前面没有super.和this.**
>  * 先从子类找匹配方法，如果没有，再从直接父类找，再没有，继续往上追溯
>
>* **方法前面有this.**
>  * 先从子类找匹配方法，如果没有，再从直接父类找，再没有，继续往上追溯
>
>* **方法前面有super.**
>  * 从当前子类的直接父类找，如果没有，继续往上追溯

>**super调用变量**总结：起点不同（就近原则）
>
>* **变量前面没有super.和this.**
>  * 在构造器、代码块、方法中如果出现使用某个变量，先查看是否是当前块声明的`局部变量`，
>  * 如果不是局部变量，先从当前执行代码的`本类去找成员变量`
>  * 如果从当前执行代码的本类中没有找到，会往上找`父类声明的成员变量`（权限修饰符允许在子类中访问的）
>
>* **变量前面有this.** 
>  * 通过this找成员变量时，先从当前执行代码的==本类去找成员变量==
>  * 如果从当前执行代码的本类中没有找到，会往上找==父类声明的成员变量（==权限修饰符允许在子类中访问的）
>
>* **变量前面super.** 
>  * 通过super找成员变量，直接从当前执行代码的直接父类去找成员变量（权限修饰符允许在子类中访问的）
>  * 如果直接父类没有，就去父类的父类中找（权限修饰符允许在子类中访问的）
>
>**<font color='red'>特别说明：为了增强可读性，应该避免子类声明和父类重名的成员变量</font>**

## super调用构造器

① 子类继承父类时，不会继承父类的构造器。只能通过“super(形参列表)”的方式调用父类指定的构造器。

````java
class Person{
    int age;
    public Person(){
        System.out.println("Person()构造器");
    }
}
class Student extends Person{
    public Student(){
        super();
    }
}
public class Test{
    public static void main(String []args){
        Student s1 = new Student();
    }
}
//输出结果:Person()构造器
//Student的空参构造器中没有输出语句，但使用了super(),直接继承了父类中的构造器
````

② 规定：“super(形参列表)”，必须声明在构造器的首行。

③ 我们前面讲过，在构造器的首行可以使用"this(形参列表)"，调用本类中重载的构造器，
     结合②，结论：在构造器的首行，"this(形参列表)" 和 "super(形参列表)"只能二选一。

④ 如果在子类构造器的首行既没有显示调用"this(形参列表)"，也没有显式调用"super(形参列表)"，
​     则子类此构造器默认调用"super()"，即调用父类中空参的构造器。

````java
class Person{
    int age;
    public Person(){
        System.out.println("Person()构造器");
    }
}
class Student extends Person{
    	public Student(){}  加不加都可以
    	public Student(int age){
        this.age = age;
    }
}
public class Test{
    public static void main(String []args){
        Student s1 = new Student();
    }
}
//输出结果：Person()构造器
//默认调用父类中的空参构造器
````



⑤ 由③和④得到结论：子类的任何一个构造器中，==要么会调用本类中重载的构造器，要么会调用父类的构造器==。
     只能是这两种情况之一。

⑥ 由⑤得到：一个类中声明有n个构造器，最多有n-1个构造器中使用了"this(形参列表)"，则剩下的那个一定使用"super(形参列表)"。

> 开发中常见错误：
>
> 如果子类构造器中既未显式调用父类或本类的构造器，且父类中又没有空参的构造器，则`编译出错`。

***在通过子类的构造器创建对象时，一定在调用子类构造器的过程中，直接或间接的调用到父类的构造器，也正因为调用过父类的构造器，才会将父类中声明的属性或方法加载到内存中，供子类对象使用。***
