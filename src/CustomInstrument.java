public class CustomInstrument extends Instrument {
	CustomInstrument(int strings,  boolean sharp){
	this.strings=strings;
	for (int count=0; count<strings; count++) {
		tuning.add(new InstrumentString(new Note(4,1, "C",4,0),22,0, sharp, 4));
			}
	name="Custom Instrument";

	}
	
}

