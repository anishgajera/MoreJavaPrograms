package com.company;

/*
    Class "Rectangle" -
    This class is used to create rectangle objects
    (per assignment instructions) and has multiple
    attributes and methods within.

    Written by Anish Gajera starting on Monday, February, 28 2022, for CS 2336.501
 */

public class Rectangle
{
    double width = 1;
    double height = 1;

    //construct a rectangle object (no-arg constructor)
    Rectangle()
    {
    }

    //create a rectangle with width and height 1 (since width/height are both 1 as above)
    Rectangle(double newWidth, double newHeight)
    {
        width = newWidth;
        height = newHeight;
    }

    //return the area of this rectangle
    double getArea()
    {
        return width * height;
    }

    //return the perimeter of this rectangle
    double getPerimeter()
    {
        return (2 * width) + (2 * height);
    }

    //set a new width/height for the rectangle
    void setWidthHeight(double newWidth, double newHeight)
    {
        width = newWidth;
        height = newHeight;
    }
}
