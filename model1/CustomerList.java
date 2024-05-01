package model1;
//CustomerList为Customer对象的管理模块，内部使用数组管理一组Customer 对象
public class CustomerList {
    private Customer[] customers;//用于保存客户对象的数组
    private int total; //用于保存客户对象的数量

    public CustomerList(int totalCustomer){     //构造器
        customers = new Customer[totalCustomer]; //动态化创建customer数组
    }

    /**
     * 用途：将参数customer添加到数组中最后一个客户对象记录之后  (增加客户)
     * @param customer 参数：customer指定要添加的客户对象
     * @return 添加成功返回true；false表示数组已满，无法添加
     */
    public boolean addCustomer(Customer customer){
        if(total < customers.length){  //判断客户数组是否为满
            customers[total] = customer;
            total ++;
            return true;  //表示已经添加
        }
        return false;  //表示数组已满，无法添加
    }

    /**
     * 用途：用参数customer替换数组中由index指定的对象
     * @param index index指定所替换对象在数组中的位置，从0开始
     * @param cust 参数：customer指定替换的新客户对象
     * @return 返回：替换成功返回true；false表示索引无效，无法替换
     */
    public boolean replaceCustomer(int index,Customer cust){
        if(index >= 0 && index < total){
            customers[index] = cust;    //暂疑 index -1 ? index
            return true;
        }
        return false;
    }

    /**
     * 用途：从数组中删除参数index指定索引位置的客户对象记录
     * @param index 参数： index指定所删除对象在数组中的索引位置，从0开始
     * @return 删除成功返回true；false表示索引无效，无法删除
     */
    public boolean deleteCustomer(int index){
        if(index < 0 || index >= total){
            return false;  //索引index非有效范围 无法删除
        }
        for (int i = index; i < total - 1; i++) {
            customers[i] = customers[i + 1];
        }
        customers[total - 1] = null;
        total--; //删除之后总数total-1；
        return true; //删除成功 返回true
    }

    /**
     * 用途：返回数组中记录的所有客户对象
     * 将带有数据的数组返回，若其中有null，不返回
     * @return 返回： Customer[] 数组中包含了当前所有客户对象，该数组长度与对象个数相同。
     */
    public Customer[] getAllCustomers() {
//        return customers;
//        正确，但不推荐，有null也会返回
        Customer[] returnCustomers = new Customer[total];
        for (int i = 0; i < returnCustomers.length; i++) {
            returnCustomers[i] = customers[i];
        }
        return returnCustomers;
    }

    /**
     * 用途：返回参数index指定索引位置的客户对象记录
     * @param index 参数： index指定所要获取的客户在数组中的索引位置，从0开始
     * @return 返回：封装了客户信息的Customer对象
     */
    public Customer getCustomer(int index){
        if(index < 0 || index >= total){
            return null; // 判断索引index是否为有效范围
        }
        return customers[index];
    }


    /**
     * 用于获取客户列表中的客户数量
     * @return total
     */
    public int getTotal(){
        return total;
    }
}
