/**
 * Created by 栋 on 2017/5/1.
 */
import java.io.*;
import java.util.*;

public class BankImpl implements Bank
{
    private int threadnum;			// number of threads
    private int resourcenum;			// the number of resources
	
	// the variables of banker's algorithm
    private int[] available; 
    private int[][] max; 	
    private int[][] allocation;	
    private int[][] need;		

    boolean state = false;

    public BankImpl(int[] resources) {
        resourcenum = resources.length;
        threadnum =  NUMBER_OF_CUSTOMERS;
        available = new int[resourcenum];
		max = new int[NUMBER_OF_CUSTOMERS][];
        allocation = new int[NUMBER_OF_CUSTOMERS][];
        need = new int[NUMBER_OF_CUSTOMERS][];
		
		//intialize
		for(int i = 0; i < resourcenum; i++){
			available[i] = resources[i];
		}

    }

	// the fuction to add a customer with it max demand of resources
    public void addCustomer(int threadNum, int[] maxDemand) {
        max[threadNum] = new int[resourcenum];
        allocation[threadNum] = new int[resourcenum];
        need[threadNum] = new int[resourcenum];
		
		for(int i = 0; i < maxDemand.length; i++){
			max[threadNum][i] = maxDemand[i];
		}
		
		for(int i = 0; i < maxDemand.length; i++){
			max[threadNum][i] = need[i];
		}
    }
	
		// judging wether it is in a safe state.
    private boolean state (int threadNum, int[] request) {
		boolean state = true;
		
        System.out.print("\threadnum Customer " + threadNum + " requesting ");
        for (int i = 0; i < resourcenum; i++) System.out.print(request[i] + " ");

        System.out.print("Available = ");
        for (int i = 0; i < resourcenum; i++)
            System.out.print(available[i] + "  ");

        // first check if there are sufficient resources available
        for (int i = 0; i < resourcenum; i++)
            if (request[i] > available[i]) {
                System.err.println("NOT ENOUGH RESOURCES");
                return false;
            }
		// finsh initiate to false
        boolean[] finsih = new boolean[threadnum];
        for (int i = 0; i < threadnum; i++)
            finsih[i] = false;

        // copy the available matrix to availabletmp
        int[] availabletmp = new int[resourcenum];
		for(int i = 0; i < available.length; i++){
			availabletmp[i] = available[i];
		}
        for (int i = 0; i < resourcenum; i++) {
            availabletmp[i] -= request[i];
            need[threadNum][i] -= request[i];
            allocation[threadNum][i] += request[i];
        }
		// second step in the text book
        for (int i = 0; i < threadnum; i++) {
            // first find a thread that can finish
            for (int j = 0; j < threadnum; j++) {
                if (!finsih[j]) {
                    boolean temp = true;
                    for (int k = 0; k < resourcenum; k++) {
                        if (need[j][k] > availabletmp[k])
                            temp = false;
                    }
                    if (temp) { // if this thread can finish
                        finsih[j] = true;
                        for (int x = 0; x < resourcenum; x++)
                            availabletmp[x] += allocation[j][x];
                    }
                }
            }
        }
        for (int i = 0; i < resourcenum; i++) {
            need[threadNum][i] += request[i];
            allocation[threadNum][i] -= request[i];
        }
        // judge wether all threads can finish
        for (int i = 0; i < threadnum; i++)
            if (!finsih[i]) {
                state = false;
                break;
            }

        return state;
    }
	
	

	//get the current state of the four things
    public void getState() {
		//Available
        System.out.print("Available = \t[");
        for (int i = 0; i < resourcenum-1; i++)
            System.out.print(available[i]+" ");
        System.out.println(available[resourcenum-1]+"]");
		//Allocation
        System.out.print("\nAllocation = \t");
        for (int i = 0; i < threadnum; i++) {
            System.out.print("[");
            for (int j = 0; j < resourcenum-1; j++)
                System.out.print(allocation[i][j]+" ");
            System.out.print(allocation[i][resourcenum-1]+"]"+",");
        }
		//Max
        System.out.print("\nMax = \t\t");
        for (int i = 0; i < threadnum; i++) {
            System.out.print("[");
            for (int j = 0; j < resourcenum-1; j++)
                System.out.print(max[i][j]+" ");
            System.out.print(max[i][resourcenum-1]+"]"+",");
        }
		//Need
        System.out.print("\nNeed = \t\t");
        for (int i = 0; i < threadnum; i++) {
            System.out.print("[");
            for (int j = 0; j < resourcenum-1; j++)
                System.out.print(need[i][j]+" ");
            System.out.print(need[i][resourcenum-1]+"]"+",");
        }
        System.out.println(" ");
        for (int i = 0;i<threadnum;i++)
        {
            for (int j=0;j<1;j++)
            {
                if (need[i][j]<=available[j]&& need[i][j+1]<=available[j+1]&&need[i][j+2]<=available[j+2])
                {
                    state= true;
                }
            }

        }
        if (state)
        {
            System.out.println("safe state!");
        }
        if (!state)
        {
            System.out.println("deadlock waring!!!");
        }
        System.out.println();
    }



	// the function of RQ
    public synchronized boolean requestResources(int threadNum, int[] request)  {
		//judge state when requesting
        if (!state(threadNum,request)) {
            return false;
        }
		// error input
		for (int i = 0; i < m; i++) {
            if(request[i] < 0){
                System.out.println(" ");
                System.out.println("Error input!");
                return false;
            }
        }

        for (int i = 0; i < m; i++) {
            if(request[i] > need[threadNum][i]){
                return false;
            }
        }

        // when the state is safe
        for (int i = 0; i < resourcenum; i++) {
            available[i] -= request[i];
            allocation[threadNum][i] += request[i];
            need[threadNum][i] = max[threadNum][i] - allocation[threadNum][i];
        }
        return true;
    }

	// the function of RL
    public  synchronized void releaseResources(int threadNum, int[] release)  {
        System.out.print("\threadnum Customer " + threadNum + " releasing ");
		// error input
		for (int i = 0; i < m; i++) {
            if(release[i] < 0){
                System.out.println(" ");
                System.out.println("Error input!");
                return;
            }
        }
        for (int i = 0; i < resourcenum; i++) System.out.print(release[i] + " ");
		// release the resources
        for (int i = 0; i < resourcenum; i++) {
            available[i] += release[i];
            allocation[threadNum][i] -= release[i];
            need[threadNum][i] = max[threadNum][i] + allocation[threadNum][i];
        }
		// After release
        System.out.print("Available = ");
        for (int i = 0; i < resourcenum; i++)
            System.out.print(available[i] + "  ");

        System.out.print("Allocated = ");
        for (int i = 0; i < resourcenum; i++)
            System.out.print(allocation[threadNum][i] + "  ");
        System.out.print("");

    }
}