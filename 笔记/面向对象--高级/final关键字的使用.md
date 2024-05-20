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
  class Son extends Eunuch{//错误的写法
  	
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

  * final修饰成员变量:

    > 在那些位置可以给成员变量赋值?
    >
    > * 显示赋值
    >
    > * 代码块中赋值
    >
    > * 构造器中赋值
    >
    > 创建对象时，常量的值就应该设置好，后续不可以通过方法修改其值

    ````java
    class A{
        //成员变量
        final int MIN_SCORE = 0;    //常量
    }
    public class FinalTest {
        public static void main(String[] args) {
            A a = new A();
    //        a.MIN_SCORE = 3; 错误的，MIN_SCORE是一个常量，只能调用不能修改
            System.out.println(a.MIN_SCORE);
        }
    }
    ````

    ````java
    class A{
        final int MIN_SCORE = 0;
        final int MAX_SCORE;
        final int LEFT;
        final int RIGHT;
        {
            MAX_SCORE = 100;	//代码块中赋值
        }
        
        public A(){
            LEFT = 2;	//构造器中赋值
        }
        public A(int right){
            LEFT = right;	//构造器中赋值
        }
    }
    ````

    

  * final修饰局部变量:一旦赋值就不能修改

    >* 方法内部声明的局部变量，在调用局部变量前，一定要赋值，而且一旦赋值，就不可以更改
    >* 方法的形参:在调用此方法时，给形参进行赋值，一旦赋值，就不能够更改

    ````java
    class A{
        public void method(final int num){	//使用final修饰形参
            System.out.println(num);
        }
    }
    ````

    

## final和static的搭配

>final和static修饰成员变量，此成员变量成为全局常量。
>
>全局都可以调用此变量，且变量值不可以更改。