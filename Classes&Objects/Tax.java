package com.company;

/*
    Class "Tax" -
    This class is defined per assignment instructions.
    The implementation of this class will take place in this file
    as well as the class itself.

    Written by Anish Gajera starting on Monday, February, 28 2022, for CS 2336.501
 */

//this is to implement the Tax class which is defined below
class implementTaxClass
{
    public static void main(String[] args)
    {
        //define the initial Tax objects (we will be printing the tax table for these 2 years per assignment instructions)
        //2001 Tax object
        Tax tax2001 = new Tax();
        //2009 Tax object
        Tax tax2009 = new Tax();

        //we define the 2001 tax rates here (implementing functions/methods from the Tax class itself)
        double[] rates2001 = {15, 27.5, 30.5, 35.5, 39.1};
        tax2001.setRates(rates2001);
        //tax bracket(s) per rate for all possible filing statuses for 2001
        int[][] brackets2001 = {
                //single
                {27050, 65550, 136750, 297350},
                //married jointly/or a qualifying widow(er)
                {45200, 109250, 166500, 297350},
                //married but separate filing
                {22600, 54625, 83250, 148675},
                //head of household
                {36250, 93650, 151650, 297350}
        };
        tax2001.setBrackets(brackets2001);

        //start tax table income range at $50,000 (per assignment)
        final double FROM = 50000;
        //end tax table income range at $60,000 (per assignment)
        final double TO = 60000;
        //increase each row ot the tax table by $1,000 each time (per assignment)
        final double INTERVAL = 1000;

        //print tax tables for 2001 and 2009 (per assignment)
        System.out.println("\n2001 tax tables for taxable income from $50,000 to $60,000");
        print(tax2001, FROM, TO, INTERVAL);
        System.out.println("\n2009 tax tables for taxable income from $50,000 to $60,000");
        print(tax2009, FROM, TO, INTERVAL);

    }

    //void function to print the header of the tax table and the actual table itself
    public static void print(Tax tax, double from, double to, double interval)
    {
        //print the header of the tax table (in assignment preview)
        System.out.println(
                "---------------------------------------------------------------------\n" +
                        "Taxable       Single      Married Jointly     Married        Head of\n" +
                        "Income                    or Qualifying       Separate       a House\n" +
                        "                          Widow(er)\n" +
                        "---------------------------------------------------------------------");
        //using for loop, print all necessary data for the tax table (per assignment)
        for (double taxableIncome = from; taxableIncome <= to; taxableIncome += interval)
        {
            //use mutator methods from Tax class to print necessary information
            //format each print accordingly
            tax.setTaxableIncome(taxableIncome);
            System.out.printf("%-13.0f", taxableIncome);
            tax.setFilingStatus(tax.SINGLE_FILER);
            System.out.printf("%8.2f", tax.getTax());
            tax.setFilingStatus(tax.MARRIED_JOINTLY_OR_QUALIFYING_WIDOWER);
            System.out.printf("%15.2f", tax.getTax());
            tax.setFilingStatus(tax.MARRIED_SEPARATELY);
            System.out.printf("%18.2f", tax.getTax());
            tax.setFilingStatus(tax.HEAD_OF_HOUSEHOLD);
            System.out.printf("%15.2f\n", tax.getTax());
        }
    }
}

//this is the Tax class which is used in the main function above for implementation
public class Tax
{
    //initial data fields/attributes
    private int filingStatus;
    //the 4 tax filing statuses (per assignment)
    public static final int SINGLE_FILER = 0;
    public static final int MARRIED_JOINTLY_OR_QUALIFYING_WIDOWER = 1;
    public static final int MARRIED_SEPARATELY = 2;
    public static final int HEAD_OF_HOUSEHOLD = 3;
    //tax brackets for each of the statuses will be stored in this 2D array
    private int[][] brackets;
    private double[] rates;
    private double taxableIncome;

    //construct Tax object
    Tax()
    {
        //default tax filing status will be that of a single filer
        filingStatus = SINGLE_FILER;

        //default tax rates
        double[] currentYearRates = {10, 15, 25, 28, 33, 35};;
        setRates(currentYearRates);

        //tax bracket(s) per rate for all possible filing statuses (per assignment)
        int[][] currentYearBrackets = {
                //single
                {8350, 33950, 82250, 171550, 372950},
                //married jointly/or a qualifying widow(er)
                {16700, 67900, 137050, 20885, 372950},
                //married but separate filing
                {8350, 33950, 68525, 104425, 186475},
                //head of household
                {11950, 45500, 117450, 190200, 372950}
            };
        //set bracket(s) accordingly
        setBrackets(currentYearBrackets);

        //initial/default taxable income
        taxableIncome = 0;
    }

    //construct Tax object with proper specified values for each attribute
    Tax(int filingStatus, int[][] brackets, double[] rates, double taxableIncome)
    {
        //use mutator methods to set each attribute for the object in question
        setFilingStatus(filingStatus);
        setBrackets(brackets);
        setRates(rates);
        setTaxableIncome(taxableIncome);
    }

    //mutator for filingStatus
    public void setFilingStatus(int filingStatus)
    {
        this.filingStatus = filingStatus;
    }
    //mutator for tax bracket(s)
    public void setBrackets(int[][] brackets)
    {
        this.brackets = new int[brackets.length][brackets[0].length];
        for (int i = 0; i < brackets.length; i++)
        {
            for (int j = 0; j < brackets[i].length; j++)
                this.brackets[i][j] = brackets[i][j];
        }
    }
    //mutator for tax rates
    public void setRates(double[] rates)
    {
        this.rates = new double[rates.length];
        for (int i = 0; i < rates.length; i++)
        {
            this.rates[i] = rates[i] / 100;
        }
    }
    //mutator for taxableIncome
    public void setTaxableIncome(double taxableIncome)
    {
        this.taxableIncome = taxableIncome;
    }

    //accessor for filingStatus
    public int getFilingStatus()
    {
        return filingStatus;
    }
    //accessor for tax bracket(s)
    public int[][] getBrackets()
    {
        return brackets;
    }
    //accessor for tax rates
    public double[] getRates()
    {
        return rates;
    }
    //accessor for tex
    public double getTax() {
        //initially tax is 0
        double tax = 0;
        //variable for taxed income
        double taxedIncome = 0;
        //income = all taxable income (essentially)
        double income = taxableIncome;
        if (rates.length >= 2) {
            for (int i = rates.length - 2; i >= 0; i--) {
                //if the income is greater than the one on the tax bracket
                if (income > brackets[filingStatus][i]) {
                    //calculate tax
                    tax += (taxedIncome = income - brackets[filingStatus][i]) * rates[i + 1];
                    income -= taxedIncome;
                }
            }
        }
        //return
        return tax += brackets[filingStatus][0] * rates[0];
    }
}
