/*Problem Statement: You are given 'N’ roses and you are also given an array 'arr'  where 'arr[i]'  denotes that the 'ith' rose will bloom on the 'arr[i]th' day.
You can only pick already bloomed roses that are adjacent to make a bouquet. You are also told that you require exactly 'k' adjacent bloomed roses to make a single bouquet.
Find the minimum number of days required to make at least ‘m' bouquets each containing 'k' roses. Return -1 if it is not possible.

Examples
Example 1:
Input Format:
 N = 8, arr[] = {7, 7, 7, 7, 13, 11, 12, 7}, m = 2, k = 3
Result:
 12
Explanation:
 On the 12th the first 4 flowers and the last 3 flowers would have already bloomed. So, we can easily make 2 bouquets, one with the first 3 and another with the last 3 flowers.

Example 2:
Input Format:
 N = 5, arr[] = {1, 10, 3, 10, 2}, m = 3, k = 2
Result:
 -1
Explanation:
 If we want to make 3 bouquets of 2 flowers each, we need at least 6 flowers. But we are given only 5 flowers, so, we cannot make the bouquets.


Let's grasp the question better with the help of an example. Consider an array: {7, 7, 7, 7, 13, 11, 12, 7}. We aim to create bouquets with k, which is 3 adjacent flowers, and we need to make m, which is 2 such bouquets. Now, if we try to make bouquets on the 11th day, the first 4 flowers and the 6th and the last flowers would have bloomed. So, we will be having 6 flowers in total on the 11th day. However, we require two groups of 3 adjacent flowers each. Although we can form one group with the first 3 adjacent flowers, we cannot create a second group. Therefore, 11 is not the answer in this case.

If we choose the 12th day, we can make 2 such groups, one with the first 3 adjacent flowers and the other with the last 3 adjacent flowers. Hence, we need a minimum of 12 days to make 2 bouquets.

Observation: 

Impossible case: To create m bouquets with k adjacent flowers each, we require a minimum of m*k flowers in total. If the number of flowers in the array, represented by array-size, is less than m*k, it becomes impossible to form m bouquets even after all the flowers have bloomed. In such cases, where array-size < m*k, we should return -1.
 Maximum possible answer: The maximum potential answer corresponds to the time needed for all the flowers to bloom. In other words, it is the highest value within the given array i.e. max(arr[]).
Minimum possible answer: The minimum potential answer corresponds to the time needed for atleast one flower to bloom. In other words, it is the smallest value within the given array i.e. min(arr[]).
Note: From the above observations, we can conclude that our answer lies between the range [min(arr[]), max(arr[])].

How to calculate the number of bouquets we can make on dth day:

We will count the number of adjacent bloomed flowers(say cnt) and whenever we get a flower that is not bloomed, we will add the number of bouquets we can make with ‘cnt’ adjacent flowers i.e. floor(cnt/k) to the answer. We will follow the process throughout the array. 

Now, we will write a function possible(), that will return true if, on a certain day, we can make at least m bouquets otherwise it will return false. The steps will be the following:

possible(arr[], day, m, k) algorithm:

We will declare two variables i.e. ‘cnt’ and ‘noOfB’.
cnt -> the number of adjacent flowers,
noOfB -> the number of bouquets.
We will run a loop to traverse the array.
Inside the loop, we will do the following:
If arr[i] <= day: This means the ith flower has bloomed. So, we will increase the number of adjacent flowers i.e. ‘cnt’ by 1.
Otherwise, the flower has not bloomed. Here, we will calculate the number of bouquets we can make with ‘cnt’ adjacent flowers i.e. floor(cnt/k), and add it to the noOfB. Now, as this ith flower breaks the sequence of the adjacent bloomed flowers, we will set the ‘cnt’ 0.
Lastly, outside the loop, we will calculate the floor(cnt/k) and add it to the noOfB.
If noOfB >= m: This means, we can make at least m bouquets. So, we will return true.
Otherwise, We will return false.
Note: We actually pass a particular day as a parameter to the possible() function. The function returns true if it is possible to make atleast m bouquets on that particular day, otherwise, it returns false.

Brute Force Approach
Algorithm / Intuition
Naive Approach(Brute force): 
The extremely naive approach is to check all possible answers from min(arr[]) to max(arr[]). The minimum number for which possible() returns true, is our answer.

Algorithm:
If m*k > arr.size: This means we have insufficient flowers. So, it is impossible to make m bouquets and we will return -1.
We will run a loop(say i) from min(arr[]) to max(arr[]) to check all possible answers.
Next, we will pass each potential answer, represented by the variable 'i' (which corresponds to a specific day), to the 'possible()' function. If the function returns true, indicating that we can create 'm' bouquets, we will return the value of 'i'.
Finally, if we are outside the loop, we can conclude that is impossible to make m bouquets. So, we will return -1.
*/

import java.util.*;

public class tUf {
    public static boolean possible(int[] arr, int day, int m, int k) {
        int n = arr.length; // Size of the array
        int cnt = 0;
        int noOfB = 0;
        // Count the number of bouquets:
        for (int i = 0; i < n; i++) {
            if (arr[i] <= day) {
                cnt++;
            } else {
                noOfB += (cnt / k);
                cnt = 0;
            }
        }
        noOfB += (cnt / k);
        return noOfB >= m;
    }

    public static int roseGarden(int[] arr, int k, int m) {
        long val = (long) m * k;
        int n = arr.length; // Size of the array
        if (val > n) return -1; // Impossible case.
        // Find maximum and minimum:
        int mini = Integer.MAX_VALUE, maxi = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            mini = Math.min(mini, arr[i]);
            maxi = Math.max(maxi, arr[i]);
        }

        for (int i = mini; i <= maxi; i++) {
            if (possible(arr, i, m, k))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {7, 7, 7, 7, 13, 11, 12, 7};
        int k = 3;
        int m = 2;
        int ans = roseGarden(arr, k, m);
        if (ans == -1)
            System.out.println("We cannot make m bouquets.");
        else
            System.out.println("We can make bouquets on day " + ans);
    }
}


/*
Output: We can make bouquets on day 12.

Complexity Analysis
Time Complexity: O((max(arr[])-min(arr[])+1) * N), where {max(arr[]) -> maximum element of the array, min(arr[]) -> minimum element of the array, N = size of the array}.
Reason: We are running a loop to check our answers that are in the range of [min(arr[]), max(arr[])]. For every possible answer, we will call the possible() function. Inside the possible() function, we are traversing the entire array, which results in O(N).

Space Complexity: O(1) as we are not using any extra space to solve this problem.

Optimal Approach
Algorithm / Intuition
Optimal Approach(Using Binary Search): 
We are going to use the Binary Search algorithm to optimize the approach.

The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. It does this by determining a specific condition that ensures that the target is not present in that half.

Now, we are not given any sorted array on which we can apply binary search. But, if we observe closely, we can notice that our answer space i.e. [mini(arr[]), max(arr[])] is sorted. So, we will apply binary search on the answer space.

Algorithm:
If m*k > arr.size: This means we have insufficient flowers. So, it is impossible to make m bouquets and we will return -1.
Next, we will find the maximum element i.e. max(arr[]), and the minimum element i.e. min(arr[]) in the array.
Place the 2 pointers i.e. low and high: Initially, we will place the pointers. The pointer low will point to min(arr[]) and the high will point to max(arr[]).
Calculate the ‘mid’: Now, inside the loop, we will calculate the value of ‘mid’ using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
Eliminate the halves based on the value returned by possible():
We will pass the potential answer, represented by the variable 'mid' (which corresponds to a specific day), to the 'possible()' function.
If possible() returns true: On satisfying this condition, we can conclude that the number ‘mid’ is one of our possible answers. But we want the minimum number. So, we will eliminate the right half and consider the left half(i.e. high = mid-1).
Otherwise, the value mid is smaller than the number we want. This means the numbers greater than ‘mid’ should be considered and the right half of ‘mid’ consists of such numbers. So, we will eliminate the left half and consider the right half(i.e. low = mid+1).
Finally, outside the loop, we will return the value of low as the pointer will be pointing to the answer.
The steps from 3-5 will be inside a loop and the loop will continue until low crosses high.

Note: Please make sure to refer to the video and try out some test cases of your own to understand, how the pointer ‘low’ will be always pointing to the answer in this case. This is also the reason we have not used any extra variable here to store the answer.
*/

import java.util.*;

public class tUf {
    public static boolean possible(int[] arr, int day, int m, int k) {
        int n = arr.length; // Size of the array
        int cnt = 0;
        int noOfB = 0;
        // Count the number of bouquets:
        for (int i = 0; i < n; i++) {
            if (arr[i] <= day) {
                cnt++;
            } else {
                noOfB += (cnt / k);
                cnt = 0;
            }
        }
        noOfB += (cnt / k);
        return noOfB >= m;
    }

    public static int roseGarden(int[] arr, int k, int m) {
        long val = (long) m * k;
        int n = arr.length; // Size of the array
        if (val > n) return -1; // Impossible case.
        // Find maximum and minimum:
        int mini = Integer.MAX_VALUE, maxi = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            mini = Math.min(mini, arr[i]);
            maxi = Math.max(maxi, arr[i]);
        }

        // Apply binary search:
        int low = mini, high = maxi;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (possible(arr, mid, m, k)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] arr = {7, 7, 7, 7, 13, 11, 12, 7};
        int k = 3;
        int m = 2;
        int ans = roseGarden(arr, k, m);
        if (ans == -1)
            System.out.println("We cannot make m bouquets.");
        else
            System.out.println("We can make bouquets on day " + ans);
    }
}
/*
Output:We can make bouquets on day 12

Complexity Analysis
Time Complexity: O(log(max(arr[])-min(arr[])+1) * N), where {max(arr[]) -> maximum element of the array, min(arr[]) -> minimum element of the array, N = size of the array}.
Reason: We are applying binary search on our answers that are in the range of [min(arr[]), max(arr[])]. For every possible answer ‘mid’, we will call the possible() function. Inside the possible() function, we are traversing the entire array, which results in O(N).

Space Complexity: O(1) as we are not using any extra space to solve this problem.
*/
