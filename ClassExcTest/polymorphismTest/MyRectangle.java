package ClassExcTest.polymorphismTest;
//矩形图形
public class MyRectangle extends GeometricObject {
    private String type ="矩形";
    private double width;//宽度
    private double height;//高度

    public MyRectangle(String color, double weight, double width, double height) {
        super(color, weight);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double findArea() {
        return width * height;//返回面积=长 * 宽
    }
}
