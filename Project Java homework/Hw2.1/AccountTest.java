/**
 * Created by 栋 on 2017/3/4.
 */
import java.util.*;
class AccountTest{

    public static void main(String[] args){

        BankAccount[] bank = new BankAccount[10];
        for(int i=0;i<5;i++)
            bank[i]=new CheckingAccount(50);
        for(int i=5;i<10;i++)
            bank[i]=new SavingAccount(100);

        for(int i=0;i<9;i++){
            bank[i].withdraw(20);
            bank[i].deposit(10);
            //bank[i].withdraw(10);
        }
        bank[9].withdraw(1000);

        for(int i=0;i<10;i++){
            bank[i].endMonth();
            bank[i].print();
        }

    }
}

class BankAccount{

    public static int AccountId = 0;
    public int Id = 0;
    public double money;

    BankAccount(){
        AccountId = AccountId + 1;
        Id = AccountId;
    }
    public void deposit(double num){
        money = money + num;
    }
    public void withdraw(double num){
        money = money - num;
    }
    public void endMonth(){
        return;
    }

    public void print(){
        System.out.println("my checking number is " + Id + " , my balance is " + money);

    }
}

class CheckingAccount extends BankAccount{
    CheckingAccount(double num){
        money = num;
    }
    public void endMonth(){
        if(money < 50){
            money = money - 2;
        }
        else money = money;
    }
    public void deposit(double num){
        if(num < 0){
            System.out.println("Error!");
        }
        else{
            money = money + num;
        }
    }
    public void withdraw(double num){
        if(num < 0 ){
            System.out.println("Error!");
        }
        else if(money < num){
            System.out.println("Error!");
        }
        else{
            money = money - num;
        }
    }
}

class SavingAccount extends BankAccount{
    private int transactions =0;
    SavingAccount(double num){
        money = num;
    }
    public void deposit(double num){
        if(num < 0){
            System.out.println("Error!");
        }
        else{
            money = money + num;
        }
        transactions = transactions + 1;

    }
    public void withdraw(double num){
        if(num < 0 ){
            System.out.println("Error!");
        }
        else if(money < num){
            System.out.println("Error!");
        }
        else{
            money = money - num;
        }
        transactions = transactions + 1;
    }
    public void endMonth(){
        if(transactions >= 3){
            money = money - 5;
        }
        money = money * 1.05;
    }
}
