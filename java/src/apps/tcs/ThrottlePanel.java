package apps.gui3.tcs;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//************************************************************************************************
//************************************************************************************************
//
//Class ThrottlePanel Definition - A Java Object Class to build the Throttle GUI
//
//************************************************************************************************
//************************************************************************************************

public class ThrottlePanel extends JPanel {

	public static Graphics2D g2d;

	public static ArrayList <Train> availableTrains = new ArrayList <Train> ();

	private static int instanceCount;
	private Point mousePoint = null;
	private Point lastMousePoint = null;

	private JLabel speedLabel = null;
	private int loopCt = 0;

	MouseListener ml = new MouseListener(){
		public void mouseClicked(MouseEvent e) {
			//System.out.println("\n\n*************************\nmouseClicked");
			mousePoint = e.getPoint();

			if(mousePoint != lastMousePoint) {
				/*
				if(isMouseClickOnTurntable(mousePoint) == true) {
					if(turnTable != null){
						turnTable.processMouseClick(mousePoint);
					}
				} else { //Check for click on a switch
					for(int i = 0; i < switchList.size(); i++) {
						if (switchList.get(i).isSwitchFound(mousePoint)){
							break;
						}
					}
					lastMousePoint = mousePoint;
				}
				*/
			}
		}
		public void mousePressed(MouseEvent e) {
			//mousePoint = e.getPoint();
	    	//System.out.println("LayoutPanel mousePressed - x="+mousePoint.x+" y="+mousePoint.y);
	    }
	    public void mouseReleased(MouseEvent e) {/*System.out.println("mouseReleased");*/}
	    public void mouseExited(MouseEvent e) {/*System.out.println("\n\nmouseExited\n\n");*/}
	    public void mouseEntered(MouseEvent e) {/*System.out.println("mouseEntered");*/}
	};

	class SliderListener implements ChangeListener {
	    public void stateChanged(ChangeEvent e) {
	        JSlider source = (JSlider)e.getSource();
	        if (!source.getValueIsAdjusting()) {
	        	adjSpeed((int)source.getValue());
	        }
	    }
	}

	class CheckBoxListener implements ItemListener{
        public void itemStateChanged(ItemEvent e){
        	processPowerSw((e.getStateChange() == ItemEvent.SELECTED));
        }
    }

	class ComboBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JComboBox jcmbType = (JComboBox) e.getSource();
			String lName = (String) jcmbType.getSelectedItem();
			changeLoco(lName);
		}
	}

	class RadioListener implements  ActionListener {
		public void actionPerformed(ActionEvent e) {
			JRadioButton rb = (JRadioButton) e.getSource();
			System.out.println("RadioListener actionPerformed called!!");
			if (rb.getText().equals("Reverse")){
				System.out.print("ActionEvent received: Reverse Selected!");
				changeDirection(false);
				revButton.setSelected(true);
				forButton.setSelected(false);
			} else {
				System.out.print("ActionEvent received: Forward Selected!");
				changeDirection(true);
				forButton.setSelected(true);
				revButton.setSelected(false);
			}
		}
	}


	private Dimension  pSize = new Dimension(800, 600); //Panel Size
    private Dimension  prevPanelSize = new Dimension(800, 600);
    private boolean    hasPanelSizeChanged;

    //  Loco Address readout
    //- Loco Dir readout
    //- Loco DCC F(x) states readouts
    //- Amp & Volt readouts?
    //- Key Pad to change states, speed, direction, states of switches and accessories.

    private JSlider   throttle; //Throttle to change speed
    private int       throttleID;

    private JCheckBox powerSwitch;
    private JComboBox comboLocosList;

    private JRadioButton revButton;
    private JRadioButton forButton;

    private boolean   isPoweredUp;
    private boolean   isAttachedToLoco;
    private int       dccAddress;
    private int       speed; // (0 .. 99)
    private boolean   f1State;
	//More F(x) States??

    private int       attachedTrainID;


    static final int MIN_SPEED  =  0;
	static final int MAX_SPEED  = 99;
	static final int INIT_SPEED =  0;

	static final int NOT_SET = -1;

	//********************************************************
	//********************************************************
	//
	// ThrottlePanel()
	//
	//********************************************************
	//********************************************************
	public ThrottlePanel(int id) {

		throttleID = id;
		isAttachedToLoco = false;
   		dccAddress = NOT_SET;
   		attachedTrainID = NOT_SET;

		instanceCount++;
		//Temporary Code - need to read in from dat file!
		//For now, add a few trains for test...
		if(instanceCount == 1) {

			String[] locoNames = {"Test1", "Test2", "Test3", "GN555", "GN556" };
			int[] locoAddrs = {45, 3, 56, 419, 425};
			for(int i = 0; i < 5; i++) {
				Train train = new Train(i);
				Loco loco   = new Loco(locoNames[i], i, locoAddrs[i]);
				loco.setTrainID(i);
				train.setLoco(loco);
				train.setThrottleID(NOT_SET);
				availableTrains.add(train);
			}
		}

		setBorder(BorderFactory.createLineBorder(Color.darkGray));

        int red   = 29;
        int green = 67;
        int blue  = 34;
        Color myGreen = new Color(red,green,blue);
        setBackground(myGreen);
        Dimension panelD = new Dimension(200,150);
        setPreferredSize(panelD);
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 0));

        //Create Power Switch Check Box
        JCheckBox powerSwitch = new JCheckBox("PWR");
        powerSwitch.addItemListener(new CheckBoxListener());
        add(powerSwitch);
        powerSwitch.setVisible(true);

        //Create the Throttle...
      	//throttle = new JSlider(JSlider.VERTICAL, MIN_SPEED, MAX_SPEED, INIT_SPEED);
      	throttle = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED, INIT_SPEED);
      	throttle.addChangeListener(new SliderListener());
      	add(throttle);

      	//Turn on labels at major tick marks.
      	throttle.setMajorTickSpacing(10);
      	throttle.setMinorTickSpacing(1);
      	//throttle.setPaintTicks(true);
      	throttle.setPaintLabels(true);
      	red   = 146;
        green = 188;
        blue  = 134;
        Color mySliderBG = new Color(red,green,blue);
      	throttle.setBackground(mySliderBG);
      	throttle.setVisible(true);
      	throttle.setEnabled(false);

      	//Create Loco Selection JComboBox
      	//TODO - Read in available Loco Names from ArrayList
      	String[] availLocoNames = { "Disattached", "Test1", "Test2", "Test3", "GN555", "GN556" };
		// Create the combo box, and set 1st item as Default
		comboLocosList = new JComboBox(availLocoNames);
		comboLocosList.setSelectedIndex(0);
		comboLocosList.addActionListener(new ComboBoxListener());
		add(comboLocosList);
		comboLocosList.setVisible(true);
		comboLocosList.setEnabled(false);

		//Create Forward / Reverse Radio Box
		// Create the buttons.
	    revButton = new JRadioButton("Reverse", false);
	    revButton.setVisible(true);
	    revButton.setEnabled(false);
	    add(revButton);

	    forButton = new JRadioButton("Forward", true);
	    forButton.setVisible(true);
	    forButton.setEnabled(false);
	    add(forButton);

	    // Register a listener for the radio buttons.
	    RadioListener myListener = new RadioListener();
	    revButton.addActionListener(myListener);
	    forButton.addActionListener(myListener);

        //Create Speed Readout...
      	JPanel speedReadoutBox = new JPanel();
      	speedReadoutBox.setBorder(BorderFactory.createLineBorder(Color.white));
      	speedReadoutBox.setBackground(Color.black);
      	speedReadoutBox.setPreferredSize(new Dimension(90,30));
      	speedLabel = new JLabel("Speed: 00");
      	//speedLabel.setFont(new Font("Verdana",1,20));
      	speedLabel.setFont(new Font("Calibri",1,20));
      	red   = 248;
        green = 161;
        blue  = 74;
        Color myOrange = new Color(red,green,blue);
      	speedLabel.setForeground(myOrange);
      	//speedLabel.setForeground(Color.yellow);
      	speedReadoutBox.add(speedLabel);
      	add(speedReadoutBox);
      	speedReadoutBox.setVisible(true);






        //MouseListener for finding points for debug and switch placement
//TODO - IS THIS NEEDED???????????????????????????
        addMouseListener(ml);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateThrottle(g);
    }

	private void updateThrottle(Graphics g) {
		g2d = (Graphics2D) g;

        //Set Color and Stroke for drawing Bench Work...
		//setLayoutColorAndStroke();

        pSize = getSize();

       	//System.out.println("drawThrottle speed="+speed);
       	if((pSize.width != prevPanelSize.width) || (pSize.height != prevPanelSize.height)) {
       		hasPanelSizeChanged = true;
       	} else {
       		hasPanelSizeChanged = false;
       	}

		if(hasPanelSizeChanged == true) {
			//layoutList.resetList();  //Remove List nodes and recreate!
			//System.out.println("\nLayoutPanel PanelSizeChanged!!");
			//System.out.println("LayoutPanel psize="+pSize+" prevPanelSize="+prevPanelSize);
			//turnTable = null; //Force a create of the turntable...
		}

		//********************************************************
		// Update the Controls!
		//********************************************************


		//********************************************************
		// isAttachedToLoco? - If so, Update the Train Movement!
		//********************************************************

		if(isAttachedToLoco && dccAddress != NOT_SET) {
			Train train = getTrain(dccAddress);
	    	if(train != null) {
	    		if(train.getSpeed() > 0) {
	    			int speedDelay = 100-train.getSpeed();
	    			if((loopCt++)%speedDelay == 0)
	    				train.moveTrain();
	    			if(loopCt >= 100000) loopCt = 0; //reset;
	    		}
	    	}
		}




		//********************************************************
		// Finish up...
		//********************************************************

        //Call repaint to refresh any color changes
		repaint();

        //Lastly, set the prevPanelSize equal to pSize!
        prevPanelSize = pSize;
    }

	public static Train getTrain(int lAddr) {
		for(int i = 0; i < availableTrains.size(); i++){
			if(availableTrains.get(i).getLocoAddr() == lAddr)
				return availableTrains.get(i);
		}
		return null;

	}

	public static Train getTrain(String lName) {
		for(int i = 0; i < availableTrains.size(); i++){
			if(availableTrains.get(i).getLocoName().equals(lName) == true)
				return availableTrains.get(i);
		}
		return null;

	}

    public void adjSpeed(int newSpeed) {
    	if(speed > newSpeed) {
    		//decreasing Speed...
    		if(newSpeed == 0) stop();
    	} else {
    		//increasing Speed...
    		if(speed == 0) start();
    	}

    	speed = newSpeed;
    	Train train = getTrain(dccAddress);
    	if(train != null) {
    		train.setSpeed(speed);
    	}

    	if(speedLabel != null)
    		speedLabel.setText("Speed: "+speed);
    }
	public void start() {
    	//Is this needed??
    }

    public void stop() {
    	//Is this needed??
    }

    public void processPowerSw(boolean selected) {
    	isPoweredUp = selected;

    	changeDirection(true);
		forButton.setSelected(true);
		revButton.setSelected(false);

    	if(isPoweredUp == false) {
       		comboLocosList.setSelectedIndex(0);
    		detachFromLoco();
    	}
    	comboLocosList.setEnabled(isPoweredUp);

    }

    public void changeDirection(boolean isForward) {
    	if(dccAddress != NOT_SET){
    		getTrain(dccAddress).getLoco().changeDirection(isForward);
    	}
    }

    public void changeLoco(String locoName) {
    	if(locoName.equals("Disattached") == true){
    		detachFromLoco();
    	} else {
    		Train train = getTrain(locoName);
    		if(train != null) {
    			if(attachedTrainID != train.getTrainID()) {
    				attachtoLoco(train.getLocoAddr());
    			}
    		}
    	}
    }

    public void attachtoLoco(int locoAddr) {
    	isAttachedToLoco = true;
    	dccAddress = locoAddr;
    	System.out.println("attachtoLoco locoAddr="+locoAddr);
    	throttle.setEnabled(isAttachedToLoco);
    	revButton.setEnabled(isAttachedToLoco);
    	forButton.setEnabled(isAttachedToLoco);

    	Train train = getTrain(locoAddr);
    	if(train != null) {
    		attachedTrainID = train.getTrainID();
    		train.setSpeed(speed);
    		train.setThrottleID(throttleID);
    	}

    	//TEST CODE!
    	if(dccAddress == 45) {//Test Train #1 = 45
    		int segment = LayoutPanel.layoutList.getSegment("West-Left ML").getSegmentID();
    		Train.Heading heading = Train.Heading.S;
    		getTrain(dccAddress).placeTrain(segment, heading);
    	} else if (dccAddress == 3) {//Test Train #2 = 3
    		int segment = LayoutPanel.layoutList.getSegment("West-Right ML").getSegmentID();
    		Train.Heading heading = Train.Heading.N;
    		getTrain(dccAddress).placeTrain(segment, heading);
    	}else if (dccAddress == 56) {//Test Train #3 = 56
    		int segment = LayoutPanel.layoutList.getSegment("MidUpper-Vertical ML Track").getSegmentID();
    		Train.Heading heading = Train.Heading.N;
    		getTrain(dccAddress).placeTrain(segment, heading);
    	}
    }

    public void detachFromLoco() {

    	if(dccAddress != NOT_SET) {
    		getTrain(dccAddress).setThrottleID(NOT_SET);
    	}
   		isAttachedToLoco = false;
   		dccAddress = NOT_SET;
   		attachedTrainID = NOT_SET;

   		throttle.setEnabled(isAttachedToLoco);
    	revButton.setEnabled(isAttachedToLoco);
    	forButton.setEnabled(isAttachedToLoco);

   		//Reset Speed Throttle and Speed variable!
   		speed = 0;
   		throttle.setValue(speed);
   		if(speedLabel != null)
       		speedLabel.setText("Speed: "+speed);
    }

} //End of ThrottlePanel Class Definition...