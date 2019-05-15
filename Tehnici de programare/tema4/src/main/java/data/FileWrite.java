package data;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import business.MenuItem;
import business.Order;
import javafx.collections.ObservableList;

import java.io.IOException;

public class FileWrite {
	private String path;
	private boolean appendToFile=false;
	public FileWrite(String path, Boolean append) {
		this.path=path;
		this.appendToFile=append;
	}

	public void writeToFile(Order order, Map<Order, List<MenuItem>>list, Double price) {
		try {
			FileWriter write=new FileWriter(this.path, appendToFile);
			PrintWriter printLine=new PrintWriter(write);
			printLine.println( "Order id: " + order.getOrderId());
			printLine.println("Date: "+ order.getDate());
			printLine.println("Table: "+ order.getTable());
			for(MenuItem currentItem: list.get(order)) {
				printLine.println( currentItem.getName() +"\t X \t"+ currentItem.computePrice());
			}
			printLine.println("\tTOTAL "+ price );
			printLine.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
