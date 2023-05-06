package com.company;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.Stack;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/*
    Memory - This class mocks CPU Memory (fills in memory from given input files)

    Written by Anish Gajera for CS 4348.002 on Saturday, February 18, 2023
 */

public class Memory
{
    //array variable - reperesents memory
    static int[] memory;

    //function to read address
    /**
     *
     * @param address - address to read
     * @return - return the address value read
     */
    private static int read(int address)
    {
        //return the value of memory at the address requested (array[index] where index is the address)
        return memory[address];
    }

    //function to write to address
    /**
     *
     * @param address - address to write to
     * @param data - data to write to address
     */
    private static void write(int address, int data)
    {
        //set memory at requested address to data (array[index] where index is the address)
        memory[address] = data;
    }

    //main function
    /**
     *
     * @param args - file to read
     */
    public static void main(String [] args)
    {
        System.out.println("whatever");
        //input validation - check if file name is valid
        if(args.length != 1)
        {
            //if not valid, send error message
            System.err.println("error: input file required");
            //exit status 1 indicates file upload error
            System.exit(1);
        }
        //variable to hold filename from input
        String fileName = args[0];
        //run with try-catch block
        try {
            //fill memory with contents from file
            fillMemory(fileName);
            /*for (int i = 0; i < 20; i++)
            {
                System.out.println(memory[i]);
            }*/
            //scanner object to read from CPU
            Scanner scan = new Scanner(System.in);
            //while scanner reading file
            while(scan.hasNextLine())
            {
                //get next line
                String line = scan.nextLine();
                //store the instruction from line in file
                char instruction = line.charAt(0);
                //variables for address and data to store at address
                int address, data;
                //switch case for what instruction is in file
                switch (instruction)
                {
                    case 'r': //r = read - read instruction using read method - substring (from 1-end of instr)
                        System.out.println(read(Integer.parseInt(line.substring(1))));
                        break;
                    case 'w': //w = write - write instruction using write method
                        String[] temp = line.substring(1).split(",");
                        //address to write to
                        address = Integer.parseInt(temp[0].substring(1));
                        //data to write to address
                        data = Integer.parseInt(temp[0]);
                        //write data to address
                        write(address, data);
                        break;
                    case 'e': //e = end - end process
                        //exit to end process
                        System.exit(0);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //function to fill memory
    /**
     *
     * @param filename - name of file
     * @throws FileNotFoundException - execption if file does not exist
     */
    private static void fillMemory(String filename) throws FileNotFoundException
    {
        //consists of 2000 integer entries, 0-999 for the user program, 1000-1999 for system code.
        memory = new int[2000];
        //index variable to keep track of memory fill
        int memIndex = 0;
        //scanner object which scans new file name
        Scanner scan = new Scanner(new File(filename));

        //parse the file line by line
        while(scan.hasNextLine())
        {
            String line = scan.nextLine().trim();
            //empty line case
            if(line.length() == 0)
            {
                //System.out.println("BLANK");
                continue;
            }
            //if the first character in the line is '.', it signifies jump to the address follwing the character
            if(line.charAt(0) == '.')
            {
                //System.out.println("JUMP : " + line);
                //variable loadAddr which parses to the substring of the instruction that has '.' character so that it can load to that address
                int loadAddr = Integer.parseInt(line.substring(1));
                //set index in memory to the address we want to load to (this will be the new index aka address in memory)
                memIndex = loadAddr;
                continue;
            }
            //if the line has no address, continue without skipping load address
            if(line.charAt(0) <'0' || line.charAt(0) > '9')
            {
                //System.out.println("SKIP");
                continue;
            }
            //final case if the instruction/value is found, then increment index (move to next address in memory) and split line to extract exact values
            String[] extract = line.split("\\s+");
            memory[memIndex] = Integer.parseInt(extract[0]);
            memIndex++;
            //System.out.println("NORMAL: " + line);
        }
        scan.close();
    }
}
