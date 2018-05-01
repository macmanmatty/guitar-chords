
public class EightStringGuitar extends Instrument {
	
	
	EightStringGuitar(boolean sharp){
	

		tuning.add(new InstrumentString( new Note(8,2, "E",6,0),22,1, sharp, 6));
		tuning.add(new InstrumentString(new Note(1,2, "A",5,0),22,1, sharp, 5));
		tuning.add(new InstrumentString(new Note(6,3, "D",4,0),22,1, sharp, 4));
		tuning.add(new InstrumentString(new Note(11,3, "G",2,0),22,1, sharp, 3));
		tuning.add(new InstrumentString(new Note(3,3, "B",2,0),22,1, sharp, 2));
		tuning.add(new InstrumentString(new Note(8,4, "E",1,0),22,1, sharp, 1));
		strings=tuning.size();
		frets=28;
		name="Guitar";
		
		




		

		
		
	}

}
