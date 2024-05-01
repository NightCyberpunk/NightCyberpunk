package model1;

/**
 * CustomerView为主模块，负责菜单的显示和处理用户操作
 */
public class CustomerView {
    CustomerList customerList = new CustomerList(10);

    /**
     * 用途：显示主菜单，响应用户输入
     * 根据用户操作分别调用其他相应的成员方法（如addNewCustomer），以完成客户信息处理。
     */
    public void enterMainMenu(){

        boolean isFlag = true;

        while (isFlag){
            //显示界面
            System.out.println("\n--------------------拼电商客户管理系统------------------\n");
            System.out.println("                       1 添加客户 ");
            System.out.println("                       2 修改客户 ");
            System.out.println("                       3 删除客户 ");
            System.out.println("                       4 客户列表 ");
            System.out.println("                       5 退   出\n ");
            System.out.println("请选择(1-5):");
            char key = CMUtility.readMenuSelection();
            //获取用户选择:5;
            switch (key){
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
                    System.out.println("确认是否退出(Y/N)");
                    char isExit = CMUtility.readConfirmSelection();
                    if(isExit == 'Y'){
                        isFlag = false;
                    }
                    break;
            }
        }
    }
    private void addNewCustomer(){

    }
    private void modifyCustomer(){

    }
    private void deleteCustomer(){

    }
    private void listAllCustomers(){

    }
    public static void main(String[] args){
        CustomerView view = new CustomerView();
        view.enterMainMenu(); //进入主界面
    }

}
