package com.company;
import java.util.Scanner;

/*
    Note: This file will be used to execute the ATM machine in the
    assignment. Since the assignment states that the process
    for checking different accounts in the ATM machine will not
    end, this file will be able to continue until the user
    decides to quit running the program.

    Class "ATM"
    This class uses the Account class to simulate an
    ATM machine (per assignment instructions).

    Written by Anish Gajera starting on Monday, February, 28 2022, for CS 2336.501
 */

import java.util.ArrayList;

public class ATM
{
    public static void main(String[] args) 
    {
        //create an array to store the 10 accounts (per assignment)
        Account[] accounts = new Account[10];
        //create and store 10 accounts in the array
        for (int i = 0; i < accounts.length; i++) {
            //store 10 accounts, with id numbers 1-9, initial balance 100; ignore last field in this case)
            accounts[i] = new Account(i, 100, 0);
        }
        //scanner object
        Scanner scan = new Scanner(System.in);
        //prompt user to enter id
        System.out.print("Enter an id: ");
        int id = scan.nextInt();
        System.out.print("\n");
        //check if id is valid, if not then keep asking
        while (id < 1 || id > 9)
        {
            System.out.print("Invalid id, please try again: ");
            id = scan.nextInt();
        }
        while (true)
        {
            //display the main menu
            menu();
            int choice;
            choice = scan.nextInt();
            //switch case which provides all main menu options for the ATM
            switch (choice)
            {
                //case 1 is to check balance
                case 1:
                    System.out.println("The balance is: " + accounts[id].getBalance() + "\n");
                    break;
                //case 2 is to withdraw money
                case 2:
                    double withdraw;
                    //user enters amount to withdraw from the amount
                    System.out.print("Enter amount to withdraw: ");
                    withdraw = scan.nextDouble();
                    //for the account id entered, update the account with the new balance(post withdraw)
                    accounts[id] = new Account(id, accounts[id].getBalance() - withdraw, 0);
                    System.out.print("\n");
                    break;
                //case 3 is to deposit money
                case 3:
                    double deposit;
                    //user enters amount to deposit
                    System.out.print("Enter amount to deposit: ");
                    deposit = scan.nextDouble();
                    //for the account id entered, update the account with the new balance(post deposit)
                    accounts[id] = new Account(id, accounts[id].getBalance() + deposit, 0);
                    System.out.print("\n");
                    break;
                //case 4 is to exit, but since the program will run forever (per assignment instructions), this case will allow the re-run
                case 4:
                    //prompt user to enter id
                    System.out.print("Enter an id: ");
                    id = scan.nextInt();
                    System.out.print("\n");
                    //check if id is valid, if not then keep asking
                    while (id < 1 || id > 9)
                    {
                        System.out.print("Invalid id, please try again: ");
                        id = scan.nextInt();
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }

    //function to display the menu
    public static void menu()
    {
        System.out.println("Main Menu:\n1: Check balance\n2: Withdraw\n3: Deposit\n4: Exit");
        System.out.print("Enter a choice: ");
    }

}

/*Notes: (Ignore)

Account signature: Account(int newId, double initBalance, double annualIntRate)

 */