package apps.gui3.tcs;

import java.awt.Color;
import java.awt.Point;

public class LayoutPanelDrawLocoYard {

	private static LayoutPanelDrawLocoYard _instance = null;  //A singleton class!

	private LayoutPanel lp = null;

	private LayoutPanelDrawEast east = null;

	//Loco Yard Points
	private int LocoYdMainToEastAngleFrogY, LocoYdMainToEastAngleFrogX, locoYdMainY, locoYdMainLeftX = 0;
	private int locoYdMainRtFrogY, locoYdMainRtFrogX, locoYdMainLtFrogY, locoYdMainLtFrogX = 0;
	private int locoYdToTTableTopSpurEndPtX, locoYdToTTableTopSpurEndPtY, locoYdTopSpurAngToTTX, locoYdTopSpurAngToTTY = 0;
	private int locoYdToTTableMidSpurEndPtX, locoYdToTTableMidSpurEndPtY, locoYdMidSpurAngToTTX, locoYdMidSpurAngToTTY = 0;
	private int locoYdToTTableBotSpurEndPtX, locoYdToTTableBotSpurEndPtY, locoYdBotSpurAngToTTX, locoYdBotSpurAngToTTY = 0;
	private int locoYdMidFrogY, locoYdMidFrogX, locoYdMidFrogSLAngX, locoYdMidFrogSLAngY = 0;
	private int locoYdTopHorY, locoYdTopHorRtX, locoYdTopHorLtX, locoYdTopHorLtAngleEndPtX = 0;
	private int locoYdTopHorLtAngleEndPtY, locoYdTopHorMidFrogY, locoYdTopHorMidFrogX = 0;
	private int locoYdBotHorLtEndPtX, locoYdBotHorRtEndPtX, locoYdBotHorY = 0;

	public int locoYdTurnTablePtX, locoYdTurnTablePtY, locoYdTurnTableDiameter, locoYdTurnTableDegStep = 0;


	public LayoutPanelDrawLocoYard() {
		// Instantiate and Initialize...
		if (lp == null) lp = LayoutPanel.getInstance();

		if (east == null) east = LayoutPanelDrawEast.getInstance();
	}

	public static LayoutPanelDrawLocoYard getInstance() {
		if(_instance == null) {
			_instance = new LayoutPanelDrawLocoYard();
		}
		return _instance;
	}

	private int northBottomFrogX, northBottomFrogY, eastTopLeftSLToMidTopOuterX, eastTopLeftSLToMidTopOuterTopY = 0;
	private int northBottomLineLeftX, northBotLeftFrogX = 0;


	private void initializePointsFromOtherSections() {
		int northBottomLineRightX = (int)(LayoutPanel.pSize.getWidth()/1.35);
		northBottomFrogX = northBottomLineRightX-40;
		northBottomFrogY = 90;

		northBottomLineLeftX = (int)(LayoutPanel.pSize.getWidth()/3)+60;
		northBotLeftFrogX = (int)(LayoutPanel.pSize.getWidth()/2);

		eastTopLeftSLToMidTopOuterX = east.geteastTopLeftSLToMidTopOuterX();
		eastTopLeftSLToMidTopOuterTopY = east.geteastTopLeftSLToMidTopOuterTopY();

	}

	private void initializePoints() {

		initializePointsFromOtherSections();

		LocoYdMainToEastAngleFrogY = northBottomFrogY+60;
		LocoYdMainToEastAngleFrogX = LayoutPanel.getXOnLine(eastTopLeftSLToMidTopOuterX, eastTopLeftSLToMidTopOuterTopY, northBottomFrogX, northBottomFrogY, LocoYdMainToEastAngleFrogY);

		//CLAMP!!
		if(LocoYdMainToEastAngleFrogY > eastTopLeftSLToMidTopOuterTopY){
			LocoYdMainToEastAngleFrogY = eastTopLeftSLToMidTopOuterTopY;
			LocoYdMainToEastAngleFrogX = eastTopLeftSLToMidTopOuterX;
		}

		//System.out.println("\nLocoYd LocoYdMainToEastAngleFrogX="+LocoYdMainToEastAngleFrogX+
		//" LocoYdMainToEastAngleFrogY="+LocoYdMainToEastAngleFrogY+
		//" eastTopLeftSLToMidTopOuterX="+eastTopLeftSLToMidTopOuterX+
		//" eastTopLeftSLToMidTopOuterTopY="+eastTopLeftSLToMidTopOuterTopY+
		//" pSize.width="+LayoutPanel.pSize.width+" pSize.height="+LayoutPanel.pSize.height+"\n");

		locoYdMainY = LocoYdMainToEastAngleFrogY;
		locoYdMainLeftX = (int)(LayoutPanel.pSize.getWidth()/2);//midOuterVertTopX-30;
		locoYdMainRtFrogY = locoYdMainY;
		locoYdMainRtFrogX = LocoYdMainToEastAngleFrogX-120;
		locoYdMainLtFrogY = locoYdMainRtFrogY;
		locoYdMainLtFrogX = locoYdMainLeftX+25;

		locoYdToTTableTopSpurEndPtX = locoYdMainLeftX-70;
		locoYdToTTableTopSpurEndPtY = locoYdMainY+75;

		locoYdTopSpurAngToTTX = locoYdToTTableTopSpurEndPtX-40;
		locoYdTopSpurAngToTTY = locoYdToTTableTopSpurEndPtY+10;

		locoYdToTTableMidSpurEndPtX = locoYdToTTableTopSpurEndPtX+20;//30;
		locoYdToTTableMidSpurEndPtY = locoYdToTTableTopSpurEndPtY+10;

		locoYdMidSpurAngToTTX = locoYdToTTableMidSpurEndPtX-20;
		locoYdMidSpurAngToTTY = locoYdTopSpurAngToTTY;//+10;
		locoYdMidFrogY = locoYdMainLtFrogY + ((locoYdToTTableMidSpurEndPtY - locoYdMainLtFrogY)/2);
		locoYdMidFrogX = LayoutPanel.getXOnLine(locoYdMainLtFrogX, locoYdMainLtFrogY, locoYdToTTableMidSpurEndPtX, locoYdToTTableMidSpurEndPtY, locoYdMidFrogY);
		locoYdMidFrogSLAngX = locoYdMidFrogX;
		locoYdMidFrogSLAngY = locoYdMidFrogY + 30;

		locoYdToTTableBotSpurEndPtX = locoYdToTTableMidSpurEndPtX+12;
		locoYdToTTableBotSpurEndPtY = locoYdToTTableMidSpurEndPtY+10;
		locoYdBotSpurAngToTTX = locoYdToTTableBotSpurEndPtX-30;
		locoYdBotSpurAngToTTY = locoYdMidSpurAngToTTY+10;

		locoYdTopHorY = locoYdMainY-40;
		locoYdTopHorRtX = locoYdMainRtFrogX-30;
		locoYdTopHorLtX = northBottomLineLeftX+10;
		locoYdTopHorLtAngleEndPtX = locoYdTopHorLtX-45;
		locoYdTopHorLtAngleEndPtY = locoYdTopHorY+35;
		locoYdTopHorMidFrogY = locoYdTopHorY;
		locoYdTopHorMidFrogX = northBotLeftFrogX+30;
		locoYdBotHorLtEndPtX = locoYdTopHorLtX;
		locoYdBotHorRtEndPtX = locoYdTopHorMidFrogX-30;
		locoYdBotHorY = locoYdTopHorY+20;

		locoYdTurnTableDiameter = 80;
		locoYdTurnTableDegStep = 10;
		locoYdTurnTablePtX = locoYdMidSpurAngToTTX-locoYdTurnTableDiameter;//locoYdBotHorLtEndPtX-46; //-30;
		locoYdTurnTablePtY = locoYdMainY+40;//60;//+70;

	}

	public void draw() {

		initializePoints();

        //Draw Loco Yard...


		//Draw Main Loco Yard Spur off of East ML - Part A (furtherest East)...  DONE Splitting up...
        if(LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-A") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-ML Horizontal Spur off of East ML-A", locoYdMainRtFrogX, locoYdMainRtFrogY, LocoYdMainToEastAngleFrogX, LocoYdMainToEastAngleFrogY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-A").draw();

        //Draw Main Loco Yard Spur off of East ML - Part B (Middle)...
        if(LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-B") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-ML Horizontal Spur off of East ML-B", locoYdMainLtFrogX, locoYdMainY, locoYdMainRtFrogX, locoYdMainRtFrogY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-B").draw();
        TrackSegment seg = LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-A");
        TrackSegment prevSeg = LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-B");
	    if(seg != null && prevSeg != null)
	    	seg.connectSegmentToPrevSeg(prevSeg);
	    else System.out.println("LPanel LocoYard-ML Horizontal Spur off of East ML-A Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());

        //Draw Main Loco Yard Spur off of East ML - Part C (Furtherest West)...
        if(LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-C") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-ML Horizontal Spur off of East ML-C", locoYdMainLeftX, locoYdMainY, locoYdMainLtFrogX, locoYdMainY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-C").draw();
        seg = LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-B");
        prevSeg = LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-C");
	    if(seg != null && prevSeg != null)
	    	seg.connectSegmentToPrevSeg(prevSeg);
	    else System.out.println("LPanel LocoYard-ML Horizontal Spur off of East ML-B Else seg="+seg+" prevSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //****************  LocoYard-SW0  ***
        if(LayoutPanel.getSwitch("LocoYard-SW0") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("LocoYard-SW0", false);
	        int mlY = LocoYdMainToEastAngleFrogY-25;
	        int mlX = LayoutPanel.getXOnLine(eastTopLeftSLToMidTopOuterX, eastTopLeftSLToMidTopOuterTopY, northBottomFrogX, northBottomFrogY, mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("Mid-Top SL Section from OuterTopML to North Bottom").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-A").getSegmentID();
			SwitchSegment sw0 = new SwitchSegment("LocoYard-SW0", LayoutPanel.refreshedSwId, mlSeg, slSeg, 41, LocoYdMainToEastAngleFrogX, LocoYdMainToEastAngleFrogY, mlX, mlY, LocoYdMainToEastAngleFrogX-30, LocoYdMainToEastAngleFrogY, thrown);
			LayoutPanel.switchList.add(sw0);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("Mid-Top SL Section from OuterTopML to North Bottom");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-A");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw0);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("LocoYard-SW0").draw();
		//********************************


		//LocoYard-SL Angle to Top Horizontal Spur

		if(LayoutPanel.layoutList.getSegment("LocoYard-SL Angle to Top Horizontal Spur") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-SL Angle to Top Horizontal Spur", locoYdTopHorRtX, locoYdTopHorY, locoYdMainRtFrogX, locoYdMainRtFrogY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-SL Angle to Top Horizontal Spur").draw();

		//****************  LocoYard-SW1  ***
		if(LayoutPanel.getSwitch("LocoYard-SW1") == null) {
        boolean thrown = LayoutPanel.refreshSwitchList("LocoYard-SW1", false);
        int slY = locoYdMainRtFrogY-25;
        int slX = LayoutPanel.getXOnLine(locoYdMainRtFrogX, locoYdMainRtFrogY, locoYdTopHorRtX, locoYdTopHorY, slY);
        //pt1 = Frog pt2 = ML pt3 = Spur
        int mlSeg = LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-A").getSegmentID();
        int slSeg = LayoutPanel.layoutList.getSegment("LocoYard-SL Angle to Top Horizontal Spur").getSegmentID();
		SwitchSegment sw1 = new SwitchSegment("LocoYard-SW1", LayoutPanel.refreshedSwId, mlSeg, slSeg, 51, locoYdMainRtFrogX, locoYdMainRtFrogY, locoYdMainRtFrogX-35, locoYdMainRtFrogY, slX, slY, thrown);
		LayoutPanel.switchList.add(sw1);
		TrackSegment ML = LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-A");
		TrackSegment SL = LayoutPanel.layoutList.getSegment("LocoYard-SL Angle to Top Horizontal Spur");
		if(ML != null) {
			SegmentPoint sp = ML.insertFrogPoint(sw1);
			SegmentPoint spurSegLastSp = SL.getLastPoint();
			sp.setPrevSpur(spurSegLastSp);
			spurSegLastSp.setNext(sp);
		}
		} else LayoutPanel.getSwitch("LocoYard-SW1").draw();
		//********************************


        //LocoYard-Top Horizontal Spur - Part A   DONE Splitting up...
		if(LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur-A") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-Top Horizontal Spur-A", locoYdTopHorMidFrogX, locoYdTopHorMidFrogY, locoYdTopHorRtX, locoYdTopHorY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur-A").draw();
		seg = LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur-A");
		TrackSegment nextSeg = LayoutPanel.layoutList.getSegment("LocoYard-SL Angle to Top Horizontal Spur");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel LocoYard-Top Horizontal Spur-A Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());

        //LocoYard-Top Horizontal Spur - Part B
		if(LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur-B") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-Top Horizontal Spur-B", locoYdTopHorLtX, locoYdTopHorY, locoYdTopHorMidFrogX, locoYdTopHorMidFrogY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur-B").draw();
		seg = LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur-A");
		prevSeg = LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur-B");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel LocoYard-Top Horizontal Spur Else seg="+seg+" nextSeg="+prevSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Top Horizontal Spur Left Angle...

        if(LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur Left Angle") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-Top Horizontal Spur Left Angle", locoYdTopHorLtAngleEndPtX, locoYdTopHorLtAngleEndPtY, locoYdTopHorLtX, locoYdTopHorY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur Left Angle").draw();
		seg = LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur-B");
		prevSeg = LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur Left Angle");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel LocoYard-Top Horizontal Spur Left Angle Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //SL Angle to Bottom Horizontal Spur...

        if(LayoutPanel.layoutList.getSegment("LocoYard-SL Angle to Bottom Horizontal Spur") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-SL Angle to Bottom Horizontal Spur", locoYdBotHorRtEndPtX, locoYdBotHorY, locoYdTopHorMidFrogX, locoYdTopHorMidFrogY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-SL Angle to Bottom Horizontal Spur").draw();

        //****************  LocoYard-SW2  ***
        if(LayoutPanel.getSwitch("LocoYard-SW2") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("LocoYard-SW2", false);
	        int slY = locoYdTopHorMidFrogY+15;
	        int slX = LayoutPanel.getXOnLine(locoYdBotHorRtEndPtX, locoYdBotHorY, locoYdTopHorMidFrogX, locoYdTopHorMidFrogY,slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur-A").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("LocoYard-SL Angle to Bottom Horizontal Spur").getSegmentID();
			SwitchSegment sw2 = new SwitchSegment("LocoYard-SW2", LayoutPanel.refreshedSwId, mlSeg, slSeg, 73, locoYdTopHorMidFrogX, locoYdTopHorMidFrogY, locoYdTopHorMidFrogX-35, locoYdTopHorMidFrogY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw2);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("LocoYard-Top Horizontal Spur-A");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("LocoYard-SL Angle to Bottom Horizontal Spur");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw2);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("LocoYard-SW2").draw();
		//********************************


        //Draw Bottom Horizontal Spur...

        if(LayoutPanel.layoutList.getSegment("LocoYard-Bottom Horizontal Spur") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-Bottom Horizontal Spur", locoYdBotHorLtEndPtX, locoYdBotHorY, locoYdBotHorRtEndPtX, locoYdBotHorY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-Bottom Horizontal Spur").draw();
		seg = LayoutPanel.layoutList.getSegment("LocoYard-Bottom Horizontal Spur");
		nextSeg = LayoutPanel.layoutList.getSegment("LocoYard-SL Angle to Bottom Horizontal Spur");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel LocoYard-Bottom Horizontal Spur Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


		//****************************************
		//Create the LayoutPanel.turnTable object...
		//****************************************
        if(LayoutPanel.turnTable == null) {
        	//Create the LayoutPanel.turnTable...
        	//System.out.println("Layout Calling LayoutPanel.turnTable()...");
        	LayoutPanel.turnTable = new Turntable("LayoutPanel.turnTable", 1, locoYdTurnTablePtX, locoYdTurnTablePtY, locoYdTurnTableDiameter, locoYdTurnTableDegStep, lp);
        }else{
       		LayoutPanel.turnTable.draw();
        }


        //Draw Top Track Leading to LayoutPanel.turnTable...

        if(LayoutPanel.layoutList.getSegment("LocoYard-Top Track Leading to LayoutPanel.turnTable") == null) {
        	TrackSegment topSeg = LayoutPanel.layoutList.add("LocoYard-Top Track Leading to LayoutPanel.turnTable", locoYdToTTableTopSpurEndPtX, locoYdToTTableTopSpurEndPtY, locoYdMainLeftX, locoYdMainY);
        	LayoutPanel.turnTable.addConnection(1, topSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-Top Track Leading to LayoutPanel.turnTable").draw();
        seg = LayoutPanel.layoutList.getSegment("LocoYard-Top Track Leading to LayoutPanel.turnTable");
		nextSeg = LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-C");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel LocoYard-Top Track Leading to LayoutPanel.turnTable Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


	//LayoutPanel.g2d.setColor(Color.red);

		//Draw Mid Track Leading to LayoutPanel.turnTable - Part A...  DONE Splitting up...
        if(LayoutPanel.layoutList.getSegment("LocoYard-Mid Track Leading to turnTable-A") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-Mid Track Leading to turnTable-A", locoYdMidFrogX, locoYdMidFrogY, locoYdMainLtFrogX, locoYdMainY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-Mid Track Leading to turnTable-A").draw();

		//Draw Mid Track Leading to LayoutPanel.turnTable - Part B...
        if(LayoutPanel.layoutList.getSegment("LocoYard-Mid Track Leading to turnTable-B") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-Mid Track Leading to turnTable-B", locoYdToTTableMidSpurEndPtX, locoYdToTTableMidSpurEndPtY-5, locoYdMidFrogX, locoYdMidFrogY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-Mid Track Leading to turnTable-B").draw();
		seg = LayoutPanel.layoutList.getSegment("LocoYard-Mid Track Leading to turnTable-A");
		prevSeg = LayoutPanel.layoutList.getSegment("LocoYard-Mid Track Leading to turnTable-B");
		if(seg != null && prevSeg != null)
			seg.connectSegmentToPrevSeg(prevSeg);
		else System.out.println("LPanel LocoYard-Mid Track Leading to turnTable-B Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


    //LayoutPanel.g2d.setColor(LayoutPanel.defaultLayoutDrawColor); //Reset color if need be

        //****************  LocoYard-SW3  ***
        if(LayoutPanel.getSwitch("LocoYard-SW3") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("LocoYard-SW3", false);
	        int slY = locoYdMainY+15;
	        int slX = LayoutPanel.getXOnLine(locoYdMainLtFrogX, locoYdMainY, locoYdToTTableMidSpurEndPtX, locoYdToTTableMidSpurEndPtY-5,slY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-B").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("LocoYard-Mid Track Leading to turnTable-A").getSegmentID();
			SwitchSegment sw3 = new SwitchSegment("LocoYard-SW3", LayoutPanel.refreshedSwId, mlSeg, slSeg, 71, locoYdMainLtFrogX, locoYdMainY, locoYdMainLtFrogX-25, locoYdMainY, slX, slY, thrown);
			LayoutPanel.switchList.add(sw3);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("LocoYard-ML Horizontal Spur off of East ML-B");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("LocoYard-Mid Track Leading to turnTable-A");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw3);
				SegmentPoint spurSegLastSp = SL.getLastPoint();
				sp.setPrevSpur(spurSegLastSp);
				spurSegLastSp.setNext(sp);
			}
        } else LayoutPanel.getSwitch("LocoYard-SW3").draw();
		//********************************


        //Draw SL Spur Angle to Bottom Track Heading to LayoutPanel.turnTable...

		if(LayoutPanel.layoutList.getSegment("LocoYard-SL Spur Angle to Bottom Track to LayoutPanel.turnTable") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-SL Spur Angle to Bottom Track to LayoutPanel.turnTable", locoYdMidFrogX, locoYdMidFrogY, locoYdMidFrogSLAngX, locoYdMidFrogSLAngY);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-SL Spur Angle to Bottom Track to LayoutPanel.turnTable").draw();

		//****************  LocoYard-SW4  ***
		if(LayoutPanel.getSwitch("LocoYard-SW4") == null) {
	        boolean thrown = LayoutPanel.refreshSwitchList("LocoYard-SW4", false);
	        int mlY = locoYdMidFrogY+25;
	        int mlX = LayoutPanel.getXOnLine(locoYdToTTableMidSpurEndPtX, locoYdToTTableMidSpurEndPtY-5, locoYdMainLtFrogX, locoYdMainY,mlY);
	        //pt1 = Frog pt2 = ML pt3 = Spur
	        int mlSeg = LayoutPanel.layoutList.getSegment("LocoYard-Mid Track Leading to turnTable-A").getSegmentID();
	        int slSeg = LayoutPanel.layoutList.getSegment("LocoYard-SL Spur Angle to Bottom Track to LayoutPanel.turnTable").getSegmentID();
			SwitchSegment sw4 = new SwitchSegment("LocoYard-SW4", LayoutPanel.refreshedSwId, mlSeg, slSeg, 72, locoYdMidFrogX, locoYdMidFrogY, mlX, mlY, locoYdMidFrogX, locoYdMidFrogY+25, thrown);
			LayoutPanel.switchList.add(sw4);
			TrackSegment ML = LayoutPanel.layoutList.getSegment("LocoYard-Mid Track Leading to turnTable-A");
			TrackSegment SL = LayoutPanel.layoutList.getSegment("LocoYard-SL Spur Angle to Bottom Track to LayoutPanel.turnTable");
			if(ML != null) {
				SegmentPoint sp = ML.insertFrogPoint(sw4);
				SegmentPoint spurSegFirstSp = SL.getFirstPoint();
				sp.setPrevSpur(spurSegFirstSp);
				spurSegFirstSp.setPrev(sp);
			}
		} else LayoutPanel.getSwitch("LocoYard-SW4").draw();
		//********************************


        //Draw Horizontal Mid Track Leading to LayoutPanel.turnTable...

        if(LayoutPanel.layoutList.getSegment("LocoYard-Horizontal Mid Track Leading to LayoutPanel.turnTable") == null) {
        	TrackSegment midSeg = LayoutPanel.layoutList.add("LocoYard-Horizontal Mid Track Leading to LayoutPanel.turnTable", locoYdMidSpurAngToTTX, locoYdMidSpurAngToTTY-5, locoYdToTTableMidSpurEndPtX, locoYdToTTableMidSpurEndPtY-5);
        	LayoutPanel.turnTable.addConnection(0, midSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-Horizontal Mid Track Leading to LayoutPanel.turnTable").draw();
        seg = LayoutPanel.layoutList.getSegment("LocoYard-Horizontal Mid Track Leading to LayoutPanel.turnTable");
		nextSeg = LayoutPanel.layoutList.getSegment("LocoYard-Mid Track Leading to turnTable-B");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel LocoYard-Horizontal Mid Track Leading to LayoutPanel.turnTable Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());



        //Draw Bottom Track Leading to LayoutPanel.turnTable...

        if(LayoutPanel.layoutList.getSegment("LocoYard-Bottom Track Leading to LayoutPanel.turnTable") == null) {
    		TrackSegment botSeg = LayoutPanel.layoutList.add("LocoYard-Bottom Track Leading to LayoutPanel.turnTable", locoYdToTTableBotSpurEndPtX+5, locoYdToTTableBotSpurEndPtY-7, locoYdMidFrogSLAngX, locoYdMidFrogSLAngY);
    		LayoutPanel.turnTable.addConnection(35, botSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-Bottom Track Leading to LayoutPanel.turnTable").draw();
        seg = LayoutPanel.layoutList.getSegment("LocoYard-Bottom Track Leading to LayoutPanel.turnTable");
		nextSeg = LayoutPanel.layoutList.getSegment("LocoYard-SL Spur Angle to Bottom Track to LayoutPanel.turnTable");
		if(seg != null && nextSeg != null)
			seg.connectEastSegToNextNorthSeg(nextSeg);
		else System.out.println("LPanel LocoYard-Bottom Track Leading to LayoutPanel.turnTable Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


        //Draw Horizontal Bottom Track Leading to LayoutPanel.turnTable...

		if(LayoutPanel.layoutList.getSegment("LocoYard-Horizontal Bottom Track Leading to LayoutPanel.turnTable") == null) {
	    	LayoutPanel.layoutList.add("LocoYard-Horizontal Bottom Track Leading to LayoutPanel.turnTable", locoYdBotSpurAngToTTX, locoYdBotSpurAngToTTY-7, locoYdToTTableBotSpurEndPtX+5, locoYdToTTableBotSpurEndPtY-7);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-Horizontal Bottom Track Leading to LayoutPanel.turnTable").draw();
        seg = LayoutPanel.layoutList.getSegment("LocoYard-Horizontal Bottom Track Leading to LayoutPanel.turnTable");
		nextSeg = LayoutPanel.layoutList.getSegment("LocoYard-Bottom Track Leading to LayoutPanel.turnTable");
		if(seg != null && nextSeg != null)
			seg.connectSegmentToNextSeg(nextSeg);
		else System.out.println("LPanel LocoYard-Horizontal Bottom Track Leading to LayoutPanel.turnTable Else seg="+seg+" nextSeg="+nextSeg+" listSize="+LayoutPanel.layoutList.getListSize());


    	int trkLen = 40;
    	int rhSpacingBackWall = 5;
        //Draw RoundHouse tracks - bottom to top...
        Point pt1 = LayoutPanel.turnTable.getCirclePoint(18);
		if(LayoutPanel.layoutList.getSegment("LocoYard-RH Top Track Position 18") == null) {
			TrackSegment rhSeg = LayoutPanel.layoutList.add("LocoYard-RH Top Track Position 18", pt1.x-trkLen, pt1.y, pt1.x, pt1.y);
			LayoutPanel.turnTable.addConnection(18, rhSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-RH Top Track Position 18").draw();


        pt1 = LayoutPanel.turnTable.getCirclePoint(17);
		if(LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 17") == null) {
			TrackSegment rhSeg = LayoutPanel.layoutList.add("LocoYard-RH Track Position 17", pt1.x-(trkLen-4), pt1.y-rhSpacingBackWall, pt1.x, pt1.y);
			LayoutPanel.turnTable.addConnection(17, rhSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 17").draw();


    	pt1 = LayoutPanel.turnTable.getCirclePoint(16);
    	if(LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 16") == null) {
    		TrackSegment rhSeg = LayoutPanel.layoutList.add("LocoYard-RH Track Position 16", pt1.x-(trkLen-8), pt1.y-rhSpacingBackWall*2, pt1.x, pt1.y);
    		LayoutPanel.turnTable.addConnection(16, rhSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 16").draw();


    	pt1 = LayoutPanel.turnTable.getCirclePoint(15);
      	if(LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 15") == null) {
      		TrackSegment rhSeg = LayoutPanel.layoutList.add("LocoYard-RH Track Position 15", pt1.x-(trkLen-12), pt1.y-rhSpacingBackWall*3, pt1.x, pt1.y);
      		LayoutPanel.turnTable.addConnection(15, rhSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 15").draw();


    	pt1 = LayoutPanel.turnTable.getCirclePoint(14);
        if(LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 14") == null) {
        	TrackSegment rhSeg = LayoutPanel.layoutList.add("LocoYard-RH Track Position 14", pt1.x-(trkLen-16), pt1.y-rhSpacingBackWall*4, pt1.x, pt1.y);
        	LayoutPanel.turnTable.addConnection(14, rhSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 14").draw();


    	pt1 = LayoutPanel.turnTable.getCirclePoint(13);
      	if(LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 13") == null) {
      		TrackSegment rhSeg = LayoutPanel.layoutList.add("LocoYard-RH Track Position 13", pt1.x-(trkLen-20), pt1.y-rhSpacingBackWall*5, pt1.x, pt1.y);
      		LayoutPanel.turnTable.addConnection(13, rhSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 13").draw();


    	pt1 = LayoutPanel.turnTable.getCirclePoint(12);
      	if(LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 12") == null) {
      		TrackSegment rhSeg = LayoutPanel.layoutList.add("LocoYard-RH Track Position 12", pt1.x-(trkLen-24), pt1.y-rhSpacingBackWall*6, pt1.x, pt1.y);
      		LayoutPanel.turnTable.addConnection(12, rhSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 12").draw();


    	pt1 = LayoutPanel.turnTable.getCirclePoint(11);
      	if(LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 11") == null) {
      		TrackSegment rhSeg = LayoutPanel.layoutList.add("LocoYard-RH Track Position 11", pt1.x-(trkLen-28), pt1.y-rhSpacingBackWall*7, pt1.x, pt1.y);
      		LayoutPanel.turnTable.addConnection(11, rhSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 11").draw();


    	pt1 = LayoutPanel.turnTable.getCirclePoint(10);
       	if(LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 10") == null) {
       		TrackSegment rhSeg = LayoutPanel.layoutList.add("LocoYard-RH Track Position 10", pt1.x-(trkLen-32), pt1.y-rhSpacingBackWall*8, pt1.x, pt1.y);
       		LayoutPanel.turnTable.addConnection(10, rhSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-RH Track Position 10").draw();


    	pt1 = LayoutPanel.turnTable.getCirclePoint(9);
        if(LayoutPanel.layoutList.getSegment("LocoYard-RH Bottom Track Position 9") == null) {
        	TrackSegment rhSeg = LayoutPanel.layoutList.add("LocoYard-RH Bottom Track Position 9", pt1.x, pt1.y, pt1.x-(trkLen-36), pt1.y-rhSpacingBackWall*9);
        	LayoutPanel.turnTable.addConnection(9, rhSeg);
	    } else LayoutPanel.layoutList.getSegment("LocoYard-RH Bottom Track Position 9").draw();

        //Refresh LayoutPanel.turnTable...
        if(LayoutPanel.turnTable != null) {
       		LayoutPanel.turnTable.draw();
        }


	}
}
