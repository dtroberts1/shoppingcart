package com.enterprisesim.view;
import javax.swing.JButton; 
import com.enterprisesim.view.GUIView;

/*  Name:  Dylan Roberts
 * Course: CNT 4714 – Spring 2020      
 * Assignment title: Project 1 – Event-driven Enterprise Simulation      
 * Date: Saturday January 18, 2020 
*/

public class ButtonWithExtras extends JButton{
	private GUIView guiViewParent;
	
	public ButtonWithExtras() {
		super();
	}
    public ButtonWithExtras(String text) {
		super(text);
	}
	public GUIView getButtonParent() {
		return this.guiViewParent;
	}
	public void setBtnParent(GUIView guiView) {
		this.guiViewParent = guiView;
	}
	
}
