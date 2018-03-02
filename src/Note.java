public  class Note {
	int note;
	int octave;
	int string; 
	int fret;
	String symbole;
	Note( int number,  int octave, String symbole, int string, int fret){
		this.string=string;
		this.fret=fret;
		this.note=number;
		this.octave=octave;	
		this.symbole=symbole;
	}
	public int getNoteNumber(){
		return note;
	}
	public int getOctave(){
		return octave;
	}
	public String getNote(){
		return symbole;
	}
	public void setNote(int note, boolean sharp) {
		this.note = note;
		symbole=new NoteConversion().numberToNote(note, sharp);
	}
	public void setOctave(int octave) {
		this.octave = octave;
	}
}
