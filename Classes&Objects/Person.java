package com.company;

/*
    Class "Person" -
    This class has various other subclasses
    which are defined in this file. The Person
    class is the superclass while the Student and
    Employee are subclasses of Person. The
    Faculty and Staff classes are subclasses of
    Employee. All of this is done per assignment
    instructions.

    Written by Anish Gajera starting on Monday, February, 28 2022, for CS 2336.501
 */

//class Person is a superclass for all the other classes in this .java file (all done per assignment instructions)
public class Person
{
    //4 attributes of a person (per assignment)
    private String name;
    private String address;
    private String phone;
    private String email;

    //construct a person object (no-arg constructor)
    public Person()
    {
        //initially, all the fields would not be available till the mutator methods are called to set them
        this("N/A", "N/A", "N/A", "N/A");
    }

    //construct person object by giving it its corresponding values (use "this" keyword as mentioned in class)
    public Person (String name, String address, String phone, String email)
    {
        //the "this" keyword refers to the current object in the method/constructor
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    //mutator for name
    public void setName(String name)
    {
        this.name = name;
    }
    //mutator for address
    public void setAddress(String address)
    {
        this.address = address;
    }
    //mutator for phone
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    //mutator for email
    public void setEmail(String email)
    {
        this.email = email;
    }


    //accessor for name
    public String getName()
    {
        return name;
    }
    //accessor for address
    public String getAddress()
    {
        return address;
    }
    //accessor for phone
    public String getPhone()
    {
        return phone;
    }
    //accessor for email
    public String getEmail()
    {
        return email;
    }

    //toString method of the class (per assignment)
    public String toString()
    {
        //in-line return
        return "\nName: " + name + "\nAddress: " + address + "\nPhone: " + phone + "\nEmail: " + email;
    }
}

class Student extends Person
{
    //THE ATTRIBUTES
    //each student has a class status, have it be an integer
    private int status;
    //declare the 4 statuses as finals/constants (per assignment) assigning each one an int value
    public final static int FRESHMAN = 1;
    public final static int SOPHOMORE = 2;
    public final static int JUNIOR = 3;
    public final static int SENIOR = 4;

    //essentially create a student object (from class Person)
    public Student(String name, String address, String phone, String email, int status)
    {
        //super keyword refers to attributes from the parent class object (in this case class Person)
        super(name, address, phone, email);
        this.status = status;
    }

    //mutator for status
    public void setStatus(int status)
    {
        this.status = status;
    }

    //accessor for status
    public String getStatus()
    {
        //use switch case, if the status is 1-4 assign one of the 4, else it's an unknown status
        switch (status)
        {
            case 1:
                return "Freshman";
            case 2:
                return "Sophomore";
            case 3:
                return "Junior";
            case 4:
                return "Senior";
            default:
                return "Unknown";
        }
    }

    //to-string method of the class (per assignment)
    public String toString()
    {
        //in-line return; super keyword to access attributes in parent class, etc.
        return super.toString() + "\nStatus: " + getStatus();
    }
}

class Employee extends Person
{
    //initial data fields
    private int office;
    private double salary;
    //this object is created using the MyDate class from exercise 10.14
    private MyDate dateHired;

    //construct Employee object (subclass of Person class, therefore, shares certain attributes)
    public Employee(String name, String address, String phone, String email, int office, double salary)
    {
        //super used to access parent class attributes (parent class is Person)
        super(name, address, phone, email);
        this.office = office;
        this.salary = salary;
        this.dateHired = new MyDate();
    }

    //accessor for office
    public int getOffice()
    {
        return office;
    }
    //accessor for salary
    public double getSalary()
    {
        return salary;
    }
    //accessor for dateHired
    public String getDateHired()
    {
        //get and return the date hired and format it in MM/DD/YYYY format
        return dateHired.getMonth() + "/" + dateHired.getDay() + "/" + dateHired.getYear();
    }

    //mutator for office
    public void setOffice(int office)
    {
        this.office = office;
    }
    //mutator for salary
    public void setSalary(double salary)
    {
        this.salary = salary;
    }
    //mutator for dateHired
    public void setDateHired()
    {
        //set new date hired using class MyDate
        dateHired = new MyDate();
    }

    //toString method of the class (per assignment instructions)
    public String toString()
    {
        //in-line return; super keyword to access parent class attributes
        return super.toString() + "\nOffice: " + office + "\nSalary: $" + getSalary() + "\nDate hired: " + getDateHired();
    }

}

class Faculty extends Employee
{
    //initial data fields/attributes
    private String officeHours;
    private String rank;

    //construct a Faculty object with proper attributes obtained from its parent and superclasses
    public Faculty(String name, String address, String phone, String email, int office, double salary, String officeHours, String rank)
    {
        super(name, address, phone, email, office, salary);
        this.officeHours = officeHours;
        this.rank = rank;
    }

    //accessor for officeHours
    public String getOfficeHours()
    {
        return officeHours;
    }
    //accessor for rank
    public String getRank()
    {
        return rank;
    }

    //mutator for officeHours
    public void setOfficeHours(String officeHours)
    {
        this.officeHours = officeHours;
    }

    //toString method of the class (per assignment)
    public String toString()
    {
        return super.toString() + "\nOffice hours: " + officeHours + "\nRank: " + rank;
    }

}

class Staff extends Employee
{
    //initial data fields/attributes
    private String title;

    //construct a Staff object with proper attributes obtained from its parent and superclasses
    public Staff(String name, String address, String phone, String email, int office, double salary, String title)
    {
        super(name, address, phone, email, office, salary);
        this.title = title;
    }

    //accessor for title
    public String getTitle()
    {
        return title;
    }

    //mutator for title
    public void setTitle(String title)
    {
        this.title = title;
    }

    //toString method of the class (per assignment)
    public String toString()
    {
        return super.toString() + "\nTitle: " + title;
    }
}
