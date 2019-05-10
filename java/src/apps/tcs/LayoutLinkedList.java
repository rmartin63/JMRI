package apps.gui3.tcs;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;


//************************************************************************************************
//************************************************************************************************
//
//Class LayoutLinkedList Definition - A Java Object Class to build A Linked List
//
//************************************************************************************************
//************************************************************************************************
public class LayoutLinkedList {

	private static TrackSegment head;
	private static TrackSegment tail;

	private static int numNodes = 0;

	private static LayoutLinkedList singleton = new LayoutLinkedList( );


    private LayoutLinkedList() {

       head = new TrackSegment("HEAD", -1, 0, 0, 0, 0);
       tail = head;
       head.setNext(null);
       head.setPrev(null);

       numNodes = 0;
    }

    //Static 'instance' method
    public static LayoutLinkedList getInstance( ) {
       return singleton;
    }

    public TrackSegment add(String sName, int pt1X, int pt1Y, int pt2X, int pt2Y) {
    	//pseudo code: while next isn't null, walk the list
    	//once you reach the end, create a new LinkNode and add the item to it.  Then
    	//set the last LinkNode's next to this new LinkNode
    	TrackSegment lastSegment = head;

    	while(lastSegment.getNext() != null) {
    		lastSegment = lastSegment.getNext();
    	}

    	TrackSegment newSegment = new TrackSegment(sName, numNodes++, pt1X, pt1Y, pt2X, pt2Y);

    	newSegment.setNext(null);
    	lastSegment.setNext(newSegment);
    	newSegment.setPrev(lastSegment);
    	tail = newSegment;

    	LayoutPanel.setLayoutColorAndStroke();

    	//System.out.println("\nLayoutLinkedList  listSize="+getListSize()+" tail="+tail.getSegmentName());

    	return newSegment;
    }

    public void resetList() {

    	//System.out.println("\n********\nLayoutLinkedList - resetList()...\n\n");
    	head.setNext(null);
        head.setPrev(null);
        tail = null;

        numNodes = 0;
    }

    public void updatePoints(String sName, int pt1X, int pt1Y, int pt2X, int pt2Y) {
    	TrackSegment track = getSegment(sName);
    	if(track != null){
    		track.updatePoints(pt1X, pt1Y, pt2X, pt2Y);
    	}
    }

    public TrackSegment getHead() {
    	return head;
    }
    public TrackSegment getTail() {
    	return tail;
    }
    public TrackSegment getFirstNode() {
    	return head.getNext();
    }
    public TrackSegment getLastNode() {
    	return getTail();
    }

    public static TrackSegment getSegment(String sName) {
    	TrackSegment currSegment = head;
    	boolean found = false;
    	//System.out.println("\nLayoutLinkedList  listSize="+singleton.getListSize());
    	while(currSegment != null && found == false) {
    		//System.out.println("LayoutLinkedList - Curr SegName="+currSegment.getSegmentName()+" SEARCHING FOR="+sName);
    		if(currSegment.getSegmentName().equals(sName) == true){
    			found = true;
    		}
    		else {
    			currSegment = currSegment.getNext();
    		}
    	}
    	if(found == false) {
    		String ptr = "null";
    		if(currSegment != null) ptr = "currSegment != null";
    		//System.out.println("LayoutLinkedList - SegName="+sName + "ptr=null?="+ptr+" ** !Found - Returning null!\n");
    		return null;
    	}
    	else {
    		//System.out.println("LayoutLinkedList - SegName="+sName + " **Found!!!!\n");
    		return currSegment;
    	}
    }
    public TrackSegment getSegment(int id) {
    	TrackSegment currSegment = head;
    	boolean notFound = true;

    	while(currSegment != null && notFound) {
    		if(currSegment.getSegmentID() == id){
    			notFound = false;
    		}
    		else {
    			currSegment = currSegment.getNext();
    		}
    	}
    	if(notFound) {
    		return null;
    	}
    	else {
    		return currSegment;
    	}
    }

    //Return Track Segment that contains the point!
    public TrackSegment getSegment(Point pt) {
    	TrackSegment currSegment = head;
    	boolean notFound = true;

    	while(currSegment != null && notFound) {
    		double dist = Line2D.ptSegDist(currSegment.getPoint1().x, currSegment.getPoint1().y,
    				currSegment.getPoint2().x, currSegment.getPoint2().y, pt.x, pt.y);
    		//System.out.println("getSegment for mouse pt dist="+dist);
    		if(dist <= 2){
    			notFound = false;
    		}
    		else {
    			currSegment = currSegment.getNext();
    		}
    	}
    	if(notFound) {
    		return null;
    	}
    	else {
    		return currSegment;
    	}
    }

    public int getListSize() {
    	return numNodes;
    }

    public void printList() {
    	TrackSegment currSegment = head;
    	TrackSegment prevSeg = currSegment.getPrev();
    	String prevSegName = "null";
    	if(prevSeg != null) prevSegName = prevSeg.getSegmentName();
    	TrackSegment nextSeg = currSegment.getNext();
    	String nextSegName = "null";
    	if(nextSeg != null) nextSegName = nextSeg.getSegmentName();

    	System.out.println("\n****\nTrackSegment - "+ currSegment.getSegmentName()+ " id="+
				currSegment.getSegmentID() + "   Prev=["+prevSegName+ "]   Next=["+nextSegName+"]");
    	while(currSegment.getNext() != null) {
    		currSegment = currSegment.getNext();

    		prevSeg = currSegment.getPrev();
    		prevSegName = "null";
        	if(prevSeg != null) prevSegName = prevSeg.getSegmentName();
        	nextSeg = currSegment.getNext();
        	nextSegName = "null";
        	if(nextSeg != null) nextSegName = nextSeg.getSegmentName();

    		System.out.println("TrackSegment - "+ currSegment.getSegmentName()+ " id="+
    				currSegment.getSegmentID() + "   Prev=["+prevSegName+ "]   Next=["+nextSegName+"]");

    	}
    	System.out.println("****\n");
    }
}
