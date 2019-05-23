package apps.gui3.tcs;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

//import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference.Elements;

//************************************************************************************************
//************************************************************************************************
//
//Class LayoutPanel Definition - A Java Object Class to build the Layout Outline GUI
//
//************************************************************************************************
//************************************************************************************************

//************************************************************************************************
//                   NOTES ON CONVERTING BETWEEn STUB & LIVE LAYOUT
//************************************************************************************************
// 1. Search "CHANGE #1..." below and toggle between LocoNetAPI & LocoNetAPI_STUB
// 2. In SwitchSegment - Search "CHANGE #2" and toggle between lnAPI & lnAPI_S.
//
//************************************************************************************************

public class LayoutPanel extends JPanel {

	private static LayoutPanel _instance = null;  //A singleton class!

	public static Graphics2D g2d;

	public static boolean layoutConnected = false;

	final public static Color defaultLayoutDrawColor = new Color(156,215,245); //Default LayoutPanel Draw Color

	public static Dimension pSize = null;

	//Layout Linked List of TrackSegments...
	public static LayoutLinkedList layoutList = null;

	public static ArrayList <SwitchSegment> switchList = new ArrayList <SwitchSegment>();

	public static LocoNetAPI lnAPI = null;
	
	public static RouteManager rMgr = null; //Route Manager...

	public static int refreshedSwId;

	private Point mousePoint = null;
	private Point lastMousePoint = null;

	private TrackSegment lastRouteSeg = null;

	private final int NOT_SET = -1;

	//Turntable...
	public static Turntable turnTable = null;

	//Draw Classes...
	LayoutPanelDrawYard drawYard = null;
	LayoutPanelDrawLocoYard drawLocoYard = null;
	LayoutPanelDrawNorth drawNorth = null;
	LayoutPanelDrawMid drawMid = null;
	LayoutPanelDrawMidUpper drawMidUpper = null;
	LayoutPanelDrawEast drawEast = null;
	LayoutPanelDrawSouth drawSouth = null;
	LayoutPanelDrawWest drawWest = null;


	MouseListener ml = new MouseListener(){
		public void mouseClicked(MouseEvent e) {
			mousePoint = e.getPoint();
			//System.out.println("\n*************************\nmouseClicked  Point="+mousePoint+" lastMousePoint="+lastMousePoint+
			//		" swListSize="+switchList.size());

			if(mousePoint != lastMousePoint) {
				boolean isRouteDialogOpen = false;
				if(rMgr != null && layoutList != null)
					isRouteDialogOpen = rMgr.getManagedForCreateEdit();

				if(!isRouteDialogOpen) {
					if(isMouseClickOnTurntable(mousePoint) == true) {
						if(turnTable != null){
							turnTable.processMouseClick(mousePoint);

							lastMousePoint = mousePoint;
						}
						lastRouteSeg = null;
					} else if (isMouseClickOnSwitch(mousePoint) == true){
						//Check for click on a switch
						lastMousePoint = mousePoint;
						lastRouteSeg = null;
					} else { //Route not Open - See if click is on Segment for debug
						TrackSegment seg = isMouseClickOnSegment(mousePoint);
						if(seg != null) {
							//Segment found!!
							System.out.println("Seg Found - "+seg.getSegmentName());
						}
					}
				} else {
						//Process Route Clicks first if the Route Create/Edit Dialog is open
						if(rMgr.getManagedForCreateEdit() == true) {
							//Check if chick was on a segment first...
							TrackSegment seg = isMouseClickOnSegment(mousePoint);
							if(seg != null) {

								if(lastRouteSeg != seg) {
									//Segment found!!
									lastRouteSeg = seg;

									System.out.println("Seg Found - "+seg.getSegmentName());
									rMgr.addSegment(true, seg.getSegmentID(), seg.getSegmentName(), -1, "");
								}
							} else {
								//*******************
								// TODO!!!
								// NOTE: THIS MAY NOT BE NEEDED SINCE Segment cover all track and Switch are
								//       only an overlay!!
								//*******************
								//Seg is null if mouse click was not on a segment
								//Then check if click was on a switch
								for(int i = 0; i < switchList.size(); i++) {
									if (switchList.get(i).isSwitchFound(mousePoint)){
										break;
									}
								}
							}
						} else {
							lastRouteSeg = null;
						}
						lastMousePoint = mousePoint;
					} // else isRouteDialogOpen
			} //if(mousePoint != lastMousePoint)
		}
		public void mousePressed(MouseEvent e) {
			//mousePoint = e.getPoint();
	    	//System.out.println("LayoutPanel mousePressed - x="+mousePoint.x+" y="+mousePoint.y);
	    }
	    public void mouseReleased(MouseEvent e) {/*System.out.println("mouseReleased");*/}
	    public void mouseExited(MouseEvent e) {/*System.out.println("\n\nmouseExited\n\n");*/}
	    public void mouseEntered(MouseEvent e) {/*System.out.println("mouseEntered");*/}
	};


    private Dimension  prevPanelSize = new Dimension(800, 600);
    private boolean    hasPanelSizeChanged;
	private int        count = 0;

	protected LayoutPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
        int red   =  9;
        int green = 45;
        int blue  = 90;
        Color myBlue = new Color(red,green,blue);
        setBackground(myBlue);
        Dimension panelD = new Dimension(800,700);
        setPreferredSize(panelD);
        setLayout(null);

		String value = System.getenv("TCS_LAYOUT_CONNECTED");
		System.out.println("\n**************\nTCS_LAYOUT_CONNECTED="+value+"\n**************\n");

		if(value != null) { 
		    if(value.equals("YES") || value.equals("Yes"))
		        layoutConnected = true;
		}

        //MouseListener for finding points for debug and switch placement
        addMouseListener(ml);

        layoutList = LayoutLinkedList.getInstance();
    }

	public static LayoutPanel getInstance() {
		if(_instance == null) {
			_instance = new LayoutPanel();
		}
		return _instance;
	}


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawLayout(g);
    }

	private void drawLayout(Graphics g) {
		g2d = (Graphics2D) g;

        //Set Color and Stroke for drawing Bench Work...
		setLayoutColorAndStroke();

		if(pSize == null)
			pSize = new Dimension(800, 600); //Panel Size


		if(lnAPI == null) {
            lnAPI = LocoNetAPI.getInstance();
        }

		if(rMgr == null) {
			 rMgr = RouteManager.getInstance();
		}

        pSize = getSize();

       	//System.out.println("LayoutPanel psize="+pSize+" prevPanelSize="+prevPanelSize);
       	if((pSize.width != prevPanelSize.width) || (pSize.height != prevPanelSize.height)) {
       		hasPanelSizeChanged = true;
       	} else {
       		hasPanelSizeChanged = false;
       	}

		//Create the draw objects, if needed...
       	if(drawWest == null) drawWest = LayoutPanelDrawWest.getInstance();
       	if(drawSouth == null) drawSouth = LayoutPanelDrawSouth.getInstance();
       	if(drawNorth == null) drawNorth = LayoutPanelDrawNorth.getInstance();
       	if(drawEast == null) drawEast = LayoutPanelDrawEast.getInstance();
		if(drawMid == null) drawMid = LayoutPanelDrawMid.getInstance();
		if(drawMidUpper == null) drawMidUpper = LayoutPanelDrawMidUpper.getInstance();
		if(drawYard == null) drawYard = LayoutPanelDrawYard.getInstance();
		if(drawLocoYard == null) drawLocoYard = LayoutPanelDrawLocoYard.getInstance();

		//Initialize Points That Other Draw Areas Depend On...
		drawNorth.initializePoints();
		drawSouth.initializePoints();
		drawEast.initializePoints();
		drawMidUpper.initializePoints();

		//********************************************************
		// Do the Drawing!
		//********************************************************

		//Test if relative Screen points have change - if so, force a reset...
		boolean hasSegChanged = false;
		if(drawMid != null && drawEast !=null) {
			hasSegmentPointChanged("Mid-OuterTopML Right Angle To North", drawMid.getmidOuterTopRightX(), drawMid.getmidOuterTopRightY(), drawEast.geteastTopLeftSLToMidTopOuterX(), drawEast.geteastTopLeftSLToMidTopOuterBotY());
		}

		//if(hasPanelSizeChanged == true || hasSegChanged == true) {
		if(hasPanelSizeChanged == true) {
			layoutList.resetList();  //Remove List nodes and recreate!
			System.out.println("\n**************\nLayoutPanel PanelSizeChanged!!"+
								"LayoutPanel psize="+pSize+" prevPanelSize="+prevPanelSize);
			if(turnTable != null) {
				turnTable.unManage();
			}
			turnTable = null; //Force a create of the turntable...

			//Unmanage Switch Frog Buttons on a re-size!
			unmanageFrogButtons();

		} else {

		    String value = System.getenv("TCS_AT_HOME");
	        System.out.println("\n**************\nTCS_AT_HOME="+value+"\n**************\n");
	        
	        //Disable while at work...
	        
	        if(value != null) {
	            if(value.equals("YES") || value.equals("Yes")) {
        	        //Draw Main West (by pass-thru bridge)...
        	        drawWest.draw();
        
        	        //Draw Main South (by docks)...
        	        drawSouth.draw();
        
        	        //Draw Main North (Mainlines by coal tracks)...
        	        drawNorth.draw();
        
        	        //Draw Main East (mainlines under timber mountain)...
        	        drawEast.draw();
        
        	        //Draw Main Mid (mainline by Trestle)...
        	        drawMid.draw();
        
        	        //Draw Main Mid Upper (Upper Level Tracks)...
        	        drawMidUpper.draw();
        
        	        //Draw Yard...
        	        drawYard.draw();
        
        	        //Draw Loco Yard...
        	        drawLocoYard.draw();
        
        
        	        //Test code to print out Segments and Switches!
        	        //printTestDebug();
        
        	        //Draw all trains...
        	        for(int i = 0; i < ThrottlePanel.availableTrains.size(); i++){
        	        	Train train = ThrottlePanel.availableTrains.get(i);
        	        	if(train != null)
        	        		if(train.getThrottleID() != ThrottlePanel.NOT_SET)
        	        			train.draw();
        	        }
	            }
	        }
		}

        //Call repaint to refresh any color changes
		repaint();

        //Lastly, set the prevPanelSize equal to pSize!
        prevPanelSize = pSize;
    }

	private void printTestDebug() {
        //Test code to print out Segments and Switches!

		if((count++%400)==0) {
        	//Print out all segments for debug
        	System.out.println("\n*************\nPRINTING OUT ALL ["+layoutList.getListSize()+
        		"] Segments");
        	TrackSegment currSegment = layoutList.getFirstNode();
        	while(currSegment != null) {
        		if(currSegment.getSegmentID() > 60 && currSegment.getSegmentID() <= 90)
        		//if((currSegment.getSegmentName().contains("TurnTable Track")) || (currSegment.getSegmentName().contains("RH")))
        		//if(currSegment.getSegmentName().contains("LocoYard"))
        		//if(currSegment.getSegmentName().contains("Yard"))
        			currSegment.printSegment();
        		currSegment = currSegment.getNext();
        	}


/*

        	System.out.println("\n*************\nPRINTING OUT ALL SW's ["+switchList.size()+
            		"] Switch Segments");
        	for(int i = 0; i < switchList.size(); i++){
        		switchList.get(i).print();
        	}
*/


/*
        	if(turnTable != null) {
        		System.out.println("\n\n*************\nPRINTING OUT ALL TT Connects ["+turnTable.getNumConnections()+
            		"] connections");
        		for(int i = 0; i < turnTable.getNumConnections(); i++){
        			TrackSegment seg = turnTable.getConnection(i);
        			if(seg.getSegmentName().contains("ttConnect"))
        				System.out.println("\n****\nTrackSegment ["+seg.getSegmentName()+"], segID="+seg.getSegmentID());
        			else
        				seg.printSegment();
        		}
        	}
*/
		}
	}

	public void setSwitchSegmentVisibility(boolean vis){
    	for(int i = 0; i < switchList.size(); i++){
    		SwitchSegment swSeg = switchList.get(i);
    		swSeg.setIsVisible(vis);
    	}
	}

	public static void resetSegmentColor(){
		TrackSegment currSegment = layoutList.getFirstNode();
    	while(currSegment != null) {
   			currSegment.setRouteNotSelectedColor();
    		currSegment = currSegment.getNext();
    	}
	}


	public static Point getSecondPtWithPtSlope(Point p1, int distance, int slope) {

		Point retPt = new Point(0,0);
		return retPt;
	}

	public static int getXOnLine(int x0, int y0, int x1, int y1, int knownY) {
		//m=(y1 - y0)/(x1 - x0)
		//NOTE: Could not use m as a double - when I did, it always resulted in m=0!
		//BIG NOTE: Below - I have to multiple by -1 on y difference since a screen's vertical pixel count increases
		//          as you move down compared to a Y-axis in Math!!!
		float m = (float)(-1)*(y1 - y0) / (x1 - x0);

		// (y - y0) = m(x - x0)
		// knownY-y0 = m*(returnX - x0)
		// knownY = m * (returnX - x0) + y0
		// knownY = m*returnX - m*x0 + y0
		// -m*returnX = m*x0 +y0 - knownY
		// returnX = (m*x0 +y0 - knownY) / (-m)
		//int returnX = (int)(((m *x0) + y0 - knownY) / ((-1) * m));
		int returnX = (int)Math.abs((((m *x1) + y1 - knownY) / ((-1) * m)));

		//System.out.print("In FX: m="+m+" x0="+x0+" y0="+y0+" x1="+x1+" y1="+y1+" knownY="+knownY+" returnX="+returnX+"\n");

		//Clamp...
		if(returnX > pSize.width) returnX = pSize.width;

		return(returnX);
	}

	public static int getYOnLine(int x0, int y0, int x1, int y1, int knownX) {
		//m=(y1 - y0)/(x1 - x0)
		//NOTE: Could not use m as a double - when I did, it always resulted in m=0!
		//BIG NOTE: Below - I have to multiple by -1 on y difference since a screen's vertical pixel count increases
		//          as you move down compared to a Y-axis in Math!!!
		float m = (float)(-1)*(y1 - y0) / (x1 - x0);

		// (y - y0) = m(x - x0)
		// returnY-y0 = m*(knownX - x0)
		// returnY = m * (knownX - x0) + y0
		int returnY = (int)Math.abs((m * (knownX - x0) - y0));  //TODO: Changed +y0 to a minus - seems to work...
		//int returnY = (int)(m*knownX - m*x0 + y0);
		//System.out.print("In getYOnLine: m="+m+" x0="+x0+" y0="+y0+" x1="+x1+" y1="+y1+" knownX="+knownX+" returnY="+returnY+"\n");

		//Clamp...
		if(returnY > pSize.height) returnY = pSize.height;

		return(returnY);
    }

	public static void setLayoutColorAndStroke() {
        //int red   = 156;
        //int green = 215;
        //int blue  = 245;
        //Color myBlue = new Color(red,green,blue);
        g2d.setColor(defaultLayoutDrawColor);
        g2d.setStroke(new BasicStroke(5));
	}

	private int findSegmentID(Point pt){
		int retId = NOT_SET;
		for(int i = 0; i < layoutList.getListSize(); i++){
			TrackSegment seg = layoutList.getSegment(i);
			if(seg != null){
				if(seg.isPointInSegment(pt) == true){
					//System.out.println("findSegmentID  segID="+seg.getSegmentID()+" listsize="+layoutList.getListSize());
					return (seg.getSegmentID());
				}
			}
		}
		//System.out.println("findSegmentID  FROG PT="+pt+" NOT FOUND!!");
		return (retId);
	}

	public static void drawRectagleBridgeIcon(int x0, int y0, int x1, int y1, int x2, int y2, int x3, int y3) {
		Polygon p = new Polygon();
		p.addPoint(x0, y0);
		p.addPoint(x1, y1);
		p.addPoint(x2, y2);
		p.addPoint(x3, y3);

		int red   = 133;
        int green = 122;
        int blue  = 112;
        Color myBrown = new Color(red,green,blue, 150);
        g2d.setColor(myBrown);
		g2d.fillPolygon(p);


        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawPolygon(p);

        //Reset Color and Stroke!
        setLayoutColorAndStroke();
	}

	public static SwitchSegment getSwitch(String pName){
		SwitchSegment retValue = null;
		for(int i = 0; i < switchList.size(); i++){
    		if(switchList.get(i).getSwitchName().equals(pName) == true) {
    			retValue = switchList.get(i);
    		}
		}

		if(retValue == null) {
			//See if switch is part of another panel...
			TestSwitchDialog tDialogPanel = TestSwitchDialog.getInstance();
			retValue = tDialogPanel.getSwitch(pName);
		}
		return (retValue);
	}

	public static SwitchSegment getSwitch(int dccAddr){
		SwitchSegment retValue = null;
		for(int i = 0; i < switchList.size(); i++){
    		if(switchList.get(i).getSwitchDccAddr()== dccAddr) {
    			retValue = switchList.get(i);
    		}
		}

		if(retValue == null) {
			//See if switch is part of another panel...
			TestSwitchDialog tDialogPanel = TestSwitchDialog.getInstance();
			retValue = tDialogPanel.getSwitch(dccAddr);
		}
		return (retValue);
	}

	private void unmanageFrogButtons() {
		//Loop through all switch objects and unmanage and delete & reset SwitchCount to zero...

		int listSize = switchList.size();
		for (int i = 0; i < listSize; i++) {
			switchList.get(i).unManage();
			switchList.get(i).removeFrogInfoRecord();
		}

		//remove all list Elements.class..
		switchList.clear();
		SwitchSegment.resetSwitchCount();
	}

	public static boolean refreshSwitchList(String switchName, boolean defaultIsThrown) {
		boolean thrown = defaultIsThrown;
		refreshedSwId = -1;

		for (int i = 0; i < switchList.size(); i++) {
			if (switchList.get(i).getSwitchName().equals(switchName)){
				thrown = switchList.get(i).getIsThrown(); //Save State for new Switch Object!
				refreshedSwId = switchList.get(i).getSwitchID();
				int dccAddr = switchList.get(i).getSwitchDccAddr();
				Point frogPt = switchList.get(i).getFrogPoint();
				break;
			}
		}
		return thrown;
	}

	private boolean isMouseClickOnSwitch(Point pt) {
		boolean found = false;

		for(int i = 0; i < switchList.size(); i++) {
			if (switchList.get(i).isSwitchFound(pt)){
				found = true;
				break;
			}
		}
		return found;
	}

	//Search through TrackSegments and see if mouse click is on a Track Segment
	private TrackSegment isMouseClickOnSegment(Point pt) {
		TrackSegment segmentClicked = layoutList.getSegment(pt);

		if(segmentClicked != null) {
			segmentClicked.setRouteSelectedColor(rMgr.getRouteColor1());
		}

		return segmentClicked;
	}


	private boolean isMouseClickOnTurntable(Point pt) {
		boolean turnTableClicked = false;

		if(drawLocoYard != null) {
			//Top Left
			int x0 = drawLocoYard.locoYdTurnTablePtX;
			int y0 = drawLocoYard.locoYdTurnTablePtY;

			//Lower Right
			int x1 = drawLocoYard.locoYdTurnTablePtX + drawLocoYard.locoYdTurnTableDiameter;
			int y1 = drawLocoYard.locoYdTurnTablePtY + drawLocoYard.locoYdTurnTableDiameter;

			if((pt.x >= x0 && pt.x <= x1) && (pt.y >= y0 && pt.y <= y1))
				turnTableClicked = true;
		}

		return turnTableClicked;
	}

	private boolean hasSegmentPointChanged(String segName, int x0, int y0, int x1, int y1) {
		boolean hasSPtsChanged = false;
		TrackSegment seg = layoutList.getSegment(segName);

		if(seg != null) {
			hasSPtsChanged = (seg.getPoint1().x != x0 || seg.getPoint1().y != y0 || seg.getPoint2().x != x1 || seg.getPoint2().y != y1);
		}
		return hasSPtsChanged;
	}
} //End of LayoutPanel Class Definition...