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
//Class Train Definition - A Java Object Class to build A Train Object
//
//************************************************************************************************
//************************************************************************************************
public class Train {

	//An ArrayList of TrackUnit for each loco & cars that makes up the train
	public ArrayList <TrainUnit> trainUnits = new ArrayList <TrainUnit> ();

	public enum Heading { //NOTE: slant tracks continue as E & W
		N, S, E, W, unSet
	}

	private int     trainID;
	private int     throttleID;

	//Controlling Loco Identification
	private Loco    controllingLoco;

	//Current Train's Controlling Loco Position!
	private TrackSegment currSegment;
	private SegmentPoint currSegPoint;

	private TrackSegment prevSegment;

	private int     speed;  //0 .. 99 mph...
	private int     speedMPH;
	private Heading heading;

    public Train(int id) {
    	trainID = id;
    	speed = 0;
    	speedMPH = 0;
   		heading = Heading.unSet;
   		prevSegment = null;
   		currSegment = null;
   		currSegPoint = null;
    }

    public void draw() {
    	//System.out.println("Train.draw() called");
    	if(LayoutPanel.g2d != null) {
    		//System.out.println("Train.draw() called! trainUnits List Size="+trainUnits.size());
    		for(int i = 0; i < trainUnits.size(); i++) {
    			trainUnits.get(i).draw();
    		}

    		if(currSegment != null) {
    			currSegment.train = this;
    		}
      	}
    }


    public void processMouseClick(Point pt) {
    	if(LayoutPanel.g2d != null) {
    	}
    }
    public String getLocoName() {
    	return (controllingLoco.getLocoName());
    }
    public int getTrainID() {
    	return (trainID);
    }
    public int getThrottleID() {
    	return (throttleID);
    }
    public int getLocoAddr() {
    	return (controllingLoco.getLocoDccAddress());
    }
    public Loco getLoco() {
    	return (controllingLoco);
    }
    public int getSpeed() {
    	return (speed);
    }
    public int getSpeedMPH() {
    	return (speedMPH);
    }
    public Loco.Direction getDirection() {
    	return (controllingLoco.getDirection());
    }
    public Heading getHeading() {
    	return (heading);
    }
    public TrackSegment getSegment() {
    	return (currSegment);
    }
    public SegmentPoint getSegmentPt() {
    	return (currSegPoint);
    }

    public void changeHeading(TrackSegment.SegOrientation prevOrient) {

    	System.out.println("\nTrain changeHeading called START heading="+heading+" Orient="+currSegment.orient+
				"  PrevOrient="+prevOrient);

    	if(prevOrient == TrackSegment.SegOrientation.RIGHTSLANT &&
				currSegment.orient == TrackSegment.SegOrientation.VERTICAL) {
			if(heading == Heading.W) setHeading(Heading.S);
			else if(heading == Heading.E) setHeading(Heading.N);
		} else if(prevOrient == TrackSegment.SegOrientation.LEFTSLANT &&
				currSegment.orient == TrackSegment.SegOrientation.VERTICAL) {
			if(heading == Heading.W) setHeading(Heading.N);
			else if(heading == Heading.E) setHeading(Heading.S);
		} else if(prevOrient == TrackSegment.SegOrientation.VERTICAL &&
				currSegment.orient == TrackSegment.SegOrientation.RIGHTSLANT) {
			if(heading == Heading.N) setHeading(Heading.E);
			else if(heading == Heading.S) setHeading(Heading.W);
		} else if(prevOrient == TrackSegment.SegOrientation.VERTICAL &&
				currSegment.orient == TrackSegment.SegOrientation.LEFTSLANT) {
			if(heading == Heading.N) setHeading(Heading.W);
			else if(heading == Heading.S) setHeading(Heading.E);
		} else if(prevOrient == TrackSegment.SegOrientation.RIGHTSLANT &&
				currSegment.orient == TrackSegment.SegOrientation.HORIZONTAL) {
			if(heading == Heading.N) setHeading(Heading.E);
			else if(heading == Heading.S) setHeading(Heading.W);
		}else if (currSegment.orient == TrackSegment.SegOrientation.RIGHTSLANT ||
				   currSegment.orient == TrackSegment.SegOrientation.LEFTSLANT) {
			if(heading == Heading.N) setHeading(Heading.W);
			else if(heading == Heading.S) setHeading(Heading.E);
		}else {
			System.out.println("Train changeHeading called ELSE - NO CHANGE!! heading="+heading+" Orient="+currSegment.orient+
					"  PrevOrient="+prevOrient);
		}
    	System.out.println("Train changeHeading called END heading="+heading+"\n");

		//NOTE:  Don't need to test for orient == HORIZONTAL since the heading does not change from
		//       the approaching SLANT Lines...
    }

    public void moveTrain() {
    	//TODO
    	//System.out.println("Train Move called");

    	//currSegment
    	if(controllingLoco != null && throttleID != ThrottlePanel.NOT_SET && currSegPoint != null)
    		if(controllingLoco.getDirection() != Loco.Direction.UNSET){
    			currSegPoint = currSegPoint.moveTrain(controllingLoco.getDirection(), heading);
    			if(currSegPoint != null)
    				currSegment = LayoutPanel.layoutList.getSegment(currSegPoint.getSegmentID());

    			//Test for changes in Segment Orientation and adjust Heading as needed - ONLY IF Orientation has changed!!
    			if(currSegment != null) {
    				if(prevSegment != null)
    					if(prevSegment.orient != currSegment.orient) {

    						//Train's heading has changed!
    						System.out.println("Train Move called - Train's heading has changed!");
    						prevSegment.setTrain(null);
    						currSegment.setTrain(this);

    						changeHeading(prevSegment.orient);

    					} //if Orient has changed...
    			} //if currSegment != null
    			prevSegment = currSegment;
    		}
    }

    public void placeTrain(int segId, Heading pHeading) {
    	currSegment  = LayoutPanel.layoutList.getSegment(segId);
    	currSegPoint = currSegment.getFirstPoint();
    	heading      = pHeading;

    	setSpeed(0);
    	setDirection(Loco.Direction.FORWARD);

    	//System.out.println("placeTrain segId="+segId+" segName="+currSegment.getSegmentName()+
    	//		" heading="+heading);

    	//Draw the Train...
   		draw();
    }

    public void setTrainID(int id) {
    	trainID = id;
    }
    public void setThrottleID(int id) {
    	throttleID = id;
    }
    public void setLoco(Loco loco) {
    	controllingLoco = loco;
    	controllingLoco.setIsHeadOfTrain(true);
    	trainUnits.add(loco);
    }
    public void setDirection(Loco.Direction dir) {
    	controllingLoco.setDirection(dir);
    }
    public void setSpeed(int pSpeed) {
    	speed = pSpeed;
    	controllingLoco.adjSpeed(pSpeed);
    }
    public void setSpeedMPH(int pSpeedMPH) {
    	speedMPH = pSpeedMPH;
    }
    public void setHeading(Heading pHeading) {
    	heading = pHeading;
    }
}

