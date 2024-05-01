public class PrivateVari {
    public static void main(String[] args) {
        NumberTest n1 = new NumberTest();
        n1.setNumber(3);
        int number = n1.getNumber();
        System.out.println(number);
    }
}
class NumberTest{
    private int number;
//    在NumberTest类中定义private变量
//    只能在NumberTest类中进行获取变量值和赋值操作
    public void setNumber(int n){
        number = n;
    }
    public int getNumber(){
        return number;
    }

}