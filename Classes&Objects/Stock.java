package com.company;

/*
    Class "Stock" -
    This class is used to create stock objects.
    The stock will be given a ticker symbol, a name,
    and will hold its previous and current closing prices.
    This class also has other methods and attributes (per assignment
    instructions).

    Written by Anish Gajera starting on Monday, February, 28 2022, for CS 2336.501
 */

public class Stock
{
    //data field that holds the ticker symbol of the stock
    String symbol;
    //data field which holds the stocks' name
    String name;
    //data field to hold previous closing price
    double previousClosingPrice;
    //data field to hold current price
    double currentPrice;

    //construct a stock object (no-arg constructor)
    Stock()
    {
    }

    //constructor which creates a stock with its designated symbol and name
    Stock(String newSymbol, String newName)
    {
        symbol = newSymbol;
        name = newName;
    }

    //constructor which obtains and returns the percent change of price(s)
    double getChangePercent(double prePrice, double newPrice)
    {
        previousClosingPrice = prePrice;
        currentPrice = newPrice;

        double change = currentPrice - previousClosingPrice;
        if (change < 0)
        {
            change *= -1;
        }
        double percentChange = (change / previousClosingPrice) * 100;

        return percentChange;
    }


}
