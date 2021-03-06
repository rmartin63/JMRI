package jmri.jmrix.srcp;

import jmri.Turnout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implement turnout manager for SRCP systems.
 * <p>
 * System names are "DTnnn", where D is the user configurable system prefix,
 * nnn is the turnout number without padding.
 *
 * @author	Bob Jacobsen Copyright (C) 2001, 2008
 */
public class SRCPTurnoutManager extends jmri.managers.AbstractTurnoutManager {

    int _bus = 0;
    SRCPBusConnectionMemo _memo = null;

    @Deprecated
    public SRCPTurnoutManager() {

    }

    public SRCPTurnoutManager(SRCPBusConnectionMemo memo, int bus) {
        _bus = bus;
        _memo = memo;
    }

    @Override
    public String getSystemPrefix() {
        return _memo.getSystemPrefix();
    }

    @Override
    public Turnout createNewTurnout(String systemName, String userName) {
        Turnout t;
        int addr = Integer.parseInt(systemName.substring(_memo.getSystemPrefix().length() + 1));
        t = new SRCPTurnout(addr, _memo);
        t.setUserName(userName);

        return t;
    }

    @Override
    public boolean allowMultipleAdditions(String systemName) {
        return true;
    }

    // private final static Logger log = LoggerFactory.getLogger(SRCPTurnout.class);

}
