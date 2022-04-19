package com.company;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;


public class CS2336Asg4_AAG190007
{
    //should there be a throws FileNotFoundException or throws IOException? ask Professor Cole
    public static void main(String[] args) throws IOException
    {
        //create object of listClass (i.e. the linked list)
        listClass list = new listClass();
        //scanner scan - used to take in file name from user
        Scanner scan = new Scanner(System.in);
        //user prompted to enter the filename
        System.out.println("Enter file name: ");
        //variable fileName is taken from the user using the scanner object
        String fileName = scan.nextLine();
        //file object with fileName entered by the user
        File file = new File(fileName);
        //input validation - if file does not exist (invalid file name)
        while (!file.exists())
        {
            System.out.println("File not found. Try again: ");
            fileName = scan.nextLine();
            file = new File(fileName);
        }
        //scanner for the reading of the file (2 ways to do this as well)
        //2nd way: use BufferedReader - BufferedReader sc = new BufferedReader(new FileReader(fileName)); ?
        BufferedReader sc = new BufferedReader(new FileReader(fileName));
        //Scanner sc = new Scanner(new FileReader(fileName));
        //line of text being referenced/used is assigned using the scanner
        String lineOfText = sc.readLine();
        int lineNumber = 0;//linenumber initially set to 0
        while (lineOfText != null)
        {
            //if the line of text is just an empty string, then it can be ignored
            if(lineOfText.equals(""))
            {
                lineOfText =  sc.readLine();
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
            for(int i = 0; i < lineSplit.length; i++)
            {
                //if where the line is split after punctuation is an empty string or a whitespace, then continue till find the next line of text
                if (lineSplit[i].equals("") || lineSplit[i].equals(" "))
                {
                    continue;
                }
                //add each word to the linked list after removing its punctuation
                //list.addToList or list.add? Review this**
                //list = list.addToList(list, lineSplit[i], String.valueOf(lineNumber));
                list.addToList(lineSplit[i], String.valueOf(lineNumber));
            }
            lineOfText = sc.readLine();
        }
        //new line for spacing
        System.out.print("\n");
        //sort list as we need to (i.e. in alphabetical order)
        list.sortList(list);
        //output the list
        list.output(list);
        //print total number of lines (per assignment criteria)
        System.out.println("Number of lines: " + lineNumber);
    }
}

/* Notes:
    //to check for file path (done with Professor Cole)
    PrintWriter pw = new PrintWriter("zzz.txt");
    pw.write("abc");
    pw.close();
 */
