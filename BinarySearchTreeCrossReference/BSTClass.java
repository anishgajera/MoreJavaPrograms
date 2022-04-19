package com.company;

public class BSTClass
{
    static Node root;// node - root of the BST
    int totalWords = 0, uniqueWords = 0;//counter for the total words and unique words criteria (per the assignment)

    //Node class
    static class Node {
        int repetition;//# of times word is repeated/is found
        String word;
        String inLines;//the word we are focusing on and which lines it's present in
        Node left;//left child node
        Node right;//right child node

        public Node(String word, int count, String inLines) {
            this.word = word;//assigning the word to the word in class Node
            this.repetition = count;//assigning the count to the repetition variable in class Node
            this.inLines = inLines;//assigning the line number values to the inLines variable in class Node
            this.left = this.right = null;//both of the children of the root are null when we begin operation(s)
        }
    }
    //function addToTree() adds the elements into the BST (similar to what I did in Asg 4 with the linked list)
    public BSTClass addToTree (BSTClass BST, String word, String line)
    {
        //replace punctuation
        //if the line has any punctuation consisting of things like: "? , . !;:-." - it can be split where there is this punctuation
        String [] punctuation = {" ",",","(", "\"", "?", ".", "!", ";", ":", "-"};
        for (String p: punctuation)
        {
            word = word.replace(p, "");
        }
        Node wordNode = new Node(word, 1, line);
        //if tree is empty (root == null), insert wordNode
        if (BST.root == null)
        {
            BSTInsert2(wordNode);
            return BST;
        }
        //else if tree is not empty
        Node temp = BSTSearch(word.toLowerCase(), BST.root);
        if (temp == null)
        {
            BSTInsert2(wordNode);
        }
        else
        {
            temp.repetition += 1;
            //wherever there is a comme, split the line (similar to what I did in Asg 4)
            String[] split = temp.inLines.split(", ");
            int k = 0;//a "flag" variable
            //add line number(s) to each line
            for (int i = 0; i < split.length; i++)
            {
                if (split[i].equals(line))
                {
                    k = 1;
                    break;
                }
            }
            /* Note: can replace above for loop with the "enhanced" for loop below
            for (String s : split) {
                if (s.equals(line)) {
                    k = 1;
                    break;
                }
            }
             */
            if (k == 0)
            {
                temp.inLines += ", " + line;
            }
        }
        return BST;
    }
    //this function wordCount() calculates, adds up, and stores the total word count (i.e. how many total words in the file to be added to the tree)
    public void wordCount(Node root) {
        //if the tree is empty (root == null)
        if (root == null) {
            //return
            return;
        }
        //call to itself with the [left node]
        wordCount(root.left);
        //derived from previous program (Asg 4 - this program is pretty much the same as that one but with a BST instead)
        if (root.word.length() > 1) {
            System.out.println(root.word + "\t" + root.repetition + "\t " + root.inLines);
        } else {
            System.out.println(root.word + "\t" + root.repetition + "\t " + root.inLines);
        }
        totalWords += root.repetition;
        uniqueWords++;
        //call to itself with the [right node]
        wordCount(root.right);
    }
    //this function BSTSearch() performs a search on the tree for the words
    private Node BSTSearch(String word, Node node)
    {
        if (node == null || node.word.equalsIgnoreCase(word))
        {
            return node;
        } else if (node.word.compareToIgnoreCase(word) > 0)
        {
            return BSTSearch(word, node.left);
        }
            return BSTSearch(word, node.right);
    }
    Node BSTInsert1(Node root, Node node)
    {
        //if tree is empty (i.e. root == null)
        if (root == null)
        {
            root = node;
            return root;
        }
        else if (node.word.compareToIgnoreCase(root.word) < 0)
        {
            root.left = BSTInsert1(root.left, node);
        }
        else if (node.word.compareToIgnoreCase(root.word) > 0)
        {
            root.right = BSTInsert1(root.right, node);
        }

        return root;
    }
    void BSTInsert2(Node node)
    {
        root = BSTInsert1(root, node);
    }

    //this function prints/displays/outputs the information inside of the BST (i.e. prints what we need it to print)
    public void output(BSTClass BST)
    {
        Node root = BST.root;
        wordCount(root);
        //print out the total number of words
        System.out.println("Total number of words: " + totalWords);
        //print out the total number of unique words
        System.out.println("Total number of unique words: " + uniqueWords);
    }
}

/* Notes (Ignore)
//put this in function output()? No, not the same as the linked list asg, won't work the same way
if (root.word.length() > 1) {
            System.out.println(root.word + "\t" + root.repetition + "\t " + root.inLines);
        } else {
            System.out.println(root.word + "\t" + root.repetition + "\t " + root.inLines);
        }
        totalWords += root.repetition;
        uniqueWords++;
        //call to itself with the [right node]
        wordCount(root.right);
 */
