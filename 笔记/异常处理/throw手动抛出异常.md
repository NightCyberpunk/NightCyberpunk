# Java 手动抛出异常 throw

**Java 中异常对象的生成有两种方式：**

- 由虚拟机**自动生成**：程序运行过程中，虚拟机检测到程序发生了问题，那么针对当前代码，就会在后台自动创建一个对应异常类的实例对象并抛出。

- 由开发人员**手动创建**：`new 异常类型([实参列表]);`，如果创建好的异常对象不抛出对程序没有任何影响，和创建一个普通对象一样，但是一旦throw抛出，就会对程序运行产生影响了。

## 使用格式

````java
throw new 异常类名(参数);
````

## 为什么还需要手动抛出异常？

​	*在实际开发中，如果出现不满足具体场景的代码问题，我们就有必要手动抛出一个指定类型的异常对象*

>例如，在Student类中，有属性id,实际开发需求，希望id是正数，而不是负数，可以通过手动抛出异常的情况处理

````java
public class ThrowTset{
    public static void mian(String []args){
        Student s1 = new Student();
        s1.register(-10);	//id 不是正数
    }
}
class Student{
    int id;
    public void register(int id){
        if(id > 0){
            this.id = id;
        }else{
            //手动抛出异常
            throw new RuntimeException("输入非法id");
        }
    }
}
//运行结果
//Exception in thread "main" java.lang.RuntimeException: 输入的id非法
//	at Throwable.Student.register(ThrowTest.java:17)
//	at Throwable.ThrowTest.main(ThrowTest.java:6)
````

## 手动和自动的理解

>**抓抛模型**
>
>* 抛
>
>  ***自动抛***：程序在执行的过程中,一旦出现异常，就会在出现异常的代码处，生成对应异常类的对象，并将此对象抛出，一旦抛出异常，此程序就不执行其后的代码了
>
>  ***手动抛***：程序在执行过程当中，不满足指定条件的请胯下，我们主动使用`throw + 异常类的对象`方式抛出异常对象  
>
>* 抓
>
>  ***狭义上讲***，try-catch的方式捕获异常，并处理
>
>  ***广义上讲***，把抓理解为处理，则此时有两种处理异常的方式，`try-catch-finally`和`throws`

## throw和throws的区别

* 从形式来讲

​	throws是使用在方法的声明处，将产生的异常向上抛出

​	throw 使用在方法内部，表示手动抛出一个异常类的对象

* 从解决问题上来讲

​	throw是产生一个异常对象

​	throws是针对于异常对象，做出一个解决异常的方案