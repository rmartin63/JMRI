package apps.gui3.tcs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;

//************************************************************************************************
//************************************************************************************************
//
//Class RouteEditDialog Definition - A Java Class to represent the Edit Route Dialog
//
//************************************************************************************************
//************************************************************************************************
public class RouteEditDialog extends JDialog {

	private static RouteEditDialog _instance = null;  //A singleton class!

	private final JPanel contentPanel = new JPanel();
	private String       rName;

	private JLabel       selectLabel;
	private JComboBox    routeComboBox;

	private JButton      colorButton;
	private RectDraw     colorBlock = new RectDraw();

	private	Vector		 listData;
	private JScrollPane  scrolledPane;
	private JList        segmentList;

	private JButton      deleteButton;
	private JButton      saveButton;
	private JButton      cancelButton;



	private Route currentRoute = null;

	private static RouteManager rMgr;

	private boolean managedForEdit;


	public static RouteEditDialog getInstance() {
		if(_instance == null) {
			rMgr = RouteManager.getInstance();
			_instance = new RouteEditDialog();

			rMgr.setEDialog(_instance);

			_instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); //TODO - Is this desired???
			_instance.managedForEdit = false;
		}
		return _instance;
	}

	/**
	 * Create the dialog.
	 */
	protected RouteEditDialog() {
		setTitle("Edit Route");
		setBounds(100, 100, 368, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			//Route Select Controls
			selectLabel = new JLabel("Select Route to Edit...");
			selectLabel.setHorizontalAlignment(SwingConstants.LEFT);

			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			routeComboBox = new JComboBox(model);
			routeComboBox.addActionListener(new RouteOptionMenuAction());
		}
		{
			//Controls to Select Route Color
			colorButton = new JButton("Select Route Color...");
			colorButton.addActionListener(new ColorAction());
		}
		{
			// Create the data model for this List
			listData = new Vector();
			ListSelectionModel listSelectionModel;
			segmentList = new JList(listData);
			segmentList.setBackground(Color.BLACK);
			segmentList.setForeground(Color.ORANGE);
			segmentList.setFont(new Font("Courier New", Font.PLAIN, 11));
			listSelectionModel = segmentList.getSelectionModel();
			listSelectionModel.addListSelectionListener(new SegmentListSelection());

			scrolledPane = new JScrollPane();

			// Add the segmentList to a scrolling pane
			scrolledPane.setViewportView(segmentList);
		}


		//Now, Create the Group Layout for the Top controls and Segment Scrolling Pane...
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(selectLabel, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
							.addGap(5)
							.addComponent(routeComboBox, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
							.addComponent(colorButton))
							.addGap(5)
							.addComponent(colorBlock, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
				.addComponent(scrolledPane, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createSequentialGroup()
				.addGroup(gl_contentPanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(selectLabel)
						.addComponent(routeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(7)
				.addGroup(gl_contentPanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(colorButton)
						.addComponent(colorBlock))
				.addGap(7)
				.addComponent(scrolledPane, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
		);

		//Now, Create bottom button panel...
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				deleteButton = new JButton("Delete");
				buttonPane.add(deleteButton);
				deleteButton.addActionListener(new DeleteAction());
			}
			{
				saveButton = new JButton("Save");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
				saveButton.addActionListener(new SaveAction());
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new CancelAction());
			}
		}
	}

	public void openDialog() {
		setManagedForEdit(true);
		setVisible(true);
		saveButton.setEnabled(false);

		//First, Clear the list
		routeComboBox.removeAllItems();

		RoutesList rl = rMgr.getRoutes();
		List<Route> list = rl.getRoutes();
		//System.out.println("RouteEditDialog openDialog() list.size()="+list.size());
		for(int i = 0; i < list.size(); i++) {
			//System.out.println("RouteEditDialog openDialog() i="+i+" Routename="+list.get(i).getRoutename());
			routeComboBox.addItem(list.get(i).getRoutename());

			if(i == 0) {
				colorBlock.setBackground(list.get(i).getRouteColor());
			}
		}
		routeComboBox.setEnabled(true);
	}

	public void setManagedForEdit(boolean isManaged) {
		managedForEdit = isManaged;
		rMgr.setManagedForEdit(isManaged);

		//Turn off Switch Segments while Dialog is open...
		LayoutPanel lPanel = LayoutPanel.getInstance();
		if(lPanel != null){
			lPanel.setSwitchSegmentVisibility(!isManaged);
		}
	}


	public void addSegmentToList(String _seg) {
		if(_seg != null) {
			// Add this Segment to the list and refresh
			if(_seg != null ) {
				listData.addElement(_seg);
				segmentList.setListData(listData);
				scrolledPane.revalidate();
				scrolledPane.repaint();

				TrackSegment seg = LayoutLinkedList.getSegment(_seg);
				if(seg != null) {
					if (currentRoute != null) {
						seg.setRouteSelectedColor(currentRoute.getRouteColor());
		    		} else {
		    			if(rMgr != null) seg.setRouteSelectedColor(rMgr.getDefaultRouteColor());
		    		}
					int segId = seg.getSegmentID();
					rMgr.editRouteAddSegment(true, segId, _seg, -1, "");
				}
			}
		}
	}

	private void removeSegmentHighlights() {
		if(currentRoute != null) {
			List<RouteSegmentInfo> segList = currentRoute.getSegments();
			if(segList != null) {
				for(int i = 0; i < segList.size(); i++) {
					String segName = new String(segList.get(i).getSegmentName());
                	TrackSegment ts = LayoutPanel.layoutList.getSegment(segName);
                	if(ts != null) ts.setRouteNotSelectedColor();
				}
			}
		}
	}

	private void processDeleteRoute(String rName) {
		if(rName != null) {
			//System.out.println("RouteEditDialog processDeleteRoute "+rName+"\n");
			rMgr.deleteRoute(rName);
		}
	}

	//Class to process Route Combo box Option Menu...
    class RouteOptionMenuAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		JComboBox cb = (JComboBox)e.getSource();
            String routeName = (String)cb.getSelectedItem();

            //Clear out the list so it can be refilled below...
            listData.removeAllElements();

            //First, Turn off any previous highlights...
    		removeSegmentHighlights();

    		//Reset the Route Color Block...
    		colorBlock.setBackground(rMgr.getDefaultRouteColor());

            //Do work with the route selected - Load the Scrolled List with segments, etc...
			// Add this Segment to the list and refresh
			//System.out.println("RouteOptionMennuAction.Combo Box Action  routeName="+routeName);
			RoutesList rl = rMgr.getRoutes();
			currentRoute = rl.getRoute(routeName);

			if(currentRoute != null) {

				colorBlock.setBackground(currentRoute.getRouteColor());

				List<RouteSegmentInfo> segList = currentRoute.getSegments();
				if(segList != null) {
					for(int i = 0; i < segList.size(); i++) {
						String listRowStr = new String(segList.get(i).getSegmentID()+" "+segList.get(i).getSegmentName());
						listData.addElement(listRowStr);
						segmentList.setListData(listData);

                    	String segName = new String(segList.get(i).getSegmentName());
                    	TrackSegment seg = LayoutPanel.layoutList.getSegment(segName);
                    	if(seg != null) {
                    			seg.setRouteSelectedColor(currentRoute.getRouteColor());
                    			//seg.setRouteSelectedColor(rMgr.getRouteColor1());
                    	} else {
                    		System.out.println("RouteOptionMennuAction.Combo Box Action SEG="+segName+" List NULL!!!");
                    	}
					}
				}
			}
			scrolledPane.revalidate();
			scrolledPane.repaint();
    	}
    }


    //Class to process Segment List Selection Events...
    class SegmentListSelection implements ListSelectionListener {
    	public void valueChanged(ListSelectionEvent e) {
    		//Process Segment List Selection Event...

            ListSelectionModel lsm = (ListSelectionModel)e.getSource();

            //First, Turn off any previous highlights...
    		removeSegmentHighlights();

            //Find out which indexes are selected. - First & Last are used for multiple selected rows...
        	//First = Last if only one row selected...
            int firstIndex = e.getFirstIndex();
            int lastIndex = e.getLastIndex();
            boolean isAdjusting = e.getValueIsAdjusting(); //Not sure what this does...

            if (lsm.isSelectionEmpty()) {
            	System.out.println("SegmentListSelection isSelectionEmpty == true!");
            } else {
                //Find out which indexes are selected. - Min & Max are used for multiple selected rows...
            	//Min = Max if only one row selected...
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();

				List<RouteSegmentInfo> segList = currentRoute.getSegments();
				if(segList != null) {
	                for (int i = minIndex; i <= maxIndex; i++) {
                    	String segName = new String(segList.get(i).getSegmentName());
                    	TrackSegment segmentSelected = LayoutPanel.layoutList.getSegment(segName);
                    	if(segmentSelected != null)
                    		if (lsm.isSelectedIndex(i)) {
                    			segmentSelected.setRouteSelectedColor(rMgr.getRouteColor1());
                    		} else {
                    			segmentSelected.setRouteNotSelectedColor();
                    		}
	                }
				}
            }
        }
    }

	//Class to process Color Button...
    class ColorAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {

    		//Choose new Route Color...
   	        Color initialBackground = colorBlock.getBackground();
    		if (currentRoute !=null) {
    			initialBackground = currentRoute.getRouteColor();
    		}

    		//JColorChooser chooser = new JColorChooser();
    		//chooser.setPreviewPanel(new JPanel());
   	        Color background = JColorChooser.showDialog(null,
   	        							"Route Color Chooser", initialBackground);
    	    if (background != null) {
    	    	colorBlock.setBackground(background);
    	    	currentRoute.setRouteColor(background);
    	    	saveButton.setEnabled(true);
    	    }
    	}
    }


	//Class to process Delete Button...
    class DeleteAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {

    		//Delete Selected Route...
    		if(currentRoute != null) {
    			String rName = currentRoute.getRoutename();
    			if(rName != null) {
    				String dialogMessage = "Deleting Route: "+rName+"\nAre You Sure?";
    				//Popup Are You Sure Dialog...
    				Object[] possibleValues = { "Delete", "Cancel" };
    				int n = JOptionPane.showOptionDialog(null,
    						dialogMessage,
    						"Are You Sure?",
    					    JOptionPane.YES_NO_OPTION,
    					    JOptionPane.WARNING_MESSAGE,
    					    null,
    					    possibleValues,
    					    possibleValues[0]);

    				//System.out.println("RouteEditDialog DeleteAction "+dialogMessage+" n="+n+" JOptionPane.YES_OPTION="+JOptionPane.YES_OPTION+"\n");
    				if(n == JOptionPane.YES_OPTION)
    					processDeleteRoute(rName);
    			}
    		}

    		//Turn off any highlights...
    		removeSegmentHighlights();

    		//Save Route changes...
    		rMgr.saveRoutes();

    		//Reset currentRoute to null on Exit
    		currentRoute = rMgr.createRoute(null);
    		LayoutPanel.resetSegmentColor();

    		//Empty List...
    		routeComboBox.removeAllItems();

	        _instance.setVisible(false);
	        setManagedForEdit(false);
    	}
    }


	//Class to process Save Button...
    class SaveAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {

    		//Turn off any highlights...
    		removeSegmentHighlights();

    		//Save Route changes...
    		rMgr.saveRoutes();

    		//Reset currentRoute to null on Exit
    		currentRoute = rMgr.createRoute(null);
    		LayoutPanel.resetSegmentColor();

    		//Empty List...
    		routeComboBox.removeAllItems();

	        _instance.setVisible(false);
	        setManagedForEdit(false);
    	}
    }

	//Class to process Cancel Button...
    class CancelAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
			//Component component = (Component) e.getSource();

    		//Turn off any highlights...
    		removeSegmentHighlights();

    		//Empty List...
    		routeComboBox.removeAllItems();

	        _instance.setVisible(false);
	        setManagedForEdit(false);
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
