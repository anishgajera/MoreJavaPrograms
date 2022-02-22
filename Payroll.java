package com.company;
import java.util.*;
import java.util.Scanner;

/*
    HW2 - Payroll
    This program accepts necessary input from the user
    which it then uses to calculate the user's
    payroll (per assignment instructions).

    Written by Anish Gajera starting on Tuesday, February 15, 2022, for CS 2336.501
 */

public class Payroll
{
    public static void main(String[] args)
    {
        //variables that hold the user's name and additional info (per assignment)
        String name;
        double hours, payRate, fedTax, stTax;
        //scanner object
        Scanner scan = new Scanner(System.in);
        //prompt user for necessary information and store it in the corresponding variables accordingly
        //name
        System.out.print("Enter employee's name: ");
        name = scan.nextLine();
        //hours worked
        System.out.print("Enter hours worked: ");
        hours = scan.nextDouble();
        //pay rate
        System.out.print("Enter hourly pay rate: ");
        payRate = scan.nextDouble();
        //federal tax
        System.out.print("Enter federal tax withholding rate: ");
        fedTax = scan.nextDouble();
        //state tax
        System.out.print("Enter state tax withholding rate: ");
        stTax = scan.nextDouble();
        //output accordingly
        System.out.println("\nEmployee name: " + name);
        System.out.println("Hours worked: " + hours);
        System.out.println("Pay rate: $" + payRate);
        //variable which holds the gross pay
        double gross = payRate * hours;
        System.out.println("Gross pay: $" + gross);
        //output deductions (what gets taken out after taxes applied)
        System.out.println("Deductions: ");
        //variable which holds the federal deduction from gross pay amount
        double fedDeduction = gross * fedTax;
        //variable which holds the state deduction from gross pay amount
        double stDeduction = gross * stTax;
        System.out.print("\tFederal withholding (" + (fedTax * 100) + "%): $");
        System.out.format("%.2f%n", fedDeduction);
        System.out.print("\tState withholding (" + (stTax * 100) + "%): $");
        System.out.format("%.2f%n", stDeduction);
        //variable which holds the total deduction
        double totalDeduction = fedDeduction + stDeduction;
        System.out.print("\tTotal deduction: $");
        System.out.format("%.2f%n", totalDeduction);
        //output net pay (gross - federal - state)
        System.out.print("Net pay: $");
        System.out.format("%.2f%n", (gross - totalDeduction));
    }
}
