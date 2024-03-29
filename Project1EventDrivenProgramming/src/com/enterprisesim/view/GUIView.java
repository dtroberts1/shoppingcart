package com.enterprisesim.view;

/*  Name:  Dylan Roberts
 * Course: CNT 4714 � Spring 2020      
 * Assignment title: Project 1 � Event-driven Enterprise Simulation      
 * Date: Saturday January 18, 2020 
*/

import java.awt.BorderLayout;    
import java.awt.GridLayout; 
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.enterprisesim.model.Order;
import com.enterprisesim.model.BookData;
import com.enterprisesim.model.MyModel;
import com.enterprisesim.model.Updater;
import com.enterprisesim.model.Inventory;
import com.enterprisesim.view.ButtonWithExtras;
import com.enterprisesim.Listeners.MyListeners;

public class GUIView implements Updater{
	
	public static MyModel model;
	int currentItemNbr = 1;
	static JFrame myFrame;
	public ButtonWithExtras processBtn;
	BookData currentBookData;
	private Order order;
	
	ButtonWithExtras confirmBtn = new ButtonWithExtras();
	ButtonWithExtras viewOrderBtn = new ButtonWithExtras();
	ButtonWithExtras finishOrderBtn = new ButtonWithExtras();
	ButtonWithExtras newOrderBtn = new ButtonWithExtras();
	ButtonWithExtras exitBtn = new ButtonWithExtras();
	
	public JLabel nbrItemsLabel;
	public JLabel bookIDLabel ;
	public JLabel qtyLabel;
	public JLabel itemInfoLabel;
	public JLabel itemSubtotalLabel;
	Inventory myInventory;
	
	public static JTextField nbrItemsTxt;
	private JTextField bookIDTxt; 
	private JTextField qtyTxt; 
	private JTextField itemsInfoTxt; 
	private JTextField itemSubtotalTxt; 

    JPanel upperPane;
    JPanel inputPane;
    JPanel labelPane;
    
    public Order getOrder() {
    	return this.order;
    }
    public int getQty() {
    	return Integer.parseInt(this.qtyTxt.getText());
    }
    public void closeWindow() {
    	this.myFrame.setVisible(false);
    }
    
    public int getBookQty() {
    	return Integer.parseInt(this.qtyTxt.getText());
    }
    public String getBookIDTxt() {
    	return this.bookIDTxt.getText();
    }
    
	public static class TransactionItem extends BookData{
		//public int itemNbr;
		public int qty;
		public double discountPercent;
		public double subTotal;
	}
	
	public static BookData isValid() {
		// iterate through all the items from inventory and check if there is matching book id,
		// then return the book corresponding to the book ID
		return null;
	}
	
	public GUIView(Inventory myInventory) {
		model = new MyModel();
		model.isProcessed = true;
		currentBookData = new BookData();
		initializeGUI(model, false);
		this.myInventory = myInventory;
	}
	public static MyModel getModel() {
		return model;
	}
	public void reset() {
		this.order = null;
		this.model = new MyModel();
		currentItemNbr = 1;
		this.model.maxItems = 0;
		processBtn.setText("Process Item #" + this.currentItemNbr);
		confirmBtn.setText("Confirm Item #" + this.currentItemNbr);
		bookIDLabel.setText("Enter Book ID for Item #" + this.currentItemNbr + ": ");
		qtyLabel.setText("Enter Quantity for Item #" + this.currentItemNbr + ": ");

		bookIDTxt.setText("");
		bookIDTxt.repaint();
		qtyTxt.setText("");
		qtyTxt.repaint();
		nbrItemsTxt.setEnabled(true);
		nbrItemsTxt.setText("");
		nbrItemsTxt.repaint();
		itemsInfoTxt.setEnabled(false);
		itemsInfoTxt.setText("");
		itemsInfoTxt.repaint();
		itemSubtotalTxt.setEnabled(false);
		itemSubtotalTxt.setText("");
		itemSubtotalTxt.repaint();

		this.model.isProcessed = true;
		this.processBtn.setEnabled(true);
		this.confirmBtn.setEnabled(false);
		this.viewOrderBtn.setEnabled(false);
		this.finishOrderBtn.setEnabled(false);
		this.currentBookData = new BookData();
		this.order = null;
		//initializeGUI(this.model, true);
		this.myFrame.invalidate();
		this.myFrame.repaint();
	}
	
	@Override
	public void update() {
		String itemInfoStr = "";

		System.out.print("val of nbrItemsTxt is " + nbrItemsTxt.getText());
		model.setNbrItems(nbrItemsTxt);
		System.out.print("Outside, max items is " + model.maxItems);
		if (this.currentItemNbr <= model.maxItems)
		{
			System.out.print("less than max items");
			if (model.isProcessed)
			{
				if (this.currentItemNbr == 1) {
					System.out.print("now creating order, (Max items is " + model.maxItems);
					this.order = new Order(model.maxItems);
				}
				else {

				}
				// Add Data to populate last 2 rows
				BookData currentBookData = this.myInventory.isInInventory(this.getBookIDTxt(), this.currentItemNbr, false);
				if (currentBookData != null) {
					model.isProcessed = false;

					// If new item is processed
					System.out.println("New Item is processed.");
					processBtn.setEnabled(false);
					confirmBtn.setEnabled(true);
				itemInfoStr += currentBookData.bookID + " " + currentBookData.bookTitleAndAuth + " ";
				itemInfoStr += "$" + currentBookData.bookPrice + " ";
				itemInfoStr += String.format("%d", this.getQty()) + " ";
				itemInfoStr += (int)MyModel.getDiscount(this.getQty()) + "% ";
				itemInfoStr += String.format("%.2f", currentBookData.bookPrice * this.getQty());
				
				itemsInfoTxt.setText(itemInfoStr);
				itemInfoLabel.setText("Item #" + this.currentItemNbr + " Info:");
				
				nbrItemsTxt.setEnabled(false);
				itemsInfoTxt.setEnabled(false);
				itemSubtotalTxt.setEnabled(false);
				}
				else {
					
				}
			}
			// If new item is confirmed
			else if (model.isProcessed == false) {
				confirmBtn.setEnabled(false);
				model.isProcessed = true;
				
				BookData currentBookData = this.myInventory.isInInventory(this.getBookIDTxt(), this.currentItemNbr, true);
				// If Book ID Matches
				if (currentBookData != null)
				{							
					this.currentItemNbr++;
					System.out.println("New Item is Confirmed");
					if (this.currentItemNbr <= model.maxItems) // if the last confirmed item isn't the last
					{
						processBtn.setText("Process Item #" + this.currentItemNbr);
						confirmBtn.setText("Confirm Item #" + this.currentItemNbr);
						processBtn.setEnabled(true);
					    bookIDLabel.setText("Enter Book ID for Item #" + this.currentItemNbr + ": ");
					    qtyLabel.setText("Enter Quantity for Item #" + this.currentItemNbr + ": ");
					}
					else {
						// Enable the other buttons
						viewOrderBtn.setEnabled(true);
						finishOrderBtn.setEnabled(true);
						processBtn.setEnabled(false);
					}
					
					if (this.order == null)
						System.out.print("order is null");
					else
						System.out.print("order is NOT null");
					this.order.addItem(currentBookData, this.getBookQty());
					//itemSubtotalTxt.setText("$" + String.format("%.2f", currentBookData.bookPrice * this.getQty()));
					itemSubtotalTxt.setText("$" + String.format("%.2f", this.getOrder().getOrderSubtotal()));
					itemSubtotalLabel.setText("Subtotal for " + (this.currentItemNbr - 1) + ((this.currentItemNbr - 1) == 1? " item" : " items") + ": ");

				}
			}		
		}
		else {
			processBtn.setEnabled(false);
			confirmBtn.setEnabled(false);
			System.out.println("==========");
			System.out.println("currentItemCount: " + this.currentItemNbr);
			System.out.println("Max Count: " + model.maxItems);
			System.out.println("isProcessed: " + model.isProcessed);
		}
		System.out.println("updating..");
	}
    
    public void initializeGUI(MyModel model, boolean reset) {
		// Frame
    	if (!reset)
    		myFrame = new JFrame();
		myFrame.setBounds(420, 120, 770, 200);
	//	myFrame.setLocationRelativeTo(null);
		
		MyListeners myListener = new MyListeners();
		processBtn = new ButtonWithExtras("Process Item #" + this.currentItemNbr);
		processBtn.setBtnParent(this);
		processBtn.addActionListener(myListener);
		
		// Buttons
		confirmBtn = new ButtonWithExtras("Confirm Item #" + this.currentItemNbr);
		confirmBtn.setBtnParent(this);
		confirmBtn.addActionListener(myListener);

		confirmBtn.setEnabled(false);
		viewOrderBtn = new ButtonWithExtras("View Order");
		viewOrderBtn.addActionListener(myListener);
		viewOrderBtn.setBtnParent(this);
		//viewOrderBtn.addActionListener(myListener);

		viewOrderBtn.setEnabled(false);
		finishOrderBtn = new ButtonWithExtras("Finish Order");
		finishOrderBtn.setBtnParent(this);
		finishOrderBtn.addActionListener(myListener);
		finishOrderBtn.setEnabled(false);
		newOrderBtn = new ButtonWithExtras("New Order");
		newOrderBtn.addActionListener(myListener);
		newOrderBtn.setBtnParent(this);
		newOrderBtn.addActionListener(myListener);
		exitBtn = new ButtonWithExtras("Exit");
		exitBtn.setBtnParent(this);
		exitBtn.addActionListener(myListener);
		
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    nbrItemsLabel = new JLabel("Enter Number of Items For this Order:");
	    bookIDLabel = new JLabel("Enter Book ID for Item #" + this.currentItemNbr + ": ");
	    qtyLabel = new JLabel("Enter Quantity for Item #" + this.currentItemNbr + ": ");
	    itemInfoLabel = new JLabel("Item #1 Info:");
	    itemSubtotalLabel = new JLabel("Subtotal for 0 items: ");
	    
	    nbrItemsTxt = new JTextField("", 30); 
	    bookIDTxt = new JTextField("", 30);
	    qtyTxt = new JTextField("", 30); 
	    itemsInfoTxt = new JTextField("", 30); 
	    itemsInfoTxt.setEnabled(false);
	    itemSubtotalTxt = new JTextField("", 30); 
	    itemSubtotalTxt.setEnabled(false);
	    
	    upperPane = new JPanel();
	    inputPane = new JPanel();
	    labelPane = new JPanel();
	    
	    labelPane.add(nbrItemsLabel);
	    labelPane.add(bookIDLabel);
	    labelPane.add(qtyLabel);
	    labelPane.add(itemInfoLabel);
	    labelPane.add(itemSubtotalLabel);
	    
	    inputPane.add(nbrItemsTxt);
	    inputPane.add(bookIDTxt);
	    inputPane.add(qtyTxt);
	    inputPane.add(itemsInfoTxt);
	    inputPane.add(itemSubtotalTxt);

	    inputPane.setLayout(new BoxLayout(inputPane, BoxLayout.Y_AXIS));
	    labelPane.setLayout(new BoxLayout(labelPane, BoxLayout.Y_AXIS));

	    upperPane.add(labelPane);
	    upperPane.add(inputPane);
	    
	    inputPane.setBounds(0, 0, 200, 300);
		
		JPanel lowerGrid = new JPanel(new GridLayout(1,3));
		lowerGrid.add(processBtn);
		
		lowerGrid.add(confirmBtn);
		lowerGrid.add(viewOrderBtn);
		lowerGrid.add(finishOrderBtn);
		lowerGrid.add(newOrderBtn);
		lowerGrid.add(exitBtn);
		

		myFrame.add(upperPane, BorderLayout.NORTH);

		myFrame.add(lowerGrid, BorderLayout.SOUTH);
		myFrame.setVisible(true);
    }
}
