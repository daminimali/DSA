/* Problem Statement: Given the head of a linked list, print the length of the linked list.

Examples
Example 1:

Input Format: 0->1->2

Result: 3

Explanation: The list has a total of 3 nodes, thus the length of the list is 3.

Example 2:

Input Format: 2->5->8->7

Result: 4

Explanation: Again, the list has 4 nodes, hence, the list length is 4.

Solution:

Approach:
The most naive method to find the length of a linked list is to count the number of nodes in the list by doing a traversal in the Linked list.

Algorithm:
Start by initializing a pointer to the head that will be used for traversing and initializing a cnt variable to 0.

Traverse the linked list using the pointer, and at every node, increment cnt.

After reaching the end of the linked list, return cnt, this will be your total number of nodes which is the length of the linked list.
*/

class Node{
    int data;
    Node next;
    Node(int data1, Node next1){
        this.data=data1;
        this.next=next1;
    }
    Node(int data1){
        this.data=data1;
        this.next=null;
    }
};
public class LinkedList{
    // Function to calculate the length of a linked list
    private static int lengthofaLL(Node head){
        int cnt=0;
        Node temp=head;
        // Traverse the linked list and count nodes
        while(temp!=null){
            temp = temp.next;
            cnt++;// increment cnt for every node traversed
        }
        return cnt;
    }
    public static void main(String[] args) {
        int[]arr={2,5,8,7};
        Node head = new Node(arr[0]);
        head.next= new Node(arr[1]);
        head.next.next= new Node(arr[2]);
        head.next.next.next= new Node(arr[3]);
        // Print the length of the linked list
        System.out.println(lengthofaLL(head));
    }
}
/*
Output: 4

Time Complexity: 
Since we are iterating over the entire list,  time complexity is O(N).

Space Complexity:
We are not using any extra space, thus space complexity is O(1) or constant.
*/
