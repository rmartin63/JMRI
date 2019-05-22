package apps.gui3.tcs;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JLabel;
//import apps.gui3.tcs.TcsFrame.ExitAction;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

//************************************************************************************************
//************************************************************************************************
//
//Class TestSwitchDialog Definition - A Java Class to represent the Test Switch Dialog
//
//************************************************************************************************
//************************************************************************************************
public class TestSwitchDialog extends JDialog {


	private static TestSwitchDialog _instance = null;  //A singleton class!

	//private final JPanel contentPanel = new JPanel();
	private static JPanel lPanel  = null;

	public static Graphics2D g2d;

	public static Dimension pSize = null;
	private static Dimension  prevPanelSize = new Dimension(800, 600);
    private static boolean    hasPanelSizeChanged;

	public static boolean layoutConnected = false;

	public static ArrayList <SwitchSegment> switchList = new ArrayList <SwitchSegment>();

	public static LocoNetAPI lnAPI = null;
	public static LocoNetAPI_STUB lnAPI_S = null;

	public static int refreshedSwId;

	final public static Color defaultLayoutDrawColor = new Color(156,215,245); //Default LayoutPanel Draw Color

	private JButton      okButton;

	private final static int TOP_LEFT_X = 10;
	private final static int TOP_LEFT_Y = 270;
	private final static int DIALOG_HEIGHT = 300;
	private final static int DIALOG_WIDTH = 250;
	private final static int LAYOUT_PANEL_HEIGHT = DIALOG_HEIGHT - 80;

	private Point mousePoint = null;
	private Point lastMousePoint = null;

	MouseListener ml = new MouseListener(){
		public void mouseClicked(MouseEvent e) {
			mousePoint = e.getPoint();

			//Adjust Y - 30 to account for top dialog frame offset
			mousePoint.y = mousePoint.y - 30;

			System.out.println("\n*************************\nmouseClicked  Point="+e.getPoint()+" mousePoint="+mousePoint+" lastMousePoint="+lastMousePoint+
					" swListSize="+switchList.size());


			if(mousePoint != lastMousePoint) {
				if (isMouseClickOnSwitch(mousePoint) == true){
					//Check for click on a switch
					lastMousePoint = mousePoint;
				}
			} //if(mousePoint != lastMousePoint)
		}
		public void mousePressed(MouseEvent e) {
			//mousePoint = e.getPoint();
	    	//System.out.println("LayoutPanel mousePressed - x="+mousePoint.x+" y="+mousePoint.y);
	    }
	    public void mouseReleased(MouseEvent e) {/*System.out.println("mouseReleased");*/}
	    public void mouseExited(MouseEvent e) {/*System.out.println("\n\nmouseExited\n\n");*/}
	    public void mouseEntered(MouseEvent e) {/*System.out.println("mouseEntered");*/}
	};


	public static TestSwitchDialog getInstance() {
		if(_instance == null) {
			_instance = new TestSwitchDialog();
			_instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
		return _instance;
	}

	/**
	 * Create the dialog.
	 */

	protected TestSwitchDialog() {
		setTitle("Test Switch Dialog");
		setBounds(TOP_LEFT_X, TOP_LEFT_Y, DIALOG_WIDTH, DIALOG_HEIGHT);
		setLayout(new FlowLayout());

		String value = System.getenv("TCS_LAYOUT_CONNECTED");
		if(value != null)
		    if(value.equals("YES") || value.equals("Yes"))
		        layoutConnected = true;

		if(lPanel == null) {
			lPanel = new JPanel() {


			    public void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        TestSwitchDialog.drawLayout(g);
			    }
			};
			lPanel.setLayout(null);
		}

		createTestLayoutPanel();

		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		add(okButton);
		okButton.addActionListener(new OkAction());
	}


	public void openDialog() {
		setVisible(true);
		System.out.println("openDialog()");
		okButton.setEnabled(true);
	}

	private void createTestLayoutPanel() {
		if(lPanel != null) {
			int red   =  9;
			int green = 45;
			int blue  = 90;
			Color myBlue = new Color(red,green,blue);
			lPanel.setBackground(myBlue);
			Dimension panelD = new Dimension(DIALOG_WIDTH, LAYOUT_PANEL_HEIGHT);
			lPanel.setPreferredSize(panelD);

			add(lPanel);

			//MouseListener for finding points for debug and switch placement
			addMouseListener(ml);
		}
	}

	private static void drawLayout(Graphics g) {
		g2d = (Graphics2D) g;

        //Set Color and Stroke for drawing Bench Work...
		setLayoutColorAndStroke();

		if(pSize == null)
			pSize = new Dimension(DIALOG_WIDTH, LAYOUT_PANEL_HEIGHT); //Panel Size

		//CHANGE #1...
		if(layoutConnected == true) {
			//For use with the Layout...
			if(lnAPI == null) {
				lnAPI = LocoNetAPI.getInstance();
			}
		} else {
			//For Testing without the Layout...
			if(lnAPI_S == null) {
				lnAPI_S = LocoNetAPI_STUB.getInstance();
			}
		}

        pSize = lPanel.getSize();

       	//System.out.println("TestSwitchPanel psize="+pSize+" prevPanelSize="+prevPanelSize);
       	if((pSize.width != prevPanelSize.width) || (pSize.height != prevPanelSize.height)) {
       		hasPanelSizeChanged = true;
       	} else {
       		hasPanelSizeChanged = false;
       	}

		//********************************************************
		// Do the Drawing!
		//********************************************************

    	//TODO!!!


       	/*if(hasPanelSizeChanged == true) {
       		//Unmanage Switch Frog Buttons on a re-size!
       		System.out.println("TestSwitchPanel hasPanelSizeChanged == true");
       		unmanageFrogButtons();
       	} else */ {
	       	int topMLLeftX = 30;
			int topMLLeftY = 55;

			int topMLFrogX = topMLLeftX+50;
			int topMLFrogY = topMLLeftY;

			int topMLRightX = DIALOG_WIDTH-30;
			int topMLRightY = topMLLeftY;

			int bottomMLLeftX = topMLLeftX;
			int bottomMLLeftY = topMLLeftY+80;

			int bottomMLRightX = topMLRightX;
			int bottomMLRightY = bottomMLLeftY;

			int bottomMLFrogX = bottomMLRightX-50;
			int bottomMLFrogY = bottomMLLeftY;


	        //Draw Test Components...

			//Draw Top ML...
			g2d.drawLine(topMLLeftX, topMLLeftY, topMLRightX, topMLRightY);
			g2d.drawRect (topMLFrogX, topMLFrogY, 3, 3);

			//Draw Bottom ML
			g2d.drawLine(bottomMLLeftX, bottomMLLeftY, bottomMLRightX, bottomMLRightY);
			//g2d.drawRect (bottomMLFrogX, bottomMLFrogY, 3, 3);

			//Draw Diagonal Spur...
			g2d.drawLine(topMLFrogX, topMLFrogY, bottomMLFrogX, bottomMLFrogY);

			//Create Top, Left Switch Segment...
	      	if(getSwitch("Upper, Left - Test SW 81") == null) {
		        boolean thrown = refreshSwitchList("Upper, Left - Test SW 81", false);
		        int mlY = topMLFrogY;
		        int mlX = topMLFrogX+30;
		        int slY = mlY+20;
		        int slX = LayoutPanel.getXOnLine(topMLFrogX, topMLFrogY, bottomMLFrogX, bottomMLFrogY, slY);
		        //pt1 = Frog pt2 = ML pt3 = Spur
		        int mlSeg = 500; //Bogus high test number!
		        int slSeg = 501; //Bogus high test number!
				SwitchSegment sw1 = new SwitchSegment("Upper, Left - Test SW 81", refreshedSwId, mlSeg, slSeg, 81, topMLFrogX, topMLFrogY, mlX, mlY, slX, slY, thrown);
				sw1.addFrogPanel(lPanel);
				switchList.add(sw1);
	      	} else getSwitch("Upper, Left - Test SW 81").draw(g2d);


			//Create Bottom, Right Switch Segment...
	      	if(getSwitch("Lower, Right - Test SW 82") == null) {
		        boolean thrown = refreshSwitchList("Lower, Right - Test SW 82", false);
		        int mlY = bottomMLFrogY;
		        int mlX = bottomMLFrogX-30;
		        int slY = mlY-20;
		        int slX = LayoutPanel.getXOnLine(topMLFrogX, topMLFrogY, bottomMLFrogX, bottomMLFrogY, slY);
		        //pt1 = Frog pt2 = ML pt3 = Spur
		        int mlSeg = 502; //Bogus high test number!
		        int slSeg = 501; //Bogus high test number!
				SwitchSegment sw1 = new SwitchSegment("Lower, Right - Test SW 82", refreshedSwId, mlSeg, slSeg, 82, bottomMLFrogX, bottomMLFrogY, mlX, mlY, slX, slY, thrown);
				sw1.addFrogPanel(lPanel);
				switchList.add(sw1);
	      	} else getSwitch("Lower, Right - Test SW 82").draw(g2d);



       	}

        //Call repaint to refresh any color changes
        lPanel.repaint();

        //Lastly, set the prevPanelSize equal to pSize!
        prevPanelSize = pSize;

    }

	public static SwitchSegment getSwitch(String pName){
		SwitchSegment retValue = null;
		for(int i = 0; i < switchList.size(); i++){
    		if(switchList.get(i).getSwitchName().equals(pName) == true) {
    			retValue = switchList.get(i);
    			//System.out.println("getSwitch pName="+pName+" retValue="+retValue);

    		}
		}
		return (retValue);
	}

	public static SwitchSegment getSwitch(int dccAddr){
		SwitchSegment retValue = null;
		for(int i = 0; i < switchList.size(); i++){
    		if(switchList.get(i).getSwitchDccAddr()== dccAddr) {
    			retValue = switchList.get(i);
    		}
		}
		return (retValue);
	}

	public static void setLayoutColorAndStroke() {
        g2d.setColor(defaultLayoutDrawColor);
        g2d.setStroke(new BasicStroke(5));
	}

	private static void unmanageFrogButtons() {
		//Loop through all switch objects and unmanage and delete & reset SwitchCount to zero...

		int listSize = switchList.size();
		//System.out.println("unmanageFrogButtons listSize="+listSize);
		for (int i = 0; i < listSize; i++) {
			//switchList.get(i).unManage();
			switchList.get(i).unManage(lPanel);
			switchList.get(i).removeFrogInfoRecord();
		}

		//remove all list Elements.class..
		switchList.clear();
		SwitchSegment.resetSwitchCount();
	}

	public static boolean refreshSwitchList(String switchName, boolean defaultIsThrown) {
		boolean thrown = defaultIsThrown;
		refreshedSwId = -1;

		for (int i = 0; i < switchList.size(); i++) {
			if (switchList.get(i).getSwitchName().equals(switchName)){
				//System.out.println("refreshSwitchList switchName="+switchName);
				thrown = switchList.get(i).getIsThrown(); //Save State for new Switch Object!
				refreshedSwId = switchList.get(i).getSwitchID();
				int dccAddr = switchList.get(i).getSwitchDccAddr();
				Point frogPt = switchList.get(i).getFrogPoint();
				break;
			}
		}
		return thrown;
	}

	private boolean isMouseClickOnSwitch(Point pt) {
		boolean found = false;

		for(int i = 0; i < switchList.size(); i++) {
			if (switchList.get(i).isSwitchFound(pt)){
				found = true;
				break;
			}
		}
		System.out.println("isMouseClickOnSwitch pt="+pt+" swlist.size="+switchList.size()+" found="+found);
		return found;
	}

	//Class to process OK Button...
    class OkAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		System.out.println("OkAction()");
	        _instance.setVisible(false);
    	}
    }

    private static class RectDraw extends JPanel {
    	@Override
    	protected void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    //g.drawRect(230,80,10,10);
		    //g.setColor(Color.RED);
		    //g.fillRect(230,80,10,10);
    	}
/*
    	@Override
    	public Dimension getPreferredSize() {
    		final int PREF_WIDTH = 35;
    		final int PREF_HEIGHT = 5;
    	    return new Dimension(PREF_WIDTH, PREF_HEIGHT); // appropriate constants
    	}
    	*/
    }
}
