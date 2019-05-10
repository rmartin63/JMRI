package apps.gui3.tcs;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


//************************************************************************************************
//************************************************************************************************
//
//Class TrainUnit Definition - A Java Object Class to build A TrainUnit Object to represent a car or loco
//
//************************************************************************************************
//************************************************************************************************
public class TrainUnit {

	
	protected int trainID;
	private int positionInTrain; // position in Train ArrayList
	private TrainUnit nextUnit;
	private TrainUnit prevUnit;
	
	private TrackSegment prevSegment;
	private SegmentPoint prevSegPoint;
	
	private TrackSegment curSegment;
	private SegmentPoint curSegPoint;

	private TrackSegment nexSegment;
	private SegmentPoint nexSegPoint;

	private String pathToImage;  //Use for mouseOver

	  	
    public TrainUnit(int id) {
    	trainID = id;

   		   		
   		//Draw the Train...
   		//draw();
    }

    public void draw() {
    	System.out.println("TrainUnit.draw() called!");
    	if(LayoutPanel.g2d != null) {
    		
      	}
    }

    public void move() {
    	if(LayoutPanel.g2d != null) {
    		//TrainSegment& Point temp = next_location;
    		//next_location = next_location.next;
    		//prev_location = curr_location;
    		//curr_location = temp;
      	}
    }
   
    public void processMouseClick(Point pt) {
    	if(LayoutPanel.g2d != null) {
    	}
    }
    
    public int getTrainID() {
    	return (trainID);
    }
     
    public void setTrainID(int id) {
    	trainID = id;
    }
}

