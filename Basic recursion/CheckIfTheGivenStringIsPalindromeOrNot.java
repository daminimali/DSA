/*
  Problem Statement: "Given a string, check if the string is palindrome or not."  A string is said to be palindrome if the reverse of the string is the same as the string.

Examples:

Example 1:
Input: Str =  “ABCDCBA”
Output: Palindrome
Explanation: String when reversed is the same as string.

Example 2:
Input: Str = “TAKE U FORWARD”
Output: Not Palindrome
Explanation: String when reversed is not the same as string.
Solution
Disclaimer: Don't jump directly to the solution, try it out yourself first.

**Approach:

Run a for loop till half the length of the string in order to check the first and last character of the string.

And check if the first and last elements of the string are equal. And then move both pointers first pointer forward and last pointer backward.

Here we are achieving this with the help of ‘i’ which moves with them for a loop. First element can be get by str[i] and last element by str[str.length() - i - 1]). If this condition gets executed then it is not palindrome and returns false.

If the loop ends after traversing elements till half of the length then, it is Palindrome and returns false.
*/
import java.io.*;
import java.util.Arrays;
class Test {
  static private boolean isPalindrome(String s) {
    int left = 0, right = s.length()-1;
        while(left<right)
        {
            char l = s.charAt(left), r = s.charAt(right);
            if(!Character.isLetterOrDigit(l)) 
                left++;
            else if(!Character.isLetterOrDigit(r)) 
                right--;
            else if(Character.toLowerCase(l)!=Character.toLowerCase(r)) 
                return false;
            else {
                left++; 
                right--;
            }
        }
        return true;
  }
  public static void main(String[] args) {
    String str = "ABCDCBA";
    boolean ans = isPalindrome(str);

    if (ans == true) {
      System.out.println("Palindrome");
    } else {
      System.out.println("Not Palindrome");
    }
  }
}
/*
Output: Palindrome

Time Complexity:  O(N)

Space Complexity: O(1)

**Recursive Approach:

In this approach, we check the string using functional recursion where firstly, the letters on the two ends of the string (start, end) are compared to see if they’re the same or not.
If they’re the same then we simply call recursion for the next elements (start+1, end-1) and so on until the start becomes greater than or equal to the end. 
If at any point the start and the end differ, we return false stating that the string is not a palindrome.
Otherwise, if the base condition is reached, then the string is obviously a palindrome and we return true.
Let us understand it through a recursive code:
*/

Java
class Recursion {
    
    static boolean palindrome(int i, String s){
        
            // Base Condition
            // If i exceeds half of the string, means all the elements 
            // are compared, we return true.
            if(i>=s.length()/2) return true;
            
            // If start is not equal to end, not palindrome.
            if(s.charAt(i)!=s.charAt(s.length()-i-1)) return false;
            
            // If both characters are same, increment i and check start+1 and end-1.
            return palindrome(i+1,s);
            

    }
    public static void main(String[] args) {

       // Example string.
       String s = "madam";
       System.out.println(palindrome(0,s));
    }
}
/*
Output:

True

Time Complexity: O(N) { Precisely, O(N/2) as we compare the elements N/2 times and swap them}.

Space Complexity: O(1) { The elements of the given array are swapped in place so no extra space is required}.
*/
