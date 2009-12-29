package jmri.jmrit.display;

import javax.swing.*;

import jmri.*;
import jmri.implementation.*;

import junit.framework.*;

/**
 * Test the SignalMastIcon.
 *
 * Description:
 * @author			Bob Jacobsen  Copyright 2009
 * @version			$Revision: 1.1 $
 */
public class SignalMastIconTest extends TestCase {

    SignalMastIcon to = null;

	public void testShow() {
        JFrame jf = new JFrame("SignalMast Icon Test");
        jf.getContentPane().setLayout(new java.awt.FlowLayout());

        to = new SignalMastIcon();

        jf.getContentPane().add(new JLabel("Should say Approach: "));
        jf.getContentPane().add(to);
        
        // reset instance manager & create test heads
        jmri.util.JUnitUtil.resetInstanceManager();
        InstanceManager.signalHeadManagerInstance().register(
            new DefaultSignalHead("IH1"){
                protected void updateOutput(){}
            }
        );
        InstanceManager.signalHeadManagerInstance().register(
            new DefaultSignalHead("IH2"){
                protected void updateOutput(){}
            }
        );
        InstanceManager.signalHeadManagerInstance().register(
            new DefaultSignalHead("IH3"){
                protected void updateOutput(){}
            }
        );

	    SignalMast s = InstanceManager.signalMastManagerInstance()
	                        .provideSignalMast("IF$shsm:basic:one-searchlight:IH1");
	    
	    to.setSignalMast(s.getSystemName());
	    
	    s.setAspect("Clear");
	    s.setAspect("Approach");


        jf.pack();
        jf.setVisible(true);

	}

	// from here down is testing infrastructure

	public SignalMastIconTest(String s) {
		super(s);
	}

	// Main entry point
	static public void main(String[] args) {
		String[] testCaseName = {"-noloading", SignalMastIconTest.class.getName()};
		junit.swingui.TestRunner.main(testCaseName);
	}

	// test suite from all defined tests
	public static Test suite() {
		TestSuite suite = new TestSuite(SignalMastIconTest.class);
		return suite;
	}

    // The minimal setup for log4J
    protected void setUp() { apps.tests.Log4JFixture.setUp(); }
    protected void tearDown() { apps.tests.Log4JFixture.tearDown(); }

	// static private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(SignalMastIconTest.class.getName());

}
