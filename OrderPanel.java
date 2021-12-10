package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterJob;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author Steven Tohme
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2021-11-01
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
	    final PrinterJob printJob = PrinterJob.getPrinterJob();
	    printJob.setPrintable(order);

	    if (printJob.printDialog()) {
		try {
		    printJob.print();
		} catch (final Exception printException) {
		    System.err.println(printException);
		}
	    }
	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {

    private JTextField field;

	@Override
	public void focusGained(final FocusEvent evt) {
		field = (JTextField) evt.getSource();
		field.selectAll();
	}

	@Override
	public void focusLost(final FocusEvent evt) {
		int num = 0;
		try {
			if (Integer.valueOf(field.getText()) >= 0) {
				if (field == hotdogfld) {
					num = 0;
				}
				else if(field == hamburgerfld) {
					num = 1;
				}
				else if(field == cheeseburgerfld) {
					num = 2;
				}
				else if(field == friesfld) {
					num = 3;
				}
				else if(field == poutinefld) {
					num = 4;
				}
				else if(field == pizzafld) {
					num = 5;
				}
				else if (field == drinkfld) {
					num = 6;
				}
				OrderPanel.this.order.update(OrderPanel.this.menu.getItem(num), Integer.valueOf(field.getText()));
				subtotalLabel.setText(String.format("$%5.2f",OrderPanel.this.order.getSubTotal()));
				taxLabel.setText(String.format("$%5.2f",OrderPanel.this.order.getTaxes()));
				totalLabel.setText(String.format("$%5.2f",OrderPanel.this.order.getTotal()));
				}
			else {
				field.setText("0");
				OrderPanel.this.order.update(OrderPanel.this.menu.getItem(num),Integer.valueOf(field.getText()));
			}
			
		}
		catch (NumberFormatException ex) {
			field.setText("0");
		}
    }
    }

    // Attributes
    private Menu menu = null;
    private final Order order = new Order();
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");
    
    private final JTextField hotdogfld = new JTextField(4);
    private final JTextField hamburgerfld = new JTextField(4);
    private final JTextField cheeseburgerfld = new JTextField(4);
    private final JTextField friesfld = new JTextField(4);
    private final JTextField poutinefld = new JTextField(4);
    private final JTextField pizzafld = new JTextField(4);
    private final JTextField drinkfld = new JTextField(4);
    
    private JTextField[] fields = {hotdogfld,hamburgerfld,cheeseburgerfld,friesfld,poutinefld,pizzafld,drinkfld};



    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.layoutView();
    }

    /**
     * Uses the GridLayout to place the labels and buttons.
     */
    private void layoutView() {
	this.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
	// Number of rows of GridLayout must be updated to accommodate all MenuItems,
	// totals, and Print button
	this.setLayout(new GridLayout(12, 3));
	this.add(new JLabel("Item"));
	this.add(new JLabel("Price"));
	this.add(new JLabel("Quantity"));

	// Add all other JLabels, JTextFields, and JButtons here
	for (int i = 0; i < this.menu.size(); i++) {
		this.add(new JLabel(this.menu.getItem(i).getName()));
		this.add(new JLabel(String.valueOf(this.menu.getItem(i).getPrice())));
		this.add(fields[i]);
		this.fields[i].setText("0");
	}
	
	this.add(new JLabel("Subtotal:"));
	this.add(new JLabel());
	this.add(subtotalLabel);
	this.add(new JLabel("Tax:"));
	this.add(new JLabel());
	this.add(taxLabel);
	this.add(new JLabel("Total:"));
	this.add(new JLabel());
	this.add(totalLabel);
	this.add(new JLabel());
	
	
	
	
	// Register listeners here
	
	for (int i = 0; i < this.menu.size();i++) {
		this.fields[i].addFocusListener(new QuantityListener());
	}

	// Register the PrinterListener with the print button.
	this.printButton.addActionListener(new PrintListener());
	this.add(this.printButton);
	return;
    }
}