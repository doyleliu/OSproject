import java.util.Scanner;

/**
 * Created by 栋 on 2017/4/8.
 */
public class WorkerThread implements Runnable
{
    private int row;
    private int col;
    private int[][] A;
    private int[][] B;
    private int[][] C;

    public WorkerThread(int row, int col, int[][] A,
                        int[][] B, int[][] C) {
        this.row = row;
        this.col = col;
        this.A = A;
        this.B = B;
        this.C = C;
    }
    public void run() {
// the thread of matrix count
        int k = A[0].length;
        for(int i =0; i < k; i ++)
            C[row][col] = C[row][col] + A[row][i]*B[i][col];// use the algorithm in each thread and count one element
    }
    public static void main(String[] args){
        Scanner cin=new Scanner(System.in);
        System.out.println("Enter the number of rows in matrix A : ");
        int A_row=cin.nextInt();
        System.out.println("Enter the number of columns in matrix A : ");
        int A_col=cin.nextInt();
        System.out.println("Enter the number of rows in matrix B : ");
        int B_row= cin.nextInt();
        System.out.println("Enter the number of columns in matrix B : ");
        int B_col=cin.nextInt();

        if(A_col==B_row)
        {
            int [][]matrix_A=new int[A_row][A_col];
            int [][]matrix_B=new int[B_row][B_col];
            int [][]matrix_ans =new int[A_row][B_col];
            for(int i=0;i<A_row;i++){
                for(int j=0;j<B_col;j++){
                    matrix_ans[i][j] = 0;
                }
            }

            System.out.println("Enter the elements of matrix A : ");
            for(int i=0;i<A_row;i++)
                for(int j=0;j<A_col;j++)
                {
                    matrix_A[i][j]=cin.nextInt();
                }
            System.out.println("Enter the elements of matrix B : ");
            for(int i=0;i<B_row;i++){
                for(int j=0;j<B_col;j++)
                {
                    matrix_B[i][j]=cin.nextInt();
                }
            }

            WorkerThread [][]t = new WorkerThread[A_row][B_col];
            Thread [][]tt = new Thread[A_row][B_col];
            for(int i=0;i<A_row;i++){
                for(int j=0;j<B_col;j++)
                {
                    t[i][j] = new WorkerThread(i,j,matrix_A,matrix_B,matrix_ans);
                    tt[i][j] = new Thread(t[i][j]);
                    tt[i][j].start();
                    try {
                        tt[i][j].join(); // join the thread to let them work together
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
            System.out.println("The matrix is: ");
            for(int i=0;i<A_row;i++){
                for(int j=0;j<B_col;j++){
                    System.out.print(matrix_ans[i][j]);
                    System.out.print(" ");
                }
                System.out.print("\n");
            }


        }
        else
        {
            System.out.print("!!! Can't multiply this matrixes !!! ");
        }
    }
}
