/*What is Recursion?
It is a phenomenon when a function calls itself indefinitely until a specified condition is fulfilled.

*What is Stack Overflow in Recursion?
-Whenever recursion calls are executed, they’re simultaneously stored in a recursion stack where they wait for the completion of the recursive function. A recursive function can only be completed if a base condition is fulfilled and the control returns to the parent function. 
-But, when there is no base condition given for a particular recursive function, it gets called indefinitely which results in a Stack Overflow i.e, exceeding the memory limit of the recursion stack and hence the program terminates giving a Segmentation Fault error. 
-The illustration above also represents the case of a Stack Overflow as there is no terminating condition for recursion to stop, hence it will also result in a memory limit exceeded error. 

*Base Condition
It is the condition that is written in a recursive function in order for it to get completed and not to run infinitely. After encountering the base condition, the function terminates and returns back to its parent function simultaneously.
To get a better understanding of how the base condition is an integral part of recursive functions, let us see an example below : 
Let’s say we have to print integers starting from 0 till 2 only, this will be how the pseudocode for it will look like 

int count = 0;
void func(){

   if(count == 3 ) return;
   print(count);
   count++;
   func();

}

main()
{

  print();

}
According to this pseudocode, the function will increment and print the value of count and then return when the base condition becomes true i.e, it will only print 0,1,2 and 3 and then execution gets completed.
Recursive code for printing numbers from 0 to 3 : 
*/
class Recursion {
    static int cnt = 0;
    static void print(){
        
         // Base Condition.
         if(cnt == 3 ) return;
         System.out.println(cnt);

         // Count incremented.
         cnt = cnt+1;
         print();

    }
    public static void main(String[] args) {
        print();
    }
}
/*
Output: 
0
1
2

*Recursive Tree
A recursive tree is basically a representative form of recursion which depicts how functions are called and returned as a series of events happening consecutively.
When a recursive call gets completed, the control returns back to its parent function which is then further executed until the last function waiting in the recursive stack returns.

As a summary of the lecture, the basics of recursion such as the following were clear to us : 

What is Recursion
Base Condition
Stack Overflow / Stack Space
Recursion Tree
*/
