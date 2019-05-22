package apps.gui3.tcs;

import java.util.ArrayList;

//import com.sun.corba.se.impl.ior.ByteBuffer;

import jmri.Turnout;
import jmri.jmrix.loconet.LnConstants;
import jmri.jmrix.loconet.LnTrafficController;
import jmri.jmrix.loconet.LocoNetListener;
import jmri.jmrix.loconet.LocoNetMessage;
import jmri.jmrix.loconet.LocoNetSystemConnectionMemo;
import jmri.jmrix.loconet.SlotManager;
import jmri.jmrix.loconet.pr3.PR3Adapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LocoNetAPI implements LocoNetListener {

	private static LocoNetAPI            _instance = null;  //A singleton class!

	public static PR3Adapter layoutPr3API = null;
	public static LnTrafficController lnController = null;
	
    public static LocoNetSystemConnectionMemo memo = null;
    public static SlotManager slotManager = null;
 
    public static ArrayList <SwitchInfo> switchList = new ArrayList <>();
    public static TcsTurnoutManager     lnTurnoutMgr = null;
    
	public static final int METERINTERVAL = 100;  // msec wait before closed

	private boolean         mTurnoutNoRetry;

	//NOTE: Could have 2 LocoNetAPI instances if have 2 PR3 units
	//      one for RR traffic and a 2nd for second LOCONET for Signaling
	//
	//For multiple LOCONET's, would need to get PR3Adaptor and LnPacketizer working!!
	//
	//The LnTrafficController logic below is from bob J (JMRI users) and he said this logic
	//is the old way and defaults to a single LN network.

	protected LocoNetAPI() {
		//instantiate and initialize...
		//createPortAdaptor();
		
		createController();

		createTurnoutManager();
	}

	public static LocoNetAPI getInstance() {
		if(_instance == null) {
			_instance = new LocoNetAPI();
		}
		return _instance;
	}
	
	public static SlotManager getSlotManager() {
	    return slotManager;
	}
	
	public static LnTrafficController getTrafficController() {
	    return lnController;
	}

   
	private void createController() {
	    
	    if(layoutPr3API == null) {
	        layoutPr3API = PR3Adapter.getInstance();
	    }
        
        if(layoutPr3API != null && lnController == null) {
            memo = layoutPr3API.getSystemConnectionMemo(); 
			lnController = memo.getLnTrafficController(); 
			slotManager = memo.getSlotManager();
		    
			lnController.addLocoNetListener(~0, this); //~0 = LocoNetInterface.ALL
		}
		
    }

    private void createTurnoutManager() {
		if(layoutPr3API != null && lnTurnoutMgr == null) {
			mTurnoutNoRetry = false;
			
			//System.out.println("LocoNetAPI::createTurnoutManager() Creating TcsTurnoutManager...");
			lnTurnoutMgr = new TcsTurnoutManager(lnController, lnController, "L", mTurnoutNoRetry);
			jmri.InstanceManager.setTurnoutManager(lnTurnoutMgr);

			//TODO - IS THIS NEEDED???
			// outwait any pending delayed sends
			try { synchronized (this) { this.wait(LocoNetAPI.METERINTERVAL+25); }}
			catch (InterruptedException e) {}
		}
    }

	public TcsTurnout addSwitch(int switchID, int dccAddr, String name, boolean thrown) {
		TcsTurnout retVal = null;
		SwitchInfo sw = new SwitchInfo(switchID, dccAddr, name,thrown);
		switchList.add(sw);

   		//NOTE from LnTurnoutManager.java - System names are "LTnnn", where nnn is the turnout number without padding.
   		String sysName = "LT"+dccAddr;
   		
   		//if(dccAddr == 74) System.out.print("\n\nLocoNetAPI() addSwitch() switchID="+switchID+" dccAddr="+dccAddr+" sysName="+
   		//     sysName+" name="+name+" thrown="+thrown+" lnTurnoutMgr="+lnTurnoutMgr+"\n\n");
   		
   		if(layoutPr3API != null) {
   		    sw.turnout = lnTurnoutMgr.createNewTurnout(sysName, name);
   		}

   		//Establish the state of the the layout switch...
   		establishSwitchState(dccAddr, thrown);

   		return retVal;
    }

    public boolean sendDCCSwitchChange(int dccAddress) {
    	boolean ret = false; //Boolean return true if DCC Operation is successful.
    	int newState = Turnout.UNKNOWN;
    	SwitchInfo sw = getSwitchInfo(dccAddress);
    	TcsTurnout turnout = null;
    	if(sw != null) turnout = sw.getTcsTurnout();

    	if(layoutPr3API != null && turnout != null) {
    		boolean isThrown = getSwitchIsThrown(dccAddress);
    		//System.out.println("LocoNetAPI  Switchinfo: id="+sw.getSwitchID()+" dccAdr="+sw.getDccAddress()+
    		//        " name="+sw.getName()+" isThrown="+sw.isThrown);
    		
                
   			//NOTE: Only testing Switch 74, which is the closest yard switch to my desk
    		if(isThrown) {
    			newState = Turnout.THROWN;
    		//	System.out.println("LocoNetAPI forwardCommandChangeToLayou() Called for dccAddr="+dccAddress+"  newState=THROWN  "+newState);
    			turnout.sendSwChangeCmdToLayout(newState);
    		}
    		else {
    			newState = Turnout.CLOSED;
    		//	System.out.println("LocoNetAPI forwardCommandChangeToLayou() Called for dccAddr="+dccAddress+"  newState=CLOSED  "+newState);
    			turnout.sendSwChangeCmdToLayout(newState);
    		}

    		ret = (turnout.isConsistentState() && (turnout.getCommandedState() == newState));
    		//System.out.println("LocoNetAPI sendDCCSwitchChange() END  tcsTurnout.getCommandedState()="+
    		//		turnout.getCommandedState()+" newState="+newState+" ret="+ret+" isConsistentState()="+turnout.isConsistentState()+"\n");
   		}
    	return ret;
    }


	private void establishSwitchState(int dccAddress, boolean isThrown) {
		SwitchInfo sw = getSwitchInfo(dccAddress);
    	TcsTurnout turnout = sw.getTcsTurnout();

    	if(layoutPr3API != null && turnout != null) {
	    	if(!isThrown) {
	    		//Just send out a CLOSED state to initially set switch to a known state:
	    		turnout.sendSwChangeCmdToLayout(Turnout.CLOSED);
	    	} else {
	    		//Just send out a THROWN state to initially set switch to a known state:
	    		turnout.sendSwChangeCmdToLayout(Turnout.THROWN);
	    	}

		    //TODO - Test calling at startup and see if need to call when state unknown or even periodically
	   		//Send out a request to try to establish the current state of the switch on the layout
	   		sendOpcSwStateMessage(dccAddress);
    	}
	}

    //Send a single OPC_SW_STATE message for this turnout to request the current state.
    void sendOpcSwStateMessage(int dccAddress) {
         LocoNetMessage l = new LocoNetMessage(4);
         l.setOpCode(LnConstants.OPC_SW_STATE);

         //System.out.println("LocoNetAPI sendOpcSwStateMessage() Called for dccAddr="+dccAddress);
         
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
         //System.out.println("sendOpcSwStateMessage() Calling sendLocoNetMessage... dccAddress("+
         //dccAddress+")? T/F="+areEqual+" opCode="+opStr+"  sw1="+sw1Str+"  sw2="+sw2Str);

         lnController.sendLocoNetMessage(l);
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
            SwitchSegment swSeg = LayoutPanel.getSwitch(dccAddress);

            //if(dccAddress == 74) System.out.println("LoocoNetAPI:message sw="+sw+" swSeg="+swSeg+" dccAddress="+dccAddress);

            if (sw != null && swSeg != null) {
                if (log.isDebugEnabled()) log.debug("SW_REQ received with valid address");
                //sort out states
                int state;
                if ((sw2 & LnConstants.OPC_SW_REQ_DIR) != 0){
                    state = Turnout.CLOSED;
                    //System.out.println("LocoNetAPI::message() SW_REQ state=CLOSED for dccAddr="+dccAddress);
                    setSwitchIsThrown(dccAddress, false);
                    swSeg.draw();
                }else{
                    state = Turnout.THROWN;
                    //System.out.println("LocoNetAPI::message() SW_REQ state=THROWN for dccAddr="+dccAddress);
                    setSwitchIsThrown(dccAddress, true);
                    swSeg.draw();
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
                    	//System.out.println("LocoNetAPI::message() OPC_LONG_ACK state=CLOSED isThrown="+sw.getIsThrown());
                        break;
                    case LnConstants.OPC_SW_REP_THROWN:
                    	//System.out.println("LocoNetAPI::message() OPC_LONG_ACK state=THROWN isThrown="+sw.getIsThrown());
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
            //System.out.println("LocoNetAPI::message() OPC_SW_REP received for dccAddr="+dccAddress+"!!");

            if (sw != null) {
            	if (log.isDebugEnabled()) log.debug("SW_REP received with valid address");
                // see if its a turnout state report
                if ((sw2 & LnConstants.OPC_SW_REP_INPUTS)==0) {
                    // LnConstants.OPC_SW_REP_INPUTS not set, these report outputs
                	//System.out.println("LocoNetAPI::message() IF LnConstants.OPC_SW_REP_INPUTS NOT set!!");
   	        		// sort out states
                    int state;
                    state = sw2 & (LnConstants.OPC_SW_REP_CLOSED|LnConstants.OPC_SW_REP_THROWN);
                    //state = adjustStateForInversion(state);

                    switch (state) {
                    case LnConstants.OPC_SW_REP_CLOSED:
                    	//System.out.println("LocoNetAPI::message() SW_REP state=CLOSED   isThrown="+sw.getIsThrown());
                    	//newCommandedState(CLOSED);
                        //if (getFeedbackMode()==MONITORING || getFeedbackMode()==DIRECT) newKnownState(CLOSED);
                        break;
                    case LnConstants.OPC_SW_REP_THROWN:
                    	//System.out.println("LocoNetAPI::message() SW_REP state=THROWN   isThrown="+sw.getIsThrown());
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
                	//System.out.println("LocoNetAPI::message() ELSE LnConstants.OPC_SW_REP_INPUTS set!!");
   	        		// sort out states
                    // see EXACT feedback note at top
                	if ((sw2 & LnConstants.OPC_SW_REP_SW) !=0) {
                		//System.out.println("LocoNetAPI::message() Switch input report!!");
                		// Switch input report
                		if ((sw2 & LnConstants.OPC_SW_REP_HI)!=0) {
                			// switch input closed (off)
                			if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.EXACT) {
                				// reached closed state
                				//newKnownState(adjustStateForInversion(Turnout.CLOSED));
                				//System.out.println("LocoNetAPI::message() SW_REP_SW IF reached closed state   isThrown="+sw.getIsThrown());
                			} else if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.INDIRECT) {
                				// reached closed state
                				//newKnownState(adjustStateForInversion(Turnout.CLOSED));
                				//System.out.println("LocoNetAPI::message() SW_REP_SW ELSE reached closed state   isThrown="+sw.getIsThrown());
                			}
                		} else {
                			// switch input thrown (input on)
   	        	       		if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.EXACT) {
   	        	       			// leaving CLOSED on way to THROWN, but ignoring that for now
   	        	       			//System.out.println("SwitchSegment::message() SW_REP_SW IF leaving closed on way to THROWN state   isThrown="+sw.getIsThrown());
   	        	       		} else if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.INDIRECT) {
   	        	       			// reached thrown state
   	        	       			//newKnownState(adjustStateForInversion(Turnout.THROWN));
   	        	       			//System.out.println("SwitchSegment::message() SW_REP_SW ELSE THROWN state   isThrown="+sw.getIsThrown());
   	        	       		}
                		}
                	} else {
                		//System.out.println("SwitchSegment::message() LAST ELSE!!");
                		// Aux input report
                		if ((sw2 & LnConstants.OPC_SW_REP_HI)!=0) {
                			// aux input closed (off)
                			if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.EXACT) {
                				// reached thrown state
                				//newKnownState(adjustStateForInversion(Turnout.THROWN));
                				//System.out.println("SwitchSegment::message() SW_REP_SW IF reached THROWN state   isThrown="+sw.getIsThrown());
                			}
                		} else {
                			// aux input thrown (input on)
                			if (sw.getTcsTurnout().getFeedbackMode() == TcsTurnout.EXACT) {
                				// leaving THROWN on the way to CLOSED, but ignoring that for now
                				//System.out.println("LocoNetAPI::message() SW_REP_SW ELSE leaving ThROWN on way to CLOSED state   isThrown="+sw.getIsThrown());
                			}
                		}
                	}
                }
            }
            //System.out.println("LocoNetAPI::message() Calling first return! isThrown="+sw.getIsThrown());
            return;
        }
        default:
        	//System.out.println("LocoNetAPI::message() default calling second return!");
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
		private TcsTurnout     turnout;

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
			//if(dccAddress == 81) System.out.println("LocoNetAPI:getIsThrown addr - 81 isthrown="+isThrown);
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
			return turnout;
		}

		public void setTcsTurnout(TcsTurnout t) {
			turnout = t;
		}
	}
}
