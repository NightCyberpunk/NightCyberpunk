# Java Annotation 注解类

## 注解概述

注解（Annotation）是从`JDK5.0`开始引入，以“`@注解名`”在代码中存在。例如：

```java
@Override
@Deprecated
@SuppressWarnings(value=”unchecked”)
注解可以在类编译、运行时进行加载，体现不同的功能
```

#### 注解与注释的区别

注解也可以看做是一种注释，通过使用 Annotation，程序员可以在不改变原有逻辑的情况下，在源文件中嵌入一些补充信息。但是，注解，不同于单行注释和多行注释。

- 对于单行注释和多行注释是给程序员看的。(编译时，注释会被过滤，字节码文件中不存在注释)
- 而注解是可以被编译器或其他程序读取的。程序还可以根据注解的不同，做出相应的处理。

## 常见的Annotation作用

#### 生成文档注释

````java
@author 标明开发该类模块的作者，多个作者之间使用,分割
@version 标明该类模块的版本
@see 参考转向，也就是相关主题
@since 从哪个版本开始增加的
@param 对方法中某参数的说明，如果没有参数就不能写
@return 对方法返回值的说明，如果方法的返回值类型是void就不能写
@exception 对方法可能抛出的异常进行说明 ，如果方法没有用throws显式抛出的异常就不能写
````

#### 在编译时进行格式检查

`@Override`: 限定重写父类方法，该注解只能用于方法

`@Deprecated`: 用于表示所修饰的元素(类，方法等)已过时。通常是因为所修饰的结构危险或存在更好的选择

`@SuppressWarnings`: 抑制编译器警告

#### 跟踪代码依赖性，实现替代配置文件功能

## Java基础涉及到三个常用注解

#### @Override注解

@override 限定重写父类方法，**该注解只能用于方法**

````java
class Person{
    public void eat(){
        System.out.println("Person.eat");
    }
}
class Student extends Person{
    @Override
    public void eat(){
        System.out.println("Student.eat");	
    }
}
````

#### @Deprecated

**@Deprecated 用于表示所修饰的元素（类，方法等）已过时。通常是因为修饰的结构危险或者存在更好的选择**

````java
在Date的源码中:
    @Deprecated
    public Date(int year, int month, int date) {
        this(year, month, date, 0, 0, 0);
    }
//表示已经过时，不推荐使用,但仍可以使用
````

也可以自己使用@Deprecated注释

````java
class Person{
    String name;
    @Deprecated
    Person(String name){
        this.name = name;
    }
}
class PersonTest{
    public static void main(String []args){
        Person p1 = new Person("张三");
    }
}
````

#### @SupperessWarnings

@SuppressWarnings 抑制编译器警告

````java
class Test{
    public static void mian(String []args){
        @SuppressWarnings("unused") int num = 10;//抑制编译器警告
    }
}
````

## 自定义注解

以@SuppressWarnings为参照，进行定义即可

>框架 = 注释 + 反射 + 设计模式

## 元注解

**元注解：对现有的注解进行解释说明的注解**

>拓展：元数据
>
>`String name = "Tom"`
>
>String 和 name可以看作"Tom"的元数据

**重要的元注解**

* @Target 

  **用于描述注解的使用范围**

  可以通过枚举类型ElementType的10个常量对象来指定

  例如,TYPE，METHOD，CONSTRUCTOR，PACKAGE.....

  ````java
  public enum ElementType {
      TYPE,
      FIELD,
      METHOD,
      PARAMETER,
      CONSTRUCTOR,
      LOCAL_VARIABLE,
      ANNOTATION_TYPE,
      PACKAGE,
      TYPE_PARAMETER,
      TYPE_USE,
      MODULE,
      RECORD_COMPONENT;
  }
  ````

* @Retention

  **用于描述注解的生命周期**

  可以通过枚举类型RetentionPolicy的3个常量对象来指定

  SOURCE（源代码）、CLASS（字节码）、RUNTIME（运行时）

  `唯有RUNTIME阶段才能被反射读取到`。

  ````java
  public enum RetentionPolicy {
      SOURCE,
      CLASS,
      RUNTIME
  }
  ````

* @Documented

  **表明这个注解应该被 javadoc工具记录****

* @Inherited

  **允许子类继承父类中的注解。**
