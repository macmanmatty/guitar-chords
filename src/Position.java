public class Position implements Comparable<Position> {
	int x; // the string the note is on 
	int y; // the fret the note is on
	Note note; // the note
	Position(int x, int y, Note note){
	this.x=x;
	this.y=y;
	this.note=note;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Note getNote() {
		return note;
	}
	@Override
	public int compareTo(Position position) {
		if (y>position.getY()){
			return 1;
		}
		else  if (y<position.getY()){
			return -1;
		}
		else {
			return 0;
		}
	}
}	
