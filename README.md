## Sample Code
This was an individual project for one of my courses. The program processes an order provided in CSV format. The inventory data is hard coded. If the order is valid, it will generate a CSV file containing the items and total price of the order. If the order is invalid, the system will generate a TXT file stating the problem. The order is invalid if it exceeds the cap limit for a category or available quantity of items.

## Requirements
- Java 8

## Steps to run the program
1. Clone the repository

2. Navigate to the src directory:
```
cd src
```

3. Compile the code:
```
javac Billing.java
```

4. Run the program:
```
java Billing
```

5. You will be prompted to enter the input file path. Enter the path and hit the Enter key.

Note: The output file will be generated in the output folder in the Billing directory. If the order exceeded a category cap, the output.txt file will list all the items in that order that is in that category.

## Maximum cap for each category
- Essentials: 12
- Luxury: 7
- Misc: 10

## Design patterns used
- Singleton
    - The Singleton design pattern ensures only a single instance of an object exists within a system. Inventory and Cards are singleton classes to ensure that we are using the same Inventory and Cards object in the system. We want all classes to be accessing the same Inventory and Cards database.
- Chain of Responsibility
    - The Chain of Responsibility design pattern allows us to give multiple objects a chance to handle the request. The order will first be passed to the ValidateQuantity handler, where it checks that the requested quantity does not exceed the available quantity in the inventory. If the order has a valid quantity, it will be passed to the ValidateCategoryCap handler. If the order did not exceed any category cap, it will be passed to the CalculatePrice handler. The CalculatePrice handler will calculate the price and pass the order to the CheckCardNumber handler, which will add the order's card number to the Cards database if it is not present in the database. Then the order gets passed to the GenerateOutputCSV handler, which will generate the output.csv containing the order information and total price. Each of the handlers processes the portion of the order it is responsible for and passes the order to its successor if there were no issues. If the ValidateQuantity or ValidateCategoryCap handler decides that the order is not valid, it will generate an output.txt file. The order will not get passed to its successor, and the programs ends there.