// TcsAction.java
package apps.gui3.tcs;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.WindowConstants;
import jmri.InstanceManager;
import jmri.UserPreferencesManager;
import jmri.jmrit.roster.rostergroup.RosterGroupSelector;
import jmri.util.swing.JmriAbstractAction;
import jmri.util.swing.JmriPanel;
import jmri.util.swing.WindowInterface;

/**
 * AbstractAction for the TCS window so that further windows can be opened
 *
 * @author Kevin Dickerson Copyright (C) 2011
 */
public class TcsAction extends JmriAbstractAction {

    TcsWindow mainFrame;
    boolean allowQuit = true;

    public TcsAction(String s, WindowInterface wi) {
        super(s, wi);
    }

    public TcsAction(String s, Icon i, WindowInterface wi) {
        super(s, i, wi);
    }

    /**
     * Method for opening a new window via the classic JMRI interface
     */
    public TcsAction(String pName, boolean allowQuit) {
        super(pName);
        this.allowQuit = allowQuit;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        mainFrame = new TcsWindow(TCS.getMenuFile(), TCS.getToolbarFile());
        UserPreferencesManager p = InstanceManager.getDefault(jmri.UserPreferencesManager.class);
        if (!p.isWindowPositionSaved(mainFrame.getWindowFrameRef())) {
            mainFrame.setSize(new Dimension(1024, 600));
            mainFrame.setPreferredSize(new Dimension(1024, 600));
        }
        if (wi instanceof RosterGroupSelector) {
            //mainFrame.setSelectedRosterGroup(((RosterGroupSelector) wi).getSelectedRosterGroup());
        }
        mainFrame.setVisible(true);
        mainFrame.setAllowQuit(allowQuit);
        mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    // never invoked, because we overrode actionPerformed above
    @Override
    public JmriPanel makePanel() {
        throw new IllegalArgumentException("Should not be invoked");
    }
}
