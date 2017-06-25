package android.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class MobileLogDTO {

	@XmlElement
	private int  trackingId;
	
	@XmlElement
	private String  mobileNumber;
	
	@XmlElement
	private String latitude;
	
	@XmlElement
	private String longitude;
	
	public MobileLogDTO() {
		super();
	}

	public MobileLogDTO(int trackingId, String mobileNumber, String latitude,
			String longitude) {
		super();
		this.trackingId = trackingId;
		this.mobileNumber = mobileNumber;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(int trackingId) {
		this.trackingId = trackingId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
	
}
