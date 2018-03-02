import javafx.scene.control.TextField;

public class NumberedTextField  extends TextField {
	
	int number;
	
	NumberedTextField(int number){
		this.number=number;
		
	}
	
	public int getNumber(){
		return number;
		
	}
	

}
