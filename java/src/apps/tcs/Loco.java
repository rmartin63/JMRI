package apps.gui3.tcs;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


//************************************************************************************************
//************************************************************************************************
//
//Class Loco Definition - A Java Object Subclass to build A Loco Object to represent a loco
//
//************************************************************************************************
//************************************************************************************************
public class Loco extends TrainUnit {

	public enum Direction {
		FORWARD, REVERSE, UNSET
	}

	private String    name;
	private int       dccAddress;
	private boolean   f1State;
	//More F(x) States??

	//private int       trainID;
	private boolean   isHeadOfTrain;
	private Direction direction;

	private boolean   isPartOfConsist;
	private int       consistID;

    public Loco(String pName, int id, int PDccAddress) {
    	super(id);

    	//trainID = id;
    	name = pName;
    	dccAddress = PDccAddress;
    	isHeadOfTrain = false;
    	direction = Direction.UNSET;
    	isPartOfConsist = false;
    	consistID = 0;
    }

    public void draw() {

    	final int iconSize = 5;//10;
    	//System.out.println("Loco Draw called");
    	if(LayoutPanel.g2d != null) {
    		//System.out.println("Loco Draw called in if not null");
    		//draw as red Triangle
    		//Can a triangle be managed to point correctly according to direction, heading & slantlines
    		//There is not a drawTriangle method neither in Graphics nor Graphics2D.
    		//You need to do it by yourself.

    		//Determine the orientation of the Segment

    		//Compare the heading (facing) to the Segment's orientation & Draw Triangle accordingly
    		//NOTE: We don't care about direction here, since only drawing loco according to where it is facing
    		//      Directions plays a role in the movement of the loco
    		Train train = getTrain();

    		if(train != null){
    			TrackSegment seg = train.getSegment();
    			//System.out.println("Loco.draw() seg="+seg);
    			if(seg != null) {
    				Point segPt1 = seg.getPoint1();
    				Point segPt2 = seg.getPoint2();

    				SegmentPoint sp  = train.getSegmentPt();
    				if(sp != null){
    					Point pt = sp.getPoint();

    					Point leadPt = new Point(pt);
    					Point tailRtPt = new Point(pt);
    					Point tailLtPt = new Point(pt);

    					//Adjust points as needed!
    					switch (seg.orient){
    					case VERTICAL:
    						if(train.getHeading() == Train.Heading.N){
    							//System.out.println("Loco.draw() Point UP Heading = "+train.getHeading());
    							//Point north (Up)
    							leadPt = new Point(pt.x, pt.y-iconSize);
    							tailRtPt = new Point(pt.x+iconSize, pt.y+iconSize);
    							tailLtPt = new Point(pt.x-iconSize, pt.y+iconSize);
    						} else {
    							//System.out.println("Loco.draw() Point Down Heading = "+train.getHeading()+" sp="+pt);
    							//Point south (down)
    							leadPt = new Point(pt.x, pt.y+iconSize);
    							tailRtPt = new Point(pt.x-iconSize, pt.y-iconSize);
    							tailLtPt = new Point(pt.x+iconSize, pt.y-iconSize);
    						}
    						break;
    					case HORIZONTAL:
    						//NOTE: Slant tracks continue as E & W!
    						if(train.getHeading() == Train.Heading.W){
    							//Point West (Left)
    							leadPt = new Point(pt.x-iconSize, pt.y);
    							tailRtPt = new Point(pt.x+iconSize, pt.y-iconSize);
    							tailLtPt = new Point(pt.x+iconSize, pt.y+iconSize);
	    					} else {
	    						//Point East (Right)
	    						leadPt = new Point(pt.x+iconSize, pt.y);
	    						tailRtPt = new Point(pt.x-iconSize, pt.y+iconSize);
	    						tailLtPt = new Point(pt.x-iconSize, pt.y-iconSize);
	    					}
	    					break;
	    				case RIGHTSLANT:
	    					//NOTE: Slant tracks continue as E & W!
	    					if(train.getHeading() == Train.Heading.W){
	    						//Point West (downward to the Left)
	    						int y = pt.y+iconSize;
	    						int x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y);
	    						leadPt = new Point(x, y);

	    						y = pt.y-iconSize;
	    						//x = pt.x-15; //LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y)-(int)(iconSize*1.5);
	    						x = getXOnPerpLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, pt, y);
	    						tailRtPt = new Point(x, y);

	    						y = pt.y+iconSize;
	    						//x = pt.x+iconSize; //LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y)+(int)(iconSize*1.5);
	    						x = getXOnPerpLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, pt, y);
	    						tailLtPt = new Point(x, y);
	    					} else {
	    						//Point East (upward to the Right)
	    						int y = pt.y-iconSize;
	    						int x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y);
	    						leadPt = new Point(x, y);

	    						y = pt.y+iconSize;
	    						x = getXOnPerpLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, pt, y);
	    						tailRtPt = new Point(x, y);

	    						y = pt.y-iconSize;
	    						x = getXOnPerpLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, pt, y);
	    						tailLtPt = new Point(x, y);
	    						/*
	    						System.out.println("Loco.draw() pt.x&y="+pt.x+" & "+pt.y+" leadPtx, y="+leadPt.x+", "+leadPt.y+
	    								" tailRtPt.x, y="+tailRtPt.x+", "+tailRtPt.y+
	    								" tailLtPt.x, y="+tailLtPt.x+", "+tailLtPt.y);*/
	    					}
	    					break;

	    				case LEFTSLANT:
	    					//NOTE: Slant tracks continue as E & W!
	    					if(train.getHeading() == Train.Heading.W){
	    						//Point West (upward to the Left)
	    						int y = pt.y-iconSize;
	    						int x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y);
	    						float d = getDistance(pt.x, pt.y, x, y);
	    						//System.out.println("before loop d="+d);
	    						while(d > 15 && y != pt.y) {
	    							y = y+1;
		    						x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y);
		    						d = getDistance(pt.x, pt.y, x, y);
		    						//System.out.println("in loop d="+d);
	    						}

	    						while(d < 15 && y != pt.y) {
	    							y = y-1;
		    						x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y);
		    						d = getDistance(pt.x, pt.y, x, y);
	    						}

	    						leadPt = new Point(x, y);

	    						y = pt.y-iconSize;
	    						x = getXOnPerpLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, pt, y);
	    						float d1 = getDistance(pt.x, pt.y, x, y);
	    						while(d1 > 15 && y != pt.y) {
	    							y = y+1;
		    						x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y);
		    						d1 = getDistance(pt.x, pt.y, x, y);
	    						}
	    						/*
	    						while(d < 6 && y != pt.y) {
	    							y = y-1;
		    						x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y);
		    						d = getDistance(pt.x, pt.y, x, y);
	    						}
	    						*/
	    						tailRtPt = new Point(x, y);

	    						y = pt.y+iconSize;
	    						x = getXOnPerpLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, pt, y);
	    						float d2 = getDistance(pt.x, pt.y, x, y);
	    						while(d2 > 15 && y != pt.y) {
	    							y = y-1;
		    						x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y);
		    						d2 = getDistance(pt.x, pt.y, x, y);
	    						}
	    						/*
	    						while(d < 6 && y != pt.y) {
	    							y = y+1;
		    						x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y);
		    						d = getDistance(pt.x, pt.y, x, y);
	    						}
	    						*/
	    						tailLtPt = new Point(x, y);

	    						//System.out.println("Loco.draw() LEFTSLANT pt.x&y="+pt.x+" & "+pt.y+" leadPtx, y="+leadPt.x+", "+leadPt.y+
	    						//		" tailRtPt.x, y="+tailRtPt.x+", "+tailRtPt.y+
	    						//		" tailLtPt.x, y="+tailLtPt.x+", "+tailLtPt.y+" d="+d+" d1="+d1+" d2="+d2+
	    						//		" dir="+direction+"\n");


	    					} else {
	    						//Point East (downward to the Right)
	    						int y = pt.y+iconSize;
	    						int x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y);
	    						leadPt = new Point(x, y);

	    						y = pt.y+iconSize;
	    						//x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y)-(int)(iconSize*1.5);
	    						x = getXOnPerpLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, pt, y);
	    						tailRtPt = new Point(x, y);

	    						y = pt.y-iconSize;
	    						//x = LayoutPanel.getXOnLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, y)+(int)(iconSize*1.5);
	    						x = getXOnPerpLine(segPt1.x, segPt1.y, segPt2.x, segPt2.y, pt, y);
	    						tailLtPt = new Point(x, y);
	    					}
	    					break;
	    				case SWITCHFROG:
	    				default:
	    					//Don't draw...
	    					break;
	    				}
    				/*
    					System.out.println("Loco draw() leadptX="+leadPt.x+" leadptY="+leadPt.y+
	    						" tailRtPtX="+tailRtPt.x+" tailRtPtY="+tailRtPt.y+
	    						" tailLtPtX="+tailLtPt.x+" tailLtPtY="+tailLtPt.y);
	    					*/
	    				LayoutPanel.g2d.setColor(Color.red);
	    				Polygon p = new Polygon();
	    			    // make a triangle.
	    			    p.addPoint(leadPt.x, leadPt.y);
	    			    p.addPoint(tailRtPt.x, tailRtPt.y);
	    			    p.addPoint(tailLtPt.x,tailLtPt.y);
	    			    LayoutPanel.g2d.fillPolygon(p);

	    				LayoutPanel.setLayoutColorAndStroke();
	    			} //else System.out.println("Loco draw() segmentPt = null!! - seg="+seg);
	    		}
    		}
      	}
    }

    public void processMouseClick(Point pt) {
    	if(LayoutPanel.g2d != null) {
    	}
    }

    public String getLocoName() {
    	return (name);
    }

    public void setLocoName(String pName) {
    	name = pName;
    }

    public Direction getDirection() {
    	return (direction);
    }

    public void setDirection(Direction dir) {
    	direction = dir;
    }

    public int getLocoDccAddress() {
    	return (dccAddress);
    }

    public void setLocoDccAddress(int addr) {
    	dccAddress = addr;
    }

    public int getTrainID() {
    	return (trainID);
    }

    public void setTrainID(int id) {
    	trainID = id;
    }

    public boolean getIsHeadOfTrain() {
    	return (isHeadOfTrain);
    }

    public void setIsHeadOfTrain(boolean isHead) {
    	isHeadOfTrain = isHead;
    }

    public Train getTrain() {
    	return (ThrottlePanel.getTrain(dccAddress));
    }
    public void start() {

    }

    public void stop() {

    }

    public void adjSpeed(int newSpeed) {

    }

    public void changeDirection(boolean isForward) {
    	if(isForward == true) direction = Direction.FORWARD;
    	else direction = Direction.REVERSE;
    }

    public void attachtoThrottle() {

    }

    public void detachFromthrottle() {

    }

    public static float getDistance(int x0, int y0, int x1, int y1) {
    	float retD = (float)0.0;

    	retD = (float)Math.sqrt(Math.pow((x0-x1), 2) + Math.pow((y0-y1), 2));

    	return retD;
    }

    public static int getXOnPerpLine(int x0, int y0, int x1, int y1, Point segPt, int knownY) {
		//m=(y1 - y0)/(x1 - x0)
		//NOTE: Could not use m as a double - when I did, it always resulted in m=0!
		//BIG NOTE: Below - I have to multiple by -1 on y difference since a screen's vertical pixel count increases
		//          as you move down compared to a Y-axis in Math!!!
		float m = (float)(-1)*(y1 - y0) / (x1 - x0);
		float perpSlope = (-1)/m;

		//NOTE: For the perpendicular line it is a line with a slope of the negative inverse of the first line

		// (y - y0) = m(x - x0)
		// knownY-y0 = m*(returnX - x0)
		// knownY = m * (returnX - x0) + y0
		// knownY = m*returnX - m*x0 + y0
		// -m*returnX = m*x0 +y0 - knownY
		// returnX = (m*x0 +y0 - knownY) / (-m)
		//int returnX = (int)(((m *x0) + y0 - knownY) / ((-1) * m));
		int returnX = (int)Math.abs((((perpSlope *segPt.x) + segPt.y - knownY) / ((-1) * perpSlope)));

		//System.out.print("In getXOnPerpLine: m="+m+" x0="+x0+" y0="+y0+" x1="+x1+" y1="+y1+"segPT="+segPt+
		//		" perpSlope="+perpSlope+" knownY="+knownY+" returnX="+returnX+"\n");

		return(returnX);
	}

}

