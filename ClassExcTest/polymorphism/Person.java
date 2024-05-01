package ClassExcTest.polymorphism;

class Person{
    public void eat(){
        System.out.println("Person.eat");
    }
    public void sleep(){
        System.out.println("Person.sleep");
    }
}
class Man extends Person{
    boolean isSmoking;//是否抽烟
    @Override
    public void eat() {
        System.out.println("Man.eat");
    }

    @Override
    public void sleep() {
        System.out.println("Man.eat");
    }
    public void earnMoney(){
        System.out.println("Man.earnMoney(only)");  //Man特有方法
    }
}
class Woman extends Person{
    @Override
    public void eat() {
        System.out.println("Woman.eat");
    }

    @Override
    public void sleep() {
        System.out.println("Woman.eat");
    }

    public void goShopping(){
        System.out.println("Woman.goShopping(only)"); //Woman类中特有的方法
    }
}