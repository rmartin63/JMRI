package apps.gui3.tcs;

import java.util.ArrayList;

//import com.sun.corba.se.impl.ior.ByteBuffer;

import jmri.Turnout;
import jmri.jmrix.loconet.LnConstants;
import jmri.jmrix.loconet.LocoNetListener;
import jmri.jmrix.loconet.LocoNetMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LocoNetAPI_STUB implements LocoNetListener {

	private static LocoNetAPI_STUB       _instance = null;  //A singleton class!

	public static ArrayList <SwitchInfo> switchList = new ArrayList <>();
	//private static TcsTurnoutManager     lnTurnoutMgr = null;

	//Contains instances of LnPacketizer & PR3Adapter
	//public static LnPacketizer lnController = null; //FUTURE!!
	//public static LocoNetInterface lnController = null;
	//public static PR3Adapter layoutPr3API = null;

	public static final int METERINTERVAL = 100;  // msec wait before closed

	private boolean         mTurnoutNoRetry;

	//NOTE: Could have 2 LocoNetAPI instances if have 2 PR3 units
	//      one for RR traffic and a 2nd for second LOCONET for Signaling
	//
	//For multiple LOCONET's, would need to get PR3Adaptor and LnPacketizer working!!
	//
	//The LnTrafficController logic below is from bob J (JMRI users) and he said this logic
	//is the old way and defaults to a single LN network.

	protected LocoNetAPI_STUB() {
		//instantiate and initialize...
		//createPortAdaptor();

		//createController();

		createTurnoutManager();
	}

	public static LocoNetAPI_STUB getInstance() {
		if(_instance == null) {
			_instance = new LocoNetAPI_STUB();
		}
		return _instance;
	}

	//Future - Need this to connect to multiple PR3/Loconets
    private void createPortAdaptor() {
    	/*
		if(layoutPr3API == null) {
			layoutPr3API = new PR3Adapter();
			try{
				layoutPr3API.connect();
				layoutPr3API.configure();
        	} catch (Exception ex) {
        		System.out.println("LocoNetAPI() PR3 Connect & Configure Exception="+ex.getLocalizedMessage());
            	//log.error(ex.getLocalizedMessage(), ex);
            	//Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
            	//parent.setCursor(normalCursor);
            	//JOptionPane.showMessageDialog(null, "An error occurred while trying to connect to " + ((jmri.jmrix.AbstractConnectionConfig)connect).getConnectionName() + ", press the back button and check the connection details","Error Opening Connection" , JOptionPane.ERROR_MESSAGE);
            	//return;
        	}
		}
	*/
    }

    @SuppressWarnings("deprecation")
	private void createController() {
    /*
		if(lnController == null) {
			//lnController = new LnPacketizer();  //FUTURE - Needed for multiple PR3/Loconets
			//System.out.println("LocoNetAPI() Creating LnTrafficController...");
			lnController = LnTrafficController.instance();
			lnController.addLocoNetListener(~0, this); //~0 = LocoNetInterface.ALL

			//FUTURE...
			//if(layoutPr3API.status() == true)
			//	lnController.connectPort(layoutPr3API);
		}
	*/
    }

    private void createTurnoutManager() {
    /*
		if(lnTurnoutMgr == null) {
			mTurnoutNoRetry = false;
			lnTurnoutMgr = new TcsTurnoutManager(lnController, lnController, "L", mTurnoutNoRetry);
			jmri.InstanceManager.setTurnoutManager(lnTurnoutMgr);

			//TODO - IS THIS NEEDED???
			// outwait any pending delayed sends
			try { synchronized (this) { this.wait(LocoNetAPI.METERINTERVAL+25); }}
			catch (InterruptedException e) {}
		}
	*/
    }

	public TcsTurnout addSwitch(int switchID, int dccAddr, String name, boolean thrown) {
		TcsTurnout retVal = null;
		SwitchInfo sw = new SwitchInfo(switchID, dccAddr, name,thrown);
		switchList.add(sw);

   		//NOTE from LnTurnoutManager.java - System names are "LTnnn", where nnn is the turnout number without padding.
   		//String sysName = "LT"+dccAddr;
   		//sw.tcsTurnout = lnTurnoutMgr.createNewTurnout(sysName, name);

   		//Establish the state of the the layout switch...
   		establishSwitchState(dccAddr, thrown);

   		return retVal;
    }

    public boolean sendDCCSwitchChange(int dccAddress) {
    	boolean ret = false; //Boolean return true if DCC Operation is successful.
    	int newState = Turnout.UNKNOWN;
    	SwitchInfo sw = getSwitchInfo(dccAddress);
    	TcsTurnout tcsTurnout = null;
    	if(sw != null) tcsTurnout = sw.getTcsTurnout();

    	if(tcsTurnout != null) {
    		boolean isThrown = getSwitchIsThrown(dccAddress);

   			//NOTE: Only testing Switch 74, which is the closest yard switch to my desk
    		if(isThrown) {
    			newState = Turnout.THROWN;
    			System.out.println("sendSwChangeCmdToLayout() Called for dccAddr="+dccAddress+"  newState=THROWN  "+newState);
    			tcsTurnout.sendSwChangeCmdToLayout(newState);
    		}
    		else {
    			newState = Turnout.CLOSED;
    			System.out.println("sendSwChangeCmdToLayout() Called for dccAddr="+dccAddress+"  newState=CLOSED  "+newState);
    			tcsTurnout.sendSwChangeCmdToLayout(newState);
    		}

    		ret = (tcsTurnout.isConsistentState() && (tcsTurnout.getCommandedState() == newState));
    		//System.out.println("sendDCCSwitchChange() END  tcsTurnout.getCommandedState()="+
    				//tcsTurnout.getCommandedState()+" newState="+newState+" ret="+ret+" isConsistentState()="+tcsTurnout.isConsistentState());
   		}
    	return ret;
    }


	private void establishSwitchState(int dccAddress, boolean isThrown) {
		SwitchInfo sw = getSwitchInfo(dccAddress);
    	TcsTurnout tcsTurnout = sw.getTcsTurnout();

    	if(!isThrown) {
    		//Just send out a CLOSED state to initially set switch to a known state:
    		if(tcsTurnout != null) tcsTurnout.sendSwChangeCmdToLayout(Turnout.CLOSED);
    	} else {
    		//Just send out a THROWN state to initially set switch to a known state:
    		if(tcsTurnout != null) tcsTurnout.sendSwChangeCmdToLayout(Turnout.THROWN);
    	}

	    //TODO - Test calling at startup and see if need to call when state unknown or even periodically
   		//Send out a request to try to establish the current state of the switch on the layout
   		//sendOpcSwStateMessage(dccAddress);
	}

    //Send a single OPC_SW_STATE message for this turnout to request the current state.
    void sendOpcSwStateMessage(int dccAddress) {
    /*
         LocoNetMessage l = new LocoNetMessage(4);
         l.setOpCode(LnConstants.OPC_SW_STATE);

         // compute address fields
         int hiadr = (dccAddress-1)/128;
         int loadr = (dccAddress-1)-hiadr*128;

         // set closed (note that this can't handle both!  Not sure how to
         // say that in LocoNet.
         //if ((state & CLOSED) != 0) {
             //hiadr |= 0x20;
             // thrown exception if also THROWN
             //if ((state & THROWN) != 0)
              //   log.error("LocoNet turnout logic can't handle both THROWN and CLOSED yet");
         //}

         // load On/Off
        //if (on)
         //   hiadr |= 0x10;
        //else
         //   hiadr &= 0xEF;

         // store and send
         l.setElement(1,loadr);
         l.setElement(2,hiadr);

         //TEST CODE!!!
         int sw1 = l.getElement(1);
         int sw2 = l.getElement(2);
         boolean areEqual = (address(sw1, sw2) == dccAddress);

         int opCode = l.getOpCode();
         byte[] opBytes = convertIntToBytes(opCode);
         String opStr = convertBytesToString(opBytes);
         byte[] sw1Bytes = convertIntToBytes(sw1);
         String sw1Str = convertBytesToString(sw1Bytes);
         byte[] sw2Bytes = convertIntToBytes(sw2);
         String sw2Str = convertBytesToString(sw2Bytes);
         System.out.println("sendOpcSwStateMessage() Calling sendLocoNetMessage... dccAddress("+
         dccAddress+")? T/F="+areEqual+" opCode="+opStr+"  sw1="+sw1Str+"  sw2="+sw2Str);

         lnController.sendLocoNetMessage(l);
    */
    }

    //CB Listener Function for LocoNetListener - Called when events come in from the LOCONET...
    @Override
    public void message(LocoNetMessage l) {
        // parse message type
        switch (l.getOpCode()) {
        case LnConstants.OPC_SW_REQ: {               /* page 9 of Loconet PE */
            int sw1 = l.getElement(1);
            int sw2 = l.getElement(2);
            int dccAddress = address(sw1, sw2);
            SwitchInfo sw = getSwitchInfo(dccAddress);
            if (sw != null) {
                if (log.isDebugEnabled()) log.debug("SW_REQ received with valid address");
                //sort out states
                int state;
                if ((sw2 & LnConstants.OPC_SW_REQ_DIR) != 0){
                    state = Turnout.CLOSED;
                    //System.out.println("LocoNetAPI::message() SW_REQ state=CLOSED for dccAddr="+dccAddress);
                    setSwitchIsThrown(dccAddress, false);
                    LayoutPanel.getSwitch(dccAddress).draw();
                }else{
                    state = Turnout.THROWN;
                    //System.out.println("LocoNetAPI::message() SW_REQ state=THROWN for dccAddr="+dccAddress);
                    setSwitchIsThrown(dccAddress, true);
                    LayoutPanel.getSwitch(dccAddress).draw();
                }
                //state = adjustStateForInversion(state);
                //newCommandedState(state);
                //if (getFeedbackMode()==MONITORING || getFeedbackMode()==DIRECT) newKnownState(state);
            }
            break;
        }

        case LnConstants.OPC_LONG_ACK: {               /* page 9 of Loconet PE */
            int sw1 = l.getElement(1);
            int sw2 = l.getElement(2);
            int dccAddress = address(sw1, sw2);
            SwitchInfo sw = getSwitchInfo(dccAddress);

            //Debug code:
            int opCode = l.getOpCode();
            byte[] opBytes = convertIntToBytes(opCode);
            String opStr = convertBytesToString(opBytes);
            byte[] sw1Bytes = convertIntToBytes(sw1);
            String sw1Str = convertBytesToString(sw1Bytes);
            byte[] sw2Bytes = convertIntToBytes(sw2);
            String sw2Str = convertBytesToString(sw2Bytes);

            //System.out.println("LocoNetAPI::message() OPC_LONG_ACK received for dccAddr="+dccAddress+"!!  "+
            //		" op="+opStr+"  sw1="+sw1Str+"  sw2="+sw2Str);

            if (sw != null) {
            	if (log.isDebugEnabled()) log.debug("OPC_LONG_ACK received with valid address");
            	//System.out.println("LocoNetAPI::message() case LnConstants.OPC_LONG_ACK for SW="+dccAddress+"!!");

            	if (log.isDebugEnabled()) log.debug("OPC_LONG_ACK received with valid address");
                // see if its a turnout state report
                if ((sw2 & LnConstants.OPC_SW_REP_INPUTS)==0) {
                    // LnConstants.OPC_SW_REP_INPUTS not set, these report outputs
                	//System.out.println("LocoNetAPI::message() IF LnConstants.OPC_LONG_ACK NOT set!!");
   	        		// sort out states
                    int state;
                    state = sw2 & (LnConstants.OPC_SW_REP_CLOSED|LnConstants.OPC_SW_REP_THROWN);
                    //state = adjustStateForInversion(state);

                    switch (state) {
                    case LnConstants.OPC_SW_REP_CLOSED:
                    	System.out.println("LocoNetAPI::message() OPC_LONG_ACK state=CLOSED isThrown="+sw.getIsThrown());
                        break;
                    case LnConstants.OPC_SW_REP_THROWN:
                    	System.out.println("LocoNetAPI::message() OPC_LONG_ACK state=THROWN isThrown="+sw.getIsThrown());
                        break;
                    case LnConstants.OPC_SW_REP_CLOSED|LnConstants.OPC_SW_REP_THROWN:
                        //newCommandedState(CLOSED+THROWN);
                        //if (getFeedbackMode()==MONITORING || getFeedbackMode()==DIRECT) newKnownState(CLOSED+THROWN);
                        break;
                    default:
                        //newCommandedState(0);
                        //if (getFeedbackMode()==MONITORING || getFeedbackMode()==DIRECT) newKnownState(0);
                        break;
                    }
                }
            }
            break;
        }

        case LnConstants.OPC_SW_REP: {               /* page 9 of Loconet PE */
            int sw1 = l.getElement(1);
            int sw2 = l.getElement(2);
            int dccAddress = address(sw1, sw2);
            SwitchInfo sw = getSwitchInfo(dccAddress);
            System.out.println("LocoNetAPI::message() OPC_SW_REP received for dccAddr="+dccAddress+"!!");

            if (sw != null) {
            	if (log.isDebugEnabled()) log.debug("SW_REP received with valid address");
                // see if its a turnout state report
                if ((sw2 & LnConstants.OPC_SW_REP_INPUTS)==0) {
                    // LnConstants.OPC_SW_REP_INPUTS not set, these report outputs
                	System.out.println("LocoNetAPI::message() IF LnConstants.OPC_SW_REP_INPUTS NOT set!!");
   	        		// sort out states
                    int state;
                    state = sw2 & (LnConstants.OPC_SW_REP_CLOSED|LnConstants.OPC_SW_REP_THROWN);
                    //state = adjustStateForInversion(state);

                    switch (state) {
                    case LnConstants.OPC_SW_REP_CLOSED:
                    	System.out.println("LocoNetAPI::message() SW_REP state=CLOSED   isThrown="+sw.getIsThrown());
                    	//newCommandedState(CLOSED);
                        //if (getFeedbackMode()==MONITORING || getFeedbackMode()==DIRECT) newKnownState(CLOSED);
                        break;
                    case LnConstants.OPC_SW_REP_THROWN:
                    	System.out.println("LocoNetAPI::message() SW_REP state=THROWN   isThrown="+sw.getIsThrown());
                        //newCommandedState(THROWN);
                        //if (getFeedbackMode()==MONITORING || getFeedbackMode()==DIRECT) newKnownState(THROWN);
                        break;
                    case LnConstants.OPC_SW_REP_CLOSED|LnConstants.OPC_SW_REP_THROWN:
                        //newCommandedState(CLOSED+THROWN);
                        //if (getFeedbackMode()==MONITORING || getFeedbackMode()==DIRECT) newKnownState(CLOSED+THROWN);
                        break;
                    default:
                        //newCommandedState(0);
                        //if (getFeedbackMode()==MONITORING || getFeedbackMode()==DIRECT) newKnownState(0);
                        break;
                    }
                } else {
                	// LnConstants.OPC_SW_REP_INPUTS set, these are feedback messages from inputs
                	System.out.println("LocoNetAPI::message() ELSE LnConstants.OPC_SW_REP_INPUTS set!!");
   	        		// sort out states
                    // see EXACT feedback note at top
                	if ((sw2 & LnConstants.OPC_SW_REP_SW) !=0) {
                		System.out.println("LocoNetAPI::message() Switch input report!!");
                		// Switch input report
                		if ((sw2 & LnConstants.OPC_SW_REP_HI)!=0) {
                			// switch input closed (off)
                			if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.EXACT) {
                				// reached closed state
                				//newKnownState(adjustStateForInversion(Turnout.CLOSED));
                				System.out.println("LocoNetAPI::message() SW_REP_SW IF reached closed state   isThrown="+sw.getIsThrown());
                			} else if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.INDIRECT) {
                				// reached closed state
                				//newKnownState(adjustStateForInversion(Turnout.CLOSED));
                				System.out.println("LocoNetAPI::message() SW_REP_SW ELSE reached closed state   isThrown="+sw.getIsThrown());
                			}
                		} else {
                			// switch input thrown (input on)
   	        	       		if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.EXACT) {
   	        	       			// leaving CLOSED on way to THROWN, but ignoring that for now
   	        	       			System.out.println("SwitchSegment::message() SW_REP_SW IF leaving closed on way to THROWN state   isThrown="+sw.getIsThrown());
   	        	       		} else if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.INDIRECT) {
   	        	       			// reached thrown state
   	        	       			//newKnownState(adjustStateForInversion(Turnout.THROWN));
   	        	       			System.out.println("SwitchSegment::message() SW_REP_SW ELSE THROWN state   isThrown="+sw.getIsThrown());
   	        	       		}
                		}
                	} else {
                		System.out.println("SwitchSegment::message() LAST ELSE!!");
                		// Aux input report
                		if ((sw2 & LnConstants.OPC_SW_REP_HI)!=0) {
                			// aux input closed (off)
                			if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.EXACT) {
                				// reached thrown state
                				//newKnownState(adjustStateForInversion(Turnout.THROWN));
                				System.out.println("SwitchSegment::message() SW_REP_SW IF reached THROWN state   isThrown="+sw.getIsThrown());
                			}
                		} else {
                			// aux input thrown (input on)
                			if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.EXACT) {
                				// leaving THROWN on the way to CLOSED, but ignoring that for now
                				System.out.println("LocoNetAPI::message() SW_REP_SW ELSE leaving ThROWN on way to CLOSED state   isThrown="+sw.getIsThrown());
                			}
                		}
                	}
                }
            }
            System.out.println("LocoNetAPI::message() Calling first return! isThrown="+sw.getIsThrown());
            return;
        }
        default:
        	//System.out.println("LocoNetAPI::message() default calling second return! isThrown="+sw.getIsThrown());
            return;
        }
        // reach here only in error
    }

    private int address(int a1, int a2) {
        // the "+ 1" in the following converts to throttle-visible numbering
        return (((a2 & 0x0f) * 128) + (a1 & 0x7f) + 1);
    }

    //Use to debug returning LN Messages
    private byte[] convertIntToBytes(int i) {
    	byte[] result = new byte[4];

    	result[0] = (byte) (i >> 24);
    	result[1] = (byte) (i >> 16);
    	result[2] = (byte) (i >> 8);
    	result[3] = (byte) (i /*>> 0*/);

    	//NOTE: Beware that you might have to think about the byte order when doing so.
    	//return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(myInteger).
    	//ByteBuffer below results in BIG_ENDIAN byte order.
    	//OR could do one line below instead of multi-lines above:
    	//result = ByteBuffer.allocate(4).putInt(i).array();
    	return result;
    }

    //Use to debug returning LN Messages
    private String convertBytesToString(byte[] bytes) {
    	final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private SwitchInfo getSwitchInfo(int dccAddr) {
    	SwitchInfo retVal = null;
    	for (int i = 0; i < switchList.size(); i++) {
			if (switchList.get(i).getDccAddress() == dccAddr){
				retVal = switchList.get(i);
				break;
			}
		}
    	return retVal;
    }

    public boolean getSwitchIsThrown(int dccAddr) {
    	boolean retThrown = false;
    	for (int i = 0; i < switchList.size(); i++) {
			if (switchList.get(i).getDccAddress() == dccAddr){
				retThrown = switchList.get(i).getIsThrown();
				break;
			}
		}
    	return retThrown;
    }

    public void setSwitchIsThrown(int dccAddr, boolean thrown) {
    	for (int i = 0; i < switchList.size(); i++) {
			if (switchList.get(i).getDccAddress() == dccAddr){
				switchList.get(i).setIsThrown(thrown);
				break;
			}
		}
    }


    static Logger log = LoggerFactory.getLogger("LocoNetAPI");

	class SwitchInfo {
		private int   	       switchID;
		private String   	   swName;
		private int            dccAddress;
		private boolean        isThrown;
		private TcsTurnout     tcsTurnout;

		public SwitchInfo (int swID, int dccAddr, String name, boolean thrown){
			switchID      = swID;
			dccAddress    = dccAddr;
			swName        = name;
			isThrown      = thrown;
		}

		public int getDccAddress() {
			return dccAddress;
		}

		public void setDccAddress(int dccAddr) {
			dccAddress = dccAddr;
		}

		public String getName() {
			return swName;
		}

		public void setName(String name) {
			swName = name;
		}

		public boolean getIsThrown() {
			return isThrown;
		}

		public void setIsThrown(boolean thrown) {
			isThrown = thrown;
		}

		public int getSwitchID() {
			return switchID;
		}

		public void setSwitchID(int id) {
			switchID = id;
		}

		public TcsTurnout getTcsTurnout() {
			return tcsTurnout;
		}

		public void setTcsTurnout(TcsTurnout turnout) {
			tcsTurnout = turnout;
		}
	}
}
