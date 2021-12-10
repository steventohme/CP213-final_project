# CP213-final_project

This project is written etnirely in Java. It implements two forms of a cahsier system. 
One using text based outputs, found in the RestaurantText.java file, 
and one using Java GUI implemented with swing, found in the RestaurantGUI.java file.

The RestaurantText.java file asks the user for commands representing menu items and
quantity wanted. If the user enters an incorrect input as a command the menu is presented
again. If the user enters an incorrect input as a quantity they are asked to enter another command.
Once the user enters 0 as a command they are given the reciept of their total order.

RestaurantGUI is almost identical to RestaurantText in function hwoever the user is prompeted with
a window that they can enter quantities for the menu items. The reciept is generated automatically.
