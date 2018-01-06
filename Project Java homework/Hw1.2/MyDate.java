import java.util.Calendar;

/**
 * Created by 栋 on 2017/3/1.
 */
import java.io.*;
import java.util.*;

import static java.lang.StrictMath.abs;

public class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(int a, int b, int c){
        if(check(a,b,c))
        {
            this.year = a;
            this.month = b;
            this.day = c;
        }
        else{
            System.out.println("Invalid input!");
            System.exit(0);
        }
        //System.out.println("" + this.year + this.month + this.day + "/n");
    }

    public boolean check(int a, int b, int c){
        boolean tmp = true;
        if((a/4*4 == a) && (a/1000*1000 != a)){
            tmp = true;
        }
        else tmp = false;
        if(tmp == true){
            if(b == 2){
                if(c > 0 && c < 30) return true;
                else return false;
            }
        }
        else{
            if(b == 2){
                if(c > 0 && c < 29) return true;
                else return false;
            }
        }
        if(b == 1 || b == 3 || b == 5 || b == 7 || b == 8 || b == 10 || b == 12 ){
            if(c > 0 && c < 32) return true;
            else return false;
        }
        else if(b == 4 || b == 6 || b == 9 || b == 11 ){
            if(c > 0 && c < 31) return true;
            else return false;
        }
        else return false;
    }

    public boolean latterThan(MyDate date){
        if(this.year < date.year) return false;
        else if(this.year > date.year) return true;
        else{
            if(this.month < date.month) return false;
            else if(this.month > date.month) return true;
            else{
                if(this.day < date.day) return false;
                else if(this.day > date.day) return true;
                else return false;
            }
        }
    }

    public int dayDifference(MyDate date1, MyDate date2){
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.set(date1.year,date1.month,date1.day);
        calendar2.set(date2.year,date2.month,date2.day);

        if(date1.latterThan(date2)){

            long l=calendar1.getTimeInMillis()-calendar2.getTimeInMillis();
            int days=new Long(l/(1000*60*60*24)).intValue();
            days = abs(days);
            System.out.println("2个日期之间相差："+days+"天。");
        }
        else
        {
            long l=calendar2.getTimeInMillis()-calendar1.getTimeInMillis();
            int days=new Long(l/(1000*60*60*24)).intValue();
            days = abs(days);
            System.out.println("2个日期之间相差："+days+"天。");
        }
        return 0;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter two dates");
        int year1 = input.nextInt();
        int month1 = input.nextInt();
        int day1 = input.nextInt();
        MyDate date1 = new MyDate(year1, month1, day1);

        int year2 = input.nextInt();
        int month2 = input.nextInt();
        int day2 = input.nextInt();
        MyDate date2 = new MyDate(year2, month2, day2);

        if(date1.latterThan(date2)){
            System.out.println("latter");
        }
        else{
            System.out.println("No latter");
        }

        MyDate dategap = new MyDate(2000 , 1, 1);
        int yeargap = dategap.dayDifference(date1, date2);


    }

}
