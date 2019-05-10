package apps.gui3.tcs;

import jmri.jmrix.loconet.LnTurnout;
import jmri.jmrix.loconet.LocoNetInterface;

public class TcsTurnoutManager extends LnTurnoutManager {

	public TcsTurnoutManager(LocoNetInterface fastcontroller,
			LocoNetInterface throttledcontroller, String prefix,
			boolean mTurnoutNoRetry) {
		super(fastcontroller, throttledcontroller, prefix, mTurnoutNoRetry);
		// TODO Auto-generated constructor stub
	}

	public TcsTurnout createNewTurnout(String systemName, String userName) {
		int addr;
	    try {
	    	addr = Integer.valueOf(systemName.substring(getSystemPrefix().length()+1)).intValue();
	    } catch (NumberFormatException e) {
	    	throw new IllegalArgumentException("Can't convert "+systemName.substring(getSystemPrefix().length()+1)+" to LocoNet turnout address");
	    }
	    TcsTurnout t = new TcsTurnout(getSystemPrefix(), addr, throttledcontroller);
	        						  t.setUserName(userName);
	    return t;
	}
}
