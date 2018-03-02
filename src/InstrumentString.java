import java.util.ArrayList;
public class InstrumentString {
	int frets;
	int startFret;
	Note note;
	
	int stringNumber;
	NoteConversion conversion= new NoteConversion(); // calls for converting notes to numbers and numbers to lettered notes
	ArrayList<Note> notes  = new ArrayList<Note>();
	InstrumentString( Note note, int frets, int startFret, boolean sharp, int stringNumber){
		this.frets=frets+1;
		this.note=note;
		this.stringNumber=stringNumber;
		this.startFret=startFret;
		
		for   (int count=0 ; count<frets; count++) {
int noteNumber=note.getNoteNumber();
int octave=note.getOctave();
			notes.add( new Note(noteNumber, octave,conversion.numberToNote(noteNumber, sharp), stringNumber, count));
			noteNumber++;
			if (noteNumber>12) {
				noteNumber=1;
			}
			if (noteNumber==4) {
				octave++;
			}
		}
	}
	
	
	


public Note getStringNote(){
	return note;
	
	
}

public int getFrets(){
	return frets;
	
}


public void setStringNote(Note note) {
	
	this.note=note;
	
}



public int getStartFret() {
	return startFret;
}



public void setStartFret(int startFret) {
	this.startFret = startFret;
}



public int getStringNumber() {
	return stringNumber;
}



public void setStringNumber(int stringNumber) {
	this.stringNumber = stringNumber;
}



public void setFrets(int frets) {
	this.frets = frets+1;
}

public ArrayList<Note> getNotes(){
	
	return notes;
	
}

}
