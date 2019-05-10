package apps.gui3.tcs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
//Class RouteMapperDialog Definition - A Java Class to represent the Route Mapper Dialog
//
//************************************************************************************************
//************************************************************************************************
public class RouteMapperDialog extends JDialog {

	private static RouteMapperDialog _instance = null;  //A singleton class!

	private final JPanel contentPanel = new JPanel();
	//private JFormattedTextField rNameTextField;
	private JTextField   rNameTextField;
	private String       rName;
	private JButton 	 createNewButton;
	private JButton      saveButton;
	private JButton      cancelButton;
	private JLabel       statusLabel;
	private	Vector		 listData;
	private JScrollPane  scrolledPane;
	private JList        segmentList;

	private Route currentRoute = null;

	private static RouteManager rMgr;

	private boolean managedForCreate;


	public static RouteMapperDialog getInstance() {
		if(_instance == null) {
			rMgr = RouteManager.getInstance();
			_instance = new RouteMapperDialog();

			//rMgr.setCDialog(_instance);

			_instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); //TODO - Is this desired???
			_instance.managedForCreate = false;
		}
		return _instance;
	}

	/**
	 * Create the dialog.
	 */
	protected RouteMapperDialog() {
		setTitle("Route Mapper");
		setBounds(100, 100, 336, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			/*
			MaskFormatter formatter = null;

	        try {
	            //# - Any valid number, uses Character.isDigit.
	            //' - Escape character, used to escape any of the special formatting characters.
	            //U - Any character (Character.isLetter). All lowercase letters are mapped to upper case.
	            //L - Any character (Character.isLetter). All upper case letters are mapped to lower case.
	            //A - Any character or number (Character.isLetter or Character.isDigit)
	            //? - Any character (Character.isLetter).
	            //* - Anything.
	            //H - Any hex character (0-9, a-f or A-F).
	            formatter = new MaskFormatter("AAAAAAAAAA");
	        } catch (java.text.ParseException ex) {
	        }
	        */
	        //rNameTextField = new JFormattedTextField(formatter);
			rNameTextField = new JTextField();
			rNameTextField.setColumns(8);
			//rNameTextField.addActionListener(new TextHandler());
		}
		{
			createNewButton = new JButton("Create Route");
			//createNewButton.addActionListener(new CreateAction());
		}
		{
			statusLabel = new JLabel("Enter Route Name to Create & Press <Enter>");
			statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		}

		{
			// Create the data model for this List
			listData = new Vector();
			segmentList = new JList(listData);
			segmentList.setBackground(Color.BLACK);
			segmentList.setForeground(Color.ORANGE);
			segmentList.setFont(new Font("Courier New", Font.PLAIN, 11));
			//segmentList.addListSelectionListener(this);

			scrolledPane = new JScrollPane();
			scrolledPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

			// Add the segmentList to a scrolling pane
			scrolledPane.setViewportView(segmentList);
		}

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		final int preferredWidthOfScrolledWin = 240;//309;
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrolledPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, preferredWidthOfScrolledWin, Short.MAX_VALUE)
						.addComponent(statusLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, preferredWidthOfScrolledWin, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addComponent(rNameTextField, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(createNewButton)))
					.addGap(1))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(1)
							.addComponent(rNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(createNewButton))
					.addGap(5)
					.addComponent(statusLabel)
					.addGap(7)
					.addComponent(scrolledPane, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
		);

		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
		//setManagedForCreate(true);
		setVisible(true);

		createNewButton.setEnabled(false);
		saveButton.setEnabled(false);
		rNameTextField.setText("");
		listData.removeAllElements();
	}

	/*
	public void setManagedForCreate(boolean isManaged) {
		managedForCreate = isManaged;
		rMgr.setManagedForCreate(isManaged);

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
			//System.out.println("RouteCreateDialog.addSegment  _seg="+_seg);
			TrackSegment seg = LayoutLinkedList.getSegment(_seg);
			if(seg != null){
				seg.setRouteSelectedColor(Color.orange);
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
				statusLabel.setText("Create: Click Create Button...");

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
				statusLabel.setText("Click on Layout Track Segments to Define Route...");
    		}
    	}
    }

*/
	//Class to process Save Button...
    class SaveAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		/*
    		//Save Route...
    		rMgr.saveRoutes();

    		//Reset currentRoute to null on Exit
    		currentRoute = rMgr.createRoute(null);
    		LayoutPanel.resetSegmentColor();
            */
	        _instance.setVisible(false);
	        //setManagedForCreate(false);
    	}
    }

	//Class to process Cancel Button...
    class CancelAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
			//Component component = (Component) e.getSource();
   			//LayoutPanel.resetSegmentColor();
	        _instance.setVisible(false);
	        //setManagedForCreate(false);
    	}
    }
}
