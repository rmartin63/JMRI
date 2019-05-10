package apps.gui3.tcs;

import jmri.Turnout;
import jmri.jmrix.loconet.LnTurnout;
import jmri.jmrix.loconet.LocoNetInterface;

public class TcsTurnout extends LnTurnout {

	public TcsTurnout(String prefix, int number, LocoNetInterface controller) {
		super(prefix, number, controller);
		// TODO Auto-generated constructor stub
	}

	// Handle a request to change state by sending a LocoNet command
    public void sendSwChangeCmdToLayout(final int newstate) {
    	forwardCommandChangeToLayout(newstate);
    }
}
