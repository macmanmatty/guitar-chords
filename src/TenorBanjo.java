
public class TenorBanjo extends Instrument {
	
	TenorBanjo(boolean sharp){
		tuning.add(new InstrumentString(new Note(4,3, "C",4,0),22,0, sharp, 4));
		tuning.add(new InstrumentString(new Note(11,3, "G",2,0),22,0, sharp, 3));
		tuning.add(new InstrumentString(new Note(6,4, "D",2,0),22,0, sharp, 2));
		tuning.add(new InstrumentString(new Note(1,4, "A",1,0),22,0, sharp, 1));
		strings=tuning.size();
		frets=24;
		name="Tenor Banjo";
		
	}
	

}
