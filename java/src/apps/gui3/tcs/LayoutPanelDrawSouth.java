package apps.gui3.tcs;

import java.awt.Color;

public class LayoutPanelDrawSouth {

	private static LayoutPanelDrawSouth _instance = null;  //A singleton class!

	private LayoutPanel lp = null;

	//South Points
	private int southBottomLineLeftX, southBottomLineY, southBottomLineRightX  = 0;
	private int southML1LeftX, southML1Y, southML1RightX, southML2LeftX, southML2Y, southML2RightX = 0;
	private int southML3LeftX, southML3Y, southML3RightX, southML4LeftX, southML4Y, southML4RightX = 0;
	private int southML5LeftX, southML5Y, southML5RightX, southML1LeftTOX = 0;
	private int southSL1LeftX, southSL1RightX, southSL1Y, southSL2LeftX, southSL2RightX, southSL2Y, southSL2AngleBottomX, southSL2AngleBottomY = 0;
	private int southSL3LeftX, southSL3RightX, southSL3Y, southSL4LeftX, southSL4RightX, southSL4Y = 0;
	private int southSL5LeftX, southSL5RightX, southSL5Y, southSL5TOAngleY, southSL5TOAngleX = 0;

	public int getsouthML4RightX() { return southML4RightX;}
	public int getsouthML4Y() { return southML4Y;}
	public int getsouthBottomLineRightX() { return southBottomLineRightX;}
	public int getsouthBottomLineY() { return southBottomLineY;}
	public int getsouthML2RightX() { return southML2RightX;}
	public int getsouthML2Y() { return southML2Y;}
	public int getsouthML3RightX() { return southML3RightX;}
	public int getsouthML3Y() { return southML3Y;}
	public int getsouthML4LeftX() { return southML4LeftX;}
	public int getsouthBottomLineLeftX() { return southBottomLineLeftX;}
	public int getsouthML5LeftX() { return southML5LeftX;}
	public int getsouthML5Y() { return southML5Y;}


	public LayoutPanelDrawSouth() {
		// Instantiate and Initialize...
		if (lp == null) lp = LayoutPanel.getInstance();
	}

	public static LayoutPanelDrawSouth getInstance() {
		if(_instance == null) {
			_instance = new LayoutPanelDrawSouth();
		}
		return _instance;
	}

	private int westXLeftLine, westBottomY = 0;

	private void initializePointsFromOtherSections() {
		westXLeftLine = (int)(LayoutPanel.pSize.getWidth()/4);
		westBottomY = (int)(LayoutPanel.pSize.getHeight())-130;

	}

	public void initializePoints() {

		initializePointsFromOtherSections();
		southBottomLineLeftX = (int)(LayoutPanel.pSize.getWidth()/3) + 30;
		southBottomLineY = (int)(LayoutPanel.pSize.getHeight()) - 10;
		southBottomLineRightX = (int)(LayoutPanel.pSize.getWidth()) - 100;

		southML1LeftX = southBottomLineLeftX+50;
        southML1Y = southBottomLineY-20;
        southML1RightX = southML1LeftX+(int)(LayoutPanel.pSize.getHeight()/20);
        southML1LeftTOX = southML1LeftX - 30;

        southML2LeftX = (int)(LayoutPanel.pSize.getWidth()/2)+10;
        southML2Y = southBottomLineY-30;
        southML2RightX = (int)(LayoutPanel.pSize.getWidth()/1.33); ///1.3); //5)*4 + 10;

        southML3LeftX = southML2LeftX;
        southML3Y = southML2Y-30;
        southML3RightX = southML2RightX+60;  //-20;

        southML4LeftX = southML2LeftX;
        southML4Y = southML3Y-20;
        southML4RightX = southML2RightX-5; //15;  //southML3RightX-15;

        southML5LeftX = southBottomLineLeftX-40;
        southML5Y = southML4Y-20;
        southML5RightX = southML1LeftTOX+40;

        southSL1LeftX = southBottomLineLeftX-40;
        southSL1Y = southBottomLineY-60;
        //See southSL1RightX assigned below


        southSL2LeftX = southSL1LeftX;
        southSL2Y = southSL1Y-20;
        southSL2RightX = LayoutPanel.getXOnLine(southML5RightX, southML5Y, southML2LeftX, southML2Y, southSL2Y)-30;
        southSL2AngleBottomX = southML2LeftX-10;
        southSL2AngleBottomY = southML2Y+20;//10;

        southSL1RightX = LayoutPanel.getXOnLine(southSL2AngleBottomX, southSL2AngleBottomY, southSL2RightX, southSL2Y, southSL1Y);

        southSL3LeftX = southSL2LeftX;
        southSL3RightX = southML5RightX+10;
        southSL3Y = southML5Y-15;

        southSL4LeftX = southSL3LeftX;
        southSL4RightX = southSL3RightX-15;
        southSL4Y = southSL3Y-15;

        southSL5LeftX = southSL4LeftX;
        southSL5RightX = southSL4RightX-20;
        southSL5Y = southSL4Y-15;
        southSL5TOAngleY = southML5Y +30;
        southSL5TOAngleX = LayoutPanel.getXOnLine(southML5RightX, southML5Y, southML2LeftX, southML2Y, southSL5TOAngleY);

        //Adjust SL3 & SL4 to clamp onto SL5TOAngle above...
        southSL3RightX = LayoutPanel.getXOnLine(southSL5RightX, southSL5Y, southSL5TOAngleX, southSL5TOAngleY, southSL3Y);
        southSL4RightX = LayoutPanel.getXOnLine(southSL5RightX, southSL5Y, southSL5TOAngleX, southSL5TOAngleY, southSL4Y);

	}

	public void draw() {

		initializePoints();


		//Draw the Bridge...
		int botRX = southML1RightX+10;
		int botRY = southML1Y+10;
		int botLX = southML1RightX-8;
		int botLY = southML1Y-27;
		int topLX = southML4LeftX-20;
		int topLY = southML4Y-5;
		int topRX = southML3LeftX-2;
		int topRY = southML3Y+13;


		//Draw Bottom, Left siding Angle...

        if(LayoutPanel.layoutList.getSegment("South-Bottom Left Siding Angle") == null) {
			LayoutPanel.layoutList.add("South-Bottom Left Siding Angle", southBottomLineLeftX-70, southBottomLineY-30, southBottomLineLeftX-40, southBottomLineY);
		}
		else LayoutPanel.layoutList.getSegment("South-Bottom Left Siding Angle").draw();


        //Draw Bottom, Left siding...

        if(LayoutPanel.layoutList.getSegment("South-Bottom Left Horizontal Siding") == null) {
			LayoutPanel.layoutList.add("South-Bottom Left Horizontal Siding", southBottomLineLeftX-40, southBottomLineY, southBottomLineLeftX, southBottomLineY);
		}
		else LayoutPanel.layoutList.getSegment("South-Bottom Left Horizontal Siding").draw();
        TrackSegment seg = LayoutPanel.layoutList.getSegment("South-Bottom Left Horizontal Siding");
        TrackSegment prevSeg = LayoutPanel.layoutList.getSegment("South-Bottom Left Siding Angle");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel South-Bottom Left Horizontal Siding Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Draw Bottom ML - split up...  DONE Splitting up...
		//South-Bottom ML - Part A...
        if(LayoutPanel.layoutList.getSegment("South-Bottom ML-A") == null) {
			LayoutPanel.layoutList.add("South-Bottom ML-A", southBottomLineLeftX, southBottomLineY, southML1LeftTOX, southBottomLineY);
		}
		else LayoutPanel.layoutList.getSegment("South-Bottom ML-A").draw();
        seg = LayoutPanel.layoutList.getSegment("South-Bottom ML-A");
        prevSeg = LayoutPanel.layoutList.getSegment("South-Bottom Left Horizontal Siding");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel South-Bottom ML-A Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//South-Bottom ML - Part B...
        if(LayoutPanel.layoutList.getSegment("South-Bottom ML-B") == null) {
			LayoutPanel.layoutList.add("South-Bottom ML-B", southML1LeftTOX, southBottomLineY, southBottomLineRightX, southBottomLineY);
		}
		else LayoutPanel.layoutList.getSegment("South-Bottom ML-B").draw();
        seg = LayoutPanel.layoutList.getSegment("South-Bottom ML-B");
        prevSeg = LayoutPanel.layoutList.getSegment("South-Bottom ML-A");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel South-Bottom ML-B Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

        //****************  South-SW0  (To spur by layout door) ***
		if(LayoutPanel.getSwitch("South-SW0") == null) {
			boolean thrown = LayoutPanel.refreshSwitchList("South-SW0", true);
	        int slY = southBottomLineY-25;
	        int slX = LayoutPanel.getXOnLine(westXLeftLine, westBottomY, southBottomLineLeftX, southBottomLineY, slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("South-Bottom ML-A").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("West-Left ML Bottom Angle").getSegmentID();
			SwitchSegment sw0 = new SwitchSegment("South-SW0", LayoutPanel.refreshedSwId, mlSeg, slSeg, 18, southBottomLineLeftX, southBottomLineY, southBottomLineLeftX-40, southBottomLineY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw0);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("South-Bottom ML-A");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("West-Left ML Bottom Angle");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw0);
				SegmentPoint prevSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(prevSegLastSp);
				prevSegLastSp.setNext(sp);
			}
		} else LayoutPanel.getSwitch("South-SW0").draw();
		//********************************************************

        //Draw Bottom ML TO Angle for South-SW1...

        if(LayoutPanel.layoutList.getSegment("South-Bottom ML To Angle for South-SW1") == null) {
			LayoutPanel.layoutList.add("South-Bottom ML To Angle for South-SW1", southML1LeftTOX, southBottomLineY, southML1LeftX, southML1Y);
		}
		else LayoutPanel.layoutList.getSegment("South-Bottom ML To Angle for South-SW1").draw();

        //****************  South-SW1 From South Bottom ML 1 to ML up hill to bridge ***
        if(LayoutPanel.getSwitch("South-SW1") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("South-SW1", true);
	        int mlSeg = LayoutPanel.layoutList.getSegment("South-Bottom ML-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("South-Bottom ML To Angle for South-SW1").getSegmentID();
			SwitchSegment sw1 = new SwitchSegment("South-SW1", LayoutPanel.refreshedSwId, mlSeg, slSeg, 17, southML1LeftTOX, southBottomLineY, southML1LeftTOX+30, southBottomLineY, southML1LeftX, southML1Y, thrown);
			LayoutPanel.switchList.add(sw1);

			TrackSegment ML = LayoutPanel.layoutList.getSegment("South-Bottom ML-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("South-Bottom ML To Angle for South-SW1");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw1);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
        } else LayoutPanel.getSwitch("South-SW1").draw();
		//********************************

        //Draw ML1 - horizontal track extending right from Switch Spur above...

        if(LayoutPanel.layoutList.getSegment("South-MainLine 1") == null) {
			LayoutPanel.layoutList.add("South-MainLine 1", southML1LeftX, southML1Y, southML1RightX, southML1Y);
		}
		else LayoutPanel.layoutList.getSegment("South-MainLine 1").draw();


		//Draw ML4 Left Angle - Left (closest to aisle) Track Up the hill to bridge...

        if(LayoutPanel.layoutList.getSegment("South-MainLine 4 Left Angle") == null) {
			LayoutPanel.layoutList.add("South-MainLine 4 Left Angle", southML1LeftX, southML1Y, southML4LeftX, southML4Y);
		}
		else LayoutPanel.layoutList.getSegment("South-MainLine 4 Left Angle").draw();

        seg = LayoutPanel.layoutList.getSegment("South-Bottom ML To Angle for South-SW1");
        TrackSegment nextSeg = LayoutPanel.layoutList.getSegment("South-MainLine 4 Left Angle");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-MainLine 4 Left Angle Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //****************  South-SW2  ***
		if(LayoutPanel.getSwitch("South-SW2") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("South-SW2", true);
	        int mlY = southML1Y-15;
	        int mlX = LayoutPanel.getXOnLine(southML1LeftX, southML1Y, southML4LeftX, southML4Y, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("South-MainLine 4 Left Angle").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("South-MainLine 1").getSegmentID();
	        SwitchSegment sw2 = new SwitchSegment("South-SW2", LayoutPanel.refreshedSwId, mlSeg, slSeg, 16, southML1LeftX, southML1Y, mlX, mlY, southML1RightX, southML1Y, thrown);
	        LayoutPanel.switchList.add(sw2);

	        TrackSegment ML = LayoutPanel.layoutList.getSegment("South-MainLine 4 Left Angle");
	        TrackSegment SL = LayoutPanel.layoutList.getSegment("South-MainLine 1");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw2);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setNextSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
		} else LayoutPanel.getSwitch("South-SW2").draw();
		//********************************

		//Draw ML1 Right Angle - Right Track up the hill to the bridge...

        if(LayoutPanel.layoutList.getSegment("South-MainLine 1 Right Angle") == null) {
			LayoutPanel.layoutList.add("South-MainLine 1 Right Angle", southML1RightX, southML1Y, southML3LeftX, southML3Y);
		}
		else LayoutPanel.layoutList.getSegment("South-MainLine 1 Right Angle").draw();
        seg = LayoutPanel.layoutList.getSegment("South-MainLine 1");
		nextSeg = LayoutPanel.layoutList.getSegment("South-MainLine 1 Right Angle");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-MainLine 1 Right Angle Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Draw ML3 - the lower of 2 tracks leading east from top of bridge...

        if(LayoutPanel.layoutList.getSegment("South-MainLine 3") == null) {
        	LayoutPanel.layoutList.add("South-MainLine 3", southML3LeftX, southML3Y, southML3RightX, southML3Y);
        }
        else LayoutPanel.layoutList.getSegment("South-MainLine 3").draw();
		seg = LayoutPanel.layoutList.getSegment("South-MainLine 1 Right Angle");
		nextSeg = LayoutPanel.layoutList.getSegment("South-MainLine 3");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-MainLine 3 Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Draw ML4 - the upper (nearest to aisle) of 2 tracks leading east from top of bridge...

        if(LayoutPanel.layoutList.getSegment("South-MainLine 4") == null) {
        	LayoutPanel.layoutList.add("South-MainLine 4", southML4LeftX, southML4Y, southML4RightX, southML4Y);
        }
        else LayoutPanel.layoutList.getSegment("South-MainLine 4").draw();
		seg = LayoutPanel.layoutList.getSegment("South-MainLine 4 Left Angle");
		nextSeg = LayoutPanel.layoutList.getSegment("South-MainLine 4");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-MainLine 4 Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	    //Draw ML5...

        if(LayoutPanel.layoutList.getSegment("South-MainLine 5") == null) {
        	LayoutPanel.layoutList.add("South-MainLine 5", southML5LeftX, southML5Y, southML5RightX, southML5Y);
        }
        else LayoutPanel.layoutList.getSegment("South-MainLine 5").draw();

		seg = LayoutPanel.layoutList.getSegment("West-Right ML Bottom Angle");
		nextSeg = LayoutPanel.layoutList.getSegment("South-MainLine 5");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-MainLine 5 Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//Draw ML5 Right Angle - leading under bridge = Split -  DONE Splitting up...
		//South-MainLine 5 Right Angle - Part A...
        if(LayoutPanel.layoutList.getSegment("South-MainLine 5 Right Angle-A") == null) {
        	LayoutPanel.layoutList.add("South-MainLine 5 Right Angle-A", southML5RightX, southML5Y, southSL5TOAngleX, southSL5TOAngleY);
        }
        else LayoutPanel.layoutList.getSegment("South-MainLine 5 Right Angle-A").draw();
		seg = LayoutPanel.layoutList.getSegment("South-MainLine 5");
		nextSeg = LayoutPanel.layoutList.getSegment("South-MainLine 5 Right Angle-A");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-MainLine 5 Right Angle-A Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//South-MainLine 5 Right Angle - Part B...
        if(LayoutPanel.layoutList.getSegment("South-MainLine 5 Right Angle-B") == null) {
        	LayoutPanel.layoutList.add("South-MainLine 5 Right Angle-B", southSL5TOAngleX, southSL5TOAngleY, southML2LeftX, southML2Y);
        }
        else LayoutPanel.layoutList.getSegment("South-MainLine 5 Right Angle-B").draw();
		seg = LayoutPanel.layoutList.getSegment("South-MainLine 5 Right Angle-A");
		nextSeg = LayoutPanel.layoutList.getSegment("South-MainLine 5 Right Angle-B");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-MainLine 5 Right Angle-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//South-MainLine 2 heading under bridge

		//Draw ML2 (ML from ML5 above under bridge heading East) - Split - DONE Splitting up...

		//South-MainLine 2 heading under bridge - Part-A...
        if(LayoutPanel.layoutList.getSegment("South-MainLine 2 heading under bridge-A") == null) {
        	LayoutPanel.layoutList.add("South-MainLine 2 heading under bridge-A", southML2LeftX, southML2Y, southSL2AngleBottomX+55, southML2Y);
        }
        else LayoutPanel.layoutList.getSegment("South-MainLine 2 heading under bridge-A").draw();
		seg = LayoutPanel.layoutList.getSegment("South-MainLine 5 Right Angle-B");
		nextSeg = LayoutPanel.layoutList.getSegment("South-MainLine 2 heading under bridge-A");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-MainLine 2 heading under bridge-A Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//South-MainLine 2 heading under bridge - Part-B...
        if(LayoutPanel.layoutList.getSegment("South-MainLine 2 heading under bridge-B") == null) {
        	LayoutPanel.layoutList.add("South-MainLine 2 heading under bridge-B", southSL2AngleBottomX+55, southML2Y, southML2RightX, southML2Y);
        }
        else LayoutPanel.layoutList.getSegment("South-MainLine 2 heading under bridge-B").draw();
		seg = LayoutPanel.layoutList.getSegment("South-MainLine 2 heading under bridge-A");
		nextSeg = LayoutPanel.layoutList.getSegment("South-MainLine 2 heading under bridge-B");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-MainLine 2 heading under bridge-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Angle from South-SW3 below along the South-MainLine 2 heading under bridge above...
        if(LayoutPanel.layoutList.getSegment("South- Angle SL Track to SL1 & SL2") == null) {
        	LayoutPanel.layoutList.add("South- Angle SL Track to SL1 & SL2", southSL2AngleBottomX+35, southSL2AngleBottomY, southSL2AngleBottomX+55, southML2Y);
        }
        else LayoutPanel.layoutList.getSegment("South- Angle SL Track to SL1 & SL2").draw();


        //****************  South-SW3  ***
        if(LayoutPanel.getSwitch("South-SW3") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("South-SW3", false);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("South-MainLine 2 heading under bridge-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("South- Angle SL Track to SL1 & SL2").getSegmentID();//findSegmentID(new Point(southSL2AngleBottomX+35, southSL2AngleBottomY));
			SwitchSegment sw3 = new SwitchSegment("South-SW3", LayoutPanel.refreshedSwId, mlSeg, slSeg, 21, southSL2AngleBottomX+55, southML2Y, southML2LeftX+15, southML2Y, southSL2AngleBottomX+35, southSL2AngleBottomY, thrown);
			LayoutPanel.switchList.add(sw3);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("South-MainLine 2 heading under bridge-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("South- Angle SL Track to SL1 & SL2");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw3);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				//SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("South-SW3").draw();
		//********************************

        //Draw SL Horizontal Track from above Spur to SL1 & SL2...

        if(LayoutPanel.layoutList.getSegment("South-SL Horizontal Track to SL1 & SL2") == null) {
        	LayoutPanel.layoutList.add("South-SL Horizontal Track to SL1 & SL2", southSL2AngleBottomX, southSL2AngleBottomY, southSL2AngleBottomX+35, southSL2AngleBottomY);
        }
        else LayoutPanel.layoutList.getSegment("South-SL Horizontal Track to SL1 & SL2").draw();
		seg = LayoutPanel.layoutList.getSegment("South-SL Horizontal Track to SL1 & SL2");
		nextSeg = LayoutPanel.layoutList.getSegment("South- Angle SL Track to SL1 & SL2");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-SL Horizontal Track to SL1 & SL2 Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


     	//Draw SL2 Right Angle (under Bridge) to SL1 & SL2 - Split -  DONE Splitting up...
		//South-SL2 Right Angle Under Bridge - Part A...
        if(LayoutPanel.layoutList.getSegment("South-SL2 Right Angle Under Bridge-A") == null) {
        	LayoutPanel.layoutList.add("South-SL2 Right Angle Under Bridge-A", southSL1RightX, southSL1Y, southSL2AngleBottomX, southSL2AngleBottomY);
        }
        else LayoutPanel.layoutList.getSegment("South-SL2 Right Angle Under Bridge-A").draw();
		seg = LayoutPanel.layoutList.getSegment("South-SL2 Right Angle Under Bridge-A");
		nextSeg = LayoutPanel.layoutList.getSegment("South-SL Horizontal Track to SL1 & SL2");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-SL2 Right Angle Under Bridge-A Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//South-SL2 Right Angle Under Bridge - Part B...
        if(LayoutPanel.layoutList.getSegment("South-SL2 Right Angle Under Bridge-B") == null) {
        	LayoutPanel.layoutList.add("South-SL2 Right Angle Under Bridge-B", southSL2RightX, southSL2Y, southSL1RightX, southSL1Y);
        }
        else LayoutPanel.layoutList.getSegment("South-SL2 Right Angle Under Bridge-B").draw();
		seg = LayoutPanel.layoutList.getSegment("South-SL2 Right Angle Under Bridge-B");
		nextSeg = LayoutPanel.layoutList.getSegment("South-SL2 Right Angle Under Bridge-A");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-SL2 Right Angle Under Bridge-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Parm Point have to be in clock-wise order!!
		LayoutPanel.drawRectagleBridgeIcon(botRX, botRY, botLX, botLY, topLX, topLY, topRX, topRY);

		//Draw SL1 (lowest)...

        if(LayoutPanel.layoutList.getSegment("South-SL1-lowest") == null) {
        	LayoutPanel.layoutList.add("South-SL1-lowest", southSL1LeftX, southSL1Y, southSL1RightX, southSL1Y);
        }
        else LayoutPanel.layoutList.getSegment("South-SL1-lowest").draw();


        //****************  South-SW4 - Switch between SL1 & SL2  ***
        if(LayoutPanel.getSwitch("South-SW4") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("South-SW4", false);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("South-SL2 Right Angle Under Bridge-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("South-SL1-lowest").getSegmentID();
			SwitchSegment sw4 = new SwitchSegment("South-SW4", LayoutPanel.refreshedSwId, mlSeg, slSeg, 24, southSL1RightX, southSL1Y, southSL2RightX, southSL2Y, southSL1RightX-40, southSL1Y, thrown);
			LayoutPanel.switchList.add(sw4);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("South-SL2 Right Angle Under Bridge-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("South-SL1-lowest");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw4);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("South-SW4").draw();
		//********************************


        //Draw SL2 (next higher)...

        if(LayoutPanel.layoutList.getSegment("South-SL2-next higher") == null) {
        	LayoutPanel.layoutList.add("South-SL2-next higher", southSL2LeftX, southSL2Y, southSL2RightX, southSL2Y);
        }
        else LayoutPanel.layoutList.getSegment("South-SL2-next higher").draw();
		seg = LayoutPanel.layoutList.getSegment("South-SL2-next higher");
		nextSeg = LayoutPanel.layoutList.getSegment("South-SL2 Right Angle Under Bridge-B");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-SL2-next higher Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//Angle to Dock Spurs SL Tracks below - split up - 2 frogs -   DONE Splitting up...
		//South-Angle to Dock Spurs - Part A...
        if(LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-A") == null) {
        	LayoutPanel.layoutList.add("South-Angle to Dock Spurs-A", southSL3RightX, southSL3Y, southSL5TOAngleX, southSL5TOAngleY);
        }
        else LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-A").draw();

		//South-Angle to Dock Spurs - Part B...
        if(LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-B") == null) {
        	LayoutPanel.layoutList.add("South-Angle to Dock Spurs-B", southSL4RightX, southSL4Y, southSL3RightX, southSL3Y);
        }
        else LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-B").draw();
        seg = LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-B");
		nextSeg = LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-A");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-Angle to Dock Spurs-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

		//South-Angle to Dock Spurs - Part C...
        if(LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-C") == null) {
        	LayoutPanel.layoutList.add("South-Angle to Dock Spurs-C", southSL5RightX, southSL5Y, southSL4RightX, southSL4Y);
        }
        else LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-C").draw();
        seg = LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-C");
		nextSeg = LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-B");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel South-Angle to Dock Spurs-C Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

        //****************  South-SW5 - The Switch from ML 5 to lead to the Dock Spurs ***
        if(LayoutPanel.getSwitch("South-SW5") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("South-SW5", false);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("South-MainLine 5 Right Angle-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-A").getSegmentID();
			SwitchSegment sw5 = new SwitchSegment("South-SW5", LayoutPanel.refreshedSwId, mlSeg, slSeg, 22, southSL5TOAngleX, southSL5TOAngleY, southML5RightX, southML5Y, southSL3RightX, southSL3Y, thrown);
			LayoutPanel.switchList.add(sw5);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("South-MainLine 5 Right Angle-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-A");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw5);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("South-SW5").draw();
		//********************************

		//SL Tracks to Dock...
        //Draw SL3...

        if(LayoutPanel.layoutList.getSegment("South-SL3 To Dock") == null) {
        	LayoutPanel.layoutList.add("South-SL3 To Dock", southSL3LeftX, southSL3Y, southSL3RightX, southSL3Y);
        }
        else LayoutPanel.layoutList.getSegment("South-SL3 To Dock").draw();


        //****************  South-SW6 - The Switch from Main Dock Spur to SL3 above ***
        if(LayoutPanel.getSwitch("South-SW6") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("South-SW6", false);
	        int mlY = southSL3Y-15;
	        int mlX = LayoutPanel.getXOnLine(southSL5TOAngleX, southSL5TOAngleY, southSL5RightX, southSL5Y, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("South-SL3 To Dock").getSegmentID();
			SwitchSegment sw6 = new SwitchSegment("South-SW6", LayoutPanel.refreshedSwId, mlSeg, slSeg, 23, southSL3RightX, southSL3Y, mlX, mlY, southSL3RightX-30, southSL3Y, thrown);
			LayoutPanel.switchList.add(sw6);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("South-SL3 To Dock");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw6);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("South-SW6").draw();
		//********************************


        //Draw SL4...

        if(LayoutPanel.layoutList.getSegment("South-SL4 To Dock") == null) {
        	LayoutPanel.layoutList.add("South-SL4 To Dock", southSL4LeftX, southSL4Y, southSL4RightX, southSL4Y);
        }
        else LayoutPanel.layoutList.getSegment("South-SL4 To Dock").draw();


        //****************  South-SW7 - The Switch from Main Dock Spur to SL4 above ***
        if(LayoutPanel.getSwitch("South-SW7") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("South-SW7", false);
	        int mlY = southSL4Y-15;
	        int mlX = LayoutPanel.getXOnLine(southSL5TOAngleX, southSL5TOAngleY, southSL5RightX, southSL5Y, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-C").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("South-SL4 To Dock").getSegmentID();
			SwitchSegment sw7 = new SwitchSegment("South-SW7", LayoutPanel.refreshedSwId, mlSeg, slSeg, 19, southSL4RightX, southSL4Y, mlX, mlY, southSL4RightX-30, southSL4Y, thrown);
			LayoutPanel.switchList.add(sw7);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-C");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("South-SL4 To Dock");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw7);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("South-SW7").draw();
		//********************************


        //Draw SL5 (closest to aisle)...

        if(LayoutPanel.layoutList.getSegment("South-SL5 Dock Closest to Aisle") == null) {
        	LayoutPanel.layoutList.add("South-SL5 Dock Closest to Aisle", southSL5LeftX, southSL5Y, southSL5RightX, southSL5Y);
        }
        else LayoutPanel.layoutList.getSegment("South-SL5 Dock Closest to Aisle").draw();
		seg = LayoutPanel.layoutList.getSegment("South-Angle to Dock Spurs-C");
		prevSeg = LayoutPanel.layoutList.getSegment("South-SL5 Dock Closest to Aisle");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel South-SL5 Dock Closest to Aisle Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

	}
}
