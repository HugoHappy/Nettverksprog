package me.kristo4.tdat2004.entity;

import javax.persistence.*;

/**
 * Created by KristofferLaptop on 06-Mar-17.
 */

@Entity
@NamedQuery(name="getAmountOfAccounts", query="SELECT COUNT(o) from Account o")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int accNumber;
    double balance;
    String accOwner;
    @Version
    private int version;

    public Account(){

    }

    public Account(int accNumber, double accBalance, String accOwner){
        this.accNumber = accNumber;
        this.balance = accBalance;
        this.accOwner = accOwner;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void depositAmount(double amount){
        balance = balance-amount;
    }

    public int getAccNumber(){
        return accNumber;
    }

    public double getBalance(){
        return balance;
    }

    public String getAccOwner(){
        return accOwner;
    }

    public void setAccBalance(double accBalance){
        this.balance = accBalance;
    }

    public void setAccOwner(String accOwner){
        this.accOwner = accOwner;
    }
}
