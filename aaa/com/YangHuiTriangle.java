package aaa.com;
//1
//1 1
//1 2 1
//1 3 3 1
//1 4 6 4 1
//输出 十行 杨辉三角
public class YangHuiTriangle {
    public static void main(String[] args) {
        int [][]arr = new int[10][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[i+1];
            arr[i][i] = 1;
            arr[i][0] = 1;
        }
        for (int i = 2; i <arr.length  ; i++) {
            for (int j = 1; j <arr[i].length -1 ; j++) {
                arr[i][j] = arr[i-1][j] + arr[i -1][j - 1];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                System.out.print("\t");
            }
            System.out.println(" ");
        }
    }
}
