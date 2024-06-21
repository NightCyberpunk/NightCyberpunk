# String不可变性的理解



````java
public class StringTest1 {
    String str = "test";
    char []c1 = {'t', 'e', 's', 't'};
    public void change(String str , char []c1){
        str = "best";
        c1[0] = 'b';
    }

    public static void main(String[] args) {
        StringTest1 t1 = new StringTest1();
        t1.change(t1.str,t1.c1);
        System.out.println(t1.str); //test
        System.out.println(t1.c1);  //best
    }
}
````

>将一个字符串进行反转。将字符串中指定部分进行反转

````java
//方法1.将String 转化为 char[]  ，针对char[]数组进行相应位置的反转 反转以后将char [] 转为String类型
public class Test{
    public String reverse(String str, int fromIndex, int toIndex){
        char []arr = str.toCharArray();
        for(int i = fromIndex, j = toIndex ; i < j ; i++ , j--){
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return new String(arr);
    }
}
````

>获取一个字符串 在 另一个 字符串 中 出现的次数

````java
public class Test{
    public static int getStringCount(String foundStr,String targetStr){
        int count = 0,index = foundStr.indexOf(targetStr);
        while(index != -1){
            count++;
            index = foundStr.indexOf(targetStr,index + targetStr.length());
        }
        return count;
    }
    public static void main(String []args){
        System.out.println(Test.getStringCount("aaaaaaa","aa"));	//3
    }
}
````

