package cp213;

import java.util.Scanner;
/**
 * Wraps around an Order object to ask for MenuItems and quantities.
 *
 * @author Steven Tohme
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2021-11-01
 */
public class Cashier {

    private Menu menu = null;
    /**
     * Constructor.
     *
     * @param menu A Menu.
     */
    public Cashier(Menu menu) {
	this.menu = menu;
    }
    
    private Order repeat_ask(Order order) {
    	Scanner keyboard = new Scanner(System.in);
    	System.out.println("Enter a command: ");
    	if (keyboard.hasNextInt()) {
    		int command = keyboard.nextInt();
    		if (command == 0) {
    			System.out.println("----------------------------------------");
    			System.out.println("Receipt");
    			System.out.println(order.toString());
    			}
    		else {
    			if (command > menu.size()) {
        			System.out.println(this.menu.toString());
            	    System.out.println("Press 0 when done");
            	    System.out.println("Press any other key to see menu again \n");
        		    this.repeat_ask(order);
        			}
    			else {
    				MenuItem food = this.menu.getItem(command - 1);
    				System.out.println("How many do you want?: ");
    				if (keyboard.hasNextInt()) {
    					int amount = keyboard.nextInt();
    					order.add(food, amount);
    					this.repeat_ask(order);
    				}
    				else {
    					System.out.println("Not a valid number");
    					this.repeat_ask(order);
    					}
    	
    				}
    			}
    		}
        else {
        	System.out.println("Not a valid number \n");
        	System.out.println("Menu:");
        	System.out.print(this.menu.toString());
        	System.out.println("Press 0 when done");
        	System.out.println("Press any other key to see menu again \n");
        	this.repeat_ask(order);
        }
   			
    	keyboard.close();
    	return order;
    }

    /**
     * Asks for commands and quantities. Prints a receipt when all orders have been
     * placed.
     *
     * @return the completed Order.
     */
    public Order takeOrder() {
    	System.out.println("Welcome to WLU Foodorama! \n");
    	System.out.println("Menu:");
    	System.out.print(this.menu.toString());
    	System.out.println("Press 0 when done");
    	System.out.println("Press any other key to see menu again \n");
    	Order order = this.repeat_ask(new Order());
	return order;
    }
}