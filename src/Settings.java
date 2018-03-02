import java.util.ArrayList;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Settings {
	Stage stage;
	OctaveBox octaveBox;
	NoteBox noteBox;
	HBox topBox= new HBox();
	HBox bottomBox= new HBox();
	Button exit; 
	FretBoard fretBoard;
	CheckBox sharpBox;
	HBox buttonHeader;
	ChoiceBox<Instrument> instrumentBox;
	ChoiceBox<Integer> maxFretBox;
	
	VBox tuningBox;
	ArrayList<NumberedTextField> startFret= new ArrayList<>();
	ArrayList<NumberedTextField> stringFrets= new ArrayList<>();
CheckBox harpString;

	Instrument instrument;
	Button newInstrument;
	Button applyChanges;
	NoteConversion conversion= new NoteConversion();
	boolean sharp;
ArrayList<NoteBox> noteBoxes= new ArrayList<NoteBox>();
ArrayList<OctaveBox> octaveBoxes= new ArrayList<OctaveBox>();
	Settings(Stage stage, FretBoard fretBoard){
		this.fretBoard=fretBoard;
		this.stage=stage;
		
		ArrayList<Instrument> instruments=  new ArrayList<Instrument>();
instruments.add( new Guitar(sharp));
instruments.add(new Bass(sharp));
instruments.add(new Banjo(sharp));
instruments.add(new Mandolin(sharp));
instruments.add(new TenorBanjo(sharp));

		instrumentBox= new ChoiceBox<Instrument>(FXCollections.observableArrayList(instruments));
		instrumentBox.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldInstrument, Number newInstrument) {
				chageInstrument(instrumentBox.getItems().get((int) newInstrument));
				
				
			}
		    });
		newInstrument=new Button("Add Instrument");
		newInstrument.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addInstrument();	
			}});
		exit= new Button("exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.hide();
				fretBoard.showFretBoard();
			}});
		applyChanges= new Button("Save Changes");
		applyChanges.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				int strings=instrument.getTuning().size();
				
				for (int count=0; count<strings; count++) {
					String text=stringFrets.get(count).getText();
					int frets=Integer.parseInt(text);
					instrument.getTuning().get(count).setFrets(frets);
					text=startFret.get(count).getText();
					frets=Integer.parseInt(text);
					instrument.getTuning().get(count).setStartFret(frets);

					
				}
				
				fretBoard.setInstrument(instrument);
	
			}});
		sharpBox=new CheckBox();
		sharpBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
		        public void changed(ObservableValue<? extends Boolean> value,
		            Boolean old, Boolean newValue) {
		        	if (newValue==true) {
		        		fretBoard.setSharp(true);
		        		setSharp(true);
		        		
		        		fretBoard.setInstrument(instrument);
		        		showSettingsWindow();
		        		
		        		
		        	}
		        	else {
		        		setSharp(false);
		        		
		        		fretBoard.setSharp(false);
		        		fretBoard.setInstrument(instrument);
		        		showSettingsWindow();


		        	}
		        }
		    });
		ArrayList<Integer> numbers= new ArrayList<Integer>();
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		numbers.add(6);
		numbers.add(7);


		
		maxFretBox=new ChoiceBox<Integer>(FXCollections.observableArrayList(numbers));
		maxFretBox.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number unused, Number newValue) {
				fretBoard.setMaxFretDistance(maxFretBox.getItems().get((int) newValue));
				
			}
		    });
		 maxFretBox.getSelectionModel().select(1);

		
		Label sharpLabel= new Label("Show Notes as Sharps");
		Label maxFretsLabel= new Label("Max Fret Distance Between Notes");
		
		Label instrumentLabel= new Label("Select Instrument");
		buttonHeader= new HBox(sharpLabel, sharpBox, maxFretsLabel, maxFretBox, instrumentLabel, instrumentBox, newInstrument, exit, applyChanges );
		 instrumentBox.getSelectionModel().select(0);

	}
	
	public void showSettingsWindow() {
		VBox mainBox= new VBox();
		mainBox.getChildren().add(buttonHeader);
		tuningBox= new VBox();
		int strings=instrument.getNumberOfStrings();
		int number=strings;
		for (int count=0; count<strings; count++)	{		
			addString(count, number);
			number--;
			
		}
		mainBox.getChildren().add(tuningBox);
		stage.setScene(new Scene(mainBox));
	}
	public void chageInstrument(Instrument instrument){ // changes the current instrument to the selceted one
	setInstrument(instrument);
	
	
	showSettingsWindow();
	}
	public void addInstrument(){ //creates and option pane for a new instrument  creation
		String text="enter the number of frets and strings for your new instrument then change the tuning";
		int [] values = new OptionPane().showInstrumentOptionPane(text, "done");
		Instrument custom=new CustomInstrument(values[0], sharp);
		instrumentBox.getItems().add(custom);
		instrument=custom;
		showSettingsWindow();
	}
	public void newBoxes(int number) { // CREATES new choice boxes for the notes and octavesof the strings
		noteBox=new NoteBox(number, sharp);
		octaveBox=new OctaveBox(number);
noteBox.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldNote, Number newNote) {
				changeStringNote(conversion.noteToNumber(noteBox.getItems().get((int) newNote)), number);				
			}
		    });
octaveBox.getSelectionModel().selectedIndexProperty().addListener( new ChangeListener<Number>() {
	@Override
	public void changed(ObservableValue<? extends Number> arg0, Number oldNote, Number newNote) {
		changeStringOctave((int) octaveBox.getItems().get((int) newNote), number);				
	}
    });
noteBox.getSelectionModel().select(instrument.getStringTuning(number).getNoteNumber()-1);
octaveBox.getSelectionModel().select(instrument.getStringTuning(number).getOctave());
NumberedTextField frets=new NumberedTextField(number);
NumberedTextField startFret=new NumberedTextField(number);

startFret.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("-?\\d*")) {
            startFret.setText(newValue.replaceAll("[^\\d]", ""));
        }
    }
});
frets.textProperty().addListener(new ChangeListener<String>() {
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, 
        String newValue) {
        if (!newValue.matches("\\d*")) {
            frets.setText(newValue.replaceAll("[^\\d]", ""));
        }
    }
});

startFret.clear();
frets.clear();

startFret.setText(String.valueOf(instrument.getTuning().get(number).getStartFret()));
frets.setText(String.valueOf(instrument.getTuning().get(number).getFrets()-1));

stringFrets.add(frets);
this.startFret.add(startFret);






	}
	public void changeStringNote( int note, int number) {
		instrument.changeStringNote(note, number, sharp);				
	}
	public void changeStringOctave( int note, int number) {
		instrument.changeStringOctave(note, number);				
	}
	public  void addString(int number, int number2){
		HBox choiceBox= new HBox();
		newBoxes(number);
		noteBoxes.add(noteBox);
		octaveBoxes.add(octaveBox);
		Label label= new Label("String number "+(number2));
		choiceBox.getChildren().add(label);
		choiceBox.getChildren().add(noteBox);
		choiceBox.getChildren().add(octaveBox);
		choiceBox.getChildren().add(stringFrets.get(number));
		choiceBox.getChildren().add(startFret.get(number));

		
		tuningBox.getChildren().add(choiceBox);
	}
	public void setInstrument(Instrument instrument){
		this.instrument=instrument;
	}

	public void setSharp(boolean sharp) {
		this.sharp = sharp;
	}
}
