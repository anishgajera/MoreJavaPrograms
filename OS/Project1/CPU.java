package com.company;
import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.Stack;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.File;
import java.io.FileNotFoundException;

/*
    CPU - This class mocks a CPU with instructions/registers to run a program

    Written by Anish Gajera for CS 4348.002 on Saturday, February 18, 2023
 */

public class CPU
{
    //register variables
    private int PC, SP, IR, AC, X, Y;
    //timer variables
    private int timer, timerInterrupt;
    //stack for saving state of register
    private Stack<Integer> saveRegister;
    //kernel mode
    private boolean kernelMode;
    //comms variables
    private Scanner memoryOutput;
    private PrintWriter memorySend;

    /**
     * (Step 1) Create CPU object which has timer and communicates with memory
     *
     * @param memoryOutput - reads memory output
     * @param memorySend - sends information to memory
     * @param timerInterrupt - interrupt interval specifier
     */
    public CPU(Scanner memoryOutput, PrintWriter memorySend, int timerInterrupt)
    {
        //use "this" keyword to set values of params to values of actual variables
        this.memoryOutput = memoryOutput;
        this.memorySend = memorySend;
        this.timerInterrupt = timerInterrupt;
        //not in kernel mode
        kernelMode = false;
        PC = IR = AC = X = Y = 0;
        timer = 0;
        SP = 1000;
        //initialize new save register (new stack acts as save register for CPU state)
        saveRegister = new Stack<>();
    }

    /**
     * Driver function - executes program using file with CPU instructions to memory
     * @param args - file which contains instructions along with interrupt interval
     */
    public static void main(String[] args)
    {
        //input validation - CPU needs two values (fileName and interrupt interval value) - if not in args, exit
        if(args.length < 2)
        {
            System.err.println("Too few arguments");
            //exit status 1 indicates file upload error
            System.exit(1);
            //System.exit(0);
        }
        //variable to hold file name (will be args array value 0)
        String fileName = args[0];
        //variable to hold interrupt interval value (will be args array value 1)
        int timerInterrupt = Integer.parseInt(args[1]);

        //try/catch block for memory process
        try {
            //process creation
            Runtime runtime = Runtime.getRuntime();
            Process memory = runtime.exec("java memory " + fileName);
            File dataset = new File(args[0]);
            /* Scanner test = new Scanner(dataset);
            while (test.hasNextLine()) {
                String i = test.nextLine();
                System.out.println(i);
            }
            test.close();
            */
            //comms channel
            //Scanner memoryOutput = new Scanner(dataset);
            Scanner memoryOutput = new Scanner(memory.getInputStream());
            PrintWriter memorySend = new PrintWriter(memory.getOutputStream());

            //execute
            CPU cpu = new CPU(memoryOutput, memorySend, timerInterrupt);
            //System.out.println(memoryOutput.hasNextLine());
            cpu.execute();
        }
        catch (IOException e) { //catch exception
            e.printStackTrace();
            //print error message
            System.err.println("Error: unable to create process");
            //system exit
            System.exit(1);
        }
    }

    /**
     * function to read memory address
     *
     * @param address - address in memory
     * @return - the value stored in the address
     */
    private int readAddress(int address)
    {
        //input validation - make sure access to certain addresses is only for kernel mode (1000+ only kernel mode)
        if(address >= 1000 && !kernelMode)
        {
            //if the address is inaccessible, print error
            System.err.println("error: kernel mode required for access to memory address 1000");
            //System.err.println("error: accessing system address 1000 in user mode");
            //system exit -1
            System.exit(-1);
        }
        //communicate to memory and flush channel
        memorySend.println("r" + address);
        memorySend.flush();
        //return
        String line = "";
        if (memoryOutput.hasNextLine())
            line = memoryOutput.nextLine();
        //String[] extract = memoryOutput.nextLine().split("\\s+");
        return Integer.parseInt(line);
    }

    /*
        fetch() - this function fetches instructions from memory (from an address in program counter,
        program counter is then updated)
     */
    private void fetch()
    {
        System.out.println("fetch! " + PC);
        //set instruction register value to the address of the PC counter
        IR = readAddress(PC);
        //System.out.println("PC: " + PC);
        //increment the PC counter
        PC++;
    }

    /**
     * function to write to memory
     *
     * @param address - address in memory to write to
     * @param data - data being written to address
     */
    private void write(int address, int data)
    {
        //send to memory (write to memory)
        memorySend.println("w" + address + "," + data);
        //flush
        memorySend.flush();
    }

    /**
     * function to push to stack
     *
     * @param data - data to push onto stack (memory)
     */
    private void push(int data)
    {
        //decrease the stack pointer
        SP--;
        //push to stack
        write(SP, data);
    }

    /*
        function to go into kernel mode (i.e. system mode)
        set kernel to true and push necessary values to save register
     */
    private void setKernelMode()
    {
        //set variable to true
        kernelMode = true;
        //temp stack pointer (SP)
        int temp = SP;
        //set SP to 2000 (only accessible by kernel)
        SP = 2000;
        push(temp); //push temp SP to stack
        push(PC); //push PC to stack
        saveRegister.push(IR); //push IR to save register
        saveRegister.push(AC); //push AC to save register
        saveRegister.push(X); //push X to save register
        saveRegister.push(Y); //push Y to save register
    }

    //function to execute instructions
    private boolean iExecute()
    {
        System.out.println("IR: " + IR);
        //use switch case for instructions from IR
        switch (IR)
        {
            case 1: //1 = Load value into AC
                //fetch() will be called in each case to actually fetch the instruction
                fetch();
                AC = IR;
                break;
            case 2: //2 = Load addr into AC
                fetch();
                AC = readAddress(IR);
                break;
            case 3: //3 = LoadInd addr - Load the value from the address found in the given address into the AC
                fetch();
                //storing address of the address of IR into AC
                //(for example, if LoadInd 500, and 500 contains 100, then load from 100)
                AC = readAddress(readAddress(IR));
                break;
            case 4: //4 = LoadIdxX addr - Load the value at (address+X) into the AC
                fetch();
                //(for example, if LoadIdxX 500, and X contains 10, then load from 510).
                System.out.println("ADDRESS: " + IR);
                AC = readAddress(IR + X);
                System.out.println("X VAL: " + X);
                System.out.println("AC: " + AC);
                break;
            case 5: ////5 = LoadIdxX addr - Load the value at (address+Y) into the AC
                fetch();
                //(for example, if LoadIdxX 500, and Y contains 10, then load from 510).
                AC = readAddress(IR + Y);
                break;
            case 6: //6 = LoadSpX - Load from (Sp+X) into the AC (if SP is 990, and X is 1, load from 991)
                //fetch();
                //stack pointer + value of X into AC register
                AC = readAddress(SP + X);
                break;
            case 7: //7 = Store addr - Store the value in the AC into the address
                fetch();
                //write whatever is in AC register to IR register
                write(IR, AC);
                break;
            case 8: //8 = Get - Gets a random int from 1 to 100 into the AC
                //fetch();
                //variable randomInt holds the generated random integer (casted to int)
                int randomInt = (int)(Math.random() * 100) + 1;
                AC = randomInt;
                break;
            case 9: //9 = Put port - If port=1, writes AC as an int to the screen; if port=2, writes AC as a char to the screen
                fetch();
                //if port == 1
                if (IR == 1)
                {
                    //print AC (as int) to screen
                    System.out.print(AC);
                }
                else if (IR == 2) //if port == 2
                {
                    //print AC (as char) to screen
                    System.out.print((char)AC);
                }
                break;
            case 10: //10 = AddX - Add the value in X to the AC
                AC += X;
                break;
            case 11: //11 = AddX - Add the value in Y to the AC
                AC += Y;
                break;
            case 12: //12 = SubX - Subtract the value in X from the AC
                AC -= X;
                break;
            case 13: //13 = SubY - Subtract the value in Y from the AC
                AC -= Y;
                break;
            case 14: //14 = CopyToX - Copy the value in the AC to X
                X = AC;
                break;
            case 15: //15 = CopyFromX - Copy the value in X to the AC
                AC = X;
                break;
            case 16: //16 = CopyToY - Copy the value in the AC to Y
                Y = AC;
                break;
            case 17: //15 = CopyFromY - Copy the value in Y to the AC
                AC = Y;
                break;
            case 18: //18 = CopyToSp - Copy the value in AC to the SP
                SP = AC;
                break;
            case 19: //19 = CopyFromSp - Copy the value in SP to the AC
                AC = SP;
                break;
            case 20: //20 = Jump addr - Jump to the address
                fetch();
                //set the PC reg to IR reg to jump
                PC = IR;
                break;
            case 21: //21 = JumpIfEqual addr - Jump to the address only if the value in the AC is zero
                fetch();
                if (AC == 0) //if the AC value is 0
                {
                    PC = IR; //then jump
                }
                break;
            case 22: //22 = JumpIfNotEqual addr - Jump to the address only if the value in the AC is not zero
                fetch();
                if (AC != 0) //if the AC value is not equal to 0
                {
                    PC = IR;
                }
            case 23: //23 = Call addr - Push return address onto stack, jump to the address
                fetch();
                //first push to stack
                push(PC);
                //then jump to address
                PC = IR;
                break;
            case 24: //24 = Ret - Pop return address from the stack, jump to the address
                PC = pop();
                break;
            case 25: //25 = IncX - Increment the value in X
                X++;
                break;
            case 26: //26 = DecX - Decrement the value in X
                X--;
                break;
            case 27: //27 = Push - Push AC onto stack
                //call push function to push to stack - dont need to do "SP--;" since we already account for it in function itself
                push(AC);
                break;
            case 28: //28 = Pop - Pop from stack into AC
                AC = pop();
                break;
            case 29: //29 = Int - Perform system call (aka syscall)
                //if not in kernel mode
                if(!kernelMode)
                {
                    //set to kernel mode
                    setKernelMode();
                    //set PC to 1500 (syscall)
                    PC = 1500;
                }
                break;
            case 30: //30 = IRet - Return from system call
                //set values of registers and variables by popping from save register (stack)
                AC = saveRegister.pop();
                IR = saveRegister.pop();
                PC = saveRegister.pop();
                Y = saveRegister.pop();
                X = saveRegister.pop();
                SP = saveRegister.pop();
                //make sure no longer in kernel mode (set variable to false)
                kernelMode = false;
                break;
            case 50: //50 = End - End execution
                //call terminate() to end execution
                terminate();
                //return false
                return false;
            default: //default - no valid instruction
                System.err.println("No valid instructions found");
                terminate();
                return false;
        }
        return true;
    }

    //function to pop value(s) from stack (memory)
    /**
     *
     * @return - value being popped off stack
     */
    private int pop()
    {
        //variable holds value that is being popped
        int popping = readAddress(SP);
        //increment the stack pointer since we are popping
        SP++;
        //return the value we popped from stack
        return popping;
    }

    //function to end process
    private void terminate()
    {
        memorySend.println("e"); //print e
        //flush
        memorySend.flush();
    }

    //function to execute program/instruction(s)
    public void execute()
    {
        System.out.println("cpu executed");
        System.out.println(memoryOutput.nextLine());
        //boolean var for running state
        boolean running = true;
        //while running = true
        while (running)
        {
            //fetch instruction from IR/PC
            fetch();
            //set running to true (instructions switch case)
            running = iExecute();
            //start timer as normal timer
            timer++;
            //check for timer interrupt
            if (timer >= timerInterrupt)
            {
                //if not in kernel mode
                if(!kernelMode)
                {
                    //reset timer
                    timer = 0;
                    //set kernel mode
                    setKernelMode();
                    //set PC register to 1000 (per assignment instructions)
                    PC = 1000;
                }
            }
        }
    }
}
