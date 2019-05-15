package bll;

import javafx.collections.ObservableList;
import model.Client;
import model.Orderr;
import model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.OrderDAO;

/**
 * The Class OrderBLL.
 */
public class OrderBLL {
	
	/** The order. */
	private static OrderDAO order= new OrderDAO();
	
	/**
	 * Adds the order.
	 *
	 * @param client the client
	 * @param product the product
	 * @param quantity the quantity
	 */
	public static void addOrder(Client client, Product product,int quantity) {
		Orderr newOrder=new Orderr(); 
		newOrder.setIdClient(client.getIdClient());
		newOrder.setIdProduct(product.getIdProduct());
		newOrder.setQuantity(quantity);
		newOrder.setClientFirstName(client.getFirstName());
		newOrder.setClientLastName(client.getLastName());
		newOrder.setProductName(product.getProductName());
		newOrder.setTotalOrderPrice(quantity*product.getProductPrice());
		order.add(newOrder);
		bill(newOrder);
	}
	
	/**
	 * Delete order.
	 *
	 * @param obj the obj
	 */
	public static void deleteOrder(Object obj) {
		order.deleteOrder(obj);
	}
	
	/**
	 * Bill.
	 *
	 * @param order the order
	 */
	public static void bill(Orderr order) {
		Document document = new Document();
		
         try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Bill.pdf"));
			document.open();
			
			Paragraph paragraphOne=new Paragraph("Bill\n");
			Paragraph paragraphTwo=new Paragraph(order.getOrderClient());
			Paragraph paragraphThree=new Paragraph(order.getProductName()+" "+order.getQuantity()+"x"+(double)order.getTotalOrderPrice()/order.getQuantity());
			Paragraph paragraphFour=new Paragraph("TOTAL "+ order.getTotalOrderPrice());
			document.add(paragraphOne);
			document.add(paragraphTwo);
			document.add(paragraphThree);
			document.add(paragraphFour);
			document.close();
			writer.close();
         } catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
         
	}
	
	/**
	 * Show orders.
	 *
	 * @return the observable list
	 */
	public static ObservableList showOrders() {
		return order.showAll();
	}

}
