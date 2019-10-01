# Code Optimisation and Improvements
Below are the changes that was made to the program and system. The changes are split into two areas: general and specific. The general improvements include areas such as changing of variable names and the system prompt messages. It doesn't change the internal structure of the system, rather it is done to improve readability, making it maintainable, improving overall efficiency without altering the way it operates. The specific improvements includes fixes and additions to the program that will give it a different feel and the way it operates. 

## General Improvements

## Specific Improvements

### Dynamic Receipt Names and Transaction Number
The receipt class was modified to include the implementation of dynamic receipt names. Previously, every time a receipt was requested, the program would overwrite the existing file. With this change, the receipt file name will have a unique name (The date and time that the receipt was printed). A transaction number was also included in the receipt file. This is purely for aesthetics reasons as many receipts would keep track of the transaction number. 

### Allow User to Enter Quantity of Items
The user will now be given the option to specific the quantity of the items that is being processed. This change was made so that it'll be more convenient for the user to process the item instead of having to repeatedly enter the same item again. The number of items desired is also reflected in the receipt output. 
