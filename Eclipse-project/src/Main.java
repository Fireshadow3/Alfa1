package fiero;

import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws IOException{
    	CleanFile c = new CleanFile();
    	ArrayList<String> strings;
    	
    	String in = "list.txt";   // file input
    	String out = "monco.txt"; // file output
    	
    	String m = "abbozzo";
    	
    	c.cleanAndPrintAfterSlash(in, out);
    	
    	strings = c.anagram(m);
    	System.out.println(strings);
    	System.out.println(c.numbersOfAnagrams(out, m));
    	System.out.println(c.wordsBetweenEightAndEleven(in));
    }

}
