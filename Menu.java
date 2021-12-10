package cp213;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.util.Scanner;
/**
 * Stores a List of MenuItems and provides a method return these items in a
 * formatted String. May be constructed from an existing List or from a file
 * with lines in the format:
 *
 * <pre>
1.25 hot dog
10.00 pizza
...
 * </pre>
 *
 * @author Steven Tohme
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2021-11-01
 */
public class Menu {

    /**
     * For testing. Reads contents of "menu.txt" from root of project.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
	try {
	    Menu menu = new Menu("menu.txt");
	    System.out.println(menu.toString());
	} catch (FileNotFoundException e) {
	    System.out.println("Cannot open menu file");
	}
	System.exit(0);
    }

    private List<MenuItem> items = new ArrayList<MenuItem>();

    /**
     * Constructor from a file of MenuItem strings. Each line in the file
     * corresponds to a MenuItem. You have to read the file line by line and add
     * each MenuItem to the ArrayList of items.
     *
     * @param filename The name of the file containing the menu items.
     * @throws FileNotFoundException Thrown if file not found or cannot be read.
     */
    public Menu(String filename) throws FileNotFoundException {

    	Scanner scanner = new Scanner(new File (filename));
    	while(scanner.hasNextLine()) {
    		String line = scanner.nextLine();
    		String price_string = "";
			String name = "";
			boolean price = true;
    		for (int i = 0; i < line.length(); i++) {
    			if (line.charAt(i) == ' ')	{
    				price = false;
    			}
    			if (price) {
    				price_string += line.charAt(i);
    			}
    			else {
    				name += line.charAt(i);
    			}
    					
    		}
    		items.add(new MenuItem(name,Double.parseDouble(price_string)));
    	}
    	scanner.close();

    }

    /**
     * Returns the List's i-th MenuItem.
     *
     * @param i Index of a MenuItem.
     * @return the MenuItem at index i
     */
    public MenuItem getItem(int i) {
    	MenuItem item = items.get(i);

	return item;
    }

    /**
     * Returns the Menu items as a String in the format:
     *
     * <pre>
    5) poutine      $ 3.75
    6) pizza        $10.00
     * </pre>
     *
     * where n) is the index + 1 of the MenuItems in the List.
     */
    @Override
    public String toString() {
    	String menu_string = "";
    	for (int i = 0; i < items.size(); i++) {
    		menu_string += String.format("%d)",i+1) + items.get(i).toString() + "\n";
    	}
    		

	return menu_string;
    }

    /**
     * Returns the number of MenuItems in the items List.
     *
     * @return Size of the items List.
     */
    public int size() {

	return items.size();
    }
}