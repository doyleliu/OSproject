/**
 * Created by 栋 on 2017/5/1.
 */
public class Customer implements Runnable {

    public static final int COUNT = 5; // thread numbers
    private int Resourcenum;
    private int[] max;
    private int CustNum; // the number of customers
    private int[] request;
    private java.util.Random rand;
    private Bank Sample;

    public Customer (int CustNum, int[] max, Bank Sample) {
        this.CustNum = CustNum;
        this.max = new int[max.length];
        this.Sample = Sample;

        System.arraycopy (max, 0, this.max, 0, max.length);
        Resourcenum = max.length;
        request = new int[Resourcenum];
        rand = new java.util.Random();
    }

    public void run() {
        boolean flag = true;
		// int customers = 0;
        while (flag) {
            try {
                Thread.sleep(1000);
                for (int i=0; i < Resourcenum; i++)
                    request[i] = rand.nextInt(max[i]+1);
                if (Sample.requestResources(CustNum, request)) {
                    Thread.sleep(1000);
                    Sample.releaseResources(CustNum, request);
                }
            } catch (Exception e) {
                flag = false;
            }
        }
        System.err.println("Customer # " + CustNum + "interrupted!");
    }
}