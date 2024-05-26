# Java JUnit 单元测试

**测试分类**

* 黑盒测试

  不需要写代码，给输入值，看程序是否能够输出期望的值

* 白盒测试

  需要写代码，关注程序具体的执行流程

**Junit单元测试**

JUnit 是由 Erich Gamma 和 Kent Beck 编写的一个测试框架（regression testing framework），供Java开发人员编写单元测试之用。

JUnit是程序员测试，是白盒测试。

>在一个Java程序中，往往有若干个方法，需要保证每一个方法都是正确的，但单一进行测试只能在main方法中进行，于是可以使用JUnit测试，对每一个分别单一进行测试，保证程序的正常运行。

**需要导入的jar包（导入步骤略）**

* juni-4.21.jar
* hamcrest-core-1.3.jar

也可以直接使用@Test,系统报错，在联网的情况下可以使用IDEA进行下载(Alt+Enter)

## 使用方法

````java
import org.junit.Test;
public class JUnitTest {
    @Test
    public void test1(){
        System.out.println("hello");
    }
    @Test
    public void test2(){
        System.out.println(
                "hello2"
        );  
    }
}
//Test是导入包中的注解
````

**限制条件**

* 所在的类必须是public的，非`abstract`的，包含唯一的无参构造器(不写自动声明)

* @Test标记的方法本身必须是public、非抽象的、非静态的、void返回值，()无参数的

  若存在返回值、参数，但使用@Test进行测试，没有办法进行传参，则无法使用

## Scanner问题

默认条件下，单元测试方法中，使用Scanner输入失效

````java
@Test
public void test(){
    Scanner scan = new Scanner(System.in);
    System.out.println("请输入一个数值：");
    int num = scan.nextInt();
    System.out.println(num);
}
//程序会运行，但无法输入数值
````

**解决方案**

在`idea64.exe.vmoptions配置文件`中加入下面一行设置，重启idea后生效。

路径`Help ——> Edit Custom VM Options`文件中添加

````properties
-Deditable.java.test.console=true
````



