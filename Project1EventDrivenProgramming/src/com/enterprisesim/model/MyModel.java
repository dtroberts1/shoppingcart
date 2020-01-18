package com.enterprisesim.model;

/*  Name:  Dylan Roberts
 * Course: CNT 4714 � Spring 2020      
 * Assignment title: Project 1 � Event-driven Enterprise Simulation      
 * Date: Saturday January 18, 2020 
*/

import javax.swing.JTextField; 

public class MyModel {
	public int currentItemNbr = 0;
	public int maxItems = 0;
	public boolean isProcessed;

	public static double getDiscount(int qty) {
		if ((qty >= 1) && (qty < 5)){
			return 0;
		}
		else if ((qty >= 5) && (qty < 10)) {
			return 0.1 * 100;
		}
		else if((qty >= 10) && (qty < 15)) {
			return 0.15 * 100;
		}
		else {
			return 0;
		}
	}

	public int getCurrentItemNbr() {
		return currentItemNbr;
	}

	public void setCurrentItemNbr(int currentItemNbrParam) {
		currentItemNbr = currentItemNbrParam;
	}

    public void setNbrItems(JTextField nbrItemsTxt) {
    	if (nbrItemsTxt.getText().equals(""))
    		maxItems = 0;
    	else
    		maxItems = Integer.parseInt(nbrItemsTxt.getText());
    	System.out.println("Max items is now " + maxItems);
    	System.out.println("And Current item number is " + currentItemNbr);
    }
    
}
