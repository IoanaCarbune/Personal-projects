package presentation;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * The Class MessageController.
 */
public class MessageController {
	
	/**
	 * Display message.
	 *
	 * @param msg the msg
	 * @param lbl the lbl
	 * @param clr the clr
	 */
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

