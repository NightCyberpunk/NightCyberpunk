package ClassExcTest.polymorphismTest;

public class GeometricTest {

    public static void main(String[] args) {
        GeometricTest test = new GeometricTest();
        Circle c1 = new Circle("red",1.0,2.3);
        Circle c2 = new Circle("red",1.0,3.3);
        test.displayGeometricObject(c1);
        test.displayGeometricObject(c2);

        boolean isEquals = test.equalsArea(c1,c2);
        if(isEquals){
            System.out.println("面积相等");
        }else{
            System.out.println("面积不相等");
        }
    }
    /**
     * 比较两个几何图形的面积是否相等
     * @param geometricObject1 几何图形1
     * @param geometricObject2 几何图形2
     * @return 相等返回1,否则返回0
     */
    public boolean equalsArea(GeometricObject geometricObject1,GeometricObject geometricObject2){
        return geometricObject1.findArea() == geometricObject2.findArea(); //判断是否相等
    }

    /**
     * 显示几何图形的面积
     * @param geometricObject  几何图像
     */
     public void displayGeometricObject(GeometricObject geometricObject){
        System.out.println(" 形的面积为"+geometricObject.findArea());
    }
}
