package apps.gui3.tcs;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;


//************************************************************************************************
//************************************************************************************************
//
//Class Turntable Definition - A Java Object Class to build A Turntable Object 
//
//************************************************************************************************
//************************************************************************************************
public class Turntable {
	
	public boolean isOccupied;
	public final int NUM_CIRCLE_POINTS = 36;
	public Point []circlePts = new Point[NUM_CIRCLE_POINTS];

	private String name;
	private int segmentID;
	private int zoneID;
	private int powerDistrict;
    
	private Point circlePoint; //Upper, Left corner of Square the size of diameter
	private Point circleCenter;
	private Point circumferencePt1 = new Point(0,0);
	private Point circumferencePt2 = new Point(0,0);
	private int diameter;
	private int degreeIndex;
	private int currentTrkRotationPos = 0; // Zero is the 3:00 - 9:00 pos (Straight Across
	private JPanel buttonPanel;
	private JButton leftButton;
	private JButton rightButton;
	
	private LayoutPanel layoutPanel;
	
	private TrackSegment track;
	
    private int oppPos;
    private TrackSegment nextSeg;
    private TrackSegment prevSeg;
	
	private int count = 0;
	
	private ArrayList <TrackSegment> turntableConnectionsList = new ArrayList <TrackSegment>();
	//private double []angle = new double[NUM_CIRCLE_POINTS];
	
	private Point []innerCirPts = new Point[NUM_CIRCLE_POINTS];
  	
    public Turntable(String sName, int id, int circleX, int circleY, int pDiameter, int degIndex, LayoutPanel panel) {
   		name = sName;
   		segmentID = id;
   		zoneID = 0;
   		powerDistrict = 0;
   		isOccupied = false;
   		
   		diameter = pDiameter;
   		degreeIndex = degIndex;
   		//System.out.println("Turntable constructor... pos="+currentTrkRotationPos);
   		
   		circlePoint = new Point(circleX, circleY);
   		setCircleCenterPt();
   		
   		layoutPanel = panel;
   		
   		buttonPanel = new JPanel();
   		buttonPanel.setBounds(circleX+5, circleY+90, 75, 35); //80,30
        int red   =  9;  
        int green = 45;  
        int blue  = 90;  
        Color myBlue = new Color(red,green,blue);  
        buttonPanel.setBackground(myBlue);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.red));
        layoutPanel.add(buttonPanel);
        
   		//Create turntable control buttons
   		leftButton = new JButton("<");

	    //Add action listener to button
   		leftButton.addActionListener(new ActionListener() {
   			public void actionPerformed(ActionEvent e)
	        {
	            rotateLeft();
	        }
	    });
   		leftButton.setMargin(new Insets(0,0,0,0));
   		leftButton.setFont(new Font("Arial", Font.BOLD, 15));   
   		leftButton.setPreferredSize(new Dimension(25,25));
   		leftButton.setFocusPainted(false);
   		leftButton.setVisible(true);
   		buttonPanel.add(leftButton);

   		//rightButton = new BasicArrowButton(BasicArrowButton.EAST);
   		rightButton = new JButton(">");
   		
	    //Add action listener to button
   		rightButton.addActionListener(new ActionListener() {
   			public void actionPerformed(ActionEvent e)
	        {
  	            rotateRight();
	        }
	    });      
   		rightButton.setMargin(new Insets(0,0,0,0));
   		rightButton.setFont(new Font("Arial", Font.BOLD, 15));   
   		rightButton.setPreferredSize(new Dimension(25,25));
   		rightButton.setFocusPainted(false);
   		
   		rightButton.setVisible(true);
   		buttonPanel.add(rightButton);
   		


   		layoutPanel.revalidate();


   		
   		for(int i = 0; i < NUM_CIRCLE_POINTS; i++) {
   			circlePts[i] = new Point();
   			innerCirPts[i] = new Point();
   		}
   		
   		//Initialize the theta angles (degrees) for the Engine House Rotation Positions...
   		/*
   		angle[0] = angle[9] = angle[18] = angle[27] = 0;
   		for(int i = 1; i < 9; i++) angle[i] = 5*i;
   		for(int i = 10; i < 18; i++) angle[i] = 5*i;
   		for(int i = 19; i < 27; i++) angle[i] = 5*i;
   		angle[28] = 40;
   		angle[28] = 35;
   		angle[30] = 30;
   		angle[31] = 25;
   		angle[32] = 20;
   		angle[33] = 18;
   		angle[34] = 14;
   		angle[35] = 7;
   		 */
   		
		for (int i = 0; i < NUM_CIRCLE_POINTS; i++) {
			TrackSegment seg = new TrackSegment("ttConnect"+i, 200+i, 0, 0, 0, 0);
			turntableConnectionsList.add(seg);	
		}
   		
   		initialCirclePts();
   		
   		//Draw the turntable...
   		draw();
   		
   		initialCirclePts();
    }
    
    public void unManage() {
    	rightButton.setVisible(false);
    	rightButton.setVisible(false);
    	buttonPanel.setVisible(false);
    }

    public void draw() {
    	if(LayoutPanel.g2d != null) {
    		
    		//For testing
    		//if(((count++)%100)==0)
    		//	rotateLeft();
    		
    		int red   = 68;
    		int green = 70;  
            int blue  = 64;  
            Color myGray = new Color(red,green,blue); 
			LayoutPanel.g2d.setColor(myGray);
    		//TODO LayoutPanel.g2d.fillOval(circlePoint.x, circlePoint.y, diameter-2, diameter-2);
    		
    		//draw Turntable Ring...
    		int red1   = 135;  
            int green1 = 112;  
            int blue1  = 87;  
            Color myBrown = new Color(red1,green1,blue1); 
			LayoutPanel.g2d.setColor(myBrown);  
            
    		Shape turnTable = new Ellipse2D.Double(circlePoint.x, circlePoint.y, diameter, diameter);
    		LayoutPanel.g2d.draw(turnTable);
    		
    		//Reset Layout Colors...
   			LayoutPanel.setLayoutColorAndStroke();	
   			
   			initialCirclePts();
    		drawTrack();
    		
       		//Setup end point connections 
       		adjustSegmentConnects(false);
    	}
    }
    

    private void drawTrack() {
    	if(LayoutPanel.g2d != null) {

    		int r = diameter / 2;
    		
    		//Default values for straight across where currentTrkRotationPos == 0 or 18...
    		double x1 = circlePoint.x;
    		double y1 = circlePoint.y + r;
    		double x2 = circlePoint.x + diameter;
    		double y2 = y1;
    		
    		double degreeAngle = 0;
			double degreeAngle2 = 0;
			
			int engineHouseIndex = 0;
    		
    		if(currentTrkRotationPos != 0 && currentTrkRotationPos != 18) {
    			if(currentTrkRotationPos > 18) {
    				degreeAngle = (currentTrkRotationPos-18)*degreeIndex;
    				degreeAngle2 = currentTrkRotationPos*degreeIndex;
    				engineHouseIndex = currentTrkRotationPos-18;
    			} else {
    				degreeAngle = currentTrkRotationPos*degreeIndex;
    				degreeAngle2 = (currentTrkRotationPos+18)*degreeIndex;
    				engineHouseIndex = currentTrkRotationPos+18;
    			}
    			x1 = circleCenter.x + Math.round(r * Math.cos(Math.toRadians(degreeAngle)));
				y1 = circleCenter.y - Math.round(r * Math.sin(Math.toRadians(degreeAngle)));
    			x2 = circleCenter.x + Math.round(r * Math.cos(Math.toRadians(degreeAngle2)));
    			y2 = circleCenter.y - Math.round(r * Math.sin(Math.toRadians(degreeAngle2)));
    		}
    		
    		//For pos = 0, engine house is on the opposite end (pos 18)...
    		if(currentTrkRotationPos == 0) engineHouseIndex = currentTrkRotationPos+18;
    		
    		circumferencePt1.x = (int)x1;
    		circumferencePt1.y = (int)y1;
    		circumferencePt2.x = (int)x2;
    		circumferencePt2.y = (int)y2;
    			
    		if(isOccupied == true)
    			LayoutPanel.g2d.setColor(Color.green);
    		
           	if(track == null) {
           		track = LayoutPanel.layoutList.add("LocoYard-TurnTable Track", circumferencePt1.x, circumferencePt1.y, circumferencePt2.x, circumferencePt2.y);
    	    } else {
    	    	LayoutPanel.layoutList.updatePoints(track.getSegmentName(), circumferencePt1.x, circumferencePt1.y, circumferencePt2.x, circumferencePt2.y);
    	    }

           	//*****************************
           	//Draw Engine House...
           	//*****************************
           	//int red   = 148;
    		//int green = 138;  
            //int blue  = 84;  
            //Color myBrown = new Color(red,green,blue); 
			//LayoutPanel.g2d.setColor(myBrown);
            int red   = 223;
    		int green = 116;  
            int blue  = 9;  
            Color myOrange = new Color(red,green,blue);
            LayoutPanel.g2d.setColor(myOrange);
			//LayoutPanel.g2d.setColor(Color.red);
			//LayoutPanel.g2d.setColor(Color.yellow);
			
			SegmentPoint sp1 = track.pointsList.getFirstPoint();
			if(currentTrkRotationPos > 0 && currentTrkRotationPos <= 18) {
				sp1 = track.pointsList.getLastPoint();
			}
			
			int x3 = sp1.getPointX();
			int y3 = sp1.getPointY();
			int x4 = innerCirPts[engineHouseIndex].x;
			int y4 = innerCirPts[engineHouseIndex].y;

			LayoutPanel.g2d.setStroke(new BasicStroke(6));
			
	   		LayoutPanel.g2d.drawLine(x3, y3, x4, y4);

    		//Reset Layout Colors...
    		LayoutPanel.setLayoutColorAndStroke();
    	}
    }
    
    public void processMouseClick(Point pt) {
    	if(LayoutPanel.g2d != null) {
    		//Top Left
    		//int x0 = circlePoint.x;
    		//int y0 = circlePoint.y;
    		
    		//Lower Right
    		//int x1 = circlePoint.x + diameter;
    		//int y1 = circlePoint.y + diameter;
    		
    		//boolean inTurnTable = ((pt.x >= x0 && pt.x <= x1) && (pt.y >= y0 && pt.y <= y1));
    		
    		int xOnLine = LayoutPanel.getXOnLine(circumferencePt1.x, circumferencePt1.y, circumferencePt2.x, circumferencePt2.y, pt.y);
    		
    		System.out.println("processMouseClick  xOnLine="+xOnLine+" pt.x="+pt.x+" pt.y="+pt.y+" centx="+circleCenter.x+" centy="+circleCenter.y+" circX1="+circumferencePt1.x+" circy1="+circumferencePt1.y+" circX2="+circumferencePt2.x+" circy2="+circumferencePt2.y+" pos="+currentTrkRotationPos+
    				" quad="+getQuadientClicked(pt));

    		if(currentTrkRotationPos == 0 || currentTrkRotationPos == 18) {
    			//Track is horizontal!
    			System.out.println("Turntable - processMouseClick...Track is horizontal");
    			if((getQuadientClicked(pt) == 1) || (getQuadientClicked(pt) == 3))
    				rotateLeft();
    			else
    				rotateRight();
    		} else if((currentTrkRotationPos == 9 || currentTrkRotationPos == 27)) {
    			//Track is vertical!
    			System.out.println("Turntable - processMouseClick...Track is vertical");
    			if((getQuadientClicked(pt) == 1) || (getQuadientClicked(pt) == 3))
    				rotateRight();
    			else
    				rotateLeft();
    		} else if((currentTrkRotationPos > 0 || currentTrkRotationPos < 9) ||
    				  (currentTrkRotationPos > 18 || currentTrkRotationPos < 27)) {
    			//Track has Positive Slope!
    			System.out.println("Turntable - processMouseClick...Track has Negative Slope");
    			if(getQuadientClicked(pt) <= 2)
    				if(xOnLine < pt.x )
    					rotateRight();
    				else
    					rotateLeft();
    			else //Quad 3 or 4
    				if(xOnLine < pt.x )
    					rotateLeft();
    				else
    					rotateRight();
  				
    		} else { //currentTrkRotationPos > 9 && currentTrkRotationPos < 18 || currentTrkRotationPos > 27 && currentTrkRotationPos < 36
    			//Track has Negative Slope!
    			System.out.println("Turntable - processMouseClick...Track has Negative Slope");
    			//if(getQuadientClicked(pt) <= 2)
    				if(xOnLine < pt.x )
    					rotateRight();
    				else
    					rotateLeft();
    			//else //Quad 3 or 4
    				//if(xOnLine < pt.x )
    				//	rotateLeft();
    				//else
    				//	rotateRight();
    		}
    	}
    }
    
    private int getQuadientClicked(Point pt) {
    	//Return 1, 2, 3, 4 - # of Quadient where 1 is top right; 2 is top left
    	//3 is lower left and 4 and lower right
    	int retQuad = 0;
    	//NOTE: Y Logic is consistent with the vertical screen having Y increase from zero at the top!!
		if(pt.x >= circleCenter.x && pt.y <= circleCenter.y)
			retQuad = 1;
		else if(pt.x < circleCenter.x && pt.y <= circleCenter.y)
			retQuad = 2;
		else if(pt.x < circleCenter.x && pt.y > circleCenter.y)
			retQuad = 3;
		else if(pt.x >= circleCenter.x && pt.y > circleCenter.y)
			retQuad = 4;
		return retQuad;
    }
    
    public void rotateRight() {
    	if(LayoutPanel.g2d != null) {
    		
    		currentTrkRotationPos--;
    		if(currentTrkRotationPos < 0) 
    			currentTrkRotationPos += NUM_CIRCLE_POINTS;
    		
    		System.out.println("Turntable - rotateRight...pos="+currentTrkRotationPos);
    		
    		adjustSegmentConnects(true);
    		
    		//Re-Draw the turntable...
       		draw();
       		
            //DEBUG - Print out TT segment & connected segments...
         	//System.out.println("\n*************\nTurntable:rotateRight - PRINTING OUT TT Segs [pos="+currentTrkRotationPos+" oppPos="+oppPos);
         	//track.printSegment();
         	//if(nextSeg == null) System.out.println("\nnextSeg == null!!\n");
         	//else nextSeg.printSegment();
         	//if(prevSeg == null) System.out.println("\nprevSeg == null!!\n");
         	//else prevSeg.printSegment();
    	}
    }
    
    public void rotateLeft() {
    	if(LayoutPanel.g2d != null) {
    		currentTrkRotationPos++;
    		
    		if(currentTrkRotationPos >= NUM_CIRCLE_POINTS) 
    			currentTrkRotationPos -= NUM_CIRCLE_POINTS;
    		
    		System.out.println("Turntable - rotateLeft... pos="+currentTrkRotationPos);
    		
    		adjustSegmentConnects(false);
    		
    		//Re-Draw the turntable...
       		draw();
       		
    		//DEBUG - Print out TT segment & connected segments...
        	//System.out.println("\n*************\nTurntable:rotateLeft - PRINTING OUT TT Segs [pos="+currentTrkRotationPos+" oppPos="+oppPos);
        	//track.printSegment();
        	//if(nextSeg == null) System.out.println("\nnextSeg == null!!\n");
        	//else nextSeg.printSegment();
        	//if(prevSeg == null) System.out.println("\nprevSeg == null!!\n");
        	//else prevSeg.printSegment();
    	}
    }
    
    public void adjustSegmentConnects(boolean rotateRight) {
    	//How does this work if have a loco on the TT track while rotating from 9 to 10???

    	boolean rotateLeft = !rotateRight;
    	
        if((rotateRight && (currentTrkRotationPos == 9 || currentTrkRotationPos == 27)) ||
           (rotateLeft && (currentTrkRotationPos == 10 || currentTrkRotationPos == 28))) {
        	track.flipSegEndPoints();
        }

        oppPos = 0;
        nextSeg = null;
        prevSeg = null;

        if(currentTrkRotationPos > 18) oppPos = currentTrkRotationPos - 18;

        else oppPos = currentTrkRotationPos + 18;

        if(currentTrkRotationPos <= 9 || currentTrkRotationPos >= 28) {
        	nextSeg = getConnectionSegment(currentTrkRotationPos);
        	prevSeg = getConnectionSegment(oppPos);
        } else {
        	nextSeg = getConnectionSegment(oppPos);
        	prevSeg = getConnectionSegment(currentTrkRotationPos);

        }
        //System.out.println("Turntable:adjustSegmentConnects pos="+currentTrkRotationPos+" nextSeg="+nextSeg.getSegmentID()+
        //		" prevSeg="+prevSeg.getSegmentID());
        
        track.processTurntableRotate(currentTrkRotationPos, nextSeg, prevSeg);  
    }
    
    private TrackSegment getConnectionSegment(int pos) {
    	TrackSegment retSeg = null;

        if(pos >= 0 && pos < NUM_CIRCLE_POINTS) {
        	if(turntableConnectionsList.get(pos).getSegmentName().contains("ttConnect") == false)
        		retSeg = turntableConnectionsList.get(pos);
        }
        return retSeg;
    }
    
    public Point getCirclePoint(int index) {
    	Point retPoint = new Point(0,0);
    	
    	if(index >= 0 && index < NUM_CIRCLE_POINTS) {
    		retPoint.x = circlePts[index].x;
    		retPoint.y = circlePts[index].y;
    	}
    		
    	return retPoint;
    }
    
    public void addConnection(int pos, TrackSegment seg) {
    	turntableConnectionsList.set(pos, seg);
    }

    public int getNumConnections() {
    	return turntableConnectionsList.size();
    }  

    public TrackSegment getConnection(String name) {
    	TrackSegment retSeg = null;
    	for(int i = 0; i < turntableConnectionsList.size(); i++) {
    		if(turntableConnectionsList.get(i).getSegmentName().equals(name) == true) {
    			retSeg = turntableConnectionsList.get(i);
    		} 
    	}
    	return retSeg;
    }  
       
    public TrackSegment getConnection(int pos) {
    	return turntableConnectionsList.get(pos);
    }  

    public String getSegmentName() {
    	return (name);
    }
    public int getSegmentID() {
    	return (segmentID);
    }
    public int getZoneID() {
    	return (zoneID);
    }
    public int getPowerDistrict() {
    	return (powerDistrict);
    }
    public Point getCirclePoint() {
    	return (circlePoint);
    } 
    public int getDiameter() {
    	return (diameter);
    }
    public int getDegreeIndex() {
    	return (degreeIndex);
    }
    
    public void setZoneID(int id) {
    	zoneID = id;
    }
    public void setPowerDistrict(int powerD) {
    	powerDistrict = powerD;
    }
    public void setCirclePoint(Point pt) {
    	circlePoint = pt;
    } 
    public void setDiameter(int dia) {
    	diameter = dia;
    }
    public void setDegreeIndex(int Index) {
    	degreeIndex = Index;
    }   

    private void setCircleCenterPt() {
    	//CirclePoint is the Upper, Left corner of Square the size of diameter
    	
    	int centerX = circlePoint.x + diameter/2;
    	int centerY = circlePoint.y + diameter/2;
    	
    	circleCenter = new Point(centerX, centerY);
    }
    
    private void initialCirclePts() {
    	int r = diameter / 2;
    	int x1 = 0;
    	int y1 = 0;
    	double degreeAngle = 0;
    	
    	for(int i = 0; i < NUM_CIRCLE_POINTS; i++) {
    		degreeAngle = i*degreeIndex;
    		
    		x1 = circleCenter.x + (int)Math.round(r * Math.cos(Math.toRadians(degreeAngle)));
			y1 = circleCenter.y - (int)Math.round(r * Math.sin(Math.toRadians(degreeAngle)));
			circlePts[i].x = x1;
			circlePts[i].y = y1;
			
			//Get a second set of points for drawing the engine house marking...
			x1 = circleCenter.x + (int)Math.round((r-3) * Math.cos(Math.toRadians(degreeAngle)));
			y1 = circleCenter.y - (int)Math.round((r-3) * Math.sin(Math.toRadians(degreeAngle)));
			innerCirPts[i].x = x1;
			innerCirPts[i].y = y1;
   		
			
			//System.out.println("Turntable - initialCirclePts... i="+i+" circlePtsI.x="+circlePts[i].x+" circlePtsI.y="+circlePts[i].y+
			//		" inner[i].x="+innerCirPts[i].x+" innerCirPts[i].y="+innerCirPts[i].y);
    	}
    }
}

