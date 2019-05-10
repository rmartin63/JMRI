package apps.gui3.tcs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;

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
import java.util.Vector;

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
//Class RouteDialog Definition - A Java Class to represent the Parent Class for the Route Dialogs
//
//************************************************************************************************
//************************************************************************************************
public abstract class RouteDialog extends JDialog {


	private static RouteDialog _instance = null;  //A singleton class!
/*
	private final JPanel contentPanel = new JPanel();
	//private JFormattedTextField rNameTextField;
	private JTextField   rNameTextField;
	private String       rName;
	private JButton 	 createNewButton;
	private JButton      colorButton;
	private RectDraw     colorBlock = new RectDraw();

	private JLabel       selectLabel;
	private	Vector		 listData;
	private JScrollPane  scrolledPane;
	private JList        segmentList;
*/
	protected JButton      saveButton;
	protected JButton      cancelButton;
	protected Route        currentRoute = null;
	protected static       RouteManager rMgr;

	//private boolean managedForCreate;

/*
	public static RouteDialog getInstance() {
		if(_instance == null) {
			rMgr = RouteManager.getInstance();
			_instance = new RouteDialog();

			//rMgr.setCDialog(_instance);

			_instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//_instance.managedForCreate = false;
		}
		return _instance;
	}
*/
	/**
	 * Create the dialog.
	 */
/*
	protected RouteDialog() {
		setTitle("Create Route");
		setBounds(100, 100, 336, 407);
	}
*/

	abstract void openDialog();
/*
	public void setManagedForCreate(boolean isManaged) {
		managedForCreate = isManaged;
		rMgr.setManagedForCreate(isManaged);
		System.out.println("setManagedForCreate()");
		//Turn off Switch Segments while Dialog is open...
		LayoutPanel lPanel = LayoutPanel.getInstance();
		if(lPanel != null){
			lPanel.setSwitchSegmentVisibility(!isManaged);
		}
	}

	public void addSegmentToList(String _seg) {
		if(_seg != null) {
			//First, check to see if new segment equals the first segment as a sign that we are done!
			boolean isDone = false;
			if(listData.isEmpty() == false) isDone = listData.get(0).equals(_seg);

			// Add this Segment to the list and refresh
			System.out.println("RouteCreateDialog.addSegment  _seg="+_seg);
			TrackSegment seg = LayoutLinkedList.getSegment(_seg);
			if(seg != null){
				if (currentRoute !=null) {
					seg.setRouteSelectedColor(currentRoute.getRouteColor());
	    		} else {
	    			seg.setRouteSelectedColor(Color.orange);
	    		}
			}
			listData.addElement(_seg);
			segmentList.setListData(listData);
			scrolledPane.revalidate();
			scrolledPane.repaint();

			if(isDone) saveButton.setEnabled(true);
		}
	}


	//Class to process Route Name TextField widget...
	class TextHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == rNameTextField) {
				rName = e.getActionCommand();
				System.out.println("TextHandler  rName="+rName);

				createNewButton.setEnabled(true);
				selectLabel.setText("Create: Click Create Button...");

				currentRoute = rMgr.createRoute(rName);
			}
		}
	}


	//Class to process Create Button...
    class CreateAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		if(rName != null) {
    			currentRoute = rMgr.createRoute(rName);
				//saveButton.setEnabled(true);
    			colorButton.setEnabled(true);
    			colorBlock.setBackground(rMgr.getDefaultRouteColor());
				selectLabel.setText("Click on Layout Track Segments to Define Route...");
    		}
    	}
    }

	//Class to process Color Button...
    class ColorAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {

    		//Choose new Route Color...
    		//JOptionPane.showMessageDialog(null, "Select Route Color...");

   	        Color initialBackground = colorBlock.getBackground();
    		if (currentRoute !=null) {
    			initialBackground = currentRoute.getRouteColor();
    		}

    		//JColorChooser chooser = new JColorChooser();
    		//chooser.setPreviewPanel(new JPanel());
   	        Color background = JColorChooser.showDialog(null,
   	        							"Route Color Chooser", initialBackground);
    	    if (background != null) {
    	    	//colorButton.setBackground(background);
    	    	colorBlock.setBackground(background);
    	    	currentRoute.setRouteColor(background);
    	    	//saveButton.setEnabled(true);
    	    }
    	}
    }

*/
	//Class to process Save Button...
    class SaveAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		//Save Route...
    		rMgr.saveRoutes();

    		//Reset currentRoute to null on Exit
    		currentRoute = rMgr.createRoute(null);
    		LayoutPanel.resetSegmentColor();

	        _instance.setVisible(false);
	        //setManagedForCreate(false);  TODO Parent
    	}
    }

	//Class to process Cancel Button...
    class CancelAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
			//Component component = (Component) e.getSource();
    		LayoutPanel.resetSegmentColor();
    		//colorBlock.setBackground(rMgr.getDefaultRouteColor()); TODO Parent
    		System.out.println("CancelAction()");
	        _instance.setVisible(false);
	        //setManagedForCreate(false); TODO Parent
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
