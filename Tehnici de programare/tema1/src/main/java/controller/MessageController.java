package controller;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/*
 * Folosesc aceasta clasa pentru a afisa un mesaj de eroare in cazul unui input invalid.
 * Un mod de a scrie aceasta clasa am gasit pe stackoverflow,adaptandu-l la ideile mele
 * https://stackoverflow.com/questions/19968012/javafx-update-ui-label-asynchronously-with-messages-while-application-different
 * */
public class MessageController {
	
	public static void displayMessage(String msg, Label lbl, Color clr) {
		Task<Void> task= new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				updateMessage(msg);
				Thread.sleep(3000);
				updateMessage("");
				return null;
				
			}
			
		};
		lbl.textProperty().bind(task.messageProperty());
		lbl.setTextFill(clr);
		task.setOnSucceeded(e -> {
		      lbl.textProperty().unbind();
		      
		    });
		Thread thread=new Thread(task);
		thread.start();
		
	}
	
}
