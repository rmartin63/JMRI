package apps.gui3.tcs;
//************************************************************************************************
//************************************************************************************************
//
// TCS - Train Control System - This is the main Frame for the TCS System which is a DCC-based
//       Command & Control & Status Layout Application based on JMRI...
//
//************************************************************************************************
//************************************************************************************************


import apps.AppsBase;
import apps.gui.GuiLafPreferencesManager;
import apps.gui3.Apps3;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.help.SwingHelpUtilities;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import jmri.InstanceManager;
import jmri.UserPreferencesManager;
import jmri.jmrit.roster.Roster;
import jmri.jmrit.roster.swing.RosterGroupsPanel;
import jmri.jmrit.throttle.LargePowerManagerButton;
import jmri.jmrix.ActiveSystemsMenu;
import jmri.jmrix.ConnectionConfig;
import jmri.util.HelpUtil;
import jmri.util.JmriJFrame;
import jmri.util.WindowMenu;
import jmri.util.swing.JToolBarUtil;
import jmri.util.swing.JmriAbstractAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//TCSFrame - Train Control System Main Frame Application
//public class TcsFrame extends JmriJFrame {
public class TcsFrame extends jmri.util.JmriJFrame {

    static Logger log = LoggerFactory.getLogger(TcsFrame.class.getName());
    static ArrayList<TcsFrame> frameInstances = new ArrayList<>();
    protected boolean allowQuit = true;
    protected String baseTitle = "TCS - Train Control System";
    protected JmriAbstractAction newWindowAction;

	//Private Variables
	private JPanel       	contentPane;
	private Dimension    	contentPaneSize;
	private LayoutPanel  	layoutPanel;
	private DashboardPanel	dashboardPanel;
	private JMenuBar 		menuBar;
	private final JPanel          statusBar = new JPanel();
    private JToolBar        toolBar = new JToolBar();
    //private JMenu           deleteRouteMenu;

    private final RouteCreateDialog   routeCreateDialog;
    private final RouteEditDialog     routeEditDialog;
    private final RouteMapperDialog   routeMapperDialog;
    private final TestSwitchDialog    testSwitchDialog;

    int groupSplitPaneLocation = 0;
    RosterGroupsPanel groups;
    JLabel operationsModeProgrammerLabel = new JLabel();

    UserPreferencesManager p;
    java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("apps.AppsBundle");
    ConnectionConfig serModeProCon = null;
    JLabel serviceModeProgrammerLabel = new JLabel();
    JLabel statusField = new JLabel();


	MouseListener ml = new MouseListener(){
	    @Override
        public void mouseClicked(MouseEvent e) {/*System.out.println("mouseClicked");*/}
	    @Override
        public void mousePressed(MouseEvent e) {/*System.out.println("mousePressed");*/}
	    @Override
        public void mouseReleased(MouseEvent e) {/*System.out.println("mouseReleased");*/}
	    @Override
        public void mouseExited(MouseEvent e) {
	    	//System.out.println("\n\nmouseExited\n\n");
	    	//((JMenu)e.getSource()).clearSelection();
	    	//menuBar.setSelected(null);//clearSelection();
	    }
	    @Override
        public void mouseEntered(MouseEvent e) {
	    	//System.out.println("mouseEntered");
	        //((JMenu)e.getSource()).doClick();
	    }
	  };

	//**********************************
	// TCSFrame Constructor
	//**********************************
	public TcsFrame() {
		this("TCS - Train Control System");

		//Force everything in the Frame to size correctly!
		pack();

	} //End of TcsFrame()

    public TcsFrame(String name) {
        //this(name, "xml/config/parts/jmri/jmrit/roster/swing/RosterFrameMenu.xml",
        //        "xml/config/parts/jmri/jmrit/roster/swing/RosterFrameToolBar.xml");
        this(name, null, "xml/config/parts/jmri/jmrit/roster/swing/RosterFrameToolBar.xml");
    }

    public TcsFrame(String name, String menubarFile, String toolbarFile) {
		super(name);  //JmriJFrame()
        buildGUI(menubarFile, toolbarFile);

        //Create Dialogs...
        routeCreateDialog = RouteCreateDialog.getInstance();
        routeEditDialog   = RouteEditDialog.getInstance();
        routeMapperDialog = RouteMapperDialog.getInstance();
        testSwitchDialog = TestSwitchDialog.getInstance();

        this.allowInFrameServlet = false;
        this.setBaseTitle(name);
    }


	protected void buildGUI(String menubarFile, String toolbarFile) {
        configureFrame();

		//Define Menu bar and its widgets...
		defineMenu();
		setJMenuBar(menuBar);

		String value = System.getenv("TCS_AT_HOME");
		System.out.println("\n**************\nTCS_AT_HOME="+value+"\n**************\n");

		if(value != null) {
			if(value.equals("YES") || value.equals("Yes")) {
				//Disable while at work...
		        addMainToolBar(toolbarFile);
		        populateContentPane();

		        addMainStatusBar();
		        additionsToToolBar();
		        frameInstances.add(this);
		        p = InstanceManager.getDefault(UserPreferencesManager.class);
		        statusBar();
			} else {
				setBounds(0, 0, 800, 200);
			}
		} else {
			setBounds(0, 0, 800, 200);
		}

        if (frameInstances.size() > 1) {
            firePropertyChange("closewindow", "setEnabled", true);
            allowQuit(frameInstances.get(0).isAllowQuit());
        } else {
            firePropertyChange("closewindow", "setEnabled", false);
        }
    }

	protected void configureFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 600);

		//Define Content Pane...
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setVisible(true);

		//Set Content Pane's Layout Manager...
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	}

	private void populateContentPane() {

		//Define First Box - JPanel for Layout Panel...
		layoutPanel = LayoutPanel.getInstance();
		contentPane.add(layoutPanel);
		layoutPanel.setVisible(true);

		System.out.println("\n**************\npopulateContentPane...\n**************\n");
    	//JOptionPane.showMessageDialog(null, "displayPanelSize Width= " + displayPanelSize.getWidth() + " height= " + displayPanelSize.getHeight());

		//Add Second Box - JPanel for bottom dashboard controls...
		dashboardPanel = new DashboardPanel();
		//dashboardPanel.setBackground(Color.darkGray);
		contentPane.add(dashboardPanel);
		dashboardPanel.setVisible(true);
    }

    /*=============== Getters and Setters for core properties ===============*/
    /**
     * @return Will closing the window quit JMRI?
     */
    public boolean isAllowQuit() {
        return allowQuit;
    }

    /**
     * @param allowQuit Set state to either close JMRI or just the roster window
     */
    public void setAllowQuit(boolean allowQuit) {
        allowQuit(allowQuit);
    }

    /**
     * @return the baseTitle
     */
    protected String getBaseTitle() {
        return baseTitle;
    }

    /**
     * @param baseTitle the baseTitle to set
     */
    protected final void setBaseTitle(String baseTitle) {
        String title = null;
        if (this.baseTitle == null) {
            title = this.getTitle();
        }
        this.baseTitle = baseTitle;
        if (title != null) {
            this.setTitle(title);
        }
    }

    protected void helpMenu(JMenuBar menuBar, final JFrame frame) {
        try {
            // create menu and standard items
            JMenu helpMenu = HelpUtil.makeHelpMenu("package.apps.gui3.dp3.DecoderPro3", true);
            // tell help to use default browser for external types
            SwingHelpUtilities.setContentViewerUI("jmri.util.ExternalLinkContentViewerUI");
            // use as main help menu
            menuBar.add(helpMenu);
        } catch (Throwable e3) {
            log.error("Unexpected error creating help: " + e3);
        }
    }

    void saveWindowDetails() {
    	/*
        p.setSimplePreferenceState(this.getClass().getName() + ".hideSummary", hideBottomPane);
        p.setSimplePreferenceState(this.getClass().getName() + ".hideGroups", hideGroups);
        p.setSimplePreferenceState(this.getClass().getName() + ".hideRosterImage", hideRosterImage);
        p.setProperty(getWindowFrameRef(), "selectedRosterGroup", groups.getSelectedRosterGroup());
        String selectedProgMode = "edit";
        if (service.isSelected()) {
            selectedProgMode = "service";
        }
        if (ops.isSelected()) {
            selectedProgMode = "ops";
        }
        p.setProperty(getWindowFrameRef(), "selectedProgrammer", selectedProgMode);
        //Method to save table sort, width and column order status
        String rostertableref = getWindowFrameRef() + ":roster";

        XTableColumnModel tcm = rtable.getXTableColumnModel();
        Enumeration<TableColumn> en = tcm.getColumns(false);
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();

            try {
                String columnName = (String) tc.getHeaderValue();
                int index = tcm.getColumnIndex(tc.getIdentifier(), false);
                p.setTableColumnPreferences(rostertableref, columnName, index, tc.getPreferredWidth(), rtable.getModel().getSortingStatus(tc.getModelIndex()), !tcm.isColumnVisible(tc));
            } catch (Exception e) {
                log.warn("unable to store settings for table column " + tc.getHeaderValue(), e);
            }
        }
        if (rosterGroupSplitPane.getDividerLocation() > 2) {
            p.setProperty(getWindowFrameRef(), "rosterGroupPaneDividerLocation", rosterGroupSplitPane.getDividerLocation());
        } else if (groupSplitPaneLocation > 2) {
            p.setProperty(getWindowFrameRef(), "rosterGroupPaneDividerLocation", groupSplitPaneLocation);
        }
        */
    }

	void additionsToToolBar() {
        //This value may return null if the TCS window has been called from a the traditional JMRI menu frame
        if (Apps3.buttonSpace() != null) {
            getToolBar().add(Apps3.buttonSpace());
        }
        getToolBar().add(new LargePowerManagerButton(true));
        //getToolBar().add(modePanel);
    }

    /**
     * For use when the TCS window is called from another JMRI instance, set
     * this to prevent the TCS from shutting down JMRI when the window is
     * closed.
     */

    protected void allowQuit(boolean quitAllowed) {
        if(allowQuit!=quitAllowed){
            newWindowAction = null;
            allowQuit = quitAllowed;
            groups.setNewWindowMenuAction(this.getNewWindowAction());
        }

        firePropertyChange("quit", "setEnabled", allowQuit);
        //if we are not allowing quit, ie opened from JMRI classic
        //then we must at least allow the window to be closed
        if (!allowQuit) {
            firePropertyChange("closewindow", "setEnabled", true);
        }
    }

	//TODO Bob - Is this needed????
	//Matches the first argument in the array against a locally know method
    //@Override
    public void remoteCalls(String[] args) {
        args[0] = args[0].toLowerCase();

    }

    protected void newWindow() {
        this.newWindow(this.getNewWindowAction());
    }

    /**
     * @return the newWindowAction
     */
    protected JmriAbstractAction getNewWindowAction() {
        if(newWindowAction==null)
            newWindowAction = new TcsFrameAction("newWindow", this, allowQuit);
        return newWindowAction;
    }

    /**
     * @param newWindowAction the newWindowAction to set
     */
    protected void setNewWindowAction(JmriAbstractAction newWindowAction) {
        this.newWindowAction = newWindowAction;
    }

    @Override
    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            title = Roster.ALLENTRIES;
        }
        if (this.baseTitle != null) {
            if (!title.equals(this.baseTitle) && !title.startsWith(this.baseTitle)) {
                super.setTitle(this.baseTitle + ": " + title);
            }
        } else {
            super.setTitle(title);
        }
    }


    protected void newWindow(JmriAbstractAction action) {
        action.setWindowInterface(this);
        action.actionPerformed(null);
        firePropertyChange("closewindow", "setEnabled", true);
    }


    public JMenuBar getMenu(){
        return menuBar;
    }


    protected void statusBar() {
    	//*********************************
    	//TODO - Make REAL Status labels!!!
    	//*********************************

        addToStatusBox(serviceModeProgrammerLabel, null);
        addToStatusBox(operationsModeProgrammerLabel, null);
        JLabel programmerStatusLabel = new JLabel("ProgrammerStatus");
        statusField.setText("Idle");
        addToStatusBox(programmerStatusLabel, statusField);
    }

    protected void systemsMenu() {
        ActiveSystemsMenu.addItems(getMenu());
    }

	//***********************************************************//

	@Override
    public void paint(Graphics g) {
	    super.paint(g);
	    //System.out.println("TCSFrame Paint - frame width="+getSize().width+"  height="+getSize().height);
	}


	// TCSFrame Public Get & Set Methods
    @Override
    public JPanel getContentPane() {
        return contentPane;
    }
    public Dimension getContentPaneSize() {
        return contentPaneSize;
    }
    public LayoutPanel getLayoutPanel() {
        return layoutPanel;
    }
    public void setContentPanel(JPanel cPanel) {
        contentPane = cPanel;
    }

	public JComponent getToolBar() {
        return toolBar;
    }


    Action prefsAction;

    public void doPreferences() {
        prefsAction.actionPerformed(null);
    }

    /**
     * Set the location of the window-specific help for the preferences pane.
     * Made a separate method so if can be overridden for application specific
     * preferences help
     */
    protected void setPrefsFrameHelp(JmriJFrame f, String l) {
        f.addHelpMenu(l, true);
    }


	protected void addMainToolBar(String toolBarFile) {
        if (toolBarFile == null) return;

        toolBar = JToolBarUtil.loadToolBar(toolBarFile, this, this);

        // this takes up space at the top until pulled to floating
        add(toolBar, BorderLayout.NORTH);
    }

    protected void addMainStatusBar(){
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));
        statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));

        statusBox = Box.createHorizontalBox();
        statusBox.add(Box.createHorizontalGlue());
        statusBar.add(statusBox);
        add(statusBar, BorderLayout.SOUTH);
    }

    public void addToStatusBox(JLabel title, JLabel value){
        JPanel statusItemPanel = new JPanel();
        statusItemPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        //Set the font size of the status bar text to be 1points less than the default configured, also set as plain
        //int fontSize = apps.GuiLafConfigPane.getFontSize()-1;
        
        //Set the font size of the status bar text to be 1points less than the default configured, also set as plain
        int fontSize = InstanceManager.getDefault(GuiLafPreferencesManager.class).getFontSize() - 1;

        if(title!=null){
            if(fontSize<=4)
                fontSize = title.getFont().getSize()-1;
            title.setFont(title.getFont().deriveFont(Font.PLAIN,fontSize));
            statusItemPanel.add(title);
        }
        if(value!=null){
            if(fontSize<=4)
                fontSize = value.getFont().getSize()-1;
            value.setFont(value.getFont().deriveFont(Font.PLAIN,fontSize));
            statusItemPanel.add(value);
        }
        addToStatusBox(statusItemPanel);
    }

    Box statusBox;
    int statusBoxIndex = 0;	// index to insert extra stuff
    static final int statusStrutWidth = 10;

    public void addToStatusBox(Component comp){
        if(statusBoxIndex!=0){
            statusBox.add(Box.createHorizontalStrut(statusStrutWidth), statusBoxIndex);
            ++statusBoxIndex;
            statusBox.add(new JSeparator(javax.swing.SwingConstants.VERTICAL),statusBoxIndex);
            ++statusBoxIndex;
        }
        statusBox.add(comp, statusBoxIndex);
        ++statusBoxIndex;
    }

    /**
     * Only close frame, etc, dispose() disposes of all
     * cached panes
     */
    @Override
    public void dispose() {
        super.dispose();
    }


    // TCSFrame Method to Define Menu
    private void defineMenu() {
    	//Define Menubar and its widgets...
    	menuBar = new JMenuBar();
    	setJMenuBar(menuBar);

    	JMenu mnFile = new JMenu("File");
    	menuBar.add(mnFile);

    	JMenuItem mntmNew = new JMenuItem("New...");
    	mnFile.add(mntmNew);

    	JMenuItem mntmOpen = new JMenuItem("Open");
    	mnFile.add(mntmOpen);

    	JMenuItem mntmSave = new JMenuItem("Save");
    	mnFile.add(mntmSave);

    	JMenuItem mntmSaveAs = new JMenuItem("Save As...");
    	mnFile.add(mntmSaveAs);

    	JMenuItem mntmPrint = new JMenuItem("Print");
    	mnFile.add(mntmPrint);

    	JMenuItem mntmExit = new JMenuItem("Exit");
    	mnFile.add(mntmExit);
    	mntmExit.addActionListener(new ExitAction());

    	JMenu mnEdit = new JMenu("Edit");
    	menuBar.add(mnEdit);
    	JMenuItem mntmUndo = new JMenuItem("Undo");
    	mnEdit.add(mntmUndo);
    	JMenuItem mntmCut = new JMenuItem("Cut");
    	mnEdit.add(mntmCut);
    	JMenuItem mntmCopy = new JMenuItem("Copy");
    	mnEdit.add(mntmCopy);
    	JMenuItem mntmPaste = new JMenuItem("Paste");
    	mnEdit.add(mntmPaste);

    	prefsAction = new apps.gui3.tabbedpreferences.TabbedPreferencesAction(Bundle.getMessage("MenuItemPreferences"));
    	mnEdit.add(prefsAction);

    	JMenu mnView = new JMenu("View");
    	menuBar.add(mnView);

    	JMenuItem mntmTestSwitch = new JMenuItem("Test Switch...");
    	mnView.add(mntmTestSwitch);
    	mntmTestSwitch.addActionListener(new ViewItemListener());


    	//LocoNet Menu...
    	systemsMenu();

    	JMenu mnInventory = new JMenu("Inventory");
    	menuBar.add(mnInventory);

    	JMenu mnRoutes = new JMenu("Routes");
    	menuBar.add(mnRoutes);

    	JMenuItem mntmCreateRoute = new JMenuItem("Create Route...");
    	mntmCreateRoute.setHorizontalAlignment(SwingConstants.LEFT);
    	mnRoutes.add(mntmCreateRoute);
    	mntmCreateRoute.addActionListener(new RouteItemListener());

    	//JMenuItem mntmRunRoute = new JMenuItem("Run / Stop");
    	//mnRoutes.add(mntmRunRoute);
    	//mntmRunRoute.addActionListener(new RouteItemListener());

    	//JMenuItem mntmSuspendRoute = new JMenuItem("Suspend");
    	//mnRoutes.add(mntmSuspendRoute);
    	//mntmSuspendRoute.addActionListener(new RouteItemListener());

    	//JMenuItem mntmResumeRoute = new JMenuItem("Resume");
    	//mnRoutes.add(mntmResumeRoute);
    	//mntmResumeRoute.addActionListener(new RouteItemListener());

    	JMenuItem mntmEditRoute = new JMenuItem("Edit Route...");
    	mnRoutes.add(mntmEditRoute);
    	mntmEditRoute.addActionListener(new RouteItemListener());

    	JMenuItem mntmRouteMapper = new JMenuItem("Route Mapper...");
    	mnRoutes.add(mntmRouteMapper);
    	mntmRouteMapper.addActionListener(new RouteItemListener());

/*
    	deleteRouteMenu = new JMenu("Delete Route");
    	mnRoutes.add(deleteRouteMenu);

    	JMenuItem deleteAll = deleteRouteMenu.add("Delete ALL Route");
    	deleteAll.addActionListener(new RouteItemListener());
*/
    	JMenu mnSignals = new JMenu("Signals");
    	menuBar.add(mnSignals);

        getMenu().add(new WindowMenu(this));

        helpMenu(menuBar, this);

    	//Add Menu Listeners...
    	mnFile.addMouseListener(ml);
    	mnEdit.addMouseListener(ml);
    	mnView.addMouseListener(ml);
    	mnInventory.addMouseListener(ml);
    	mnRoutes.addMouseListener(ml);
    	//deleteRouteMenu.addMouseListener(ml);
    	mnSignals.addMouseListener(ml);
    	//mnWindow.addMouseListener(ml);

    }// End of defineMenu


    //Class to process Exit Button...
    class ExitAction implements ActionListener {
    	@Override
        public void actionPerformed(ActionEvent e) {
			Component component = (Component) e.getSource();
	        JFrame frame = (JFrame) SwingUtilities.getRoot(component);
	        Window win = findWindow(frame);
	        if(win != null) {
	            closeWindow(new WindowEvent(win, WindowEvent.WINDOW_CLOSING));
	        }
    	}
    }

    //Class to process View Menu Items Button Presses...
    class ViewItemListener implements ActionListener {
		@Override
        public void actionPerformed(ActionEvent e) {
			if("Test Switch...".equals(e.getActionCommand())){
	            //JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());

				//Turn off all Throttles
				//if(dashboardPanel != null)
				//	dashboardPanel.powerThrottlesDown();

				//Manage dialog...
	            testSwitchDialog.openDialog();
	        }
		}
	}



    //Class to process Route Menu Items Button Presses...
	class RouteItemListener implements ActionListener {
		@Override
        public void actionPerformed(ActionEvent e) {
			if("Create Route...".equals(e.getActionCommand())){
	            //JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());

				//Turn off all Throttles
				if(dashboardPanel != null)
					dashboardPanel.powerThrottlesDown();

				//Manage dialog...
	            routeCreateDialog.openDialog();
	        }
	        if("Run / Stop".equals(e.getActionCommand())){
	            JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
	        }
	        //if("Suspend".equals(e.getActionCommand())){
	        //    JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
	        //}
	        //if("Resume".equals(e.getActionCommand())){
	        //    JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
	        //}
	        if("Edit Route...".equals(e.getActionCommand())){
	            //JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());

	        	//Turn off all Throttles
	        	if(dashboardPanel != null)
	        		dashboardPanel.powerThrottlesDown();

	        	//Manage dialog...
	            routeEditDialog.openDialog();
	        }

	        if("Route Mapper...".equals(e.getActionCommand())){
	            //JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());

	        	//Turn off all Throttles
	        	if(dashboardPanel != null)
	        		dashboardPanel.powerThrottlesDown();

	        	//Manage dialog...
				routeMapperDialog.openDialog();

	        }
	        /*
	        if("Delete ALL Route".equals(e.getActionCommand())){
	            //JOptionPane.showMessageDialog(null, "Selected Item: " + e.getActionCommand());
	        	RouteManager rMgr = RouteManager.getInstance();
	        	rMgr.deleteAllRoutes();
	        }
	        */
		}
	}




    public static Window findWindow(Component c) {
        //if (c == null) {
        if (c == null && frameInstances.size() != 0) {
            return frameInstances.get(0).getFrame();
        } else if (c instanceof Window) {
            return (Window) c;
        } else if(c != null) {
            return findWindow(c.getParent());
        } else {
            return null; 
        }
    }


	@Override
    public void windowClosing(WindowEvent e) {
        closeWindow(e);
    }


    //@TODO The disabling of the closewindow menu item doesn't quite work as this in only invoked on the closing window, and not the one that is left
    void closeWindow(WindowEvent e) {
        saveWindowDetails();
        //Save any changes made in the roster entry details
        //Roster.writeRosterFile();
        if (allowQuit && frameInstances.size() == 1) {
            handleQuit(e);
        } else {
            //As we are not the last window open or we are not allowed to quit the application then we will just close the current window
            frameInstances.remove(this);
            super.windowClosing(e);
            if ((frameInstances.size() == 1) && (allowQuit)) {
                frameInstances.get(0).firePropertyChange("closewindow", "setEnabled", false);
            }
            dispose();
        }
    }

	void handleQuit(WindowEvent e) {
		if (e != null && frameInstances.size() == 1) {
			final String rememberWindowClose = this.getClass().getName() + ".closeDP3prompt";
	        if (!p.getSimplePreferenceState(rememberWindowClose)) {
	        	JPanel message = new JPanel();
	            JLabel question = new JLabel(rb.getString("MessageLongCloseWarning"));
	            final JCheckBox remember = new JCheckBox(rb.getString("MessageRememberSetting"));
	            remember.setFont(remember.getFont().deriveFont(10.0F));
	            message.setLayout(new BoxLayout(message, BoxLayout.Y_AXIS));
	            message.add(question);
	            message.add(remember);
	            int result = JOptionPane.showConfirmDialog(null, message, rb.getString("MessageShortCloseWarning"),
	                        								JOptionPane.YES_NO_OPTION);
	            if (remember.isSelected()) {
	            	p.setSimplePreferenceState(rememberWindowClose, true);
	            }
	            if (result == JOptionPane.YES_OPTION) {
	            	AppsBase.handleQuit();
	            }
	        } else {
	        	AppsBase.handleQuit();
	        }
	    } else if (frameInstances.size() >1){
	        final String rememberWindowClose = this.getClass().getName() + ".closeMultipleDP3prompt";
	        if (!p.getSimplePreferenceState(rememberWindowClose)) {
	        	JPanel message = new JPanel();
	            JLabel question = new JLabel(rb.getString("MessageLongMultipleCloseWarning"));
	            final JCheckBox remember = new JCheckBox(rb.getString("MessageRememberSetting"));
	            remember.setFont(remember.getFont().deriveFont(10.0F));
	            message.setLayout(new BoxLayout(message, BoxLayout.Y_AXIS));
	            message.add(question);
	            message.add(remember);
	            int result = JOptionPane.showConfirmDialog(null, message, rb.getString("MessageShortCloseWarning"),
	            		JOptionPane.YES_NO_OPTION);
	            if (remember.isSelected()) {
	            	p.setSimplePreferenceState(rememberWindowClose, true);
	            }
	            if (result == JOptionPane.YES_OPTION) {
	                AppsBase.handleQuit();
	            }
	        } else {
	            AppsBase.handleQuit();
	        }
	    }
	}

}// End of TCSFrame Class Definition