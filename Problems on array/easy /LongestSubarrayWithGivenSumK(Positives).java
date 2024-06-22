/* Problem Statement: Given an array and a sum k, we need to print the length of the longest subarray that sums to k.

Examples
Example 1:
Input Format: N = 3, k = 5, array[] = {2,3,5}
Result: 2
Explanation: The longest subarray with sum 5 is {2, 3}. And its length is 2.

Example 2:
Input Format: N = 5, k = 10, array[] = {2,3,5,1,9}
Result: 3
Explanation: The longest subarray with sum 10 is {2, 3, 5}. And its length is 3.
Disclaimer: Don’t jump directly to the solution, try it out yourself first.

Solution 1
Algorithm / Intuition
Solution 1:
Naive Approach (Brute-force approach): 
Here, in this approach, we are going to generate all possible subarrays to solve this problem.
Approach:
The steps are as follows:

First, we will run a loop(say i) that will select every possible starting index of the subarray. The possible starting indices can vary from index 0 to index n-1(n = size of the array).
Inside the loop, we will run another loop(say j) that will signify the ending index of the subarray. For every subarray starting from the index i, the possible ending index can vary from index i to n-1(n = size of the array).
After that for each subarray starting from index i and ending at index j (i.e. arr[i….j]), we will run another loop to calculate the sum of all the elements(of that particular subarray).
If the sum is equal to k, we will consider its length i.e. (j-i+1). Among all such subarrays, we will consider the maximum length by comparing all the lengths.
Intuition:
We will check the sum of every possible subarray and consider the one with the sum k and the maximum length among them. To get every possible subarray sum, we will be using three nested loops. The first loops(say i and j) will iterate over every possible starting index and ending index of a subarray. Basically, in each iteration, the subarray range will be from index i to index j. Using another loop we will get the sum of the elements of the subarray [i…..j]. Among all the subarrays with sum k, we will consider the one with the maximum length.

Note: We are selecting every possible subarray using two nested loops and for each of them, we add all its elements using another loop.

Note: For a better understanding of intuition, please watch the video at the bottom of the page.
*/

import java.util.*;

public class tUf {
    public static int getLongestSubarray(int []a, long k) {
        int n = a.length; // size of the array.

        int len = 0;
        for (int i = 0; i < n; i++) { // starting index
            for (int j = i; j < n; j++) { // ending index
                // add all the elements of
                // subarray = a[i...j]:
                long s = 0;
                for (int K = i; K <= j; K++) {
                    s += a[K];
                }

                if (s == k)
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 5, 1, 9};
        long k = 10;
        int len = getLongestSubarray(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }
}

/*
Output: The length of the longest subarray is: 3

Complexity Analysis
Time Complexity: O(N3) approx., where N = size of the array.
Reason: We are using three nested loops, each running approximately N times.

Space Complexity: O(1) as we are not using any extra space.

Solution 2:
Algorithm / Intuition
Optimizing the Naive Approach (Using two loops): 
Approach:
The steps are as follows:

First, we will run a loop(say i) that will select every possible starting index of the subarray. The possible starting indices can vary from index 0 to index n-1(n = array size).
Inside the loop, we will run another loop(say j) that will signify the ending index as well as the current element of the subarray. For every subarray starting from the index i, the possible ending index can vary from index i to n-1(n = size of the array).
Inside loop j, we will add the current element to the sum of the previous subarray i.e. sum = sum + arr[j]. 
If the sum is equal to k, we will consider its length i.e. (j-i+1). Among all such subarrays with sum k, we will consider the one with the maximum length by comparing all the lengths.
Intuition: If we carefully observe, we can notice that to get the sum of the current subarray we just need to add the current element(i.e. arr[j]) to the sum of the previous subarray i.e. arr[i….j-1].

Assume previous subarray = arr[i……j-1]
current subarray = arr[i…..j]
Sum of arr[i….j] = (sum of arr[i….j-1]) + arr[j]

This is how we can remove the third loop and while moving the j pointer, we can calculate the sum.

Note: For a better understanding of intuition, please watch the video at the bottom of the page.
*/
import java.util.*;

public class tUf {
    public static int getLongestSubarray(int []a, long k) {
        int n = a.length; // size of the array.

        int len = 0;
        for (int i = 0; i < n; i++) { // starting index
            long s = 0; // Sum variable
            for (int j = i; j < n; j++) { // ending index
                // add the current element to
                // the subarray a[i...j-1]:
                s += a[j];

                if (s == k)
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 5, 1, 9};
        long k = 10;
        int len = getLongestSubarray(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }
}
/*
Output: The length of the longest subarray is: 3

Complexity Analysis
Time Complexity: O(N2) approx., where N = size of the array.
Reason: We are using two nested loops, each running approximately N times.

Space Complexity: O(1) as we are not using any extra space.

Solution 3:
Algorithm / Intuition
Better Approach(Using Hashing): 
Approach:
The steps are as follows:

First, we will declare a map to store the prefix sums and the indices.
Then we will run a loop(say i) from index 0 to n-1(n = size of the array).
For each index i, we will do the following:
We will add the current element i.e. a[i] to the prefix sum.
If the sum is equal to k, we should consider the length of the current subarray i.e. i+1. We will compare this length with the existing length and consider the maximum one.
We will calculate the prefix sum i.e. x-k, of the remaining subarray.
If that sum of the remaining part i.e. x-k exists in the map, we will calculate the length i.e. i-preSumMap[x-k], and consider the maximum one comparing it with the existing length we have achieved until now.
If the sum, we got after step 3.1, does not exist in the map we will add that with the current index into the map. We are checking the map before insertion because we want the index to be as minimum as possible and so we will consider the earliest index where the sum x-k has occurred. [Detailed discussion in the edge case section]
In this approach, we are using the concept of the prefix sum to solve this problem. Here, the prefix sum of a subarray ending at index i, simply means the sum of all the elements of that subarray.
*/
import java.util.*;

public class tUf {
    public static int getLongestSubarray(int []a, long k) {
        int n = a.length; // size of the array.

        Map<Long, Integer> preSumMap = new HashMap<>();
        long sum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            //calculate the prefix sum till index i:
            sum += a[i];

            // if the sum = k, update the maxLen:
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // calculate the sum of remaining part i.e. x-k:
            long rem = sum - k;

            //Calculate the length and update maxLen:
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            //Finally, update the map checking the conditions:
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 5, 1, 9};
        long k = 10;
        int len = getLongestSubarray(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }
}
/*
Output: The length of the longest subarray is: 3

Complexity Analysis
Time Complexity: O(N) or O(N*logN) depending on which map data structure we are using, where N = size of the array.
Reason: For example, if we are using an unordered_map data structure in C++ the time complexity will be O(N)(though in the worst case, unordered_map takes O(N) to find an element and the time complexity becomes O(N2)) but if we are using a map data structure, the time complexity will be O(N*logN). The least complexity will be O(N) as we are using a loop to traverse the array.

Space Complexity: O(N) as we are using a map data structure.

Solution 4
Algorithm / Intuition
Optimal Approach (Using 2 pointers): 
Approach:
The steps are as follows:

First, we will take two pointers: left and right, initially pointing to the index 0.
The sum is set to a[0] i.e. the first element initially.
Now we will run a while loop until the right pointer crosses the last index i.e. n-1.
Inside the loop, we will do the following:
We will use another while loop and it will run until the sum is lesser or equal to k.
Inside this second loop, from the sum, we will subtract the element that is pointed by the left pointer and increase the left pointer by 1.
After this loop gets completed, we will check if the sum is equal to k. If it is, we will compare the length of the current subarray i.e. (right-left+1) with the existing one and consider the maximum one.
Then we will move forward the right pointer by 1. If the right pointer is pointing to a valid index(< n) after the increment, we will add the element i.e. a[right] to the sum.
Finally, we will return the maximum length.
Intuition: We are using two pointers i.e. left and right. The left pointer denotes the starting index of the subarray and the right pointer denotes the ending index. Now as we want the longest subarray, we will move the right pointer in a forward direction every time adding the element i.e. a[right] to the sum. But when the sum of the subarray crosses k, we will move the left pointer in the forward direction as well to shrink the size of the subarray as well as to decrease the sum. Thus, we will consider the length of the subarray whenever the sum becomes equal to k.
The below dry run will clarify the intuition.
*/
import java.util.*;

public class Main {
    public static int getLongestSubarray(int []a, long k) {
        int n = a.length; // size of the array.

        int left = 0, right = 0; // 2 pointers
        long sum = a[0];
        int maxLen = 0;
        while (right < n) {
            // if sum > k, reduce the subarray from left
            // until sum becomes less or equal to k:
            while (left <= right && sum > k) {
                sum -= a[left];
                left++;
            }

            // if sum = k, update the maxLen i.e. answer:
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            // Move forward thw right pointer:
            right++;
            if (right < n) sum += a[right];
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 5, 1, 9};
        long k = 10;
        int len = getLongestSubarray(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }
}
/*
Output: The length of the longest subarray is: 3

Complexity Analysis
Time Complexity: O(2*N), where N = size of the given array.
Reason: The outer while loop i.e. the right pointer can move up to index n-1(the last index). Now, the inner while loop i.e. the left pointer can move up to the right pointer at most. So, every time the inner loop does not run for n times rather it can run for n times in total. So, the time complexity will be O(2*N) instead of O(N2).

Space Complexity: O(1) as we are not using any extra space.

Space Complexity: O(1) as we are not using any extra space.
*/
