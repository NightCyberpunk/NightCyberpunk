# this关键字

## 定义

>- 在Java中，this关键字不算难理解，它的作用和其词义很接近。
>  - 它在方法（准确的说是实例方法或非static的方法）内部使用，表示调用该方法的对象
>  - 它在构造器内部使用，表示该构造器正在初始化的对象。
>
>- this可以调用的结构：成员变量、方法和构造器
>
>- this理解为当前项

## 使用

>* 在声明一个属性所对应的`setXxx`方法时,通过形参给对应的属性赋值。若形参名和属性名同名了，可以使用`this`解决
>
>* 使用`this`修饰的变量，表示的是属性，没有使用`this`修饰的变量，表示的是形参
>
>  ````java
>  public class PersonTest{
>      public static void main(String []args){
>          
>      }
>  }
>  class Person{
>      String name;
>      int age;
>      String email;
>      public Person(){
>          
>      }
>      public Person(String name,String email){
>          this.name = name;
>          this.email = email;
>      } 
>      //this.name表示属性中的name
>      //this.email表示属性中的email
>      public setAge(int age){
>          this.age = age;
>      }
>      public int getAge(){
>          return this.age; //返回成员变量age
>      }
>  }
>  //this的使用，可以将形参和成员变量同名，更好的与之对应，见名知意
>  ````

## 调用

### this调用属性和方法

>---
>
>***针对于方法内的情况(非static修饰)***
>
>>一般情况下，通过对象调用方法，可以继续在方法内调用当前对象的属性或其他方法。
>>
>>可以在属性和其他方法前，使用`this`，表示当前属性或方法所属于的对象
>>
>>一般情况下，省略掉`this`的结构
>>
>>如果，方法的形参与对象的属性同名，必须使用`this`进行区别，`this`修饰的变量即为成员变量,没有用`this`修饰的为局部变量
>
>***针对于构造器内的情况***
>
>````java
>class Person{
>    String name;
>    int age;
>    public Person(String name,int age){
>        this.name = name;
>        this.age = age; //使用this表示成员变量
>    }
>}
>````
>
>
