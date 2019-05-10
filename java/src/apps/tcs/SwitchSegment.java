package apps.gui3.tcs;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

//************************************************************************************************
//************************************************************************************************
//
//Class SwitchSegment Subclass Definition - A Java Object Class to build A Switch Track Segment & Controls
//
//************************************************************************************************
//************************************************************************************************
public class SwitchSegment {

	public static ArrayList <FrogInfo> frogPointList = new ArrayList <FrogInfo>();

	public final int    	          NOT_SET = -1;
	public boolean 			          isOccupied;

	private static int                switchCount;
	private static final int          CLICK_SIZE = 6;
	private String 		              name;
	private int 			          dccAddress = 0;
	private int 			          segmentID;
	private int 			          spurSegmentID;
	private int                       switchID;
    private int 			          zoneID;
    private int 			          powerDistrict;
    private FrogInfo                  frog;
    private Point 			          frogPt;
    private Point 			          mainLinePt;
    private Point        	          spurPt;
    private Line2D                    mainLine;
    private Line2D  		          spurLine;
    private JPanel                    frogPanel;
	private JButton                   frogButton;
	private String                    toolTipText = new String("99 - CLOSED");
    private LocoNetAPI                lnAPI = null;
    private LocoNetAPI_STUB           lnAPI_S = null;
    private boolean                   isThrown;
    private boolean                   isVisible = true;
    private LayoutPanel               layoutPanel = null;


    public SwitchSegment(String sName, int switchId, int segId, int spurSegId, int dccAddr, int pt1X, int pt1Y, int pt2X, int pt2Y, int spurPtX, int spurPtY, boolean thrown) {
    	name = sName;
    	segmentID = segId;

    	dccAddress = dccAddr;
   		switchID = switchId;

   		//CHANGE #2
   		//Get singleton instance of LocoNetAPI...
   		if(LayoutPanel.layoutConnected == true) lnAPI = LocoNetAPI.getInstance();
   		else lnAPI_S = LocoNetAPI_STUB.getInstance();

   		isThrown = thrown;

   		if(lnAPI != null) {
	   		//NOTE: NOT_SET (-1) = First Time creating this switch instance!
	   		if(switchId == NOT_SET) {
	            //Now, Assign a unique ID number to this switch...
                switchID = switchCount++;
                
	   			//if(dccAddr == 74) System.out.print("\nSwitchSegment()  lnAPI != null Call addSwitch switchID="+switchId+" dccAddr="+dccAddr+" thrown="+thrown+" switchCount="+switchCount+"\n\n");
	   			lnAPI.addSwitch(switchID, dccAddr, sName, thrown);


	   		} else {  //Not first time!
	   			//if(dccAddr == 74) System.out.print("\nSwitchSegment() lnAPI != null Call setSwitch thrown="+thrown+" switchCount="+switchCount+"\n\n");
	   			lnAPI.setSwitchIsThrown(dccAddr, thrown);
	   		}
   		}

		if(lnAPI_S != null) {
			//NOTE: NOT_SET (-1) = First Time creating this switch instance!
	   		if(switchId == NOT_SET) {
	   			//if(dccAddr == 74) System.out.print("\nSwitchSegment() lnAPI_S != null Call addSwitch dccAddr="+dccAddr+" thrown="+thrown+" switchCount="+switchCount+"\n\n");
	   			lnAPI_S.addSwitch(switchID, dccAddr, sName, thrown);

	   			//Now, Assign a unique ID number to this switch...
	   			switchID = switchCount++;
	   		} else {  //Not first time!
	   			//if(dccAddr == 74) System.out.print("\nSwitchSegment() lnAPI_S != null Call setSwitch thrown="+thrown+" switchCount="+switchCount+"\n\n");
	   			lnAPI_S.setSwitchIsThrown(dccAddr, thrown);
	   		}
   		}

   		zoneID = 0;
   		powerDistrict = 0;

   		frogPt = new Point(pt1X, pt1Y);
   		mainLinePt = new Point(pt2X, pt2Y);
   		spurPt = new Point(spurPtX, spurPtY);
   		spurSegmentID = spurSegId;

   		boolean forward = false;

   		if(pt1X == pt2X) {//Vertical
   			if(pt1Y < spurPtY) forward = true;
   		} else if(pt1Y == pt2Y) {//Horizontal
   			if(pt1X < spurPtX) forward = true;
   		} else //Slant
   			if(pt1Y < spurPtY) forward = true;

   		frog = new FrogInfo(segmentID, spurSegmentID, switchID, frogPt, forward);
   		frogPointList.add(frog);

    	mainLine = new Line2D.Double ((double)pt1X, (double)pt1Y, (double)pt2X, (double)pt2Y);
		spurLine = new Line2D.Double ((double)pt1X, (double)pt1Y, (double)spurPtX, (double)spurPtY);

		layoutPanel = LayoutPanel.getInstance();

    	//Draw Switch Status & Controls...
    	draw();

    	createFrogButton();
    }

    public void createFrogButton() {
    	createFrogButton(layoutPanel);
    }

    public void createFrogButton(JPanel lPanel) {
    	//First, delete if need be...
    	if(frogButton != null) frogButton = null;
    	if(frogPanel != null) frogPanel = null;

		//Create the Frog Button and Mouse-over Tooltip
   		frogPanel = new JPanel();
   		frogPanel.setLayout(new BorderLayout());  //NOTE: Layout Position defaults to BorderLayout.CENTER!!
   		frogPanel.setBounds(frogPt.x-5, frogPt.y-5, 10, 10);

   		if(dccAddress == 81) System.out.println("SwitchSegment:createFrogButton frogpt="+frogPt);

        frogPanel.setBackground(Color.BLACK);
        frogPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        lPanel.add(frogPanel);

   		//Create turnout Frog control button
   		frogButton = new JButton("");

	    //Add action listener to button
   		frogButton.addActionListener(new ActionListener() {
   			public void actionPerformed(ActionEvent e)
	        {
   				if(lnAPI != null) System.out.println("SwitchSegment() actionPerformed thrown="+lnAPI.getSwitchIsThrown(dccAddress));
   				if(lnAPI_S != null) System.out.println("SwitchSegment() actionPerformed thrown="+lnAPI_S.getSwitchIsThrown(dccAddress));
   				//Toggle the state of the switch...
   				processChangeState();
	        }
	    });

   		frogButton.setPreferredSize(new Dimension(8,8));

   		if(isThrown) {
   			frogButton.setBackground(Color.RED);
   			toolTipText = dccAddress+" - THROWN";
   		} else {
   			frogButton.setBackground(Color.GREEN);
   			toolTipText = dccAddress+" - CLOSED";
   		}
   		frogButton.setToolTipText(toolTipText);

   		frogButton.setOpaque(true);
   		frogButton.setBorder(null);
   		frogButton.setFocusPainted(false);
   		frogButton.setBorderPainted(false);
   		frogButton.setVisible(true);
   		frogPanel.add(frogButton);
    }

    public void addFrogPanel(JPanel panel) {
    	if(panel != null) {
    		panel.add(frogPanel);
    	}
    }

    public void unManage() {
    	if(frogButton != null) {
    		frogButton.setVisible(false);
    		Container parent = frogButton.getParent();
    		parent.remove(frogButton);
    		frogButton = null;
    	}

    	if(frogPanel != null) {
    		frogPanel.setVisible(false);
    		layoutPanel.remove(frogPanel);
    		frogPanel = null;
    	}

    	layoutPanel.validate();
    	layoutPanel.repaint();
    }

    public void unManage(JPanel lPanel) {
    	if(frogButton != null) {
    		frogButton.setVisible(false);
    		Container parent = frogButton.getParent();
    		parent.remove(frogButton);
    		frogButton = null;
    	}

    	if(frogPanel != null) {
    		frogPanel.setVisible(false);
    		lPanel.remove(frogPanel);
    		frogPanel = null;
    	}

    	lPanel.validate();
    	lPanel.repaint();
    }

    public boolean getIsVisible() {
    	return isVisible;
    }

    public void setIsVisible(boolean vis) {
    	if(isVisible != vis){
    		isVisible = vis;
    	}
    }

    public static void resetSwitchCount() {
    	switchCount = 0;
    }

    public boolean isSwitchFound(Point clickedPt) {

    	//In isSwitchfound() add logic to test if mouse pt is on or nearly on the switch mline track or spur.
    	//If so, test if need to change state of switch, etc.

    	//boolean isXOnMainLine = ((clickedPt.x >= frogPt.x-CLICK_SIZE && clickedPt.x <= mainLinePt.x+CLICK_SIZE) || (clickedPt.x >= mainLinePt.x-CLICK_SIZE && clickedPt.x <= frogPt.x+CLICK_SIZE));
    	//boolean isYOnMainLine = ((clickedPt.y >= frogPt.y-CLICK_SIZE && clickedPt.y <= mainLinePt.y+CLICK_SIZE) || (clickedPt.y >= mainLinePt.y-CLICK_SIZE && clickedPt.y <= frogPt.y+CLICK_SIZE));
    	boolean isXOnMainLine = ((clickedPt.x >= frogPt.x && clickedPt.x <= mainLinePt.x+CLICK_SIZE) || (clickedPt.x >= mainLinePt.x-CLICK_SIZE && clickedPt.x <= frogPt.x));
    	boolean isYOnMainLine = ((clickedPt.y >= frogPt.y && clickedPt.y <= mainLinePt.y+CLICK_SIZE) || (clickedPt.y >= mainLinePt.y-CLICK_SIZE && clickedPt.y <= frogPt.y));

    	//boolean isXOnSpur = ((clickedPt.x >= frogPt.x-CLICK_SIZE && clickedPt.x <= spurPt.x+CLICK_SIZE) || (clickedPt.x >= spurPt.x-CLICK_SIZE && clickedPt.x <= frogPt.x+CLICK_SIZE));
    	//boolean isYOnSpur = ((clickedPt.y >= frogPt.y-CLICK_SIZE && clickedPt.y <= spurPt.y+CLICK_SIZE) || (clickedPt.y >= spurPt.y-CLICK_SIZE && clickedPt.y <= frogPt.y+CLICK_SIZE));
    	boolean isXOnSpur = ((clickedPt.x >= frogPt.x && clickedPt.x <= spurPt.x+CLICK_SIZE) || (clickedPt.x >= spurPt.x-CLICK_SIZE && clickedPt.x <= frogPt.x));
    	boolean isYOnSpur = ((clickedPt.y >= frogPt.y && clickedPt.y <= spurPt.y+CLICK_SIZE) || (clickedPt.y >= spurPt.y-CLICK_SIZE && clickedPt.y <= frogPt.y));

    	/*System.out.println("\nSwitchSegment isSwitchFound  name="+name+" c.x="+clickedPt.x+" c.y="+clickedPt.y+" f.x="+frogPt.x+" f.y="+frogPt.y+
    						" ml.x="+mainLinePt.x+" ml.y="+mainLinePt.y+" s.x="+spurPt.x+" s.y="+spurPt.y+" isXML="+isXOnMainLine+" isYML="+isYOnMainLine+
    						" isXS="+isXOnSpur+" isYS="+isYOnSpur); */

    	if(lnAPI != null) isThrown = lnAPI.getSwitchIsThrown(dccAddress);
    	if(lnAPI_S != null) isThrown = lnAPI_S.getSwitchIsThrown(dccAddress);

    	if (isXOnMainLine && isYOnMainLine) {
   			System.out.println("SwitchSegment isSwitchFound - if Clicked on mainLine!!   isThrown="+isThrown);
   			if(isThrown) {
   				if(frogButton != null) frogButton.doClick();
   				//processChangeState();
   			}
   			return true;
   		} else  if (isXOnSpur && isYOnSpur) {
   			System.out.println("SwitchSegment isSwitchFound - if Clicked on Spur!!  isThrown="+isThrown);
   			if(!isThrown) {
   				if(frogButton != null) frogButton.doClick();
   				//processChangeState();
   			}
   			return true;
    	}
    	return false;
    }
    public Point getFrogPoint() {
    	return frogPt;
    }
    public FrogInfo getFrog() {
    	return frog;
    }
    public Point getMainLinePt() {
    	return mainLinePt;
    }
    public String getSwitchName() {
    	return name;
    }
    public int getSwitchID() {
    	return switchID;
    }
    public int getSwitchDccAddr() {
    	return dccAddress;
    }
    public Point getSpurPoint() {
    	return spurPt;
    }
    public boolean getIsThrown() {
    	if(lnAPI != null) isThrown = lnAPI.getSwitchIsThrown(dccAddress);
    	if(lnAPI_S != null) isThrown = lnAPI_S.getSwitchIsThrown(dccAddress);
    	return isThrown;
    }

    public void removeFrogInfoRecord() {
    	for(int i = 0; i < frogPointList.size(); i++) {
			if(frogPointList.get(i).getSwitchID() == switchID) {
				frogPointList.remove(i);
			}
    	}
    }

    public void draw() {

    	draw(LayoutPanel.g2d);
    }

    public void draw(Graphics2D g2d) {

   		//Draw the Switch Segment... NOTE g2d is NULL on Head node!
   		if(g2d != null) {

   			isThrown = getIsThrown();

   			if(isVisible){
	   			if(isThrown) {
	   				//if(dccAddress == 81) System.out.println("\nSwitchSegment drawSwitch IF isThrown!!");
	   				//Draw ML Switch Route - Color Red...
	   				g2d.setColor(Color.red);
	   				g2d.draw(mainLine);

	   				//draw Spur - Color Green...
	   				g2d.setColor(Color.green);
	   				g2d.draw(spurLine);
	   			} else {
	   				//if(dccAddress == 81) System.out.println("\nSwitchSegment drawSwitch ELSE  NOT isThrown!!");
	   				//Draw ML Switch Route - Color Green...
	   				g2d.setColor(Color.green);
	   				g2d.draw(mainLine);

	   				//draw Spur - Color Red...
	   				g2d.setColor(Color.red);
	   				g2d.draw(spurLine);
	   			}

	   			//Refresh Frog Button...
	   			if(frogButton != null) {
		   	   		if(isThrown) {
		   	   			frogButton.setBackground(Color.RED);
		   	   			toolTipText = dccAddress+" - THROWN";
		   	   		} else {
		   	   			frogButton.setBackground(Color.GREEN);
		   	   			toolTipText = dccAddress+" - CLOSED";
		   	   		}
		   	   		frogButton.setToolTipText(toolTipText);
	   			}

	   			//Reset Layout Colors...
	   			LayoutPanel.setLayoutColorAndStroke();
   			}
    	}
    }

    public void print(){
    	isThrown = getIsThrown();
    	System.out.println("SwitchSegment Ct="+switchID+" name="+name+" dccAddr="+dccAddress+
    			" isThrown="+isThrown+" segID="+segmentID+" spurSegId="+spurSegmentID+" switchID="+switchID+
    			" frogPt="+frogPt+" mainLinePt="+mainLinePt+" spurPt="+spurPt+" facingForward="+frog.getForwardFacing());
    }

    private void processChangeState() {
    	//Changes switch state, re-draws, and send DCC message out to throw switch.

    	isThrown = getIsThrown();
	    boolean first = isThrown; //for debug!!
	    isThrown = !isThrown;

	    if(isThrown) {
	    	System.out.println("SwitchSegment processChangeState Sw dccAddress="+dccAddress+" THROWN = RED"+" First="+first);
   			frogButton.setBackground(Color.RED);
   			toolTipText = dccAddress+" - THROWN";
   		} else {
   			System.out.println("SwitchSegment processChangeState Sw dccAddress="+dccAddress+" !THROWN = GREEN"+" First="+first);
   			frogButton.setBackground(Color.GREEN);
   			toolTipText = dccAddress+" - CLOSED";
   		}
   		frogButton.setToolTipText(toolTipText);
   		//frogButton.super.paintComponent(g);
   		//frogButton.repaint();


   		if(lnAPI != null) lnAPI.setSwitchIsThrown(dccAddress, isThrown);
   		if(lnAPI_S != null) lnAPI_S.setSwitchIsThrown(dccAddress, isThrown);

	   	System.out.println("\nSwitchSegment processChangeState Sw dccAddress="+dccAddress+" name="+name+" first isThrown="+first+
	   			" Last isThrown="+isThrown+" lnAPIThrown now="+lnAPI.getSwitchIsThrown(dccAddress));

	   	//Update the layout switch...
   		if(lnAPI != null) lnAPI.sendDCCSwitchChange(dccAddress);
   		if(lnAPI_S != null) lnAPI_S.sendDCCSwitchChange(dccAddress);

    	//Redraw GUI Switch State!
    	//draw();
    }
}

class FrogInfo {
	private int   	segmentID;
	private int   	spurSegmentID;
	private int   	switchID;
	private boolean facingForward;  //Facing toward Next points
	private Point 	frogPt;

	public FrogInfo (int id, int spurSegId, int swID, Point pt, boolean face){
		segmentID     = id;
		spurSegmentID = spurSegId;
		switchID      = swID;
		facingForward = face;
		frogPt        = pt;
	}

	public int getSegmentID() {
		return segmentID;
	}

	public boolean isFacingForward() {
		return facingForward;
	}

	public void setSegmentID(int id) {
		segmentID = id;
	}
	public int getSpurSegmentID() {
		return spurSegmentID;
	}
	public void setSpurSegmentID(int id) {
		spurSegmentID = id;
	}
	public int getSwitchID() {
		return switchID;
	}

	public void setSwitchID(int id) {
		switchID = id;
	}

	public boolean getForwardFacing() {
		return facingForward;
	}

	public Point getFrogPt() {
		return frogPt;
	}

	public void setFrogPt(Point pt) {
		frogPt = pt;
	}
}