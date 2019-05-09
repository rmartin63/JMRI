package apps.gui3.tcs;

import java.awt.Color;

public class LayoutPanelDrawMidUpper {

	private static LayoutPanelDrawMidUpper _instance = null;  //A singleton class!

	private LayoutPanel lp = null;

	private LayoutPanelDrawMid mid = null;
	private LayoutPanelDrawEast east = null;

	//Mid Upper Points
	private int midUpDeckTopMLHorY, midUpDeckTopMLHorLeftX, midUpDeckTopMLHorRightX, midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY = 0;
	private int midUpDeckBotMLHorY, midUpDeckBotMLHorLeftX, midUpDeckBotMLHorRightX = 0;
	private int midUpDeckVertMLX, midUpDeckVertMLTopY, midUpDeckVertMLBotY = 0;
	private int midUpDeckMLTopAngleTopX, midUpDeckMLTopAngleTopY, midUpDeckMLTopAngleBotX, midUpDeckMLTopAngleBotY = 0;
	private int midUpDeckMLBotAngleTopX, midUpDeckMLBotAngleTopY, midUpDeckMLBotAngleBotX, midUpDeckMLBotAngleBotY = 0;

	static private int midMidDeckMLHelixFrogX, midMidDeckMLHelixFrogY = 0;
	static public int getMidMidDeckMLHelixFrogX(){return midMidDeckMLHelixFrogX;}
	static public int getMidMidDeckMLHelixFrogY(){return midMidDeckMLHelixFrogY;}
	private int midMidDeckMLToSawMillSpurFrogY, midMidDeckMLToSawMillSpurFrogX, midSawMillFrogToSMillY, midSawMillFrogToSMillX = 0;
	private int midSawMillSpurLeftEndX, midSawMillSpurLeftEndY = 0;
	private int midSawMillLeftVertTopY, midSawMillLeftVertBotY, midSawMillLeftVertX = 0;
	private int midSawMillRtVertTopY, midSawMillRtVertBotY, midSawMillRtVertX = 0;
	private int midSawMillLfSpurHorRtX, midSawMillLfSpurHorLtX, midSawMillLfSpurHorY = 0;
	private int midLogMainSpurEndPtX, midLogMainSpurEndPtY = 0;

	private int midLoggingTopRtSpurFrogX, midLoggingTopRtSpurFrogY, midLoggingTopRtSpurAngleRtX = 0;
	private int midLoggingTopRtSpurAngleRtY, midLoggingTopRtSpurMidPtFrogX, midLoggingTopRtSpurMidPtFrogY = 0;
	private int midLoggingTopRtSpurFarRtEndPtX, midLoggingTopRtSpurFarRtEndPtY, midLoggingTopRtSpurEndPtX = 0;
	private int midLoggingTopRtSpurEndPtY, midLoggingTopFarLeftSpurFrogY, midLoggingTopFarLeftSpurFrogX = 0;
	private int midLoggingTopFarLeftSpurEndPtX, midLoggingTopFarLeftSpurEndPtY, midLoggingBotNextLeftSpurFrogY = 0;
	private int midLoggingBotNextLeftSpurFrogX, midLoggingBotNextLeftSpurEndPtX, midLoggingBotNextLeftSpurEndPtY = 0;


	public LayoutPanelDrawMidUpper() {
		// Instantiate and Initialize...
		if (lp == null) lp = LayoutPanel.getInstance();

		if (mid == null) mid = LayoutPanelDrawMid.getInstance();
		if (east == null) east = LayoutPanelDrawEast.getInstance();

	}

	public static LayoutPanelDrawMidUpper getInstance() {
		if(_instance == null) {
			_instance = new LayoutPanelDrawMidUpper();
		}
		return _instance;
	}

	private int midInnerTopLeftX, midInnerTopLeftY, midInnerBotLeftY, midOuterTopRightX  = 0;
	private int midInnerVertTopX, midInnerVertTopY, midInnerVertBotY = 0;
	private int eastTopSLToMidTopInnerBotX, eastFarRightLineX, eastFarRightLineBottomY, eastNearLineFrogY = 0;
	private int eastHelixNextTopSidHorLfX, eastHelixNextTopSidHorY, eastHelixLfSidVertX, eastHelixLfSidVertTopY = 0;
	private int eastBlueLineSpurLfX, eastBlueLineSpurLfY = 0;
	private int southBottomLineY = 0;

	private void initializePointsFromOtherSections() {
		midInnerTopLeftX = mid.getmidInnerTopLeftX();
		midInnerTopLeftY = mid.getmidInnerTopLeftY();
		midInnerBotLeftY = mid.getmidInnerBotLeftY();
		midOuterTopRightX = mid.getmidOuterTopRightX();

		midInnerVertTopX = mid.getmidInnerVertTopX();
		midInnerVertTopY = mid.getmidInnerVertTopY();
		midInnerVertBotY = mid.getmidInnerVertBotY();

		eastTopSLToMidTopInnerBotX = east.geteastTopSLToMidTopInnerBotX();
		eastFarRightLineX = (int)(LayoutPanel.pSize.getWidth())-10;
		eastFarRightLineBottomY = (int)(LayoutPanel.pSize.getHeight())-80;
		eastNearLineFrogY = (int)(LayoutPanel.pSize.getHeight()/1.31);

		eastHelixNextTopSidHorLfX = east.geteastHelixNextTopSidHorLfX();
		eastHelixNextTopSidHorY = east.geteastHelixNextTopSidHorY();
		eastHelixLfSidVertX = east.geteastHelixLfSidVertX();
		eastHelixLfSidVertTopY = east.geteastHelixLfSidVertTopY();
		eastBlueLineSpurLfX = east.geteastBlueLineSpurLfX();
	    eastBlueLineSpurLfY = east.geteastBlueLineSpurLfY();

		southBottomLineY = (int)(LayoutPanel.pSize.getHeight()) - 10;

	}

	public void initializePoints() {

		initializePointsFromOtherSections();

		midUpDeckTopMLHorY = midInnerTopLeftY+20;
		midUpDeckTopMLHorLeftX = midInnerTopLeftX+20;
		midUpDeckTopMLHorRightX = eastTopSLToMidTopInnerBotX-20;
		midUpDeckTopMLHorFrogX = midUpDeckTopMLHorLeftX+50;
		midUpDeckTopMLHorFrogY = midUpDeckTopMLHorY;

		midUpDeckBotMLHorY = midInnerBotLeftY-20;
		midUpDeckBotMLHorLeftX = midUpDeckTopMLHorLeftX;
		midUpDeckBotMLHorRightX = midOuterTopRightX+10;

		midUpDeckVertMLX = midInnerVertTopX+20;
		midUpDeckVertMLTopY = midInnerVertTopY+20;
		midUpDeckVertMLBotY = midInnerVertBotY-20;

		midUpDeckMLTopAngleTopX = midUpDeckTopMLHorLeftX;
		midUpDeckMLTopAngleTopY = midUpDeckTopMLHorY;
		midUpDeckMLTopAngleBotX = midUpDeckVertMLX;
		midUpDeckMLTopAngleBotY = midUpDeckVertMLTopY;

		midUpDeckMLBotAngleTopX = midUpDeckVertMLX;
		midUpDeckMLBotAngleTopY = midUpDeckVertMLBotY;
		midUpDeckMLBotAngleBotX = midUpDeckBotMLHorLeftX;
		midUpDeckMLBotAngleBotY = midUpDeckBotMLHorY;

		midMidDeckMLHelixFrogX = eastHelixLfSidVertX;
		midMidDeckMLHelixFrogY = eastHelixLfSidVertTopY + 70;

		midMidDeckMLToSawMillSpurFrogY = midUpDeckBotMLHorY - 40;
		midMidDeckMLToSawMillSpurFrogX = LayoutPanel.getXOnLine(midUpDeckBotMLHorRightX, midUpDeckBotMLHorY, midMidDeckMLHelixFrogX, midMidDeckMLHelixFrogY, midMidDeckMLToSawMillSpurFrogY);

		midSawMillFrogToSMillY = midMidDeckMLToSawMillSpurFrogY;
		midSawMillFrogToSMillX = midUpDeckTopMLHorFrogX+50;

		midSawMillSpurLeftEndX = midSawMillFrogToSMillX-50;
		midSawMillSpurLeftEndY = midMidDeckMLToSawMillSpurFrogY;

		midSawMillLeftVertTopY = midUpDeckVertMLTopY;
		midSawMillLeftVertBotY = midSawMillSpurLeftEndY-30;
		midSawMillLeftVertX = midUpDeckVertMLX+40;

		midSawMillRtVertTopY = midUpDeckVertMLTopY;
		midSawMillRtVertBotY = midSawMillLeftVertBotY-10;
		midSawMillRtVertX = midSawMillLeftVertX+20;

		midSawMillLfSpurHorRtX = midSawMillFrogToSMillX-30;
		midSawMillLfSpurHorLtX = midSawMillSpurLeftEndX-10;
		midSawMillLfSpurHorY = midSawMillSpurLeftEndY+20;


		midLogMainSpurEndPtX = eastFarRightLineX-25;
		midLogMainSpurEndPtY = eastFarRightLineBottomY-15;

		midLoggingTopRtSpurFrogY = eastNearLineFrogY-10;
		midLoggingTopRtSpurFrogX = LayoutPanel.getXOnLine(midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midLogMainSpurEndPtX, midLogMainSpurEndPtY, midLoggingTopRtSpurFrogY);
		midLoggingTopRtSpurAngleRtX = midLoggingTopRtSpurFrogX+30;
		midLoggingTopRtSpurAngleRtY = midLoggingTopRtSpurFrogY;
		midLoggingTopRtSpurFarRtEndPtX = midLogMainSpurEndPtX+15;
		midLoggingTopRtSpurFarRtEndPtY = midLogMainSpurEndPtY-10;
		midLoggingTopRtSpurMidPtFrogY = midLoggingTopRtSpurAngleRtY+10;
		midLoggingTopRtSpurMidPtFrogX = LayoutPanel.getXOnLine(midLoggingTopRtSpurAngleRtX, midLoggingTopRtSpurAngleRtY, midLoggingTopRtSpurFarRtEndPtX, midLoggingTopRtSpurFarRtEndPtY, midLoggingTopRtSpurMidPtFrogY);
		midLoggingTopRtSpurEndPtX = eastFarRightLineX-10;//midLoggingTopRtSpurMidPtFrogX+40;
		midLoggingTopRtSpurEndPtY = midLoggingTopRtSpurMidPtFrogY;

		midLoggingTopFarLeftSpurFrogY = midLoggingTopRtSpurFrogY-20;
		midLoggingTopFarLeftSpurFrogX = LayoutPanel.getXOnLine(midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midLogMainSpurEndPtX, midLogMainSpurEndPtY, midLoggingTopFarLeftSpurFrogY);
		midLoggingTopFarLeftSpurEndPtX = midLoggingTopFarLeftSpurFrogX;
		midLoggingTopFarLeftSpurEndPtY = southBottomLineY-20;
		midLoggingBotNextLeftSpurFrogY = midLoggingTopFarLeftSpurFrogY+40;
		midLoggingBotNextLeftSpurFrogX = LayoutPanel.getXOnLine(midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midLogMainSpurEndPtX, midLogMainSpurEndPtY, midLoggingBotNextLeftSpurFrogY);
		midLoggingBotNextLeftSpurEndPtX = midLoggingBotNextLeftSpurFrogX;
		midLoggingBotNextLeftSpurEndPtY = midLoggingTopFarLeftSpurEndPtY-10;


	}

	public void draw() {

		initializePoints();

        //Draw Main Mid Upper Deck Tracks...

        //Section From Upper Deck Top ML Horizontal section To Top of Helix...

        if(LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal section To Top of Helix") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Top ML Horizontal section To Top of Helix", midUpDeckTopMLHorRightX, midUpDeckTopMLHorY, eastHelixNextTopSidHorLfX, eastHelixNextTopSidHorY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal section To Top of Helix").draw();
        TrackSegment seg = LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal section To Top of Helix");
        TrackSegment nextSeg = LayoutPanel.layoutList.getSegment("East-Helix Top-1 Horizontal Track");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Top ML Horizontal section To Top of Helix Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

    	//Draw the Upper Deck Bridge To Top of Helix...
    	int upperBotLY = (int)((LayoutPanel.pSize.getHeight()/2.7));
    	int upperBotLX = LayoutPanel.getXOnLine(midUpDeckTopMLHorRightX, midUpDeckTopMLHorY, eastHelixNextTopSidHorLfX, eastHelixNextTopSidHorY, upperBotLY)-12;
    	int upperTopLY = eastHelixNextTopSidHorY+10;
    	int upperTopLX = LayoutPanel.getXOnLine(midUpDeckTopMLHorRightX, midUpDeckTopMLHorY, eastHelixNextTopSidHorLfX, eastHelixNextTopSidHorY, upperTopLY)-12;
    	int upperTopRY = upperTopLY+10;
    	int upperTopRX = upperTopLX+10;
    	int upperBotRY = upperBotLY+10;
    	int upperBotRX = upperBotLX+10;

    	//Parm Point have to be in clock-wise order!!
    	LayoutPanel.drawRectagleBridgeIcon(upperBotLX, upperBotLY, upperTopLX, upperTopLY, upperTopRX, upperTopRY, upperBotRX, upperBotRY);


    	//MidUpper-Top ML Horizontal Track - Part A...  DONE Splitting up...
    	if(LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal Track-A") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Top ML Horizontal Track-A", midUpDeckTopMLHorLeftX, midUpDeckTopMLHorY, midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal Track-A").draw();


	    //MidUpper-Top ML Horizontal Track - Part B...
    	if(LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal Track-B") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Top ML Horizontal Track-B", midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midUpDeckTopMLHorRightX, midUpDeckTopMLHorY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal Track-B").draw();
    	seg = LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal Track-B");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal section To Top of Helix");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Top ML Horizontal Track-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //After MidUpper-Top ML Horizontal Track-B is created, connect the links...
    	seg = LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal Track-A");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal Track-B");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Top ML Horizontal Track-A Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //Angle From Upper Deck vertical ML to Upper Deck Top ML Horizontal section...

        if(LayoutPanel.layoutList.getSegment("MidUpper-Angle From Vertical ML to Top ML Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Angle From Vertical ML to Top ML Horizontal Track", midUpDeckMLTopAngleBotX, midUpDeckMLTopAngleBotY, midUpDeckMLTopAngleTopX, midUpDeckMLTopAngleTopY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Angle From Vertical ML to Top ML Horizontal Track").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Angle From Vertical ML to Top ML Horizontal Track");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal Track-A");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Angle From Vertical ML to Top ML Horizontal Track Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //Upper Deck vertical ML Section...

	    if(LayoutPanel.layoutList.getSegment("MidUpper-Vertical ML Track") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Vertical ML Track", midUpDeckVertMLX, midUpDeckVertMLTopY, midUpDeckVertMLX, midUpDeckVertMLBotY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Vertical ML Track").draw();

	    seg = LayoutPanel.layoutList.getSegment("MidUpper-Vertical ML Track");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Angle From Vertical ML to Top ML Horizontal Track");
	    if(seg != null && nextSeg != null)
	    	seg.connectFirstSegPtToNextSegFirstSegPt(nextSeg);
	    else System.out.println("LPanel MidUpper-Vertical ML Track Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //Angle From Upper Deck vertical ML to Upper Deck Bottom ML Horizontal section...

        if(LayoutPanel.layoutList.getSegment("MidUpper-Vertical ML Track Lower Angle") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Vertical ML Track Lower Angle", midUpDeckMLBotAngleTopX, midUpDeckMLBotAngleTopY, midUpDeckMLBotAngleBotX, midUpDeckMLBotAngleBotY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Vertical ML Track Lower Angle").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Vertical ML Track");
	    nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Vertical ML Track Lower Angle");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Vertical ML Track Lower Angle Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //Upper Deck Bottom ML Horizontal section...
	    int midUpperBotMLFrogX = eastBlueLineSpurLfX-30;
	    int midUpperBotMLFrogY = LayoutPanel.getYOnLine(midUpDeckBotMLHorLeftX, midUpDeckBotMLHorY, midUpDeckBotMLHorRightX, midUpDeckBotMLHorY, midUpperBotMLFrogX);

	    //Upper Deck Bottom ML Horizontal section - A...
        if(LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Horizontal Track-A") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Bottom ML Horizontal Track-A", midUpDeckBotMLHorLeftX, midUpDeckBotMLHorY, midUpperBotMLFrogX, midUpperBotMLFrogY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Horizontal Track-A").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Vertical ML Track Lower Angle");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Horizontal Track-A");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Bottom ML Horizontal Track-A Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //Upper Deck Bottom ML Horizontal section - B...
        if(LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Horizontal Track-B") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Bottom ML Horizontal Track-B", midUpperBotMLFrogX, midUpperBotMLFrogY, midUpDeckBotMLHorRightX, midUpDeckBotMLHorY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Horizontal Track-B").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Horizontal Track-A");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Horizontal Track-B");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Bottom ML Horizontal Track-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //Spur Angle from Mid Upper Bottom ML to Helix...
		if(LayoutPanel.layoutList.getSegment("Mid-Spur Angle from Mid Upper Bottom ML to Helix") == null) {
			 LayoutPanel.layoutList.add("Mid-Spur Angle from Mid Upper Bottom ML to Helix", midUpperBotMLFrogX, midUpperBotMLFrogY, eastBlueLineSpurLfX, eastBlueLineSpurLfY);
	    } else LayoutPanel.layoutList.getSegment("Mid-Spur Angle from Mid Upper Bottom ML to Helix").draw();
        seg = LayoutPanel.layoutList.getSegment("Mid-Spur Angle from Mid Upper Bottom ML to Helix");
        nextSeg = LayoutPanel.layoutList.getSegment("East-Spur Hor track from Helix Track To Mid Section");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel Mid-Spur Angle from Mid Upper Bottom ML to Helix Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());



	    //****************  MidUpper-SW0  ***
	    if(LayoutPanel.getSwitch("MidUpper-SW0") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("MidUpper-SW0", true);
	        int slY = eastBlueLineSpurLfY;
	        int slX = eastBlueLineSpurLfX;
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Horizontal Track-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("Mid-Spur Angle from Mid Upper Bottom ML to Helix").getSegmentID();
//TODO - SW #!!!
	        SwitchSegment sw0 = new SwitchSegment("MidUpper-SW0", LayoutPanel.refreshedSwId, mlSeg, slSeg, 331, midUpperBotMLFrogX, midUpperBotMLFrogY, midUpperBotMLFrogX+30, midUpperBotMLFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw0);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Horizontal Track-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("Mid-Spur Angle from Mid Upper Bottom ML to Helix");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw0);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
	    } else LayoutPanel.getSwitch("MidUpper-SW0").draw();
		//**********************************


	    //MidUpper-Bottom ML Hor Track To Lower Helix-A...  DONE Splitting up...
        if(LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Hor Track To Lower Helix-A") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Bottom ML Hor Track To Lower Helix-A", midUpDeckBotMLHorRightX, midUpDeckBotMLHorY, midMidDeckMLToSawMillSpurFrogX, midMidDeckMLToSawMillSpurFrogY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Hor Track To Lower Helix-A").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Horizontal Track-B");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Hor Track To Lower Helix-A");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Bottom ML Hor Track To Lower Helix-A Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //MidUpper-Bottom ML Hor Track To Lower Helix-B...
        if(LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Hor Track To Lower Helix-B") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Bottom ML Hor Track To Lower Helix-B", midMidDeckMLToSawMillSpurFrogX, midMidDeckMLToSawMillSpurFrogY, midMidDeckMLHelixFrogX, midMidDeckMLHelixFrogY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Hor Track To Lower Helix-B").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Hor Track To Lower Helix-A");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Hor Track To Lower Helix-B");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Bottom ML Hor Track To Lower Helix-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //****************  MidUpper-SW1  ***
	    if(LayoutPanel.getSwitch("MidUpper-SW1") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("MidUpper-SW1", false);
	        int slY = midMidDeckMLHelixFrogY+20;
	        int slX = LayoutPanel.getXOnLine(midUpDeckBotMLHorRightX, midUpDeckBotMLHorY, midMidDeckMLHelixFrogX, midMidDeckMLHelixFrogY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("East-Top Helix Left Vertical track-A").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Hor Track To Lower Helix-B").getSegmentID();
			SwitchSegment sw1 = new SwitchSegment("MidUpper-SW1", LayoutPanel.refreshedSwId, mlSeg, slSeg, 31, midMidDeckMLHelixFrogX, midMidDeckMLHelixFrogY, midMidDeckMLHelixFrogX, midMidDeckMLHelixFrogY+40, slX, slY, thrown);
			LayoutPanel.switchList.add(sw1);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("East-Top Helix Left Vertical track-A");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Hor Track To Lower Helix-B");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw1);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setNextSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
	    } else LayoutPanel.getSwitch("MidUpper-SW1").draw();
		//**********************************

        //Draw the Saw Mill Mountain Bridge from Helix...
        int SMbotLY = (int)((LayoutPanel.pSize.getHeight()/2));
		int SMbotLX = LayoutPanel.getXOnLine(midUpDeckBotMLHorRightX, midUpDeckBotMLHorY, midMidDeckMLHelixFrogX, midMidDeckMLHelixFrogY, SMbotLY)-8;
		int SMtopLY = midMidDeckMLHelixFrogY+20;
        int SMtopLX = LayoutPanel.getXOnLine(midUpDeckBotMLHorRightX, midUpDeckBotMLHorY, midMidDeckMLHelixFrogX, midMidDeckMLHelixFrogY, SMtopLY)-8;
        int SMtopRY = SMtopLY+10;
        int SMtopRX = SMtopLX+10;
        int SMbotRY = SMbotLY+10;
        int SMbotRX = SMbotLX+10;

        //Parm Point have to be in clock-wise order!!
      	LayoutPanel.drawRectagleBridgeIcon(SMbotLX, SMbotLY, SMtopLX, SMtopLY, SMtopRX, SMtopRY, SMbotRX, SMbotRY);


      	//MidUpper-Main Saw Mill Spur off of ML-A...    DONE Splitting up...
      	if(LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur off of ML-A") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Main Saw Mill Spur off of ML-A", midSawMillSpurLeftEndX, midSawMillSpurLeftEndY, midSawMillFrogToSMillX, midSawMillFrogToSMillY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur off of ML-A").draw();

      	//MidUpper-Main Saw Mill Spur off of ML-B...
      	if(LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur off of ML-B") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Main Saw Mill Spur off of ML-B", midSawMillFrogToSMillX, midSawMillFrogToSMillY, midMidDeckMLToSawMillSpurFrogX, midMidDeckMLToSawMillSpurFrogY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur off of ML-B").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur off of ML-A");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur off of ML-B");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Main Saw Mill Spur off of ML-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //****************  UpperMid-SW2  ***
        if(LayoutPanel.getSwitch("MidUpperMLToSawMill-SW2") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("MidUpperMLToSawMill-SW2", false);
	        int mlY = midMidDeckMLToSawMillSpurFrogY+25;
	        int mlX = LayoutPanel.getXOnLine(midUpDeckBotMLHorRightX, midUpDeckBotMLHorY, midMidDeckMLHelixFrogX, midMidDeckMLHelixFrogY, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Hor Track To Lower Helix-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur off of ML-B").getSegmentID();
			SwitchSegment sw2 = new SwitchSegment("MidUpperMLToSawMill-SW2", LayoutPanel.refreshedSwId, mlSeg, slSeg, 32, midMidDeckMLToSawMillSpurFrogX, midMidDeckMLToSawMillSpurFrogY, mlX, mlY, midMidDeckMLToSawMillSpurFrogX-30, midMidDeckMLToSawMillSpurFrogY, thrown);
			LayoutPanel.switchList.add(sw2);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("MidUpper-Bottom ML Hor Track To Lower Helix-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur off of ML-B");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw2);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("MidUpperMLToSawMill-SW2").draw();
		//***********************************

		//Saw Mill Spur Angle to Right Vertical...

        if(LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur Angle to Right Vertical") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Main Saw Mill Spur Angle to Right Vertical", midSawMillRtVertX, midSawMillRtVertBotY, midSawMillSpurLeftEndX, midSawMillSpurLeftEndY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur Angle to Right Vertical").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur off of ML-A");
        TrackSegment prevSeg = LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur Angle to Right Vertical");
	    if(seg != null && prevSeg != null)
	    	seg.connectSegmentToPrevSeg(prevSeg);
	    else System.out.println("LPanel MidUpper-Main Saw Mill Spur Angle to Right Vertical Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Saw Mill Spur Right Vertical...

	    if(LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Spur Right Vertical") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Saw Mill Spur Right Vertical", midSawMillRtVertX, midSawMillRtVertTopY, midSawMillRtVertX, midSawMillRtVertBotY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Spur Right Vertical").draw();
	    seg = LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur Angle to Right Vertical");
        prevSeg = LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Spur Right Vertical");
	    if(seg != null && prevSeg != null)
	    	seg.connectSegmentToPrevSeg(prevSeg);
	    else System.out.println("LPanel MidUpper-Saw Mill Spur Right Vertical Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Saw Mill Left Spur Angle From Frog to Left Horizontal...

	    if(LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Angle From Frog") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Saw Mill Left Spur Angle From Frog", midSawMillLfSpurHorRtX, midSawMillLfSpurHorY, midSawMillFrogToSMillX, midSawMillFrogToSMillY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Angle From Frog").draw();


	    //****************  UpperMid-SW3  ***
	    if(LayoutPanel.getSwitch("MidUpperMLTo Saw Mill Left Spur-SW3") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("MidUpperMLTo Saw Mill Left Spur-SW3", false);
	        int slY = midSawMillLfSpurHorY;
	        int slX = midSawMillLfSpurHorRtX;
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur off of ML-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Angle From Frog").getSegmentID();
			SwitchSegment sw3 = new SwitchSegment("MidUpperMLTo Saw Mill Left Spur-SW3", LayoutPanel.refreshedSwId, mlSeg, slSeg, 33, midSawMillFrogToSMillX, midSawMillFrogToSMillY, midSawMillFrogToSMillX-30, midSawMillFrogToSMillY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw3);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("MidUpper-Main Saw Mill Spur off of ML-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Angle From Frog");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw3);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
	    } else LayoutPanel.getSwitch("MidUpperMLTo Saw Mill Left Spur-SW3").draw();
		//***********************************


        //Saw Mill Left Spur Horizontal...

		if(LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Horizontal") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Saw Mill Left Spur Horizontal", midSawMillLfSpurHorLtX, midSawMillLfSpurHorY, midSawMillLfSpurHorRtX, midSawMillLfSpurHorY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Horizontal").draw();
		seg = LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Angle From Frog");
        prevSeg = LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Horizontal");
	    if(seg != null && prevSeg != null)
	    	seg.connectSegmentToPrevSeg(prevSeg);
	    else System.out.println("LPanel MidUpper-Saw Mill Left Spur Horizontal Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Saw Mill Left Spur Angle to Left Vertical...

		if(LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Angle to Left Vertical") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Saw Mill Left Spur Angle to Left Vertical", midSawMillLeftVertX, midSawMillLeftVertBotY, midSawMillLfSpurHorLtX, midSawMillLfSpurHorY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Angle to Left Vertical").draw();
		seg = LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Horizontal");
        prevSeg = LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Angle to Left Vertical");
	    if(seg != null && prevSeg != null)
	    	seg.connectSegmentToPrevSeg(prevSeg);
	    else System.out.println("LPanel MidUpper-Saw Mill Left Spur Angle to Left Vertical Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Saw Mill Spur Left Vertical...

	    if(LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Vertical") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Saw Mill Left Spur Vertical", midSawMillLeftVertX, midSawMillLeftVertTopY, midSawMillLeftVertX, midSawMillLeftVertBotY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Vertical").draw();
		seg = LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Angle to Left Vertical");
        prevSeg = LayoutPanel.layoutList.getSegment("MidUpper-Saw Mill Left Spur Vertical");
	    if(seg != null && prevSeg != null)
	    	seg.connectSegmentToPrevSeg(prevSeg);
	    else System.out.println("LPanel MidUpper-Saw Mill Left Spur Vertical Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Main Spur to Logging Spur - Split up - 4 times!!! 4 frogs -   DONE Splitting up...
	    //Main Spur to Logging Spur - MidUpper-Main Track to Logging Spur-A...
        if(LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-A") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Main Track to Logging Spur-A", midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midLoggingTopFarLeftSpurFrogX, midLoggingTopFarLeftSpurFrogY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-A").draw();

	    //Main Spur to Logging Spur - MidUpper-Main Track to Logging Spur-B...
        if(LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-B") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Main Track to Logging Spur-B", midLoggingTopFarLeftSpurFrogX, midLoggingTopFarLeftSpurFrogY, midLoggingTopRtSpurFrogX, midLoggingTopRtSpurFrogY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-B").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-A");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-B");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Main Track to Logging Spur-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //Main Spur to Logging Spur - MidUpper-Main Track to Logging Spur-C...
        if(LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-C") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Main Track to Logging Spur-C", midLoggingTopRtSpurFrogX, midLoggingTopRtSpurFrogY, midLoggingBotNextLeftSpurFrogX, midLoggingBotNextLeftSpurFrogY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-C").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-B");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-C");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Main Track to Logging Spur-C Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //Main Spur to Logging Spur - MidUpper-Main Track to Logging Spur-D...
        if(LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-D") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Main Track to Logging Spur-D", midLoggingBotNextLeftSpurFrogX, midLoggingBotNextLeftSpurFrogY, midLogMainSpurEndPtX, midLogMainSpurEndPtY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-D").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-C");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-D");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Main Track to Logging Spur-D Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());



        //****************  UpperMid-SW4  ***
        if(LayoutPanel.getSwitch("MidUpperMLToLogSpur-SW4") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("MidUpperMLToLogSpur-SW4", false);
	        int slY = midUpDeckTopMLHorFrogY+25;
	        int slX = LayoutPanel.getXOnLine(midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midLogMainSpurEndPtX, midLogMainSpurEndPtY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal Track-A").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-A").getSegmentID();
			SwitchSegment sw4 = new SwitchSegment("MidUpperMLToLogSpur-SW4", LayoutPanel.refreshedSwId, mlSeg, slSeg, 34, midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midUpDeckTopMLHorFrogX+30, midUpDeckTopMLHorFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw4);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("MidUpper-Top ML Horizontal Track-A");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-A");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw4);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
        } else LayoutPanel.getSwitch("MidUpperMLToLogSpur-SW4").draw();
		//***********************************


		//Draw the Logging Bridge...
        int topLY = midSawMillSpurLeftEndY-25;
        int topLX = LayoutPanel.getXOnLine(midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midLogMainSpurEndPtX, midLogMainSpurEndPtY, topLY)-12;
        int topRY = topLY-12;
        int topRX = topLX+12;
        int botRY = eastNearLineFrogY-50;
        int botRX = LayoutPanel.getXOnLine(midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midLogMainSpurEndPtX, midLogMainSpurEndPtY, botRY)+12;
        int botLY = botRY+12;
		int botLX = botRX-12;
		//System.out.println("Log Bridge topLY="+topLY+" topLX="+topLX+" topRY="+topRY+" topRX="+topRX);
        //Parm Point have to be in clock-wise order!!
      	LayoutPanel.drawRectagleBridgeIcon(topLX, topLY, topRX, topRY, botRX, botRY, botLX, botLY);


      	//Far Left Logging Spur...

      	if(LayoutPanel.layoutList.getSegment("MidUpper-Far Left Logging Spur") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Far Left Logging Spur", midLoggingTopFarLeftSpurFrogX, midLoggingTopFarLeftSpurFrogY, midLoggingTopFarLeftSpurEndPtX, midLoggingTopFarLeftSpurEndPtY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Far Left Logging Spur").draw();


      	//****************  UpperMid-SW5  ***
      	if(LayoutPanel.getSwitch("MidUpperFarLeft LogSpur-SW5") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("MidUpperFarLeft LogSpur-SW5", false);
	        int mlY = midLoggingTopFarLeftSpurFrogY+25;
	        int mlX = LayoutPanel.getXOnLine(midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midLogMainSpurEndPtX, midLogMainSpurEndPtY, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("MidUpper-Far Left Logging Spur").getSegmentID();
			SwitchSegment sw5 = new SwitchSegment("MidUpperFarLeft LogSpur-SW5", LayoutPanel.refreshedSwId, mlSeg, slSeg, 37, midLoggingTopFarLeftSpurFrogX, midLoggingTopFarLeftSpurFrogY, mlX, mlY, midLoggingTopFarLeftSpurFrogX, midLoggingTopFarLeftSpurFrogY+30, thrown);
			LayoutPanel.switchList.add(sw5);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("MidUpper-Far Left Logging Spur");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw5);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
      	} else LayoutPanel.getSwitch("MidUpperFarLeft LogSpur-SW5").draw();
		//***********************************


      	//Far Left-1 Logging Spur...

		if(LayoutPanel.layoutList.getSegment("MidUpper-Far Left-1 Logging Spur") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Far Left-1 Logging Spur", midLoggingBotNextLeftSpurFrogX, midLoggingBotNextLeftSpurFrogY, midLoggingBotNextLeftSpurEndPtX, midLoggingBotNextLeftSpurEndPtY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Far Left-1 Logging Spur").draw();


		//****************  UpperMid-SW6  ***
		if(LayoutPanel.getSwitch("MidUpperFarLeft-1 LogSpur-SW6") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("MidUpperFarLeft-1 LogSpur-SW6", false);
	        int mlY = midLoggingBotNextLeftSpurFrogY+25;
	        int mlX = LayoutPanel.getXOnLine(midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midLogMainSpurEndPtX, midLogMainSpurEndPtY, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-D").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("MidUpper-Far Left-1 Logging Spur").getSegmentID();
			SwitchSegment sw6 = new SwitchSegment("MidUpperFarLeft-1 LogSpur-SW6", LayoutPanel.refreshedSwId, mlSeg, slSeg, 39, midLoggingBotNextLeftSpurFrogX, midLoggingBotNextLeftSpurFrogY, mlX, mlY, midLoggingBotNextLeftSpurFrogX, midLoggingBotNextLeftSpurFrogY+30, thrown);
			LayoutPanel.switchList.add(sw6);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-D");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("MidUpper-Far Left-1 Logging Spur");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw6);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
		} else LayoutPanel.getSwitch("MidUpperFarLeft-1 LogSpur-SW6").draw();
		//***********************************


		//Frog Angle and Logging spur to the right...

      	if(LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur Frog Angle to the right") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Logging Spur Frog Angle to the right", midLoggingTopRtSpurFrogX, midLoggingTopRtSpurFrogY, midLoggingTopRtSpurAngleRtX, midLoggingTopRtSpurAngleRtY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur Frog Angle to the right").draw();


      	//****************  UpperMid-SW7  ***
      	if(LayoutPanel.getSwitch("MidUpperRight LogSpur-SW7") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("MidUpperRight LogSpur-SW7", false);
	        int mlY = midLoggingTopRtSpurFrogY+25;
	        int mlX = LayoutPanel.getXOnLine(midUpDeckTopMLHorFrogX, midUpDeckTopMLHorFrogY, midLogMainSpurEndPtX, midLogMainSpurEndPtY, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-C").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur Frog Angle to the right").getSegmentID();
			SwitchSegment sw7 = new SwitchSegment("MidUpperRight LogSpur-SW7", LayoutPanel.refreshedSwId, mlSeg, slSeg, 36, midLoggingTopRtSpurFrogX, midLoggingTopRtSpurFrogY, mlX, mlY, midLoggingTopRtSpurFrogX+25, midLoggingTopRtSpurFrogY, thrown);
			LayoutPanel.switchList.add(sw7);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("MidUpper-Main Track to Logging Spur-C");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur Frog Angle to the right");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw7);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
      	} else LayoutPanel.getSwitch("MidUpperRight LogSpur-SW7").draw();
		//***********************************


      	//Upper-Logging Spur to the Right -   DONE Splitting up...
      	//MidUpper-Logging Spur to the Right-A...
		if(LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to the Right-A") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Logging Spur to the Right-A", midLoggingTopRtSpurAngleRtX, midLoggingTopRtSpurAngleRtY, midLoggingTopRtSpurMidPtFrogX, midLoggingTopRtSpurMidPtFrogY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to the Right-A").draw();
		seg = LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur Frog Angle to the right");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to the Right-A");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Logging Spur to the Right-A Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

      	//MidUpper-Logging Spur to the Right-B...
		if(LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to the Right-B") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Logging Spur to the Right-B", midLoggingTopRtSpurMidPtFrogX, midLoggingTopRtSpurMidPtFrogY, midLoggingTopRtSpurFarRtEndPtX, midLoggingTopRtSpurFarRtEndPtY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to the Right-B").draw();
        seg = LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to the Right-A");
        nextSeg = LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to the Right-B");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel MidUpper-Logging Spur to the Right-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

      	//Logging Spur to Far Right...
      	//g2d.drawLine(midLoggingTopRtSpurMidPtFrogX, midLoggingTopRtSpurMidPtFrogY, midLoggingTopRtSpurEndPtX, midLoggingTopRtSpurEndPtY);
      	if(LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to Far Right") == null) {
	    	LayoutPanel.layoutList.add("MidUpper-Logging Spur to Far Right", midLoggingTopRtSpurMidPtFrogX, midLoggingTopRtSpurMidPtFrogY, midLoggingTopRtSpurEndPtX, midLoggingTopRtSpurEndPtY);
	    } else LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to Far Right").draw();

      	//****************  UpperMid-SW8  ***
      	if(LayoutPanel.getSwitch("MidUpper FarRight LogSpur-SW7") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("MidUpper FarRight LogSpur-SW8", false);
	        int mlY = midLoggingTopRtSpurMidPtFrogY+25;
	        int mlX = LayoutPanel.getXOnLine(midLoggingTopRtSpurAngleRtX, midLoggingTopRtSpurAngleRtY, midLoggingTopRtSpurFarRtEndPtX, midLoggingTopRtSpurFarRtEndPtY, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to the Right-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to Far Right").getSegmentID();
			SwitchSegment sw8 = new SwitchSegment("MidUpper FarRight LogSpur-SW8", LayoutPanel.refreshedSwId, mlSeg, slSeg, 38, midLoggingTopRtSpurMidPtFrogX, midLoggingTopRtSpurMidPtFrogY, mlX, mlY, midLoggingTopRtSpurMidPtFrogX+30, midLoggingTopRtSpurMidPtFrogY, thrown);
			LayoutPanel.switchList.add(sw8);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to the Right-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("MidUpper-Logging Spur to Far Right");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw8);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
      	} else LayoutPanel.getSwitch("MidUpper FarRight LogSpur-SW8").draw();
		//***********************************
	}
}
