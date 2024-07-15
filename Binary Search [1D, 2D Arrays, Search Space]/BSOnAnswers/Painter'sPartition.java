/*Problem Statement: Given an array/list of length ‘N’, where the array/list represents the boards and each element of the given array/list represents the length of each board. Some ‘K’ numbers of painters are available to paint these boards. Consider that each unit of a board takes 1 unit of time to paint. You are supposed to return the area of the minimum time to get this job done of painting all the ‘N’ boards under the constraint that any painter will only paint the continuous sections of boards.

Pre-requisite: BS-18. Allocate Books or Book Allocation | Hard Binary Search

Examples
Example 1:
Input Format:
 N = 4, boards[] = {5, 5, 5, 5}, k = 2
Result:
 10
Explanation:
 We can divide the boards into 2 equal-sized partitions, so each painter gets 10 units of the board and the total time taken is 10.

Example 2:
Input Format:
 N = 4, boards[] = {10, 20, 30, 40}, k = 2
Result:
 60
Explanation:
 We can divide the first 3 boards for one painter and the last board for the second painter.

We can allocate the boards to the painters in several ways but it is clearly said in the question that we have to allocate the boards in such a way that the painters can paint all the boards in the minimum possible time. The painters will work simultaneously.

Note: Upon close observation, we can understand that this problem is similar to the previous problem: BS-18. Allocate Books or Book Allocation | Hard Binary Search. There we had to allocate books to the students and here we need to allocate walls to the painters.

Assume the given array is {10, 20, 30, 40} and number of painters, k = 2. Now, we can allocate these boards in different ways. Some of them are the following:

10 | 20, 30, 40  → Minimum time required to paint all the boards  = 90
10, 20 | 30, 40  → Minimum time required to paint all the boards = 70
10, 20, 30 | 40  → Minimum time required to paint all the boards = 60
From the above allocations, we can clearly observe that in the last case, the first painter will paint the first 3 walls in 60 units of time and the second painter will take 40 units of time. So, the minimum time required to paint all the boards is 60.

Observations:

Minimum possible answer: We will get the minimum answer when we give n boards of the array to n painters(i.e. Each painter will be allocated 1 board). Now, in this case, the minimum time required to paint all the boards will be the maximum element in the array. So, the minimum possible answer is max(arr[]).
Maximum possible answer: We will get the maximum answer when we give all n boards to a single painter. The total time required is the summation of array elements i.e. sum(arr[]). So, the maximum possible answer is sum(arr[]).
From the observations, it is clear that our answer lies in the range [max(arr[]), sum(arr[])].

How to calculate the number of painters we need if we have to paint all the walls within ‘time’ units of time:

In order to calculate the number of painters we will write a function, countPainters(). This function will take the array and ‘time’ as parameters and return the number of painters to whom we can allocate the boards.

countPainters(arr[], time):

We will first declare two variables i.e. ‘painters’(stores the no. of painters), and ‘boardsPainter’(stores the unit of boards, a painter will paint). As we are starting with the first painter, ‘painters’ should be initialized with 1.
We will start traversing the given array.
If boardsPainter + arr[i] <= time: If upon adding the current board with ‘boardsPainter’ does not exceed the time limit, we can allocate this i-th board to the current painter.
Otherwise, we will move to the next painter(i.e. painters += 1 ) and allocate the i-th board.
Finally, we will return the value of ‘painters’.

Brute Force Approach
Algorithm / Intuition
Naive Approach: 
The extremely naive approach is to check all possible answers from max(arr[]) to sum(arr[]). The minimum time for which we can paint all the boards will be our answer.

Algorithm:
First, we will find the maximum element and the summation of the given array.
We will use a loop(say time) to check all possible answers from max(arr[]) to sum(arr[]).
Next, inside the loop, we will send ‘time’, to the function countPainters() function to get the number of painters to whom we can allocate the boards.
The first value of ‘time’, for which the number of painters will be lesser or equal to ‘k’, will be our answer. So, we will return that particular value of ‘time’.
Finally, if we are out of the loop, we will return max(arr[]) as there cannot exist any answer smaller than that.
*/

import java.util.*;

public class tUf {
    public static int countPainters(ArrayList<Integer> boards, int time) {
        int n = boards.size(); // size of array.
        int painters = 1;
        long boardsPainter = 0;
        for (int i = 0; i < n; i++) {
            if (boardsPainter + boards.get(i) <= time) {
                // allocate board to current painter
                boardsPainter += boards.get(i);
            } else {
                // allocate board to next painter
                painters++;
                boardsPainter = boards.get(i);
            }
        }
        return painters;
    }

    public static int findLargestMinDistance(ArrayList<Integer> boards, int k) {
        int low = Collections.max(boards);
        int high = boards.stream().mapToInt(Integer::intValue).sum();

        for (int time = low; time <= high; time++) {
            if (countPainters(boards, time) <= k) {
                return time;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        ArrayList<Integer> boards = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
        int k = 2;
        int ans = findLargestMinDistance(boards, k);
        System.out.println("The answer is: " + ans);
    }
}

/*
Output: The answer is: 60

Complexity Analysis
Time Complexity: O(N * (sum(arr[])-max(arr[])+1)), where N = size of the array, sum(arr[]) = sum of all array elements, max(arr[]) = maximum of all array elements.
Reason: We are using a loop from max(arr[]) to sum(arr[]) to check all possible values of time. Inside the loop, we are calling the countPainters() function for each number. Now, inside the countPainters() function, we are using a loop that runs for N times.

Space Complexity:  O(1) as we are not using any extra space to solve this problem.

Optimal Approach
Algorithm / Intuition
Optimal Approach(Using Binary Search): 

We are going to use the Binary Search algorithm to optimize the approach.

The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. It does this by determining a specific condition that ensures that the target is not present in that half.

Upon closer observation, we can recognize that our answer space, represented as [max(arr[]), sum(arr[])], is actually sorted. Additionally, we can identify a pattern that allows us to divide this space into two halves: one consisting of potential answers and the other of non-viable options. So, we will apply binary search on the answer space.

Algorithm:

Place the 2 pointers i.e. low and high: Initially, we will place the pointers. The pointer low will point to max(arr[]) and the high will point to sum(arr[]).
Calculate the ‘mid’: Now, inside the loop, we will calculate the value of ‘mid’ using the following formula:
mid = (low+high) // 2 ( ‘//’ refers to integer division)
Eliminate the halves based on the number of painters returned by countPainters():
We will pass the potential value of time, represented by the variable 'mid', to the ‘countPainters()' function. This function will return the number of painters we need to paint all the boards.
If painters > k: On satisfying this condition, we can conclude that the number ‘mid’ is smaller than our answer. So, we will eliminate the left half and consider the right half(i.e. low = mid+1).
Otherwise, the value mid is one of the possible answers. But we want the minimum value. So, we will eliminate the right half and consider the left half(i.e. high = mid-1).
Finally, outside the loop, we will return the value of low as the pointer will be pointing to the answer.
The steps from 3-4 will be inside a loop and the loop will continue until low crosses high.

Note: Please make sure to refer to the video and try out some test cases of your own to understand, how the pointer ‘low’ will be always pointing to the answer in this case. This is also the reason we have not used any extra variable here to store the answer.
*/

import java.util.*;

public class tUf {
    public static int countPainters(ArrayList<Integer> boards, int time) {
        int n = boards.size(); // size of array.
        int painters = 1;
        long boardsPainter = 0;
        for (int i = 0; i < n; i++) {
            if (boardsPainter + boards.get(i) <= time) {
                // allocate board to current painter
                boardsPainter += boards.get(i);
            } else {
                // allocate board to next painter
                painters++;
                boardsPainter = boards.get(i);
            }
        }
        return painters;
    }

    public static int findLargestMinDistance(ArrayList<Integer> boards, int k) {
        int low = Collections.max(boards);
        int high = boards.stream().mapToInt(Integer::intValue).sum();

        // Apply binary search:
        while (low <= high) {
            int mid = (low + high) / 2;
            int painters = countPainters(boards, mid);
            if (painters > k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        ArrayList<Integer> boards = new ArrayList<>(Arrays.asList(10, 20, 30, 40));
        int k = 2;
        int ans = findLargestMinDistance(boards, k);
        System.out.println("The answer is: " + ans);
    }
}
/*
Output:The answer is: 60.

Complexity Analysis
Time Complexity: O(N * log(sum(arr[])-max(arr[])+1)), where N = size of the array, sum(arr[]) = sum of all array elements, max(arr[]) = maximum of all array elements.
Reason: We are applying binary search on [max(arr[]), sum(arr[])]. Inside the loop, we are calling the countPainters() function for the value of ‘mid’. Now, inside the countPainters() function, we are using a loop that runs for N times.

Space Complexity:  O(1) as we are not using any extra space to solve this problem.
*/
