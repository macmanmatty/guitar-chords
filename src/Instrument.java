import java.util.ArrayList;
public abstract class Instrument {
	ArrayList <InstrumentString> tuning= new ArrayList<InstrumentString>();
	int strings;
	int frets;
	String name;
	
	public ArrayList<InstrumentString> getTuning(){
		return tuning;
	}
	public void setTuning(ArrayList<InstrumentString> notes){
	tuning=notes;
	}
	public void addString(InstrumentString note){
		tuning.add(note);
		strings=tuning.size();
	}
	public void changeString(Note note, int number){
		tuning.get(number).setStringNote(note);
	}
	public void changeStringNote(int note, int number, boolean sharp){
		tuning.get(number).getStringNote().setNote(note, sharp);
	}
	public void changeStringOctave(int octave, int number){
		tuning.get(number).getStringNote().setOctave(octave);
	}
	public Note getStringTuning(int number ){
		return tuning.get(number).getStringNote();
	}
	public int getFrets() {
		// TODO Auto-generated method stub
		return frets;
	}
	public void setFrets(int frets) {
		this.frets=frets;
	}
	public int getNumberOfStrings(){
		return strings;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
