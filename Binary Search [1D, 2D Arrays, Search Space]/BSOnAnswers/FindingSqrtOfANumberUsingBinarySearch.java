/*Problem Statement: You are given a positive integer n. Your task is to find and return its square root. If ‘n’ is not a perfect square, then return the floor value of 'sqrt(n)'.

Note: The question explicitly states that if the given number, n, is not a perfect square, our objective is to find the maximum number, x, such that x squared is less than or equal to n (x*x <= n). In other words, we need to determine the floor value of the square root of n.

Examples
Example 1:
Input Format:
 n = 36
Result:
 6
Explanation:
 6 is the square root of 36.

Example 2:
Input Format:
 n = 28
Result:
 5
Explanation:
 Square root of 28 is approximately 5.292. So, the floor value will be 5.

Brute Force Approach
Algorithm / Intuition
Naive Approach(Using linear search): 
We can guarantee that our answer will lie between the range from 1 to n i.e. the given number. So, we will perform a linear search on this range and we will find the maximum number x, such that x*x <= n.

Algorithm:
We will first declare a variable called ‘ans’.
Then, we will first run a loop(say i) from 1 to n.
Until the value i*i <= n, we will update the variable ‘ans’, with i.
Once, the value i*i becomes greater than n, we will break out from the loop as the current number i, or the numbers greater than i, cannot be our answers. 
Finally, our answer should have been stored in ‘ans’.
*/

import java.util.*;

public class tUf {
    public static int floorSqrt(int n) {
        int ans = 0;
        // linear search on the answer space
        for (long i = 1; i <= n; i++) {
            long val = i * i;
            if (val <= (long) n) {
                ans = (int) i;
            } else {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 28;
        int ans = floorSqrt(n);
        System.out.println("The floor of square root of " + n + " is: " + ans);
    }
}
/*
Output: The floor of square root of 28 is: 5

Complexity Analysis
Time Complexity: O(N), N = the given number.
Reason: Since we are using linear search, we traverse the entire answer space.

Space Complexity: O(1) as we are not using any extra space.

Optimal Approach 1:
Algorithm / Intuition
First Approach(Using in-built sqrt() function): 
A straightforward solution to this problem is to utilize the built-in sqrt() function. This approach doesn't require any code implementation but serves as one of the possible solutions.
*/

import java.util.*;
import java.lang.Math;

public class tUf {
    public static int floorSqrt(int n) {
        int ans = (int) Math.sqrt(n);
        return ans;
    }

    public static void main(String[] args) {
        int n = 28;
        int ans = floorSqrt(n);
        System.out.println("The floor of square root of " + n + " is: " + ans);
    }
}
/*
Output: Output: The floor of square root of 28 is: 5

Complexity Analysis
Time Complexity: O(logN), N = size of the given array.
Reason: We are basically using the Binary Search algorithm.

Space Complexity: O(1) as we are not using any extra space.

Optimal Approach 2:
Algorithm / Intuition
Optimal Approach(Using binary search): 
We are going to use the Binary Search algorithm to optimize the approach.

The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. It does this by determining a specific condition that ensures that the target is not present in that half.

Now, we are not given any sorted array on which we can apply binary search. But, if we observe closely, we can notice that our answer space i.e. [1, n] is sorted. So, we will apply binary search on the answer space.

Algorithm:
The steps are as follows:

We will declare a variable called ‘ans’.

Place the 2 pointers i.e. low and high: Initially, we will place the pointers. The pointer low will point to 1 and the high will point to n.
Calculate the ‘mid’: Now, inside a loop, we will calculate the value of ‘mid’ using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
Eliminate the halves accordingly: 
If mid*mid <= n: On satisfying this condition, we can conclude that the number ‘mid’ is one of the possible answers. So, we will store ‘mid’ in the variable ‘ans’. But we want the maximum number that holds this condition. So, we will eliminate the left half and consider the right half(i.e. low = mid+1).
Otherwise, the value mid is larger than the number we want. This means the numbers greater than ‘mid’ will not be our answers and the right half of ‘mid’ consists of such numbers. So, we will eliminate the right half and consider the left half(i.e. high = mid-1).
Finally, the ‘ans’ variable will be storing our answer. In addition to that, the high pointer will also point to the same number i.e. our answer. So, we can return either of the ‘ans’ or ‘high’.
The steps from 2-3 will be inside a loop and the loop will continue until low crosses high.

Dry-run: Please refer to the video for the dry-run.

Note: In this case, the 'high' pointer serves as our answer, eliminating the need for an additional 'ans' variable. Therefore, it is perfectly acceptable to omit the use of an extra variable to store the answer. Consequently, in the following code, no additional variable is utilized for storing the answer.
*/

import java.util.*;

public class tUf {
    public static int floorSqrt(int n) {
        int low = 1, high = n;
        //Binary search on the answers:
        while (low <= high) {
            long mid = (low + high) / 2;
            long val = mid * mid;
            if (val <= (long)(n)) {
                //eliminate the left half:
                low = (int)(mid + 1);
            } else {
                //eliminate the right half:
                high = (int)(mid - 1);
            }
        }
        return high;
    }

    public static void main(String[] args) {
        int n = 28;
        int ans = floorSqrt(n);
        System.out.println("The floor of square root of " + n + " is: " + ans);
    }
}

/*
Output: The floor of square root of 28 is: 5

Complexity Analysis
Time Complexity: O(logN), N = size of the given array.
Reason: We are basically using the Binary Search algorithm.

Space Complexity: O(1) as we are not using any extra space.
*/
