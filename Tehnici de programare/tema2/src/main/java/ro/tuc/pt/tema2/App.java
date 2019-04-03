package ro.tuc.pt.tema2;

import Controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
       launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		new MainController().start(primaryStage);
	}
}
