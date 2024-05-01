package ClassExcTest.polymorphismTest;
//几何图形类
public class GeometricObject {
    protected String type="1";//图像类型
    protected String color;
    protected double weight;

    protected GeometricObject(){

    }
    protected GeometricObject(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double findArea(){
        return 0.0;
    }
}
