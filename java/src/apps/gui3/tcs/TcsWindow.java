// TcsWindow.java
package apps.gui3.tcs;

import jmri.Application;

/**
 * Standalone TCS Window.
 * (Modeled after DecoderPro3)
 */
//public class TcsWindow extends RosterFrame {
public class TcsWindow extends TcsFrame {

    /**
     * Loads TCS with the default set of menus and toolbars
     */
    public TcsWindow() {
        super(Application.getApplicationName());
    }

    /**
     * Loads TCS with specific menu and toolbar files
     */
    public TcsWindow(String menuFile, String toolbarFile) {
    	super(Application.getApplicationName(),
                menuFile,
                toolbarFile);
        this.setNewWindowAction(new TcsAction("newWindow", this));
    }

    // for some reason, the super implementation does not get called automatically
    @Override
    public void remoteCalls(String[] args) {
        super.remoteCalls(args);
    }

}