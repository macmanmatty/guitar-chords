import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
public class OctaveBox extends ChoiceBox<Number> {
	int number;
	ArrayList<Integer> numbers=  new ArrayList<Integer>();
	OctaveBox(int number){
		this.number=number;
		for (int count=0; count<12; count++) {
			numbers.add(count);	
		}
		this.getItems().addAll(FXCollections.observableArrayList(numbers));
	}
	public int getNumber() {
		return number;
	}
}
