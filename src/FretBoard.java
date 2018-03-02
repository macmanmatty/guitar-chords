import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class FretBoard {
	int strings; // number of strings
	int maxGaps;
	boolean sharp; // are notes expressed as sharps or flats if true sharps if false flats
	Stage stage;
	ChoiceBox<String> modiferBox; // choice box for chord modifer name
CheckBox sharpBox;
	NoteBox bassBox;
	NoteBox noteBox;// choice box for the root note of the chord
	NoteBox scaleNoteBox;
	HBox scaleHBox;
	HBox scaleHeader;
	Button settingsButton;
	Settings settings;
	ChoiceBox<String> scaleBox; // choice box for chord modifer name
CheckBox showNotes;
	Button findChord;
	Button showScales;
	Button showArpgs;
	int arpgNoteNumber;
	boolean inversions;
	CheckBox allowInversions;
	Button about;
	HBox header;
	HBox menuBox;
	boolean root;
	int maxFrets;
	int start;
	boolean showFullInstrumentBoard;
	String modifer;
	// chord modifer string
	int bassNoteNumber; // number for the bass note of the chord
	boolean showChordNotes; // whether or not show the notes of the chord in the chord image
	String scale; // the name of the scale
	int scaleNote; // the root note fo the scale
	int rootNoteNumber; // number of the root note
	ArrayList<InstrumentString> tuning= new ArrayList<InstrumentString>(); // the notes of the fret board
	int frets; // number of frets
	Instrument instrument; // the instrument being played
	NoteConversion conversion= new NoteConversion(); // calls for converting notes to numbers and numbers to lettered notes
	int maxFretDistance; // how many frets should the chords note be played
	ArrayList<ArrayList<Note>> notes= new ArrayList< ArrayList<Note>>(); // the notes of the instrument on each string at each fret as 2-d array
	private boolean rootNotInChord;
	FretBoard(Instrument instrument, boolean sharp, int maxFretDistance, boolean showChordNotes, Stage stage){
		this.stage=stage;
		this.sharp=sharp;
		this.showChordNotes=showChordNotes;
	 tuning=instrument.getTuning();
	frets=instrument.getFrets();
	this.maxFretDistance=maxFretDistance;
	int minFrets=1;
	this.instrument=instrument;
		 strings=tuning.size();
		 for (int count=0; count<strings; count++) {
			 maxFrets=tuning.get(count).getFrets();
			 if ( tuning.get(count).getFrets()>maxFrets) {
				 maxFrets=tuning.get(count).getFrets();
			 }
			 if ( tuning.get(count).getStartFret()<minFrets) {
				 minFrets=tuning.get(count).getStartFret();
			 }
		 }
		 if(minFrets<1) {
			 for (int count=0; count<strings; count++) {
				 tuning.get(count).setStartFret(tuning.get(count).getStartFret()-minFrets+1);
			 }
		 }
		 instrument.setFrets(maxFrets+2);
		 for (int count=0; count<strings;  count++) {
			  int note=tuning.get(count).getStringNote().getNoteNumber();	
			  int octave=tuning.get(count).getStringNote().getOctave();
			 notes.add(new ArrayList<Note>());	
			 int frets=tuning.get(count).getFrets();
			for   (int counter=0 ; counter<frets; counter++) {
				notes.get(count).add( new Note(note, octave,conversion.numberToNote(note, sharp), count, counter));
				note++;
				if (note>12) {
					note=1;
				}
				if (note==4) {
					octave++;
				}
			}
		 }
		 modiferBox=new ChoiceBox<String>();
		 noteBox=new NoteBox(0, sharp);
		 bassBox=new  NoteBox(0, sharp);
		 scaleNoteBox= new NoteBox(0, sharp);
		 bassBox.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number unused, Number newModifer) {
					 String rootNote=bassBox.getItems().get((int) newModifer);
					 bassNoteNumber=conversion.noteToNumber(rootNote);
				}
			    }); 
		 scaleNoteBox.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number unused, Number newModifer) {
					 String rootNote=scaleNoteBox.getItems().get((int) newModifer);
					 scaleNote=conversion.noteToNumber(rootNote);
				}
			    }); 
		ArrayList<String> scaleNames= new ArrayList<String>();
		scaleNames.add("Major");
		scaleNames.add("Minor");
		 scaleBox=new ChoiceBox<String>(FXCollections.observableArrayList(scaleNames));
		 scaleBox.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number unused, Number newModifer) {
					scale=modiferBox.getItems().get((int) newModifer);
				}
			    }); 
ArrayList<String> chordNames= new ArrayList<String>();
chordNames.add("Major");
chordNames.add("Minor");
chordNames.add("5th");
chordNames.add("3rd");
chordNames.add("7th");
chordNames.add("Major 7th");
chordNames.add("Minor 7th");
chordNames.add("Diminished");
chordNames.add("6th");
chordNames.add("Minor 6th");
chordNames.add("add9");
chordNames.add("Minor add9");
chordNames.add("sus2");
chordNames.add("add4");
chordNames.add("Minor add4");
chordNames.add("sus4");
chordNames.add("9th");
chordNames.add("6th add4");
chordNames.add("11th");
chordNames.add("Minor 11th");
chordNames.add("6th / 9th");
chordNames.add("13th");
chordNames.add(" minor 13th");
chordNames.add("Major 7th add9 add4");
chordNames.add("add9 add4");
chordNames.add("Augmented 5th");
chordNames.add("Augmented 7th");
		 modiferBox=new ChoiceBox<String>(FXCollections.observableArrayList(chordNames));
		 modiferBox.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number unused, Number newModifer) {
					modifer=modiferBox.getItems().get((int) newModifer);
				}
			    }); 
		 noteBox.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number unused, Number newModifer) {
					 String rootNote=noteBox.getItems().get((int) newModifer);
					 rootNoteNumber=conversion.noteToNumber(rootNote);
					Integer number=(Integer)newModifer;
						bassBox.getSelectionModel().select(number);
				}
			    }); 
		 noteBox.getSelectionModel().select(0);
		 modiferBox.getSelectionModel().select(0);
		bassBox.getSelectionModel().select(0);
		 scaleBox.getSelectionModel().select(0);
		 scaleNoteBox.getSelectionModel().select(0);
settingsButton= new Button("Settings");
		 findChord= new Button("Find Chord");
		 showScales=new Button("Show Scale");
		 showArpgs= new Button("Show Arpeggos");
		 about= new Button ("About");
		 showScales.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					makeScale();
				}});
		 findChord.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					findChord();
				}});
		 settingsButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					changeSettings();
				}});
		 showArpgs.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					showArpgs();
				}});
		 about.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String text= "Guitar Chords version 1.0 By Jesse Matty";
					new OptionPane().showOptionPane(text, "ok");
				}});
		 sharpBox=new CheckBox();
			sharpBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			        public void changed(ObservableValue<? extends Boolean> value,
			            Boolean old, Boolean newValue) {
			        	if (newValue==true) {
			        		setSharp(true);
			        		setInstrument(instrument);
			        		showFretBoard();
			        	}
			        	else {
			        		setSharp(false);
			        		setInstrument(instrument);
			        		showFretBoard();
			        	}
			        }
			    });
		 showNotes=new CheckBox();
			showNotes.selectedProperty().addListener(new ChangeListener<Boolean>() {
		        public void changed(ObservableValue<? extends Boolean> value,
		            Boolean old, Boolean newValue) {
		        	if (newValue==true) {
		        		setShowChordNotes(true);
		        	}
		        	else {
		        		setShowChordNotes(false);
		        	}
		        }
		    }); 
			allowInversions=new CheckBox();
			allowInversions.selectedProperty().addListener(new ChangeListener<Boolean>() {
		        public void changed(ObservableValue<? extends Boolean> value,
		            Boolean old, Boolean newValue) {
		        	if (newValue==true) {
		        		setInversions(true);
		        	}
		        	else {
		        		setInversions(false);
		        	}
		        }
		    }); 
			Label showNotesLabel= new Label("Display Notes in Chords");
		 Label label= new Label("Select the chord root note and chord name using the drop down boxes and then click find chord to see the chord formations");
		 header= new HBox(label);
			Label noteLabel= new Label("Root Note: ");
			Label chordLabel= new Label("Chord Name: ");
			Label bassNoteLabel= new Label("Bass Note:");
			Label scaleLabel= new Label("Select the scale to be displayed");
			Label scaleNoteLabel= new Label(" Scale Root Note: ");
			Label scaleNameLabel= new Label(" Scale Name ");
			Label inversionsLabel= new Label("Allow Inversions");
			Label showNotesAsSharp= new Label("Show Notes as Sharp");
			scaleHeader= new HBox(scaleLabel);
		 menuBox= new HBox(noteLabel, noteBox,  chordLabel, modiferBox,  bassNoteLabel, bassBox, inversionsLabel, allowInversions,  showNotes, showNotesLabel, sharpBox, showNotesAsSharp,   findChord, showArpgs ,  settingsButton, about);
		 scaleHBox= new HBox(scaleNoteLabel, scaleNoteBox, scaleNameLabel, scaleBox, showScales);
	}
	private void changeSettings(){
		if (settings==null) {
 settings=new Settings(stage, this);
 settings.setInstrument(instrument);
		}
		settings.showSettingsWindow();
	}
private void 	findChord() {
	rootNotInChord=false;
	
	ArrayList<Integer> notes =makeChord(rootNoteNumber);
	if(notes.size()>strings) {
	 String text="The selected chord contains more notes than you have strings and is unplayable on your instrument";
	 new OptionPane().showOptionPane(text, "ok");
		return;
	}
	ArrayList<Chord> positions=	makeChordFingerings(notes);
	displayChordFingerings(positions);
}
private ArrayList<Integer> 	makeChord(int rootNoteNumber){
	ArrayList<Integer> notes = new  ArrayList<Integer>();
	if (modifer.equalsIgnoreCase("major")) {
		notes.add(0);
		notes.add(4);
		notes.add(7);
	}
	else if (modifer.equalsIgnoreCase("minor")) {
		notes.add(0);
		notes.add(3);
		notes.add(7);
	}
	else if (modifer.equalsIgnoreCase("diminished")) {
		notes.add(0);
		notes.add(3);
		notes.add(6);
	}
	else if (modifer.equalsIgnoreCase("5th")) {
		notes.add(0);
		notes.add(7);
	}
	else if (modifer.equalsIgnoreCase("augmented 5th")) {
		notes.add(0);
		notes.add(4);
		notes.add(8);
	}
	else if (modifer.equalsIgnoreCase("3rd")) {
		notes.add(0);
		notes.add(4);
	}
	else if (modifer.equalsIgnoreCase("minor 3rd")) {
		notes.add(0);
		notes.add(3);
	}
	else if (modifer.equalsIgnoreCase("sus2")) {
		notes.add(0);
		notes.add(2);
		notes.add(7);
	}
	else if (modifer.equalsIgnoreCase("add9")) {
		notes.add(0);
		notes.add(2);
		notes.add(4);
		notes.add(7);
	}	
	else if (modifer.equalsIgnoreCase("add4")) {
		notes.add(0);
		notes.add(4);
		notes.add(5);
		notes.add(7);
	}
	else if (modifer.equalsIgnoreCase("minor add4")) {
		notes.add(0);
		notes.add(3);
		notes.add(5);
		notes.add(7);
	}
	else if (modifer.equalsIgnoreCase("minor add9")) {
		notes.add(0);
		notes.add(3);
		notes.add(2);
		notes.add(7);
	}
	else if (modifer.equalsIgnoreCase("6th")) {
		notes.add(0);
		notes.add(4);
		notes.add(7);
		notes.add(9);
	}
	else if (modifer.equalsIgnoreCase("6th add4")) {
		notes.add(0);
		notes.add(4);
		notes.add(5);
		notes.add(7);
		notes.add(9);
	}
	else if (modifer.equalsIgnoreCase("minor 6th")) {
		notes.add(0);
		notes.add(3);
		notes.add(7);
		notes.add(9);
	}	
	else if (modifer.equalsIgnoreCase("sus4")) {
		notes.add(0);
		notes.add(5);
		notes.add(7);
	}	
	else if (modifer.equalsIgnoreCase("7th")) {
		notes.add(0);
		notes.add(4);
		notes.add(7);
		notes.add(10);
	}	
	else if (modifer.equalsIgnoreCase("major 7th")) {
		notes.add(0);
		notes.add(4);
		notes.add(7);
		notes.add(11);
	}
	else if (modifer.equalsIgnoreCase("minor 7th")) {
		notes.add(0);
		notes.add(3);
		notes.add(7);
		notes.add(10);
	}
	else if (modifer.equalsIgnoreCase("major 9th")) {
		notes.add(0);
		notes.add(2);
		notes.add(4);
		notes.add(7);
		notes.add(11);
	}
	else if (modifer.equalsIgnoreCase("9th")) {
		notes.add(0);
		notes.add(2);
		notes.add(4);
		notes.add(7);
		notes.add(10);
	}
	else if (modifer.equalsIgnoreCase("10th")) {
		notes.add(0);
		notes.add(4);
		notes.add(5);
		notes.add(7);
		notes.add(9);
	}
	else 	if (modifer.equalsIgnoreCase("major 10th")) {
		notes.add(0);
		notes.add(4);
		notes.add(5);
		notes.add(7);
		notes.add(10);
	}
	else if (modifer.equalsIgnoreCase("add9 add4")) {
		notes.add(0);
		notes.add(2);
		notes.add(4);
		notes.add(5);
		notes.add(7);
	}
	else if (modifer.equalsIgnoreCase("major 7th add9 add4")) {
		notes.add(0);
		notes.add(2);
		notes.add(4);
		notes.add(5);
		notes.add(7);
		notes.add(11);
	}
	else if (modifer.equalsIgnoreCase("7th add9 add4")) {
		notes.add(0);
		notes.add(2);
		notes.add(4);
		notes.add(5);
		notes.add(7);
		notes.add(10);
	}
	else if (modifer.equalsIgnoreCase("11th")) {
		notes.add(0);
		notes.add(4);
		notes.add(5);
		notes.add(7);
		notes.add(10);
	}
	else if (modifer.equalsIgnoreCase("minor 11th")) {
		notes.add(0);
		notes.add(3);
		notes.add(5);
		notes.add(7);
		notes.add(10);
	}
	else if (modifer.equalsIgnoreCase("13th")) {
		notes.add(0);
		notes.add(2);
		notes.add(4);
		notes.add(5);
		notes.add(9);
		notes.add(7);
		notes.add(10);
	}
	else if (modifer.equalsIgnoreCase("minor 13th")) {
		notes.add(0);
		notes.add(2);
		notes.add(4);
		notes.add(5);
		notes.add(9);
		notes.add(7);
		notes.add(10);
	}
	else if (modifer.equalsIgnoreCase("6th / 9th")) {
		notes.add(0);
		notes.add(2);
		notes.add(4);
		notes.add(5);
		notes.add(7);
	}
	else if (modifer.equalsIgnoreCase("augmented 7th")) {
		notes.add(0);
		notes.add(4);
		notes.add(8);
		notes.add(10);
	}
	int size=notes.size();
	boolean rootNoteInChord=false;
	
	
	
	
	for (int count=0; count<size; count++) {
		 int  newNumber=notes.get(count)+rootNoteNumber;
		 if (newNumber>12){
		newNumber=(notes.get(count)+rootNoteNumber)-12;
		 }
		  notes.set(count,newNumber);
	}
	
	
	for (int count=0; count<size; count++) {
		if (notes.get(count)==this.bassNoteNumber) {
			rootNoteInChord=true;
			break;	
		}	
	}
	if(rootNoteInChord==false) {
		notes.add(this.bassNoteNumber);
		rootNotInChord=true;
		
	}
	
	return notes;
	}
 private void makeScale() {
		ArrayList<Integer> notes = new  ArrayList<Integer>();
	if (scale.equalsIgnoreCase("major")) {
		notes.add(0);
		notes.add(2);
		notes.add(4);
		notes.add(5);
		notes.add(7);
		notes.add(9);
		notes.add(11);
	}
	if (scale.equalsIgnoreCase("minor")) {
			notes.add(0);
			notes.add(2);
			notes.add(3);
			notes.add(5);
			notes.add(7);
			notes.add(8);
			notes.add(10);
		}
	int size=notes.size();
	for (int count=0; count<size; count++) {
		 int  newNumber=notes.get(count)+scaleNote;
		 if (newNumber>12){
		newNumber=(notes.get(count)+scaleNote)-12;
		 }
		  notes.set(count,newNumber);
	}
	showScale(notes);
 }
public void  showArpgs(){
	 ArrayList<Integer> notes=makeChord(rootNoteNumber);
	 showScale(notes);
 }
 private void showScale(ArrayList<Integer> scaleNotes) {
			VBox pane=new VBox();
			pane.getChildren().add(header);
			pane.getChildren().add(menuBox);
			Pane fretBoardImage =new FretBoardImage().showScale(strings, maxFrets, this.notes, scaleNotes, tuning, showFullInstrumentBoard);
			pane.getChildren().add(scaleHeader);
			pane.getChildren().add(scaleHBox);
			pane.getChildren().add(fretBoardImage);
			stage.setScene(new Scene(pane));
			stage.show();
		}
private void displayChordFingerings(ArrayList<Chord> positions) { // displays the chords
	 int size=positions.size();
		HBox gridPane= new HBox();
	 if(size>0) {
	gridPane.setStyle("  -fx-padding: 10; -fx-spacing: 10;");
		 for (int count=0; count<size; count++) {

 if (showChordNotes==true) {
	 gridPane.getChildren().add(new ChordImage().getChordImageWithNotes(positions.get(count),maxFrets, strings, tuning, showFullInstrumentBoard, maxFretDistance*2));
 }
 else {
	 gridPane.getChildren().add(new ChordImage().getChordImage(positions.get(count), maxFrets, strings, tuning, showFullInstrumentBoard, maxFretDistance*2));
 }
		 }
	 }
	VBox pane = new VBox();
		pane.getChildren().add(header);
		pane.getChildren().add(menuBox);
		pane.getChildren().add(scaleHeader);
		pane.getChildren().add(scaleHBox);
		String note=conversion.numberToNote(rootNoteNumber, sharp);
		if (rootNoteNumber!=bassNoteNumber) {
			String note2=conversion.numberToNote(bassNoteNumber, sharp);
			note=note+"/"+note2;
			

			
			
		}
		
		Label chordLabel= new Label(note+ " "+ modifer + " Chords");
		chordLabel.setFont(new Font(30));
        chordLabel.setMaxWidth(Double.MAX_VALUE);
		chordLabel.setAlignment(Pos.CENTER);
		pane.getChildren().add(chordLabel);

		ScrollPane scrollPane=new ScrollPane(gridPane);
				pane.getChildren().add((scrollPane));
		stage.setScene(new Scene(pane));
		stage.show();
}
private ArrayList<Chord> makeChordFingerings(ArrayList<Integer> notes){ // makes the array list of all possible chords sorts them  and then removes the duplicates. 
	ArrayList <ArrayList<Integer>> direction= new ArrayList<ArrayList<Integer>>();
	out:
	  for(int length=0; length<1;  length++) {
		  for( int start=length; start<length+1; start++) {
			  if (length>1) {
				  break out;
			  }
	    ArrayList<Integer> l =  new ArrayList<Integer>();
	    l.add(0);
	    l.add(1);
	    direction =makeCombinations(l, strings);
	  }
	  }
	int directionSize=direction.size();
	 ArrayList<Chord> positions= new ArrayList<Chord> ();
				for (int counter=0; counter<strings ; counter++) {
					int frets=tuning.get(counter).getFrets();
					for (int counter2=0;  counter2<directionSize; counter2++) {
		for (int start=0; start<frets; start++) {
			positions.addAll(makeChord(notes, start, direction.get(counter2)));
		}
		}
		}
				int size=positions.size();
				ArrayList <Chord> sortedChords= new ArrayList<Chord>(); //arraylist to hold the sorted chords
if(size>0) { // sorts the chords if there are any  and removes any that are equal to each other IE the exact same positions on the fret board
		Collections.sort(positions);
		Chord chord2=positions.get(0);
		Chord chord;
		sortedChords.add(chord2);
		for (int count=0; count<size; count++) {
			chord=positions.get(count);
			if (chord.equals(chord2)==false) {
				sortedChords.add(chord);
			}
			chord2=chord;
		}
}
size=sortedChords.size();
for (int count = size-1; count >= 0; count--)
{
   for (int counter = 1; counter<= count; counter++)
   {
      if (sortedChords.get(counter-1).getLowestFret() > sortedChords.get(counter).getLowestFret())
      {
          Chord temp = sortedChords.get(counter-1);
           sortedChords.set(counter-1, sortedChords.get(counter));
           sortedChords.set(counter, temp);
} } } 
		return sortedChords;
	}
private  ArrayList<ArrayList<Integer>> combinations(ArrayList<Integer> list, int maxLength) {
    return combinations(list, maxLength, new ArrayList<Integer>(), new ArrayList<ArrayList<Integer>>());
  }
  private  ArrayList<ArrayList<Integer>> combinations(ArrayList<Integer> list, int length, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> result) {
    if (length == 0) {
      ArrayList<ArrayList<Integer>> newResult =  new ArrayList<>(result);
      newResult.add(current);
      return newResult;
    }
    ArrayList<ArrayList<ArrayList<Integer>>> res3 = new ArrayList<>();
    int size=list.size();
    for (int count=0; count<size; count++ ) {
      ArrayList<Integer> newCurrent = new ArrayList<>(current);
      newCurrent.add(list.get(count));
      res3.add(combinations(list, length - 1, newCurrent, result));
    }
    ArrayList<ArrayList<Integer>> res2 = new ArrayList<>();
  size=res3.size();
    for (int count=0 ; count<size; count++) {
      res2.addAll(res3.get(count));
    }
    return res2;
  }
  private ArrayList< ArrayList <Integer>> makeCombinations(ArrayList<Integer> list, int maxLength) {
    ArrayList<ArrayList<Integer>> combs = combinations(list, maxLength);
    int size=combs.size();
    ArrayList <ArrayList <Integer>> numbers= new ArrayList<>();
    for (int count =0 ; count<size; count++) {
	    int size2=combs.get(count).size();
      for (int counter=0; counter<size2; counter++) {
      numbers.add(combs.get(count));
      }
    }
    return numbers;
  }
private ArrayList<Chord> makeChord(ArrayList<Integer> chordNotes, int start, ArrayList<Integer> up) { // takes an array list of notes as numbers and calculates  chords that can be made using four fingers on the fret board from as given fret position and notes that make up a chord 
	ArrayList<ArrayList<Position>> notePlaces= new ArrayList<ArrayList<Position>>();
	ArrayList<Chord> chords= new ArrayList<Chord>();
	int size=chordNotes.size();
	if(inversions==true) {
		root=true;
	}
	else {
	root=false;
	}
	for (int count=0; count<strings; count++) {
		ArrayList<Position> position=makePosition(count, start,  chordNotes, up.get(count), tuning.get(count).getFrets(),tuning.get(count).getStartFret(), maxFretDistance ); // calculates the positions;
		notePlaces.add(position);
	}
	 int [] sizes= new int [notePlaces.size()];
	 int [] counterArray= new int [notePlaces.size()];
	 int totalCombos=1;
	 for (int count=0; count<strings; count++) { // calculates the total number of chords possible from the positions
		 sizes[count]=notePlaces.get(count).size();
		 totalCombos=totalCombos*notePlaces.get(count).size();

		 
	 }
		 for (int counter=totalCombos; counter>0; counter--) { // makes the chords from the arraylists of possible fret positions  using iteration
			 ArrayList<Position> chordPositions = new ArrayList<Position>();
			 for (int count=0; count<strings; count++) {
			chordPositions.add(notePlaces.get(count).get(counterArray[count]));
			

			}
				

				int unhitStrings=0;
				int allChordNotes=0;
				int fretPlace=0;
				int lowestFret=frets+10;
				int highestFret=0;
				size=chordPositions.size();
				for (int count2=0;  count2<size; count2++) { // caclulates the highest and lowest frets ignoring open and unhit strings un hit strings =-1
					 fretPlace=chordPositions.get(count2).getY();
					 if (fretPlace<lowestFret && fretPlace!=-1 && fretPlace!=0) {
						 lowestFret=fretPlace;
					 }
					if (fretPlace>highestFret && fretPlace!=-1 && fretPlace!=0) {
						 highestFret=fretPlace;
					 }
				}
				if (lowestFret==frets+10) { // lowest fret never changed  set equal to zero as all strings are either unhit or open
					lowestFret=0;
					
				}
				
				
				size=chordNotes.size();
				int size2=chordPositions.size();
				int noteFret=0;
				for (int count3=0; count3<size; count3++) { // checks if all required notes are part of the chord
					in:
					for (int counter4=0; counter4<size2; counter4++ ) {
						if (chordPositions.get(counter4).getNote().getNoteNumber()==chordNotes.get(count3) ) {
							allChordNotes++;
							break in;
						}
					}
				}
				boolean rootNote=false;
				
				if (rootNotInChord==true) {// checks to see if the bass note  is in the chord occurring not as the root only if the bass note  is not equal to a another  note in the chord if it were rootNotinchord would be false;
					int previousNoteNumber=-1;

				for (int count4=0; count4<size2; count4++) {
					int noteNumber2=chordPositions.get(count4).getNote().getNoteNumber();
					
					if (noteNumber2==bassNoteNumber && previousNoteNumber!=-1) {
						rootNote=true;
						
					}
					previousNoteNumber=noteNumber2;
					
				}
				
				}
				
				boolean stringGap=false;
				boolean gap=false;
				boolean sameNotes=false;
				
				int previousNoteFret=-1;
				int previousNoteNumber=-1;
				int noteNumber=0;
				
				for (int counter3=0; counter3<size2; counter3++ ) { // checks for un-hit strings  in the middle of the chord
					noteFret=chordPositions.get(counter3).getY();
					noteNumber=chordPositions.get(counter3).getNote().getNoteNumber();

					if (noteFret==-1 && previousNoteFret!=-1) {
						gap=true;
					}
					if (noteFret!=-1 && previousNoteFret==-1 &&gap==true) {
						stringGap=true;
					}
					
					if (noteNumber==previousNoteNumber && noteFret!=-1 && previousNoteFret!=-1) {
						sameNotes=true;
						
						
					}
					
					
					 previousNoteFret=noteFret;
					 previousNoteNumber=noteNumber;
				}
				if (allChordNotes==chordNotes.size()  && stringGap==false && sameNotes==false && rootNote==false && highestFret-lowestFret<=maxFretDistance-1) {
					chords.add(new Chord(chordPositions, lowestFret, lowestFret, unhitStrings, modifer, bassNoteNumber));
				}
				 for(int counter2 =size-1; counter2>=0; counter2--) {
		            if(counterArray[counter2] + 1 < sizes[counter2]) {
		                counterArray[counter2]++;
		                break;
		            }
		            counterArray[counter2] = 0;
		 }
				

		 }
		return chords;
	 }
private ArrayList<Position> makePosition(int string, int start, ArrayList<Integer> chordNotes, int up, int frets, int startFret, int maxFretDistance) { 
		int size=chordNotes.size();// the size of the array of numbers that make up the chord notes
		startFret--; 
		ArrayList<Position> positions= new ArrayList<Position>();
		out:
			if(up==1) {
		for (int count=start; count<maxFretDistance+start; count++) { // finds all positions that match the chord notes up the neck. 
			if (count>=frets) {
				if (positions.size()==0) {
					positions.add(new Position(string, -1, new Note (-1,-1, "X", -1, -1) ));
					}
				break out;
			}
				int noteNumber=this.notes.get(string).get(count).getNoteNumber();
				for (int counter2=0; counter2<size; counter2++) {
					if (noteNumber==chordNotes.get(counter2)) {
						if ( bassNoteNumber==noteNumber && root==false) { // adds the root note and then allows other notes be added to the root note which is always the lowest note in the chord
							positions.add( new Position(string, count+startFret, this.notes.get(string).get(count)));
							root=true;
							break out;
						}
						else  if ( root==true){// add any note that matches the notes that make up the chord once the root note has been found
					positions.add(new Position(string, count+startFret, this.notes.get(string).get(count)));
						}
						}
					
				}
				 if (count>=frets-1 || count>=maxFretDistance+start) {
						if (positions.size()==0) {
						positions.add(new Position(string, -1, new Note (-1,-1, "X", -1, -1) )); // if no notes are found add empty note
						}
						break out;
					}
			}
			}
			else {//does the same thing as for loop number 1 but going down the neck instead.
				if(start>=frets)	{
					positions.add(new Position(string, -1, new Note (-1,-1, "X", -1, -1) ));
					return  positions;
				}
				out2:
		for (int count=start; count>=start-maxFretDistance; count--) {
			if (count<0) {
				if (positions.size()==0) {
				positions.add(new Position(string, -1, new Note (-1,-1, "X", -1, -1) ));
				}
				break out2;
			}
			int noteNumber=this.notes.get(string).get(count).getNoteNumber();
			for (int counter2=0; counter2<size; counter2++) {
				if (noteNumber==chordNotes.get(counter2)) {
					if ( bassNoteNumber==noteNumber && root==false) {
						positions.add(new Position(string, count+startFret, this.notes.get(string).get(count)));
						root=true;
						break out2;
					}
					else  if ( root==true){
				positions.add(new Position(string, count+startFret, this.notes.get(string).get(count)));
					}
					}
				
			}
			if ( count<=start-maxFretDistance) {
				if(positions.size()==0) {
			positions.add(new Position(string, -1, new Note (-1,-1, "X", -1, -1) ));
				}
			break out2;
			}
		}
			}
		return positions;
			}
public void setInstrument(Instrument instrument){ // sets new instrument and regenerates the fretboard
	notes= new ArrayList<ArrayList<Note>>();
	tuning=new ArrayList<>();
	this.instrument=instrument;
	 tuning=instrument.getTuning();
	frets=instrument.getFrets();
		 strings=tuning.size();
		 int minFrets=1;
		 for (int count=0; count<strings; count++) {
			 maxFrets=tuning.get(count).getFrets();
			 if ( tuning.get(count).getFrets()>maxFrets) {
				 maxFrets=tuning.get(count).getFrets();
			 }
			 if ( tuning.get(count).getStartFret()<minFrets) {
				 minFrets=tuning.get(count).getStartFret();
			 }
		 }
		 if(minFrets<1) {
			 for (int count=0; count<strings; count++) {
				 tuning.get(count).setStartFret(tuning.get(count).getStartFret()-minFrets+1);
			 }
		 }
		 instrument.setFrets(maxFrets+1);
		 for (int count=0; count<strings;  count++) {
			  int note=tuning.get(count).getStringNote().getNoteNumber();	
			  int octave=tuning.get(count).getStringNote().getOctave();
			 notes.add(new ArrayList<Note>());	
			 int frets=tuning.get(count).getFrets();
			for   (int counter=0 ; counter<frets; counter++) {
				notes.get(count).add( new Note(note, octave,conversion.numberToNote(note, sharp), count, counter));
				note++;
				if (note>12) {
					note=1;
				}
				if (note==4) {
					octave++;
				}
			}
		 }
}
public void showFretBoard() {
	VBox pane=new VBox();
	pane.getChildren().add(header);
	pane.getChildren().add(menuBox);
	Pane fretBoardImage =new FretBoardImage().drawFretBoard(strings, maxFrets, notes, instrument.getTuning());
	pane.getChildren().add(scaleHeader);
	pane.getChildren().add(scaleHBox);
	pane.getChildren().add(fretBoardImage);
	stage.setScene(new Scene(pane));
	stage.show();
}
public void setSharp(boolean sharp) {
	this.sharp = sharp;
}
public void setShowChordNotes(boolean showChordNotes) {
	this.showChordNotes = showChordNotes;
}
public boolean getFullInstrumentBoard() {
	return showFullInstrumentBoard;
}
public void setFullInstrumentBoard(boolean showFullInstrumentBoard) {
	this.showFullInstrumentBoard = showFullInstrumentBoard;
}
public void setInversions(boolean inversions) {
	this.inversions = inversions;
}
public int getMaxFretDistance() {
	return maxFretDistance;
}
public void setMaxFretDistance(int maxFretDistance) {
	this.maxFretDistance = maxFretDistance;
}
}
