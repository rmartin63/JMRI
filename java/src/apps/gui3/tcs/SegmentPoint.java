package apps.gui3.tcs;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;


//************************************************************************************************
//************************************************************************************************
//
//Class SegmentPoint Definition - A Java Object Class to build A Linked List Node for a Track Segment Point
//
//************************************************************************************************
//************************************************************************************************
public class SegmentPoint {

	public boolean       isOccupied;
    private int          pointID;
    private int          segmentID;
    private SegmentPoint next;
    private SegmentPoint prev;
    private SegmentPoint nextSpur;
    private SegmentPoint prevSpur;
    private boolean      isFrogPt;
    private int          switchID;
    private Point        segmentPoint;

    private final int    NOT_SET = -1;

    private TrackSegment.SegOrientation orient;


    public SegmentPoint(int id, int x, int y, boolean isFrog) {
    	pointID = id;
    	isOccupied = false;
    	segmentPoint = null;
    	isFrogPt = isFrog;
    	switchID = NOT_SET;
    	next = null;
    	prev = null;
    	nextSpur = null;
    	prevSpur = null;

    	//System.out.println("Creating SegmentPoint - ptID="+id + " x="+x+" y="+y);
    	if(LayoutPanel.g2d != null && x > 0 && y > 0) {
    		segmentPoint = new Point(x, y);
    		//paintPoint(Color.red);
    	}
    }

    public SegmentPoint getNext() {
    	return (next);
    }
    public SegmentPoint getPrev() {
    	return (prev);
    }
    public SegmentPoint getNextSpur() {
    	return (nextSpur);
    }
    public SegmentPoint getPrevSpur() {
    	return (prevSpur);
    }

    public int getSwitchID() {
    	int retVal = NOT_SET;
    	if(isFrogPt == true) retVal = switchID;
    	return (retVal);
    }
    public boolean getIsFrogPt() {
    	return (isFrogPt);
    }
    public int getPointID() {
    	return (pointID);
    }
    public int getSegmentID() {
    	return (segmentID);
    }
    public Point getPoint() {
    	return (segmentPoint);
    }
    public int getPointX() {
    	int ret = 0;
    	if(segmentPoint != null) ret = segmentPoint.x;
    	return (ret);
    }
    public int getPointY() {
    	int ret = 0;
    	if(segmentPoint != null) ret = segmentPoint.y;
    	return (ret);
    }
    public SegmentPoint moveTrain(Loco.Direction dir, Train.Heading heading) {
    	TrackSegment seg = LayoutPanel.layoutList.getSegment(segmentID);
    	SegmentPoint retValue = this;
    	SegmentPoint spurValue = this;
    	boolean isThrown = false;


    	if(isFrogPt == true) {
    		for(int i = 0; i < LayoutPanel.switchList.size(); i++){
    			SwitchSegment sw = LayoutPanel.switchList.get(i);
    			if(sw.getSwitchID() == getSwitchID()) {
    				isThrown = sw.getIsThrown();
    				break;
    			}
    		}
    	}
    	//System.out.println("SegPt moveTrain() segmentID="+segmentID+" currPT="+getPoint()+" #seginLIst="+LayoutPanel.layoutList.getListSize()+
    	//		" tailSegID="+LayoutPanel.layoutList.getTail().getSegmentID());
    	if(seg != null){
    		switch (seg.orient){
    		case VERTICAL:
    			if(heading == Train.Heading.N){
    				//Point north (Up)
    				if(dir == Loco.Direction.REVERSE) {
    					//Get Next Pt...
    					//System.out.println("SegPt moveTrain() Get Next Pt...");
    					if(isFrogPt == true && nextSpur != null && isThrown)
    						retValue = getNextSpur();
    					else
    						retValue = getNext();
    				} else {
    					//dir == Forward
    					//Get Prev Pt...
    					//System.out.println("SegPt moveTrain() Get Prev Pt...");
    					if(isFrogPt == true && prevSpur != null && isThrown)
    						retValue = getPrevSpur();
    					else
    						retValue = getPrev();
    				}

    			} else {
    				//Point south (down)
    				if(dir == Loco.Direction.REVERSE) {
    					//Get Prev Pt...
    					//System.out.println("SegPt moveTrain() Get Prev Pt...");
    					if(isFrogPt == true && prevSpur != null && isThrown)
    						retValue = getPrevSpur();
    					else
							retValue = getPrev();
    				} else {
    					//dir == Forward
    					//Get Next Pt...
    					//System.out.println("SegPt moveTrain() Get Next Pt...");
    					if(isFrogPt == true && nextSpur != null && isThrown)
    						retValue = getNextSpur();
    					else
    						retValue = getNext();
    				}
    			}
    			//System.out.println("SegPt moveTrain() VERTICAL Heading = "+heading+" dir="+dir+
    			//		" currId="+getPointID()+" nextId="+getNext().getPointID()+" prevId="+getPrev().getPointID()+
    			//		" isFrogPt="+isFrogPt+" nextSpur="+nextSpur+" prevSpur="+prevSpur);
    			break;
    		case HORIZONTAL:

    			//NOTE: Slant tracks continue as E & W!
    			if(heading == Train.Heading.W){
    				//Point West (Left)
    				if(dir == Loco.Direction.REVERSE) {
    					//Get Next Pt...
    					//System.out.println("SegPt moveTrain() Get Next Pt...");
    					if(isFrogPt == true && nextSpur != null && isThrown)
    						retValue = getNextSpur();
    					else
    						retValue = getNext();
    				} else {
    					//dir == Forward
    					//Get Prev Pt...
    					//System.out.println("SegPt moveTrain() Get Prev Pt...");
    					if(isFrogPt == true && prevSpur != null && isThrown)
    						retValue = getPrevSpur();
    					else
    						retValue = getPrev();
    				}
    			} else {
    				//Point East (Right)
					if(dir == Loco.Direction.REVERSE) {
						//Get Prev Pt...
						//System.out.println("SegPt moveTrain() Get Prev Pt...");
						if(isFrogPt == true && prevSpur != null && isThrown)
							retValue = getPrevSpur();
						else
							retValue = getPrev();
					} else {
						//dir == Forward
						//Get Next Pt...
						//System.out.println("SegPt moveTrain() Get Next Pt...");
						if(isFrogPt == true && nextSpur != null && isThrown)
							retValue = getNextSpur();
						else
							retValue = getNext();
					}
				}
    			/*
    			if(getSegmentID() == 20 || getSegmentID() == 21)
    				System.out.println("SegPt moveTrain() HORIZONTAL Heading = "+heading+" dir="+dir+
    					" currId="+getPointID()+" nextId="+getNext().getPointID()+" prevId="+getPrev().getPointID()+
    					" isFrogPt="+isFrogPt+" nextSpur="+nextSpur+" prevSpur="+prevSpur);
    					*/
				break;
			case RIGHTSLANT:

				//NOTE: Slant tracks continue as E & W!
				if(heading == Train.Heading.W){
					//Point West (downward to the Left)
					if(dir == Loco.Direction.REVERSE) {
						//Get Next Pt...
    					//System.out.println("SegPt moveTrain() Get Next Pt...");
    					if(isFrogPt == true && nextSpur != null && isThrown)
    						retValue = getNextSpur();
    					else
    						retValue = getNext();
    				} else {
    					//dir == Forward
    					//Get Prev Pt...
						//System.out.println("SegPt moveTrain() Get Prev Pt...");
    					if(isFrogPt == true && prevSpur != null && isThrown)
    						retValue = getPrevSpur();
    					else
    						retValue = getPrev();
    				}

				} else {
					//Point East (upward to the Right)
					if(dir == Loco.Direction.REVERSE) {
						//Get Prev Pt...
						//System.out.println("SegPt moveTrain() Get Prev Pt...");
    					if(isFrogPt == true && prevSpur != null && isThrown)
    						retValue = getPrevSpur();
    					else
    						retValue = getPrev();

    				} else {
    					//dir == Forward
    					//Get Next Pt...
    					//System.out.println("SegPt moveTrain() Get Next Pt...");
    					if(isFrogPt == true && nextSpur != null && isThrown)
    						retValue = getNextSpur();
    					else
    						retValue = getNext();
    				}
				}
/*
				if(getSegmentID() == 20 || getSegmentID() == 21)
					System.out.println("SegPt moveTrain() RIGHTSLANT Heading = "+heading+" dir="+dir+
						" currId="+getPointID()+" nextId="+getNext().getPointID()+" prevId="+getPrev().getPointID()+
    					" isFrogPt="+isFrogPt+" nextSpur="+nextSpur+" prevSpur="+prevSpur);
    					*/
				break;

			case LEFTSLANT:

				//NOTE: Slant tracks continue as E & W!
				if(heading == Train.Heading.W){
					//Point West (upward to the Left)
					if(dir == Loco.Direction.REVERSE) {
    					//Get Next Pt...
						//System.out.println("SegPt moveTrain() Get Next Pt...");
    					if(isFrogPt == true && nextSpur != null && isThrown)
    						retValue = getNextSpur();
    					else
    						retValue = getNext();
    				} else {
    					//dir == Forward
    					//Get Prev Pt...
    					//System.out.println("SegPt moveTrain() Get Prev Pt...");
    					if(isFrogPt == true && prevSpur != null && isThrown)
    						retValue = getPrevSpur();
    					else
    						retValue = getPrev();
    				}
				} else {
					//Point East (downward to the Right)
					if(dir == Loco.Direction.REVERSE) {
    					//Get Prev Pt...
						//System.out.println("SegPt moveTrain() Get Prev Pt...");
    					if(isFrogPt == true && prevSpur != null && isThrown)
    						retValue = getPrevSpur();
    					else
    						retValue = getPrev();
    				} else {
    					//dir == Forward
    					//Get Next Pt...
    					//System.out.println("SegPt moveTrain() Get Next Pt...");
    					if(isFrogPt == true && nextSpur != null && isThrown)
    						retValue = getNextSpur();
    					else
    						retValue = getNext();
    				}

				}
				//System.out.println("SegPt moveTrain() LEFTSLANT Heading = "+heading+" dir="+dir+
				//		" currId="+getPointID()+" nextId="+getNext().getPointID()+" prevId="+getPrev().getPointID()+
    			//		" isFrogPt="+isFrogPt+" nextSpur="+nextSpur+" prevSpur="+prevSpur);
				break;
			case SWITCHFROG:
			default:
				System.out.println("SegPt moveTrain() SWITCHFROG OR DEFAULT Heading = "+heading+" dir="+dir);
				//Don't draw...
				break;
			}
    	} //else System.out.println("SegPt moveTrain() seg=null!");

    	if(retValue == null)
    		return (this); //don't Move!
    	else if(retValue.getPointID() == NOT_SET)
    		return (this); //don't Move!

    	return (retValue);
    }
    public void setNext(SegmentPoint sPoint) {
    	next = sPoint;
    }
    public void setPrev(SegmentPoint sPoint) {
    	prev = sPoint;
    }
    public void setNextSpur(SegmentPoint sPoint) {
    	nextSpur = sPoint;
    }
    public void setPrevSpur(SegmentPoint sPoint) {
    	prevSpur = sPoint;
    }
    public void setIsFrogPt(boolean isFrog) {
    	isFrogPt = isFrog;
    }
    public void setSwitchID(int id) {
    	switchID = id;
    }
    public void setSegmentID(int id) {
    	segmentID = id;
    }
    public void setPoint(Point pt) {
    	segmentPoint = pt;
    }
    private void paintPoint(Color colorParm) {
    	if(LayoutPanel.g2d != null) {
    		LayoutPanel.g2d.setColor(colorParm);
    		LayoutPanel.g2d.drawOval(segmentPoint.x, segmentPoint.y, 3, 3);
    		LayoutPanel.g2d.fillOval(segmentPoint.x, segmentPoint.y, 3, 3);

    		LayoutPanel.setLayoutColorAndStroke();
    	}
    }
}
