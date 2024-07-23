/*Problem Statement: Given a linked list, delete the tail of the linked list and print the updated linked list.

Examples
Example 1:

Examples:

Input Format: 0->1->2

Result: 0->1


Explanation: The tail of the list is the last node. After removing the tail, and updating the linked list, this result is what we get.

Example 2:

Input Format: 12->5->8->7

Result: 12->5->8

Explanation: Again, after deleting the tail and updating the linked list, the list ends at the second last node, which is the new tail.

Solution

Approach:
The main intuition is to point the second last node to null to get the updated linked list. Hence, we will iterate till the second last node and make it point to NULL. This will effectively skip the last node of the list therefore, we will free up the memory being occupied by this node (Only in the case of C++).

Two edge cases to consider are:

If the input linked list is empty, we return null.
If there is only one node in the list, that node itself will be the tail, therefore we return null after deleting that node.
Algorithm:
Start by initializing a pointer to the head of the list that will be used to iterate through the linked list. Iterate up to the second last node, this node will be our new tail of the list.

After reaching the second-to-last node, free up the memory occupied by the former tail or the last node of the list.
Note: In the case of languages like Java, Python, and Javascript, there is no need for the deletion of objects or nodes because these have an automatic garbage collection mechanism that automatically identifies and reclaims memory that is no longer in use.

Finally, point the second last node or the new tail to NULL to obtain the updated linked list.
*/
// Node class definition
class Node {
    int data;
    Node next;
    // Constructor with both data and next pointer
    Node(int data1, Node next1) {
        this.data = data1;
        this.next = next1;
    }
    // Constructor with only data (next pointer set to null)
    Node(int data1) {
        this.data = data1;
        this.next = null;
    }
}
// LinkedList class
public class LinkedList {
    // Function to delete the tail of the linked list
    private static Node deleteTail(Node head) {
        // Check if the linked list is empty or has only one node
        if (head == null || head.next == null)
            return null;
        // Create a temporary pointer for traversal
        Node temp = head;
        // Traverse the list until the second-to-last node
        while (temp.next.next != null) {
            temp = temp.next;
        }
        // Nullify the connection from the second-to-last node to delete the last node
        temp.next = null;
        // Return the updated head of the linked list
        return head;
    }
    // Function to print the linked list
    private static void printLL(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }
    // Main method
    public static void main(String[] args) {
        // Initialize an array with integer values
        int[] arr = {2, 5, 8, 7};
        // Create the linked list with nodes initialized with array values
        Node head = new Node(arr[0]);
        head.next = new Node(arr[1]);
        head.next.next = new Node(arr[2]);
        head.next.next.next = new Node(arr[3]);
        // Delete the tail of the linked list
        head = deleteTail(head);
        // Print the modified linked list
        printLL(head);
    }
}
/*
Output: 12 5 8

Time Complexity: O(N) for traversing the linked list and updating the tail.
*/
Space Complexity: O(1), as we have not used any extra space.
