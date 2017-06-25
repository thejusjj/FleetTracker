package android.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import android.dto.MobileLogDTO;
import android.dto.TripDTO;
import android.dto.TripLogsDTO;
import android.dto.VehicleDriverDTO;
import android.util.Constants;

public class TripTrackerDB {

	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl,
					Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return con;
		}
	}

	public static int createNewTrip(TripDTO trip) throws SQLException, Exception {
		int tripId = 0;
		PreparedStatement pstmt = null;
		Connection dbConn = null;
		ResultSet rs = null;
		try {
			dbConn = TripTrackerDB.createConnection();
			String query = "INSERT INTO TRIP_MASTER(TRIP_NAME,SOURCE,DESTINATION,VEHICLE_REG_NO,DRIVER_ID) values (?,?,?,?,?)";
			pstmt = dbConn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, trip.getTripName());
			pstmt.setString(2, trip.getSource());
			pstmt.setString(3, trip.getDestination());
			pstmt.setString(4, trip.getVehicleRegNo());
			pstmt.setString(5, trip.getDriver().getDriverId());
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				tripId = rs.getInt(1);
				System.out.println("Generated Trip Id: " + tripId);
			}
		} catch (SQLException sqle) {
			 sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (dbConn != null) {
				dbConn.close();
			}
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return tripId;
	}

	public static Integer startTrip(String tripId, String initialETA, String startTime) throws SQLException, Exception {
        // update start time and initialETA in Trip_Master for given trip id
        PreparedStatement pstmt = null;
        Connection dbConn = null;
        int rowsUpdated = 0;
        try {
               dbConn = TripTrackerDB.createConnection();
               String query = "UPDATE TRIP_MASTER SET INITIAL_ETA = ?, START_TIME = ? WHERE TRIP_ID = ?";
               pstmt = dbConn.prepareStatement(query);
               pstmt.setString(1, initialETA);
               pstmt.setString(2, startTime);
               pstmt.setInt(3, Integer.parseInt(tripId));
               rowsUpdated = pstmt.executeUpdate();
               System.out.println("TRIP_MASTER updated for Trip Id:" +tripId+ " and rows updated as: " +rowsUpdated);
        } catch (SQLException sqle) {
               sqle.printStackTrace();
        } catch (Exception e) {
               e.printStackTrace();
               if (dbConn != null) {
                     dbConn.close();
               }
        } finally {
               if (dbConn != null) {
                     dbConn.close();
               }
        }
        return rowsUpdated;
        
 }

	
	public static Boolean updateTrip(String tripId, String lat, String lan, String time) throws SQLException, Exception{
		PreparedStatement pstmt = null;
		Connection dbConn = null;
		Boolean insertStatus = false;
		ResultSet rs = null;
		int tripTrackerId = 0;
		try {
			dbConn = TripTrackerDB.createConnection();			
			String query = "INSERT into TRIP_TRACKER(TRIP_ID, LATITUDE, LONGITUDE, TIME) values (?,?,?,?)";
			pstmt = dbConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, tripId);
			pstmt.setString(2, lat);
			pstmt.setString(3, lan);
			pstmt.setString(4, time);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				tripTrackerId = rs.getInt(1);
				System.out.println("Generated Trip Tracker Id: " + tripTrackerId);
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			 sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (dbConn != null) {
				dbConn.close();
			}
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}

	public static List<TripLogsDTO> getTripLogs(String tripId) throws SQLException {
		PreparedStatement pstmt = null;
		Connection dbConn = null;
		List<TripLogsDTO> tripLogs = new ArrayList<TripLogsDTO>();
		ResultSet rs = null;
		try {
			dbConn = TripTrackerDB.createConnection();			
			String query = "select * from TRIP_TRACKER where TRIP_ID = ?";
			pstmt = dbConn.prepareStatement(query);
			pstmt.setString(1, tripId);
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				TripLogsDTO tripLog = new TripLogsDTO();
				tripLog.setTripTrackerId(rs.getInt("TRIP_TRACKER_ID"));
				tripLog.setTripId(rs.getInt("TRIP_ID"));
				tripLog.setLatitude(rs.getString("LATITUDE"));
				tripLog.setLongitude(rs.getString("LONGITUDE"));
				tripLog.setTime(rs.getString("TIME"));
				tripLogs.add(tripLog);
			}
		} catch (SQLException sqle) {
			 sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (dbConn != null) {
				dbConn.close();
			}
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return tripLogs;
	}

	public static List<TripDTO> getAllTrips() throws SQLException {
		PreparedStatement pstmt = null;
		Connection dbConn = null;
		List<TripDTO> trips = new ArrayList<TripDTO>();
		ResultSet rs = null;
		try {
			dbConn = TripTrackerDB.createConnection();			
			String query = "select * from TRIP_MASTER";
			pstmt = dbConn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				TripDTO trip = new TripDTO();
				trip.setTripId(rs.getInt("TRIP_ID"));
				trip.setTripName(rs.getString("TRIP_NAME"));
				trip.setSource(rs.getString("SOURCE"));
				trip.setDestination(rs.getString("DESTINATION"));
				trip.setStartTime(rs.getString("START_TIME"));
				trip.setInitialETA(rs.getString("INITIAL_ETA"));
				trip.setRealTimeETA(rs.getString("REALTIME_ETA"));
				trip.setVehicleRegNo(rs.getString("VEHICLE_REG_NO"));
				PreparedStatement getDrivertPstmt = dbConn.prepareStatement("SELECT * FROM DRIVER WHERE DRIVER_ID = ?");
				getDrivertPstmt.setString(1, rs.getString("DRIVER_ID"));
				ResultSet driverRS = getDrivertPstmt.executeQuery();
				if(driverRS != null && driverRS.next()) {
					trip.setDriver(new VehicleDriverDTO(driverRS.getString("DRIVER_ID"),
							driverRS.getString("NAME"), driverRS.getString("ADDRESS"),
							driverRS.getString("LICENSE_NO"), driverRS.getString("CONTACT_NO"),
							driverRS.getString("ALTERNATE_CONTACT_NO")));
				}
				trips.add(trip);
			}
		} catch (SQLException sqle) {
			 sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (dbConn != null) {
				dbConn.close();
			}
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return trips;
	}

	public static Integer login(String userName, String password) throws SQLException {
		PreparedStatement pstmt = null;
		Connection dbConn = null;
		int userId = 0;
		ResultSet rs = null;
		try {
			dbConn = TripTrackerDB.createConnection();			
			String query = "select * from USER_MASTER where USER_NAME = ? and PASSWORD = ?";
			pstmt = dbConn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				userId = rs.getInt("USER_ID");
			}
		} catch (SQLException sqle) {
			 sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (dbConn != null) {
				dbConn.close();
			}
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return userId;
	}

	public static MobileLogDTO getUserPosition(String mobileNumber) throws SQLException {
		PreparedStatement pstmt = null;
		Connection dbConn = null;
		MobileLogDTO mobileLog = new MobileLogDTO();
		ResultSet rs = null;
		try {
			dbConn = TripTrackerDB.createConnection();			
			String query = "select * from MOBILE_NUMBER_TRACKER where MOBILE_NUMBER like ?";
			pstmt = dbConn.prepareStatement(query);
			pstmt.setString(1, "%" + mobileNumber + "%");
			rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				mobileLog.setTrackingId(rs.getInt("TRACKING_ID"));
				mobileLog.setMobileNumber(rs.getString("MOBILE_NUMBER"));
				mobileLog.setLatitude(rs.getString("LATITUDE"));
				mobileLog.setLongitude(rs.getString("LONGITUDE"));
			}
		} catch (SQLException sqle) {
			 sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (dbConn != null) {
				dbConn.close();
			}
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return mobileLog;
	}
	
	public static int setUserPosition(String mobileNumber, String latitude, String longitude) throws SQLException {
		
		int trackingId = 0;
		PreparedStatement pstmt = null;
		Connection dbConn = null;
		ResultSet rs = null;
		try {
			dbConn = TripTrackerDB.createConnection();
			String query = "INSERT INTO MOBILE_NUMBER_TRACKER(MOBILE_NUMBER,LATITUDE,LONGITUDE) values (?,?,?)";
			pstmt = dbConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, mobileNumber);
			pstmt.setString(2, latitude);
			pstmt.setString(3, longitude);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				trackingId = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			 sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			if (dbConn != null) {
				dbConn.close();
			}
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return trackingId;
	}
	
}