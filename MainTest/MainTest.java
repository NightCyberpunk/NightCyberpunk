package MainTest;

public class MainTest {
    public static void main(String[] args) {
        String []arr = new String[]{"Aa","Bb","Cc"};
        Main.main(arr);
    }
}
class Main{
    public static void main(String[] args){//看作普通的静态方法
        for(int i=0;i<args.length;i++){
            System.out.println(args[i]);
        }
    }
}
