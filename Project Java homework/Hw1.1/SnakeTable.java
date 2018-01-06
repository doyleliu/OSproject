/**
 * Created by �� on 2017/2/28.
 */
import java.util.*;
public class SnakeTable {
    private int N;

    public boolean Check(int num){
        if ((num > 9) || (num < 0)){
            return false;
        }
        else return  true;
    }

    public void CreateTable(int num){
        int[][] table = new int[num][num];
        int tmp = 1;
        for(int i = 0; i < num ; i++){
            for(int j = 0; j < num ; j ++){
                if(i / 2 * 2 == i){
                    table[i][j] = tmp;
                }
                else{
                    table[i][num-j-1] = tmp;
                }
                tmp ++;
            }
        }
        for(int i = 0; i < num ; i++ ){
            for(int j = 0 ; j < num; j++){
                System.out.print(table[i][j]);
                System.out.print("   ");
            }
            System.out.print('\n');
        }
        //System.out.println(Arrays.asList(table));
    }



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
//BufferedReader in = new BufferedReader(new FileReader(filename));
        System.out.println("Enter table size 1-9, 0 to exit:");
        int line = input.nextInt();
        while(line != 0)
        {
            SnakeTable tmp = new SnakeTable();
            if(tmp.Check(line)){
                tmp.CreateTable(line);
            }
            else {System.out.println("please enter a number in the range 0-9\n");}
            System.out.println("Enter table size 1-9, 0 to exit:");
            line = input.nextInt();
        }
    }
}
