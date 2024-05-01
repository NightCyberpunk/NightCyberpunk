package ClassExcTest.equalTest;

public class UserTest {
    public static void main(String[] args) {
        User u1 = new User("Tom",12);
        User u2 = new User("Tom",12);
        System.out.println(u1.equals(u2));
        System.out.println(u1 instanceof User);
    }
}
class User{
    String name;
    int age;

    public User() {
    }

    public User(String name,int age) {
        this.name = name;
        this.age = age;
    }

}

