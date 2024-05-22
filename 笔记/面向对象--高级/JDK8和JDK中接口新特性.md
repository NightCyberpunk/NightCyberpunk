# Java interface特性细节

* 接口中声明的静态方法只能被接口调用，不能被实现类调用

  ````java
  interface CompareA{
      public static void method1(){
          System.out.println("CompareA.method1()");
      }
  }
  
  class SubClass implements CompareA{
      
  }
  public CompareTest{
      public static void main(String []args){
          CompareA.method1();	//输出 CompareA.method1()
          SubClass.method1();	//报错，接口中声明的静态方法，只能被接口调用，不能被实现方法调用
      }
  }
  ````

* 默认方法(default):接口中声明的默认方法可以被实现类继承

  实现类未重写此方法，默认调用接口中声明的，若实现类重写了此方法，则调用的是实现类中的重写后方法

  ````java
  interface CompareA{
      public default void method(){
          System.out.println("CompareA.method()");
      }
  }
  
  class SubClass implements CompareA{
      
  }
  public CompareTest{
      public static void main(String []args){
          SubClass sub1 = new SubClass();	//此时方法非静态，应该实例化才能调用
          sub1.method();	//输出:CompareA.method();
      }
  }
  ````

* 接口冲突问题：类实现了两个接口，而两个接口定义了同名同参数的默认方法

  在没有重写这两个接口的默认方法的情况下，会引起接口冲突。

  ````java
  interface CompareA{
      public default void method(){
          System.out.println("A.method()");
      }
  }
  
  interface CompareB{
      public default void method(){
          System.out.println("B.method()");
      }
  }
  
  class SubClass implements CompareA,CompareB{
      SubClass sub = new SubClass();
      sub.method();	//报错，接口冲突
  }
  ````

* 类优先原则：

  子类（或实现类） 继承了父类并实现了接口，父类和接口中都声明了同名同参数的方法，其中接口中的方法是default方法，在没有重写此方法的情况下，调用的是父类中的方法。若重写，则会调用自身重写的方法

  ````java
  class SuperClass{
      public void method(){
          System.out.println("SuperClass");
      }
  }
  interface Compare{
      public default void method(){
          System.out.println("Compare")''
      }
  }
  
  class SubClass extends SuperClass implements Compare{
      SubClass sub = new SubClass();
      sub.method();	//输出:SuperClass
  }
  ````

* super的使用,调用父类或接口中的方法（重写的情况下）

  使用`super.方法`调用父类的方法

  使用`方法`调用自身重写的方法

  使用`接口名.super.方法`调用接口中的方法

  ````java
  interface CompareA{
      public default void method1(){
          System.out.println("CompareA.method1");
      }
  }
  
  interface CompareB{
      public default void method1(){
          System.out.println("CompareB.method1");
      }
  }
  class SuperClass{
      public void method2(){
          System.out.println("SuperClass.method2");
      }
  }
  class SubClass extends SuperClass implements CompareA,CompareB{
      @Override
      public void method1() {
          System.out.println("SubClass.method1");
      }
      public void method2(){
          System.out.println("SubClass.method2");
      }
  
      public void method(){
          method1();  //自己的方法
          method2();  //自己的方法
  
          super.method2();    //父类的方法
  
          CompareA.super.method1();   //接口的方法
          CompareB.super.method1();   //接口的方法
      }
  }
  
  class Test{
      public static void main(String[] args) {
          SubClass sub = new SubClass();
          sub.method();
      }
  }
  ````