import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
public class Chord implements Comparable<Chord> {
	ArrayList<Position> positions= new ArrayList<Position>(); // finger positions for the chord
	int lowestFret; // the lowest fret used to make  the chord
	int unhitStrings; // number of unhit strings
	int lowestNoteNumber; // lowest note in the chord
	String name;  // the chord name
	int note;// the root chord note
	
	
	Chord(ArrayList<Position> positions, int lowestFret, int lowestNoteNumber, int unhitStrings, String name, int note ){
		this.positions=positions;
		this.lowestFret=lowestFret;
		this.lowestNoteNumber=lowestNoteNumber;
		this.unhitStrings=unhitStrings;
		this.name=name;
		this.note=note;
		
	}
	@Override
	public int compareTo(Chord chord) { // method for comparing chords  overriden use for colections.sort() call
	int size=positions.size();
	
		ArrayList<Position> chord2Positions=chord.getPositions();
		if (size>chord2Positions.size() ) {
			return -1;
		}
		if (size<chord2Positions.size() ) {
			return 1;
		}
		for (int count=0; count<size; count++) {
			int y2=positions.get(count).getY();
			 int y1=chord2Positions.get(count).getY();
			 if (y1!=y2) {
				 return y2-y1;
			 }
	}
			return 0;
	}
			public int getTotalNotes(){
		int size=positions.size();
		int noteTotal=0;
		for (int count=0; count<size; count++) {
			noteTotal=positions.get(count).getY()+positions.get(count).getX();
		}
		return noteTotal;
	}
	public boolean equals(Chord chord) { // method for comparing chords to see if all the notes and positions on the fretboard  match 
		int size=positions.size();
		ArrayList<Position> chord2Positions=chord.getPositions();
		if (size!=chord2Positions.size() ) {
			return false;
		}
		 int matchingNotes=0;
		for (int count=0; count<size; count++) {
			int x2=positions.get(count).getX();
			int y2=positions.get(count).getY();
			int  x1=chord2Positions.get(count).getX();
			 int y1=chord2Positions.get(count).getY();
			 if (x1==x2 &&y1==y2 ) {
				 matchingNotes++;
			 }
	}
		if (matchingNotes==size) {
			return true;
		}
		else {
			return false;
		}
	}
	public ArrayList<Position> getPositions() {
		return positions;
	}
	public int getLowestFret() {
		return lowestFret;
	}
	public void setLowestFret(int lowestFret) {
		this.lowestFret = lowestFret;
	}
	public int getUnhitStrings() {
		return unhitStrings;
	}
	public int getLowestNoteNumber() {
		return lowestNoteNumber;
	}
	
	public void changePosition(int string){
		
		
		
		
	}
	
	
	
}
