# Java Enum 枚举类

## 概述

- 枚举类型本质上也是一种类，只不过是这个类的对象是有限的、固定的几个，不能让用户随意创建。

>如果针对于某个类，其实例是确定个数，则推荐将此类声明为枚举类。
>
>如果枚举类的实例只有一个，则可以看作时单例的实现方式。

### 枚举类的实现

>##### JDK5.0之前
>
>````java
>class Season{
>
>    //2.声明当前类的对象的实例对象 使用Final修饰
>    private final String seasonName;  //季节的名称
>    private final String seasonDesc;  //季节的描述
>    //1.0 私有化列表构造器
>
>
>    private Season(String seasonName, String seasonDesc) {
>        this.seasonName = seasonName;
>        this.seasonDesc = seasonDesc;
>    }
>    //3.提供实力变量的get方法
>
>    public String getSeasonName() {
>        return seasonName;
>    }
>
>    public String getSeasonDesc() {
>        return seasonDesc;
>    }
>    //4.创建当前类的实例 
>    public static final Season SPRING = new Season("春天","春暖花开");
>    public static final Season SUMMER = new Season("夏天","夏日炎炎");
>    public static final Season AUTUMN = new Season("秋天","秋高气爽");
>    public static final Season WINTER = new Season("冬天","白雪皑皑");
>    
>    //还可以重写toString方法
>
>}
>````
>
>##### JDK5.0之中
>
>````java
>num Season1{
>
>    //必须在枚举类开头 声明对象 中间使用,隔开(枚举类中需要声明了私有化构造器)
>    SPRING("春天","春暖花开"),
>    SUMMER("夏天","夏日炎炎"),
>    AUTUMN("秋天","秋高气爽"),
>    WINTER("冬天","白雪皑皑");
>    private final String seasonName;    //季节名称(使用final修饰，当作常量，不允许更改)
>    private final String seasonDesc;    //季节描述
>    
>    
>    //提供私有化构造器
>    private Season1(String seasonName,String seasonDesc){
>        this.seasonName = seasonName;
>        this.seasonDesc = seasonDesc;
>    }
>````
>
>

### 细节

* 使用enum关键字定义的枚举类，默认其父类时java.lang.Enum类
* 使用enum关键字定义的枚举类，不要再显示的定义其父类，否则报错

## enum类中的方法

已经定义了enum类

````java
enum Season {
    SPRING("春季","春风又绿江南岸"),
    SUMMER("夏季","映日荷花别样红"),
    AUTUMN("秋季","秋水共长天一色"),
    WINTER("冬季","千里冰封，万里雪飘");
    private final String seasonName;    //季节名称
    private final String seasonDesc;    //季节描述

    private Season(String seasonName, String seasonDesc){
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
}
````



* toString()   默认返回的是常量名（对象名），可以继续手动重写该方法！

  ````java
  public class SeasonTest{
      public static void main(String []args){
          System.out.println(Season.SPRING.toString());	//输出：SPRING
          System.out.println(Season.SUMMER.toString());	//输出：SUMMER
          System.out.println(Season.AUTUMN.toString());	//输出：AUTUMN
          System.out.println(Season.WINTER.toString());	//输出：WINTER
          System.out.println(Season.SPRING); 				//输出：SPRING
          System.out.println(Season.SUMMER);				//输出：SUMMER
          System.out.println(Season.AUTUMN);				//输出: AUTUMN
          System.out.println(Season.WINTER);				//输出：WINTER
      }
  }
  //未重写toSpring()的结果
  String toString(): 默认返回的是常量名（对象名），可以继续手动重写该方法！
  ````

* name()   得到当前枚举常量的名称。建议优先使用toString()。

  ````java
  public class SeasonTest{
      public static void main(String []args){
          System.out.println(Season.SPRING.name());
          System.out.println(Season.SUMMER.name());
          System.out.println(Season.AUTUMN.name());
          System.out.println(Season.WINTER.name());
      }
  }
  //输出结果
  //SPRING
  //SUMMER
  //AUTUMN
  //WINTER
  String name():得到当前枚举常量的名称。建议优先使用toString()。
  ````

* values()

  ````java
  public class SeasonTest{
      public static void main(String []args){
          Season[] values = Season.values();
          for (int i = 0; i < values.length; i++) {
              System.out.println(values[i]);
          }
      }
  }
  //输出结果
  //SPRING
  //SUMMER
  //AUTUMN
  //WINTER
  static 枚举类型[] values():返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值，是一个静态方法
  ````

* valueOf(String objName)

  ````java
  static 枚举类型 valueOf(String name)：可以把一个字符串转为对应的枚举类对象。要求字符串必须是枚举类对象的“名字”。如不是，会有运行时异常：IllegalArgumentException。
      
      
  public class SeasonTest{
      public static void main(String []args){
          String objName = "SPRING";
          Season season1 = Season.valueOf(objName);
          System.out.println(season1);	//SPRING
          Season season2 = Season.valueOf("WINTER");
          System.out.println(season2);	//WINTER
      }
  }
  //若重写了toSpring()方法，则按照重写后的来输出
  ````

* int ordinal（ ）

  ````java
  public class SeasonTest{
      public static void main(String []args){
          System.out.println(Season.SPRING.ordinal());
          System.out.println(Season.SUMMER.ordinal());
          System.out.println(Season.AUTUMN.ordinal());
          System.out.println(Season.WINTER.ordinal());
      }
  }
  //int ordinal():返回当前枚举常量的次序号，默认从0开始
  //输出结果 
  //0
  //1
  //2
  //3
  ````

## 枚举类实现接口操作



* 枚举类实现接口，在枚举类中重写接口中的抽象方法。当通过不同的枚举类对象调用此方法时，执行的是同一个方法

  ````java
  enum Season implements Info{
      SPRING("春天","春暖花开"),
      SUMMER("夏天","烈日炎炎"),
      AUTUMN("秋天","秋高气爽"),
      WINTER("冬天","白雪皑皑");
      private final String seasonName;	//季节名称
      private final String seasonDesc;	//季节描述
      private Season(String seasonName,String seasonDesc){
          this.seasonName = seasonName;
          this.seasonDesc = seasonDesc;
      }
      public String getSeasonName(){
          returtn this.seasonName;
      }
      public String getSeasonDesc(){
          return this.seasonName;
      }
      
      public void show(){
          System.out.println("这是一个季节");
      }
  }
  interface Info{
      void show();
  }
  
  public class SeasonTest{
      public static void main(String []args){
          Season.SPRING.show();
          Season.SUMMER.show();
      }
  }
  //此时，重写方法是在Season类中
  //输出结果:
  // 这是一个季节
  // 这是一个季节
  ````

* 让枚举类的每一个对象都重写接口中的抽象方法，当通过不同的枚举类对象调用此方法时，执行的是不同的方法

  ````java
  interface Info{
      void show();
  }
  
  enum Season implements Info{
      SPRING("春天","春意盎然"){
          public void show(){
              System.out.println("春风又绿江南岸");
          }
      },
      SUMMER("夏天","烈日炎炎"){
          public void show(){
              System.out.println("映日荷花别样红");
          }
      },
      AUTUMN("秋天","秋高气爽"){
          public void show(){
              System.out.println("秋水共长天一色");
          }
      },
      WINTER("冬天","白雪皑皑"){
          public void show(){
              System.out.println("窗含西岭千秋雪");
          }
      };
      private final String seasonName;
      private final String seasonDesc;
      private Season(String seasonName,String seasonDesc){
          this.seasonName = seasonName;
          this.seasonDesc = seasonDesc;
      }
      public String getSeasonName(){
          return this.seasonName;
      }
      public String getSeasonDesc(){
          return this.seasonDesc;
      }
      
  }
  public class SeasonTest{
      public static void main(String []args){
          Season []seasons = Season.values();
          for(int i = 0; i < seasons.length;i++){
              seasons[i].show();
          }
      }
  }
  
  //输出
  春风又绿江南岸
  映日荷花别样红
  秋水共长天一色
  窗含西岭千秋雪
  ````

  ## 练习
  
  >定义Employee类，表示员工信息。
  >
  >定义枚举类Status,表示员工状态
  
  ````java
  class Employee{
      private String name;
      private int age;
      private Status status;
      
      public Employee(){
          
      }
      public Employee(String name,int age,Status status){
          this.name = name;
          this.age = age;
          this.status = status;
      }
      public void setName(String name){
          this.name = name;
      }
      public void setAge(int age){
          this.age = age;
      }
      public void setStatus(Status status){
          this.status = status;
      }
      public String getName(){
          return this.name;
      }     
      public int getAge(){
          return this.age;
      }
      public Status getStatus(){
          return this.status;
      }
          @Override
      public String toString() {
          return "Employee{" +
                  "name='" + name + '\'' +
                  ", age=" + age +
                  ", status=" + status +
                  '}';
      }
  }
  enum Status{
      BUSY,FREE,VOCATION,DIMISSION;
  }
  
  public class EmployeeTest{
      public static void main(String []args){
          Employee e1 = new Employee("Tom",21,Status.BUSY);
          System.out.println(e1.toString());
      }
  }
  //输出结果
  Employee{name='TOM', age=21, status=BUSY}
  ````
  
  >使用枚举类实现单例模式
  
  ````java
  //jdk 5.0之前，使用枚举类实现单例模式
  class Bank{
      private Bank(){}
      public static final Bank instance = new Bank();	//使用final，不能更改instance的内容
  }	
  //jdk5.0 使用enum 枚举类实现单例模式
  class Bank{
      CPB;
  }
  class GirlFriend{
      XIAO_LI(20);
      private final int age;
      private GirlFriend(int age){
          this.age = age;
      }
  }
  ````
  
  >定义颜色枚举类Color
  >
  >声明final修饰的Int类型的属性red,green,blue  即RGB
  >
  >声明final修饰的String类型的属性description
  >
  >声明带参构造器
  >
  >创建七个常量对象（七个颜色）
  >
  >重写toString方法
  
  ````java
  public class ColorTest {
      public static void main(String[] args) {
          Color []colors = Color.values();
          for (int i = 0; i < colors.length; i++) {
              System.out.println(colors[i]);
          }
      }
  }
  enum Color{
      RED(255,0,0,"红色"),
      ORANGE(255,128,0,"橙色"),
      YELLOW(255,255,0,"黄色"),
      GREEN(0,255,0,"绿色"),
      CYAN(0,255,255,"青色"),
      BLUE(0,0,255,"蓝色"),
      PURPLE(128,0,255,"紫色");
      private final int red;
      private final int greed;
      private final int blue;
      private final String description;   //颜色的描述
  
      Color(int red, int greed, int blue, String description) {
          this.red = red;
          this.greed = greed;
          this.blue = blue;
          this.description = description;
      }
  
      public String toString() {
          return super.toString()+"("+red+","+green+","+blue+")"+"——>"+description+")";
          //super.toString()表示父类的toString()方法，即未重写之前的toString方法
      }
      
  }
  //输出效果
  RED(255,0,0)——>红色)
  ORANGE(255,128,0)——>橙色)
  YELLOW(255,255,0)——>黄色)
  GREEN(0,255,0)——>绿色)
  CYAN(0,255,255)——>青色)
  BLUE(0,0,255)——>蓝色)
  PURPLE(128,0,255)——>紫色)
  ````
  
  

