package apps.gui3.tcs;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
//import javax.xml.bind.annotation.adapters.XmlAdapter;

//************************************************************************************************
//************************************************************************************************
//
//Class Route Definition - A Java Class to represent a defined Layout Route that a train can follow
//
//************************************************************************************************
//************************************************************************************************

//02/20
//@XmlAccessorType (XmlAccessType.FIELD)
public class Route {

	public enum RouteState {
		RUN, STOP, SUSPEND
	}

	@XmlElement
	private int routeID;
	@XmlElement
    private String name;
	@XmlElement
    private RouteState state;
	@XmlJavaTypeAdapter(ColorAdapter.class)
	@XmlElement
    private Color routeColor;
	@XmlElement
    private int numOfTimesAccessed;
	@XmlElement
    private static int idCounter = 0;

	@XmlElement
    private final List<RouteSegmentInfo> segments = new ArrayList <>();

    public List<RouteSegmentInfo> getSegments() {
        return segments;
    }


    public void setSegments(List<RouteSegmentInfo> sList) {
    	//Delete the old segments before adding the new ones from sList
    	segments.clear();

    	//Now, add the new segments...
    	segments.addAll(sList);
    }

	public Route() {

	}

    public Route(String rName) {
    	name = rName;
    	state = RouteState.STOP;
    	routeID = idCounter++;
    	numOfTimesAccessed = 1;
    }

    public void addSegment(boolean isTrackSeg, int  seg_id, String segName, int sw_id, String swName) {
    	RouteSegmentInfo info = new RouteSegmentInfo(isTrackSeg, seg_id, segName, sw_id, swName);

    	segments.add(info);
    }

    public void removeSegment(boolean isTrackSeg, int  _seg, int _swSeg) {
    	//TODO!!!
    	if(isTrackSeg) {
    		//Search through segments ArrayList for _seg ID...
    		for(int i = 0; i < segments.size(); i++) {
				if(segments.get(i).getIsTrackSegment())
					if(segments.get(i).getSegmentID() == _seg)
						segments.remove(i);
    		}
    	} else {
    		//Search through segments ArrayList for _swSeg ID...
    		for(int i = 0; i < segments.size(); i++) {
				if(segments.get(i).getIsTrackSegment() == false) {
					if(segments.get(i).getSwitchID() == _swSeg)
						segments.remove(i);
				}
    		}
    	}
    }

    public RouteSegmentInfo getSegment(boolean isTrackSeg, int  _seg, int _swSeg) {
    	if(isTrackSeg == true) {
    		//Search through segments ArrayList for _seg ID...
    		for(int i = 0; i < segments.size(); i++) {
				if(segments.get(i).getIsTrackSegment()){
					if(segments.get(i).getSegmentID() == _seg)
						return segments.get(i);
				}
			}
    	} else {
    		//Search through segments ArrayList for _swSeg ID...
    		for(int i = 0; i < segments.size(); i++) {
				if(segments.get(i).getIsTrackSegment() == false) {
					if(segments.get(i).getSwitchID() == _swSeg)
						return segments.get(i);
				}
    		}
    	}
    	return null;
    }



    public void sortSegments() {
    	//TODO...
    	//http://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
    	//Collections.sort(segments, new CustomComparator);
    }

    //Print the route and its segments
    public String print() {
    	String printLine = new String("");

    	printLine = "RouteID="+routeID+" Name="+name+" State="+state+" Color="+routeColor+" #TimesAccessed="+numOfTimesAccessed+
    			" idCounter="+idCounter+"\n";
    	for(int i = 0; i < segments.size(); i++){
    		RouteSegmentInfo seg = segments.get(i);
    		printLine = printLine + "   isTrackSegment="+seg.getIsTrackSegment()+
    				" segmentID="+seg.getSegmentID()+" segmentName="+seg.getSegmentName()+
    				" switchID="+seg.getSwitchID()+" switchName="+seg.getSwitchName()+"\n";
    	}
    	printLine = printLine + "\n";

    	return printLine;
    }

    //Getter & Setter Methods...
    public int getRouteID() {
    	return routeID;
    }

    public void setRouteID(int id) {
    	routeID = id;
    }

    public String getRoutename() {
    	return name;
    }

    public void setRouteName(String _name) {
    	name = _name;
    }

    public RouteState getRouteState() {
    	return state;
    }

    public void setRouteState(RouteState _state) {
    	state = _state;
    }

    public Color getRouteColor() {
    	return routeColor;
    }

    public void setRouteColor(Color _color) {
    	routeColor = _color;
    }

    public int getNumOfTimesAccessed() {
    	return numOfTimesAccessed;
    }

	public void setNumOfTimesAccessed(int _numOfTimesAccessed) {
		numOfTimesAccessed = _numOfTimesAccessed;
	}
}

class CustomComparator implements Comparator<Route> {
    @Override
    public int compare(Route o1, Route o2) {
        //return o1.getRouteCount().compareTo(o2.getRouteCount());
    	int a = o1.getNumOfTimesAccessed();
    	int b = o2.getNumOfTimesAccessed();
    	int cmp = a > b ? +1 : a < b ? -1 : 0;
        return cmp;
    }
}


//02/20  @XmlAccessorType (XmlAccessType.FIELD)
class RouteSegmentInfo { //One instance for each segment and each switch.

    @XmlElement
	private boolean       isTrackSegment;
	@XmlElement
	private int           segmentID;
	@XmlElement
	private String 		  segmentName;
	@XmlElement
	private int           switchID;
	@XmlElement
	private String 		  switchName;

	public RouteSegmentInfo() {

	}

	public RouteSegmentInfo (boolean isTrackSeg, int  seg_id, String segName, int sw_id, String swName){
		isTrackSegment = isTrackSeg;
		segmentID      = seg_id;
		segmentName    = segName;
		switchID       = sw_id;
		switchName     = swName;
	}

	public boolean getIsTrackSegment() {
		return isTrackSegment;
	}

	public void setIsTrackSegment(boolean isTrackSeg) {
		isTrackSegment = isTrackSeg;
	}

	public int getSegmentID() {
		return segmentID;
	}

	public void setSegmentID(int  _seg) {
		segmentID = _seg;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String  _seg) {
		segmentName = _seg;
	}

	public int getSwitchID() {
		return switchID;
	}

	public void setSwitchID(int _swSeg) {
		switchID = _swSeg;
	}

	public String getSwitchName() {
		return switchName;
	}

	public void setSwitchID(String _swSeg) {
		switchName = _swSeg;
	}
}

class ColorAdapter extends XmlAdapter<String,Color> {
	  public Color unmarshal(String s) {
		System.out.println("ColorAdapter::unmarshal s="+s+" Return Color="+Color.decode(s));
	    return Color.decode(s);
	  }
	  public String marshal(Color c) {
		  String rgb = Integer.toHexString(c.getRGB());
		  String ss = new String("#" + rgb.substring(2, rgb.length()));
		  System.out.println("ColorAdapter::marshal Color="+c+" return str="+ss+" toStr="+c.toString()+
				  " back to Color="+Color.decode(ss));
		return "#" + rgb.substring(2, rgb.length());
	  }
	}
