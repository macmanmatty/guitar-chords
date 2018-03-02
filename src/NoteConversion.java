public class NoteConversion {
	NoteConversion(){}
	public String numberToNote(int number, boolean sharp) { // CONVERTS A	number to a musical note strings
		String note="";
		switch (number) {
		case 1:{
			note="A";
			break;
		}
		case 2:{
			if (sharp==true) {
				note="A#";
				break;	
			}
			note="Bb";
			break;
			}	
		case 3:{
			note="B";
			break;
		}
		case 4:{
			note="C";
			break;
		}
		case 5:{
			if (sharp==true) {
				note="C#";
				break;	
			}
			note="Db";
			break;
			}	
		case 6:{
			note="D";
			break;
		}
		case 7:{
			if (sharp==true) {
				note="D#";
				break;	
			}
			note="Eb";
			break;
		}
		case 8:{
			note="E";
			break;
		}
		case 9:{
			note="F";
			break;
		}
		case 10:{
			if (sharp==true) {
				note="F#";
				break;	
			}
			note="Gb";
			break;
		}
		case 11:{
			note="G";
			break;
		}
		case 12:{
			if (sharp==true) {
				note="G#";
				break;	
			}
			note="Ab";
			break;
		}
		}
		return note;
		}
	public Integer noteToNumber(String note) { //takes a given string note and turns it into a number
		int number=0; 
		switch (note) {
		case "A":{
			number=1;
			break;
		}
		case "a":{
			number=1;
			break;
		}	
		case "Bb":{
			number=2;
			break;
		}		
		case "bb":{
			number=2;
			break;
		}		
		case "BB":{
			number=2;
			break;
		}	
		case "A#":{
			number=2;
			break;
		}
		case "a#":{
			number=2;
			break;
		}	
		case "B":{
			number=3;
			break;
		}
		case "b":{
			number=3;
			break;
		}
		case "C":{
			number=4;
			break;
		}
		case "c":{
			number=4;
			break;
		}
		case "Db":{
			number=5;
			break;
		}		
		case "db":{
			number=5;
			break;
		}		
		case "DB":{
			number=5;
			break;
		}	
		case "C#":{
			number=5;
			break;
		}
		case "c#":{
			number=5;
			break;
		}	
		case "D":{
			number=6;
			break;
		}
		case "d":{
			number=6;
			break;
		}
		case "Eb":{
			number=7;
			break;
		}		
		case "eb":{
			number=7;
			break;
		}		
		case "EB":{
			number=7;
			break;
		}	
		case "D#":{
			number=7;
			break;
		}
		case "d#":{
			number=7;
			break;
		}	
		case "E":{
			number=8;
			break;
		}
		case "e":{
			number=8;
			break;
		}
		case "f":{
			number=9;
			break;
		}
		case "F":{
			number=9;
			break;
		}
		case "gb":{
			number=10;
			break;
		}		
		case "Gb":{
			number=10;
			break;
		}		
		case "GB":{
			number=10;
			break;
		}	
		case "F#":{
			number=10;
			break;
		}
		case "f#":{
			number=10;
			break;
		}	
		case "G":{
			number=11;
			break;
		}
		case "g":{
			number=11;
			break;
		}
		case "ab":{
			number=12;
			break;
		}		
		case "Ab":{
			number=12;
			break;
		}		
		case "AB":{
			number=12;
			break;
		}	
		case "G#":{
			number=12;
			break;
		}
		case "g#":{
			number=12;
			break;
		}	
		}
		return number;
	}
}
