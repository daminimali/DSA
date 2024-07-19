/* Problem Statement: Given a row-wise sorted matrix of size MXN, where M is no. of rows and N is no. of columns, find the median in the given matrix.

Note: MXN is odd.

Examples
Example 1:
Input Format:M = 3, N = 3, matrix[][] =

                    1 4 9 
                    2 5 6
                    3 8 7
                    
Result: 5
Explanation:  If we find the linear sorted array, the array becomes 1 2 3 4 5 6 7 8 9. So, median = 5
Example 2:
Input Format:M = 3, N = 3, matrix[][] =

                    1 3 8 
                    2 3 4
                    1 2 5
                    
Result: 3
Explanation:  If we find the linear sorted array, the array becomes 1 1 2 2 3 3 4 5 7 8. So, median = 3
Disclaimer: Don’t jump directly to the solution, try it out yourself first. Problem Link

Brute-force Approach
Algorithm / Intuition
The extremely naive approach is to use a linear array/list to store the elements of the given matrix. Now, we will sort the list and return the middle element.

Algorithm:

We will use a loop(say i) to select a particular row at a time.
Next, we will use another loop(say j) for every row to traverse each column.
Inside the loops, we will store each element, matrix[i][j] to a linear array/list.
Finally, we will return the middle element of that linear array.
*/

import java.util.*;

public class tUf {
    public static int median(int matrix[][], int m, int n) {
        List<Integer> lst = new ArrayList<>();

        // Traverse the matrix and
        // copy the elements to the list:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                lst.add(matrix[i][j]);
            }
        }

        // Sort the list:
        Collections.sort(lst);
        return lst.get((m * n) / 2);
    }

    public static void main(String[] args) {
        int matrix[][] = {
            {1, 2, 3, 4, 5},
            {8, 9, 11, 12, 13},
            {21, 23, 25, 27, 29}
        };
        int m = matrix.length, n = matrix[0].length;
        int ans = median(matrix, m, n);
        System.out.println("The median element is: " + ans);
    }
}
    
                                    
/*                             
Output: The median element is: 11

Complexity Analysis
Time Complexity: O(MXN) + O(MXN(log(MXN))), where M = number of row in the given matrix, N = number of columns in the given matrix

Reason: At first, we are traversing the matrix to copy the elements. This takes O(MXN) time complexity. Then we are sorting the linear array of size (MXN), that takes O(MXN(log(MXN))) time complexity

Space Complexity: O(MXN) as we are using a temporary list to store the elements of the matrix.

Optimal Approach
Algorithm / Intuition
Now, if we wish to further optimize the previous approach we cannot afford to check every element. So, we have to eliminate some parts to reduce the time complexity under O(MXN). This is where the binary search algorithm comes in

The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. It does this by determining a specific condition that ensures that the target is not present in that half.

Observations:

What is the search space where we will apply binary search?

If we carefully observe, our answer lies between the smallest and the largest number in the given matrix. So, the search space will be [min(matrix), max(matrix)].
While applying binary search how to check if an element ‘x’ is a possible median?

If ‘x’ is the median, the number of elements smaller or equal to ‘x’ will be surely greater than (MXN) // 2 (integer division).
How to check how many numbers are smaller or equal to an element ‘mid’?

One of the ways is to traverse the whole matrix and count the numbers. But in that case, the time complexity will be high. So, we have to find other ways. It is given that the matrix is row-wise sorted. So, we can apply the concept of upper bound
For every particular row, we will find the upper bound of the current element ‘mid’. The index returned will be the number of smaller or equal elements in that row.
We will do it for each row and add them to get the total number for the whole matrix.
Mathematically, smaller_equal_in_row = upperBound(matrix[row], mid)
We will just convert the above observation into code.

Algorithm:

Calculate min(matrix) and max(matrix): As the given matrix is row-wise sorted the minimum element will be the minimum element in the first column and the maximum will be the maximum in the last column.
Place the 2 pointers low and high: Initially, we will place the pointers. The pointer low will point to min(matrix) and the high will point to max(matrix).
Calculate the ‘mid’: Now, inside a loop, we will calculate the value of ‘mid’ using the following formula: mid = (low+high) // 2 ( ‘//’ refers to integer division)
Use the calculateSmallEqual() function to get the number of elements <= mid: Inside the function, we will use the above-mentioned upper bound formula for each row and calculate the total number of elements <= mid. Then we will return the total number from the function calculateSmallEqual().
If smallEqual <= (M*N) // 2: We can conclude that our median must be a bigger number. So, we will eliminate the left i.e. the smaller half (low = mid+1).
If smallEqual > (M*N) // 2: We can conclude that the element mid might be the median. But we have to reach the smallest number to find the actual median. So, in this case, we will remove the right half( i.e. high = mid-1).
The steps from 3-6 will be inside a loop and the loop will continue until low crosses high

Note: Please make sure to refer to the video and try out some test cases of your own to understand, how the pointer ‘low’ will be always pointing to the answer in this case. This is also the reason we have not used any extra variable here to store the answer.
*/
                                    
import java.util.*;

class tUf {
    static int upperBound(int[] arr, int x, int n) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] > x) {
                ans = mid;
                // look for a smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }

    static int countSmallEqual(int[][] matrix, int m, int n, int x) {
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            cnt += upperBound(matrix[i], x, n);
        }
        return cnt;
    }

    static int median(int[][] matrix, int m, int n) {
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;

        // point low and high to right elements
        for (int i = 0; i < m; i++) {
            low = Math.min(low, matrix[i][0]);
            high = Math.max(high, matrix[i][n - 1]);
        }

        int req = (n * m) / 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            int smallEqual = countSmallEqual(matrix, m, n, mid);
            if (smallEqual <= req) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4, 5},
            {8, 9, 11, 12, 13},
            {21, 23, 25, 27, 29}
        };
        int m = matrix.length;
        int n = matrix[0].length;
        int ans = median(matrix, m, n);
        System.out.println("The median element is: " + ans);
    }
}
                                    
/*                              
Output: The median element is: 11

Complexity Analysis
Time Complexity: O(log(109)) X O(M(logN)), where M = number of rows in the given matrix, N = number of columns in the given matrix

Reason: Our search space lies between [0, 109] as the min(matrix) can be 0 and the max(matrix) can be 109. We are applying binary search in this search space and it takes O(log(109)) time complexity. Then we call countSmallEqual() function for every ‘mid’ and this function takes O(M(logN)) time complexity.

Space Complexity : O(1) as we are not using any extra space
*/
