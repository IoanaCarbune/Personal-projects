package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import business.Restaurant;

public class RestaurantSerializator {
	public static void serialization(Restaurant restaurant) {
		try {
			FileOutputStream fileOut=new FileOutputStream("serfile.ser");
			ObjectOutputStream out=new ObjectOutputStream(fileOut);
			out.writeObject(restaurant);
			out.close();
			fileOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public static Restaurant deserialization() {
		Restaurant restaurant=null;
		try {
			FileInputStream fileIn=new FileInputStream("serfile.ser");
			ObjectInputStream in=new ObjectInputStream(fileIn);
			restaurant=(Restaurant)in.readObject();
			in.close();
			fileIn.close();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(restaurant==null)
			return new Restaurant();
		return restaurant; 
	}
}
