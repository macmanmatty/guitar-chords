import java.awt.Insets;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class OptionPane {
	int option;
	int count;
	int []  number= new int [2];
	TextField stringsField;
	OptionPane(){}
	public void showOptionPane(String text, String buttonText){
		Runnable  runnable=	new Runnable(){
			@Override 
			public void run(){
	Stage dialogPane = new Stage();
    dialogPane.initModality(Modality.APPLICATION_MODAL);
Button button = new Button(buttonText);
    button.setOnAction(new EventHandler<ActionEvent>() { 
		@Override
        public void handle( ActionEvent k ) {
			option=1;
			dialogPane.hide();
        }
    });
    VBox vbox = new VBox(new Text(text), button);
    vbox.setMinSize(300,300);
	vbox.setStyle("   -fx-padding: 15; -fx-spacing: 10;" );
    dialogPane.setScene(new Scene(vbox));
    dialogPane.showAndWait();
	}};
	Platform.runLater(runnable);
	}
		public void showOptionPane(String text, String buttonText, ImageView  imageView){
			Runnable  runnable=	new Runnable(){
				@Override 
				public void run(){
		Stage dialogPane = new Stage();
	    dialogPane.initModality(Modality.APPLICATION_MODAL);
	    Button button= new Button(buttonText);
	    button.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
	        public void handle( ActionEvent k ) {
				dialogPane.hide();
	        }
	    });
	    VBox vbox = new VBox(new Text(text), button, imageView);
	    vbox.setMinSize(300,300);
		vbox.setStyle("   -fx-padding: 15; -fx-spacing: 10;" );
	    vbox.setAlignment(Pos.CENTER);
	    dialogPane.setScene(new Scene(vbox));
	    dialogPane.showAndWait();
				}};
				Platform.runLater(runnable);
		}
		public int[] showInstrumentOptionPane(String text, String buttonText){
		Stage dialogPane = new Stage();
	    dialogPane.initModality(Modality.APPLICATION_MODAL);
	    Button button= new Button(buttonText);
	    button.setOnAction(new EventHandler<ActionEvent>() { 
			@Override
	        public void handle( ActionEvent k ) {
			 number[0]= Integer.parseInt(stringsField.getText());

			
			stringsField.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, 
			        String newValue) {
			        if (!newValue.matches("\\d*")) {
			            stringsField.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			dialogPane.hide();
	        }
	    });
	    HBox stringsBox= new HBox();
	    HBox fretsBox= new HBox();
	    Label stringLabel= new Label ("Enter Number of Strings");
	    stringsField= new TextField();
	    stringsField.setPrefSize(100, 50);
	    stringsField.setPrefSize(100, 50);
	    stringsBox.getChildren().add(stringLabel);
	    stringsBox.getChildren().add(stringsField);
	    VBox vbox = new VBox(new Text(text), stringsBox,  fretsBox, button);
	    vbox.setMinSize(300,300);
		vbox.setStyle("   -fx-padding: 15; -fx-spacing: 10;" );
	    vbox.setAlignment(Pos.CENTER);
	    dialogPane.setScene(new Scene(vbox));
	    dialogPane.showAndWait();
				return number;
		}
		public int showOptionPane(String text, String buttonText, String button2Text, ImageView imageView){
			Runnable  runnable=	new Runnable(){
				@Override 
				public void run(){
			Stage dialogPane = new Stage();
		    dialogPane.initModality(Modality.APPLICATION_MODAL);
		    Button button = new Button(buttonText);
		    Button button2 = new Button(button2Text);
		    button.setOnAction(new EventHandler<ActionEvent>() { 
				@Override
		        public void handle( ActionEvent k ) {
					option=1;
					dialogPane.hide();
		        }
		    });
		    button2.setOnAction(new EventHandler<ActionEvent>() { 
				@Override
		        public void handle( ActionEvent k ) {
					option=2;
					dialogPane.hide();
		        }
		    });
		    VBox vbox = new VBox(new Text(text), button, button2, imageView);
		    vbox.setAlignment(Pos.CENTER);
		    vbox.setMinSize(300,300);
			vbox.setStyle("   -fx-padding: 15; -fx-spacing: 10;" );
		    dialogPane.setScene(new Scene(vbox));
		    dialogPane.showAndWait();
				}};
				Platform.runLater(runnable);
		    return option;
			}
		public int showOptionPane(String text, ArrayList<String> buttonText,  ImageView imageView){
			Runnable  runnable=	new Runnable(){
				@Override 
				public void run(){
			Stage dialogPane = new Stage();
			 VBox vbox = new VBox();
			 Label label= new Label (text);
			 vbox.getChildren().add(label);
				 vbox.getChildren().add(imageView);
		    dialogPane.initModality(Modality.APPLICATION_MODAL);
		    int size=buttonText.size();
		    for ( count=0; count<size; count++){
		    Button button = new Button(buttonText.get(count));
		    button.setOnAction(new EventHandler<ActionEvent>() { 
				@Override
		        public void handle( ActionEvent k ) {
					option=count;
					dialogPane.hide();
		        }
		    });
		    vbox.getChildren().add(button);
		    }
		    vbox.setAlignment(Pos.CENTER);
		    vbox.setMinSize(300,300);
			vbox.setStyle("   -fx-padding: 15; -fx-spacing: 10;" );
		    dialogPane.setScene(new Scene(vbox));
		    dialogPane.showAndWait();
				}};
				Platform.runLater(runnable);
		    return option;
			}
		public int showOptionPane(String text, String buttonText, String button2Text){
			Runnable  runnable=	new Runnable(){
				@Override 
				public void run(){
			Stage dialogPane = new Stage();
		    dialogPane.initModality(Modality.APPLICATION_MODAL);
		    Button button = new Button(buttonText);
		    Button button2 = new Button(button2Text);
		    button.setOnAction(new EventHandler<ActionEvent>() { 
				@Override
		        public void handle( ActionEvent k ) {
					option=1;
					dialogPane.hide();
		        }
		    });
		    button2.setOnAction(new EventHandler<ActionEvent>() { 
				@Override
		        public void handle( ActionEvent k ) {
					option=2;
					dialogPane.hide();
		        }
		    });
		    VBox vbox = new VBox(new Text(text), button, button2);
		    vbox.setAlignment(Pos.CENTER);
		    vbox.setMinSize(300,300);
			vbox.setStyle("   -fx-padding: 15; -fx-spacing: 10;" );
		    dialogPane.setScene(new Scene(vbox));
		    dialogPane.showAndWait();
				}};
				Platform.runLater(runnable);
		    return option;
			}
}
