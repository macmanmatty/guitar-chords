import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
public class FretBoardImage {
	public Pane drawFretBoard(int strings , int maxFrets,  ArrayList<ArrayList<Note>> notes, ArrayList<InstrumentString> instrumentStrings){
		strings=strings+1;
		Line line;
		Pane pane=  new Pane();
		 line = new Line(instrumentStrings.get(0).getStartFret()*60, 0, instrumentStrings.get(0).getFrets()*60, 0);
			pane.getChildren().add(line);


		for (int count=0; count<strings-1; count++) {
			int 	startFret=instrumentStrings.get(count).getStartFret();
				int frets=instrumentStrings.get(count).getFrets();
					 line = new Line(startFret*60, (count+1)*35, frets*60, (count+1)*35);
					 if (count>=0 && count<strings) {
							line.setStroke(Color.web("cc9900")); 
							}						
				pane.getChildren().add(line);
				if(count>0) {
				 line = new Line((frets-(frets-instrumentStrings.get(count-1).getFrets()))*60, (count)*35, frets*60, (count)*35);

				 pane.getChildren().add(line);
				 
			
				 
				 
				 
				 
				 
				 if (instrumentStrings.get(count-1).getStartFret()>startFret) {
				 line = new Line(startFret*60, (count)*35, instrumentStrings.get(count-1).getStartFret()*60, (count)*35);
				 pane.getChildren().add(line);
				 }
				 
				 
				 else  if (instrumentStrings.get(count-1).getStartFret()<startFret) {

				 line = new Line(startFret*60, (count+1)*35, instrumentStrings.get(count-1).getStartFret()*60, (count+1)*35);
				 pane.getChildren().add(line);
				 }
				 
				 

				}
				
				
			}
		 line = new Line(instrumentStrings.get(instrumentStrings.size()-1).getStartFret()*60, strings*35, instrumentStrings.get(instrumentStrings.size()-1).getFrets()*60, strings*35);
		pane.getChildren().add(line);

		
		
		for (int count=1; count<maxFrets+1; count++) {
			int stringTotal=0;
			int endStringTotal=0;
			int oldStringTotal=0;
			int startFret=0;
			
			for (int counter=0; counter<strings-1; counter++) {
				 	startFret=instrumentStrings.get(counter).getStartFret();
				int 	frets=instrumentStrings.get(counter).getFrets();

				if (startFret<=count) {
					stringTotal++;
					
				}
				if (frets>=count) {
					endStringTotal++;
					
				}
				 

			}
			if (stringTotal<strings-1) {
if(stringTotal>oldStringTotal && startFret>=2) {
				line= new Line(count*60,  0, count*60, (stringTotal+1)*35);
				pane.getChildren().add(line);
}

else if(stringTotal>oldStringTotal &&startFret<2) {
				line= new Line(count*60,  (strings-stringTotal-1)*35, count*60, (strings)*35);
				pane.getChildren().add(line);
}

				
			}
			
			else {
			
			line= new Line(count*60, (strings-endStringTotal-1)*35, count*60, (stringTotal+1)*35);
			pane.getChildren().add(line);
			
			}
			oldStringTotal=stringTotal;

			
		}
		for (int count=0; count<strings-1; count++){
			int 	startFret=instrumentStrings.get(count).getStartFret();
			int frets=instrumentStrings.get(count).getFrets();
for (int counter=startFret-1; counter<frets ; counter++) {
	
		String note=notes.get(count).get(counter-startFret+1).getNote();
		String noteOctave=Integer.toString(notes.get(count).get(counter).getOctave());
if(counter-startFret+1!=0) {
	Label noteLabel = new Label(note+noteOctave);
	noteLabel.setTranslateX(((counter+1)*60)-38);
	noteLabel.setTranslateY(((count+2)*35)-40);
	noteLabel.setStyle("-fx-font-size: 9px");
					Ellipse shape= new Ellipse( ((counter+1)*60)-30,((count+2)*35)-35, 11,11);
					shape.setFill(Color.web("ffffff"));
					shape.setStroke(Color.web("000000"));
					pane.getChildren().add(shape);
					pane.getChildren().add(noteLabel);
}
else {
	Label noteLabel = new Label(note+noteOctave);
	noteLabel.setTranslateX(((counter+1)*60)-8);
	noteLabel.setTranslateY(((count+2)*35)-40);
	noteLabel.setStyle("-fx-<font-size>:9px;-fx-<font-weight>:bold ");
	Ellipse shape= new Ellipse( ((counter+1)*60),((count+2)*35)-35, 12,12);
	shape.setFill(Color.web("ffffff"));
	shape.setStroke(Color.web("000000"));
	pane.getChildren().add(shape);
	pane.getChildren().add(noteLabel);
}
			}
		}
	return pane;
	}
	public Pane showScale(int strings , int maxFrets, ArrayList<ArrayList<Note>> notes, ArrayList<Integer> scaleNotes, ArrayList<InstrumentString> instrumentStrings, boolean showFretBoard){
strings=strings+1;
		int size2=scaleNotes.size();
		Line line;
		Pane pane=  new Pane();
		 line = new Line(instrumentStrings.get(0).getStartFret()*60, 0, instrumentStrings.get(0).getFrets()*60, 0);
			pane.getChildren().add(line);


		for (int count=0; count<strings-1; count++) {
			int 	startFret=instrumentStrings.get(count).getStartFret();
				int frets=instrumentStrings.get(count).getFrets();
					 line = new Line(startFret*60, (count+1)*35, frets*60, (count+1)*35);
					 if (count>=0 && count<strings) {
							line.setStroke(Color.web("cc9900")); 
							}						
				pane.getChildren().add(line);
				if(count>0) {
				 line = new Line((frets-(frets-instrumentStrings.get(count-1).getFrets()))*60, (count)*35, frets*60, (count)*35);

				 pane.getChildren().add(line);
				 line = new Line(startFret*60, (count)*35, instrumentStrings.get(count-1).getStartFret()*60, (count)*35);
				 pane.getChildren().add(line);

				 

				}
				
				
			}
		 line = new Line(instrumentStrings.get(instrumentStrings.size()-1).getStartFret()*60, strings*35, instrumentStrings.get(instrumentStrings.size()-1).getFrets()*60, strings*35);
		pane.getChildren().add(line);

		
		
		for (int count=1; count<maxFrets+1; count++) {
			int stringTotal=0;
			int endStringTotal=0;
			for (int counter=0; counter<strings-1; counter++) {
				int 	startFret=instrumentStrings.get(counter).getStartFret();
				int 	frets=instrumentStrings.get(counter).getFrets();

				if (startFret<=count) {
					stringTotal++;
					
				}
				if (frets>=count) {
					endStringTotal++;
					
				}
				 

			}
			if (stringTotal<strings-1) {

				line= new Line(count*60,  (strings-stringTotal-1)*35, count*60, (stringTotal+1+(strings-stringTotal-1))*35);
				pane.getChildren().add(line);
				
			}
			
			else {
			
			line= new Line(count*60, (strings-endStringTotal-1)*35, count*60, (stringTotal+1)*35);
			pane.getChildren().add(line);
			
			}
			
		}
		for (int count=0; count<strings-1; count++){
			int 	startFret=instrumentStrings.get(count).getStartFret();
			int frets=instrumentStrings.get(count).getFrets();
for (int counter=startFret-1; counter<frets ; counter++) {
	


String note=notes.get(count).get(counter-startFret+1).getNote();
String noteOctave=Integer.toString(notes.get(count).get(counter).getOctave());
for ( int counter2=0; counter2<size2; counter2++) {
if(counter!=0 &&  notes.get(count).get(counter-startFret+1).getNoteNumber()==scaleNotes.get(counter2)) {
Label noteLabel = new Label(note+noteOctave);
noteLabel.setTranslateX(((counter+1)*60)-38);
noteLabel.setTranslateY(((count+2)*35)-40);
noteLabel.setStyle("-fx-font-size: 9px");
			Ellipse shape= new Ellipse( ((counter+1)*60)-30,((count+2)*35)-35, 11,11);
			shape.setFill(Color.web("ffffff"));
			shape.setStroke(Color.web("000000"));
			pane.getChildren().add(shape);
			pane.getChildren().add(noteLabel);
}
else  if (notes.get(count).get(counter-startFret+1).getNoteNumber()==scaleNotes.get(counter2)){
Label noteLabel = new Label(note+noteOctave);
noteLabel.setTranslateX(((counter+1)*60)-8);
noteLabel.setTranslateY(((count+2)*35)-40);
noteLabel.setStyle("-fx-<font-size>:9px ");
Ellipse shape= new Ellipse( ((counter+1)*60),((count+2)*35)-35, 11,11);
shape.setFill(Color.web("ffffff"));
shape.setStroke(Color.web("000000"));
pane.getChildren().add(shape);
pane.getChildren().add(noteLabel);
}
}

			}
		}
		
		

		
	return pane;
	}

}
