/*  Name:  Dylan Roberts
 * Course: CNT 4714 – Spring 2020      
 * Assignment title: Project 1 – Event-driven Enterprise Simulation      
 * Date: Saturday January 18, 2020 
*/

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import com.enterprisesim.model.Inventory;
import com.enterprisesim.view.GUIView;

public class Application {
	
	public static class BookData{
		public String bookID;
		public String bookTitleAndAuth;
		public String bookPrice;
	}

	public static void main(String[] args) {
		//System.out.print(java.time.LocalDate.now());

	
		// TODO Auto-generated method stub
		 File file = new File("src/resources/inventory.txt");
		 String allInventoryData = "";
		 int nbrLines = 0;

		 
		 try (Scanner scanner = new Scanner(file)){
			 int i = 0;

			 scanner.reset();
			   while ((scanner.hasNextInt()) || (scanner.hasNext())) {
				//   if (scanner.nextLine()())
				   try {
					allInventoryData += scanner.nextLine();
					allInventoryData += " ,";
				   } catch (Exception e) {
					   e.printStackTrace();
				   }
				   nbrLines++;
			   }
			  System.out.print("Number of lines is " + nbrLines);
			  Inventory myInventory = new Inventory(nbrLines);
			  System.out.print("allInventoryData is " + allInventoryData);
			  myInventory.setInventory(allInventoryData);

			  new GUIView(myInventory);
			  scanner.close();
		 }
			 catch (FileNotFoundException e) {
				 System.out.println("can't find file " + file.toString());
			 }
			 catch (IOException e) {
				 e.printStackTrace();
			 }
		
	}
	
}
