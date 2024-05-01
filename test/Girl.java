package test;

public class Girl {
    private String name;
    private int age;

    public Girl() {
    } //无参构造器

    public Girl(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void marry(Boy boy){
        System.out.println("i want to marry with"+boy.getName());
        boy.marry(this);
    }
    public int compare(Girl girl){
        if (this.age > girl.age){
            return 1;
        } else if (this.age < girl.age){
            return -1;
        }
        else {
            return 0;
        }
    }
}
