## Feature - Loyatly Program
The loyalty program is a feature implemented in the CashRegister program which allows customer to have a loyatly account. The amount of loyalty points that can be accrued depends on how much was spent. In this case, it is 1 point for every $10 spent. Points can then be used to redeem offers such as discounts. The program keeps track of the customers that are part of the loyalty program, their membership number, and their current balance. Users of the software will be given the choice to enter a membership number (to update their existing balance), create a new account, or simply skip if they wish.

### Usage

After the last item has been added to the checkout, a prompy will display with the following options underneath:

```java
Do you have a UniSA Loyatly Card?
> Y - Yes, C - Create, Enter - Skip
```
It will ask you to select between three options:
*  **(1) Y - Yes**
    * User will be prompted to enter in the **membership number**.
    * If the username exists, the loyalty points will be added and it will return back to the cash register program.
    * If the username does not exist, it will ask if the user would like to create an account (see below).
    
*  **(2) C - Create**
    * User will be prompted to enter in the customer's **name**.
    * The program will then create an account for that customer.
    * It will then automatically add the required loyatly points to the account and return back to the cash register program.

*  **(3) Enter - Skip**
    * Choosing this option will ignore the first two options and continue with the program

