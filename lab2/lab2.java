package lab2;
import java.util.Scanner;

public class lab2 {


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        lab2 obj = new lab2();

        System.out.print("Количество строк пирамиды: ");
        int range = scanner.nextInt();

        obj.Pyramid(range);
        obj.Array(6,6);

    }

    public void Pyramid(int range){
        int n = range; 
	 
        for(int i = 0;i < n; i++){
                for(int x = 0;x <=n-i;x++){
                    System.out.print(" ");
                }
                for(int j = 1;j <=i*2+1;j++){
                    System.out.print("*");
                }
                System.out.println();   
        }
    }

    public void Array(int n, int m){
        int[][] arr = new int[n][m];
        int buf = 50;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                arr[i][j] = buf + 3;
                buf = arr[i][j];
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
