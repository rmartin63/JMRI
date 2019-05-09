package apps.gui3.tcs;

import java.awt.Color;

public class LayoutPanelDrawNorth {

	private static LayoutPanelDrawNorth _instance = null;  //A singleton class!

	private LayoutPanel lp = null;

	//North Points
	private int northTopLineLeftX, northTopLineRightX, northTopLineY = 0;
	private int northMidLineLeftX, northMidLineRightX, northMidLineY = 0;
	private int northBottomLineLeftX, northBottomLineRightX, northBottomLineY = 0;
	private int northBottomFrogX, northBottomFrogY = 0;

	public int getnorthBottomLineRightX() { return northBottomLineRightX;}
	public int getnorthBottomLineY() { return northBottomLineY;}
	public int getnorthBottomFrogX() { return northBottomFrogX;}
	public int getnorthBottomFrogY() { return northBottomFrogY;}
	public int getnorthMidLineRightX() { return northMidLineRightX;}
	public int getnorthMidLineY() { return northMidLineY;}
	public int getnorthMidLineLeftX() { return northMidLineLeftX;}
	public int getnorthBottomLineLeftX() { return northBottomLineLeftX;}

	private int northBotLeftFrogX, northBotLeftFrogY, northBotMidFrogX, northBotMidFrogY = 0;
	private int northMidLeftFrogX, northMidLeftFrogY, northMidMidLeftFrogX, northMidMidLeftFrogY = 0;
	private int northMidMidRtFrogX, northMidMidRtFrogY, northMidRtFrogX, northMidRtFrogY = 0;
	private int northTopMLLeftFrogX, northTopMLLeftFrogY, northTopMLRtFrogX, northTopMLRtFrogY = 0;
	private int northRightMidSidingRightX, northRightMidSidingLeftX, northRightMidSidingY = 0;
	private int northRightMidSidingFrogX, northRightMidSidingFrogY = 0;
	private int northRightBotSidingRightX, northRightBotSidingLeftX, northRightBotSidingY = 0;
	private int northRightBotSidingFrogX, northRightBotSidingFrogY = 0;
	private int northRtTopCoalSpurLeftX, northRtTopCoalSpurRightX, northRtTopCoalSpurY = 0;
	private int northRtTopCoalSpurFrogX, northRtTopCoalSpurFrogY = 0;
	private int northRtBotCoalSpurRightX, northRtBotCoalSpurY, northRtBotCoalSpurFrogX, northRtBotCoalSpurFrogY = 0;

	public int getnorthRtBotCoalSpurFrogX() { return northRtBotCoalSpurFrogX;}
	public int getnorthRtBotCoalSpurFrogY() { return northRtBotCoalSpurFrogY;}
	public int getnorthRtBotCoalSpurRightX() { return northRtBotCoalSpurRightX;}
	public int getnorthRtBotCoalSpurY() { return northRtBotCoalSpurY;}

	public int getnorthRtTopCoalSpurFrogX() { return  northRtTopCoalSpurFrogX;}
	public int getnorthRtTopCoalSpurFrogY() { return northRtTopCoalSpurFrogY;}
	public int getnorthRtTopCoalSpurLeftX() { return northRtTopCoalSpurLeftX;}
	public int getnorthRtTopCoalSpurY() { return northRtTopCoalSpurY;}
	public int getnorthRtTopCoalSpurRightX() { return northRtTopCoalSpurRightX;}


	public LayoutPanelDrawNorth() {
		// Instantiate and Initialize...
		if (lp == null) lp = LayoutPanel.getInstance();
	}

	public static LayoutPanelDrawNorth getInstance() {
		if(_instance == null) {
			_instance = new LayoutPanelDrawNorth();
		}
		return _instance;
	}

	private int eastFarRightLineX, eastMidLineX, eastMidLineTopY = 0;

	private void initializePointsFromOtherSections() {
		eastFarRightLineX = (int)(LayoutPanel.pSize.getWidth())-10;
		eastMidLineX = (int)(LayoutPanel.pSize.getWidth()/1.2);
		eastMidLineTopY = (int)(LayoutPanel.pSize.getHeight()/4);

	}

	public void initializePoints() {

		initializePointsFromOtherSections();

		northTopLineLeftX = (int)(LayoutPanel.pSize.getWidth()/3)+50;
		northTopLineRightX = (int)(LayoutPanel.pSize.getWidth())-100;
		northTopLineY = 25;

		northRightMidSidingRightX = northTopLineRightX;
		northRightMidSidingLeftX = northTopLineRightX-80;
		northRightMidSidingY = northTopLineY+20;
		northRightMidSidingFrogX = northRightMidSidingLeftX-20;
		northRightMidSidingFrogY = northTopLineY;

		northRightBotSidingRightX = northTopLineRightX;
		northRightBotSidingLeftX = northRightMidSidingLeftX-30;
		northRightBotSidingY = northRightMidSidingY+20;
		northRightBotSidingFrogX = northRightBotSidingLeftX-40;
		northRightBotSidingFrogY = northTopLineY;

		northMidLineLeftX = (int)(LayoutPanel.pSize.getWidth()/2.61);
		northMidLineRightX = (int)(LayoutPanel.pSize.getWidth()/1.35);//1.32);
		northMidLineY = 55;//70;
		northBottomLineLeftX = (int)(LayoutPanel.pSize.getWidth()/3)+60;
		northBottomLineRightX = (int)(LayoutPanel.pSize.getWidth()/1.35);
		northBottomLineY = 90;
		northBottomFrogX = northBottomLineRightX-40;
		northBottomFrogY = northBottomLineY;

		northBotLeftFrogX = (int)(LayoutPanel.pSize.getWidth()/2);//northBottomLineLeftX+80;
		northBotLeftFrogY = northBottomLineY;

		northMidLeftFrogX = northBotLeftFrogX-100;
		northMidLeftFrogY = northMidLineY;

		northMidMidLeftFrogX = northBotLeftFrogX-55;//40;
		northMidMidLeftFrogY = northMidLeftFrogY;

		northMidMidRtFrogX = (int)(LayoutPanel.pSize.getWidth()/1.6);
		northMidMidRtFrogY = northMidLeftFrogY;

		northBotMidFrogX = northMidMidRtFrogX-50;
		northBotMidFrogY = northBotLeftFrogY;


		northMidRtFrogX = northMidMidRtFrogX+110;
		northMidRtFrogY = northMidLeftFrogY;

		northTopMLLeftFrogX = northMidMidLeftFrogX+20;
		northTopMLLeftFrogY = northTopLineY;
		northTopMLRtFrogX = northMidMidRtFrogX+30;
		northTopMLRtFrogY = northTopMLLeftFrogY;

		//NOTE: See rest of northRtTopCoalSpur below...
		northRtTopCoalSpurRightX = eastFarRightLineX;
		northRtTopCoalSpurY = northRightBotSidingY+40;

		northRtBotCoalSpurRightX = northRtTopCoalSpurRightX;
		northRtBotCoalSpurY = northRtTopCoalSpurY+20;
		northRtBotCoalSpurFrogX = LayoutPanel.getXOnLine(northMidLineRightX, northMidLineY, eastMidLineX, eastMidLineTopY, northRtBotCoalSpurY);
		northRtBotCoalSpurFrogY = northRtBotCoalSpurY;

		northRtTopCoalSpurFrogX = northRtBotCoalSpurFrogX+30;
		northRtTopCoalSpurFrogY = northRtBotCoalSpurY;
		northRtTopCoalSpurLeftX = northRtTopCoalSpurFrogX+20;

	}

	public void draw() {

		initializePoints();

		//Draw North 3rd from Top ML Track...  DONE Splitting up...
		//Draw North 3rd from Top ML Track - Part A...
        if(LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-A") == null) {
	    	LayoutPanel.layoutList.add("North-Bottom [3rd] ML-A", northBottomLineLeftX, northBottomLineY, northBotLeftFrogX, northBotLeftFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-A").draw();
        TrackSegment seg = LayoutPanel.layoutList.getSegment("West-Right ML Top Angle");
		TrackSegment nextSeg = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-A");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Bottom [3rd] ML-A Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Draw North 3rd from Top ML Track - Part B...
        if(LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-B") == null) {
	    	LayoutPanel.layoutList.add("North-Bottom [3rd] ML-B", northBotLeftFrogX, northBotLeftFrogY, northBotMidFrogX, northBotMidFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-B").draw();
        seg = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-A");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-B");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Bottom [3rd] ML-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Draw North 3rd from Top ML Track - Part C...
        if(LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-C") == null) {
	    	LayoutPanel.layoutList.add("North-Bottom [3rd] ML-C", northBotMidFrogX, northBotMidFrogY, northBottomFrogX, northBottomFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-C").draw();
        seg = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-B");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-C");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Bottom [3rd] ML-C Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Draw North 3rd from Top ML Track - Part D...
        if(LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-D") == null) {
	    	LayoutPanel.layoutList.add("North-Bottom [3rd] ML-D", northBottomFrogX, northBottomFrogY, northBottomLineRightX, northBottomLineY);
	    } else LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-D").draw();
        seg = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-C");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-D");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Bottom [3rd] ML-D Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());



		//Draw North Mid ML Track - 4 frogs - 5 splits...   DONE Splitting up...
		//North-Mid ML Track - Part A...
		if(LayoutPanel.layoutList.getSegment("North-Mid ML Track-A") == null) {
	    	LayoutPanel.layoutList.add("North-Mid ML Track-A", northMidLineLeftX, northMidLineY, northMidLeftFrogX, northMidLeftFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Mid ML Track-A").draw();

		//North-Mid ML Track - Part B...
		if(LayoutPanel.layoutList.getSegment("North-Mid ML Track-B") == null) {
	    	LayoutPanel.layoutList.add("North-Mid ML Track-B", northMidLeftFrogX, northMidLeftFrogY, northMidMidLeftFrogX, northMidMidLeftFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Mid ML Track-B").draw();
        seg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-A");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-B");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Mid ML Track-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//North-Mid ML Track - Part C...
		if(LayoutPanel.layoutList.getSegment("North-Mid ML Track-C") == null) {
	    	LayoutPanel.layoutList.add("North-Mid ML Track-C", northMidMidLeftFrogX, northMidMidLeftFrogY, northMidMidRtFrogX, northMidMidRtFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Mid ML Track-C").draw();
        seg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-B");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-C");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Mid ML Track-C Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//North-Mid ML Track - Part D...
		if(LayoutPanel.layoutList.getSegment("North-Mid ML Track-D") == null) {
	    	LayoutPanel.layoutList.add("North-Mid ML Track-D", northMidMidRtFrogX, northMidMidRtFrogY, northMidRtFrogX, northMidRtFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Mid ML Track-D").draw();
        seg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-C");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-D");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Mid ML Track-D Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//North-Mid ML Track - Part E...
		if(LayoutPanel.layoutList.getSegment("North-Mid ML Track-E") == null) {
	    	LayoutPanel.layoutList.add("North-Mid ML Track-E", northMidRtFrogX, northMidRtFrogY, northMidLineRightX, northMidLineY);
	    } else LayoutPanel.layoutList.getSegment("North-Mid ML Track-E").draw();
        seg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-D");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-E");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Mid ML Track-E Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Link to West-Left ML Top Angle
		seg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-A");
		TrackSegment prevSeg = LayoutPanel.layoutList.getSegment("West-Left ML Top Angle");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel North-Mid ML Track Else #2 seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Draw North Top ML Track - split up - 4 frogs -   DONE Splitting up...
		//North-Top ML Track - Part A...
        if(LayoutPanel.layoutList.getSegment("North-Top ML Track-A") == null) {
	    	LayoutPanel.layoutList.add("North-Top ML Track-A", northTopLineLeftX, northTopLineY, northTopMLLeftFrogX, northTopMLLeftFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Top ML Track-A").draw();

		//North-Top ML Track - Part B...
        if(LayoutPanel.layoutList.getSegment("North-Top ML Track-B") == null) {
	    	LayoutPanel.layoutList.add("North-Top ML Track-B", northTopMLLeftFrogX, northTopMLLeftFrogY, northTopMLRtFrogX, northTopMLRtFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Top ML Track-B").draw();
        seg = LayoutPanel.layoutList.getSegment("North-Top ML Track-A");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Top ML Track-B");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Top ML Track-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//North-Top ML Track - Part C...
        if(LayoutPanel.layoutList.getSegment("North-Top ML Track-C") == null) {
	    	LayoutPanel.layoutList.add("North-Top ML Track-C", northTopMLRtFrogX, northTopMLRtFrogY, northRightBotSidingFrogX, northRightBotSidingFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Top ML Track-C").draw();
        seg = LayoutPanel.layoutList.getSegment("North-Top ML Track-B");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Top ML Track-C");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Top ML Track-C Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//North-Top ML Track - Part D...
        if(LayoutPanel.layoutList.getSegment("North-Top ML Track-D") == null) {
	    	LayoutPanel.layoutList.add("North-Top ML Track-D", northRightBotSidingFrogX, northRightBotSidingFrogY, northRightMidSidingFrogX, northRightMidSidingFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Top ML Track-D").draw();
        seg = LayoutPanel.layoutList.getSegment("North-Top ML Track-C");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Top ML Track-D");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Top ML Track-D Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//North-Top ML Track - Part E...
        if(LayoutPanel.layoutList.getSegment("North-Top ML Track-E") == null) {
	    	LayoutPanel.layoutList.add("North-Top ML Track-E", northRightMidSidingFrogX, northRightMidSidingFrogY, northTopLineRightX, northTopLineY);
	    } else LayoutPanel.layoutList.getSegment("North-Top ML Track-E").draw();
        seg = LayoutPanel.layoutList.getSegment("North-Top ML Track-D");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Top ML Track-E");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Top ML Track-E Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Left SL from Bottom to Mid...

        if(LayoutPanel.layoutList.getSegment("North-Left SL from Bottom to Mid") == null) {
	    	LayoutPanel.layoutList.add("North-Left SL from Bottom to Mid", northMidMidLeftFrogX, northMidMidLeftFrogY, northBotLeftFrogX, northBotLeftFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Left SL from Bottom to Mid").draw();

        //****************  North-SW1  ***
        if(LayoutPanel.getSwitch("North-SW1") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("North-SW1", false);
	        int slY = northBotLeftFrogY-15;
	        int slX = LayoutPanel.getXOnLine(northBotLeftFrogX, northBotLeftFrogY, northMidMidLeftFrogX, northMidMidLeftFrogY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("North-Left SL from Bottom to Mid").getSegmentID();
			SwitchSegment sw1 = new SwitchSegment("North-SW1", LayoutPanel.refreshedSwId, mlSeg, slSeg, 56, northBotLeftFrogX, northBotLeftFrogY, northBotLeftFrogX-35, northBotLeftFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw1);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("North-Left SL from Bottom to Mid");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw1);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("North-SW1").draw();
		//********************************

		//****************  North-SW2  ***
        if(LayoutPanel.getSwitch("North-SW2") == null) {
        	boolean thrown = LayoutPanel.refreshSwitchList("North-SW2", false);
        int slY = northMidMidLeftFrogY+15;
        int slX = LayoutPanel.getXOnLine(northBotLeftFrogX, northBotLeftFrogY, northMidMidLeftFrogX, northMidMidLeftFrogY, slY);
        //pt1 = Frog pt2 = ML pt3 = Spur
        int mlSeg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-C").getSegmentID();
        int slSeg = LayoutPanel.layoutList.getSegment("North-Left SL from Bottom to Mid").getSegmentID();
		SwitchSegment sw2 = new SwitchSegment("North-SW2", LayoutPanel.refreshedSwId, mlSeg, slSeg, 58, northMidMidLeftFrogX, northMidMidLeftFrogY, northMidMidLeftFrogX+30, northMidMidLeftFrogY, slX, slY, thrown);
		LayoutPanel.switchList.add(sw2);
		TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Mid ML Track-C");
		TrackSegment SL = LayoutPanel.layoutList.getSegment("North-Left SL from Bottom to Mid");
		if(ML != null) {
			SegmentPoint sp = ML.insertFrogPoint(sw2);
			SegmentPoint spurSegFirstSp = SL.getFirstPoint();
			sp.setNextSpur(spurSegFirstSp);
			spurSegFirstSp.setPrev(sp);
		}
        } else LayoutPanel.getSwitch("North-SW2").draw();
		//********************************


		//Right SL from Bottom to Mid...

		if(LayoutPanel.layoutList.getSegment("North-Right SL from Bottom to Mid") == null) {
	    	LayoutPanel.layoutList.add("North-Right SL from Bottom to Mid", northBotMidFrogX, northBotMidFrogY, northMidMidRtFrogX, northMidMidRtFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Right SL from Bottom to Mid").draw();


		//****************  North-SW3  ***
		if(LayoutPanel.getSwitch("North-SW3") == null) {
			boolean thrown = LayoutPanel.refreshSwitchList("North-SW3", false);
	        int slY = northBotMidFrogY-15;
	        int slX = LayoutPanel.getXOnLine(northBotMidFrogX, northBotMidFrogY, northMidMidRtFrogX, northMidMidRtFrogY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-C").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("North-Right SL from Bottom to Mid").getSegmentID();
			SwitchSegment sw3 = new SwitchSegment("North-SW3", LayoutPanel.refreshedSwId, mlSeg, slSeg, 53, northBotMidFrogX, northBotMidFrogY, northBotMidFrogX+35, northBotMidFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw3);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Bottom [3rd] ML-C");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("North-Right SL from Bottom to Mid");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw3);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
		} else LayoutPanel.getSwitch("North-SW3").draw();
		//********************************

		//****************  North-SW4  ***
		if(LayoutPanel.getSwitch("North-SW4") == null) {
			boolean thrown = LayoutPanel.refreshSwitchList("North-SW4", false);
	        int slY = northMidMidRtFrogY+15;
	        int slX = LayoutPanel.getXOnLine(northBotMidFrogX, northBotMidFrogY, northMidMidRtFrogX, northMidMidRtFrogY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-D").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("North-Right SL from Bottom to Mid").getSegmentID();
			SwitchSegment sw4 = new SwitchSegment("North-SW4", LayoutPanel.refreshedSwId, mlSeg, slSeg, 52, northMidMidRtFrogX, northMidMidRtFrogY, northMidMidRtFrogX-30, northMidMidRtFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw4);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Mid ML Track-D");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("North-Right SL from Bottom to Mid");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw4);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
		} else LayoutPanel.getSwitch("North-SW4").draw();
		//********************************


		//Left SL from Mid to Top...

    	if(LayoutPanel.layoutList.getSegment("North-Left SL from Mid to Top") == null) {
	    	LayoutPanel.layoutList.add("North-Left SL from Mid to Top", northMidLeftFrogX, northMidLeftFrogY, northTopMLLeftFrogX, northTopMLLeftFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Left SL from Mid to Top").draw();

    	//****************  North-SW5  ***
    	if(LayoutPanel.getSwitch("North-SW5") == null) {
    		boolean thrown = LayoutPanel.refreshSwitchList("North-SW5", false);
	        int slY = northMidLeftFrogY-12;
	        int slX = LayoutPanel.getXOnLine(northMidLeftFrogX, northMidLeftFrogY, northTopMLLeftFrogX, northTopMLLeftFrogY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("North-Left SL from Mid to Top").getSegmentID();
			SwitchSegment sw5 = new SwitchSegment("North-SW5", LayoutPanel.refreshedSwId, mlSeg, slSeg, 59, northMidLeftFrogX, northMidLeftFrogY, northMidLeftFrogX+35, northMidLeftFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw5);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Mid ML Track-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("North-Left SL from Mid to Top");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw5);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
    	} else LayoutPanel.getSwitch("North-SW5").draw();
		//********************************

		//****************  North-SW6  ***
		if(LayoutPanel.getSwitch("North-SW6") == null) {
			boolean thrown = LayoutPanel.refreshSwitchList("North-SW6", false);
	        int slY = northTopMLLeftFrogY+12;
	        int slX = LayoutPanel.getXOnLine(northMidLeftFrogX, northMidLeftFrogY, northTopMLLeftFrogX, northTopMLLeftFrogY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("North-Top ML Track-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("North-Left SL from Mid to Top").getSegmentID();
			SwitchSegment sw6 = new SwitchSegment("North-SW6", LayoutPanel.refreshedSwId, mlSeg, slSeg, 57, northTopMLLeftFrogX, northTopMLLeftFrogY, northTopMLLeftFrogX-30, northTopMLLeftFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw6);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Top ML Track-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("North-Left SL from Mid to Top");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw6);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
		} else LayoutPanel.getSwitch("North-SW6").draw();
		//********************************


		//Right SL from Mid to Top...

    	if(LayoutPanel.layoutList.getSegment("North-Right SL from Mid to Top") == null) {
	    	LayoutPanel.layoutList.add("North-Right SL from Mid to Top", northTopMLRtFrogX, northTopMLRtFrogY, northMidRtFrogX, northMidRtFrogY);
	    } else LayoutPanel.layoutList.getSegment("North-Right SL from Mid to Top").draw();


    	//****************  North-SW7  ***
    	if(LayoutPanel.getSwitch("North-SW7") == null) {
    		boolean thrown = LayoutPanel.refreshSwitchList("North-SW7", false);
	        int slY = northMidRtFrogY-13;
	        int slX = LayoutPanel.getXOnLine(northMidRtFrogX, northMidRtFrogY, northTopMLRtFrogX, northTopMLRtFrogY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-E").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("North-Right SL from Mid to Top").getSegmentID();
			SwitchSegment sw7 = new SwitchSegment("North-SW7", LayoutPanel.refreshedSwId, mlSeg, slSeg, 47, northMidRtFrogX, northMidRtFrogY, northMidRtFrogX-40, northMidRtFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw7);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Mid ML Track-E");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("North-Right SL from Mid to Top");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw7);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
    	} else LayoutPanel.getSwitch("North-SW7").draw();
		//********************************

		//****************  North-SW8  ***
		if(LayoutPanel.getSwitch("North-SW8") == null) {
			boolean thrown = LayoutPanel.refreshSwitchList("North-SW8", false);
	        int slY = northTopMLRtFrogY+13;
	        int slX = LayoutPanel.getXOnLine(northMidRtFrogX, northMidRtFrogY, northTopMLRtFrogX, northTopMLRtFrogY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("North-Top ML Track-C").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("North-Right SL from Mid to Top").getSegmentID();
			SwitchSegment sw8 = new SwitchSegment("North-SW8", LayoutPanel.refreshedSwId, mlSeg, slSeg, 54, northTopMLRtFrogX, northTopMLRtFrogY, northTopMLRtFrogX+40, northTopMLRtFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw8);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Top ML Track-C");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("North-Right SL from Mid to Top");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw8);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
		} else LayoutPanel.getSwitch("North-SW8").draw();
		//********************************


		//Right Mid Siding SL Switch Spur

    	if(LayoutPanel.layoutList.getSegment("North-Right Mid Siding SL Switch Spur") == null) {
	    	LayoutPanel.layoutList.add("North-Right Mid Siding SL Switch Spur", northRightMidSidingFrogX, northRightMidSidingFrogY, northRightMidSidingLeftX, northRightMidSidingY);
	    } else LayoutPanel.layoutList.getSegment("North-Right Mid Siding SL Switch Spur").draw();


    	//****************  North-SW9  ***
    	if(LayoutPanel.getSwitch("North-SW9") == null) {
    		boolean thrown = LayoutPanel.refreshSwitchList("North-SW9", false);
	        int slY = northRightMidSidingFrogY+15;
	        int slX = LayoutPanel.getXOnLine(northRightMidSidingFrogX, northRightMidSidingFrogY, northRightMidSidingLeftX, northRightMidSidingY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("North-Top ML Track-E").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("North-Right Mid Siding SL Switch Spur").getSegmentID();
			SwitchSegment sw9 = new SwitchSegment("North-SW9", LayoutPanel.refreshedSwId, mlSeg, slSeg, 48, northRightMidSidingFrogX, northRightMidSidingFrogY, northRightMidSidingFrogX+35, northRightMidSidingFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw9);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Top ML Track-E");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("North-Right Mid Siding SL Switch Spur");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw9);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
    	} else LayoutPanel.getSwitch("North-SW9").draw();
		//********************************


		//Right Middle Siding...

		if(LayoutPanel.layoutList.getSegment("North-Right Middle Siding") == null) {
	    	LayoutPanel.layoutList.add("North-Right Middle Siding", northRightMidSidingLeftX, northRightMidSidingY, northRightMidSidingRightX, northRightMidSidingY);
	    } else LayoutPanel.layoutList.getSegment("North-Right Middle Siding").draw();
		seg = LayoutPanel.layoutList.getSegment("North-Right Mid Siding SL Switch Spur");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Right Middle Siding");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Right Middle Siding Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Right Bottom Siding SL Switch Spur

    	if(LayoutPanel.layoutList.getSegment("North-Right Bottom Siding SL Switch Spur") == null) {
	    	LayoutPanel.layoutList.add("North-Right Bottom Siding SL Switch Spur", northRightBotSidingFrogX, northRightBotSidingFrogY, northRightBotSidingLeftX, northRightBotSidingY);
	    } else LayoutPanel.layoutList.getSegment("North-Right Bottom Siding SL Switch Spur").draw();

    	//****************  North-SW10  ***
    	if(LayoutPanel.getSwitch("North-SW10") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("North-SW10", false);
	        int slY = northRightBotSidingFrogY+25;
	        int slX = LayoutPanel.getXOnLine(northRightBotSidingFrogX, northRightBotSidingFrogY, northRightBotSidingLeftX, northRightBotSidingY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("North-Top ML Track-D").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("North-Right Bottom Siding SL Switch Spur").getSegmentID();
			SwitchSegment sw10 = new SwitchSegment("North-SW10", LayoutPanel.refreshedSwId, mlSeg, slSeg, 49, northRightBotSidingFrogX, northRightBotSidingFrogY, northRightBotSidingFrogX+35, northRightBotSidingFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw10);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("North-Top ML Track-D");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("North-Right Bottom Siding SL Switch Spur");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw10);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
    	} else LayoutPanel.getSwitch("North-SW10").draw();
		//********************************


		//Right Bottom Siding...

		if(LayoutPanel.layoutList.getSegment("North-Right Bottom Siding") == null) {
	    	LayoutPanel.layoutList.add("North-Right Bottom Siding", northRightBotSidingLeftX, northRightBotSidingY, northRightBotSidingRightX, northRightBotSidingY);
	    } else LayoutPanel.layoutList.getSegment("North-Right Bottom Siding").draw();
		seg = LayoutPanel.layoutList.getSegment("North-Right Bottom Siding SL Switch Spur");
		nextSeg = LayoutPanel.layoutList.getSegment("North-Right Bottom Siding");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel North-Right Bottom Siding Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	}
}
