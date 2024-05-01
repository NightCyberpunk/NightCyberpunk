package test;

public class StudentUitl {
    public void show(Student []stu){
        for (int i = 0; i < stu.length; i++) {
            System.out.println("序号:"+stu[i].number+"\t 性别:"+((stu[i].gerden)==true?"男":"女")+"\t 班级"+stu[i].state+"\t 成绩"+stu[i].score);
        }
    }
    public void sort(Student []stu){
        for (int i = 0; i < stu.length - 1; i++) {
            for (int j = 0; j < stu.length - 1 - i; j++) {
                if(stu[j].score > stu[j+1].score)
                {
                    Student temp = stu[j];
                    stu[j] = stu[j + 1];
                    stu[j+1] = temp;
                }
            }
        }
    }

}
