# final关键字

## final的理解

final，最终的

## final的使用

>final可以用来修饰的结构：类、方法、变量

* #### final修饰类

  表示这个类不能被继承，没有子类。提高安全性，提高程序的可读性。例如：String类、System类、StringBuffer类

  ````java
  final class Eunuch{//太监类
  	
  }
  class Son extends Eunuch{//错误
  	
  }
  ````

* #### final修饰方法

  表示这个方法，不能够被重写。例如，Object类中的getClass( )

  ````java
  class A{
      public final void method(){}
      
  }
  class B extends A{
      public void method(){
          
      }
  }
  //错误的写法
  ````

* #### final修饰变量

  final修饰某个变量（成员变量或局部变量），==一旦赋值，它的值就不能被修改==，即常量，常量名建议使用大写字母。

  final既可以修饰成员变量，也可以修饰局部变量。

  * final修饰成员变量