package com.company;

/*
    Class listClass - This class contains the linked list
    used to perform the list operations. List is used to
    go through the file and nodes in the list point to words
    in the file.
 */
public class listClass
{
    static Node head;//node - head of the list

    static class Node
    {
        int repetition;//# times word is found/repeats
        String word;
        String inLines;//the word we are focusing on and which lines it's present in
        Node next;//node which points to the next node of the list ("pointer")

        public Node(String word, int count, String inLines)
        {
            this.word = word;//assigning the word to the word in class Node
            this.repetition = count;//assigning the count to the repetition variable in class Node
            this.inLines = inLines;//assigning the line number values to the inLines variable in class Node
        }
    }
    //function addToList actually "forms" the list by adding to it
    public void addToList(String word, String line)
    {
        //replace punctuation
        //if the line has any punctuation consisting of things like: "? , . !;:-." - it can be split where there is this punctuation
        String [] punctuation = {" ",",","(", "\"", "?", ".", "!", ";", ":", "-"};
        //"enhanced" for loop
        for (String p: punctuation)
        {
            word = word.replace(p, "");
        }
        Node current = head;
        //check if the word exists in the list already
        //if it does, increment and return early
        while (current != null)
        {
            if (current.word.equalsIgnoreCase(word))
            {
                current.repetition++;
                current.inLines += ", " + line;
                return;
            }
            current = current.next;
        }
        //add to front of the linked list
        Node wordNode = new Node(word, 1, line);
        Node temp;//temporary node (acts as placeholder)
        temp = head;
        wordNode.next = temp;
        head = wordNode;
    }
    //this function takes the list as a parameter and sorts it using nodes/node reassigns, word swaps, etc.
    public void sortList(listClass linkyList)
    {
        Node current = linkyList.head;
        int nodes = 0;//# of nodes in the linked list
        //count # of nodes in the list
        while(current != null)
        {
            nodes++;
            current = current.next;
        }
        //swap data in nodes to sort list ("linkyList")
        for(int i = 0; i < nodes; i++ )
        {
            current = linkyList.head;
            //USE WHILE LOOP(?)
            for (int j = 0; j < nodes; j++)
            {
                Node word1 = current;
                if (word1.next != null)
                {
                    Node word2 = word1.next;
                    if(word1.word.toLowerCase().compareTo(word2.word.toLowerCase()) > 0)//error on this line? (different way of checking?)
                    {
                        String text = word1.word;
                        word1.word = word2.word;
                        word2.word = text;

                        int count = word1.repetition;
                        word1.repetition = word2.repetition;
                        word2.repetition = count;

                        String two = word1.inLines;
                        word1.inLines=word2.inLines;
                        word2.inLines = two;
                    }
                }
                current = current.next;
            }
        }
    }
    //this function prints/displays/outputs the information inside of the linked list (i.e. prints what we need it to print)
    public void output(listClass linkyList)
    {
        Node current = linkyList.head;
        int totalWords = 0;
        int uniqueWords = 0;
        //go through the linked list (stepwise? essentially yes)
        //while the current node is not pointing to a null value/it is not null
        while (current != null)
        {
                if (current.word.length() > 1)
                {
                    System.out.println(current.word + "\t" + current.repetition + "\t " + current.inLines);
                } else
                {
                    System.out.println(current.word + "\t" + current.repetition + "\t " + current.inLines);
                }
                totalWords += current.repetition;
                current = current.next;
                uniqueWords++;
        }
        //print out the total number of words
        System.out.println("Total number of words: " + totalWords);
        //print out the total number of unique words
        System.out.println("Total number of unique words: " + uniqueWords);
    }
}

/* Notes (Ignore)
public listClass addToList(listClass linkyList, String word, String line)
    {
        Node current = head;
        while (current != null)
        {
            if (current.word.equalsIgnoreCase(word))
            {
                current.repetition++;
                current.inLines += ", " + line;
                return;
            }
            current = current.next;
        }
        Node wordNode = new Node(word, 1, line);
        Node temp;//temporary node (acts as placeholder)
        temp = linkyList.head;
        wordNode.next = temp;
        head = wordNode;
        int count = 1;
        while(temp != null)
        {
            //if the word is the same ignoring the cases (2 ways to do this?)
            //2nd way: if(temp.word.toLowerCase.equals(word.toLowerCase())) ?
            if(temp.word.equalsIgnoreCase(word))
            {
                count++;
                break;
            }
            temp = temp.next;//temp is assigned the next value
        }
        if (count == 1)
        {
            Node node = new Node(word, 1, line);//if word not in list, create new node for it
            node.next = null;//node points to null
            if(linkyList.head == null)//if the list is empty, assign the head node of the list to the node variable
            {
                linkyList.head = node;
            }
            else
            {
                Node lastNode = linkyList.head;
                while(lastNode.next != null)
                {
                    lastNode = lastNode.next;
                }
                lastNode = node;
            }
        }
        else//if words are present in the list
        {
            temp.repetition += 1;//count increase
            String[] a = temp.inLines.split(",");
            int var = 0;
            for (int i = 0; i < a.length; i++)//add the line # to "line" (string), making sure duplicate value is not added
            {
                if (a[i].equals(line))
                {
                    var = 1;
                    break;
                }
            }
            //if line # not already present in the list, then add it
            if (var == 0)
            {
                temp.inLines += ", " + line;
            }
        }
        return linkyList;
    }
 */
