// Paned.java
package apps.gui3.tcs;

import java.awt.Dimension;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import jmri.InstanceManager;
import jmri.jmrit.decoderdefn.DecoderIndexFile;
import jmri.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import apps.gui3.dp3.Bundle;
import apps.gui3.dp3.DecoderPro3;


/**
 * The JMRI application for developing the TCS GUI <P>
 *
 * <hr> This file is part of JMRI. <P> JMRI is free software; you can
 * redistribute it and/or modify it under the terms of version 2 of the GNU
 * General Public License as published by the Free Software Foundation. See the
 * "COPYING" file for a copy of this license. <P> JMRI is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *
 * @author	Bob Martin Copyright 2014
 * @version $Revision: 001 $
 */
public class TCS extends apps.gui3.Apps3 {

    private static String menuFile = null;
    private static String toolbarFile = null;
    private static final String applicationName = "TCS - Train Control System";

    public TCS(String[] args) {
        super(applicationName, "TCSConfig.xml", args);
    }

    public synchronized static String getMenuFile() {
        if (menuFile == null) {
            menuFile = "dp3/Gui3Menus.xml";
            File file = new File(menuFile);
            // decide whether name is absolute or relative
            if (!file.isAbsolute()) {
                // must be relative, but we want it to
                // be relative to the preferences directory
                menuFile = FileUtil.getUserFilesPath() + "dp3/Gui3Menus.xml";
                file = new File(menuFile);
            }
            if (!file.exists()) {
                menuFile = "xml/config/parts/jmri/jmrit/roster/swing/RosterFrameMenu.xml";
            } else {
                log.info("Found user created menu structure this will be used instead of the system default");
            }
        }
        return menuFile;
    }

    public synchronized static String getToolbarFile() {
        if (toolbarFile == null) {
            toolbarFile = "dp3/Gui3MainToolBar.xml";
            File file = new File(toolbarFile);
            // decide whether name is absolute or relative
            if (!file.isAbsolute()) {
                // must be relative, but we want it to
                // be relative to the preferences directory
                toolbarFile = FileUtil.getUserFilesPath() + "dp3/Gui3MainToolBar.xml";
                file = new File(toolbarFile);
            }
            if (!file.exists()) {
                toolbarFile = "xml/config/parts/jmri/jmrit/roster/swing/RosterFrameToolBar.xml";
            } else {
                log.info("Found user created toolbar structure this will be used instead of the system default");
            }
        }
        return toolbarFile;
    }

    @Override
    protected void createMainFrame() {
        // create and populate main window

        mainFrame = new TcsWindow(getMenuFile(), getToolbarFile());

        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
		//frame.setResizable(false);
        mainFrame.setMaximumSize(new Dimension(1280, 1024));


        //mainFrame.setMinimumSize(new Dimension(800,600));
        //mainFrame.setMinimumSize(new Dimension(1024,768));
        mainFrame.setMinimumSize(new Dimension(1120,860));
		System.out.println("Main - frame width="+mainFrame.getSize().width+"  height="+mainFrame.getSize().height);
    }

    /**
     * Force our test size. Superclass method set to max size, filling real
     * window.
     * @param d
     */
    @Override
    protected void displayMainFrame(java.awt.Dimension d) {
        jmri.UserPreferencesManager p = InstanceManager.getDefault(jmri.UserPreferencesManager.class);
        if (!p.hasProperties(mainFrame.getWindowFrameRef())) {
            mainFrame.setSize(new java.awt.Dimension(1024, 600));
            mainFrame.setPreferredSize(new java.awt.Dimension(1024, 600));
        }

        mainFrame.setVisible(true);
    }

    /*
     * @Override protected ResourceBundle getActionModelResourceBundle() {
     * return
     * ResourceBundle.getBundle("apps.gui3.dp3.DecoderPro3ActionListBundle"); }
     */

    // Main entry point
    public static void main(String args[]) {
    	preInit(args);
        TCS app = new TCS(args);
        app.start();
    }

    static public void preInit(String[] args) {
        apps.gui3.Apps3.preInit(applicationName);
        setConfigFilename("TCSConfig.xml", args);
    }

    /**
     * Final actions before releasing control of app to user
     */
    @Override
    protected void start() {
        super.start();

        if ((!configOK) || (!configDeferredLoadOK)) {
            if (preferenceFileExists) {
                //if the preference file already exists then we will launch the normal preference window
                AbstractAction prefsAction = new apps.gui3.tabbedpreferences.TabbedPreferencesAction(Bundle.getMessage("MenuItemPreferences"));
                prefsAction.actionPerformed(null);
            }
        }

        Runnable r = new Runnable() {

            @Override
            public void run() {
                try {
                    InstanceManager.getDefault(DecoderIndexFile.class);
                } catch (Exception ex) {
                    log.error("Error in trying to initialize decoder index file " + ex.toString());
                }
            }
        };
        Thread thr = new Thread(r, "initialize decoder index");
        thr.start();
    }

    static Logger log = LoggerFactory.getLogger(TCS.class.getName());
    
}
