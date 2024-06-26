/* Brute Force Approach
Algorithm / Intuition
Solution 1: Brute force

Intuition: Simply traverse from 1 to min(a,b) and check for every number.

Approach: 
-Traverse from 1 to min(a,b).
-And check if i is divisible by both a and b.If yes store it in the answer.
-Find the maximum value of i which divides both a and b.
*/
public class Main {
  public static void main(String args[]) {
    int num1 = 3, num2 = 6;
    int ans = 1;
    for (int i = 1; i <= Math.min(num1, num2); i++) {
      if (num1 % i == 0 && num2 % i == 0) {
        ans = i;
      }
    }
    System.out.print("The GCD of the two number is "+ans);
  }
}
/*
Output: The GCD of the two numbers is 4

Time Complexity: O(N).
Space Complexity: O(1).


**Optimal Approach**
Algorithm / Intuition
Solution 2: Using Euclidean’s theorem.

Intuition: Gcd is the greatest number which is divided by both a and b.If a number is divided by both a and b, it is should be divided by (a-b) and b as well.
gcd(a,b)=gcd(b,a%b)

Approach:
-Recursively call gcd(b,a%b) function till the base condition is hit.
-b==0 is the base condition.When base condition is hit return a,as gcd(a,0) is equal to a.
*/

public class Main {
  static int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }
  public static void main(String args[]) {
    int a = 4, b = 8;
    int ans = gcd(a, b);
    System.out.print("The GCD of the two numbers is "+ans);
  }
}
/*
Output: The GCD of the two numbers is 4

Time Complexity: O(logɸmin(a,b)), where ɸ is 1.61.
Space Complexity: O(1).
*/
