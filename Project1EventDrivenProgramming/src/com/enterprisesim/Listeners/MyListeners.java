package com.enterprisesim.Listeners;

/*  Name:  Dylan Roberts
 * Course: CNT 4714 – Spring 2020      
 * Assignment title: Project 1 – Event-driven Enterprise Simulation      
 * Date: Saturday January 18, 2020 
*/

import java.awt.event.ActionEvent;   
import java.awt.event.ActionListener;
import com.enterprisesim.view.ButtonWithExtras;
import com.enterprisesim.view.GUIView;

public class MyListeners implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String conditionString = e.getActionCommand().replaceAll("\\d", "");
		//System.out.printf("source is %s", conditionString);
		
		if (e.getSource() instanceof ButtonWithExtras) {
			GUIView guiView = ((ButtonWithExtras)e.getSource()).getButtonParent();

			System.out.print("Type is correct. view is " + guiView.toString());
		
			if(conditionString.equals("Confirm Item #"))
			{	
				guiView.update();
			}	
			else if(conditionString.equals("Process Item #"))
			{			
				guiView.update();
			}	
			else if(conditionString.equals("View Order"))
			{			
				guiView.getOrder().viewOrder(guiView.getModel());
			}
			else if(conditionString.equals("New Order"))
			{			
				guiView.reset();
			}	
			else if(conditionString.equals("Finish Order"))
			{	
				guiView.getOrder().displayFinalPrompt();
				guiView.getOrder().printoutOrder();
				guiView.closeWindow();
	
			}		
			else if(conditionString.equals("Exit"))
			{			
				guiView.closeWindow();
			}	
		}
	}

	

}
