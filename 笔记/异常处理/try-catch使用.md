# Java 异常处理 try-catch

## Java异常处理

Java采用的异常处理机制，是将异常处理的程序代码集中于在一起，与正常的程序代码分开，使得程序简介、优雅、易于维护。

**Java异常处理方式**

* try-catch-finally

* throws + 异常类型

## try-catch-finally(抓抛模型）

### 模型解释

**过程`抛`**

​	程序在执行的过程中，一旦出现异常，就会在出现异常的代码处，生成对应异常类的对象，并将此对象抛出。一旦抛出，此程序就不执行其后的代码了。

**过程`抓`**

​	针对于过程"抛"出的异常对象，进行捕获处理，此捕获处理的过程，就称之为抓。

​	一旦将异常进行了处理，代码就可以继续执行

### 基本结构

````java
try{
	......	//可能产生异常的代码
}
catch( 异常类型1 e ){
	......	//当产生异常类型1型异常时的处置措施
}
catch( 异常类型2 e ){
	...... 	//当产生异常类型2型异常时的处置措施
}  
finally{
	...... //无论是否发生异常，都无条件执行的语句
} 

````

### 使用细节

* 将可能出现异常的代码声明在try语句中。一旦代码出现异常，就会自动生成一个对应异常类的对象，并将此对象抛出。
* 针对于try中抛出的异常类的对象，使用之后的catch语句进行匹配。
* 一旦匹配上，就进入到catch语句块中进行处理。一旦处理结束，代码就可以继续执行。
* try中声明的变量，出了try后，不能继续调用（作用域）

---

* 如果声明了多个catch结构，不同的异常类型在不存在子父类的关系的情况下，声明的位置上下不受影响，但若声明了多个异常类型满足子父类的关系，则必须将子父类结构的上面，否则，编译器会报错

  **可以使用`Ctrl + H` 快捷键 查看继承关系**

  ````java
  package Throwable;
  import org.junit.Test;
  import java.util.InputMismatchException;
  import java.util.Scanner;
  public class ErrorTest{
      public static void main(String []args){
          try{
              Scanner scanner = new Scanner(System.in);
              int num = scanner.nextInt();	//若 输入 不是int类型就会出现异常
              Sytem.out.println(num);
          }catch(InputMismatchException e){
              System.out.println("出现了InputMismatchException异常");
          }catch(NullPointerException e){
              System.out.println("出现了NullPointerException异常");
          }catch(RuntimeException e){
              System.out.println("出现了RuntimeException异常");	//若存RuntimeException和InputMismatchException必须将RuntimeException声明到后面
          }
      }
  }
  ````

**catch中异常处理的方式**

* 自己编写输出语句

* `printStackTrace()` 打印异常的详细信息 (推荐)

  使用`printStackTrace`打印信息，和编译器自动报错的信息一样，但使用此方法之后，程序还可以继续向下运行，异常就被处理掉了

* `getMessage()` 获取发生异常的原因

````java
public class CatchTest{
    public static void main(String []args){
        try{
            String str;
            str = "abc";
            int i = Integer.parseInt(str);	//abc是Int类型
            System.out.println(i);
        }catch(NumberFormatException e){
            e.printStackTrace();	//输出错误信息
            System.out.println(e.getMessage());	//For input string: "acb"
        }
        System.out.println("程序结束");
    }
}
````

**对于运行异常**

开发中，通常不进行显示的处理

一旦在程序执行中，出现了运行时的异常，那么就根据异常的提示信息修改代码即可

**对于编译时异常**

编译时异常，一定要进行处理，否则编译不通过。

### finally的使用

#### finally的理解

* 将一定要被执行的代码生命在finally的结构中

* 无论try中或catch中是否存在仍未被处理的异常，无论try中或catch中是否存在return语句，finally中生命的语句都一定要被执行

  ```java
  public class FinallyTest {
      public static void main(String[] args) {
          int result = test("12");
          System.out.println(result);
      }
      public static int test(String str){
          try{
              Integer.parseInt(str);
              return 1;
          }catch (NumberFormatException e){
              return -1;
          }finally {	//无论try和catch中是否存在异常，finally中的语句都会最终执行
              System.out.println("test结束");
          }
      }
  }
  ```

**若try中不存在异常，也会执行finally中的语句**

````java
public class FinallyTest {
    public static void main(String[] args) {
        int num = test(3);
        System.out.println(num);
    }

    public static int test(int num){
        try{
            return num;
        }catch (NumberFormatException e){
            return num--;
        }finally {
            System.out.println("test结束");
            return ++num;
        }
    }
}
//输出结果
//test结束
// 4
````

**finally语句和catch语句都是可选的，但finally语句不能单独使用**

#### finally什么时候使用

在开发中，有一些资源，例如，输入流、输出流、数据库连接、socket连接资源，在使用完以后，必须显式的进行关闭操作。

否则，GC不会自动回收这些资源，不管是否出现了未被触雷的异常的情况，这些资源都能够被关闭，我们必须将这些操作声明在finally中