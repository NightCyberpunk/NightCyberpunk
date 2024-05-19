package BlockTest;

public class User {
    private String userName;
    private String password;
    private long registrationTime;  //注册时间

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRegistration() {
        return registrationTime;
    }

    public User(){
        System.out.println("新用户注册");
        registrationTime = System.currentTimeMillis();//获取系统当前的时间(距离1970-1-1 0:00的毫秒数)
        userName = System.currentTimeMillis()+"";
        password = "123456";
    }

    public User(String userName,String password){
        System.out.println("新用户注册");
        registrationTime = System.currentTimeMillis();//获取系统当前的时间(距离1970-1-1 0:00的毫秒数)
        this.userName = userName;
        this.password = password;
    }

    public String getInfo(){
        return "用户名"+userName+"  密码:"+password+" 注册时间"+registrationTime;
    }
}
