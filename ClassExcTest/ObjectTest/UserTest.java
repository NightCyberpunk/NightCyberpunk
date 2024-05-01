package ClassExcTest.ObjectTest;

import java.util.Objects;

class User{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(this == obj){
//            return true;
//        }
//        if(obj instanceof User){
//            User user = (User)obj;
//            return this.age == user.age && this.name.equals(user.name);
//        }
//        return false;
//    }

    //IDEA 自动实现

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

}
