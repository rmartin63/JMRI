package apps.gui3.tcs;

import java.awt.Color;

public class LayoutPanelDrawMid {

	private static LayoutPanelDrawMid _instance = null;  //A singleton class!

	private LayoutPanel lp = null;
	private LayoutPanelDrawNorth north = null;
	private LayoutPanelDrawSouth south = null;
	private LayoutPanelDrawEast east = null;

	//Mid Points
	private int midFarRtVertX, midFarRtVertTopY, midFarRtVertBotY = 0;
	private int midInnerBotFrogX, midInnerBotFrogY = 0;
	private int midOuterBotFrogX, midOuterBotFrogY = 0;
	private int midOuterTopLeftX, midOuterTopLeftY, midOuterTopRightX, midOuterTopRightY = 0;
	private int midOuterVertTopX, midOuterVertTopY, midOuterVertBotX, midOuterVertBotY = 0;
	private int midOuterBotLeftX, midOuterBotLeftY, midOuterBotRightX, midOuterBotRightY = 0;
	private int midInnerTopLeftX, midInnerTopLeftY, midInnerTopRightX, midInnerTopRightY = 0;
	private int midInnerVertTopX, midInnerVertTopY, midInnerVertBotX, midInnerVertBotY = 0;
	private int midInnerBotLeftX, midInnerBotLeftY, midInnerBotRightX, midInnerBotRightY = 0;

	public int getmidFarRtVertX() { return midFarRtVertX;}
	public int getmidFarRtVertTopY() { return midFarRtVertTopY;}
	public int getmidInnerVertBotY() { return midInnerVertBotY;}
	public int getmidInnerVertTopY() { return midInnerVertTopY;}
	public int getmidInnerVertTopX() { return midInnerVertTopX;}
	public int getmidOuterTopRightX() { return midOuterTopRightX;}
	public int getmidInnerBotLeftY() { return midInnerBotLeftY;}
	public int getmidInnerTopLeftY() { return midInnerTopLeftY;}
	public int getmidInnerTopLeftX() { return midInnerTopLeftX;}
	public int getmidOuterTopRightY() { return midOuterTopRightY;}


	public LayoutPanelDrawMid() {
		// Instantiate and Initialize...
		if (lp == null) lp = LayoutPanel.getInstance();

		if (north == null) north = LayoutPanelDrawNorth.getInstance();
		if (south == null) south = LayoutPanelDrawSouth.getInstance();
		if (east == null) east = LayoutPanelDrawEast.getInstance();

	}

	public static LayoutPanelDrawMid getInstance() {
		if(_instance == null) {
			_instance = new LayoutPanelDrawMid();
		}
		return _instance;
	}

	private int southML4LeftX, southML4RightX = 0;
	private int eastTopLeftSLToMidTopOuterX, eastTopLeftSLToMidTopOuterBotY, eastNearCurveLineX, eastNearCurveLineTopY = 0;
	private int eastTopLeftSLToMidTopOuterTopY, eastNearLineFrogX, eastNearLineFrogY, eastNearLineX, eastNearLineTopY, eastMidLineX, eastMidLineTopY = 0;
	private int eastMidLineFrogX, eastMidLineFrogY = 0;
	private int eastMidFarRight1VertTopAngleBotY = 0;
	private int eastTopSLToMidTopInnerBotX, eastTopSLToMidTopInnerBotY, eastTopSLToMidTopInnerFrogX, eastTopSLToMidTopInnerFrogY = 0;
	private int northBottomFrogX, northBottomFrogY, northBottomLineRightX, northBottomLineY = 0;

	private void initializePointsFromOtherSections() {
		southML4LeftX = south.getsouthML4LeftX();
		southML4RightX = south.getsouthML4RightX();

		eastTopLeftSLToMidTopOuterX = east.geteastTopLeftSLToMidTopOuterX();
		eastTopLeftSLToMidTopOuterBotY = east.geteastTopLeftSLToMidTopOuterBotY();
		eastNearCurveLineX = east.geteastNearCurveLineX();
		eastNearCurveLineTopY = east.geteastNearCurveLineTopY();

		eastTopLeftSLToMidTopOuterTopY = east.geteastTopLeftSLToMidTopOuterTopY();
		eastNearLineFrogX = east.geteastNearLineFrogX();
		eastNearLineFrogY = east.geteastNearLineFrogY();
		eastMidLineFrogX = east.geteastMidLineFrogX();
		eastMidLineFrogY = east.geteastMidLineFrogY();
		eastMidLineX = east.geteastMidLineX();
		eastMidLineTopY = east.geteastMidLineTopY();
		eastNearLineX = east.geteastNearLineX();
		eastNearLineTopY = east.geteastNearLineTopY();
		eastMidFarRight1VertTopAngleBotY = east.geteastMidFarRight1VertTopAngleBotY();

		eastTopSLToMidTopInnerBotX = east.geteastTopSLToMidTopInnerBotX();
		eastTopSLToMidTopInnerBotY = east.geteastTopSLToMidTopInnerBotY();
		eastTopSLToMidTopInnerFrogX = east.geteastTopSLToMidTopInnerFrogX();
		eastTopSLToMidTopInnerFrogY = east.geteastTopSLToMidTopInnerFrogY();

		northBottomFrogX = north.getnorthBottomFrogX();
		northBottomFrogY = north.getnorthBottomFrogY();
		northBottomLineRightX = north.getnorthBottomLineRightX();
		northBottomLineY = north.getnorthBottomLineY();
	}

	public void initializePoints() {

		initializePointsFromOtherSections();
		midOuterTopLeftX = southML4LeftX+45;
		midOuterTopLeftY = (int)((LayoutPanel.pSize.getHeight()/3));
		midOuterTopRightX = southML4RightX-90;
		midOuterTopRightY = midOuterTopLeftY;

		midOuterBotLeftX = midOuterTopLeftX;
		midOuterBotLeftY = (int)((LayoutPanel.pSize.getHeight()/1.45));
		midOuterBotRightX = midOuterTopRightX+50;
		midOuterBotRightY = midOuterBotLeftY;

		midOuterVertTopX = midOuterTopLeftX-50;
		midOuterVertTopY = midOuterTopLeftY+40;
		midOuterVertBotX = midOuterVertTopX;
		midOuterVertBotY = midOuterBotRightY-40;


		midInnerTopLeftX = midOuterTopLeftX+20;
		midInnerTopLeftY = midOuterTopRightY+20;
		midInnerTopRightX = midOuterTopRightX+30;
		midInnerTopRightY = midInnerTopLeftY;

		midInnerVertTopX = midOuterVertBotX+20;
		midInnerVertTopY = midOuterVertTopY+20;
		midInnerVertBotX = midInnerVertTopX;
		midInnerVertBotY = midOuterVertBotY-20;

		midInnerBotLeftX = midOuterBotLeftX+20;
		midInnerBotLeftY = midOuterBotRightY-20;
		midInnerBotRightX = midOuterBotRightX+30;//+20;
		midInnerBotRightY = midInnerBotLeftY;
		midInnerBotFrogX = midInnerBotRightX-60;
		midInnerBotFrogY = midInnerBotRightY;

		midOuterBotFrogX = midInnerBotFrogX - 50;
		midOuterBotFrogY = midOuterBotRightY;

		midFarRtVertX = eastNearLineX-10;//20;
		midFarRtVertTopY = eastMidFarRight1VertTopAngleBotY+20;
		midFarRtVertBotY = eastMidLineTopY-60;

	}

	public void draw() {

		initializePoints();
        //Draw Main Mid Tracks...

		//Outer Bottom ML Right Angle...

		if(LayoutPanel.layoutList.getSegment("Mid-Outer Bottom ML Right Angle") == null) {
	    	LayoutPanel.layoutList.add("Mid-Outer Bottom ML Right Angle", midOuterBotRightX, midOuterBotRightY, eastNearCurveLineX, eastNearCurveLineTopY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Outer Bottom ML Right Angle").draw();
		TrackSegment seg = LayoutPanel.layoutList.getSegment("East- Near Aisle ML Vertical piece to Mid Section Outer ML");
        TrackSegment prevSeg = LayoutPanel.layoutList.getSegment("Mid-Outer Bottom ML Right Angle");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel Mid-Outer Bottom ML Right Angle Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Outer Bottom Horizontal ML... //TODO: NEED TO SPLIT UP!!
		if(LayoutPanel.layoutList.getSegment("Mid-Outer Bottom Horizontal ML-A") == null) {
	    	LayoutPanel.layoutList.add("Mid-Outer Bottom Horizontal ML-A", midOuterBotLeftX, midOuterBotLeftY, midOuterBotFrogX, midOuterBotFrogY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Outer Bottom Horizontal ML-A").draw();

		if(LayoutPanel.layoutList.getSegment("Mid-Outer Bottom Horizontal ML-B") == null) {
	    	LayoutPanel.layoutList.add("Mid-Outer Bottom Horizontal ML-B", midOuterBotFrogX, midOuterBotFrogY, midOuterBotRightX, midOuterBotRightY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Outer Bottom Horizontal ML-B").draw();
		seg = LayoutPanel.layoutList.getSegment("Mid-Outer Bottom ML Right Angle");
        prevSeg = LayoutPanel.layoutList.getSegment("Mid-Outer Bottom Horizontal ML-B");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel Mid-Outer Bottom Horizontal ML-B Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Connect Mid Outer Bot Hor ML A To Mid Outer Bot Hor ML B...
		seg = LayoutPanel.layoutList.getSegment("Mid-Outer Bottom Horizontal ML-A");
		TrackSegment nextSeg = LayoutPanel.layoutList.getSegment("Mid-Outer Bottom Horizontal ML-B");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel Mid-Outer Bottom Horizontal ML-A Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //OuterVertLowerAngle...

        if(LayoutPanel.layoutList.getSegment("Mid-Outer Vertical Lower Angle") == null) {
	    	LayoutPanel.layoutList.add("Mid-Outer Vertical Lower Angle", midOuterVertBotX, midOuterVertBotY, midOuterBotLeftX, midOuterBotLeftY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Outer Vertical Lower Angle").draw();
        seg = LayoutPanel.layoutList.getSegment("Mid-Outer Bottom Horizontal ML-A");
        prevSeg = LayoutPanel.layoutList.getSegment("Mid-Outer Vertical Lower Angle");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel Mid-Outer Vertical Lower Angle Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Outer Vertical ML over Trestle...

		if(LayoutPanel.layoutList.getSegment("Mid-Outer Vertical ML over Trestle") == null) {
	    	LayoutPanel.layoutList.add("Mid-Outer Vertical ML over Trestle", midOuterVertTopX, midOuterVertTopY, midOuterVertBotX, midOuterVertBotY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Outer Vertical ML over Trestle").draw();
		seg = LayoutPanel.layoutList.getSegment("Mid-Outer Vertical Lower Angle");
        prevSeg = LayoutPanel.layoutList.getSegment("Mid-Outer Vertical ML over Trestle");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel Mid-Outer Vertical ML over Trestle Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //OuterVertUpperAngle...

		if(LayoutPanel.layoutList.getSegment("Mid-Outer Vertical Upper Angle ML") == null) {
	    	LayoutPanel.layoutList.add("Mid-Outer Vertical Upper Angle ML", midOuterVertTopX, midOuterVertTopY, midOuterTopLeftX, midOuterTopLeftY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Outer Vertical Upper Angle ML").draw();
		seg = LayoutPanel.layoutList.getSegment("Mid-Outer Vertical ML over Trestle");
		nextSeg = LayoutPanel.layoutList.getSegment("Mid-Outer Vertical Upper Angle ML");
		if(seg != null && prevSeg != null)
			//Changeover from prev links to next links since going from North to East!
			seg.connectFirstSegPtToNextSegFirstSegPt(nextSeg);
		else System.out.println("LPanel Mid-Outer Vertical Upper Angle ML Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//OuterTopML...

        if(LayoutPanel.layoutList.getSegment("Mid-OuterTopML Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("Mid-OuterTopML Horizontal Track", midOuterTopLeftX, midOuterTopLeftY, midOuterTopRightX, midOuterTopRightY);
	    } else LayoutPanel.layoutList.getSegment("Mid-OuterTopML Horizontal Track").draw();
        seg = LayoutPanel.layoutList.getSegment("Mid-Outer Vertical Upper Angle ML");
	    nextSeg = LayoutPanel.layoutList.getSegment("Mid-OuterTopML Horizontal Track");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel Mid-OuterTopML Horizontal Track Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //3 Track sections to form the SL Angle from Outer Top Mid ML to North Bot ML...

	    //Mid-OuterTopML Right Angle To North...

        if(LayoutPanel.layoutList.getSegment("Mid-OuterTopML Right Angle To North") == null) {
	    	LayoutPanel.layoutList.add("Mid-OuterTopML Right Angle To North", midOuterTopRightX, midOuterTopRightY, eastTopLeftSLToMidTopOuterX, eastTopLeftSLToMidTopOuterBotY);
	    } else LayoutPanel.layoutList.getSegment("Mid-OuterTopML Right Angle To North").draw();
        seg = LayoutPanel.layoutList.getSegment("Mid-OuterTopML Horizontal Track");
	    nextSeg = LayoutPanel.layoutList.getSegment("Mid-OuterTopML Right Angle To North");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel Mid-OuterTopML Right Angle To North Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Mid-Vertical Section from OuterTopML to North Bottom...

        if(LayoutPanel.layoutList.getSegment("Mid-Vertical Section from OuterTopML to North Bottom") == null) {
	    	LayoutPanel.layoutList.add("Mid-Vertical Section from OuterTopML to North Bottom", eastTopLeftSLToMidTopOuterX, eastTopLeftSLToMidTopOuterTopY, eastTopLeftSLToMidTopOuterX, eastTopLeftSLToMidTopOuterBotY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Vertical Section from OuterTopML to North Bottom").draw();
        seg = LayoutPanel.layoutList.getSegment("Mid-OuterTopML Right Angle To North");
        nextSeg = LayoutPanel.layoutList.getSegment("Mid-Vertical Section from OuterTopML to North Bottom");
		if(seg != null && nextSeg != null) {
			//Changeover from next links to prev links since going from East to North!
			seg.connectEastSegToNextNorthSeg(nextSeg);
		}else System.out.println("LPanel Mid-Vertical Section from OuterTopML to North Bottom Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Mid-Top SL Section from OuterTopML to North Bottom...

        if(LayoutPanel.layoutList.getSegment("Mid-Top SL Section from OuterTopML to North Bottom") == null) {
	    	LayoutPanel.layoutList.add("Mid-Top SL Section from OuterTopML to North Bottom", northBottomFrogX, northBottomFrogY, eastTopLeftSLToMidTopOuterX, eastTopLeftSLToMidTopOuterTopY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Top SL Section from OuterTopML to North Bottom").draw();
        seg = LayoutPanel.layoutList.getSegment("Mid-Vertical Section from OuterTopML to North Bottom");
        prevSeg = LayoutPanel.layoutList.getSegment("Mid-Top SL Section from OuterTopML to North Bottom");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel Mid-Top SL Section from OuterTopML to North Bottom Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//****************  MainMid-SW0  ***
		if(LayoutPanel.getSwitch("MainMid-SW0") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("MainMid-SW0", false);
	        int slY = northBottomFrogY+30;
	        int slX = LayoutPanel.getXOnLine(eastTopLeftSLToMidTopOuterX, eastTopLeftSLToMidTopOuterTopY, northBottomFrogX, northBottomFrogY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-D").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Mid-Top SL Section from OuterTopML to North Bottom").getSegmentID();
			SwitchSegment sw0 = new SwitchSegment("MainMid-SW0", LayoutPanel.refreshedSwId, mlSeg, slSeg, 46, northBottomFrogX, northBottomFrogY, northBottomLineRightX, northBottomLineY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw0);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-D");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Mid-Top SL Section from OuterTopML to North Bottom");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw0);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
		} else LayoutPanel.getSwitch("MainMid-SW0").draw();
		//********************************


		//InnerBotML Right Angle...

        if(LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML Right Angle To East SW") == null) {
	    	LayoutPanel.layoutList.add("Mid-Inner Bottom ML Right Angle To East SW", midInnerBotRightX, midInnerBotRightY, eastMidLineFrogX, eastMidLineFrogY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML Right Angle To East SW").draw();


        //Swith in East ML Hidden to connect to Mid Lower...
        //****************  MainMid-SW1  ***
        if(LayoutPanel.getSwitch("MainMid-SW1") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("MainMid-SW1", false);
	        int slY = eastMidLineFrogY-30;
	        int slX = LayoutPanel.getXOnLine(midInnerBotRightX, midInnerBotRightY, eastMidLineFrogX, eastMidLineFrogY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vertical ML-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML Right Angle To East SW").getSegmentID();
			SwitchSegment sw1 = new SwitchSegment("MainMid-SW1", LayoutPanel.refreshedSwId, mlSeg, slSeg, 26, eastMidLineFrogX, eastMidLineFrogY, eastMidLineFrogX, eastMidLineFrogY-40, slX, slY, thrown);
			LayoutPanel.switchList.add(sw1);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vertical ML-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML Right Angle To East SW");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw1);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("MainMid-SW1").draw();
		//********************************


		//InnerBotML - Part A...  DONE Splitting up...
		if(LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML-A") == null) {
	    	LayoutPanel.layoutList.add("Mid-Inner Bottom ML-A", midInnerBotLeftX, midInnerBotLeftY, midInnerBotFrogX, midInnerBotFrogY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML-A").draw();

		//InnerBotML - Part B...
		if(LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML-B") == null) {
	    	LayoutPanel.layoutList.add("Mid-Inner Bottom ML-B", midInnerBotFrogX, midInnerBotFrogY, midInnerBotRightX, midInnerBotRightY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML-B").draw();
		seg = LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML Right Angle To East SW");
		prevSeg = LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML-B");
		if(seg != null && prevSeg != null) {
			seg.connectSegmentToPrevSeg(prevSeg);
		}else System.out.println("LPanel Mid-Inner Bottom ML-B Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		seg = LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML-B");
		prevSeg = LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML-A");
		if(seg != null && prevSeg != null) {
			seg.connectSegmentToPrevSeg(prevSeg);
		}else System.out.println("LPanel Mid-Inner Bottom ML-A Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Spur track between InnerBotML & Mid-Outer Bottom Horizontal ML-B...
		if(LayoutPanel.layoutList.getSegment("Mid-Bottom Spur between Inner and Outer ML") == null) {
	    	LayoutPanel.layoutList.add("Mid-Bottom Spur between Inner and Outer ML", midOuterBotFrogX, midOuterBotFrogY, midInnerBotFrogX, midInnerBotFrogY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Bottom Spur between Inner and Outer ML").draw();



 		//Switch from Mid-Bot Outer ML to Mid-Bot Spur...
        //****************  MainMid-SW2  ***
		if(LayoutPanel.getSwitch("MainMid-SW2") == null) {
        boolean thrown = LayoutPanel.refreshSwitchList("MainMid-SW2", false);
        int mlY = midOuterBotFrogY;
        int mlX = midOuterBotFrogX+30;
        int slX = LayoutPanel.getXOnLine(midOuterBotFrogX, midOuterBotFrogY, midInnerBotFrogX, midInnerBotFrogY, midOuterBotFrogY-10);
        //pt1 = Frog pt2 = ML pt3 = Spur
        int mlSeg = LayoutPanel.layoutList.getSegment("Mid-Outer Bottom Horizontal ML-B").getSegmentID();
        int slSeg = LayoutPanel.layoutList.getSegment("Mid-Bottom Spur between Inner and Outer ML").getSegmentID();
 //TODO: Fix SW #!!!
		SwitchSegment sw2 = new SwitchSegment("MainMid-SW2", LayoutPanel.refreshedSwId, mlSeg, slSeg, 442, midOuterBotFrogX, midOuterBotFrogY, mlX, mlY, slX, midOuterBotFrogY-10, thrown);
		LayoutPanel.switchList.add(sw2);
		TrackSegment ML = LayoutPanel.layoutList.getSegment("Mid-Outer Bottom Horizontal ML-B");
		TrackSegment SL = LayoutPanel.layoutList.getSegment("Mid-Bottom Spur between Inner and Outer ML");
		if(ML != null) {
			SegmentPoint sp = ML.insertFrogPoint(sw2);
			SegmentPoint spurSegFirstSp = SL.getFirstPoint();
			sp.setNextSpur(spurSegFirstSp);
			spurSegFirstSp.setPrev(sp);
		}
		} else LayoutPanel.getSwitch("MainMid-SW2").draw();
		//********************************


		  		//Switch from Mid-Bot Inner ML to Mid-Bot Spur...
        //****************  MainMid-SW3  ***
		if(LayoutPanel.getSwitch("MainMid-SW3") == null) {
        boolean thrown = LayoutPanel.refreshSwitchList("MainMid-SW3", false);
        int mlY = midInnerBotFrogY;
        int mlX = midInnerBotFrogX - 30;
        int slX = LayoutPanel.getXOnLine(midOuterBotFrogX, midOuterBotFrogY, midInnerBotFrogX, midInnerBotFrogY, midInnerBotFrogY+10);
        //pt1 = Frog pt2 = ML pt3 = Spur
        int mlSeg = LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML-B").getSegmentID();
        int slSeg = LayoutPanel.layoutList.getSegment("Mid-Bottom Spur between Inner and Outer ML").getSegmentID();
//TODO: Fix SW #!!
		SwitchSegment sw3 = new SwitchSegment("MainMid-SW3", LayoutPanel.refreshedSwId, mlSeg, slSeg, 443, midInnerBotFrogX, midInnerBotFrogY, mlX, mlY, slX, midInnerBotFrogY+10, thrown);
		LayoutPanel.switchList.add(sw3);
		TrackSegment ML = LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML-B");
		TrackSegment SL = LayoutPanel.layoutList.getSegment("Mid-Bottom Spur between Inner and Outer ML");
		if(ML != null) {
			SegmentPoint sp = ML.insertFrogPoint(sw3);
			SegmentPoint spurSegLastSp = SL.getLastPoint();
			sp.setPrevSpur(spurSegLastSp);
			spurSegLastSp.setNext(sp);
		}
		} else LayoutPanel.getSwitch("MainMid-SW3").draw();
		//********************************




        //InnerVertLowerAngle...

		if(LayoutPanel.layoutList.getSegment("Mid-Inner Vertical Lower Angle") == null) {
	    	LayoutPanel.layoutList.add("Mid-Inner Vertical Lower Angle", midInnerVertBotX, midInnerVertBotY, midInnerBotLeftX, midInnerBotLeftY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Inner Vertical Lower Angle").draw();
		seg = LayoutPanel.layoutList.getSegment("Mid-Inner Vertical Lower Angle");
	    nextSeg = LayoutPanel.layoutList.getSegment("Mid-Inner Bottom ML-A");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel Mid-Inner Vertical Lower Angle Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Inner Vertical ML...

	    if(LayoutPanel.layoutList.getSegment("Mid-Inner Vertical ML") == null) {
	    	LayoutPanel.layoutList.add("Mid-Inner Vertical ML", midInnerVertTopX, midInnerVertTopY, midInnerVertBotX, midInnerVertBotY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Inner Vertical ML").draw();
	    seg = LayoutPanel.layoutList.getSegment("Mid-Inner Vertical Lower Angle");
        prevSeg = LayoutPanel.layoutList.getSegment("Mid-Inner Vertical ML");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel Mid-Inner Vertical ML Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Inner Vertical Upper Angle...

		if(LayoutPanel.layoutList.getSegment("Mid-Inner Vertical Upper Angle") == null) {
	    	LayoutPanel.layoutList.add("Mid-Inner Vertical Upper Angle", midInnerVertTopX, midInnerVertTopY, midInnerTopLeftX, midInnerTopLeftY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Inner Vertical Upper Angle").draw();
		seg = LayoutPanel.layoutList.getSegment("Mid-Inner Vertical ML");
		nextSeg = LayoutPanel.layoutList.getSegment("Mid-Inner Vertical Upper Angle");
		if(seg != null && prevSeg != null)
			//Changeover from prev links to next links since going from North to East!
			seg.connectFirstSegPtToNextSegFirstSegPt(nextSeg);
		else System.out.println("LPanel Mid-Inner Vertical Upper Angle Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Mid-Inner Top Horizontal ML...

		if(LayoutPanel.layoutList.getSegment("Mid-Inner Top Horizontal ML") == null) {
	    	LayoutPanel.layoutList.add("Mid-Inner Top Horizontal ML", midInnerTopLeftX, midInnerTopLeftY, midInnerTopRightX, midInnerTopRightY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Inner Top Horizontal ML").draw();
		seg = LayoutPanel.layoutList.getSegment("Mid-Inner Vertical Upper Angle");
	    nextSeg = LayoutPanel.layoutList.getSegment("Mid-Inner Top Horizontal ML");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel Mid-Inner Top Horizontal ML Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//InnerTopML Right Angle to SL on NE ML

	    if(LayoutPanel.layoutList.getSegment("Mid-Inner TopML Right Angle to SL on NE ML") == null) {
	    	LayoutPanel.layoutList.add("Mid-Inner TopML Right Angle to SL on NE ML", midInnerTopRightX, midInnerTopRightY, eastTopSLToMidTopInnerBotX, eastTopSLToMidTopInnerBotY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Inner TopML Right Angle to SL on NE ML").draw();
	    seg = LayoutPanel.layoutList.getSegment("Mid-Inner Top Horizontal ML");
	    nextSeg = LayoutPanel.layoutList.getSegment("Mid-Inner TopML Right Angle to SL on NE ML");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel Mid-Inner TopML Right Angle to SL on NE ML Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //SL Angle from Far Rt Vert on Mid Section to Inner Upper ML on Mid Section...
        if(LayoutPanel.layoutList.getSegment("Mid-Far Rt Vert SL Angle to Inner Upper ML") == null) {
	    	LayoutPanel.layoutList.add("Mid-Far Rt Vert SL Angle to Inner Upper ML", eastTopSLToMidTopInnerFrogX, eastTopSLToMidTopInnerFrogY, eastTopSLToMidTopInnerBotX, eastTopSLToMidTopInnerBotY);
	    }else LayoutPanel.layoutList.getSegment("Mid-Far Rt Vert SL Angle to Inner Upper ML").draw();

        seg = LayoutPanel.layoutList.getSegment("Mid-Inner TopML Right Angle to SL on NE ML");
        nextSeg = LayoutPanel.layoutList.getSegment("Mid-Far Rt Vert SL Angle to Inner Upper ML");
		if(seg != null && nextSeg != null) {
			//Changeover from next links to prev links since going from East to North!
			seg.connectEastSegToNextNorthSeg(nextSeg);
		}else System.out.println("LPanel Mid-Far Rt Vert SL Angle to Inner Upper ML Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Mid-Far Right Vertical Top Angle Track A...
        if(LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical Top Angle Track-A") == null) {
	    	LayoutPanel.layoutList.add("Mid-Far Right Vertical Top Angle Track-A", northBottomLineRightX, northBottomLineY, eastTopSLToMidTopInnerFrogX, eastTopSLToMidTopInnerFrogY);
	    }else LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical Top Angle Track-A").draw();

        //Link to North Bottom ML right Point...
        seg = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-D");
        nextSeg = LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical Top Angle Track-A");
		if(seg != null && nextSeg != null) {
			seg.connectSegmentToNextSeg(nextSeg);
		}else System.out.println("LPanel Mid-Far Right Vertical Top Angle Track-A Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Mid-Far Right Vertical Top Angle Track A...
		if(LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical Top Angle Track-B") == null) {
	    	LayoutPanel.layoutList.add("Mid-Far Right Vertical Top Angle Track-B", eastTopSLToMidTopInnerFrogX, eastTopSLToMidTopInnerFrogY, midFarRtVertX, midFarRtVertTopY);
	    }else LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical Top Angle Track-B").draw();

        //Link to Mid-Far Right Vertical Top Angle Track-A...
        seg = LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical Top Angle Track-A");
        nextSeg = LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical Top Angle Track-B");
		if(seg != null && nextSeg != null) {
			seg.connectSegmentToNextSeg(nextSeg);
		}else System.out.println("LPanel Mid-Far Right Vertical Top Angle Track-B Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //****************  MainMid-SW4  ***
		if(LayoutPanel.getSwitch("MainMid-SW4") == null) {
        boolean thrown = LayoutPanel.refreshSwitchList("MainMid-SW4", false);
        int mlY = eastTopSLToMidTopInnerFrogY+40;
        int mlX = LayoutPanel.getXOnLine(midFarRtVertX, midFarRtVertTopY, northBottomLineRightX, northBottomLineY, mlY);
        //pt1 = Frog pt2 = ML pt3 = Spur
        int mlSeg = LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical Top Angle Track-A").getSegmentID();
        int slSeg = LayoutPanel.layoutList.getSegment("Mid-Far Rt Vert SL Angle to Inner Upper ML").getSegmentID();
		SwitchSegment sw4 = new SwitchSegment("MainMid-SW4", LayoutPanel.refreshedSwId, mlSeg, slSeg, 42, eastTopSLToMidTopInnerFrogX, eastTopSLToMidTopInnerFrogY, mlX, mlY, eastTopSLToMidTopInnerBotX, eastTopSLToMidTopInnerBotY-20, thrown);
		LayoutPanel.switchList.add(sw4);
		TrackSegment ML = LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical Top Angle Track-A");
		TrackSegment SL = LayoutPanel.layoutList.getSegment("Mid-Far Rt Vert SL Angle to Inner Upper ML");
		if(ML != null) {
			SegmentPoint sp = ML.insertFrogPoint(sw4);
			SegmentPoint spurSegFirstSp = SL.getFirstPoint();
			sp.setNextSpur(spurSegFirstSp);
			spurSegFirstSp.setPrev(sp);
		}
		} else LayoutPanel.getSwitch("MainMid-SW4").draw();
		//********************************



		//Far Right Vertical on Middle Section...

		if(LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical") == null) {
	    	LayoutPanel.layoutList.add("Mid-Far Right Vertical", midFarRtVertX, midFarRtVertTopY, midFarRtVertX, midFarRtVertBotY);
	    }else LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical").draw();
		seg = LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical Top Angle Track-B");
	    nextSeg = LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel Mid-Far Right Vertical Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Mid Far Right Bottom Angle Above East Near Vert ML...

		if(LayoutPanel.layoutList.getSegment("Mid-Far Right Bottom Angle Above East Near Vert ML") == null) {
	    	LayoutPanel.layoutList.add("Mid-Far Right Bottom Angle Above East Near Vert ML", midFarRtVertX, midFarRtVertBotY, eastMidLineX, eastMidLineTopY);
	    }else LayoutPanel.layoutList.getSegment("Mid-Far Right Bottom Angle Above East Near Vert ML").draw();
		seg = LayoutPanel.layoutList.getSegment("Mid-Far Right Bottom Angle Above East Near Vert ML");
		prevSeg = LayoutPanel.layoutList.getSegment("Mid-Far Right Vertical");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else  System.out.println("LPanel Mid-Far Right Bottom Angle Above East Near Vert ML Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	}
}
