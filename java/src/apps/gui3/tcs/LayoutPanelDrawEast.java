package apps.gui3.tcs;

import java.awt.Color;

public class LayoutPanelDrawEast {

	private static LayoutPanelDrawEast _instance = null;  //A singleton class!

	private LayoutPanel lp = null;
	private LayoutPanelDrawNorth north = null;
	private LayoutPanelDrawSouth south = null;

	//East Points
	private int eastFarRightLineX, eastFarRightLineTopY, eastFarRightLineBottomY = 0;
	private int eastMidLineX, eastMidLineTopY, eastMidLineBottomY = 0;
	private int eastNearLineX, eastNearLineTopY, eastNearLineBottomY, eastMidLineFrogX, eastMidLineFrogY = 0;
	private int eastNearLineFrogX, eastNearLineFrogY = 0;
	private int eastNearCurveLineX, eastNearCurveLineTopY, eastNearCurveLineBottomY = 0;
	private int eastNearCBottomAngleX, eastNearCBottomAngleY = 0;
	private int eastTopSLToMidTopInnerFrogY, eastTopSLToMidTopInnerFrogX, eastTopSLToMidTopInnerBotX, eastTopSLToMidTopInnerBotY = 0;
	private int eastTopLeftSLToMidTopOuterX, eastTopLeftSLToMidTopOuterTopY, eastTopLeftSLToMidTopOuterBotY = 0;
	private int eastMidFarRight1VertTopAngleBotY = 0;
	private int bottomHelixHorizontalFrogX, eastBlueLineSpurRtX, eastBlueLineSpurLfX = 0;
	private int bottomHelixHorizontalFrogY, eastBlueLineSpurRtY, eastBlueLineSpurLfY = 0;


	//Getter Functions...
	public int geteastTopLeftSLToMidTopOuterX() { return eastTopLeftSLToMidTopOuterX;}
	public int geteastTopLeftSLToMidTopOuterTopY() { return eastTopLeftSLToMidTopOuterTopY;}
	public int geteastTopSLToMidTopInnerBotX() { return eastTopSLToMidTopInnerBotX;}
	public int geteastTopLeftSLToMidTopOuterBotY() { return eastTopLeftSLToMidTopOuterBotY;}
	public int geteastMidFarRight1VertTopAngleBotY() { return eastMidFarRight1VertTopAngleBotY;}
	public int geteastNearCurveLineX() { return eastNearCurveLineX;}
	public int geteastNearCurveLineTopY() { return eastNearCurveLineTopY;}
	public int geteastNearLineFrogX() { return eastNearLineFrogX;}
	public int geteastNearLineFrogY() { return eastNearLineFrogY;}
	public int geteastMidLineFrogX() { return eastMidLineFrogX;}
	public int geteastMidLineFrogY() { return eastMidLineFrogY;}
	public int geteastNearLineX() { return eastNearLineX;}
	public int geteastNearLineTopY() { return eastNearLineTopY;}
	public int geteastMidLineX() { return eastMidLineX;}
	public int geteastMidLineTopY() { return eastMidLineTopY;}
	public int geteastTopSLToMidTopInnerBotY() { return eastTopSLToMidTopInnerBotY;}
	public int geteastTopSLToMidTopInnerFrogX() { return eastTopSLToMidTopInnerFrogX;}
	public int geteastTopSLToMidTopInnerFrogY() { return eastTopSLToMidTopInnerFrogY;}
	public int getbottomHelixHorizontalFrogX() { return eastBlueLineSpurRtX;}
	public int getbottomHelixHorizontalFrogY() { return eastBlueLineSpurRtY;}
	public int geteastBlueLineSpurRtX() { return eastBlueLineSpurRtX;}
	public int geteastBlueLineSpurRtY() { return eastBlueLineSpurRtY;}
	public int geteastBlueLineSpurLfX() { return eastBlueLineSpurLfX;}
	public int geteastBlueLineSpurLfY() { return eastBlueLineSpurLfY;}



	private int eastHelixTopSidHorY, eastHelixTopSidHorLfX, eastHelixTopSidHorRtX = 0;
	private int eastHelixLfSidVertTopY, eastHelixLfSidVertBotY, eastHelixLfSidVertX = 0;
	private int eastHelixBotSidHorY, eastHelixBotSidHorLfX, eastHelixBotSidHorRtX = 0;
	private int eastHelixRtSidVertTopY, eastHelixRtSidVertBotY, eastHelixRtSidVertX = 0;
	private int eastHelixNextTopSidHorY, eastHelixNextTopSidHorLfX, eastHelixNextTopSidHorRtX = 0;

	//Temporary!!!
	public int geteastHelixLfSidVertTopY() { return eastHelixLfSidVertTopY;}
	public int geteastHelixLfSidVertX() { return eastHelixLfSidVertX;}
	public int geteastHelixNextTopSidHorY() { return eastHelixNextTopSidHorY;}
	public int geteastHelixNextTopSidHorLfX() { return eastHelixNextTopSidHorLfX;}


	public LayoutPanelDrawEast() {
		// Instantiate and Initialize...
		if (lp == null) lp = LayoutPanel.getInstance();

		if (north == null) north = LayoutPanelDrawNorth.getInstance();
		if (south == null) south = LayoutPanelDrawSouth.getInstance();
	}

	public static LayoutPanelDrawEast getInstance() {
		if(_instance == null) {
			_instance = new LayoutPanelDrawEast();
		}
		return _instance;
	}

	private int southML4RightX, southML4Y, southBottomLineRightX, southBottomLineY  = 0;
	private int southML2RightX, southML2Y, southML3RightX, southML3Y = 0;
	private int midFarRtVertX, midFarRtVertTopY = 0;
	private int northBottomLineRightX, northBottomLineY, northMidLineRightX, northMidLineY = 0;
	private int northRtBotCoalSpurFrogX, northRtBotCoalSpurFrogY, northRtBotCoalSpurRightX, northRtBotCoalSpurY = 0;
	private int northRtTopCoalSpurFrogX, northRtTopCoalSpurFrogY, northRtTopCoalSpurLeftX, northRtTopCoalSpurY = 0;
	private int northRtTopCoalSpurRightX = 0;

	private void initializePointsFromOtherSections() {
		southML4RightX = south.getsouthML4RightX();
		southML4Y = south.getsouthML4Y();
		southBottomLineRightX = south.getsouthBottomLineRightX();
		southBottomLineY = south.getsouthBottomLineY();
		southML2RightX = south.getsouthML2RightX();
		southML2Y = south.getsouthML2Y();
		southML3RightX = south.getsouthML3RightX();
		southML3Y = south.getsouthML3Y();

		midFarRtVertX = eastNearLineX-10;//20; //mid.getmidFarRtVertX();
		midFarRtVertTopY = eastMidFarRight1VertTopAngleBotY+20; //mid.getmidFarRtVertTopY();

		northBottomLineRightX = north.getnorthBottomLineRightX();
		northBottomLineY = north.getnorthBottomLineY();
		northMidLineRightX = north.getnorthMidLineRightX();
		northMidLineY = north.getnorthMidLineY();

		northRtBotCoalSpurFrogX = north.getnorthRtBotCoalSpurFrogX();
		northRtBotCoalSpurFrogY = north.getnorthRtBotCoalSpurFrogY();
		northRtBotCoalSpurRightX = north.getnorthRtBotCoalSpurRightX();
		northRtBotCoalSpurY = north.getnorthRtBotCoalSpurY();

		northRtTopCoalSpurFrogX = north.getnorthRtTopCoalSpurFrogX();
		northRtTopCoalSpurFrogY = north.getnorthRtTopCoalSpurFrogY();
		northRtTopCoalSpurLeftX = north.getnorthRtTopCoalSpurLeftX();
		northRtTopCoalSpurY = north.getnorthRtTopCoalSpurY();
		northRtTopCoalSpurRightX = north.getnorthRtTopCoalSpurRightX();
	}

	public void initializePoints() {

		initializePointsFromOtherSections();

		eastFarRightLineX = (int)(LayoutPanel.pSize.getWidth())-10;
		eastFarRightLineTopY = 210;
		eastFarRightLineBottomY = (int)(LayoutPanel.pSize.getHeight()) - 80;

		eastHelixTopSidHorY = eastFarRightLineTopY-40;
		eastHelixTopSidHorLfX = (int)((LayoutPanel.pSize.getWidth()/1.12));
		eastHelixTopSidHorRtX = eastFarRightLineX-35;

		eastHelixLfSidVertTopY = eastHelixTopSidHorY+30;
		eastHelixLfSidVertBotY = eastHelixLfSidVertTopY+130;//110;//140;  //TODO!!!
		eastHelixLfSidVertX = (int)((LayoutPanel.pSize.getWidth()/1.16));

		eastHelixBotSidHorY = eastHelixLfSidVertBotY+30;
		eastHelixBotSidHorLfX = eastHelixLfSidVertX+20;
		eastHelixBotSidHorRtX = eastFarRightLineX-35;

		eastHelixRtSidVertTopY = eastHelixLfSidVertTopY+30;
		eastHelixRtSidVertBotY = eastHelixLfSidVertBotY;
		eastHelixRtSidVertX = eastFarRightLineX-20;

		eastHelixNextTopSidHorY = eastHelixTopSidHorY+35;
		eastHelixNextTopSidHorLfX = eastHelixTopSidHorLfX+10;
		eastHelixNextTopSidHorRtX = eastHelixTopSidHorRtX-5;

		eastMidFarRight1VertTopAngleBotY = (int)(LayoutPanel.pSize.getHeight()/4);

		eastMidLineX = (int)(LayoutPanel.pSize.getWidth()/1.27);  //1.2);
		eastMidLineTopY = (int)(LayoutPanel.pSize.getHeight()/1.55);
		eastMidLineBottomY = (int)(LayoutPanel.pSize.getHeight()/1.14);
		eastMidLineFrogX = eastMidLineX;
		eastMidLineFrogY = eastMidLineTopY + ((eastMidLineBottomY-eastMidLineTopY)/2);
//****
		eastNearLineX = eastMidLineX- 10;//20;
		eastNearLineTopY = (int)(LayoutPanel.pSize.getHeight()/2+40)+30;
		eastNearLineBottomY = eastMidLineBottomY-20;
		eastNearLineFrogX = eastNearLineX;
		eastNearLineFrogY = (int)(LayoutPanel.pSize.getHeight()/1.31);

        eastNearCBottomAngleX = southML4RightX;
        eastNearCBottomAngleY = southML4Y;

		eastNearCurveLineX = eastNearLineX;//-20;
		eastNearCurveLineTopY = (int)((LayoutPanel.pSize.getHeight()/1.26));
		eastNearCurveLineBottomY = eastNearLineBottomY-15;

		eastTopSLToMidTopInnerFrogY = (int)(LayoutPanel.pSize.getHeight()/4)-25;
		eastTopSLToMidTopInnerFrogX = LayoutPanel.getXOnLine(midFarRtVertX, midFarRtVertTopY, northBottomLineRightX, northBottomLineY, eastTopSLToMidTopInnerFrogY);
		eastTopSLToMidTopInnerBotX = eastTopSLToMidTopInnerFrogX;
		eastTopSLToMidTopInnerBotY = eastTopSLToMidTopInnerFrogY+70;

		eastTopLeftSLToMidTopOuterX = eastTopSLToMidTopInnerFrogX-20;
		eastTopLeftSLToMidTopOuterTopY = eastTopSLToMidTopInnerFrogY+20;
		eastTopLeftSLToMidTopOuterBotY = eastTopSLToMidTopInnerBotY-20;

		int tempX = (eastHelixBotSidHorRtX - eastHelixBotSidHorLfX);
		bottomHelixHorizontalFrogX = eastHelixBotSidHorLfX + Math.abs(tempX)/2;
		bottomHelixHorizontalFrogY = eastHelixBotSidHorY;
		eastBlueLineSpurRtX = bottomHelixHorizontalFrogX-20;
	    eastBlueLineSpurRtY = bottomHelixHorizontalFrogY+20;
	    eastBlueLineSpurLfX = eastBlueLineSpurRtX-265;
	    eastBlueLineSpurLfY = eastBlueLineSpurRtY;

	}

	public void draw() {

		initializePoints();


        //Draw Far Right ML...

        if(LayoutPanel.layoutList.getSegment("East-Far Right ML") == null) {
        	LayoutPanel.layoutList.add("East-Far Right ML", eastFarRightLineX, eastFarRightLineTopY, eastFarRightLineX, eastFarRightLineBottomY);
        }
        else LayoutPanel.layoutList.getSegment("East-Far Right ML").draw();


        //Draw Far Right Bottom Angle...

        if(LayoutPanel.layoutList.getSegment("East-Far Right Bottom Angle") == null) {
        	LayoutPanel.layoutList.add("East-Far Right Bottom Angle", southBottomLineRightX, southBottomLineY, eastFarRightLineX, eastFarRightLineBottomY);
        }
        else LayoutPanel.layoutList.getSegment("East-Far Right Bottom Angle").draw();
        TrackSegment seg = LayoutPanel.layoutList.getSegment("South-Bottom ML-B");
        TrackSegment nextSeg = LayoutPanel.layoutList.getSegment("East-Far Right Bottom Angle");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel East-Far Right Bottom Angle Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		seg = LayoutPanel.layoutList.getSegment("East-Far Right Bottom Angle");
        nextSeg = LayoutPanel.layoutList.getSegment("East-Far Right ML");
		if(seg != null && nextSeg != null) {
			//Changeover from next links to prev links since going from East to North!
			seg.connectEastSegToNextNorthSeg(nextSeg);
		}else System.out.println("LPanel East-Far Right Bottom Angle Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Draw Far Right-1 ML Bottom Angle To South...

        if(LayoutPanel.layoutList.getSegment("East-Far Right-1 ML Bottom Angle") == null) {
        	LayoutPanel.layoutList.add("East-Far Right-1 ML Bottom Angle", southML2RightX, southML2Y, eastMidLineX, eastMidLineBottomY);
        }
        else LayoutPanel.layoutList.getSegment("East-Far Right-1 ML Bottom Angle").draw();
        seg = LayoutPanel.layoutList.getSegment("South-MainLine 2 heading under bridge-B");
        nextSeg = LayoutPanel.layoutList.getSegment("East-Far Right-1 ML Bottom Angle");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel East-Far Right-1 ML Bottom Angle Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Draw Far Right-1 Vertical ML - Part A...
        if(LayoutPanel.layoutList.getSegment("East-Far Right-1 Vertical ML-A") == null) {
        	LayoutPanel.layoutList.add("East-Far Right-1 Vertical ML-A", eastMidLineX, eastMidLineTopY, eastMidLineFrogX, eastMidLineFrogY);
        }
        else LayoutPanel.layoutList.getSegment("East-Far Right-1 Vertical ML-A").draw();

        //Connect ML-A to Mid-Far Right Bottom Angle Above East Near Vert ML
        seg = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vertical ML-A");
        TrackSegment prevSeg = LayoutPanel.layoutList.getSegment("Mid-Far Right Bottom Angle Above East Near Vert ML");
	    if(seg != null && prevSeg != null) {
	    	seg.connectSegmentToPrevSeg(prevSeg);
		}else System.out.println("LPanel East-Far Right-1 Vertical ML-A Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Draw Far Right-1 Vertical ML -Part B...
        if(LayoutPanel.layoutList.getSegment("East-Far Right-1 Vertical ML-B") == null) {
        	LayoutPanel.layoutList.add("East-Far Right-1 Vertical ML-B", eastMidLineFrogX, eastMidLineFrogY, eastMidLineX, eastMidLineBottomY);
        }
        else LayoutPanel.layoutList.getSegment("East-Far Right-1 Vertical ML-B").draw();

        //Connect ML-B to ML-A
        seg = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vertical ML-B");
        prevSeg = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vertical ML-A");
	    if(seg != null && prevSeg != null) {
	    	seg.connectSegmentToPrevSeg(prevSeg);
		}else System.out.println("LPanel East-Far Right-1 Vertical ML Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //Connect ML-B to Bottom Angle Heading South
        seg = LayoutPanel.layoutList.getSegment("East-Far Right-1 ML Bottom Angle");
        nextSeg = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vertical ML-B");
		if(seg != null && nextSeg != null) {
			//Changeover from next links to prev links since going from East to North!
			seg.connectEastSegToNextNorthSeg(nextSeg);
		}else System.out.println("LPanel East-Far Right-1 ML Bottom Angle Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Draw East-Far Right-1 Vert Top Angle To North Mid ML-Part A...  DONE Splitting up...
        if(LayoutPanel.layoutList.getSegment("East-Far Right-1 Vert Top Angle To North Mid ML-A") == null) {
	    	LayoutPanel.layoutList.add("East-Far Right-1 Vert Top Angle To North Mid ML-A", northMidLineRightX, northMidLineY, northRtBotCoalSpurFrogX, northRtBotCoalSpurFrogY);
	    } else LayoutPanel.layoutList.getSegment("East-Far Right-1 Vert Top Angle To North Mid ML-A").draw();
        seg = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vert Top Angle To North Mid ML-A");
        prevSeg = LayoutPanel.layoutList.getSegment("North-Mid ML Track-E");
	    if(seg != null && prevSeg != null)
	    	seg.connectSegmentToPrevSeg(prevSeg);
	    else System.out.println("LPanel East-Far Right-1 Vert Top Angle To North Mid ML-A #1 Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //Draw East-Far Right-1 Vert Top Angle To North Mid ML-Part B...
	    int y1 = northRtBotCoalSpurFrogY+40;//30;
	    int midFarRtVertX = eastNearLineX-10;
	    int x1 = midFarRtVertX + ((eastHelixLfSidVertX - midFarRtVertX)/2);

	    //int x1 = LayoutPanel.getXOnLine(northMidLineRightX, northMidLineY, eastMidLineX, eastMidFarRight1VertTopAngleBotY, y1);
        if(LayoutPanel.layoutList.getSegment("East-Far Right-1 Vert Top Angle To North Mid ML-B") == null) {
	    	LayoutPanel.layoutList.add("East-Far Right-1 Vert Top Angle To North Mid ML-B", northRtBotCoalSpurFrogX, northRtBotCoalSpurFrogY, x1, y1);//eastMidLineX, eastMidFarRight1VertTopAngleBotY);
	    } else LayoutPanel.layoutList.getSegment("East-Far Right-1 Vert Top Angle To North Mid ML-B").draw();
        seg = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vert Top Angle To North Mid ML-B");
        prevSeg = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vert Top Angle To North Mid ML-A");
	    if(seg != null && prevSeg != null)
	    	seg.connectSegmentToPrevSeg(prevSeg);
	    else System.out.println("LPanel East-Far Right-1 Vert Top Angle To North Mid ML-B #1 Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Right Bottom Coal Siding Part A... DONE Splitting up...
    	if(LayoutPanel.layoutList.getSegment("East-Right Bottom Coal Siding-A") == null) {
	    	LayoutPanel.layoutList.add("East-Right Bottom Coal Siding-A", northRtBotCoalSpurFrogX, northRtBotCoalSpurFrogY, northRtTopCoalSpurFrogX, northRtTopCoalSpurFrogY);
	    } else LayoutPanel.layoutList.getSegment("East-Right Bottom Coal Siding-A").draw();

    	//Right Bottom Coal Siding Part B...
    	if(LayoutPanel.layoutList.getSegment("East-Right Bottom Coal Siding-B") == null) {
	    	LayoutPanel.layoutList.add("East-Right Bottom Coal Siding-B", northRtTopCoalSpurFrogX, northRtTopCoalSpurFrogY, northRtBotCoalSpurRightX, northRtBotCoalSpurY);
	    } else LayoutPanel.layoutList.getSegment("East-Right Bottom Coal Siding-B").draw();
    	seg = LayoutPanel.layoutList.getSegment("East-Right Bottom Coal Siding-B");
        prevSeg = LayoutPanel.layoutList.getSegment("East-Right Bottom Coal Siding-A");
	    if(seg != null && prevSeg != null)
	    	seg.connectSegmentToPrevSeg(prevSeg);
	    else System.out.println("LPanel East-Right Bottom Coal Siding-B Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //****************  East-SW0  ***
    	if(LayoutPanel.getSwitch("East-SW0") == null) {
		    boolean thrown = LayoutPanel.refreshSwitchList("East-SW0", false);

		    int mlY = northRtBotCoalSpurFrogY+20;//30;
		    //int mlX = LayoutPanel.getXOnLine(northRtBotCoalSpurFrogX, northRtBotCoalSpurFrogY, x1, y1, mlY);
		    int mlX = x1;
		    //pt1 = Frog pt2 = ML pt3 = Spur
		    int mlSeg = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vert Top Angle To North Mid ML-A").getSegmentID();
		    int slSeg = LayoutPanel.layoutList.getSegment("East-Right Bottom Coal Siding-A").getSegmentID();
		    SwitchSegment sw0 = new SwitchSegment("East-SW0", LayoutPanel.refreshedSwId, mlSeg, slSeg, 43, northRtBotCoalSpurFrogX, northRtBotCoalSpurFrogY, mlX, mlY, northRtBotCoalSpurFrogX+30, northRtBotCoalSpurFrogY, thrown);
		    LayoutPanel.switchList.add(sw0);
		    TrackSegment ML = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vert Top Angle To North Mid ML-A");
		    TrackSegment SL = LayoutPanel.layoutList.getSegment("East-Right Bottom Coal Siding-A");
		    if(ML != null) {
		    	SegmentPoint sp = ML.insertFrogPoint(sw0);
		    	SegmentPoint spurSegFirstSp = SL.getFirstPoint();
		    	sp.setNextSpur(spurSegFirstSp);
		    	spurSegFirstSp.setPrev(sp);
		    }
    	} else LayoutPanel.getSwitch("East-SW0").draw();
	    //********************************


    	//Right Top Coal Siding Spur...

    	if(LayoutPanel.layoutList.getSegment("East-Right Top Coal Siding SL Angle") == null) {
	    	LayoutPanel.layoutList.add("East-Right Top Coal Siding SL Angle", northRtTopCoalSpurFrogX, northRtTopCoalSpurFrogY, northRtTopCoalSpurLeftX, northRtTopCoalSpurY);
	    } else LayoutPanel.layoutList.getSegment("East-Right Top Coal Siding SL Angle").draw();


    	//****************  East-SW1  ***
    	if(LayoutPanel.getSwitch("East-SW1") == null) {
        boolean thrown = LayoutPanel.refreshSwitchList("East-SW1", false);
        int slY = northRtTopCoalSpurFrogY-20;
        int slX = LayoutPanel.getXOnLine(northRtTopCoalSpurFrogX, northRtTopCoalSpurFrogY, northRtTopCoalSpurLeftX, northRtTopCoalSpurY, slY);
        //pt1 = Frog pt2 = ML pt3 = Spur
        int mlSeg = LayoutPanel.layoutList.getSegment("East-Right Bottom Coal Siding-A").getSegmentID();
        int slSeg = LayoutPanel.layoutList.getSegment("East-Right Top Coal Siding SL Angle").getSegmentID();
		SwitchSegment sw1 = new SwitchSegment("East-SW1", LayoutPanel.refreshedSwId, mlSeg, slSeg, 44, northRtTopCoalSpurFrogX, northRtTopCoalSpurFrogY, northRtTopCoalSpurFrogX+35, northRtTopCoalSpurFrogY, slX, slY, thrown);
		LayoutPanel.switchList.add(sw1);
		TrackSegment ML = LayoutPanel.layoutList.getSegment("East-Right Bottom Coal Siding-A");
		TrackSegment SL = LayoutPanel.layoutList.getSegment("East-Right Top Coal Siding SL Angle");
		if(ML != null) {
			SegmentPoint sp = ML.insertFrogPoint(sw1);
			SegmentPoint spurSegFirstSp = SL.getFirstPoint();
			sp.setNextSpur(spurSegFirstSp);
			spurSegFirstSp.setPrev(sp);
		}
    	} else LayoutPanel.getSwitch("East-SW1").draw();
		//********************************


    	//Right Top Coal Siding...

		if(LayoutPanel.layoutList.getSegment("East-Right Top Coal Siding") == null) {
	    	LayoutPanel.layoutList.add("East-Right Top Coal Siding", northRtTopCoalSpurLeftX, northRtTopCoalSpurY, northRtTopCoalSpurRightX, northRtTopCoalSpurY);
	    } else LayoutPanel.layoutList.getSegment("East-Right Top Coal Siding").draw();
		seg = LayoutPanel.layoutList.getSegment("East-Right Top Coal Siding SL Angle");
		nextSeg = LayoutPanel.layoutList.getSegment("East-Right Top Coal Siding");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel East-Right Top Coal Siding Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //East Hidden Vertical Green ML...
	    y1 = northRtBotCoalSpurFrogY+40;//30;
	    //x1 = LayoutPanel.getXOnLine(northMidLineRightX, northMidLineY, eastMidLineX, eastMidFarRight1VertTopAngleBotY, y1);
	    /*int*/ midFarRtVertX = eastNearLineX-10;
	    x1 = midFarRtVertX + ((eastHelixLfSidVertX - midFarRtVertX)/2);
	    int x2 = x1;
	    int y2 = (int)(LayoutPanel.pSize.getHeight()/2); //y1 + 280;

		if(LayoutPanel.layoutList.getSegment("East-Hidden Vertical Green ML") == null) {
	    	LayoutPanel.layoutList.add("East-Hidden Vertical Green ML", x1, y1, x2, y2);
	    } else LayoutPanel.layoutList.getSegment("East-Hidden Vertical Green ML").draw();
		seg = LayoutPanel.layoutList.getSegment("East-Hidden Vertical Green ML");
        prevSeg = LayoutPanel.layoutList.getSegment("East-Far Right-1 Vert Top Angle To North Mid ML-B");
	    if(seg != null && prevSeg != null)
	    	seg.connectSegmentToPrevSeg(prevSeg);
	    else System.out.println("LPanel East-Hidden Vertical Green ML Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //East Hidden Green ML mid Angle to Lower Vertical
	    int x3 = x2+20;
	    int y3 = y2+40;
	    //LayoutPanel.g2d.drawLine(x2, y2, x3, y3);
		if(LayoutPanel.layoutList.getSegment("East Hidden Green ML mid Angle to Lower Vertical") == null) {
	    	LayoutPanel.layoutList.add("East Hidden Green ML mid Angle to Lower Vertical", x2, y2, x3, y3);
	    } else LayoutPanel.layoutList.getSegment("East Hidden Green ML mid Angle to Lower Vertical").draw();
		seg = LayoutPanel.layoutList.getSegment("East Hidden Green ML mid Angle to Lower Vertical");
        prevSeg = LayoutPanel.layoutList.getSegment("East-Hidden Vertical Green ML");
	    if(seg != null && prevSeg != null)
	    	prevSeg.connectSegmentToNextSeg(seg);
	    else System.out.println("LPanel East Hidden Green ML mid Angle to Lower Vertical Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //East Hidden Green ML Lower Vertical
	    int x4 = x3;
	    int y4 = southML3Y-30;
	    //LayoutPanel.g2d.drawLine(x2, y2, x3, y3);
		if(LayoutPanel.layoutList.getSegment("East Hidden Green ML Lower Vertical") == null) {
	    	LayoutPanel.layoutList.add("East Hidden Green ML Lower Vertical", x3, y3, x4, y4);
	    } else LayoutPanel.layoutList.getSegment("East Hidden Green ML Lower Vertical").draw();
		seg = LayoutPanel.layoutList.getSegment("East Hidden Green ML Lower Vertical");
        prevSeg = LayoutPanel.layoutList.getSegment("East Hidden Green ML mid Angle to Lower Vertical");
	    if(seg != null && prevSeg != null)
	    	prevSeg.connectSegmentToNextSeg(seg);
	    else System.out.println("LPanel East Hidden Green ML Lower Vertical Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //East Hidden Green ML Angle To South...
	    int x5 = southML3RightX;
	    int y5 = southML3Y;
	    //LayoutPanel.g2d.drawLine(x2, y2, x3, y3);
		if(LayoutPanel.layoutList.getSegment("East-Hidden Green ML Angle To South") == null) {
	    	LayoutPanel.layoutList.add("East-Hidden Green ML Angle To South", x5, y5, x4, y4);
	    } else LayoutPanel.layoutList.getSegment("East-Hidden Green ML Angle To South").draw();
		seg = LayoutPanel.layoutList.getSegment("East-Hidden Green ML Angle To South");
        prevSeg = LayoutPanel.layoutList.getSegment("East Hidden Green ML Lower Vertical");
	    if(seg != null && prevSeg != null)
	    	seg.connectEastSegToNextNorthSeg(prevSeg);
	    else System.out.println("LPanel East-Hidden Green ML Angle To South Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

        //Link to last South ML 3...
        seg = LayoutPanel.layoutList.getSegment("East-Hidden Green ML Angle To South");
        prevSeg = LayoutPanel.layoutList.getSegment("South-MainLine 3");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel East-Hidden Green ML Angle To South #2 Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

        //Draw East- Near Aisle ML Bottom Angle To South ML 4...

        if(LayoutPanel.layoutList.getSegment("East- Near Aisle ML Bottom Angle To South ML 4") == null) {
        	LayoutPanel.layoutList.add("East- Near Aisle ML Bottom Angle To South ML 4", eastNearCBottomAngleX, eastNearCBottomAngleY, eastNearCurveLineX, eastNearCurveLineBottomY);
        }
        else LayoutPanel.layoutList.getSegment("East- Near Aisle ML Bottom Angle To South ML 4").draw();
        seg = LayoutPanel.layoutList.getSegment("South-MainLine 4");
        nextSeg = LayoutPanel.layoutList.getSegment("East- Near Aisle ML Bottom Angle To South ML 4");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel East- Near Aisle ML Bottom Angle To South ML 4 Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Draw East- Vertical section leading up from Near ML Bottom Angle above...

        if(LayoutPanel.layoutList.getSegment("East- Near Aisle ML Vertical piece to Mid Section Outer ML") == null) {
        	LayoutPanel.layoutList.add("East- Near Aisle ML Vertical piece to Mid Section Outer ML", eastNearCurveLineX, eastNearCurveLineTopY, eastNearCurveLineX, eastNearCurveLineBottomY);
        } else LayoutPanel.layoutList.getSegment("East- Near Aisle ML Vertical piece to Mid Section Outer ML").draw();

        seg = LayoutPanel.layoutList.getSegment("East- Near Aisle ML Bottom Angle To South ML 4");
        nextSeg = LayoutPanel.layoutList.getSegment("East- Near Aisle ML Vertical piece to Mid Section Outer ML");
		if(seg != null && nextSeg != null) {
			//Changeover from next links to prev links since going from East to North!
			seg.connectEastSegToNextNorthSeg(nextSeg);
		}else System.out.println("LPanel East- Near Aisle ML Vertical piece to Mid Section Outer ML Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Far Right Top Left Angle...

        if(LayoutPanel.layoutList.getSegment("East-Far Right Top Left Angle") == null) {
        	LayoutPanel.layoutList.add("East-Far Right Top Left Angle", eastHelixTopSidHorRtX, eastHelixTopSidHorY, eastFarRightLineX, eastFarRightLineTopY);
        }
        else LayoutPanel.layoutList.getSegment("East-Far Right Top Left Angle").draw();
        seg = LayoutPanel.layoutList.getSegment("East-Far Right ML");
        prevSeg = LayoutPanel.layoutList.getSegment("East-Far Right Top Left Angle");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel East-Far Right Top Left Angle Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Top Helix Horizontal track...

        if(LayoutPanel.layoutList.getSegment("East-Top Helix Horizontal track") == null) {
        	LayoutPanel.layoutList.add("East-Top Helix Horizontal track", eastHelixTopSidHorLfX, eastHelixTopSidHorY, eastHelixTopSidHorRtX, eastHelixTopSidHorY);
        }
        else LayoutPanel.layoutList.getSegment("East-Top Helix Horizontal track").draw();
        seg = LayoutPanel.layoutList.getSegment("East-Far Right Top Left Angle");
        prevSeg = LayoutPanel.layoutList.getSegment("East-Top Helix Horizontal track");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel East-Top Helix Horizontal track Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Angle to left of Top Helix Horizontal track...

        if(LayoutPanel.layoutList.getSegment("East-Top Helix Left Angle track") == null) {
        	LayoutPanel.layoutList.add("East-Top Helix Left Angle track", eastHelixLfSidVertX, eastHelixLfSidVertTopY, eastHelixTopSidHorLfX, eastHelixTopSidHorY);
        } else LayoutPanel.layoutList.getSegment("East-Top Helix Left Angle track").draw();
        seg = LayoutPanel.layoutList.getSegment("East-Top Helix Left Angle track");
        nextSeg = LayoutPanel.layoutList.getSegment("East-Top Helix Horizontal track");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel East-Top Helix Left Angle track Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Left Helix Vertical Track PART A Upper...  DONE Splitting up...
		if(LayoutPanel.layoutList.getSegment("East-Top Helix Left Vertical track-A") == null) {
        	LayoutPanel.layoutList.add("East-Top Helix Left Vertical track-A", eastHelixLfSidVertX, eastHelixLfSidVertTopY, LayoutPanelDrawMidUpper.getMidMidDeckMLHelixFrogX(), LayoutPanelDrawMidUpper.getMidMidDeckMLHelixFrogY());
        }
        else LayoutPanel.layoutList.getSegment("East-Top Helix Left Vertical track-A").draw();

		seg = LayoutPanel.layoutList.getSegment("East-Top Helix Left Angle track");
        nextSeg = LayoutPanel.layoutList.getSegment("East-Top Helix Left Vertical track-A");
		if(seg != null && nextSeg != null)
			seg.connectFirstSegPtToNextSegFirstSegPt(nextSeg);
		else System.out.println("LPanel East-Top Helix Left Vertical track-A Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

        //Left Helix Vertical Track PART B Lower...
		if(LayoutPanel.layoutList.getSegment("East-Top Helix Left Vertical track-B") == null) {
        	LayoutPanel.layoutList.add("East-Top Helix Left Vertical track-B", LayoutPanelDrawMidUpper.getMidMidDeckMLHelixFrogX(), LayoutPanelDrawMidUpper.getMidMidDeckMLHelixFrogY(), eastHelixLfSidVertX, eastHelixLfSidVertBotY);
        }
        else LayoutPanel.layoutList.getSegment("East-Top Helix Left Vertical track-B").draw();

		seg = LayoutPanel.layoutList.getSegment("East-Top Helix Left Vertical track-B");
        prevSeg = LayoutPanel.layoutList.getSegment("East-Top Helix Left Vertical track-A");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);//Had to change when split up!!
		else System.out.println("LPanel East-Top Helix Left Vertical track-B Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Bottom Angle from Left Helix Vertical Track...

        if(LayoutPanel.layoutList.getSegment("East-Bottom Angle from Left Helix Vertical Track") == null) {
        	LayoutPanel.layoutList.add("East-Bottom Angle from Left Helix Vertical Track", eastHelixLfSidVertX, eastHelixLfSidVertBotY, eastHelixBotSidHorLfX, eastHelixBotSidHorY);
        }
        else LayoutPanel.layoutList.getSegment("East-Bottom Angle from Left Helix Vertical Track").draw();
        seg = LayoutPanel.layoutList.getSegment("East-Top Helix Left Vertical track-B");
        nextSeg = LayoutPanel.layoutList.getSegment("East-Bottom Angle from Left Helix Vertical Track");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel East-Bottom Angle from Left Helix Vertical Track Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Bottom Helix Horizontal track...
		//Bottom Helix Horizontal track - A...
		if(LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal track-A") == null) {
			 LayoutPanel.layoutList.add("East-Bottom Helix Horizontal track-A", eastHelixBotSidHorLfX, eastHelixBotSidHorY, bottomHelixHorizontalFrogX, bottomHelixHorizontalFrogY);
	    } else LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal track-A").draw();
		seg = LayoutPanel.layoutList.getSegment("East-Bottom Angle from Left Helix Vertical Track");
	    nextSeg = LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal track-A");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel East-Bottom Helix Horizontal track-A Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //Bottom Helix Horizontal track - B...
		if(LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal track-B") == null) {
			 LayoutPanel.layoutList.add("East-Bottom Helix Horizontal track-B", bottomHelixHorizontalFrogX, bottomHelixHorizontalFrogY, eastHelixBotSidHorRtX, eastHelixBotSidHorY);
	    } else LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal track-B").draw();
		seg = LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal track-A");
	    nextSeg = LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal track-B");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel East-Bottom Helix Horizontal track-B Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	    //Bottom Helix Horizontal Spur track...
		if(LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal Spur Track") == null) {
			 LayoutPanel.layoutList.add("East-Bottom Helix Horizontal Spur Track", eastBlueLineSpurRtX, eastBlueLineSpurRtY, bottomHelixHorizontalFrogX, bottomHelixHorizontalFrogY);
	    } else LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal Spur Track").draw();


	    //TODO - Need to Add Switch Segment
    	//****************  East-SW2  ***
    	if(LayoutPanel.getSwitch("East-SW2") == null) {
        boolean thrown = LayoutPanel.refreshSwitchList("East-SW2", true);

        int slY = eastBlueLineSpurRtY;
        int slX = eastBlueLineSpurRtX;
        //pt1 = Frog pt2 = ML pt3 = Spur
        int mlSeg = LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal track-B").getSegmentID();
        int slSeg = LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal Spur Track").getSegmentID();
//TODO - FIX SW #!!!!
		SwitchSegment sw2 = new SwitchSegment("East-SW2", LayoutPanel.refreshedSwId, mlSeg, slSeg, 444, bottomHelixHorizontalFrogX, bottomHelixHorizontalFrogY, bottomHelixHorizontalFrogX-30, bottomHelixHorizontalFrogY, slX, slY, thrown);
		LayoutPanel.switchList.add(sw2);
		TrackSegment ML = LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal track-B");
		TrackSegment SL = LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal Spur Track");
		if(ML != null) {
			SegmentPoint sp = ML.insertFrogPoint(sw2);
			SegmentPoint spurSegLastSp = SL.getLastPoint();
			sp.setPrevSpur(spurSegLastSp);
			spurSegLastSp.setNext(sp);
		}
    	} else LayoutPanel.getSwitch("East-SW2").draw();

		//********************************

	    //Spur Horizontal track from Bottom Helix Track To Mid Section...
		if(LayoutPanel.layoutList.getSegment("East-Spur Hor track from Helix Track To Mid Section") == null) {
			 LayoutPanel.layoutList.add("East-Spur Hor track from Helix Track To Mid Section", eastBlueLineSpurLfX, eastBlueLineSpurLfY, eastBlueLineSpurRtX, eastBlueLineSpurRtY);
	    } else LayoutPanel.layoutList.getSegment("East-Spur Hor track from Helix Track To Mid Section").draw();
		seg = LayoutPanel.layoutList.getSegment("East-Spur Hor track from Helix Track To Mid Section");
	    nextSeg = LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal Spur Track");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel East-Spur Hor track from Helix Track To Mid Section Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //Angle to right of Bottom Helix Horizontal Track...

	    if(LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal Right Angle Track") == null) {
			 LayoutPanel.layoutList.add("East-Bottom Helix Horizontal Right Angle Track", eastHelixBotSidHorRtX, eastHelixBotSidHorY, eastHelixRtSidVertX, eastHelixRtSidVertBotY);
	    } else LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal Right Angle Track").draw();
	    seg = LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal track-B");
	    nextSeg = LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal Right Angle Track");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel East-Bottom Helix Horizontal Right Angle Track Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //Right Helix Vertical Track...

	    if(LayoutPanel.layoutList.getSegment("East-Right Helix Vertical Track") == null) {
	    	LayoutPanel.layoutList.add("East-Right Helix Vertical Track", eastHelixRtSidVertX, eastHelixRtSidVertTopY, eastHelixRtSidVertX, eastHelixRtSidVertBotY);
	    }else LayoutPanel.layoutList.getSegment("East-Right Helix Vertical Track").draw();

	    seg = LayoutPanel.layoutList.getSegment("East-Bottom Helix Horizontal Right Angle Track");
	    nextSeg = LayoutPanel.layoutList.getSegment("East-Right Helix Vertical Track");
		if(seg != null && nextSeg != null) {
			//Changeover from next links to prev links since going from East to North!
			seg.connectEastSegToNextNorthSeg(nextSeg);
		 } else System.out.println("LPanel East-Right Helix Vertical Track Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Right Helix Vertical Top Angle Track...

        if(LayoutPanel.layoutList.getSegment("East-Right Helix Top Angle Track") == null) {
	    	LayoutPanel.layoutList.add("East-Right Helix Top Angle Track", eastHelixNextTopSidHorRtX, eastHelixNextTopSidHorY, eastHelixRtSidVertX, eastHelixRtSidVertTopY);
	    }else LayoutPanel.layoutList.getSegment("East-Right Helix Top Angle Track").draw();
        seg = LayoutPanel.layoutList.getSegment("East-Right Helix Top Angle Track");
	    nextSeg = LayoutPanel.layoutList.getSegment("East-Right Helix Vertical Track");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel East-Right Helix Top Angle Track Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Helix Next Top (Top-1) Horizontal Track...

        if(LayoutPanel.layoutList.getSegment("East-Helix Top-1 Horizontal Track") == null) {
	    	LayoutPanel.layoutList.add("East-Helix Top-1 Horizontal Track", eastHelixNextTopSidHorLfX, eastHelixNextTopSidHorY, eastHelixNextTopSidHorRtX, eastHelixNextTopSidHorY);
	    }else LayoutPanel.layoutList.getSegment("East-Helix Top-1 Horizontal Track").draw();
        seg = LayoutPanel.layoutList.getSegment("East-Helix Top-1 Horizontal Track");
	    nextSeg = LayoutPanel.layoutList.getSegment("East-Right Helix Top Angle Track");
	    if(seg != null && nextSeg != null)
	    	seg.connectSegmentToNextSeg(nextSeg);
	    else System.out.println("LPanel East-Helix Top-1 Horizontal Track Else seg="+seg+" prevSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	}
}
