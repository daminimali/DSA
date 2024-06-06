/* Example 1:
Input:
 n = 36
Output:
 1 2 3 4 6 9 12 18 36
Explanation:
 All the divisors of 36 are printed.

Example 2:
Input:
 n = 97
Output:
 1 97
Explanation:
 Since 97 is a prime number, only 1 and 97 are printed.

**BRUTE FORCE**
Solution 1:
Intuition:
As we know the divisors of a number will definitely be lesser or equal to the number, all the numbers between 1 and the number, are the possible candidates for the divisors.
Approach:
-This is the basic approach. As we know the possible candidates, we iterate upon all the candidates and check whether they divide the actual number.
-If it divides, then it is one of the divisors. Therfore, we print it.
-If it does not divide, then it is not a divisor. We do this for all the candidates.
*/

import java.util.*;

public class Solution{
		
	public static void main(String[] args){
		
		printDivisorsBruteForce(36);
		
	}

	static void printDivisorsBruteForce(int n){
		System.out.println("The Divisors of "+n+" are:");
		for(int i = 1; i <= n; i++)
			if(n % i == 0)
				System.out.print(i + " ");
			
		System.out.println();
	}
	
}

/*
Output:
The Divisors of 36 are:
1 2 3 4 6 9 12 18 36
Time Complexity: O(n), because the loop has to run from 1 to n always.
Space Complexity: O(1), we are not using any extra space.

**OPTIMAL APPROACH**
Solution 2:
Intuition:
-The above method takes O(n) time complexity. We can also think of another approach. If we take a closer look, we can notice that the quotient of a division by one of the divisors is actually another divisor. Like, 4 divides 36. The quotient is 9, and 9 also divides 36.
-Another intuition is that the root of a number actually acts as a splitting part of all the divisors of a number.
-Also, the quotient of a division by any divisor gives an equivalent divisor on the other side. Like, 4 gives 9 while dividing 36. See the image below.

Approach:
-From the intuition, we can come to the conclusion that we don't need to traverse all the candidates and if we found a single divisor, we can also find another divisor(Here, we need to be careful, if the given number is a perfect square, like 36, 6 also give 6 as quotient. This is a corner case.)
-By keeping these in mind, it is enough if we traverse up to the root of a number. Whenever we find a divisor, we print it and also print the quotient. If the quotient is the same, we ignore it. Because the root of a perfect square will give the same quotient as itself.
-The quotients are the numbers that are on the other side of the root. So, it's okay if we stop traversing at the root.
-This approach is more time efficient than the previous one. But the output sequences are not the same. In the previous approach, we get an ordered output unlike here.
*/

import java.util.*;

public class Solution{
		
	public static void main(String[] args){
		
		printDivisorsOptimal(36);
		
	}

	public static void printDivisorsOptimal(int n){
		
		System.out.println("The divisors of "+n+" are:");
		for(int i = 1; i <= (int)Math.sqrt(n); i++)
			if(n % i == 0){
				System.out.print(i + " ");
				if(i != n/i) System.out.print(n/i + " ");
			}
			
		System.out.println();
	}
	
}
/*
Output:
The Divisors of 36 are:
1 36 2 18 3 12 4 9 6

Time Complexity: O(sqrt(n)), because every time the loop runs only sqrt(n) times.
Space Complexity: O(1), we are not using any extra space.
*/
