# String用法考点

### String类的理解

>***`String`的源码***以`jdk8.0`为例说明
>
>````java
>public final class String
>    implements java.io.Serializable, Comparable<String>, CharSequence,
>               Constable, ConstantDesc {
>    @Stable
>    private final byte[] value;
>    private final byte coder;
>    private boolean hashIsZero; // Default to false;
>    @java.io.Serial
>    private static final long serialVersionUID = -6849794470754667710L;
>    static final boolean COMPACT_STRINGS;
>    static {
>        COMPACT_STRINGS = true;
>    }
>    @java.io.Serial
>    private static final ObjectStreamField[] serialPersistentFields =
>        new ObjectStreamField[0];
>    public String() {
>        this.value = "".value;
>        this.coder = "".coder;
>    }
>    @IntrinsicCandidate
>    public String(String original) {
>        this.value = original.value;
>        this.coder = original.coder;
>        this.hash = original.hash;
>        this.hashIsZero = original.hashIsZero;
>    }xxxxxxxxxx public final class String    implements java.io.Serializable, Comparable<String>, CharSequence,               Constable, ConstantDesc {   
>````

### 类的声明

````java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence,
               Constable, ConstantDesc
````

* `final` 表示`String`是不可被继承的
* `Serializable` 表示可序列化的接口。凡是实现此接口的类的对象就可以通过网络或本地流进行数据的传输
* `Comparable` 实现此接口，表示`String`可以比较大小

### 内部声明的属性

````java
private final char value[];	//存储字符串数据的容器(jdk 8.0)
private final byte[] value;	//存储字符串数据的容器(jdk9以后)
//为了节省内存空间，做了优化.
````

* `final` 指明此value数组一旦初始化，其地址就不可改变

### 字符串常量的存储位置

* 字符串常量都存储在字符串常量池(`StringTable`)中
* 字符串常量池不允许存放两个相同内容的字符串常量

````java
public class StringDemo {
    public static void main(String[] args) {
        String s1 = "hello";	//
        String s2 = "hello";
        System.out.println(s1 == s2);   //true
    }
}
````

* 字符串常量池，在不同的`jdk`版本中，存放位置不同
  * `jdk7`前，字符串常量池存放在方法区中。
  * `jdk7`及以后，字符串常量池存放在堆空间中。

### String不可变性的理解

* 当对字符串变量进行重新赋值时，需要重新指定一个字符串常量的位置进行赋值，不能在原有的位置上修改
* 当对现有的字符串进行拼接操作时，需要重新开辟空间保存拼接以后的字符串，不能再原有的位置上进行修改
* 当调用字符串的`replace()`替换现有的某个字符时，需要重新开辟空间保存修改以后的字符串，不能再原有位置上修改

````java
public class StringDemo {
    @Test
    public void test1(){
        String s1 = "hello";    //字面量的定义方式
        String s2 = "hello";    //内容相同的字符串
        System.out.println(s1 == s2);   //true
        // 内存中只有一个"hello"对象被创建，同时被s1和s2共享。
    }
    @Test
    public void test2(){
        String s1 = "hello";
        String s2 = "hello";
        s2 = "hi";
        System.out.println(s1); //hello
    }
    @Test
    public void test3(){
        String s1 = "hello";
        String s2 = "hello";
        s2 +="World";
        System.out.println(s1); //hello
        System.out.println(s2); //helloWorld
    }
    @Test
    public void test4(){
        String s1 = "hello";
        String s2 = "hello";
        String s3 = s2.replace('l','w');

        System.out.println(s1); //hello
        System.out.println(s2); //hello
        System.out.println(s3); //hewwo
    }
}
````

### String实例化的两种方式

````java
String s1 = "内容";
String s1 = new String("内容");
//String s1 = new String("hello"); 在内存中创建了几个对象？
//两个。一个是堆空间中new的对象，另一个实在字符串常量池中的字面量
````

````java
public class StringDemo{
    public static void main(String []args){
        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");
        String s4 = new String("hello");
        
        
        System.out.println(s1 == s2);   //true
        System.out.println(s1 == s3);   //false
        System.out.println(s1 == s4);   //false
        System.out.println(s3 == s4);   //false
        System.out.println(s1.equals(s3));  //true
    }
}
````

### String的连接操作:`+`

拼接操作`+`下运算出来结果的字符串，若其字符串内容相同，地址是否相同？

>**有以下几种情况:**
>
>````java
>情况1 常量 + 常量 ： 结果仍然存储在字符串常量池中
>情况2 常量 + 变量 (变量 + 常量) ： 都会通过new的方式创建一个新的字符串，返回会堆空间中此字符串对象的地址
>情况3 调用字符串的inter()方法 ： 返回的是字符串常量池中字面量的地址
>情况4 使用concat()方法 ， 通过new的方法创建一个新的字符串，返回堆空间中此字符串对象的地址
>````

````java
public class StringDemo{
	@Test
    public void test3(){
        String s1 = "hello";
        String s2 = "world";
        String s3 = "helloworld";
        String s4 = "hello"+"world";

        String s5 = s1+"wrold";		//通过查看字节码文件发现调用了StringBuilder的toString() 表示重新new String()了
        String s6 = "hello"+s2;
        String s7 = s1+s2;

        System.out.println(s3 == s4);   //true
        System.out.println(s3 == s5);   //false
        System.out.println(s3 == s6);   //false
        System.out.println(s3 == s7);   //false
        System.out.println(s5 == s6);   //false
        System.out.println(s5 == s7);   //false
    }
}
````

````java
//使用concat()方法
public class StringDemo{
    public static void main(String []args){
        String s1 = "hello";
        String s2 = "world";

        String s3 = s1.concat(s2);
        String s4 = "hello".concat("world");
        String s5 = s1.concat("world");

        System.out.println(s3 == s4);	//false
        System.out.println(s3 == s5);	//false
        System.out.println(s4 == s5);	//false
    }
}

//使用此方法，不论是常量、变量调用，调用玩concat()方法，都会返回一个新的new对象
````

### String 的构造器和常用方法

#### String的构造器

* `public String() ` ：初始化新创建的 String对象，以使其表示空字符序列。
* ` String(String original)`： 初始化一个新创建的 `String` 对象，使其表示一个与参数相同的字符序列；换句话说，新创建的字符串是该参数字符串的副本。
* `public String(char[] value) ` ：通过当前参数中的字符数组来构造新的String。
* `public String(char[] value,int offset, int count) ` ：通过字符数组的一部分来构造新的String。
* `public String(byte[] bytes) ` ：通过使用平台的**默认字符集**解码当前参数中的字节数组来构造新的String。
* `public String(byte[] bytes,String charsetName) ` ：通过使用指定的字符集解码当前参数中的字节数组来构造新的String。

````java
//字面量定义方式：字符串常量对象
String str = "hello";

//构造器定义方式：无参构造
String str1 = new String();

//构造器定义方式：创建"hello"字符串常量的副本
String str2 = new String("hello");

//构造器定义方式：通过字符数组构造
char chars[] = {'a', 'b', 'c','d','e'};     
String str3 = new String(chars);
String str4 = new String(chars,0,3);

//构造器定义方式：通过字节数组构造
byte bytes[] = {97, 98, 99 };     
String str5 = new String(bytes);
String str6 = new String(bytes,"GBK");
````

#### String方法

##### 常用方法

````java
（1）boolean isEmpty()：字符串是否为空
（2）int length()：返回字符串的长度
（3）String concat(xx)：拼接
（4）boolean equals(Object obj)：比较字符串是否相等，区分大小写
（5）boolean equalsIgnoreCase(Object obj)：比较字符串是否相等，不区分大小写
（6）int compareTo(String other)：比较字符串大小，区分大小写，按照Unicode编码值比较大小
（7）int compareToIgnoreCase(String other)：比较字符串大小，不区分大小写
（8）String toLowerCase()：将字符串中大写字母转为小写
（9）String toUpperCase()：将字符串中小写字母转为大写
（10）String trim()：去掉字符串前后空白符
（11）public String intern()：结果在常量池中共享
````

* `boolean isEmpty()` ：判断字符串是否为空

  ````java
  public class StringMethod{
      public static void main(String []args){
          String s1 = "";
          String s2 = new String();
          String s3 = new String("");
          System.out.println(s1.isEmpty());
          System.out.println(s2.isEmpty());
          System.out.println(s3.isEmpty());
      }
  }
  //输出 
  //true
  //true
  //true
  ````

* `int length()` 返回字符串的长度

  ````java
  public class StringMethod{
      public static void main(String []args){
  		String s1 = "hello";
          String s2 = "";
          System.out.println(s1.length());
          System.out.println(s2.length());
      }
  }
  //输出结果
  // 5
  // 0
  ````

* `String concat(Xx)` 拼接

* `boolean equals(Object obj)` 比较字符串是否相等， 区分大小写

* `boolean equalsIgnoreCase(Object obj)` 比较字符串是否相等，忽略大小写

  ````java
  public class StringDemo{
      public static void main(String []args){
          String s1 = "abc";
          String s2 = "ABC";
          String s3 = "abc";
          System.out.println(s1.equals(s2));	//false
          System.out.println(s1.equals(s3));	//true
          //忽略大小写
  	    System.out.println(s1.equalsIgnoreCase(s2)); 	//true
          System.out.println(s1.equalsIgnoreCase(s3));	//true
      }
  }
  //输出结果
  //false
  //true
  //true
  //true
  ````

* `int compareTo(String other)` 比较字符串大小，区分大小写，按照`Unicode`编码值进行比较

* `int compareToIgnoreCase(String other)` 比较字符串大小，不区分大小写

  ````java
  public class StringDemo{
      public static void main(String []args){
          //compareTo 比较数值，按照Unicode进行比较
          String s1 = "abcd";
          String s2 = "abed";
          System.out.println(s1.compareTo(s2));	//-2  表示后者比前者大
          String s3 = "abc";
          String s4 = "abc";
          System.out.println(s3.compareTo(s4));	//0 表示相等
      }
  }
  //输出结果 -2
  // 0
  //若使用汉字进行方法的使用，则会按照汉字所对应的码值进行做差比较
  ````

* `String toLowerCase()` 将字符串中大写字母转化为小写

* `String toUpperCase()` 将字符串中小写字母转化为大写

  ````java
  public class StringTest{
      public static void main(String []args){
          String s1= "abc";
          System.out.println(s1.toUpperCase());	//ABC
          String s2 = s1.toUpperCase();	
          System.out.println(s2.toLowerCase());	//abc
      }
  }
  ````

* `String trim()`去掉字符串前后空白符

  ````java
  public class StringTest{
      public static void main(String []args){\
          String s1 = "       hell   o      ";
          System.out.println(s1);
          System.out.println(s1.trim());
      }
  }
  //输出结果
  //      hell   o      
  //hell   o
  //将字符串前后的空白符去掉，但中间的空白字符不去掉
  ````

* `public String intern()` 结果在字符串常量池中共享

##### 查找方法

````java
（11）boolean contains(xx)：是否包含xx
（12）int indexOf(xx)：从前往后找当前字符串中xx，即如果有返回第一次出现的下标，要是没有返回-1
（13）int indexOf(String str, int fromIndex)：返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始
（14）int lastIndexOf(xx)：从后往前找当前字符串中xx，即如果有返回最后一次出现的下标，要是没有返回-1
（15）int lastIndexOf(String str, int fromIndex)：返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索。
````

* `boolean contains(xx)` 判断是否包含xx

  ````java
  public class StringTest{
      public static void main(String []args){
          String s1 = "我爱中华";
          System.out.println(s1.contains("中华"));  //true
          System.out.println(s1.contains("1"));   //false
      }
  }
  ````

* `int indexOf(xx)`   从前往后找当前字符串中xx,如果存在则返回第一次出现的下表，否则返回-1

* `int lastIndexOf(xx)` 从后往前查找当前字符`xx` ,若存在则返回最后一次出现的下标，否则返回-1

* `int indexOf(String str,int fromIndex)`  返回指定子字符串`String str`在此字符串中第一次出现的索引，从指定的索引`fromIndex`开始

* `int lastIndexOf(String str,int fromIndex)` 返回指定子字符串`String str`在此字符串中最后一次出现的索引，从指定索引`fromIndex`开始

  ````java
  public class StringTest{
      public static void main(String []args){
          String s1 = "爱我中华";
          System.out.println(s1.indexOf("中")); //2
          System.out.println(s1.indexOf("中华"));	//2
          System.out.println(s1.indexOf("1"));	//-1	//未在字符串中查找到内容"1",则返回-1
      }
  }
  ````

##### 字符串的截取

````java
（16）String substring(int beginIndex) ：返回一个新的字符串，它是此字符串的从beginIndex开始截取到最后的一个子字符串。 
（17）String substring(int beginIndex, int endIndex) ：返回一个新字符串，它是此字符串从beginIndex开始截取到endIndex(不包含)的一个子字符串。 
````

* `String substring(int beginIndex)` 返回一个新的字符串，从`beginIndex`开始截取到最后一个字符串

* `String substring(in beginIndex,int endIndex)` 返回一个新的字符串，从`beginIndex`到`endIndex`进行截取（不包含`endindex`）

  ````java
  public class StringTest{
      public static void main(String []args){
          String s1 = "abcdef";
          System.out.println(s1.substring(2));    //cdef
          System.out.println(s1.substring(2, 5)); //cde
      }
  }
  ````

##### 和字符/字符数组相关

````java
（18）char charAt(index)：返回[index]位置的字符
（19）char[] toCharArray()： 将此字符串转换为一个新的字符数组返回
（20）static String valueOf(char[] data)  ：返回指定数组中表示该字符序列的 String
（21）static String valueOf(char[] data, int offset, int count) ： 返回指定数组中表示该字符序列的 String
（22）static String copyValueOf(char[] data)： 返回指定数组中表示该字符序列的 String
（23）static String copyValueOf(char[] data, int offset, int count)：返回指定数组中表示该字符序列的 String
````

* `char charAt(index)` 返回`index`位置的字符

  ````java
  public class StringTest{
      public static void main(String []args){
          String s1 = "Java之父——詹姆斯高斯林";
          System.out.println(s1.charAt(0));	//J
          System.out.println(s1.charAt(1));	//a
          System.out.println(s1.charAt(2));	//v
      }
  }
  ````

  

* `char[] toCharArray()` 将此字符串转换为一个新的字符数组并返回

* `static String valueOf(char[] data)` 返回指定数组中表示该字符序列的`String `

* `static String valueOf(char[] data ,int offset, int count )` 

### String与常见的其他结构之间的转换

#### String 与基本数据类型、包装类之间的转换

String可以和其他数据类型和包装类进行转换。

##### String、基本数据类型之间的转换

* `String` ——> 基本数据类型 :调用包装类的`parseXxx(String str)`方法

````java
public class StringTest{
    public static void main(String[] args){
        //String 转化为 基本数据类型 调用包装类的parseXxx(String str)方法
        String s1 = "123";
        int i1 = Integer.parseInt(s1);
    }
}
````

* 基本数据类型 ——> `String` 
  * 使用连接符`+`运算
  * 使用相应的`valueOf()`方法

````java
public class StringTest{
    public static void main(String []args){
        int num = 10;
        //方式一 使用 + 连接符运算
        String s1 = num+"";
        String s2 = String.valueOf(num);
    }
}
````

##### String、char类型之间的转换

* `String` ——> `char`  :调用`String`中的`toCharArr()`方法

  ````java
  public class StringDemo{
      public static void main(String []args){
          String str = "hello";
          char []arr = str.toCharArr();
          for (int i = 0; i < arr.length; i++) {
              System.out.println(arr[i]);
          }
      }
  }
  //输出
  h
  e
  l
  l
  o
  ````

* `char` ——> `String` :调用`String`中的构造器

  ````java
  public class StringDemo{
      public static void main(String []args){
          String str = "hello";
          char []arr = str.toCharArr();
          String str1 = new String(arr);
          System.out.println(str1);
      }
  }
  //输出 hello
  ````

##### String与byte[]之间的转换

`String`类型和`byte[]`类型进行相互转换，则会在数字和字符串之间进行转换。若是`String`转换为`byte[]`，则会将每一个字符转换为ASCII值。这里便要引入编码和解码的概念

编码: 将`String` 转化为字节或字节数目

解码：将字节或字节数组转化为`String`

要求，解码时使用字符集要与编码是使用的字符集一致，否则会乱码

* `String` ——> `byte[]` ：调用`String`中的`getbyte()`   

  ````java
  public class StringDemo{
      public static void main(String []args){
  		String str = new String("hello");
          //String 转换为 byte[]  调用String的getBytes();
          byte []arr = str.getBytes();
          for (int i = 0; i < arr.length; i++) {
              System.out.println(arr[i]);
          }
      }
  }
  //输出
  104
  101
  108
  108
  111
  //getbyte()使用默认的字符集，将字母转化为ASCII码值
  //也可以使用指定的字符集
  public class StringDemo{
      public static void main(String []args){
          String str = new String("hello");
          byte []arr = str.getBytes("gbk");
      }
  }
  //在uft-8字符集中，一个汉字占用3个字节，一个字母占用1个字节
  //在gbk字符集中，一个汉字占用2个字节，一个字母占用1个字节
  ````

* `byte[]` ——> `String`

  ````java
  public class StringDemo{
      public static void main(String []args){
  		public void test5() throws UnsupportedEncodingException {
          String str = new String("hello，中国");
          byte []arr = str.getBytes("gbk");	//使用gbk字符集进行编码
          String s1 = new String(arr,"gbk");	//使用gbk字符集 进行解码
          System.out.println(s1);
          String s2 = new String(arr,"utf-8");	//使用utf-8字符集进行解码
          System.out.println(s2);
     		 }
      }
  }
  
  //输出结果
  //hello，中国
  //hello���й�
  //汉字在使用不同的字符集进行编码解码时，会出现乱码现象
  ````

  
