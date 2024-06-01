# 自定义异常

>**自定义异常的必要**
>
>Java中不同的异常类，分别表示着某一种具体的异常情况。那么在开发中总是有些异常情况是核心类库中没有定义好的，此时我们需要根据自己业务的异常情况来定义异常类。

## 自定义异常类

>自定义异常类可以参照`lang包`中系统提供的异常类型，`Exception`、`Throwable `等

* 自定义的异常类要继承一个现有的异常体系

  * 编译时异常，自定义类继承`java.lang.Exception`
  * 运行时异常，自定义类继承`java.lang.RuntimeException`

* 提供多个构造器，构造器构成重载

* 自定义异常需要提供`serialVersionUID`     

  ````java
  static final long serialVersionUID = -3387516993124229948L;	//Exception
  static final long serialVersionUID = -7034897190745766939L;	//RuntimeException
  static final long serialVersionUID = 7818375828146090155L;	//IOException
  static final long serialVersionUID = -2848938806368998894L;	//NumberFormatException
  //每一个异常类的 serialVersionUID ,序列版本号
  ````


***自定义异常类的示例***

>提供一个小于0  的异常
>
>定义`Student`类，类中有属性`id`,有方法`getId` `setId`
>
>实例化Student，输入数值，若非负，则调用`setId`赋值；若为负，则显示异常

````JAVA
class BelowZeroException extends Exception{
	static final long serialVersionUID = -33875163124229948L;		//序列版本号
    public BelowZeroException (){
        
    }
    public BelowZeroException(String name){
        super(name);
    }
    
    puubilc BelowZeroException(String message,Throwable cause){
        super(message,cause);
    }
}

}public class StudentTest {
    public static void main(String[] args) throws BelowZeroException {
        Student s1 = new Student();
        int num;
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();
        if(num > 0){
            s1.setId(num);
        }else{
            throw new BelowZeroException("输入非法id");
        }

        System.out.println("此学生的id为  " + s1.getId());
    }
}
class Student{
    private int id;
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}

````

***优化后***

````java
package Throwable;

import java.util.Scanner;

class BelowZeroException extends Exception{
	static final long serialVersionUID = -33875163124229948L;		//序列版本号
    public BelowZeroException (){
        
    }
    public BelowZeroException(String name){
        super(name);
    }
    
    puubilc BelowZeroException(String message,Throwable cause){
        super(message,cause);
    }
}
class Student{
    private int id;

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }
    public void register(int id) throws BelowZeroException {
        if(id > 0){
            this.id = id;
        }else{
            throw new BelowZeroException("输入非法ID");
        }
    }
}
public class StudentTest {
    public static void main(String[] args) {
        Student s1 =new Student();
        Scanner scanner = new Scanner(System.in);
        try{
            int num = scanner.nextInt();
            s1.register(num);
        } catch (BelowZeroException e) {
            throw new RuntimeException(e);
        }
        System.out.println("此学生的ID为 "+ s1.getId());
    }
}
````



## 自定义异常类的使用

>在具体的代码中，满足指定条件的情况下，需要手动使用`throw + 自定义异常类的对象` 将异常对象抛出
>
>如果自定义异常类时非运行时异常，则必须要考虑如何处理此异常类的对象（使用`try-catch-finally`或`throws`处理）

## 为什么需要自定义异常类

​	自定义异常类可以通过异常的名称，就可以直接判断异常出现的原因，尽管如此，有必要在实际开发场景中，不满足我们指定的条件时，指明我们自己特有的异常类，通过此异常类的名称，就能判读出具体出现的问题

## 程序示例

### 示例一

````java
public class ReturnExceptionDemo {
    static void methodA() throws Exception{
        try {
            System.out.println("进入方法A");
            throw new Exception("制造异常");
        }finally {
            System.out.println("使用A方法中的finally");
        }
    }
    static void methodB(){
        try {
            System.out.println("进入方法B");
            return;
        }finally {
            System.out.println("调用B方法中的finally");
        }
    }
    public static void main(String []args){
        try{
            methodA();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        methodB();
    }
}
//输出结果
进入方法A
使用A方法中的finally
制造异常
进入方法B
调用B方法中的finally
    
  
````

### 示例二

>需求
>
>在一款角色扮演游戏中，每个人都会有名字和生命值，角色的生命值不能为负数
>
>当生命值为负数时要抛出自定义异常
>
>**自定义异常类`NoLifeValueException`**
>
>````java
>//自定义异常类NoLifeValueExcpeiton 继承 RuntimeException
>//空参构造器 和 带参构造器
>public class NoLifeValueException extends RuntimeException{
>    private static final long serialVersionUID = -1111111111L;
>    public NoLifeValueException(){
>
>    }
>    public NoLifeValueException(String message){
>        super(message);
>    }
>}
>````
>
>**定义类`Person`**
>
>````java
>//定义Person类
>//属性： 名称和生命值
>//提供getter setter方法
>//提供空参构造器 带参构造器
>public class Person {
>    private String name;
>    private int lifeValue;
>
>    public Person() {
>    }
>
>    public Person(String name, int lifeValue) {
>        this.name = name;
>        setLifeValue(lifeValue);    //在构造器中调用setLifeValue方法
>    }
>
>    public String getName() {
>        return name;
>    }
>
>    public void setName(String name) {
>        this.name = name;
>    }
>
>    public int getLifeValue() {
>        return lifeValue;
>    }
>
>    public void setLifeValue(int lifeValue) {
>        if(lifeValue > 0){
>            this.lifeValue = lifeValue;
>        }else{
>            throw new NoLifeValueException("生命值不能为负数" + lifeValue);
>        }
>    }
>}
>````
>
>**`main`入口**
>
>````java
>public class PersonTest {
>    public static void main(String[] args) {
>        Person p1 = new Person("Steve",100);
>        System.out.println("名称："+ p1.getName()+" 生命值："+ p1.getLifeValue());
>
>        Person p2 = new Person("张三",-20);
>    }
>}
>//输出结果
>名称：Steve 生命值：100
>Exception in thread "main" Throwable.NoLifeValueException: 生命值不能为负数-20
>	at Throwable.Person.setLifeValue(Person.java:31)
>	at Throwable.Person.<init>(Person.java:12)
>	at Throwable.PersonTest.main(PersonTest.java:8)
>````

### 示例三

>**需求**
>
>编写应用程序DicisionDemo.java 接受命令行的两个参数，要求不能输入负数，计算两数相除
>
>对数据类型不一致（Number Format Exception） 、缺少命令行参数（ArrayIndexOutOfException）、除0(ArithmeticException)以及输入负数(BelowZeroException)进行异常处理
>
>**提示**
>
>* 在主类(DivisionDemo)中定义异常方法(divide)完成两数相除的功能
>* 在main（）方法中调用divide方法，使用异常处理语句进行异常处理
>* 在程序中，自定义对应输入负数的异常类(BelowZeroException)
>* 与形式接受参数 java DicisionDemo 20 10  //args[0] ="20" args[1]="10"
>* Interger类的static方法parseInt(String s)将s转换对应的Int值 
>
>编写`DicisionDemo `主程序 
>
>````java
>public class DivisionDemo {
>    public static void main(String[] args) {
>        try {
>            int m = Integer.parseInt(args[0]);
>            int n = Integer.parseInt(args[1]);
>
>            int result =divide(m,n);
>            System.out.println("结果为 :" + result);
>        } catch (BelowZeroException e) {
>            System.out.println(e.getMessage());
>        }catch (NumberFormatException e){
>            System.out.println("数据类型不一致");
>        }catch (ArrayIndexOutOfBoundsException e){
>            System.out.println("缺少命令行参数");
>        }catch(ArithmeticException e){
>            System.out.println("非法除0");
>        } 
>    }
>
>    public static int divide (int m ,int n) throws BelowZeroException{
>        if(m < 0 || n < 0){
>            throw new BelowZeroException("输入负数，非法输入");
>        }
>        return m / n;
>    }
>}
>````
>
>自定义类`BelowZeroException`类
>
>````java
>public class BelowZeroException{
>    public BelowZeroException(){
>        
>    }
>    public BelowZeroException(String message){
>        super(message);
>    }
>}
>````
