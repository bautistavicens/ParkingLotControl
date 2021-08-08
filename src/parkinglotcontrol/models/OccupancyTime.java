package parkinglotcontrol.models;

import java.io.Serializable;

public class OccupancyTime implements Serializable {
	
	private static final long serialVersionUID = 1198303662082529458L;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	
	public OccupancyTime() {
		this.setStartDate(null);
		this.setEndDate(null);
		this.setStartTime(null);
		this.setEndTime(null);
	}
	
	//Use this builder for unlimited/unknown time.
	public OccupancyTime(String startDate) {
		this.setStartDate(startDate);
		this.setEndDate(null);
		this.setStartTime(null);
		this.setEndTime(null);
	}
	
	public OccupancyTime(String startDate, String endDate) {
		this(startDate);
		this.setEndDate(endDate);
		this.setStartTime(null);
		this.setEndTime(null);
	}
	
	public OccupancyTime(String startDate, String endDate, String startTime, String endTime) {
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}

	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	//Use this for table
	public String fromDayHour() {
		if (startDate == null && startTime == null) {
			
			return "-";
		}
		
		if (startDate != null && startTime == null) {
		
			return startDate+" // " + "- Hs";
		}
		
		if (startDate == null && startTime != null) {
			
			return "-" +" // " + startTime.toString() + " Hs";
		}
		
		else {
			
			return startDate + " // " + startTime.toString() + " Hs";
		}
	}
	
	public String toDayHour() {
		if (endDate == null && endTime == null) {
			
			return "-";
		}
		
		if (endDate == null && endTime != null) {
			
			return "-" +" // "+ endTime.toString() +" Hs";
		}
		
		if (endDate != null && endTime == null) {
		
			return endDate +" // " + "- Hs";
		}
		else {
			
			return endDate +" // "+ endTime.toString() +" Hs";
		}
	}
	
	
	public String toString() {
		return "Inicio: "+ startDate +" "+ startTime + " Fin: " + endDate + " " + endTime;  
	}
}


	
	