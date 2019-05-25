package apps.gui3.tcs;


import apps.gui3.tcs.RoutesList.RouteSortedOrder;
import java.awt.Color;
import java.io.File;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

//************************************************************************************************
//************************************************************************************************
//
//Class RouteManager Definition - A Java Singleton Class to manage the Layout Routes
//
//************************************************************************************************
//************************************************************************************************

public class RouteManager {

	private static RouteManager _instance = null;  //A singleton class!

    private RoutesList routes = null;
    private Route currentRoute = null;

    //Refactor into a abstract class RouteDialog!!
    private RouteCreateDialog cDialog = null;
    private boolean managedForCreate;
    private RouteEditDialog eDialog = null;
    private boolean managedForEdit;
    private Timer timer = null;

    private final Color defaultRouteColor = new Color(255, 189, 0); //Orange... //Color(int r, int g, int b)

    protected RouteManager() {
    	routes = RoutesList.getInstance();

    }
    public static RouteManager getInstance() {
		if(_instance == null) {
			_instance = new RouteManager();

			//Read in any stored Routes to initialize the routesList!
			_instance.readInRoutes();
		}
		return _instance;
	}


    public void addSegment(boolean isTrackSeg, int  seg_id, String segName, int sw_id, String swName) {
    	if(getManagedForCreate() == true) createRouteAddSegment(isTrackSeg, seg_id, segName, sw_id, swName);
    	else if (getManagedForEdit() == true) editRouteAddSegment(isTrackSeg, seg_id, segName, sw_id, swName);
    }

    public void setCDialog(RouteCreateDialog _dialog) {
    	cDialog = _dialog;
    }

    public Route createRoute(String rName) {
    	if(rName != null) {
    		currentRoute = getRoute(rName);

    		if(currentRoute == null) { //Create...
    			currentRoute = new Route(rName);
    			currentRoute.setRouteColor(defaultRouteColor);
    			routes.addRoute(currentRoute);
    		}
    	}
    	return currentRoute;
    }

    //Refactor into a abstract class RouteDialog!!
    public void createRouteAddSegment(boolean isTrackSeg, int  seg_id, String segName, int sw_id, String swName) {
    	if(currentRoute != null) {
    		currentRoute.addSegment(isTrackSeg, seg_id, segName, sw_id, swName);
    		routes.addSegment(currentRoute);

    		if(cDialog != null && isTrackSeg == true) cDialog.addSegmentToList(segName);
    	}
    }

    public void setEDialog(RouteEditDialog _dialog) {
    	eDialog = _dialog;
    }

    public void selectRoute(String rName) {
    	currentRoute = getRoute(rName);
    }

    //Refactor into a abstract class RouteDialog!!
    public void editRouteAddSegment(boolean isTrackSeg, int  seg_id, String segName, int sw_id, String swName) {
    	if(currentRoute != null) {

    		currentRoute.addSegment(isTrackSeg, seg_id, segName, sw_id, swName);

    		if(eDialog != null && isTrackSeg == true) eDialog.addSegmentToList(segName);
    	}
    }

    public void keepDialogOpen() {
    	if (managedForCreate == true && cDialog != null) {
    		cDialog.setVisible(managedForCreate);
    	}
    	if (managedForEdit == true && eDialog != null){
    		eDialog.setVisible(managedForEdit);
    	}
    }

    public void deleteRoute(String rName) {
    	Route route = getRoute(rName);

    	if(route != null) {
    		routes.deleteRoute(route);
    	}
    }

    public void deleteAllRoutes() {
    	if(routes != null) {
    		List<Route> list = routes.getRoutes();
    		int count = 0;
    		int size = list.size();
    		for(int i = 0; i < size; i++) {
    			//JOptionPane.showMessageDialog(null, "Delete All Route="+list.get(i).getRoutename()+" i="+i);
    			list.remove(i);
    			count++;
    		}
    		JOptionPane.showMessageDialog(null, "Delete All Routes: "+count+" DELETED!!");

    		saveRoutes();
    	}
    }

    public RoutesList getRoutes() {
    	return routes;
    }

    public Color getRouteColor1() {
    	int red   = 248;
        int green = 203;
        int blue  = 40;
		Color highlightedColor = new Color(red,green,blue, 150);

    	return highlightedColor;
    }


    public Route getRoute(String rName) {
    	return routes.getRoute(rName);
    }

    public Route getRoute(int id) {
    	return routes.getRoute(id);
    }

    public Color getDefaultRouteColor() {
    	return defaultRouteColor;
    }

    public Route getCurrentRoute() {
    	return currentRoute;
    }

    public boolean getManagedForCreateEdit() {
    	boolean retVal = false;
    	if(getManagedForCreate() == true) retVal = true;
    	else if(getManagedForEdit() == true) retVal = true;
    	return retVal;
    }

    public boolean getManagedForCreate() {
		return managedForCreate;
	}
    public boolean getManagedForEdit() {
		return managedForEdit;
	}

    public void setManagedForCreate(boolean isManaged) {
    	if(isManaged && !managedForCreate) {
    		//Requesting to Manage...
    		//Create and launch Timer to keep Dialog visible
    		timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                	keepDialogOpen();
                }
            }, 0, (long)(0.25*1000));  // call 4 times a second!!
    	} else if(!isManaged && managedForCreate) {
    		//Requesting to unmanage...
    		if(timer != null)
    			timer.cancel();
    		timer = null;
    	}
		managedForCreate = isManaged;
	}

    public void setManagedForEdit(boolean isManaged) {
		managedForEdit = isManaged;
	}

    public void saveRoutes() {
    	//First, sort List by Route Name, then Save to disk!
    	routes.sort(RouteSortedOrder.A_Z);

    	System.out.println("\nRouteManager.saveRoutes CALLED!!");
    	try {
    		// create JAXB context; provide it with a list of classes to search for annotations
    	    
    		JAXBContext jaxbContext = JAXBContext.newInstance(RoutesList.class, Route.class, RouteSegmentInfo.class);
    		//...and initializing Marshaller
    		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    		// for getting nice formatted output
    		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

    		//specify the location and name of xml file to be created
    		File XMLfile = new File("c:/Users/rem63/JMRI/RoutesList.xml");
   		
    		// Writing to XML file
    		jaxbMarshaller.marshal(routes, XMLfile);

    		// Writing to console
    		jaxbMarshaller.marshal(routes, System.out);

    	} catch (JAXBException e) {
    		// some exception occurred
    		e.printStackTrace();
    	}

    }

    public void readInRoutes() {
    	    System.out.println("\nRouteManager.readInRoutes CALLED!!");
    	try {

    	   
    		// create JAXB context; provide it with a list of classes to search for annotations
    		JAXBContext jaxbContext = JAXBContext.newInstance(RoutesList.class, Route.class, RouteSegmentInfo.class);
    		//...and initializing Unmarshaller
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //specify the name of xml file to be read in
    		File XMLfile = new File("c:/Users/rem63/JMRI/RoutesList.xml");

    		if(XMLfile.exists()) {
    		    routes = (RoutesList) jaxbUnmarshaller.unmarshal(XMLfile);

	        	//Next, sort List by Route Name...
	        	routes.sort(RouteSortedOrder.A_Z);

	            List<Route> routeSegs = routes.getRoutes();

	            //Debug:  Print out the data that is read in...
	            
	            for(int i = 0; i < routes.getRoutes().size(); i++){
	    			System.out.println(routes.getRoutes().get(i).print());
	        	}
    		} else {
    			System.out.println("readInRoutes: Routes XML File not found!");

    		}
        } catch (JAXBException e) {
            e.printStackTrace();
        } 
    }
}

