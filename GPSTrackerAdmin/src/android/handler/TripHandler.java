package android.handler;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import android.db.TripTrackerDB;
import android.dto.MobileLogDTO;
import android.dto.TripDTO;
import android.dto.TripLogsDTO;

import com.google.gson.Gson;

@Path("/trip")
public class TripHandler {
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String login(@QueryParam("userName") String userName,
			@QueryParam("password") String password) {
		Integer result = 0;
		try {
			result = TripTrackerDB.login(userName,password);
		} catch (Exception e) {
			result = 0;
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("Login Successful", result == 0 ? false : true);
			obj.put("UserId", result.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	@POST
	@Path("/createTrip")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String createTrip(TripDTO trip) {
		System.out.println("Inside Create Trip");
		Integer result;
		try {
			result = TripTrackerDB.createNewTrip(trip);
		} catch (Exception e) {
			result = 0;
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("Insert Successful", result == 0 ? false : true);
			obj.put("Trip ID", result.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}

	@POST
	@Path("/startTrip")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String startTrip(@QueryParam("tripId") String tripId,
			@QueryParam("initialETA") String initialETA,
			@QueryParam("startTime") String startTime) {
		Integer result = null;
		try {
			if (tripId != null) {
				result = TripTrackerDB.startTrip(tripId, initialETA, startTime);
			}
		} catch (Exception e) {
			result = 0;
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("Insert Successful", result == 0 ? false : true);
			obj.put("Trip ID", result.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}

	@POST
	@Path("/updateTrip")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String updateTrip(@QueryParam("tripId") String tripId,
			@QueryParam("lat") String lat, @QueryParam("lan") String lan,
			@QueryParam("time") String time) {
		Boolean result;
		try {
			result = TripTrackerDB.updateTrip(tripId, lat, lan, time);
		} catch (Exception e) {
			result = false;
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("Update Successful", result.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
	}

	@GET
	@Path("/getAllTrips")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllTrips() throws JSONException {
		List<TripDTO> trips = null;
		JSONObject obj = new JSONObject();
		Gson gson = new Gson();
		try {
			trips = TripTrackerDB.getAllTrips();
			obj.put("All Trips", gson.toJson(trips));
			obj.put("Fetch Successful", true);
		} catch (JSONException e) {
			obj.put("exception occured", e.getStackTrace());
			e.printStackTrace();
		} catch (Exception e) {
			obj.put("exception occured", e.getStackTrace());
			e.printStackTrace();
		}
		return obj.toString();
	}

	@GET
	@Path("/getTripLogs")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getTripLogs(@QueryParam("tripId") String tripId)
			throws JSONException {
		List<TripLogsDTO> tripLogs = null;
		JSONObject obj = new JSONObject();
		Gson gson = new Gson();
		try {
			tripLogs = TripTrackerDB.getTripLogs(tripId);
			obj.put("Trip Logs", gson.toJson(tripLogs));
			obj.put("Fetch Successful", true);
		} catch (JSONException e) {
			obj.put("exception occured", e.getStackTrace());
			e.printStackTrace();
		} catch (Exception e) {
			obj.put("exception occured", e.getStackTrace());
			e.printStackTrace();
		}
		
		return obj.toString();
	}
	
	
	@GET
	@Path("/getUserPosition")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getUserPosition(@QueryParam("mobileNumber") String mobileNumber)
			throws JSONException {
		MobileLogDTO mobileLog = null;
		JSONObject obj = new JSONObject();
		Gson gson = new Gson();
		try { 
			mobileLog = TripTrackerDB.getUserPosition(mobileNumber);
			obj.put("UserPosition", gson.toJson(mobileLog));
			obj.put("Fetch Successful", true);
		} catch (JSONException e) {
			obj.put("exception occured", e.getStackTrace());
			e.printStackTrace();
		} catch (Exception e) {
			obj.put("exception occured", e.getStackTrace());
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	@GET
	@Path("/setUserPosition")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String setUserPosition(@QueryParam("mobileNumber")String mobileNumber, @QueryParam("latitude")String latitude, @QueryParam("longitude")String longitude)
			throws JSONException {
		
		Integer result;
		try {
			result = TripTrackerDB.setUserPosition(mobileNumber, latitude, longitude);
		} catch (Exception e) {
			result = 0;
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("Insert Successful", result == 0 ? false : true);
			obj.put("Tracking ID", result.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj.toString();
		
	}

}
