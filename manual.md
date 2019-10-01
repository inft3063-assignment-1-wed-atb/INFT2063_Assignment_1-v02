# Cash Register Manual

## About the Project
The cash register software is a program that allows users to process transacation of various items such as groceries and other goods. It includes several important featues such as: an in-built loyalty program where customers can earn points for spending, multiple payment options, printing of receipts (if requested), and an automatically generated summary report of the transacations processed. The software also includes a secure authorisation systems where only authorised users can access the software. 

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)  
* [Getting started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Features](#features)
  * [Feature - User Authentication](#feature---user-authentication)
  * [Feature - Loyalty Program](#feature---loyalty-program)
  * [Feature - Reporting](#feature---loyalty-program)
  * [Feature - Payment Options](#feature---payment-options)
  * [Feature - Reporting](#feature---reporting)

# Getting started

## Prerequisites

## Installation

## Features

### Feature - User Authentication
The user authentication feature was implemented to protect the cash register program from unauthorised access and act as a way to differentiate between different cashiers. The program stores all existing users and their associated passwords locally in an array. Verification involves comparing the user's input with the existing usernames and passwords held in said array. In an effort to deter brute-forcing attacks and automated password inputs, the feature comes along with a robot check that counts the number of attempts an input has failed. If there have been too many failed attempts, the feature will force close the program.

#### Usage

The User Authentication feature begins with the following options:
```java
> L - Login | S - Sign_up | E - Exit
```
It will ask you to select between three options:
*  **(1) L - Login**
    * This will ask the user for an existing ***username***.
    * If the username entered is located, the program will proceed to ask the user for a ***password***.
    * If the password entered matches with the password assocaited with the username, then authentication is complete.
    * However, if user either enters a non-existent username or incorrect password, they will return to the main menu.
    
*  **(2) S - Sign_up**
    * This will ask the user for a new ***username*** to use.
    * Once the username has been entered, the program will proceed to ask the user for a new ***password***.
    * The program will display the new username and associated password and confirm successful sign up.

*  **(3) G - Guest**
    * Selecting this option will assign current user a guest account.
    * The program will inform current user that they are logging on as guest.
    * User can now proceed to main program under the guest account.

*  **(4) E - Exit**
    * Selecting this option will force the program to close.
    
* Note: Too many failed attempts will force shut the program.

### Feature - Loyalty Program
The loyalty program is a feature implemented in the CashRegister program which allows customer to have a loyalty account. The amount of loyalty points that can be accrued depends on how much was spent. In this case, it is 1 point for every $10 spent. Points can then be used to redeem offers such as discounts. The program keeps track of the customers that are part of the loyalty program, their membership number, and their current balance. Users of the software will be given the choice to enter a membership number (to update their existing balance), create a new account, or simply skip if they wish.

#### Usage

After the last item has been added to the checkout, a prompt will display with the following options underneath:

```java
Do you have a UniSA Loyalty Card?
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
    * It will then automatically add the required loyalty points to the account and return back to the cash register program.

*  **(3) Enter - Skip**
    * Choosing this option will ignore the first two options and continue with the program

### Feature - Payment Options
The system supports three different methods of payment. Customers can pay by bank card, cash or by gift card. When the customer chooses to pay by bank card or by gift card, the system awaits the customer to issue the card to the EFTPOS machine to process the payment. The system does not record any of the customer’s bank card or gift card details and link them directly to the customer’s bank or the gift card system. If the customer wants to pay by cash, the amount tendered is recorded and the required change is calculated by the system. It is validated if the customer is paying the total cost of the item when making payments. 

### Usage: 
After a customer skips or add their loyalty account to the sale, a prompt will display with the following options underneaths: 
```java
Please Choose the type of payment
>1. Cash 
>2. Bank Card 
>3. Gift Card
```
It will ask the user to select between three options:

*  **(1) 1 - Cash**
    * User will be prompted to enter the **cash amount tendered**.
    * If the cash tendered is equalled to or more than the total items cost then the amount of change required is displayed.
    * If the cash tendered is less than the total items cost, a warning will display and the user will be prompted to enter the cash amount tendered again.

*  **(2) 2 – Bank card**
    * User will issue the bank card to the EFTPOS machine which will be connected to the bank system.
    *Processing progress bar will be displayed in the system while the EFTPOS machine process the payment.
    *If the payment is successful a payment successful dialog will be displayed.
    
* **(3) 3 – Gift card**
	* User will issue the gift card to the EFTPOS machine which will be connected to the gift card system.
    * Processing progress bar will be displayed in the system while the EFTPOS machine process the payment.
    * If the payment is successful a payment successful dialog will be displayed.

### Feature - Reporting
The system automatically generates a summary report of all the transactions. The system will display the report and export it in a text file before the system terminates. The report will have various information about the transaction including date and time of the transaction, total amount and cashier. The report will also provide inventory information that would be very useful for inventory management. The report will also present information about starting and ending balance of the cash register.

### Usage:
After the user logs in and enter the cash register’s amount or after a transaction is complete, a prompt will display with the following options:
```java
Would you like to proceed with the transaction? (y/n)
```
It will ask the user to select between two options:

*  **(1) Y - Yes**
    * A new transaction will occur and the user is asked to provide further information

*  **(2) N – No**
    * A summary report of all the transactions is displayed by the system and a text file of the report is generated.
