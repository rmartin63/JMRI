package apps.gui3.tcs;

public class LayoutPanelDrawWest {

	private static LayoutPanelDrawWest _instance = null;  //A singleton class!

	private LayoutPanel lp = null;

	private LayoutPanelDrawNorth north = null;
	private LayoutPanelDrawSouth south = null;

	//West Points
	private int westXLeftLine, westXRightLine, westTopY, westBottomY  = 0;

	public int getwestXLeftLine() { return westXLeftLine;}
	public int getwestBottomY() { return westBottomY;}

	public LayoutPanelDrawWest() {
		// Instantiate and Initialize...
		if (lp == null) lp = LayoutPanel.getInstance();

		if (north == null) north = LayoutPanelDrawNorth.getInstance();
		if (south == null) south = LayoutPanelDrawSouth.getInstance();
	}

	public static LayoutPanelDrawWest getInstance() {
		if(_instance == null) {
			_instance = new LayoutPanelDrawWest();
		}
		return _instance;
	}

	private int northMidLineLeftX, northMidLineY, northBottomLineLeftX, northBottomLineY = 0;
	private int southBottomLineLeftX, southBottomLineY, southML5LeftX, southML5Y = 0;

	private void initializePointsFromOtherSections() {
		northMidLineLeftX = north.getnorthMidLineLeftX();
		northMidLineY = north.getnorthMidLineY();
		northBottomLineLeftX = north.getnorthBottomLineLeftX();
		northBottomLineY = north.getnorthBottomLineY();

		southBottomLineLeftX = south.getsouthBottomLineLeftX();
		southBottomLineY = south.getsouthBottomLineY();
		southML5LeftX = south.getsouthML5LeftX();
		southML5Y = south.getsouthML5Y();

//System.out.println("WEST - n1="+northMidLineLeftX+" n2="+northMidLineY+" n3="+northBottomLineLeftX+" n4="+northBottomLineY+
//		" s1="+southBottomLineLeftX+" s2="+southBottomLineY+" s3="+southML5LeftX+" s4="+southML5Y);

	}

	public void initializePoints() {

		initializePointsFromOtherSections();
        westXLeftLine = (int)(LayoutPanel.pSize.getWidth()/4);
		westXRightLine = westXLeftLine + 20;
		westTopY = 195;
		westBottomY = (int)(LayoutPanel.pSize.getHeight())-130;

	}

	public void draw() {

		initializePoints();

		//Draw the Bridge...
		int topLX = westXLeftLine-15;
		int topLY = (int)(LayoutPanel.pSize.getHeight()/2)-30;
		int topRX = westXRightLine+15;
		int topRY = topLY;
		int botLX = topLX;
		int botLY = topLY+(int)(LayoutPanel.pSize.getHeight()/5);
		int botRX = topRX;
		int botRY = botLY;

		//Draw Left ML Top Angle (SEGMENT 1)...
		if(LayoutPanel.layoutList.getSegment("West-Left ML Top Angle") == null) {
			LayoutPanel.layoutList.add("West-Left ML Top Angle", westXLeftLine, westTopY-20, northMidLineLeftX, northMidLineY);
		}
		else LayoutPanel.layoutList.getSegment("West-Left ML Top Angle").draw();

		//Draw Left ML (SEGMENT 2)...
		if(LayoutPanel.layoutList.getSegment("West-Left ML") == null) {
			LayoutPanel.layoutList.add("West-Left ML", westXLeftLine, westTopY-20, westXLeftLine, westBottomY);
		}
		else LayoutPanel.layoutList.getSegment("West-Left ML").draw();
		TrackSegment seg = LayoutPanel.layoutList.getSegment("West-Left ML");
		TrackSegment prevSeg = LayoutPanel.layoutList.getSegment("West-Left ML Top Angle");
		if(seg != null && prevSeg != null)
			seg.connectFirstSegPtToNextSegFirstSegPt(prevSeg);
		else System.out.println("LPanel West-Left ML Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Draw Left ML Bottom Angle (SEGMENT 2)...
		if(LayoutPanel.layoutList.getSegment("West-Left ML Bottom Angle") == null) {
			LayoutPanel.layoutList.add("West-Left ML Bottom Angle", westXLeftLine, westBottomY, southBottomLineLeftX, southBottomLineY);
		}
		else LayoutPanel.layoutList.getSegment("West-Left ML Bottom Angle").draw();

		seg = LayoutPanel.layoutList.getSegment("West-Left ML Bottom Angle");
		prevSeg = LayoutPanel.layoutList.getSegment("West-Left ML");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel West-Left ML Bottom Angle Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Draw Right ML Top Angle...

		if(LayoutPanel.layoutList.getSegment("West-Right ML Top Angle") == null) {
			LayoutPanel.layoutList.add("West-Right ML Top Angle", westXRightLine, westTopY, northBottomLineLeftX, northBottomLineY);
		}
		else LayoutPanel.layoutList.getSegment("West-Right ML Top Angle").draw();


		//Draw West ML Right Line...

		if(LayoutPanel.layoutList.getSegment("West-Right ML") == null) {
			LayoutPanel.layoutList.add("West-Right ML", westXRightLine, westTopY, westXRightLine, westBottomY-30);
		}
		else LayoutPanel.layoutList.getSegment("West-Right ML").draw();
		seg = LayoutPanel.layoutList.getSegment("West-Right ML");
		TrackSegment nextSeg = LayoutPanel.layoutList.getSegment("West-Right ML Top Angle");
		if(seg != null && nextSeg != null)
			//Changeover from prev links to next links since going from North to East!
			seg.connectFirstSegPtToNextSegFirstSegPt(nextSeg);
		else System.out.println("LPanel West-Right ML Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Draw Right ML Bottom Angle...

		if(LayoutPanel.layoutList.getSegment("West-Right ML Bottom Angle") == null) {
			LayoutPanel.layoutList.add("West-Right ML Bottom Angle", westXRightLine, westBottomY-30, southML5LeftX, southML5Y);
		}
		else LayoutPanel.layoutList.getSegment("West-Right ML Bottom Angle").draw();

		seg = LayoutPanel.layoutList.getSegment("West-Right ML Bottom Angle");
		prevSeg = LayoutPanel.layoutList.getSegment("West-Right ML");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel West-Right ML Bottom Angle Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Parm Point have to be in clock-wise order!!
		LayoutPanel.drawRectagleBridgeIcon(topLX, topLY, topRX, topRY, botRX, botRY, botLX, botLY);



	}
}
