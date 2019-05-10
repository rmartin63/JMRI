package apps.gui3.tcs;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

//************************************************************************************************
//************************************************************************************************
//
//Class DashboardPanel Definition - A Java Object Class to build the Dashboard Outline GUI
//
//************************************************************************************************
//************************************************************************************************

public class DashboardPanel extends JPanel {


	private Dimension  pSize = new Dimension(800, 600); //Panel Size
    private Dimension  prevPanelSize = new Dimension(800, 600);
    private boolean    hasPanelSizeChanged;

	//Layout ArrayList of ThrottlePanels...
	private ArrayList <ThrottlePanel> throttlePanels = new ArrayList <ThrottlePanel> ();
	private final int MAX_NUM_THROTTLES = 4;

	public DashboardPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.darkGray);
        Dimension panelD = new Dimension(800,100);
        setPreferredSize(panelD);

        //this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setLayout(new GridLayout(1, 4, 5, 0));
        //this.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 0));

        for(int i = 0; i < MAX_NUM_THROTTLES; i++) {
        	ThrottlePanel tp = new ThrottlePanel(i);
        	add(tp);
        	tp.setVisible(true);
        	throttlePanels.add(tp);
        }
    }

	//A routine to power down all throttles for times like creating Routes...
	public void powerThrottlesDown() {
		for(int i = 0; i < throttlePanels.size(); i++) {
			throttlePanels.get(i).processPowerSw(false);
		}
	}
} //End of DashboardPanel Class Definition...