public class Mandolin extends Instrument {
	boolean sharp;
	
	Mandolin(boolean sharp){
		tuning.add(new InstrumentString(new Note(11,3, "G",4,0),22,1, sharp, 4));
		tuning.add(new InstrumentString(new Note(6,4, "D",2,0),22,1, sharp, 3));
		tuning.add(new InstrumentString(new Note(1,4, "A",2,0),22,1, sharp, 2));
		tuning.add(new InstrumentString(new Note(8,5, "E",1,0),22,1, sharp, 1));
		strings=tuning.size();
		frets=24;
		name="Mandolin";
		
	}
}
