package com.company;
import java.util.*;
import java.util.Scanner;
/*
    HW2 - Loan Amortization
    This program takes in input from the user
    and uses it to calculate a loan amortization
    schedule based on input.

    Written by Anish Gajera starting on Tuesday, February 15, 2022, for CS 2336.501
 */

public class LoanAmortization
{
    public static void main(String[] args)
    {
        //variables that hold necessary information (user input)
        double loanAmt, interestRate;
        int years;
        //scanner object
        Scanner scan = new Scanner(System.in);
        //prompt user to input necessary information
        //loan amount
        System.out.print("Loan amount: ");
        loanAmt = scan.nextDouble();
        //number of years
        System.out.print("Number of years: ");
        years = scan.nextInt();
        //annual interest rate
        System.out.print("Annual Interest rate: ");
        interestRate = scan.nextDouble();
        //function amortizationSchedule takes in the necessary input and creates the loan amortization schedule
        amortizationSchedule(loanAmt, interestRate, years);
    }

    //function that is called to initiate the amortization schedule
    public static void amortizationSchedule(double loanAmt, double interestRate, int years)
    {
        //variables which hold necessary info that is needed in the calculation of certain elements for the amortization schedule
        double interest, principal, balance;
        double moInterestRate, monthlyPmt;
        //number of months = number of years * 12
        int months = years * 12;

        //outputs the monthly and total payment (per assignment example)
        moInterestRate = interestRate / 12;
        //function moPayment calculates the monthly payment of the loan
        monthlyPmt = moPayment(loanAmt, moInterestRate, years);
        //print monthly and total payment(s) (format it a specific way)
        System.out.format("\nMonthly Payment: %8.2f%n", monthlyPmt);
        System.out.format("Total Payment:   %8.2f%n", monthlyPmt * years * 12);
        //print amortization schedule (format it a specific way)
        System.out.println("Payment#\t  " + "Interest\t" + "Principal\t" + "Balance");
        //for loop which is used to print out the amortization schedule (this includes all payments)
        for (int i = 1; i <= months; i++)
        {
            //calculate the amount paid and the remaining balance on the loan after payment
            interest = loanAmt * (moInterestRate / 100);
            principal = monthlyPmt - interest;
            balance = loanAmt - principal;
            //print the actual data for the amortization schedule (format it a certain way)
            System.out.format("%8d%11.2f%11.2f%12.2f\n", i, interest, principal, balance);
            //update the remaining balance (as it will be lower this time)
            loanAmt = balance;
        }
    }

    //function which calculates the monthly payment
    static double moPayment(double loanAmount, double moInterestRate, int years)
    {
        //divide the monthly interest rate by 100 (i.e. move decimal back 2 places)
        moInterestRate /= 100.0;
        return loanAmount * moInterestRate / ( 1 - 1 / Math.pow(1 + moInterestRate, years * 12.0));
    }
}
