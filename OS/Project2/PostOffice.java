import java.util.*;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.Random;

/*
    Project 2: Post Office Simulation (with Semaphores)

    This project uses semaphores that are used throughout the project
    to simulate a post office with 50 customers, 3 postal workers, and
    1 scale. The program uses a semaphore queue (custQueue) to keep track
    of customers since there can only be 10 customers inside the post office
    at a time. There is only 1 scale which can be used one at a time which is
    also implemented in this project.

    Written by Anish Gajera for CS 4348.002 on Saturday, March 11, 2023
 */

/*
    Interface CheckStatusInterface - This interface is used to provide
    a way for objects implementing this interface to perform an action or check
    the status of a customer in the post office (useful for monitoring
    customer progress in post office, etc. when a customer's status changes (i.e. they
    complete an action, leave the post office, etc.))
 */
interface checkStatusInterface
{
    void checkStatus(PostOffice.Customer customer);
}
/*
    Class PostOffice - This class acts as the main Post Office Simulation class
    for project 2.
 */
public class PostOffice implements checkStatusInterface
{
    //variable holds number of customers in simulation
    private static final int CUSTOMER_COUNT = 50;
    //variable holds number of postal workers in post office
    private static final int POSTAL_WORKER_COUNT = 3;
    //variable holds max number or customers that are allowed in the post office at a time
    private static final int MAX_CUSTOMERS_ALLOWED = 10;

    //semaphore for max customers allowed in post office (10)
    private static Semaphore customerLimit = new Semaphore(MAX_CUSTOMERS_ALLOWED);
    //semaphore for available postal workers (3)
    private static Semaphore postalWorkers = new Semaphore(POSTAL_WORKER_COUNT);
    //semaphore for scale in post office (there is only 1 that can be used one at a time only)
    private static Semaphore scaleAvailable = new Semaphore(1);
    //variable holds number of customers done in post office (as they get done)
    static int customersDone = 0;
    //semaphore for customer done in post office (permits 1 at a time)
    private Semaphore customerDoneSem = new Semaphore(1);
    //semaphore for postal workers to know when to exit
    private Semaphore doneSemaphore = new Semaphore(0);

    //enumerator for actions that customer can make in post office (3 - buy stamps, mail letter/package)
    enum action
    {
        //3 actions customer can perform
        BUY_STAMPS, MAIL_LETTER, MAIL_PACKAGE
    }
    /*
        Class PostalWorker - This class represents a postal worker in the post office
        simulation. A postal worker will have an id, semaphore, queue to check for customers,
        etc. that are all entailed in this class.
     */
    static class PostalWorker implements  Runnable
    {
        //postal worker id
        private int id;
        //scale semaphore
        private Semaphore scale;
        //customer queue (note: 10 customers in post office at a time)
        private custQueue<Customer> custQueue;
        public Thread thread;
        private Semaphore doneSem;

        //accessor for postal worker id
        public int getID()
        {
            //return id
            return id;
        }

        //create postal worker object
        public PostalWorker(int id, Semaphore scale, custQueue<Customer> custQueue, Semaphore doneSem)
        {
            //set values
            this.id = id;
            this.scale = scale;
            this.custQueue = custQueue;
            this.doneSem = doneSem;
            //print output
            System.out.println("Postal worker " + id + " created");
        }

        //void method run()
        @Override
        public void run() {
            while(!doneSem.tryAcquire()) //while not acquired
            {
                try {
                    //take customer from custQueue
                    Customer customer = custQueue.take();
                    //if no customer left in queue, break
                    if(customer == null)
                    {
                        break;
                    }
                    //output
                    System.out.println("Customer " + customer.getID() + " enters post office");
                    System.out.println("Postal worker " + id + " serving customer " + customer.getID());
                    //switch case - check what the customer action (from enum) is, continue accordingly
                    switch (customer.action)
                    {
                        //if BUY_STAMPS action
                        case BUY_STAMPS:
                            customer.BUY_STAMPS(this);
                        //if MAIL_LETTER action
                        case MAIL_LETTER:
                            customer.MAIL_LETTER(this);
                        //if MAIL_PACKAGE action
                        case MAIL_PACKAGE:
                            customer.MAIL_PACKAGE(this);
                    }
                    //more output
                    System.out.println("Postal worker " + id + " finished serving customer " + customer.getID());
                    System.out.println("Customer " + customer.getID() + " leaves post office");
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            try {
                //System.out.println("Joined postal worker " + id);
                this.thread.join(); //join the postal worker thread
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }

    @Override
    //function to check status of customers (if all done, etc.) - interface of this function above
    public void checkStatus(Customer customer)
    {
        //increase number of customers that are done in post office each time function is called
        customersDone++;
        System.out.println("Joined customer " + customer.getID());
        //wait(customerDoneSem);
        //customerDoneSem.acquireUninterruptibly();
        //System.out.println(customersDone); //debug statement
        if (customersDone == CUSTOMER_COUNT)
        {
            doneSemaphore.release(POSTAL_WORKER_COUNT + 1); //signal postal workers to be released
            //signal(doneSemaphore);
            //output
            //release the semaphore
            //customerDoneSem.release();
            //System.exit(0);
            //return;
        }
        try {
            customer.thread.join(); // join the customer thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
        //once all 50 customers done
        //customerDoneSem.release();

        //release the semaphore
        //customerDoneSem.release();
        /*
        do
        {
            //output
            System.out.println("All customers left the post office");
            //exit
            System.exit(0);
            //release the semaphore
            customerDoneSem.release();
        } while (customersDone == CUSTOMER_COUNT);
         */
    }

    //function for thread sleep (sleep value in milliseconds)
    private static void sleep(int milliseconds)
    {
        try {
            Thread.sleep(milliseconds); //sleep thread for x amount of milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
        Class Customer - This class represents a customer in the post office
        simulation. A customer will have an id, semaphore, action(s) they will make,
        etc. that are all entailed in this class.
     */
    class Customer implements Runnable
    {
        //customer id
        private int id;
        //customer semaphore
        private Semaphore custSem;
        //postal worker semaphore
        private Semaphore postalWorkerSem;
        //action customer makes/will make
        private action action;
        //semaphore for when customer is served
        private Semaphore custServed;
        public Thread thread;
        //check status via interface
        private checkStatusInterface checkStatus;

        //accessor method for customer id
        public int getID()
        {
            return id;
        }

        //customer constructor
        public Customer(int id, Semaphore custSem, Semaphore postalWorkerSem, checkStatusInterface checkStatus)
        {
            //this keyword to set all values
            this.id = id;
            this.custSem = custSem;
            this.postalWorkerSem = postalWorkerSem;
            this.action = PostOffice.action.values()[new Random().nextInt(action.values().length)];
            //this.action = action.values()[new Random().nextInt(action.values().length)];
            this.custServed = new Semaphore(0);
            this.checkStatus = checkStatus;
            System.out.println("Customer " + id + " created");
        }

        //function to represent customer buying stamps
        public void BUY_STAMPS(PostalWorker postalWorker)
        {
            //output
            System.out.println("Customer " + id + " asks postal worker " + postalWorker.getID() + " to buy stamps");
            //sleep for 1 second
            sleep(1000); //1000 milliseconds = 1 second
            System.out.println("Customer " + id + " finished buying stamps");
            //release current semaphore once stamp is bought/customer is served
            signal(custServed);
            //custServed.release();
        }

        //function to represent customer mailing letter
        public void MAIL_LETTER(PostalWorker postalWorker)
        {
            System.out.println("Customer " + id + " asks postal worker " + postalWorker.getID() + " to mail a letter");
            //sleep for 1.5 seconds
            sleep(1500); //1500 milliseconds = 1.5 seconds
            System.out.println("Customer " + id + " finished mailing a letter");
            //release current semaphore once letter is sent/customer is served
            signal(custServed);
            //custServed.release();
        }

        //function to represent customer mailing package
        public void MAIL_PACKAGE(PostalWorker postalWorker)
        {
            System.out.println("Customer " + id + " asks postal worker " + postalWorker.getID() + " to mail a package");
            try {
                scaleAvailable.acquire();
                System.out.println("Scales in use by postal worker " + postalWorker.getID());
                //sleep for 2 seconds
                sleep(2000); //2000 milliseconds = 2 seconds
                System.out.println("Customer " + id + " finished mailing a package");
                System.out.println("Scales released by postal worker " + postalWorker.getID());
                //release scale semaphore so that its avaialble for another postal worker to use
                scaleAvailable.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //release current semaphore once package is sent/customer is served
            signal(custServed);
            //custServed.release();
        }

        @Override
        //void run - check if customer has been served - if so release the customer semaphore and others as necessary
        public void run() {
            PostOffice.this.wait(custSem);
            //custSem.acquire();
            try {
                PostOffice.this.wait(postalWorkerSem);
                //postalWorkerSem.acquire();
                PostOffice.this.wait(custServed);
                //custServed.acquire();
            } finally {
                signal(postalWorkerSem);
                //postalWorkerSem.release();
                signal(custSem);
                //custSem.release();
                if(checkStatus != null)
                {
                    checkStatus.checkStatus(this);
                }
            }
        }
    }

    //function main() - where simulation is ran from
    public static void main(String[] args)
    {
        PostOffice test = new PostOffice();
        test.simulatePostOffice();
        //PostOffice.simulatePostOffice();
        //System.out.println("HELLO HELLO HOLA"); //debug statement
        System.exit(0);
    }

    public void simulatePostOffice()
    {
        System.out.println("Simulating Post Office with 50 customers and 3 postal workers\n");

        //create new custQueue
        custQueue<Customer> customerQueue = new custQueue<>();

        //postal worker thread(s) creation and start
        for(int i = 0; i < POSTAL_WORKER_COUNT; i++)
        {
            PostalWorker postalWorker = new PostalWorker(i, scaleAvailable, customerQueue, doneSemaphore);
            Thread t = new Thread(postalWorker);
            postalWorker.thread = t;
            t.start();
        }

        //customer thread(s) creation and start
        for(int i = 0; i < CUSTOMER_COUNT; i++)
        {
            Customer customer = new Customer(i, customerLimit, postalWorkers, this);
            Thread t = new Thread(customer);
            customer.thread = t;
            t.start();
            try {
                customerQueue.add(customer);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        //make sure all customers served
        /*try {
            customerQueue.emptyCustQueue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //when done, stop postal worker threads
        customerQueue.setFinished();
        //postalWorkers.release();
        //System.out.println("YOOOOOOOOOOOOOOOO");
        wait(doneSemaphore);
        //System.exit(0);
    }

    //void function wait acquires the semaphore
    public void wait(Semaphore semaphore)
    {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    //void function signal releases the semaphore
    public void signal(Semaphore semaphore)
    {
        semaphore.release();
    }

    public void acqUni(Semaphore sem)
    {
        sem.acquireUninterruptibly();
    }
}

/*
    Class custQueue - This class will act as a (thread safe) queue for customers (type Customer)
    entering and exiting the post office since there can only be 10 inside at a time. Class uses
    a linked list for the queue and semaphores to check for/handle customers.
 */
class custQueue<Customer>
{
    //empty queue object with type "Customer"
    private Queue<Customer> custQueue = new LinkedList<>();
    //semaphore for mutually excluded customer(s)
    private Semaphore custMutex = new Semaphore(1);
    //
    private Semaphore element = new Semaphore(0);
    //semaphore for finished customer(s)
    private Semaphore custFinished = new Semaphore(0);
    //boolean holds if customer is finished or not
    private boolean finished = false;

    //function add
    public void add(Customer customer) throws InterruptedException
    {
        //acquire customer
        custMutex.acquire();
        //add customer
        custQueue.add(customer);
        custMutex.release();
        element.release();
    }

    //function to take customer from queue
    public Customer take() throws InterruptedException
    {
        //acquire
        element.acquire();
        //acquire customer
        custMutex.acquire();
        Customer customer = null;
        try {
            if (finished && custQueue.isEmpty())
            {
                customer = null;
                //element.release();
            }
            else
            {
                customer = custQueue.remove();
            }
        } finally {
            custMutex.release();
        }
        return customer;
    }

    //function that establishes that queue is finished, essentially ending post office simulation
    public void setFinished()
    {
        custMutex.acquireUninterruptibly();
        finished = true;
        custMutex.release();
        element.release();
    }

    //function to check if queue is empty
    public void emptyCustQueue() throws InterruptedException
    {
        //acquire value of custFinished semaphore (happens from main)
        custFinished.acquire();
    }
}

/* Notes:

//enhanced switch case - check what the customer action (from enum) is, continue accordingly
                    switch (customer.action)
                    {
                        //if BUY_STAMPS action
                        case BUY_STAMPS -> customer.BUY_STAMPS(this);
                        //if MAIL_LETTER action
                        case MAIL_LETTER -> customer.MAIL_LETTER(this);
                        //if MAIL_PACKAGE action
                        case MAIL_PACKAGE -> customer.MAIL_PACKAGE(this);
                    }


 */
