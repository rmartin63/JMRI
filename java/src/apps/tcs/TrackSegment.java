package apps.gui3.tcs;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;


//************************************************************************************************
//************************************************************************************************
//
//Class TrackSegment Definition - A Java Object Class to build A Linked List Node for a Track Segment
//
//************************************************************************************************
//************************************************************************************************
public class TrackSegment {

	public enum SegOrientation {
		VERTICAL, HORIZONTAL, RIGHTSLANT, LEFTSLANT,
	    SWITCHFROG
	}
    public SegOrientation orient;
	public boolean isOccupied;
	public Train train;

    //Layout Linked List of SegmentPoints...
  	public SegmentPointLinkedList pointsList;


	private String name;
	private int segmentID;
	private int zoneID;
	private int powerDistrict;

	private int instanceCount = 0;
    private TrackSegment next;
    private TrackSegment prev;
    private Point segmentPoint1;
    private Point segmentPoint2;

    private Color segDrawColor = LayoutPanel.defaultLayoutDrawColor;

    public TrackSegment(String sName, int id, int pt1X, int pt1Y, int pt2X, int pt2Y) {
   		name = sName;
   		segmentID = id;
   		zoneID = 0;
   		powerDistrict = 0;
   		isOccupied = false;
   		pointsList = null;
   		segmentPoint1 = new Point(pt1X, pt1Y);
   		segmentPoint2 = new Point(pt2X, pt2Y);

   		//Draw the Track Segment... NOTE g2d is NULL on Head node!
   		if(LayoutPanel.g2d != null) {
   			LayoutPanel.g2d.drawLine(pt1X, pt1Y, pt2X, pt2Y);

    		pointsList =  new SegmentPointLinkedList();

    		if(segmentID == 0) pointsList.setNumPoints(0);

    		createSegmentPoints();
    	}
    }

    public void draw() {
    	if(LayoutPanel.g2d != null) {
    		//TODO!!! -
    		LayoutPanel.g2d.setColor(segDrawColor);
    		LayoutPanel.g2d.drawLine(segmentPoint1.x, segmentPoint1.y, segmentPoint2.x, segmentPoint2.y);
    	}
    }

    public void setRouteSelectedColor(Color highlightedColor) {
    	segDrawColor = highlightedColor;
    	draw();
    }

    public void setRouteNotSelectedColor() {
    	segDrawColor = LayoutPanel.defaultLayoutDrawColor;
    	LayoutPanel.g2d.setColor(segDrawColor);
    	draw();
    }

    public void updatePoints(int pt1X, int pt1Y, int pt2X, int pt2Y) {
    	segmentPoint1 = new Point(pt1X, pt1Y);
   		segmentPoint2 = new Point(pt2X, pt2Y);

   		updateSegmentPoints();

   		draw();
    }

    public TrackSegment getNext() {
    	return (next);
    }
    public TrackSegment getPrev() {
    	return (prev);
    }
    public String getSegmentName() {
    	return (name);
    }
    public int getSegmentID() {
    	return (segmentID);
    }
    public Point getPoint1() {
    	return segmentPoint1;
    }
    public Point getPoint2() {
    	return segmentPoint2;
    }
    public SegmentPoint getPoint(int id) {
    	return pointsList.getPoint(id);
    }
    public SegmentPoint getFirstPoint() {
    	return pointsList.getFirstPoint();
    }
    public SegmentPoint getLastPoint() {
    	return pointsList.getLastPoint();
    }
    public SegmentPointLinkedList getPointsList() {
    	return pointsList;
    }
    public void setNext(TrackSegment segment) {
    	next = segment;
    }
    public void setPrev(TrackSegment segment) {
    	prev = segment;
    }

    public void connectSegmentToNextSeg(TrackSegment nextSeg){
    	SegmentPoint lastPoint = pointsList.getTail();
    	SegmentPoint nextSegFirstPoint = nextSeg.getPointsList().getFirstPoint();

    	if(lastPoint != null && nextSegFirstPoint != null) {
    		lastPoint.setNext(nextSegFirstPoint);
    		nextSegFirstPoint.setPrev(lastPoint);
    	}
    }

    public void connectEastSegToNextNorthSeg(TrackSegment nextNorthSeg){
    	SegmentPoint lastPoint = pointsList.getTail();
    	SegmentPoint nextNorthSegLastPoint = nextNorthSeg.getPointsList().getLastPoint();

    	if(lastPoint != null && nextNorthSegLastPoint != null) {
    		lastPoint.setNext(nextNorthSegLastPoint);
    		nextNorthSegLastPoint.setNext(lastPoint);
    		//System.out.println("TrackSegment connectEastSegToNextNorthSeg nextNorthSegLastPoint.next set!");
    	}
    }

    public void connectFirstSegPtToNextSegFirstSegPt(TrackSegment nextSeg){
    	SegmentPoint firstPoint = pointsList.getFirstPoint();
    	SegmentPoint nextSegFirstPoint = nextSeg.getPointsList().getFirstPoint();

    	if(firstPoint != null && nextSegFirstPoint != null) {
    		firstPoint.setPrev(nextSegFirstPoint);
    		nextSegFirstPoint.setPrev(firstPoint);
    	}

    }

    public void connectSegmentToPrevSeg(TrackSegment prevSeg){
    	SegmentPoint firstPoint = pointsList.getFirstPoint();
    	SegmentPoint prevSegLastPoint = prevSeg.getPointsList().getLastPoint();

    	if(firstPoint != null && prevSegLastPoint != null) {
    		prevSegLastPoint.setNext(firstPoint);
    		firstPoint.setPrev(prevSegLastPoint);
    	}
    }

    public SegmentPoint getSegmentPoint(Point pt) {
    	SegmentPoint ret = null;

    	if(isPointInSegment(pt) == true) {
    		//System.out.println("getSegmentPoint in if isPointInSegment(pt) == true");
    		ret = pointsList.getPoint(pt);
    	}
    	return (ret);
    }

    public void setTrain(Train t) {
    	//if (t != null) System.out.println("TrackSegment:setTrain seg="+name+" train != null!");
    	//else System.out.println("TrackSegment:setTrain seg="+name+" train == null!");

    	train = t;
    	isOccupied = (t != null);
    }

    public boolean isPointInSegment(Point pt) {
    	boolean retValue = false;
    	final int BUF = 2;
    	//m=(y1 - y0)/(x1 - x0)
    	//NOTE: Could not use m as a double - when I did, it always resulted in m=0!
    	//BIG NOTE: Below - I have to multiple by -1 on y difference since a screen's vertical pixel count increases
    	//          as you move down compared to a Y-axis in Math!!!
    	//float m = (float)(-1)*(segmentPoint2.y - segmentPoint1.y) / (segmentPoint2.x - segmentPoint1.x);

    	// (y - y0) = m(x - x0)
    	// pt.y-segmentPoint1.y = m*(pt.x - segmentPoint1.x)
    	// If sides are equal then point pt is on the line!
    	//retValue = ((pt.y-segmentPoint1.y) == (m*(pt.x - segmentPoint1.x)));

    	if(segmentPoint1.y == segmentPoint2.y){
    		//Vertical
    		retValue = ((segmentPoint1.y-BUF < pt.y) && (pt.y <= segmentPoint1.y+BUF));
    	} else if(segmentPoint1.x == segmentPoint2.x){
    		//horizontal
    		retValue = ((segmentPoint1.x-BUF < pt.x) && (pt.x <= segmentPoint1.x+BUF));
    	} else {
    		//SLANT
    		retValue = (((segmentPoint1.x-BUF < pt.x) && (pt.x <= segmentPoint2.x+BUF)) &&
    					((segmentPoint1.y-BUF < pt.y) && (pt.y <= segmentPoint2.y+BUF)));
    	}

    	//System.out.println("isPointInSegment - segID="+segmentID+" retValue="+retValue + " pt="+pt);
    	return (retValue);
    }

    public SegmentPoint insertFrogPoint(SwitchSegment sw) {
    	//First test if frog point is already in list of SegmentPoints; only add if not found
    	Point frog = sw.getFrogPoint();
    	SegmentPoint retSp = pointsList.getPoint(frog);

      	if(retSp == null) {

	    	SegmentPoint newPoint = new SegmentPoint(pointsList.incrementNumPoints(), frog.x, frog.y, true);

	    	SegmentPoint sp            = pointsList.getFirstPoint();
	    	int currSegID              = sp.getSegmentID();
	    	SegmentPoint first         = pointsList.getFirstPoint();
	    	SegmentPoint last          = pointsList.getLastPoint();
	    	SegmentPoint nextSp        = sp.getNext();
	    	boolean isAscendingOrder   = true;

	    	SegmentPoint insertAfterSp = last;

	    	if(first.getPointX() >= last.getPointX() && first.getPointY() >= last.getPointY())
	    		isAscendingOrder = false;

	    	//System.out.println("insertFrogPoint BEFORE While frog="+frog+" currSegID="+currSegID+" isAscendingOrder="+
	    		//isAscendingOrder);

	    	//Find where to insert the new frog point...
	    	while (sp != last) {
	    		if(isAscendingOrder == true) {
	    			if((orient == SegOrientation.VERTICAL && sp.getPointY() <= frog.y && nextSp.getPointY() >= frog.y) ||
	    					(nextSp == null)){
	    				insertAfterSp = sp;
	    				break;
	    			} else if(orient != SegOrientation.VERTICAL && sp.getPointX() <= frog.x && nextSp.getPointX() >= frog.x) {
	    				insertAfterSp = sp;
	    				break;
	    			}
	    		} else { //isAscendingOrder != true
	    			if((orient == SegOrientation.VERTICAL && sp.getPointY() >= frog.y && nextSp.getPointY() <= frog.y) ||
	    					(nextSp == null)){
	    				insertAfterSp = sp;
	    				break;
	    			} else if(orient != SegOrientation.VERTICAL && sp.getPointX() >= frog.x && nextSp.getPointX() <= frog.x) {
	    				insertAfterSp = sp;
	    				break;
	    			}
	    		}
	    		//System.out.println("insertFrogPoint bottom of while loop  frog="+frog+" sp="+sp.getPoint()+
		    		//	" nextSp="+nextSp.getPoint()+" insertAfterSp="+insertAfterSp);
	    		sp = sp.getNext();
	    		nextSp = nextSp.getNext();

	    		if(sp.getSegmentID() != currSegID) break;
	    	}
	    	//System.out.println("insertFrogPoint AFTER While frog="+frog+" currSegID="+currSegID+" insertAfterSpID="+
	    		//	insertAfterSp.getPointID());
	    	pointsList.insertAfter(insertAfterSp, newPoint);

	    	FrogInfo frogInfo = sw.getFrog();
	    	newPoint.setSwitchID(frogInfo.getSwitchID());
	    	retSp = newPoint;

    	} else {
    		//Frog Point already exists
    		retSp.setIsFrogPt(true);
    		FrogInfo frogInfo = sw.getFrog();
    		retSp.setSwitchID(frogInfo.getSwitchID());
    	}
    	return (retSp);
    }

    public void printSegment() {
    	String nextS = "null";
    	String nextIdS = "null";
    	if(next != null){
    		nextS = next.name;
    		nextIdS = Integer.toString(next.segmentID);
    	}
    	String prevS = "null";
    	String prevIdS = "null";
        if(prev != null){
    		prevS = prev.name;
    		prevIdS = Integer.toString(prev.segmentID);
    	}

    	System.out.println("\n****\nTrackSegment ["+name+"], segID="+segmentID+" Orient="+orient+" s1x="+
    			segmentPoint1.x+" s1y="+segmentPoint1.y+" s2x="+segmentPoint2.x+" s2y="+segmentPoint2.y+
    			" prev.name="+prevS+" prevID="+prevIdS+" next.name="+nextS+" nextID="+nextIdS);

    	SegmentPoint sp = pointsList.getFirstPoint();

    	int i = 0;
    	while (sp != null){

    		SegmentPoint nextSp = sp.getNext();
    		String nextId = "null";
    		if(nextSp != null) {
    			nextId = Integer.toString(nextSp.getPointID());
    		}

    		SegmentPoint prevSp = sp.getPrev();
    		String prevId = "null";
    		if(prevSp != null) {
    			prevId = Integer.toString(prevSp.getPointID());
    		}

    		SegmentPoint spurNextSp = sp.getNextSpur();
    		String spurNextId = "null";
    		if(spurNextSp != null) {
    			spurNextId = Integer.toString(spurNextSp.getPointID());
    		}

    		SegmentPoint spurPrevSp = sp.getPrevSpur();
    		String spurPrevId = "null";
    		if(spurPrevSp != null) {
    			spurPrevId = Integer.toString(spurPrevSp.getPointID());
    		}

    		if(sp != null)
    			System.out.println("SegPt"+i+" id="+sp.getPointID()+" prevId="+prevId+" nextId="+nextId+
    					" spurNextId="+spurNextId+" spurPrevId="+spurPrevId+" isFrogPt="+sp.getIsFrogPt()+" switchID="+sp.getSwitchID()+
    					" sPTx="+sp.getPoint().x+" sPTy="+sp.getPoint().y);
    		else System.out.println("SegPt"+i+" id= SP = NULL!!!");

    		sp = sp.getNext();
    		i+=1;
    		if(sp != null)
    			if(sp.getSegmentID() != segmentID || i > 300)
    				sp = null; //end loop!
    	}
    }

    public void flipSegEndPoints() {
    	Point tempSegPt = segmentPoint1;
    	segmentPoint1 = segmentPoint2;
    	segmentPoint2 = tempSegPt;

    	updateSegmentPoints();
    }

    public void processTurntableRotate(int pos, TrackSegment nextSeg, TrackSegment prevSeg) {
    	//AT default pos = 0: Track segpoint =>  First is to left and last is to the right
    	//Also NextSeg is to the right and prevSeg is to the left.

    	//NOTE: Remove newPos parm if not needed!

    	SegmentPoint firstPoint = getFirstPoint();
    	SegmentPoint lastPoint = getLastPoint();

    	if(firstPoint != null && prevSeg != null) {
    		SegmentPoint prevSegLastPoint = prevSeg.getLastPoint();
    	    firstPoint.setPrev(prevSegLastPoint);
    	    prevSegLastPoint.setNext(firstPoint);
    	} else {
    		firstPoint.setPrev(null);
    	}

    	if(lastPoint != null && nextSeg != null) {
    		SegmentPoint nextSegFirstPoint = nextSeg.getFirstPoint();
    		lastPoint.setNext(nextSegFirstPoint);
    		nextSegFirstPoint.setPrev(lastPoint);
    	}else{
    		lastPoint.setNext(null);
    	}

    }

    public void createSegmentPoints() {
    	int x0 = segmentPoint1.x;
    	int y0 = segmentPoint1.y;
    	int x1 = segmentPoint2.x;
    	int y1 = segmentPoint2.y;

    	//VERTICAL, HORTIZONTAL, RIGHTSLANT, LEFTSLANT, SWITCHFLOG
    	orient = SegOrientation.VERTICAL;

    	if(x0 == x1) {
    		//Vertical Line Segment!
    		orient = SegOrientation.VERTICAL;
    		if(y0 < y1) {
	    		for(int i = y0; i <= y1; i+=15){
	    			//System.out.println("Seg="+segmentID+" createSegmentPoints VERT - SegName="+name + " numPts="+pointsList.getNumPoints()+
	    			//		" SP1x="+x0+" SP1y="+y0+" SP2x="+x1+" SP2y="+y1+" x="+x0+" y="+i);
	    			pointsList.add(segmentID, x0, i);
	    		}
    		} else {
    			for(int i = y0; i >= y1; i-=15){
	    			//System.out.println("Seg="+segmentID+" createSegmentPoints VERT - SegName="+name + " numPts="+pointsList.getNumPoints()+
	    			//		" SP1x="+x0+" SP1y="+y0+" SP2x="+x1+" SP2y="+y1+" x="+x0+" y="+i);
	    			pointsList.add(segmentID, x0, i);
	    		}
    		}
    	}
    	else if (y0 == y1) {
    		//Horizontal Line Segment!
    		orient = SegOrientation.HORIZONTAL;
    		if(x0 < x1) {
	    		for(int i = x0; i <= x1; i+=15){
	    			//System.out.println("Seg="+segmentID+" createSegmentPoints HORT - SegName="+name + " numPts="+pointsList.getNumPoints()+
	    			//		" SP1x="+x0+" SP1y="+y0+" SP2x="+x1+" SP2y="+y1+" x="+i+" y="+y0);
	    			pointsList.add(segmentID, i, y0);
	    		}
    		} else {
    			for(int i = x0; i >= x1; i-=15){
	    			//System.out.println("Seg="+segmentID+" createSegmentPoints VERT - SegName="+name + " numPts="+pointsList.getNumPoints()+
	    			//		" SP1x="+x0+" SP1y="+y0+" SP2x="+x1+" SP2y="+y1+" x="+x0+" y="+i);
	    			pointsList.add(segmentID, i, y0);
	    		}
    		}
    	}
    	else {
    		//Curve (slant) line Segment! - Test Slope...
    		int pointX = segmentPoint1.x;
    		if(getSlopeOfSegment() > 0) {
    			//Positive Slope mean a RIGHTSLANT!
    			orient = SegOrientation.RIGHTSLANT;
    			if(y0 < y1) {
    	    		for(int i = y0; i <= y1; i+=15){
	    				pointX = LayoutPanel.getXOnLine(x0, y0, x1, y1, i);
	        			pointsList.add(segmentID, pointX, i);
    	    		}
    			} else {
        			for(int i = y0; i >= y1; i-=15){
        				pointX = LayoutPanel.getXOnLine(x0, y0, x1, y1, i);
    	    			pointsList.add(segmentID, pointX, i);
    	    		}
        		}
    			if(pointsList.getPoint(new Point(x1, y1)) == null)
    				pointsList.add(segmentID, x1, y1);
    		}
    		else {
    			//Negative slope means a LEFTSLANT!
    			orient = SegOrientation.LEFTSLANT;
    			if(y0 < y1) {
	    			for(int i = y0; i <= y1; i+=15){
	    				pointX = LayoutPanel.getXOnLine(x0, y0, x1, y1, i);
	        			pointsList.add(segmentID, pointX, i);
	        		}
    			} else {
        			for(int i = y0; i >= y1; i-=15){
        				pointX = LayoutPanel.getXOnLine(x0, y0, x1, y1, i);
    	    			pointsList.add(segmentID, pointX, i);
    	    		}
    			}
    			if(pointsList.getPoint(new Point(x1, y1)) == null)
    				pointsList.add(segmentID, x1, y1);
    		}
    	}
    }

    public void updateSegmentPoints() {
    	int x0 = segmentPoint1.x;
    	int y0 = segmentPoint1.y;
    	int x1 = segmentPoint2.x;
    	int y1 = segmentPoint2.y;

    	//Used below for calculating the SLANT lines!
		int diffX = Math.abs(x1-x0);
		int diffY = Math.abs(y1-y0);

		//Save off the current Orient to compare at the end...
		SegOrientation prevOrient = orient;

    	//VERTICAL, HORTIZONTAL, RIGHTSLANT, LEFTSLANT, SWITCHFLOG
    	orient = SegOrientation.VERTICAL;

    	SegmentPoint sp = pointsList.getFirstPoint();

    	if(x0 == x1) {
    		//Vertical Line Segment!
    		orient = SegOrientation.VERTICAL;
    		if(y0 < y1) {
	    		for(int i = y0; i <= y1; i+=15){
	    			if(sp != null) {
	    				sp.setPoint(new Point(x0, i));
	    			} else break;
	    			if(sp == pointsList.getLastPoint())
	    				break;
	    			else
	    				sp = sp.getNext();
	    		}
    		} else {
    			for(int i = y0; i >= y1; i-=15){
	    			if(sp != null) {
	    				sp.setPoint(new Point(x0, i));
	    			} else break;
	    			if(sp == pointsList.getLastPoint())
	    				break;
	    			else
	    				sp = sp.getNext();
	    		}
    		}
    	}
    	else if (y0 == y1) {
    		//Horizontal Line Segment! & SLants
    		orient = SegOrientation.HORIZONTAL;
    		if(x0 < x1) {
	    		for(int i = x0; i <= x1; i+=15){
	    			if(sp != null) {
	    				sp.setPoint(new Point(i, y0));
	    			} else break;
	    			if(sp == pointsList.getLastPoint())
	    				break;
	    			else
	    				sp = sp.getNext();
	    		}
    		} else {
    			for(int i = x0; i >= x1; i-=15){
	    			if(sp != null) {
	    				sp.setPoint(new Point(i, y0));
	    			} else break;
	    			if(sp == pointsList.getLastPoint())
	    				break;
	    			else
	    				sp = sp.getNext();
	    		}
    		}
    	}
    	else {
    		//Curve (slant) line Segment! - Test Slope...
    		int pointX = segmentPoint1.x;
    		int pointY = segmentPoint1.y;
    		if(getSlopeOfSegment() > 0) {
    			//Positive Slope mean a RIGHTSLANT!
    			orient = SegOrientation.RIGHTSLANT;
    			if(diffX > diffY) {
	    			if(x0 < x1) {
	    	    		for(int i = x0; i <= x1; i+=15){
		    				pointY = LayoutPanel.getYOnLine(x0, y0, x1, y1, i);
		        			if(sp != null) {
			    				if(pointY > y1) sp.setPoint(new Point(x1, y1));
		        				else sp.setPoint(new Point(i, pointY));
			    			} else break;
			    			if(sp == pointsList.getLastPoint()) break;
			    			else {
			    				sp = sp.getNext();
			    				//Set by default to last point in case it falls out of the for loop
			    				sp.setPoint(new Point(x1, y1));
			    			}
	    	    		}
	    			} else {
	        			for(int i = x0; i >= x1; i-=15){
	        				pointY = LayoutPanel.getYOnLine(x0, y0, x1, y1, i);
		        			if(sp != null) {
		        				if(pointY > y1) sp.setPoint(new Point(x1, y1));
		        				else sp.setPoint(new Point(i, pointY));
			    			} else break;
			    			if(sp == pointsList.getLastPoint())
			    				break;
			    			else {
			    				sp = sp.getNext();
			    				//Set by default to last point in case it falls out of the for loop
			    				sp.setPoint(new Point(x1, y1));
			    			}
	    	    		}
	        		}
    			} else { //diffY > diffX
    				if(y0 < y1) {
	    	    		for(int i = y0; i <= y1; i+=15){
		    				pointX = LayoutPanel.getXOnLine(x0, y0, x1, y1, i);
		        			if(sp != null) {
			    				if(pointX < x1) {
			    					sp.setPoint(new Point(x1, y1));
			    				}
		        				else {
		        					sp.setPoint(new Point(pointX, i));
		        				}
			    			} else break;
			    			if(sp == pointsList.getLastPoint()) break;
			    			else {
			    				sp = sp.getNext();
			    				//Set by default to last point in case it falls out of the for loop
			    				sp.setPoint(new Point(x1, y1));
			    			}
	    	    		}
	    			} else {
	        			for(int i = y0; i >= y1; i-=15){
	        				pointX = LayoutPanel.getXOnLine(x0, y0, x1, y1, i);
		        			if(sp != null) {
			    				if(pointX < x1) {
			    					sp.setPoint(new Point(x1, y1));
			    				}
		        				else {
		        					sp.setPoint(new Point(pointX, i));
		        				}
			    			} else break;
			    			if(sp == pointsList.getLastPoint()) break;
			    			else {
			    				sp = sp.getNext();
			    				//Set by default to last point in case it falls out of the for loop
			    				sp.setPoint(new Point(x1, y1));
			    			}
	    	    		}
	        		}
    			}
    			if(sp != pointsList.getLastPoint()) {
    				SegmentPoint last = pointsList.getLastPoint();
    				while(sp != null && sp != last) {
    					sp.setPoint(new Point(x1, y1));
    					sp = sp.getNext();
    				}
    			}
    		}
    		else {
    			//Negative slope means a LEFTSLANT!
    			orient = SegOrientation.LEFTSLANT;
    			if(diffX > diffY) {
	    			if(x0 < x1) {
		    			for(int i = x0; i <= x1; i+=15){
		    				pointY = LayoutPanel.getYOnLine(x0, y0, x1, y1, i);
		        			if(sp != null) {
		        				if(pointY > y1)
		        					sp.setPoint(new Point(x1, y1));
		        				else
		        					sp.setPoint(new Point(i, pointY));
			    			} else break;

			    			if(sp == pointsList.getLastPoint()) break;
			    			else {
			    				sp = sp.getNext();
			    				//Set by default to last point in case it falls out of the for loop
			    				sp.setPoint(new Point(x1, y1));
			    			}
		        		}
	    			} else {
		    			for(int i = x0; i >= x1; i-=15){
		    				pointY = LayoutPanel.getYOnLine(x0, y0, x1, y1, i);
		        			if(sp != null) {
		        				if(pointY < y1)
		        					sp.setPoint(new Point(x1, y1));
		        				else
		        					sp.setPoint(new Point(i, pointY));
			    			} else break;

			    			if(sp == pointsList.getLastPoint()) break;
			    			else {
			    				sp = sp.getNext();
			    				//Set by default to last point in case it falls out of the for loop
			    				sp.setPoint(new Point(x1, y1));
			    			}
		        		}
	    			}
    			} else {  //diffY > diffX
	    			if(y0 < y1) {
		    			for(int i = y0; i <= y1; i+=15){
		    				pointX = LayoutPanel.getXOnLine(x0, y0, x1, y1, i);
		        			if(sp != null) {
			    				if(pointX > x1)
			    					sp.setPoint(new Point(x1, y1));
		        				else
		        					sp.setPoint(new Point(pointX, i));
			    			} else break;

			    			if(sp == pointsList.getLastPoint()) break;
			    			else {
			    				sp = sp.getNext();
			    				//Set by default to last point in case it falls out of the for loop
			    				sp.setPoint(new Point(x1, y1));
			    			}
		        		}
	    			} else {
	        			for(int i = y0; i >= y1; i-=15){
	        				pointX = LayoutPanel.getXOnLine(x0, y0, x1, y1, i);
		        			if(sp != null) {
			    				sp.setPoint(new Point(pointX, i));
			    				if(pointX < x1) sp.setPoint(new Point(x1, y1));
		        				else sp.setPoint(new Point(pointX, i));
			    			} else break;
			    			if(sp == pointsList.getLastPoint())
			    				break;
			    			else {
			    				sp = sp.getNext();
			    				//Set by default to last point in case it falls out of the for loop
			    				sp.setPoint(new Point(x1, y1));
			    			}
	    	    		}
	    			}
    			}
    			if(sp != pointsList.getLastPoint()) {
    				SegmentPoint last = pointsList.getLastPoint();
    				while(sp != null && sp != last) {
    					sp.setPoint(new Point(x1, y1));
    					sp = sp.getNext();
    				}
    			}
    		}
    	}

    	//Test if the Segment Orient has changed and if so, adjust the heading as needed...
    	if(prevOrient != orient) {
    		System.out.println("updateSegmentPoints() prevOrient="+prevOrient+" orient="+orient);

    		if(train != null)
    			train.changeHeading(prevOrient);
    		else System.out.println("updateSegmentPoints() train=null");
    	}
    }

    private float getSlopeOfSegment() {
    	int x0 = segmentPoint1.x;
    	int y0 = segmentPoint1.y;
    	int x1 = segmentPoint2.x;
    	int y1 = segmentPoint2.y;
    	//m=(y1 - y0)/(x1 - x0)
    	//NOTE: Could not use m as a double - when I did, it always resulted in m=0!
    	//BIG NOTE: Below - I have to multiple by -1 on y difference since a screen's vertical pixel count increases
    	//          as you move down compared to a Y-axis in Math!!!
    	float m = (float)(-1)*(y1 - y0) / (x1 - x0);
    	return (m);
    }
}