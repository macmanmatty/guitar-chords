import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
public class NoteBox extends ChoiceBox<String> {
	boolean sharp;
	int number;
	ArrayList<String> notes=  new ArrayList<String>();
	NoteConversion conversion= new NoteConversion();
	NoteBox(int number, boolean sharp){
		this.sharp=sharp;
		this.number=number;
		for (int count=1; count<13; count++) {
			notes.add(conversion.numberToNote(count, sharp));			
		}
		this.getItems().addAll(FXCollections.observableArrayList(notes));
	}
	public int getNumber() {
		return number;
	}
	public void setSharp(boolean sharp) {
		this.sharp = sharp;
	}
	
	
}
