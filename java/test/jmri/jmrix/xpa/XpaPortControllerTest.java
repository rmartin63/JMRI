package jmri.jmrix.xpa;

import jmri.util.JUnitUtil;
import org.junit.After;
import org.junit.Before;

/**
 * JUnit tests for the XpaPortController class
 * <p>
 *
 * @author      Paul Bender Copyright (C) 2016
 */
public class XpaPortControllerTest extends jmri.jmrix.AbstractSerialPortControllerTestBase {

    @Override
    @Before
    public void setUp(){
       apps.tests.Log4JFixture.setUp();
       JUnitUtil.resetInstanceManager();
       XpaSystemConnectionMemo memo = new XpaSystemConnectionMemo();
       XpaTrafficController tc = new XpaTrafficController();
       apc = new XpaPortController(memo){
            @Override
            public boolean status(){
              return true;
            }
            @Override
            public void configure(){
            }
            @Override
            public java.io.DataInputStream getInputStream(){
                return null;
            }
            @Override
            public java.io.DataOutputStream getOutputStream(){
                return null;
            }

            /**
             * Get an array of valid baud rates; used to display valid options.
             */
            @Override
            public String[] validBaudRates(){
               String[] retval = {"9600"};
               return retval;
            }
            /**
             * Open a specified port. The appname argument is to be provided to the
             * underlying OS during startup so that it can show on status displays, etc
             */
            @Override
            public String openPort(String portName, String appName){
               return "";
            }

       };
    }

    @Override
    @After
    public void tearDown(){
       JUnitUtil.tearDown();
    }

}
