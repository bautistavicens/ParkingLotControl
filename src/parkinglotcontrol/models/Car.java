package parkinglotcontrol.models;

import java.io.Serializable;

public class Car implements Serializable {

	private static final long serialVersionUID = -6419309617838467207L;
	private String brand;
	private String licencePlate;
	private String owner;
	private ParkingLot parking;
	
	
	public Car(String licencePlate, ParkingLot parking) {
		this.licencePlate = licencePlate;
		this.parking = parking;
	}
	public Car(String licencePlate, String owner, ParkingLot parking) {
		this(licencePlate, parking);
		this.owner = owner;
	}
	public Car(String licencePlate, String owner, String brand, ParkingLot parking) {
		this(licencePlate, owner, parking);
		this.brand = brand;
		
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLicencePlate() {
		return licencePlate;
	}
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public ParkingLot getParking() {
		return parking;
	}
	public void setParking(ParkingLot parking) {
		this.parking = parking;
	}
	
	
	//Use this to change the car parking,
	public void changeParking(ParkingLot parking) {
		this.setParking(parking);
	}
	
	
}
