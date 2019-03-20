package ro.tuc.pt.tema1;

import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application
{

	@Override
	public void  start(Stage primaryStage) throws Exception {
		new MainController().start(primaryStage);
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
 