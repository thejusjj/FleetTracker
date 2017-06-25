package android.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class TripDTO {
	@XmlElement
	private int tripId;
	@XmlElement
	private String tripName;
	@XmlElement
	private String source;
	@XmlElement
	private String destination;
	@XmlElement
	private String startTime;
	@XmlElement
	private String initialETA;
	@XmlElement
	private String realTimeETA;
	@XmlElement
	private String vehicleRegNo;
	@XmlElement
	private VehicleDriverDTO driver;
	
	public TripDTO(){
		super();
	}

	public TripDTO(int tripId, String tripName, String source,
			String destination, String startTime, String initialETA,
			String realTimeETA, String vehicleRegNo, VehicleDriverDTO driver) {
		super();
		this.tripId = tripId;
		this.tripName = tripName;
		this.source = source;
		this.destination = destination;
		this.startTime = startTime;
		this.initialETA = initialETA;
		this.realTimeETA = realTimeETA;
		this.vehicleRegNo = vehicleRegNo;
		this.driver = driver;
	}

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public String getTripName() {
		return tripName;
	}

	public void setTripName(String tripName) {
		this.tripName = tripName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getInitialETA() {
		return initialETA;
	}

	public void setInitialETA(String initialETA) {
		this.initialETA = initialETA;
	}

	public String getRealTimeETA() {
		return realTimeETA;
	}

	public void setRealTimeETA(String realTimeETA) {
		this.realTimeETA = realTimeETA;
	}

	public String getVehicleRegNo() {
		return vehicleRegNo;
	}

	public void setVehicleRegNo(String vehicleRegNo) {
		this.vehicleRegNo = vehicleRegNo;
	}

	public VehicleDriverDTO getDriver() {
		return driver;
	}

	public void setDriver(VehicleDriverDTO driver) {
		this.driver = driver;
	}
	
	
	
}
