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
//Class RouteCreateDialog Definition - A Java Class to represent the Create / Edit Route Dialog
//
//************************************************************************************************
//************************************************************************************************
//public class RouteCreateDialog extends JDialog {
public class RouteCreateDialog extends RouteDialog {
	private static RouteCreateDialog _instance = null;  //A singleton class!

	private final JPanel contentPanel = new JPanel();
	//private JFormattedTextField rNameTextField;
	private JTextField   rNameTextField;
	private String       rName;
	private JButton 	 createNewButton;
	private JButton      colorButton;
	private RectDraw     colorBlock = new RectDraw();
	private JButton      saveButton;
	private JButton      cancelButton;
	private JLabel       selectLabel;
	private	Vector		 listData;
	private JScrollPane  scrolledPane;
	private JList        segmentList;

	private Route currentRoute = null;

	private static RouteManager rMgr;

	private boolean managedForCreate;


	public static RouteCreateDialog getInstance() {
		if(_instance == null) {
			rMgr = RouteManager.getInstance();
			_instance = new RouteCreateDialog();

			rMgr.setCDialog(_instance);

			_instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			_instance.managedForCreate = false;
		}
		return _instance;
	}

	/**
	 * Create the dialog.
	 */
	protected RouteCreateDialog() {
		setTitle("Create Route");
		setBounds(100, 100, 336, 407);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			selectLabel = new JLabel("Enter Route Name to Create & Press <Enter>");
			selectLabel.setHorizontalAlignment(SwingConstants.LEFT);
		}
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
			rNameTextField.addActionListener(new TextHandler());
		}
		{
			createNewButton = new JButton("Create Route");
			createNewButton.addActionListener(new CreateAction());
		}
		{
			//Controls to Select Route Color
			colorButton = new JButton("Select Route Color...");
			colorButton.addActionListener(new ColorAction());
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
		gl_contentPanel.setAutoCreateGaps(true);
		gl_contentPanel.setAutoCreateContainerGaps(true);

		final int preferredWidthOfScrolledWin = 240;//309;
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(selectLabel, GroupLayout.DEFAULT_SIZE, preferredWidthOfScrolledWin, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(rNameTextField, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
					.addComponent(createNewButton))

				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(colorButton))
						.addComponent(colorBlock, GroupLayout.DEFAULT_SIZE, preferredWidthOfScrolledWin, Short.MAX_VALUE))
				.addComponent(scrolledPane, GroupLayout.DEFAULT_SIZE, preferredWidthOfScrolledWin, Short.MAX_VALUE)
		);

		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createSequentialGroup()
				.addComponent(selectLabel)
				.addGroup(gl_contentPanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(rNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(createNewButton))
				.addGroup(gl_contentPanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(colorButton)
						.addComponent(colorBlock))
				.addComponent(scrolledPane, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
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
		setManagedForCreate(true);
		setVisible(true);
		System.out.println("openDialog()");
		createNewButton.setEnabled(false);
		colorButton.setEnabled(false);
		colorBlock.setBackground(rMgr.getDefaultRouteColor());
		saveButton.setEnabled(false);
		rNameTextField.setText("");
		listData.removeAllElements();
	}

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


	//Class to process Save Button...
    class SaveAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		//Save Route...
    		rMgr.saveRoutes();

    		//Reset currentRoute to null on Exit
    		currentRoute = rMgr.createRoute(null);
    		LayoutPanel.resetSegmentColor();

	        _instance.setVisible(false);
	        setManagedForCreate(false);
    	}
    }

	//Class to process Cancel Button...
    class CancelAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
			//Component component = (Component) e.getSource();
    		LayoutPanel.resetSegmentColor();
    		colorBlock.setBackground(rMgr.getDefaultRouteColor());
    		System.out.println("CancelAction()");
	        _instance.setVisible(false);
	        setManagedForCreate(false);
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
