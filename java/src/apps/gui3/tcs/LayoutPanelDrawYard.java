package apps.gui3.tcs;

import java.awt.Color;

public class LayoutPanelDrawYard {

	private static LayoutPanelDrawYard _instance = null;  //A singleton class!

	private LayoutPanel lp = null;

	//Yard Points
	private final int yardHeightSpacing = 15;
	private int yardLineLeftX, yardLineLeftTopX, yardBottomLineY, yardBottomLine1Y, yardBottomLine2Y = 0;
	private int yardBottomLine1FrogX, yardBottomLine1FrogY = 0;
	private int yardBottomLine3Y, yardBottomLine4Y, yardBottomLine5Y, yardBottomLine6Y = 0;
	private int yardBottomLine7Y, yardBottomLine8Y, yardBottomLine9Y = 0;
	private int yardBottomLineRightX, yardBottomLine1RightX, yardBottomLine2RightX, yardBottomLine3RightX = 0;
	private int yardBottomLine4RightX, yardBottomLine5RightX, yardBottomLine6RightX = 0;
	private int yardBottomLine7RightX, yardBottomLine8RightX, yardBottomLine9RightX = 0;
	private int yardTopLeftTOX, yardTopRightTOX = 0;

	public LayoutPanelDrawYard() {
		// Instantiate and Initialize...
		if (lp == null) lp = LayoutPanel.getInstance();
	}

	public static LayoutPanelDrawYard getInstance() {
		if(_instance == null) {
			_instance = new LayoutPanelDrawYard();
		}
		return _instance;
	}

	private int northTopLineLeftX, northTopLineY = 0;

	private void initializePointsFromOtherSections() {
		northTopLineLeftX = (int)(LayoutPanel.pSize.getWidth()/3)+50;
		northTopLineY = 25;
	}

	private void initializePoints() {

		initializePointsFromOtherSections();

		yardLineLeftX = 15;
		yardLineLeftTopX = 145;
		yardBottomLineY = 160;//145;
		yardTopLeftTOX = northTopLineLeftX-110;
		yardTopRightTOX = northTopLineLeftX-40;
		yardBottomLine1Y = yardBottomLineY - yardHeightSpacing;
		yardBottomLine2Y = yardBottomLine1Y - (yardHeightSpacing*2);
		yardBottomLine3Y = yardBottomLine2Y - yardHeightSpacing;
		yardBottomLine4Y = yardBottomLine3Y - yardHeightSpacing;
		yardBottomLine5Y = yardBottomLine4Y - yardHeightSpacing;
		yardBottomLine6Y = yardBottomLine5Y - yardHeightSpacing;
		yardBottomLine7Y = yardBottomLine6Y - yardHeightSpacing;
		yardBottomLine8Y = yardBottomLine7Y - yardHeightSpacing;
		yardBottomLine9Y = yardBottomLine8Y - yardHeightSpacing;

		int x0 = northTopLineLeftX;
		int y0 = northTopLineY;
		int x1 = (int)(LayoutPanel.pSize.getWidth()/4)-30;
		int y1 = yardBottomLine1Y;

		yardBottomLine1RightX = LayoutPanel.getXOnLine(x0, y0, x1, y1, yardBottomLine1Y);
		yardBottomLineRightX = yardBottomLine1RightX+10;
		yardBottomLine2RightX = LayoutPanel.getXOnLine(x0, y0, x1, y1, yardBottomLine2Y);
		yardBottomLine3RightX = LayoutPanel.getXOnLine(x0, y0, x1, y1, yardBottomLine3Y);
		yardBottomLine4RightX = LayoutPanel.getXOnLine(x0, y0, x1, y1, yardBottomLine4Y);
		yardBottomLine5RightX = LayoutPanel.getXOnLine(x0, y0, x1, y1, yardBottomLine5Y);
		yardBottomLine6RightX = LayoutPanel.getXOnLine(x0, y0, x1, y1, yardBottomLine6Y);
		yardBottomLine7RightX = yardTopLeftTOX-40;
		yardBottomLine8RightX = northTopLineLeftX;
		yardBottomLine9RightX = yardBottomLine8RightX - 80;

		yardBottomLine1FrogY = yardBottomLine2Y+12;
        yardBottomLine1FrogX = LayoutPanel.getXOnLine(yardBottomLine1RightX, yardBottomLine1Y, northTopLineLeftX, northTopLineY, yardBottomLine1FrogY);


	}

	public void draw() {

		initializePoints();

		//Yard ML Left Horizontal Track (Next to Top) - 2 Frogs - DONE Splitting up...

		//Yard ML Left Horizontal Track (Next to Top) - Part A (right-most)...
        if(LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-A") == null) {
	    	LayoutPanel.layoutList.add("Yard-ML Left Horizontal Track-A", yardTopRightTOX, yardBottomLine8Y, yardBottomLine8RightX, yardBottomLine8Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-A").draw();
        TrackSegment seg = LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-A");
        TrackSegment nextSeg = LayoutPanel.layoutList.getSegment("North-Top ML Track-A");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-ML Left Horizontal Track-A Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Yard ML Left Horizontal Track (Next to Top) - Part B (middle)...
        if(LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-B") == null) {
	    	LayoutPanel.layoutList.add("Yard-ML Left Horizontal Track-B", yardTopLeftTOX, yardBottomLine8Y, yardTopRightTOX, yardBottomLine8Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-B").draw();


		//Yard ML Left Horizontal Track (Next to Top) - Part C (left)...
        if(LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-C") == null) {
	    	LayoutPanel.layoutList.add("Yard-ML Left Horizontal Track-C", yardLineLeftTopX, yardBottomLine8Y, yardTopLeftTOX, yardBottomLine8Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-C").draw();
        seg = LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-C");
        nextSeg = LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-B");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-ML Left Horizontal Track-C Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Angle to Top Yard Track ...

        if(LayoutPanel.layoutList.getSegment("Yard-Angle to Top Yard Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-Angle to Top Yard Track", yardBottomLine9RightX, yardBottomLine9Y, yardTopRightTOX, yardBottomLine8Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-Angle to Top Yard Track").draw();


        //****************  Yard-SW0  ***
		if(LayoutPanel.getSwitch("Yard-SW0") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("Yard-SW0", false);
	        int slY = yardBottomLine8Y-12;
	        int slX = LayoutPanel.getXOnLine(yardBottomLine9RightX, yardBottomLine9Y, yardTopRightTOX, yardBottomLine8Y, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Yard-Angle to Top Yard Track").getSegmentID();
			SwitchSegment sw0 = new SwitchSegment("Yard-SW0", LayoutPanel.refreshedSwId, mlSeg, slSeg, 62, yardTopRightTOX, yardBottomLine8Y, yardTopRightTOX-35, yardBottomLine8Y, slX, slY, thrown);
			LayoutPanel.switchList.add(sw0);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Yard-Angle to Top Yard Track");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw0);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
		} else LayoutPanel.getSwitch("Yard-SW0").draw();
		//********************************

        seg = LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-B");
        nextSeg = LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-A");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-ML Left Horizontal Track-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Top Yard Horizontal Track

		if(LayoutPanel.layoutList.getSegment("Yard-Top Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-Top Horizontal Track", yardLineLeftTopX, yardBottomLine9Y, yardBottomLine9RightX, yardBottomLine9Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-Top Horizontal Track").draw();
		seg = LayoutPanel.layoutList.getSegment("Yard-Top Horizontal Track");
        nextSeg = LayoutPanel.layoutList.getSegment("Yard-Angle to Top Yard Track");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-Top Horizontal Track Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//SL Angle to Top-2 Horizontal Track...

        if(LayoutPanel.layoutList.getSegment("Yard-SL Angle to Top-2 Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-SL Angle to Top-2 Horizontal Track", yardBottomLine7RightX, yardBottomLine7Y, yardTopLeftTOX, yardBottomLine8Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-SL Angle to Top-2 Horizontal Track").draw();

        //****************  Yard-SW1  ***
        if(LayoutPanel.getSwitch("Yard-SW1") == null) {
        	boolean thrown = LayoutPanel.refreshSwitchList("Yard-SW1", false);
	        int slY = yardBottomLine8Y+12;
	        int slX = LayoutPanel.getXOnLine(yardBottomLine7RightX, yardBottomLine7Y, yardTopLeftTOX, yardBottomLine8Y, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-C").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Yard-SL Angle to Top-2 Horizontal Track").getSegmentID();
			SwitchSegment sw1 = new SwitchSegment("Yard-SW1", LayoutPanel.refreshedSwId, mlSeg, slSeg, 64, yardTopLeftTOX, yardBottomLine8Y, yardTopLeftTOX-35, yardBottomLine8Y, slX, slY, thrown);
			LayoutPanel.switchList.add(sw1);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("Yard-ML Left Horizontal Track-C");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Yard-SL Angle to Top-2 Horizontal Track");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw1);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("Yard-SW1").draw();
		//********************************


		//Top-2 Yard Horizontal Track

		if(LayoutPanel.layoutList.getSegment("Yard-Top-2 Yard Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-Top-2 Yard Horizontal Track", yardLineLeftX, yardBottomLine7Y, yardBottomLine7RightX, yardBottomLine7Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-Top-2 Yard Horizontal Track").draw();
		seg = LayoutPanel.layoutList.getSegment("Yard-Top-2 Yard Horizontal Track");
        nextSeg = LayoutPanel.layoutList.getSegment("Yard-SL Angle to Top-2 Horizontal Track");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-Top-2 Yard Horizontal Track Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


//LayoutPanel.g2d.setColor(Color.red);
//LayoutPanel.setLayoutColorAndStroke();

		//ML Yard Angle to bottom Yard Track - 6 Frogs   ...

		//Yard-ML Angle to bottom-1 Yard Track - Part A (Top-Most)...
		if(LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-A") == null) {
	    	LayoutPanel.layoutList.add("Yard-ML Angle to bottom-1 Yard Track-A", yardBottomLine6RightX, yardBottomLine6Y, northTopLineLeftX, northTopLineY);
	    } else LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-A").draw();

		//Yard-ML Angle to bottom-1 Yard Track - Part B (Top-1)...
		if(LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-B") == null) {
	    	LayoutPanel.layoutList.add("Yard-ML Angle to bottom-1 Yard Track-B", yardBottomLine5RightX, yardBottomLine5Y, yardBottomLine6RightX, yardBottomLine6Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-B").draw();
        seg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-B");
        nextSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-A");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-ML Angle to bottom-1 Yard Track-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Yard-ML Angle to bottom-1 Yard Track - Part C (Top-2)...
		if(LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-C") == null) {
	    	LayoutPanel.layoutList.add("Yard-ML Angle to bottom-1 Yard Track-C", yardBottomLine4RightX, yardBottomLine4Y, yardBottomLine5RightX, yardBottomLine5Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-C").draw();
        seg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-C");
        nextSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-B");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-ML Angle to bottom-1 Yard Track-C Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Yard-ML Angle to bottom-1 Yard Track - Part D (Top-3)...
		if(LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-D") == null) {
	    	LayoutPanel.layoutList.add("Yard-ML Angle to bottom-1 Yard Track-D", yardBottomLine3RightX, yardBottomLine3Y, yardBottomLine4RightX, yardBottomLine4Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-D").draw();
        seg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-D");
        nextSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-C");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-ML Angle to bottom-1 Yard Track-D Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Yard-ML Angle to bottom-1 Yard Track - Part E (Top-4)...
		if(LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-E") == null) {
	    	LayoutPanel.layoutList.add("Yard-ML Angle to bottom-1 Yard Track-E", yardBottomLine2RightX, yardBottomLine2Y, yardBottomLine3RightX, yardBottomLine3Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-E").draw();
        seg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-E");
        nextSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-D");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-ML Angle to bottom-1 Yard Track-E Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Yard-ML Angle to bottom-1 Yard Track - Part E (Top-5 WHICH IS NEXT TO BOTTOM)...
		if(LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-F") == null) {
	    	LayoutPanel.layoutList.add("Yard-ML Angle to bottom-1 Yard Track-F", yardBottomLine1FrogX, yardBottomLine1FrogY, yardBottomLine2RightX, yardBottomLine2Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-F").draw();
        seg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-F");
        nextSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-E");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-ML Angle to bottom-1 Yard Track-F Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Yard-ML Angle to bottom-1 Yard Track - Part F (Top-6 WHICH IS THE BOTTOM)...
		if(LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-G") == null) {
	    	LayoutPanel.layoutList.add("Yard-ML Angle to bottom-1 Yard Track-G", yardBottomLine1RightX, yardBottomLine1Y,yardBottomLine1FrogX, yardBottomLine1FrogY);
	    } else LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-G").draw();
        seg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-G");
        nextSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-F");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-ML Angle to bottom-1 Yard Track-G Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//****************  Yard-SW2  ***
		if(LayoutPanel.getSwitch("Yard-SW2") == null) {
			boolean thrown = LayoutPanel.refreshSwitchList("Yard-SW2", false);
	        int slY = northTopLineY+20;
	        int slX = LayoutPanel.getXOnLine(northTopLineLeftX, northTopLineY, yardBottomLine1RightX, yardBottomLine1Y, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("North-Top ML Track-A").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-A").getSegmentID();
			SwitchSegment sw2 = new SwitchSegment("Yard-SW2", LayoutPanel.refreshedSwId, mlSeg, slSeg, 61, northTopLineLeftX, northTopLineY, northTopLineLeftX-35, northTopLineY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw2);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Top ML Track-A");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-A");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw2);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
		} else LayoutPanel.getSwitch("Yard-SW2").draw();

		//********************************


		//***********************************
        //Draw Yard Tracks - Bottom to Top...
		//***********************************

		//Bottom Yard Horizontal Track...

        if(LayoutPanel.layoutList.getSegment("Yard-Bottom Yard Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-Bottom Yard Horizontal Track", yardLineLeftX, yardBottomLineY, yardBottomLineRightX, yardBottomLineY);
	    } else LayoutPanel.layoutList.getSegment("Yard-Bottom Yard Horizontal Track").draw();


		//Bottom-1 Yard Horizontal Track...

        if(LayoutPanel.layoutList.getSegment("Yard-Bottom-1 Yard Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-Bottom-1 Yard Horizontal Track", yardLineLeftX, yardBottomLine1Y, yardBottomLine1RightX, yardBottomLine1Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-Bottom-1 Yard Horizontal Track").draw();
        seg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-G");
        TrackSegment prevSeg = LayoutPanel.layoutList.getSegment("Yard-Bottom-1 Yard Horizontal Track");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel Yard-Bottom-1 Yard Horizontal Track Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//LayoutPanel.g2d.setColor(Color.red);
        //Bottom Yard Spur Track...

        if(LayoutPanel.layoutList.getSegment("Yard-Bottom Yard Spur Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-Bottom Yard Spur Track", yardBottomLineRightX, yardBottomLineY, yardBottomLine1FrogX, yardBottomLine1FrogY);
	    } else LayoutPanel.layoutList.getSegment("Yard-Bottom Yard Spur Track").draw();
		seg = LayoutPanel.layoutList.getSegment("Yard-Bottom Yard Horizontal Track");
		nextSeg = LayoutPanel.layoutList.getSegment("Yard-Bottom Yard Spur Track");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Yard-Bottom Yard Spur Track Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//LayoutPanel.setLayoutColorAndStroke();

		//****************  Yard-SW3  ***
        //***********
        //TEST Closest Yard Track Switch which is 74!!
        //***********
		if(LayoutPanel.getSwitch("Yard-SW3") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("Yard-SW3", false);
	        int mlY = yardBottomLine1Y;
	        int mlX = yardBottomLine1RightX;
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-G").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Yard-Bottom Yard Spur Track").getSegmentID();

	        SwitchSegment sw3 = new SwitchSegment("Yard-SW3", LayoutPanel.refreshedSwId, mlSeg, slSeg, 74, yardBottomLine1FrogX, yardBottomLine1FrogY, mlX, mlY, yardBottomLineRightX, yardBottomLineY, thrown);
			LayoutPanel.switchList.add(sw3);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-G");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Yard-Bottom Yard Spur Track");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw3);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
		}
		else LayoutPanel.getSwitch("Yard-SW3").draw();

		//********************************

		//Bottom-2 Yard Horizontal Track...

		if(LayoutPanel.layoutList.getSegment("Yard-Bottom-2 Yard Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-Bottom-2 Yard Horizontal Track", yardLineLeftX, yardBottomLine2Y, yardBottomLine2RightX, yardBottomLine2Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-Bottom-2 Yard Horizontal Track").draw();

		//****************  Yard-SW4  ***
		if(LayoutPanel.getSwitch("Yard-SW4") == null) {
			boolean thrown = LayoutPanel.refreshSwitchList("Yard-SW4", false);
	        int mlY = yardBottomLine2Y+12;
	        int mlX = LayoutPanel.getXOnLine(yardBottomLine1RightX, yardBottomLine1Y, northTopLineLeftX, northTopLineY, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-F").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Yard-Bottom-2 Yard Horizontal Track").getSegmentID();
			SwitchSegment sw4 = new SwitchSegment("Yard-SW4", LayoutPanel.refreshedSwId, mlSeg, slSeg, 69, yardBottomLine2RightX, yardBottomLine2Y, mlX, mlY, yardBottomLine2RightX-35, yardBottomLine2Y, thrown);
			LayoutPanel.switchList.add(sw4);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-F");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Yard-Bottom-2 Yard Horizontal Track");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw4);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
		} else LayoutPanel.getSwitch("Yard-SW4").draw();
		//********************************


		//Bottom-3 Yard Horizontal Track...

		if(LayoutPanel.layoutList.getSegment("Yard-Bottom-3 Yard Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-Bottom-3 Yard Horizontal Track", yardLineLeftX, yardBottomLine3Y, yardBottomLine3RightX, yardBottomLine3Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-Bottom-3 Yard Horizontal Track").draw();

		//****************  Yard-SW5  ***
		if(LayoutPanel.getSwitch("Yard-SW5") == null) {
			boolean thrown = LayoutPanel.refreshSwitchList("Yard-SW5", false);
	        int mlY = yardBottomLine3Y+12;
	        int mlX = LayoutPanel.getXOnLine(yardBottomLine1RightX, yardBottomLine1Y, northTopLineLeftX, northTopLineY, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-E").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Yard-Bottom-3 Yard Horizontal Track").getSegmentID();
			SwitchSegment sw5 = new SwitchSegment("Yard-SW5", LayoutPanel.refreshedSwId, mlSeg, slSeg, 68, yardBottomLine3RightX, yardBottomLine3Y, mlX, mlY, yardBottomLine3RightX-35, yardBottomLine3Y, thrown);
			LayoutPanel.switchList.add(sw5);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-E");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Yard-Bottom-3 Yard Horizontal Track");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw5);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
		} else LayoutPanel.getSwitch("Yard-SW5").draw();
		//********************************


		//Bottom-4 Yard Horizontal Track...

		if(LayoutPanel.layoutList.getSegment("Yard-Bottom-4 Yard Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-Bottom-4 Yard Horizontal Track", yardLineLeftX, yardBottomLine4Y, yardBottomLine4RightX, yardBottomLine4Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-Bottom-4 Yard Horizontal Track").draw();

		//****************  Yard-SW6  ***
		if(LayoutPanel.getSwitch("Yard-SW6") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("Yard-SW6", false);
	        int mlY = yardBottomLine4Y+12;
	        int mlX = LayoutPanel.getXOnLine(yardBottomLine1RightX, yardBottomLine1Y, northTopLineLeftX, northTopLineY, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-D").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Yard-Bottom-4 Yard Horizontal Track").getSegmentID();
			SwitchSegment sw6 = new SwitchSegment("Yard-SW6", LayoutPanel.refreshedSwId, mlSeg, slSeg, 67, yardBottomLine4RightX, yardBottomLine4Y, mlX, mlY, yardBottomLine4RightX-35, yardBottomLine4Y, thrown);
			LayoutPanel.switchList.add(sw6);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-D");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Yard-Bottom-4 Yard Horizontal Track");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw6);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
		} else LayoutPanel.getSwitch("Yard-SW6").draw();
		//********************************


		//Bottom-5 Yard Horizontal Track...

		if(LayoutPanel.layoutList.getSegment("Yard-Bottom-5 Yard Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-Bottom-5 Yard Horizontal Track", yardLineLeftX, yardBottomLine5Y, yardBottomLine5RightX, yardBottomLine5Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-Bottom-5 Yard Horizontal Track").draw();

		//****************  Yard-SW7  ***
		if(LayoutPanel.getSwitch("Yard-SW7") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("Yard-SW7", false);
	        int mlY = yardBottomLine5Y+12;
	        int mlX = LayoutPanel.getXOnLine(yardBottomLine1RightX, yardBottomLine1Y, northTopLineLeftX, northTopLineY, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-C").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Yard-Bottom-5 Yard Horizontal Track").getSegmentID();
			SwitchSegment sw7 = new SwitchSegment("Yard-SW7", LayoutPanel.refreshedSwId, mlSeg, slSeg, 66, yardBottomLine5RightX, yardBottomLine5Y, mlX, mlY, yardBottomLine5RightX-35, yardBottomLine5Y, thrown);
			LayoutPanel.switchList.add(sw7);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-C");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Yard-Bottom-5 Yard Horizontal Track");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw7);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
		} else LayoutPanel.getSwitch("Yard-SW7").draw();
		//********************************


		//Bottom-6 Yard Horizontal Track...

		if(LayoutPanel.layoutList.getSegment("Yard-Bottom-6 Yard Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("Yard-Bottom-6 Yard Horizontal Track", yardLineLeftX, yardBottomLine6Y, yardBottomLine6RightX, yardBottomLine6Y);
	    } else LayoutPanel.layoutList.getSegment("Yard-Bottom-6 Yard Horizontal Track").draw();

		//****************  Yard-SW8  ***
		if(LayoutPanel.getSwitch("Yard-SW8") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("Yard-SW8", false);
	        int mlY = yardBottomLine6Y+12;
	        int mlX = LayoutPanel.getXOnLine(yardBottomLine1RightX, yardBottomLine1Y, northTopLineLeftX, northTopLineY, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Yard-Bottom-6 Yard Horizontal Track").getSegmentID();
			SwitchSegment sw8 = new SwitchSegment("Yard-SW8", LayoutPanel.refreshedSwId, mlSeg, slSeg, 63, yardBottomLine6RightX, yardBottomLine6Y, mlX, mlY, yardBottomLine6RightX-35, yardBottomLine6Y, thrown);
			LayoutPanel.switchList.add(sw8);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("Yard-ML Angle to bottom-1 Yard Track-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Yard-Bottom-6 Yard Horizontal Track");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw8);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
		} else LayoutPanel.getSwitch("Yard-SW8").draw();
		//********************************

	}
}
