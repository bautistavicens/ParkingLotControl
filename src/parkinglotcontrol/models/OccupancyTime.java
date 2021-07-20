package parkinglotcontrol.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class OccupancyTime {
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public OccupancyTime() {
		this.setStartDate(null);
		this.setEndDate(null);
		this.setStartTime(null);
		this.setEndTime(null);
	}
	
	//Use this builder for unlimited/unknown time.
	public OccupancyTime(LocalDate startDate) {
		this.setStartDate(startDate);
		this.setEndDate(null);
		this.setStartTime(null);
		this.setEndTime(null);
	}
	
	//use this for a single day stay.
	public OccupancyTime(LocalTime startTime, LocalTime endTime) {
		this.setStartDate(LocalDate.now());
		this.setEndDate(LocalDate.now());
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}
	
	// Use this constructor for a limited time, with a stay time greater than one day (limit days)
	public OccupancyTime(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setStartTime(startTime);
		this.setEndTime(endTime);
	}

	
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	//Use this for table
	public String fromDayHour() {
		if (startDate == null && startTime == null) {
			
			return "-";
		}
		
		if (startDate != null && startTime == null) {
		
			return startDate.toString() +" // " + "- Hs";
		}
		
		if (startDate == null && startTime != null) {
			
			return "-" +" // " + startTime.toString() + " Hs";
		}
		
		else {
			
			return startDate.toString() + " // " + startTime.toString() + " Hs";
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
		
			return endDate.toString() +" // " + "- Hs";
		}
		else {
			
			return endDate.toString() +" // "+ endTime.toString() +" Hs";
		}
	}
	
	
	public String toString() {
		return "Inicio: "+ startDate +" "+ startTime + " Fin: " + endDate + " " + endTime;  
	}
}


	
	