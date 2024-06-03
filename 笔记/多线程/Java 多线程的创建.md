# Java 多线程的创建

>Java元的JVM允许程序运行多个线程，使用`java.lang.Thread`类代表线程，所有的线程对象都必须是`Thread`类或其子类
>
>线程的创建方式有两种，一者为`继承Thread类`，一者为`实现Runnable接口`

## 实现方式之一：继承Thread类

### 实现步骤

Java通过继承Thread类来**创建**并**启动多线程**的步骤如下：

* 创建一个继承于`Thread`类
* 重写`Thread`类的`run()`  将此线程要执行的操作，生命在此方法体中
* 创建当前`Thread` 的子类的对象
* 通过对象调用`start()` --->1. 启动线程  2.调用当前线程的`run()`方法

>创建一个分线程1，用于遍历100日内的偶数
>
>````java
>class PrintNumber extends Thread{
>
>@Override
>////重写Thread类中的run()方法  ---->将此线程要执行的操作，声明在方法体中
>public void run() {
>   for(int i=0;i<=100;i++){
>       if(i % 2 == 0){
>           System.out.println(i);
>       }
>   }
>}
>}
>
>public class NumberTest{
>public static void main(String []args){
>		//创建当前Thread的子类的对象
>   PrintNumber t1 = new PrintNumber();
>   //通过对象调用start()方法   start是父类Thread中的方法
>   t1.start();
>}
>}
>
>//API中start()的描述
>//Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
>//启动线程，JVM会调用当前线程的run()方法
>````
>
>**分线程会和main()并发进行,改动后**
>
>````java
>public class ThreadTest {
>public static void main(String[] args) {
>   OneThread o1 = new OneThread();
>   o1.start();
>   for(int i = 0; i< 1000000; i++){
>       System.out.println("这是main方法");
>   }
>}
>}
>
>class OneThread extends Thread{
>@Override
>public void run() {
>   for (int i = 0; i < 1000000; i++) {
>       System.out.println("这是OneThread");
>   }
>}
>}
>//当运行此程序时，输出为
>//这是main方法 与 这是OneThread 交叉输出 
>//也可以使用Thread.currentThread().getName()直观的输出 该线程的名称
>````

### 深入思考

#### 问题一

**run()方法已经重写，并且run()方法中是分线程所需要执行的操作，那能否使用run()方法替换start()方法的调用，实现分线程的创建和调用?**

*不能，如果这样使用，则不会创建多线程，就相当于一个普通的方法*

***示例***

```java
public class ThreadTest {
    public static void main(String[] args) {
        OneThread o1 = new OneThread();
        o1.start();
        for(int i = 0; i< 1000000; i++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class OneThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}

//输出结果（节选）
Thread-0
Thread-0
Thread-0
main
main
main
main
```

**更改之后**

````java
public class ThreadTest {
    public static void main(String[] args) {
        OneThread o1 = new OneThread();
        o1.start();
        for(int i = 0; i< 1000000; i++){
            System.out.println(Thread.currentThread().getName());
        }
    }
}

class OneThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
//输出结果（节选）
main
main
main
//此时，全是main方法中的线程，相当于调用了一个普通类的普通方法，并不是多线程
````

#### 问题二

能否在提供一个线程，用于输出偶数？

````java
class PrintNumber{
    public void run(){
        for(int i = 0; i< 100 ;i++){
            if(i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}

public class NumberTest{
    public static void main(String []args){
        PrintNumber p1 = new PrintNumber();
        p1.start();
        p1.start();
    }
}
//输出结果
0 - 100的 偶数（省略）
Exception in thread "main" java.lang.IllegalThreadStateException
	at java.base/java.lang.Thread.start(Thread.java:1525)
	at 多线程.EvenNUmberTest.main(EvenNUmberTest.java:9)
````

不能让已经start()的线程再次执行star()方法，否则报异常。非法的线程状态。

若想要再次提供一个线程，可以再实例化一个对象，用这个对象调用start()方法

#### 问题三

>创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
>
>````java
>public class NumberTest {
>public static void main(String[] args) {
>   EvenNumberPrint t1 = new EvenNumberPrint();
>   OddNumberPrint t2 = new OddNumberPrint();
>   t1.start();
>   t2.start();
>}
>}
>
>class EvenNumberPrint extends Thread{   //用于打印偶数
>@Override
>public void run() {
>   for (int i = 0; i <= 100; i++) {
>       if(i % 2 == 0){
>           System.out.println(Thread.currentThread().getName()+" "+i);
>       }
>   }
>}
>}
>class OddNumberPrint extends Thread{    //用于打印奇数
>@Override
>public void run() {
>   for (int i = 0; i <= 100; i++) {
>       if(i % 2 != 0){
>           System.out.println(Thread.currentThread().getName()+" "+ i);
>       }
>   }
>}
>}
>````
>
>

## 实现方式之一 :实现Runnable接口

### 实现步骤

***以下为实现Runnable接口的具体步骤***

* 创建一个实现Runnable接口的类
* 实现接口中的run()方法 ——> 将此线程要执行的操作，声明在run()方法体中
* 创建当前实现类的对象
* 将此对象作为参数传递到Thread类中的构造器中，创建Thread类的实例
* Thread类的实例调用start（）方法。start的作用 ①启动线程②调用run（）方法

***普通方式***

````java
public class EvenNumberTest {
    public static void main(String[] args) {
        //创建当前类的实例化
        EvenNumberPrint p = new EvenNumberPrint();
        //将此对象作为参数传递到Thread类的构造器中
        Thread t1 = new Thread(p);
        t1.start();
    }
}

class EvenNumberPrint implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() +" "+ i);
        }
    }
}

//输出结果
//
Thread-0 0
Thread-0 1
    .....省略
Thread-0 100
    
````

***使用接口匿名实现类的匿名对象***

````java
//使用实现Runnable接口的方式，提供了Runnable接口匿名实现类的匿名对象
public class EvenNumberTest {
    public static void main(String[] args) {
        new Thread(new Runnable(){
            public void run(){
                for(int i = 0; i < 100 ; i++){
                    System.out.println(Thread.currentThread().getName() + i);
                }
            }
        }).start();
    }
}
````

## 两种实现方式的对比

**共同点**

*  启动线程时，使用的都是`Thread`类中定义的`start()`
* 创建的线程对象，都是`Thread`类或其子类的实例

**不同点**

一个是类的继承，一个是接口的实现

**建议**

建议使用实现`Runnable`接口的实现。

* 可以避免类的单继承的局限性
* 更适合处理有共享数据的问题 
* 实现了代码和数据的分离

**两种方式的联系**

查看`Thread`的源码会发现，`Thread`类 也是实现了`Ruunable`接口

````java
public class Thread implements Runnable {    }
````