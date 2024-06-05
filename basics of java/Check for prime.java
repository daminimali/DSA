/* Example 1:
Input:
 N = 3
Output:
 Prime
Explanation:
 3 is a prime number

Example 2:
Input:
 N = 26
Output:
 Non-Prime
Explanation:
 26 is not prime

**BRUTE FORCE APPROACH**
Solution 1: Using Iterative solution

Approach: 

-A prime number is a natural number that is only divisible by 1 and by itself. Examples 1 2 3 5 7 11 13 17 19 …
-Running a for loop for checking if the number is divisible by a number from 2 to a number less than a given number.
-And then checking if the number is divisible by the numbers from 2 to the number less than a given number
-Then, If the remainder is zero, that means it is divisible and hence not a prime number.
-If the loop runs till square root and none of the numbers divided it completely. So it is the Prime number.
*/

class Solution {

  public static boolean isPrime(int N) {

    for (int i = 2; i < N; i++) {
      if (N % i == 0) {
        return false;
      }
    }
    return true;

  }
  public static void main(String args[]) {
    int n = 20;
    boolean ans = (isPrime(n));
    if (n != 1 && ans == true) {
      System.out.println("Prime Number");
    } else {
      System.out.println("Non-Prime Number");
    }
  }
}
/* Output: Prime Number
Time Complexity: O(n)
Space Complexity: O(1)

**OPTIMAL FORCE APPROACH**
Solution 2: Optimized Approach

Approach: 
-Running the for loop till the square root of the number
-A prime number is a natural number that is only divisible by 1 and by itself. Examples 1 2 3 5 7 11 13 17 19 …
-Using a for loop for checking if the number is divisible by a number from 2 to its square root.
-Running the for loop from 2 to the square root of the number.
-And then checking if the number is divisible by the numbers from 2 to its square root.
-Then, If the remainder is zero, that means it is divisible and hence not a prime number.
-If the loop runs till square root and none of the numbers divided it completely. So it is the Prime number.
*/

class Solution {

  public static boolean isPrime(int N) {

    for (int i = 2; i < Math.sqrt(N); i++) {
      if (N % i == 0) {
        return false;
      }
    }
    return true;

  }
  public static void main(String args[]) {
    int n = 20;
    boolean ans = (isPrime(n));
    if (n != 1 && ans == true) {
      System.out.println("Prime Number");
    } else {
      System.out.println("Non Prime Number");
    }
  }
}
/* Output: Prime Number
Time Complexity: O(√n)
Space Complexity: O(1)
*/
