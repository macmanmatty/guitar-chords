
public class Bass extends Instrument {
	boolean sharp;
	
	
	Bass(boolean sharp){
		
		tuning.add(new InstrumentString(new Note(8,1, "E",4,0),22,1, sharp, 4));
		tuning.add(new InstrumentString(new Note(1,1, "A",2,0),22,1, sharp, 3));
		tuning.add(new InstrumentString(new Note(6,2, "D",2,0),22,1, sharp, 2));
		tuning.add(new InstrumentString(new Note(11,2, "G",1,0),22,1, sharp, 1));
		strings=tuning.size();
		frets=24;
		name="Bass";
		




		

		
		
	}

}
