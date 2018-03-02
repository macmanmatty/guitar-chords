public class Banjo extends Instrument {
	Banjo(boolean sharp){
		
		tuning.add(new InstrumentString(new Note(11,4, "G",1,0),24,5, sharp, 5));
		tuning.add(new InstrumentString(new Note(6,3, "D",4,0),24,1, sharp, 4));
		tuning.add(new InstrumentString(new Note(11,3, "G",2,0),24,1, sharp, 3));
		tuning.add(new InstrumentString(new Note(3,3, "B",2,0),24,1, sharp, 2));
		tuning.add(new InstrumentString(new Note(6,4, "D",1,0),24,1, sharp, 1));
		strings=tuning.size();
		frets=24;
		name="Banjo";
		
	}
}
