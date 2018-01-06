import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by 栋 on 2017/4/11.
 */
public class Worker implements Comparable<Worker>{
    public String Name;
    public double SalaryRate;
    public double TotalSalary;
    public int WorkHours;
    public void EndMonth(){
        return;
    }
    @Override
    public int compareTo(Worker worker){
        return this.Name.compareTo(worker.Name);
    }

    public static void main(String[] args) {
//        Worker[] workers = new Worker[10];
//        for (int i = 0; i < 5; i++)
//            workers[i] = new HourlyWorker("Jim" + i, i, 38 + i);
//        for (int i = 5; i < 10; i++)
//            workers[i] = new SalariedWorker("Tony" + i, i, 33 + i);

//        List<Worker> WorkerList = new ArrayList();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter numbers of worker");
        int num = input.nextInt();
        Worker[] workers = new Worker[num];
        for(int i=0;i<num;i++){
            System.out.println("Workers Names");
            String tmpname = input.next();
            System.out.println("jobs:(H/S)");
            String tmp = input.next();
            System.out.println("Salary rate:");
            double tmprate = input.nextDouble();
            System.out.println("Work hours:");
            int tmphours = input.nextInt();
            if((tmphours < 0)||(tmprate < 0)){
                System.out.println("Error input");
                return;
            }
            char fir = tmp.charAt(0);
            if(fir == 'H' || fir == 'h')
            {
                workers[i] = new HourlyWorker(tmpname,tmprate,tmphours);
//                WorkerList.add(workers[i]);
            }
            else if(fir == 'S' || fir == 's')
            {
                workers[i] = new SalariedWorker(tmpname,tmprate,tmphours);
//                WorkerList.add(workers[i]);
            }
            else{
                System.out.println("Error");
                return;
            }
            workers[i].EndMonth();
        }

//        for (int i = 0; i < 10; i++) {
//            workers[i].EndMonth();
//        }
        String judge = input.next();
        if(judge.charAt(0) == '+') Arrays.sort(workers,new SortSalary());
        else if(judge.charAt(0) == '-') Arrays.sort(workers,new SortRate());
        else Arrays.sort(workers);



//        Arrays.sort(workers);

        //        Arrays.sort(workers,new SortSalary());
//        Arrays.sort(workers,new SortRate());
//        Arrays.sort(workers,new SortName());
        for (int i = 0; i < num; i++) {
//            System.out.println(workers[i].Name);
//            System.out.println(workers[i].TotalSalary);
            System.out.println(workers[i]);
        }

//        Arrays.sort(workers,new SortSalary());
//        Arrays.sort(workers,new SortRate());
//        Arrays.sort(workers,new SortName());


    }
}

class SortSalary implements Comparator<Worker>{
    public int compare(Worker worker1, Worker worker2){ // implementing compare function

        if(worker1.TotalSalary == worker2.TotalSalary){
            return 0;
        }
        else if(worker1.TotalSalary > worker2.TotalSalary){
            return -1;
        }
        else return 1;
    }
}

class SortRate implements Comparator<Worker>{
    public int compare(Worker worker1, Worker worker2){

        if(worker1.SalaryRate == worker2.SalaryRate){
            return 0;
        }
        else if(worker1.SalaryRate > worker2.SalaryRate){
            return -1;
        }
        else return 1;
    }
}

class SortName implements Comparator<Worker>{
    public int compare(Worker worker1, Worker worker2){
        int num = worker1.Name.compareTo(worker2.Name);
        if(num == 0){
            return 0;
        }
        else if(num < 0){
            return -1;
        }
        else return 1;
    }
}

class HourlyWorker extends Worker {
    HourlyWorker(String a, double b, int c){
        this.Name = a;
        this.SalaryRate = b;
        this.WorkHours = c;
    }

    public double computePay(){
        if(WorkHours < 40){
            TotalSalary = WorkHours * SalaryRate;
        }
        else{
            TotalSalary = 40 * SalaryRate + 2 * (WorkHours - 40) * SalaryRate;
        }
//        System.out.println(TotalSalary);
        return TotalSalary;
    }

    public void EndMonth(){
        computePay();
    }
    public String toString()
    {
        return ("Hourlyworker; Name:"+Name + "," +"SalaryRate:"+ SalaryRate + ","+ "total:"+this.computePay());
    }


}

class SalariedWorker extends Worker{
    SalariedWorker(String a, double b, int c){
        this.Name = a;
        this.SalaryRate = b;
        this.WorkHours = c;
    }
    public double computePay(){
        TotalSalary = 40 * WorkHours;
        return TotalSalary;
    }
    public void EndMonth(){
        computePay();
    }
    public String toString()
    {
        return ("SalariedWorker; Name:"+Name + "," +"SalaryRate:"+ SalaryRate + ","+ "total:"+this.computePay());
    }

}
