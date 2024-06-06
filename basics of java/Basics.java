/* *Count digits in a number**
SOLUTION 1:
Approach: 
 -Store the integer in a variable X and initialize a counter variable to count the number of digits.
 -We know that in programming languages when we divide X by Y it will result in an integer (given both the variables are integers). For example, 133/10 will result in 13 similarly 1/10 will result in 0.
 -Using a for loop and above observation keep on dividing X by 10 and increment the count in every iteration when X equals 0 terminate the loop and the count will have the number of digits in N.
*/
 public class tUf { 
    static int count_digits(int n)
    {
       int x = n; int count =0;
       while( x!=0 ) 
      {
          x = x / 10;
          count++;
      }
       return count;
    } 
    public static void main(String args[]) 
    { 
         int n = 12345;
        System.out.println("Number of digits in "+n+" is "+count_digits(n));
    } 
}
/*
Time Complexity: O (n) where n is the number of digits in the given integer

Space Complexity: O(1)

SOLUTION 2:

-Convert the integer into a string.
-Find the length of the string 
*/

import java.util.*;
public class tUf {
  static int count_digits(int n) {
    String n2 = Integer.toString(n);
    return n2.length();
  }

  public static void main(String args[]) {
    int n = 12345;
    System.out.println("Number of digits in " + n + " is " + count_digits(n));
  }
}

/*
Time Complexity: O (1) 

Space Complexity: O(1)

SOLUTION 3:
Algorithm / Intuition
Use logarithm base 10 to count the number of digits. As

The number of digits in an integer = the upper bound of log10(n).

Example :

n = 12345
log10(12345) = 4.091
Integral part of 4.091 is 4 and 4 + 1 =  5 which is also the number of digits in 12345  
*/

import java.util.*;
public class tUf { 
    static int count_digits(int n)
    {
       int digits = (int) Math.floor(Math.log10(n) + 1);
        return digits;
    } 

    public static void main(String args[]) 
    { 
         int n = 12345;
        System.out.println("Number of digits in "+n+" is "+count_digits(n));
    } 
}
/*
Time Complexity: O (1) 
Space Complexity: O(1)
*/
