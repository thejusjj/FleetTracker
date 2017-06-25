package android.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class TripLogsDTO {
	@XmlElement
	private int tripTrackerId;
	@XmlElement
	private int  tripId;
	@XmlElement
	private String latitude;
	@XmlElement
	private String longitude;
	@XmlElement
	private String time;
	
	public TripLogsDTO() {
		super();
	}
	public TripLogsDTO(int tripTrackerId, int tripId, String latitude, String longitude,
			String time) {
		super();
		this.tripTrackerId = tripTrackerId;
		this.tripId = tripId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.time = time;
	}
	public int getTripTrackerId() {
		return tripTrackerId;
	}
	public void setTripTrackerId(int tripTrackerId) {
		this.tripTrackerId = tripTrackerId;
	}
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
