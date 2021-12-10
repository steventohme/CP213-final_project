package cp213;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Stores a HashMap of MenuItem objects and the quantity of each MenuItem
 * ordered. Each MenuItem may appear only once in the HashMap.
 *
 * @author Steven Tohme
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2021-11-01
 */
public class Order implements Printable {
    /**
     * The current tax rate on menu items.
     */
    public static final BigDecimal TAX_RATE = new BigDecimal(0.13);
    private Map<MenuItem, Integer> items = new HashMap<MenuItem, Integer>();

    /**
     * Update the quantity of a particular MenuItem in an order.
     *
     * @param item     The MenuItem to purchase - the HashMap key.
     * @param quantity The number of the MenuItem to purchase - the HashMap value.
     */
    public void add(MenuItem item, int quantity) {
    	if (items.containsKey(item)){
    		Integer updated_quantity = quantity + items.get(item);
    		items.put(item, updated_quantity);
    	}
    	else {
    		items.put(item, quantity);
    	}

    }

    /**
     * Calculates the total value of all MenuItems and their quantities in the
     * HashMap.
     *
     * @return the total price for the MenuItems ordered.
     */
    public BigDecimal getSubTotal() {
    	double total = 0;
    	for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
    		MenuItem item = entry.getKey();
    		BigDecimal price = item.getPrice();
    		Integer quantity = entry.getValue();
    		total += price.doubleValue() * quantity;
    	}
    	BigDecimal bd = new BigDecimal(total);
	return bd;
    }

    /**
     * Calculates and returns the total taxes to apply to the subtotal of all
     * MenuItems in the order. Tax rate is TAX_RATE.
     *
     * @return total taxes on all MenuItems
     */
    public BigDecimal getTaxes() {
    	BigDecimal subtotal = this.getSubTotal();
    	double tax_total = subtotal.doubleValue() * TAX_RATE.doubleValue();
    	BigDecimal bd = new BigDecimal(tax_total);
	return bd;
    }

    /**
     * Calculates and returns the total price of all MenuItems order, including tax.
     *
     * @return total price
     */
    public BigDecimal getTotal() {
	return new BigDecimal(this.getSubTotal().doubleValue() + this.getTaxes().doubleValue());
    }

    /**
     * Returns a String version of a receipt for all the MenuItems in the order.
     */
    @Override
    public String toString() {
    	String menu_string = "";
    	BigDecimal subtotal = this.getSubTotal();
    	BigDecimal taxes = this.getTaxes();
    	BigDecimal total = this.getTotal();
    	for (Map.Entry<MenuItem, Integer> entry : items.entrySet()) {
    		MenuItem item = entry.getKey();
    		String item_name = item.getName();
    		BigDecimal price_big = item.getPrice();
    		double price = price_big.doubleValue();
    		Integer quantity = entry.getValue();
    		menu_string = String.format("%-15s%2d @ $%5.2f = $%6.2f",item_name,quantity,price,price*quantity) + "\n";
    	}
    	menu_string += "\n" + String.format("Subtotal:                    $%6.2f",subtotal) + "\n" + String.format("Taxes:                       $%6.2f",taxes) + "\n"+ String.format("Total:                       $%6.2f",total);
    	

	return menu_string;
    }

    /**
     * Replaces the quantity of a particular MenuItem in an Order with a new
     * quantity. If the MenuItem is not in the order, it is added. If quantity is 0
     * or negative, the MenuItem is removed from the Order.
     *
     * @param item The MenuItem to update
     * @param quantity The quantity to apply to item
     */
    public void update(MenuItem item, int quantity) {
    	if (quantity <= 0) {
    		items.remove(item);
    	}
    	else {
    		items.put(item, quantity);
    	}

    }

    /*
     * Implements the Printable interface print method. Prints lines to a Graphics2D
     * object using the drawString method. Prints the current contents of the Order.
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
	int result = PAGE_EXISTS;

	if (pageIndex == 0) {
	    Graphics2D g2d = (Graphics2D) graphics;
	    g2d.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	    g2d.drawString(this.toString(),0,0);
	    

	} else {
	    result = NO_SUCH_PAGE;
	}
	return result;
    }
}