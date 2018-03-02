import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main (String[] arg) {
		launch(arg);
		
	}
	@Override
	public void start(Stage mainWindow) throws Exception {
		
	Instrument guitar= new Guitar(true);
	
	
	
	FretBoard fret=new FretBoard (guitar,true, 4, false,  mainWindow);
	fret.showFretBoard();
	
	
	}
	

}
