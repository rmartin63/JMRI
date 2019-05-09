package apps.gui3.tcs;


import java.util.ArrayList;
import java.util.List;
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;


//************************************************************************************************
//************************************************************************************************
//
//Class RoutesList Definition - A Java Singleton Class to hold an ArrayList of the Layout Routes
//
//************************************************************************************************
//************************************************************************************************
//@XmlRootElement(name = "routesList")
//@XmlAccessorType (XmlAccessType.FIELD)
public class RoutesList {

	public enum RouteSortedOrder {
		A_Z, Z_A, NEW_OLD, OLD_NEW
	}
    private RouteSortedOrder routeSortOrder;

	private static RoutesList _instance = null;  //A singleton class!

	//@XmlElement(name="route")
    private List<Route> rList = new ArrayList <>();

    public List<Route> getRoutes() {
        return rList;
    }

    public void setRoutes(List<Route> routes) {
        this.rList = routes;
    }


    protected RoutesList() {

    }

    public static RoutesList getInstance() {
		if(_instance == null) {
			_instance = new RoutesList();
			_instance.routeSortOrder = RouteSortedOrder.A_Z;
		}
		return _instance;
	}

    public void addRoute(Route r) {
    	if(r != null) rList.add(r);
    }

    public void deleteRoute(Route r) {
    	if(r != null) rList.remove(r);
    }

    public void addSegment(Route r) {
    	if(r != null) {
    		for(int i = 0; i < rList.size(); i++){
    			if(rList.get(i) == r)
    				rList.set(i, r);
        	}
    	}
    }


    public Route getRoute(String rName) {
    	Route retVal = null;

    	if(rName != null) {
	    	//Search through routes ArrayList for rName...
    		//JOptionPane.showMessageDialog(null, "RouteList getRoute rList.size="+rList.size());
			for(int i = 0; i < rList.size(); i++) {
				//JOptionPane.showMessageDialog(null, "RouteList getRoute i="+i);
				if(rList.get(i).getRoutename().contains(rName) == true)
					retVal = rList.get(i);
			}
    	}
    	return retVal;
    }

    public Route getRoute(int id) {
    	Route retVal = null;
    	//Search through routes ArrayList for rName...
		for(int i = 0; i < rList.size(); i++) {
			if(rList.get(i).getRouteID() == id)
				retVal = rList.get(i);
		}
    	return retVal;
    }

    public RouteSortedOrder getRouteSortOrder() {
    	RouteSortedOrder retVal = routeSortOrder;
    	return retVal;
    }

    public void sort(RouteSortedOrder sOrder) {
    	if(rList.size() > 1) {
	    	//Sort list by: A_Z, Z_A, NEW_OLD, OLD_NEW


    		//START HERE - Finish getting sort working for Edit dialog Route Select list!
    		//Do I want to save (Write the Route List back out to XML prior to closing???

    		if(sOrder == RouteSortedOrder.A_Z) {
		    	for(int j = 0; j < rList.size(); j++) {
		    		for (int i = j+1 ; i < rList.size(); i++) {
		    			String routeName1 = rList.get(i).getRoutename();
		    	    	String routeName2 = rList.get(j).getRoutename();
		    	    	if(routeName1.compareTo(routeName2) < 0) {

		    	    		//*****************************
		    	    		//Swap the two List elements...
		    	    		//*****************************

		    	    		//First, copy rList.get(i) to temp...
		    	    		Route temp = new Route();
		    	    		temp.setRouteName(routeName2);
		    	    	    temp.setRouteState(rList.get(j).getRouteState());
	    	    	    	temp.setRouteID(rList.get(j).getRouteID());
	    	    	    	temp.setRouteColor(rList.get(j).getRouteColor());
	    	    	    	temp.setNumOfTimesAccessed(rList.get(j).getNumOfTimesAccessed());
	    	    	    	//Assign rList.get(j).segments to temp.segments
	    	    	    	temp.setSegments(rList.get(j).getSegments());

		    	    		//Next, rList.get(j) <= rList.get(i);
		    	    	    rList.get(j).setRouteName(routeName1);
		    	    	    rList.get(j).setRouteState(rList.get(i).getRouteState());
	    	    	    	rList.get(j).setRouteID(rList.get(i).getRouteID());
	    	    	    	rList.get(j).setRouteColor(rList.get(i).getRouteColor());
	    	    	    	rList.get(j).setNumOfTimesAccessed(rList.get(i).getNumOfTimesAccessed());
	    	    	    	//Assign rList.get(i).segments to rList.get(j).segments
	    	    	    	rList.get(j).setSegments(rList.get(i).getSegments());

		    	    		//Finally, rList.get(i) <= temp;
		    	    	    rList.get(i).setRouteName(routeName2);
		    	    	    rList.get(i).setRouteState(temp.getRouteState());
	    	    	    	rList.get(i).setRouteID(temp.getRouteID());
	    	    	    	rList.get(i).setRouteColor(temp.getRouteColor());
	    	    	    	rList.get(i).setNumOfTimesAccessed(temp.getNumOfTimesAccessed());
	    	    	    	//Assign temp.segments to rList.get(i).segments
	    	    	    	rList.get(i).setSegments(temp.getSegments());
		    	    	}
		    	    }
		    	}
    		}
    	}
    }
}

