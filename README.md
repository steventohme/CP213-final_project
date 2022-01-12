# CP213-final_project

This project is written entirely in Java. It implements two forms of a cashier system. 
One using text-based outputs, found in the RestaurantText.java file, 
and one using Java GUI implemented with swing, found in the RestaurantGUI.java file.

The RestaurantText.java file asks the user for commands representing menu items and
the quantity wanted. If the user enters an incorrect input as a command the menu is presented
again. If the user enters an incorrect input as a quantity they are asked to enter another command.
Once the user enters 0 as a command they are given the receipt of their total order.

RestaurantGUI is almost identical to RestaurantText in function however the user is prompted with
a window that they can enter quantities for the menu items. If the user enters an incorrect value
the JTextbox value is returned to 0. The receipt is generated automatically on any input.
