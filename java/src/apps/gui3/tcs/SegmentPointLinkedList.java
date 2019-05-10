package apps.gui3.tcs;
import java.awt.Graphics2D;
import java.awt.Point;


//************************************************************************************************
//************************************************************************************************
//
//Class SegmentPointLinkedList Definition - A Java Object Class to build A Linked List of Segment Points
//
//************************************************************************************************
//************************************************************************************************
public class SegmentPointLinkedList {
	
	private SegmentPoint head;
	private SegmentPoint tail;
	private static int numNodes;

	
    public SegmentPointLinkedList() { 

       head = new SegmentPoint(-1, 0, 0, false);
       tail = head;
       
       head.setNext(null);
       head.setPrev(null);
       head.setSegmentID(-1);
    }

    public void add(int segId, int x, int y) { 
    	add(segId, x, y, false);
    }
    public void add(int segId, int x, int y, boolean isFrog) { 
    	//pseudo code: while next isn't null, walk the list
    	//once you reach the end, create a new LinkNode and add the item to it.  Then
    	//set the last LinkNode's next to this new LinkNode
    	SegmentPoint lastPoint = head;
    	
    	while(lastPoint.getNext() != null) {
    		lastPoint = lastPoint.getNext();
    	}
    	
    	SegmentPoint newPoint = new SegmentPoint(numNodes++, x, y, isFrog);
    	
    	newPoint.setNext(null);
    	lastPoint.setNext(newPoint);
    	newPoint.setPrev(lastPoint);
    	newPoint.setNextSpur(null);
    	newPoint.setPrevSpur(null);
    	newPoint.setSegmentID(segId);
    	tail = newPoint;
    }

    public SegmentPoint getPoint(int id) {
    	SegmentPoint currPoint = head;
    	boolean notFound = true;
    	
    	while(currPoint.getNext() != null && notFound) {
    		if(currPoint.getPointID() == id){
    			notFound = false;
    		}
    		else {
    			currPoint = currPoint.getNext();
    		}
    	}
    	return currPoint;
    }
    
    public SegmentPoint getPoint(Point pt) {
    	SegmentPoint currPoint = getFirstPoint();
    	int currSegId = currPoint.getSegmentID();
    	boolean notFound = true;
    	
    	int loopCt = 0;
    	while(currPoint != null && notFound && loopCt++ <= numNodes) {
    		if(currPoint.getPoint() != null){
    			if(isPointFound(currPoint.getPoint(), pt) == true){
    				notFound = false;
    				break; //Break out of while loop
    			}
    		}
    		
    		if(currPoint.getSegmentID() != currSegId) 
    			currPoint = null;
    		else
    			currPoint = currPoint.getNext();
    	}
    	return currPoint;
    }
    
    public SegmentPoint getHead() {
    	return head;
    }
    public SegmentPoint getTail() {
    	return tail;
    }
    public SegmentPoint getFirstPoint() {
    	return head.getNext();
    }
    public SegmentPoint getLastPoint() {
    	return getTail();
    }
    
    
    public void insertAfter(SegmentPoint afterSp, SegmentPoint newSp) {
  
    	if(afterSp != null && newSp != null) {
    		newSp.setSegmentID(afterSp.getSegmentID());
    		newSp.setPrev(afterSp); 
    		newSp.setNextSpur(null);
    		newSp.setPrevSpur(null);
    		
        	if(afterSp == getLastPoint() == true){
        		newSp.setNext(null);
        		tail = newSp;
        	} else {
        		newSp.setNext(afterSp.getNext()); 
        		afterSp.getNext().setPrev(newSp);
        	}
    		afterSp.setNext(newSp);
    	}
    }
    
    public void insertBefore(SegmentPoint beforeSp, SegmentPoint newSp) {
    	//insert sp after current point...
    	if(beforeSp != null && newSp != null) {
    		newSp.setSegmentID(beforeSp.getSegmentID());
    		newSp.setNextSpur(null);
    		newSp.setPrevSpur(null);
    		
    		beforeSp.getPrev().setNext(newSp);
    		newSp.setPrev(beforeSp.getPrev());
    			
    		newSp.setNext(beforeSp);
        	beforeSp.setPrev(newSp);
    	}
    }

    
    public boolean isPointFound(Point segPt, Point pt) {
    	
    	boolean isFound = (segPt.x == pt.x && segPt.y == pt.y);
    	final int BUFFER = 2;
    	
    	if(!isFound){
    		boolean isXClose = (pt.x >= segPt.x-BUFFER && pt.x <= segPt.x+BUFFER);
    		boolean isYClose = (pt.y >= segPt.y-BUFFER && pt.y <= segPt.y+BUFFER);
    		
    		if(isXClose && isYClose) isFound = true;
    	}
    	//System.out.println("SegmentPointLinkedList isPointFound - isFound="+isFound);  
    	return isFound;
    }
    
    public void connectFrogPtToSpurFirstPt(SegmentPoint frogPt){
    	connectFirstSegPtToPrevSeg(frogPt);
    }

    public void connectFirstSegPtToPrevSeg(SegmentPoint prevSegPt){
    	SegmentPoint currPoint = head;
    	currPoint = currPoint.getNext();
    	
    	if(getNumPoints() >= 1 && currPoint != null) {
    		currPoint.setPrev(prevSegPt);
    		prevSegPt.setNext(currPoint);
    	}
    }
    
    public void connectLastSegPtToNextSeg(SegmentPoint nextSegPt){
    	SegmentPoint currPoint = tail;
    	
    	if(getNumPoints() >= 1 && currPoint != null) {
    		currPoint.setNext(nextSegPt);
    		nextSegPt.setPrev(currPoint);
    	}
    }
         
    public static int getNumPoints() {
    	return(numNodes);
    }
    public int incrementNumPoints() {
    	return(numNodes++);
    }
    public void setNumPoints(int num) {
    	numNodes = num;
    }

}

