package com.company;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.BufferedReader;


public class CS2336Asg5_AAG190007
{
    public static void main(String[] args) throws IOException
    {
        //create an object of the BST class
        BSTClass BST = new BSTClass();
        //scanner scan - used to take in file name from user
        Scanner scan = new Scanner(System.in);
        //user prompted to enter the filename
        System.out.println("Enter file name: ");
        //variable fileName is taken from the user using the scanner object
        String fileName = scan.nextLine();
        //file object with fileName entered by the user
        File file = new File(fileName);
        //input validation - if file does not exist (invalid file name)
        while (!file.exists()) {
            System.out.println("File not found. Try again: ");
            fileName = scan.nextLine();
            file = new File(fileName);
        }
        //scanner for the reading of the file (2 ways to do this as well)
        //2nd way: use BufferedReader - BufferedReader sc = new BufferedReader(new FileReader(fileName)); ?
        //Scanner sc = new Scanner(new FileReader(fileName));
        BufferedReader sc = new BufferedReader(new FileReader(fileName));
        //line of text being referenced/used is assigned using the scanner
        String lineOfText = sc.readLine();
        int lineNumber = 0;//linenumber initially set to 0
        while (lineOfText != null)
        {
            //if the line of text is just an empty string, then it can be ignored
            if (lineOfText.equals(""))
            {
                lineOfText = sc.readLine();
                continue;
            }
            //since we need to ignore the words: "the," "a," and "an," we can replace all valid possible cases of these words
            //replace all of the words with empty strings, therefore they are avoided as mentioned in the code directly above
            lineOfText = lineOfText.replaceAll("The ", "");
            lineOfText = lineOfText.replaceAll("the ", "");
            lineOfText = lineOfText.replaceAll(" The", "");
            lineOfText = lineOfText.replaceAll(" A", "");
            lineOfText = lineOfText.replaceAll("a ", "");
            lineOfText = lineOfText.replaceAll(" An", "");
            lineOfText = lineOfText.replaceAll("an ", "");

            //if the line has any punctuation consisting of things like: "? , . !;:-." - it can be split where there is this punctuation
            String[] lineSplit = lineOfText.split(",|\\.|\\?|\\s|!|:|;|-");
            lineNumber++;//increase the line number
            System.out.println(lineNumber + " " + lineOfText);//display line number and line of text
            for (int i = 0; i < lineSplit.length; i++)
            {
                //if where the line is split after punctuation is an empty string or a whitespace, then continue till find the next line of text
                if (lineSplit[i].equals("") || lineSplit[i].equals(" "))
                {
                    continue;
                }
                //add each word to the BST after removing its punctuation
                //BST = BST.addToTree(BST, lineSplit[i], String.valueOf(lineNumber));
                BST = BST.addToTree(BST, lineSplit[i], String.valueOf(lineNumber));
            }
            lineOfText = sc.readLine();
        }
        //new line for spacing
        System.out.print("\n");
        //ouput the BST
        BST.output(BST);
        //print total number of lines (per assignment criteria)
        System.out.println("Number of lines: " + lineNumber);

    }
}
