import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
public class ChordImage { // class for drawing chord images
	ChordImage(){
	}
	
	
	
	
	
	
	
	
	public Pane getChordImage( Chord chord, int maxFrets, int strings,  ArrayList<InstrumentString> instrumentStrings, boolean showFretBoard, int maxFretDistance){ // draws a basic chord image with black dots on the hit frets and open dots for the open strings and x's for unhit strings takes a chord 
strings=strings+1;
int 	startFret=instrumentStrings.get(0).getStartFret();

Line line;
Pane pane=  new Pane();
 line = new Line(0, startFret*60,   0, instrumentStrings.get(0).getFrets()*60 );
	pane.getChildren().add(line);


for (int count=0; count<strings-1; count++) {
	 	startFret=instrumentStrings.get(count).getStartFret();
	
	
	
	
		int frets=instrumentStrings.get(count).getFrets();
			 line = new Line((count+1)*25, startFret*60,  (count+1)*25   , frets*60);
			 if (count>=0 && count<strings) {
					line.setStroke(Color.web("cc9900")); 
					}						
		pane.getChildren().add(line);
		if(count>0) {
		 line = new Line((((count)*25)), (frets-(frets-instrumentStrings.get(count-1).getFrets()))*60,(count)*25,  frets*60 );

		 pane.getChildren().add(line);
		
		 if (instrumentStrings.get(count-1).getStartFret()>startFret) {

		 line = new Line((count)*25,startFret*60,(count)*25 , instrumentStrings.get(count-1).getStartFret()*60 );
		 pane.getChildren().add(line);
		 }
		 if (instrumentStrings.get(count-1).getStartFret()<startFret) {

		 line = new Line((count+1)*25,startFret*60,(count+1)*25 , instrumentStrings.get(count-1).getStartFret()*60 );
		 pane.getChildren().add(line);
		 }

		}
		
		
	}
 line = new Line(strings*25, instrumentStrings.get(instrumentStrings.size()-1).getStartFret()*60,strings*25 , instrumentStrings.get(instrumentStrings.size()-1).getFrets()*60) ;
pane.getChildren().add(line);



for (int count=1; count<maxFrets+1; count++) {
	int stringTotal=0;
	int endStringTotal=0;
	int oldStringTotal=0;
	
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
		if(stringTotal>oldStringTotal&& startFret>=2) {
			
			line= new Line(0, count*60,(stringTotal+1)*25  , count*60 );
			pane.getChildren().add(line);
			
		}
		else if(stringTotal>oldStringTotal && startFret<2) {
			line= new Line((strings-stringTotal-1)*25, count*60,(strings)*25  , count*60 );
		pane.getChildren().add(line);
		}
		
	}
	
	else {
	
	line= new Line((strings-endStringTotal-1)*25, count*60,(stringTotal+1)*25 , count*60 );
	pane.getChildren().add(line);
	
	}
	oldStringTotal=stringTotal;
	
	if (count!=maxFrets) {
	Label label=new Label(String.valueOf(count));
	label.setStyle("-fx-font-size: 20px");
	label.setTranslateY(count*60);
	label.setTranslateX(0);
	pane.getChildren().add(label);
	}
	
	


	
}



ArrayList<Position> positions=chord.getPositions();


int size=positions.size();
for (int count=0; count<size; count++){
int x=positions.get(count).getX();
int y=positions.get(count).getY();
startFret= instrumentStrings.get(count).getStartFret();

if (y==-1) {
	Label label=new Label("X");
	label.setStyle("-fx-font-size: 20px");
	label.setTranslateY(startFret*60-14.5);
	
		
	
	label.setTranslateX(((x+1)*26.5)-10.5);
	pane.getChildren().add(label);
}
else {
	if (y!=startFret-1) {
		Ellipse shape= new Ellipse( ((x+1)*25),((y+1)*60)-30, 7, 7);
		pane.getChildren().add(shape);
	}
	else {
		Ellipse shape= new Ellipse(((x+1)*25), (startFret*60),  7, 7);
		shape.setFill(Color.web("ffffff"));
		shape.setStroke(Color.web("000000"));
		pane.getChildren().add(shape);
	}
}

}


		
		
		
	


		return pane;
	}
	public Pane getChordImageWithNotes( Chord chord, int maxFrets, int strings,  ArrayList<InstrumentString> instrumentStrings, boolean showFretBoard, int maxFretDistance){ // draws a basic chord image with black dots on the hit frets and open dots for the open strings and x's for unhit strings takes a chord 
			strings=strings+1;
			int lowestFret=chord.getLowestFret();
			int 	startFret=instrumentStrings.get(0).getStartFret();

			Line line;
			Pane pane=  new Pane();
			 line = new Line(0, startFret*60,   0, instrumentStrings.get(0).getFrets()*60 );
				pane.getChildren().add(line);


			for (int count=0; count<strings-1; count++) {
				 	startFret=instrumentStrings.get(count).getStartFret();
				
				
				
				
					int frets=instrumentStrings.get(count).getFrets();
						 line = new Line((count+1)*25, startFret*60,  (count+1)*25   , frets*60);
						 if (count>=0 && count<strings) {
								line.setStroke(Color.web("cc9900")); 
								}						
					pane.getChildren().add(line);
					if(count>0) {
					 line = new Line((((count)*25)), (frets-(frets-instrumentStrings.get(count-1).getFrets()))*60,(count)*25,  frets*60 );

					 pane.getChildren().add(line);
					
					 if (instrumentStrings.get(count-1).getStartFret()>startFret) {

					 line = new Line((count)*25,startFret*60,(count)*25 , instrumentStrings.get(count-1).getStartFret()*60 );
					 pane.getChildren().add(line);
					 }
					 if (instrumentStrings.get(count-1).getStartFret()<startFret) {

					 line = new Line((count+1)*25,startFret*60,(count+1)*25 , instrumentStrings.get(count-1).getStartFret()*60 );
					 pane.getChildren().add(line);
					 }

					}
					
					
				}
			 line = new Line(strings*25, instrumentStrings.get(instrumentStrings.size()-1).getStartFret()*60,strings*25 , instrumentStrings.get(instrumentStrings.size()-1).getFrets()*60) ;
			pane.getChildren().add(line);



			for (int count=1; count<maxFrets+1; count++) {
				int stringTotal=0;
				int endStringTotal=0;
				int oldStringTotal=0;
				
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
					if(stringTotal>oldStringTotal&& startFret>=2) {
						
						line= new Line(0, count*60,(stringTotal+1)*25  , count*60 );
						pane.getChildren().add(line);
						
					}
					else if(stringTotal>oldStringTotal && startFret<2) {
					line= new Line((strings-stringTotal-1)*25, count*60,(strings)*25  , count*60 );
					pane.getChildren().add(line);
					}
					
				}
				
				else {
				
				line= new Line((strings-endStringTotal-1)*25, count*60,(stringTotal+1)*25 , count*60 );
				pane.getChildren().add(line);
				
				}
				if (count!=maxFrets) {
					Label label=new Label(String.valueOf(count));
					label.setStyle("-fx-font-size: 20px");
					label.setTranslateY(count*60);
					label.setTranslateX(0);
					pane.getChildren().add(label);
					}
			}



		ArrayList<Position> positions=chord.getPositions();

		int size=positions.size();
		for (int count=0; count<size; count++){
		int x=positions.get(count).getX();
		int y=positions.get(count).getY();
	 	startFret=instrumentStrings.get(count).getStartFret();


		 x=positions.get(count).getX();
	 y=positions.get(count).getY();
		String note=positions.get(count).getNote().getNote();
		String noteOctave=Integer.toString(positions.get(count).getNote().getOctave());
	
		if (y==-1) {
			Label label=new Label("X");
			label.setStyle("-fx-font-size: 20px");
			label.setTranslateY(startFret*60-14.5);
			label.setTranslateX(((x+1)*26.5)-10.5);
			pane.getChildren().add(label);
		}
		else {
			if (y!=startFret-1) {
				Label noteLabel = new Label(note+noteOctave);
				noteLabel.setTranslateX(((x+1)*25)-8);
				noteLabel.setTranslateY(((y+1)*60)-35);
				noteLabel.setStyle("-fx-font-size: 9px; -fx-<font-weight>:bold");
				Ellipse shape= new Ellipse( ((x+1)*25),((y+1)*60)-30, 11, 11);
				shape.setFill(Color.web("ffffff"));
				shape.setStroke(Color.web("000000"));
				pane.getChildren().add(shape);
				pane.getChildren().add(noteLabel);
			}
			else {
				Label noteLabel = new Label(note+noteOctave);
				noteLabel.setTranslateX(((x+1)*25)-8);
				noteLabel.setTranslateY(startFret*60-5);
				noteLabel.setStyle("-fx-font-size: 9px; -fx-<font-weight>:bold");

				Ellipse shape= new Ellipse(((x+1)*25),( startFret*60),  11, 11);
				shape.setFill(Color.web("ffffff"));
				shape.setStroke(Color.web("000000"));
				pane.getChildren().add(shape);
				pane.getChildren().add(noteLabel);
			}
		}
		}
		
		
				return pane;
			}
}
