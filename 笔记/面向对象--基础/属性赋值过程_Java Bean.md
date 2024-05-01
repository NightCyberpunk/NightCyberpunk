## 类中属性赋值过程

## 总述

1.在类的属性中，可以有哪些位置给属性赋值?

* 默认赋值

* 显示赋值

* 构造器中赋值

* 通过`对象.方法`的方法赋值

* 通过`对象.属性`的方法赋值(非private属性)

2.这些位置执行的先后顺序是什么?

>默认初始化  -->  显示初始化 --> 构造器中初始化 -->   `对象.方法`/`对象.属性`赋值

3.以上操作在对象创建过程中可以执行的次数如何?

>只能执行一次 	: `默认赋值` `显示赋值` `构造器中赋值`
>
>可多次执行   	 :通过`对象.方法`/`对象.属性`的方法赋值

## 实例

### 默认赋值

>````java
>class Person{
>    int age;
>    String name;
>}
>public class PersonTest{
>    public static void main(String []args){
>        Person p1 = new Person;
>        System.out.println(p1.age);
>        System.out.println(p1.name);
>    }
>}
>//创建Person p1 对象但不进行赋值
>//输出 0 null
>````
>
>将变量创建但不进行赋值，此时变量有默认赋值
>
>`byte` `short` `int` `long`  类型，默认赋值为`0`
>
>`double` `float` 类型，默认赋值为`0.0`
>
>`String`类型，默认赋值为`null`
>
>`char` 类型，默认赋值为`\u0000` 即空字符

### 显示赋值

>在Java中，显示赋值是指将一个值直接赋给一个变量或者常量
>
>````java
>public class PersonTest{
>    public static void main(String []args){
>        int a = 3;
>    }
>}
>````

### 通过`对象.属性`的方法赋值

>````java
>class Person{
>    int age;
>    String name;
>}
>public class PersonTest{
>    public static void main(String []args){
>        Person p1 = new Person();
>        p1.age = 18;
>        p1.name = "张三";
>    }
>}
>//通过对象.属性的方法进行赋值但对象不允许为私有化变量
>````
>
>***成功运行，age赋值18,name赋值张三***
>
>---
>
>````java
>class Person{
>    int age;
>    private String anme;
>}
>public class PersonTest{
>    public static void main(String []args){
>        Person p1 = new Person();
>        p1.age = 18;
>        p1.name = "张三";
>    }
>}
>//系统报错
>java: name 在 test.User 中是 private 访问控制
>````
>
>----

### 通过`对象.方法`的方法进行赋值

>通过`对象.方法`的方法进行赋值时，此时，可以对`private`私有化的变量进行赋值
>
>````java
>class Person{
>    private int age;
>    public void setAge(int a){
>        age = a;
>    }
>}
>public class PersonTest{
>    public static void main(String []args){
>        Person p1 = new Person();
>        p1.setName(18); //此时，即使age私有化，也可以调用Person类中的方法进行对age赋值
>    }
>}
>````

# Java Bean

*Java Bean，符合以下标准的Java类*

>* 类是公共的
>* 有一个无参的公共构造器
>* 有属性，且有对应的`get`  `set`方法

````JAVA
import java.util.Date;

public class Customer {
    public Customer() {

    }   //无参构造器

    private int id;
    private String name;
    private String email;
    private Date birth;
    public void setId(int i){
        id = i;
    }
    public int getId(){
        return id;
    }
    public void setName(String n){
        name = n;
    }
    public String getName(){
        return name;
    }

}
````

