/*
Problem Statement: Given an array of size N. Find the highest and lowest frequency element.

Examples:

Example 1:
Input: array[] = {10,5,10,15,10,5};
Output: 10 15
Explanation: The frequency of 10 is 3, i.e. the highest and the frequency of 15 is 1 i.e. the lowest.

Example 2:

Input: array[] = {2,2,3,4,4,2};
Output: 2 3
Explanation: The frequency of 2 is 3, i.e. the highest and the frequency of 3 is 1 i.e. the lowest.
Solution
Disclaimer: Don't jump directly to the solution, try it out yourself first.

Solution:

In order to find the highest and lowest frequency element, first we need to find the frequencies of all the elements of the given array. In the previous article, we learned how to count the frequency of each element in the array. 

Approach: 
Brute-Force approach(Using two loops): 
We will be using the same countFreq() function used in the previous article with slight modifications. We will use two loops, in which, the first loop will be used to iterate the given array. Selecting an element, we will use the second loop to traverse the remaining array to find the frequency(i.e. The number of times the element occurs in the array) of the selected element i.e. arr[i]. Now after the second loop completes traversing we will keep a track of the element with the highest and lowest frequency. We also have to maintain a visited array so that it will keep track of the duplicate elements that we already count.

The steps are as follows:

Make a visited array of type boolean.
Use the first loop to point to an element of the array.
Initialize the variable count to 1.
Make that index true in the visited array.
Run the second loop, if we find the element then mark the visited index true and increase the count.
If the visited index is already true then skip the other steps.
After step 5, we will keep a track of the element with the highest frequency and the lowest frequency.
*/

import java.util.*;

public class tUf {

    public static void main(String args[]) {

        int arr[] = {10, 5, 10, 15, 10, 5};
        int n = arr.length;
        countFreq(arr, n);
    }
    public static void countFreq(int arr[], int n) {
        boolean visited[] = new boolean[n];
        int maxFreq = 0, minFreq = n;
        int maxEle = 0, minEle = 0;
        for (int i = 0; i < n; i++) {

            // Skip this element if already processed
            if (visited[i] == true)
                continue;

            // Count frequency
            int count = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    visited[j] = true;
                    count++;
                }
            }
            if (count > maxFreq) {
                maxEle = arr[i];
                maxFreq = count;
            }
            if (count < minFreq) {
                minEle = arr[i];
                minFreq = count;
            }
        }
        System.out.println("The highest frequency element is: " + maxEle);
        System.out.println("The lowest frequency element is: " + minEle);

    }
}  
/*
Output:
The highest frequency element is: 10
The lowest frequency element is: 15
Time Complexity: O(N*N), where N = size of the array. We are using the nested loop to find the frequency.
Space Complexity:  O(N), where N = size of the array. It is for the visited array we are using.

Optimized approach(Using map):

We can use a map of value and frequency pair, in which we can easily update the frequency of an element if it is already present in the map, if it is not present in the map then insert it in the map with frequency as 1. After completing all the iterations, we will find the element with the highest frequency and the element with the lowest frequency.

The steps are as follows:

Take a unordered_map<int, int> / HashMap of <Integer, Integer> pair.
Use a for loop to iterate the array.
If the element is not present in the map then insert it with frequency 1, otherwise increase the existing frequency by 1.
After visiting the whole array, we will find the element with the highest frequency and the element with the lowest frequency by iterating the map.
*/

import java.util.*;

public class Main {

    public static void main(String args[]) {

        int arr[] = {10, 5, 10, 15, 10, 5};
        int n = arr.length;
        Frequency(arr, n);
    }
    static void Frequency(int arr[], int n) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }

        int maxFreq = 0, minFreq = n;
        int maxEle = 0, minEle = 0;
        // Traverse through map and find the elements
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            int element = entry.getKey();

            if (count > maxFreq) {
                maxEle = element;
                maxFreq = count;
            }
            if (count < minFreq) {
                minEle = element;
                minFreq = count;
            }
        }

        System.out.println("The highest frequency element is: " + maxEle);
        System.out.println("The lowest frequency element is: " + minEle);
    }
}
/*
Output:
The highest frequency element is: 10
The lowest frequency element is: 15
Time Complexity: O(N), where N = size of the array. The insertion and retrieval operation in the map takes O(1) time.
Space Complexity:  O(N), where N = size of the array. It is for the map we are using.
*/
