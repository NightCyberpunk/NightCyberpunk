package test;

import java.sql.SQLOutput;

public class Boy {
    private String name;
    private int age;

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
    //构造器

    public Boy() {
    }

    public Boy(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void marry(Girl girl){
        System.out.println("marr with"+girl.getName());
    }
    public void shout(){
        if(age >= 22){
            System.out.println("Can be married");
        }
        else {
            System.out.println("can't be married");
        }
    }
}
