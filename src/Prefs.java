import java.util.ArrayList;

public class Prefs {
	boolean sharp;
	ArrayList<Note> tuning= new ArrayList<Note>();
	
	Prefs(){}
	
	
	public void addString(Note note){	
	tuning.add(note);			
	}
	
	public void changeStringNote(int stringNumber, Note note){	
		tuning.add(stringNumber, note);			
		}
public void setSharp( Boolean sharp){		
		this.sharp=sharp;
		
	}
	
	

}
