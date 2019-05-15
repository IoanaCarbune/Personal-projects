package start;

import javafx.application.Application;
import javafx.stage.Stage;
import presentation.MainController;

public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainController ctrl=new MainController(primaryStage);
		
	}
}
