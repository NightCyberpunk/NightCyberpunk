# 方法的重载（overload）

* 定义：在同一个类中，允许存在一个以上的同名方法，只要他们的参数列表不同即可。满总这样特征的多个方法，彼此之间构成方法的重载。
* 特点：“两同一不同”。两同:同一个类、相同的方法名。一不同:参数列表不同。

***方法的重载与形参的名、权限修饰符、返回值类型都没有关系***

----

## 方法名相同、形参不同

>````java
>public void sum(int i,int j){
>
>}
>public void sum(int i,double d){
>
>}
>public void sum(String str1,String str2){
>
>}
>public void sum(String str1,int i){
>
>}
>````
>
>***编译通过***

## 方法名相同、形参相同

>````java
>public void sum(int i,int j){
>
>}
>public void sum(int m,int n){
>
>}
>````
>
>***编译报错***
>
>报错信息:
>
>java: 已在类 test.test中定义了方法 add(int,int)

## 实际问题(举例)

>----
>
>### 优先调用
>
>*优先使用形参不需要类型转换的方法*
>
>````java
>public class test {
>public static void main(String[] args) {
>   test.sum(1,2);  //形参为(int,int)
>}
>public static void sum(int i, int j){
>   System.out.println("int");
>}
>public static void sum(double i,double j){
>   System.out.println("double");
>}
>
>}
>````
>
><img src="C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240322231453394.png" alt="image-20240322231453394" style="zoom: 33%;" /> ***int***
>
>----
>
>### 自动类型提升
>
>````java
>public class test {
>public static void main(String[] args) {
>   test.sum(1,2.0);  //形参为(int--- >double,double)
>}
>public static void sum(int i, int j){
>   System.out.println("int");
>}
>public static void sum(double i,double j){
>   System.out.println("double");
>}
>
>}
>````
>
><img src="C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240322231701379.png" alt="image-20240322231701379" style="zoom:33%;" />***double***
>
>----
>
>### 强制类型转换
>
>````java
>public class test {
>public static void main(String[] args) {
>   test.sum((double) 1,(double) 2);  //形参强制转换为(double,double)
>}
>public static void sum(int i, int j){
>   System.out.println("int");
>}
>public static void sum(double i,double j){
>   System.out.println("double");
>}
>
>}
>````
>
><img src="C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240322231913364.png" alt="image-20240322231913364" style="zoom: 33%;" />***double***
>
>----

## 其他

>---
>
>```java
>public class test {
>public static void main(String[] args) {
>   int []arr1 = new int[]{1,2,3,4,5};
>   System.out.println(arr1); //[I@b4c966a
>   char []arr2 = new char[]{'a','b','c'};
>   System.out.println(arr2);   //abc
>   boolean []arr3 = new boolean[]{true,false};
>   System.out.println(arr3);   //[Z@2f4d3709
>}
>}
>```
>
><img src="C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240322233417671.png" alt="image-20240322233417671" style="zoom:33%;" />
>
>**Java自身提供的API也存在着方法的重构,同一个方法名，编译器通过输入形参的不用，选择合适的方法**
>
>![image-20240322233846988](C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240322233846988.png)

# 可变个数的方法

* 使用场景
  在调用方法时，可能会出现方法的形参类型是确定的，但参数的个数不确定。这时候需要用到可变个数的方法
* 声明格式：方法名（参数类型 ...参数名）
* 注意:可变个数形参的方法在调用的时候，实际的实参个数可以是0个、一个乃至多个。

## 可变个数方法的举例

>----
>
>````java
>public class test {
>public static void main(String[] args) {
>   test t1 = new test();
>   t1.printf();
>   t1.printf(1);
>   t1.printf(1,2);
>   t1.printf(1,2,3);
>}
>public void printf(int ... num){
>   System.out.println(111);
>}
>}
>````
>
>![image-20240323001540476](C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240323001540476.png)

## 可变个数方法的重载

>````java
>public class test {
>public static void main(String[] args) {
>   test t1 = new test();
>   t1.printf();
>   t1.printf(1);
>   t1.printf(1,2);
>   t1.printf(1,2,3);
>}
>public void printf(int ... num){
>   System.out.println("0");
>}
>public void printf(int i){
>   System.out.println(1);
>}
>public void printf(int i,int j){
>   System.out.println(2);
>}
>public void printf(int i,int j ,int k){
>   System.out.println(3);
>}
>}
>````
>
>***当既存在可变个数方法，又存在同名非可变个数方法的时候，编译器会优先调用非可变个数的方法***
>
>***同时，可变个数方法，又与其他同方法名方法构成了重载方法***
>
>![image-20240323002202694](C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240323002202694.png)
>
>----

## 数组类型的冲突

>----
>
>````java
>public void f(int ... nums){
>   System.out.println(1);
>}
>public void f(int []nums){
>   System.out.println(2);
>}
>````
>
>***特殊地，可变个数方法的形参与其同方法名的数组形参方法，不能构成重载***
>
>![image-20240323002809664](C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240323002809664.png)
>
>***可以理解为int[ ]   和   int ... 为同一类型***
>
>````java
>public class test {
>public static void main(String[] args) {
>   test t1 = new test();
>   t1.f(new int[]{1,2,3,4,5});
>   t1.f(1,2,3,4,5);
>}
>public void f(int ... nums){
>   for (int i = 0; i < nums.length; i++) {
>       System.out.print(nums[i]);
>   }
>   System.out.println();
>}
>}
>
>输出为:    
>12345
>12345
>````
>
>![image-20240323003416846](C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240323003416846.png)
>
>----

## 多个参数的可变方法

>>## **若有多个参数，可变个数的形参，必须声明在形参列表的最后**
>
>>````java
>>public class test {
>>public void f(int ... nums,int i){
>>````
>
>>    }
>>    }
>>    //编译报错
>>
>>    ````
>>    
>>    ````
>
>>***此时，有两个参数，一个为可变个数，一个为普通int,但可变个数参数在前***
>
>>![image-20240323003953866](C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240323003953866.png)
>
>>````java
>>public class test {
>>public void f(int i,int ... nums){
>>````
>
>>    }
>>    }
>>    //编译通过
>>
>>    ````
>>    
>>    ````
>
>>***此时，有两个参数，一个为可变个数，一个为普通Int,但可变个数参数再后***
>
>
>
>
>
>>## 可变个数方法只能存在一个可变个数参数
>
>>````java
>>public void f(int ... num1,int ... num2){
>>````
>
>>    }
>>    //编译报错
>>
>>    ````
>>    
>>    ````
>
>>![image-20240323004359271](C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240323004359271.png)
>
>>## 原因杂谈
>
>>````java
>>public class test {
>>public static void main(String[] args) {
>>   test t1 = new test();
>>   t1.f(1,2,3,4,5,6);
>>}
>>public void f(int ... num1,int ... num2){
>>}
>>}
>>//假设编译通过
>>````
>
>>***假设编译通过，此时，存在六个形参***
>
>>***但编译器不清楚，1,2,3,4,5,6 中，哪个作为形参传给num1,哪个传给num2***
>
>>````java
>>public class test {
>>public static void main(String[] args) {
>>   test t1 = new test();
>>   t1.f(1,2,3,4,5,6);
>>}
>>public void f(int  num1,int ... num2){
>>}
>>}
>>//编译通过
>>//将1传给num1
>>//将2,3,4,5,6传给num2
>>````

## 总结

* 可变个数形参的方法与同一个类中，同名的多个方法可以构成重载
* 可变个数形参若与同类方法的形参类型相同，则不能构成重载
* 可变个数的形参必须声明在形参的末尾
* 可变个数的形参只能在形参列表中出现一次







