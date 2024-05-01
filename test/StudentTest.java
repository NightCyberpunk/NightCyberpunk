package test;

import jdk.jshell.execution.Util;

public class StudentTest {
    public static void main(String[] args) {
        StudentUitl Uitl = new StudentUitl();
        Student []stu = new Student[10];
        for (int i = 0; i < stu.length; i++) {
            stu[i] = new Student();
            stu[i].number = i+1;
            if(Math.random()>0.5){
                stu[i].gerden = false;
            }else{
                stu[i].gerden = true;
            }
            stu[i].state =(int)(Math.random()*2+1);
            stu[i].score =(int)(Math.random()*101);
        }
        Uitl.show(stu);
        System.out.println("**************");
        Uitl.sort(stu);
        Uitl.show(stu);
    }
}
