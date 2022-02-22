package com.company;
import java.util.*;
import java.util.Scanner;

/*
    HW2 - Compare Loans
    This program accepts necessary input from the user
    which it then uses to calculate total payments for
    each interest rate starting from 5% to 8%, with an
    increment of 1/8.

    Written by Anish Gajera starting on Tuesday, February 15, 2022, for CS 2336.501
 */

public class CompareLoans
{
    public static void main(String[] args)
    {
        //variables which hold the loan amount and the period
        double loanAmt;
        int years;
        //scanner object
        Scanner scan = new Scanner(System.in);
        //prompt user for necessary info
        //loan amount
        System.out.print("Loan amount: ");
        loanAmt = scan.nextDouble();
        //period
        System.out.print("Number of years: ");
        years = scan.nextInt();
        //output headers for necessary info
        System.out.println("Interest Rate\t Monthly Payment \tTotal Payment");
        double interestRate = .05;
        for (double i = 5.0; i <= 8; i += 0.125)
        {
            //print the interest rate
            System.out.printf("%-5.3f", i);
            //print spacing after printing interest rate
            System.out.print("%\t\t\t ");
            //divide the interest rate by 1200 (to account for moving decimal back by 2)
            double moInterestRate = i / 1200;
            //calculate the monthly payment based on the loan amount and interest rate
            double monthlyPmt = loanAmt * moInterestRate / (1 - 1 / Math.pow(1 + moInterestRate, years * 12));
            //format and print the monthly payment and the total payment
            System.out.printf("%-19.2f", monthlyPmt);
            System.out.printf("%-8.2f\n", (monthlyPmt * 12) * years);
        }
    }
}

/*Notes: (Ignore)
    //do while loop for output
        do
        {
            interestRate += 0.00125;
            double moPay = loanAmt * interestRate;
            double totalPayment = moPay + loanAmt;
            System.out.println((interestRate * 100) + "\t\t" + moPay + "\t\t  " + totalPayment);

        }while(interestRate <= .08);

 */