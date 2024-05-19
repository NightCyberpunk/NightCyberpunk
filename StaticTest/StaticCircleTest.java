package StaticTest;

public class StaticCircleTest {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        System.out.println(c1);
        Circle c2 = new Circle();
        System.out.println(c2);
        System.out.println(Circle.total);
    }
}
class Circle{
    double radius;
    int id; //编号
    static int total;  //创建的Circle实例的个数

    public Circle(){
        this.id = init;
        init++;
        total++;
    }
    public Circle(double radius){
        this();     //用法很妙
        this.radius = radius;
    }

    private static int init = 1001;

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", id=" + id +
                '}';
    }
}