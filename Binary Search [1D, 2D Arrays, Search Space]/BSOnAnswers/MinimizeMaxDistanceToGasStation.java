/* Problem Statement: You are given a sorted array ‘arr’ of length ‘n’, which contains positive integer positions of ‘n’ gas stations on the X-axis. You are also given an integer ‘k’. You have to place 'k' new gas stations on the X-axis. You can place them anywhere on the non-negative side of the X-axis, even on non-integer positions. Let 'dist' be the maximum value of the distance between adjacent gas stations after adding k new gas stations.
Find the minimum value of ‘dist’.

Note: Answers within 10^-6 of the actual answer will be accepted. For example, if the actual answer is 0.65421678124, it is okay to return 0.654216. Our answer will be accepted if that is the same as the actual answer up to the 6th decimal place.

Examples
Example 1:
Input Format:
 N = 5, arr[] = {1,2,3,4,5}, k = 4
Result:
 0.5
Explanation:
 One of the possible ways to place 4 gas stations is {1,1.5,2,2.5,3,3.5,4,4.5,5}. Thus the maximum difference between adjacent gas stations is 0.5. Hence, the value of ‘dist’ is 0.5. It can be shown that there is no possible way to add 4 gas stations in such a way that the value of ‘dist’ is lower than this. 
Example 2:
Input Format:
 N = 10, arr[] = {1,2,3,4,5,6,7,8,9,10}, k = 1
Result:
 1
Explanation:
 One of the possible ways to place 1 gas station is {1,1.5,2,3,4,5,6,7,8,9,10}. Thus the maximum difference between adjacent gas stations is still 1. Hence, the value of ‘dist’ is 1. It can be shown that there is no possible way to add 1 gas station in such a way that the value of ‘dist’ is lower than this. 

Let’s understand how to place the new gas stations so that the maximum distance between two consecutive gas stations is reduced. 

Let’s consider a small example like this: given gas stations = {1, 7} and k = 2. 

Observation: A possible arrangement for placing 2 gas stations is as follows: {1, 7, 8, 9}. In this arrangement, the new gas stations are positioned after the last existing one. Prior to adding the new stations, the maximum distance between stations was 6 (i.e. the distance between 1 and 7). Even after placing the 2 new stations, the maximum distance remains unchanged at 6.

Conclusions:

From the above observation, we can conclude that placing new gas stations before the first existing station or after the last existing station will make no difference to the maximum distance between two consecutive stations.
So, in order to minimize the maximum distance we have to place the new gas stations in between the existing stations.
How to place the gas stations in between so that the maximum distance is minimized:

Until now we have figured out that we have to place the gas stations in between the existing ones. But we have to place them in such a way that the maximum distance between two consecutive stations is the minimum possible. 
Let’s understand this considering the previous example. Given gas stations = {1, 7} and k = 2.
If we place the gas stations as follows: {1, 2, 6, 7}, the maximum distance will be 4(i.e. 6-2 = 4). But if we place them like this: {1, 3, 5, 7}, the maximum distance boils down to 2. It can be proved that we cannot make the maximum distance lesser than 2.
To minimize the maximum distance between gas stations, we need to insert new stations with equal spacing. If we have to add 'k' gas stations within a section of length 'section_length', each station should be placed at a distance of
(section_length / (k + 1)) from one another.
This way, we maintain a uniform spacing between consecutive gas stations.

For example, the gas stations are = {1, 7} and k = 2. Here, the ‘dist’ is = (7-1) = 6. So, the space between two gas stations will be dis / (k+1) = 6 / (2+1) = 2. The placements will be as follows: {1, 3, 5, 7}.

Brute Force Approach
Algorithm / Intuition
Naive Approach: 
We are given n gas stations. Between them, there are n-1 sections where we may insert the new stations to reduce the distance. So, we will create an array of size n-1 and each of its indexes will represent the respective sections between the given gas stations. 

In each iteration, we will identify the index 'i' where the distance (arr[i+1] - arr[i]) is the maximum. Then, we will insert new stations into that section to reduce that maximum distance. The number of stations inserted in each section will be tracked using the previously declared array of size n-1.

Finally, after placing all the stations we will find the maximum distance between two consecutive stations. To calculate the distance using the previously discussed formula, we will just do as follows for each section:
distance = section_length / (number_of_stations_ inserted+1)

Among all the values of ‘distance’, the maximum one will be our answer.

Algorithm:
First, we will declare an array ‘howMany[]’ of size n-1, to keep track of the number of placed gas stations.
Next, using a loop we will pick k gas stations one at a time.
Then, using another loop, we will find the index 'i' where the distance (arr[i+1] - arr[i]) is the maximum and insert the current gas station between arr[i] and arr[i+1] (i.e. howMany[i]++).
Finally, after placing all the new stations, we will find the distance between two consecutive gas stations. For a particular section,
distance = section_length / (number_of_stations_ inserted+1)
    = (arr[i+1]-arr[i]) / (howMany[i]+1)
Among all the distances, the maximum one will be the answer.
*/

import java.util.*;

public class tUf {
    public static double minimiseMaxDistance(int[] arr, int k) {
        int n = arr.length; //size of array.
        int[] howMany = new int[n - 1];

        //Pick and place k gas stations:
        for (int gasStations = 1; gasStations <= k; gasStations++) {
            //Find the maximum section
            //and insert the gas station:
            double maxSection = -1;
            int maxInd = -1;
            for (int i = 0; i < n - 1; i++) {
                double diff = arr[i + 1] - arr[i];
                double sectionLength =
                    diff / (double)(howMany[i] + 1);
                if (sectionLength > maxSection) {
                    maxSection = sectionLength;
                    maxInd = i;
                }
            }
            //insert the current gas station:
            howMany[maxInd]++;
        }

        //Find the maximum distance i.e. the answer:
        double maxAns = -1;
        for (int i = 0; i < n - 1; i++) {
            double diff = arr[i + 1] - arr[i];
            double sectionLength =
                diff / (double)(howMany[i] + 1);
            maxAns = Math.max(maxAns, sectionLength);
        }
        return maxAns;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;
        double ans = minimiseMaxDistance(arr, k);
        System.out.println("The answer is: " + ans);
    }
}
/*
Output: The answer is: 0.5

Complexity Analysis
Time Complexity: O(k*n) + O(n), n = size of the given array, k = no. of gas stations to be placed.
Reason: O(k*n) to insert k gas stations between the existing stations with maximum distance. Another O(n) for finding the answer i.e. the maximum distance.

Space Complexity: O(n-1) as we are using an array to keep track of placed gas stations.

Better Approach
Algorithm / Intuition
Better Approach(Using Heap): 
In the previous approach, for every gas station, we were finding the index i for which the distance between arr[i+1] and arr[i] is maximum. After that, our job was to place the gas station. Instead of using a loop to find the maximum distance, we can simply use the heap data structure i.e. the priority queue.

Priority Queue: Priority queue internally uses the heap data structure. In the max heap implementation, the first element is always the greatest of the elements it contains and the rest elements are in decreasing order.

Note: Please refer to the article: priority_queue in C++ STL to know more about the data structure.

Thus using a priority queue, we can optimize the search for the maximum distance. We will use the max heap implementation and the elements will be in the form of pairs i.e. <distance, index> as we want the indices sorted based on the distance. As we are using max heap the maximum distance will always be the first element.

Algorithm:
First, we will declare an array ‘howMany[]’ of size n-1, to keep track of the number of placed gas stations and a priority queue that uses max heap.
We will insert the first n-1 indices with the respective distance value, arrr[i+1]-arr[i] for every index.
Next, using a loop we will pick k gas stations one at a time.
Then we will pick the first element of the priority queue as this is the element with the maximum distance. Let’s call the index ‘secInd’.
Now we will place the current gas station at ‘secInd’(howMany[secInd]++) and calculate the new section length,
new_section_length = initial_section_length / (number_of_stations_ inserted+1)
            = (arr[secInd+1] - arr[secInd]) / (howMany[i] + 1)
After that, we will again insert the pair <new_section_length, secInd> into the priority queue for further consideration.
After performing all the steps for k gas stations, the distance at the top of the priority queue will be the answer as we want the maximum distance.
*/

import java.util.*;

public class tUf {
    public static class Pair {
        double first;
        int second;

        Pair(double first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static double minimiseMaxDistance(int[] arr, int k) {
        int n = arr.length; // size of array.
        int[] howMany = new int[n - 1];
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.first, a.first));

        // insert the first n-1 elements into pq
        // with respective distance values:
        for (int i = 0; i < n - 1; i++) {
            pq.add(new Pair(arr[i + 1] - arr[i], i));
        }

        // Pick and place k gas stations:
        for (int gasStations = 1; gasStations <= k; gasStations++) {
            // Find the maximum section
            // and insert the gas station:
            Pair tp = pq.poll();
            int secInd = tp.second;

            // insert the current gas station:
            howMany[secInd]++;

            double inidiff = arr[secInd + 1] - arr[secInd];
            double newSecLen = inidiff / (double) (howMany[secInd] + 1);
            pq.add(new Pair(newSecLen, secInd));
        }

        return pq.peek().first;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;
        double ans = minimiseMaxDistance(arr, k);
        System.out.println("The answer is: " + ans);
    }
}
/*
Output: The answer is: 0.5

Complexity Analysis
Time Complexity: O(nlogn + klogn),  n = size of the given array, k = no. of gas stations to be placed.
Reason: Insert operation of priority queue takes logn time complexity. O(nlogn) for inserting all the indices with distance values and O(klogn) for placing the gas stations.

Space Complexity: O(n-1)+O(n-1)
Reason: The first O(n-1) is for the array to keep track of placed gas stations and the second one is for the priority queue.

Optimal Approach
Algorithm / Intuition
Optimal Approach(Using Binary Search): 
We are going to use the Binary Search algorithm to optimize the approach.

The primary objective of the Binary Search algorithm is to efficiently determine the appropriate half to eliminate, thereby reducing the search space by half. It does this by determining a specific condition that ensures that the target is not present in that half.

Observations:

Minimum possible answer: We will get the minimum answer when we place all the gas stations in a single location. Now, in this case, the maximum distance will be 0.
Maximum possible answer: We will not place stations before the first or after the last station rather we will place stations in between the existing stations. So, the maximum possible answer is the maximum distance between two consecutive existing stations.
From the observations, it is clear that our answer lies in the range [0, max(dist)].

Upon closer observation, we can recognize that our answer space is actually sorted. Additionally, we can identify a pattern that allows us to divide this space into two halves: one consisting of potential answers and the other of non-viable options. So, we will apply binary search on the answer space.

Changes in the binary search algorithm to apply it to the decimal answer space:

The traditional binary search algorithm used for integer answer space won't be effective in this case. As our answer space consists of decimal numbers, we need to adjust some conditions to tailor the algorithm to this specific context. The changes are the following:

while(low <= high): The condition 'while(low <= high)' inside the 'while' loop won't work for decimal answers, and using it might lead to a TLE error. To avoid this, we can modify the condition to 'while(high - low > 10^(-6))'. This means we will only check numbers up to the 6th decimal place. Any differences beyond this decimal precision won't be considered, as the question explicitly accepts answers within 10^-6 of the actual answer.
low = mid+1: We have used this operation to eliminate the left half. But if we apply the same here, we might ignore several decimal numbers and possibly our actual answer. So, we will use this: low = mid.
high = mid-1: Similarly, We have used this operation to eliminate the right half. But if we apply the same here, we might ignore several decimal numbers and possibly the actual answer. So, we will use this: high = mid.
We are applying binary search on the answer i.e. the possible values of distances. So, we have to figure out a way to check the number of gas stations we can place for a particular value of distance.

How to check the number of gas stations we can place with a particular distance ‘dist’: 

In order to find out the number of gas stations we will use the following function:

numberOfGasStationsRequired(dist, arr[]): 

We will use a loop(say i) that will run from 1 to n.
For each section between i and i-1, we will do the following:
No. of stations = (arr[i]-arr[i-1]) / dist
Let's keep in mind a crucial edge case: if the section_length (arr[i] - arr[i-1]) is completely divisible by 'dist', the actual number of stations required will be one less than what we calculate.
if (arr[i]-arr[i-1] == (No. of stations*dist): No. of stations -= 1.
Now, we will add the no. of stations regarding all the sections and the total will be the answer.
Algorithm:
First, we will find the maximum distance between two consecutive gas stations i.e. max(dist).
Place the 2 pointers i.e. low and high: Initially, we will place the pointers. The pointer low will point to 0 and the high will point to max(dist).
Now, we will use the ‘while’ loop like this: while(high - low > 10^(-6)).
Calculate the ‘mid’: Now, inside the loop, we will calculate the value of ‘mid’ using the following formula:
mid = (low+high) / 2.0
Eliminate the halves based on the number of stations returned by numberOfGasStationsRequired():
We will pass the potential value of ‘dist’, represented by the variable 'mid', to the ‘numberOfGasStationsRequired()' function. This function will return the number of gas stations we can place.
If result > k: On satisfying this condition, we can conclude that the number ‘mid’ is smaller than our answer. So, we will eliminate the left half and consider the right half(i.e. low = mid).
Otherwise, the value mid is one of the possible answers. But we want the minimum value. So, we will eliminate the right half and consider the left half(i.e. high = mid).
Finally, outside the loop, we can return either low or high as their difference is beyond 10^(-6). They both can be the possible answer. Here, we have returned the ‘high’.
The steps from 4-5 will be inside a loop and the loop will continue until (low-high <= 10^(-6)).

*/

import java.util.*;

public class tUf {
    public static int numberOfGasStationsRequired(double dist, int[] arr) {
        int n = arr.length; // size of the array
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            int numberInBetween = (int)((arr[i] - arr[i - 1]) / dist);
            if ((arr[i] - arr[i - 1]) == (dist * numberInBetween)) {
                numberInBetween--;
            }
            cnt += numberInBetween;
        }
        return cnt;
    }

    public static double minimiseMaxDistance(int[] arr, int k) {
        int n = arr.length; // size of the array
        double low = 0;
        double high = 0;

        //Find the maximum distance:
        for (int i = 0; i < n - 1; i++) {
            high = Math.max(high, (double)(arr[i + 1] - arr[i]));
        }

        //Apply Binary search:
        double diff = 1e-6 ;
        while (high - low > diff) {
            double mid = (low + high) / (2.0);
            int cnt = numberOfGasStationsRequired(mid, arr);
            if (cnt > k) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;
        double ans = minimiseMaxDistance(arr, k);
        System.out.println("The answer is: " + ans);
    }
}

/*
Output: The answer is: 0.5

Complexity Analysis
Time Complexity: O(n*log(Len)) + O(n), n = size of the given array, Len = length of the answer space.
Reason: We are applying binary search on the answer space. For every possible answer, we are calling the function numberOfGasStationsRequired() that takes O(n) time complexity. And another O(n) for finding the maximum distance initially.

Space Complexity: O(1) as we are using no extra space to solve this problem.
*/
