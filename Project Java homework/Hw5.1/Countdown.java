import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by 栋 on 2017/3/20.
 */
public class Countdown{

    public static void main(String[] args){
        Count t1 = new Count(1);
        Thread t = new Thread(t1);
        t.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{;
            String input = null;
            while((input = br.readLine())!= null){
                if(input.length() == 0){
                    //System.out.println("I am in!");
                    Count t2 = new Count(0);
                    Thread tt = new Thread(t2);
                    tt.start();
//                    t.interrupt();

                    break;
                }
            }

        }catch(Exception e){
//            e.printStackTrace();
        }

    }
}

class Count implements Runnable{
    static boolean judge = true;
    static int no = 0;
    int time = 10;
    public void run(){
        int check = no;
        int t = time;
        new Thread().start();
        while (t >= 0) {
//            System.out.println(t);
            try {
                if(judge == true){
                    Thread.sleep(1000);
                    if(no == 2){
                        System.out.println(" ");
                        System.out.println("user hits the Enter key");
                        System.out.println("");
                        System.out.println("interrputed");
                        System.out.println("finished");
                        return;
                    }
                    System.out.println(t);
                    t = t - 1;
                }
                else{
                    //System.out.println(no);
                    if(check == 1)
                    {

                        System.out.println(" ");
                        System.out.println("user hits the Enter key");
                        System.out.println("");
                        System.out.println("interrputed");
                        System.out.println("finished");
                        return;
                    }
                    else return;
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        if(t < 0){
            System.out.println("0");
            System.out.println("user hits the Enter key");
            System.out.println("finished");
        }

    }
    Count(int num ){
        no = no + 1;
        if(num == 0) interruptt();
    }

    void interruptt()
    {
        judge = false;
//        try{Thread.sleep(1000);}
//        catch (Exception e){
//        }
    }
}
