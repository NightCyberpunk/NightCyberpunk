# Java 异常处理 throws

**Java 中异常对象的生成有两种方式：**

- 由虚拟机**自动生成**：程序运行过程中，虚拟机检测到程序发生了问题，那么针对当前代码，就会在后台自动创建一个对应异常类的实例对象并抛出。

- 由开发人员**手动创建**：`new 异常类型([实参列表]);`，如果创建好的异常对象不抛出对程序没有任何影响，和创建一个普通对象一样，但是一旦throw抛出，就会对程序运行产生影响了。

## 使用格式

````java
throw new 异常类名(参数);
````

## 举例

````java
public class ThrowTest {
    public static void main(String[] args) {
        method3();
    }
    
    public  static void method3(){	//解决异常
        try{
            method2();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void method2() throws FileNotFoundException,IOException{ //再次手动抛出异常
        method1();	//调用method1()
    }
    public void method1() throws FileNotFoundException, IOException {	//手动抛出异常，抛给调用者
        File file = new File("D:\\hello.txt");
        FileInputStream fis = new FileInputStream(file);    //可能报错FileNotFoundException
        int data = fis.read();  //可能报错IOException
        while(data != -1){
            System.out.println((char) data);
            data = fis.read();  //可能报错IOException
        }

        fis.close();    //可能报错IOException
    }
}
````

**是否真正处理了异常**？

* 从编译是否能通过的角度来看，可以视为一旦出现异常时的解决方案——继续向上抛出(throws)
* 从是否真正解决异常来看，throws仅仅式将可能出现的异常抛给了此方法的调用者，此调用者仍然需要考虑如何处理相关异常，从这个角度上来看，throws不算真正意义上的处理了异常。

## 方法重写

>方法声明的格式
>
>````properties
>权限修饰符 返回值 方法名(形参列表) [throws 异常类型]{  //方法体    }
>````
>
>子类重写的方法抛出的异常类型可以与父类被重写的方法抛出的异常类型相同，或者式父类被重写的方法抛出的异常类型的子类
>

````java
import java.io.FileNotFoundException;
import java.io.IOException;
class Father{
    public void method1() throws IOEpxception{
        
    }
}
````





