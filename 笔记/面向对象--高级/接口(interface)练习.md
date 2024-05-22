# 接口(interfacec)练习

#### 练习一

>声明一个接口Eatable，其中有一个方法eat()
>
>声明三个类Chinese、American、Indian，都实现此接口，并重写eat()方法

````java
interface Eatable(){
    void eat();
}

class Chinese implements Eatable{
    public void eat(){
        System.out.println("中国人使用筷子吃饭");
    }
}

class American implements Eatable{
    public void eat(){
        System.out.println("美国人使用刀叉吃饭");
    }
}

class India implements Eatable{
    public void eat(){
        System.out.println("印度人使用手抓饭");
    }
}

public class EatableTest{
    public static void main(){
        Eatable []eatable = new Eatable[3];
        eatable[0] = new Chinese();
        eatable[1] = new American();
        eatable[2] = new India();
        for(int i = 0; i < eatable.length; i++){
            eatable[i].eat();
        }
    }
}
````

