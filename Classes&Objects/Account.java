package com.company;
import java.util.Date;

/*
    Class "Account" -
    This class is used to create account objects.
    The class simulates an account with an initial
    balance, and has methods such as deposit, withdraw,
    etc. to make changes to an account (per assignment
    instructions).

    Written by Anish Gajera starting on Monday, February, 28 2022, for CS 2336.501
 */


public class Account
{
    //private data field to hold the id for the account (default 0)
    private int id = 0;
    //private data field to hold the balance (default 0)
    private double balance = 0;
    //private data field to hold the annual interest rate (default 0)
    private double annualInterestRate = 0;
    //private data field to hold the date the account was created
    private Date dateCreated = new Date();

    //create default account object (no-arg constructor)
    Account()
    {
    }

    //create an account with a specified id and initial balance (mutator method)
    Account(int newId, double Balance, double annualIntRate)
    {
        id = newId;
        balance = Balance;
        //divide annual interest rate by 100 (since it will be entered as a percentage)
        annualInterestRate = annualIntRate / 100.0;
    }

    //method to withdraw a specified amount from the [initial] balance in the account
    public double withdraw(double withdrawAmt)
    {
       return balance -= withdrawAmt;
    }

    //method to deposit a specified amount to the [initial] balance in the account
    public double deposit(double depositAmt)
    {
        return balance += depositAmt;
    }

    public double monthlyInterest(double monthlyInterestRate)
    {
        return balance * monthlyInterestRate;
    }

    //accessor method for the ID
    public int getId()
    {
        return id;
    }
    //accessor method for the balance
    public double getBalance()
    {
        return balance;
    }
    //accessor method for the annual interest rate
    public double getAnnualInterestRate()
    {
        return annualInterestRate;
    }
    //accessor method for the date created
    public Date getDateCreated()
    {
        return dateCreated;
    }
    //accessor method to get monthly interest rate (annual interest rate / 12)
    public double getMonthlyInterestRate()
    {
        return annualInterestRate / 12.0;
    }
}



/* Notes: (Ignore)

* Setter = Mutator
* Getter = Accessor

 */