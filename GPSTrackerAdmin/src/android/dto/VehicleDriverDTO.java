package android.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class VehicleDriverDTO {
	@XmlElement
	private String driverId;
	@XmlElement
	private String driveName;
	@XmlElement
	private String address;
	@XmlElement
	private String licenseNumber;
	@XmlElement
	private String contactNumber;
	@XmlElement
	private String alternateContactNumber;
	
	public VehicleDriverDTO() {
		super();
	}

	public VehicleDriverDTO(String driverId, String driveName, String address,
			String licenseNumber, String contactNumber,
			String alternateContactNumber) {
		super();
		this.driverId = driverId;
		this.driveName = driveName;
		this.address = address;
		this.licenseNumber = licenseNumber;
		this.contactNumber = contactNumber;
		this.alternateContactNumber = alternateContactNumber;
	}

	public String getDriverId() {
		return driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public String getDriveName() {
		return driveName;
	}

	public void setDriveName(String driveName) {
		this.driveName = driveName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAlternateContactNumber() {
		return alternateContactNumber;
	}

	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	
	
	

}
