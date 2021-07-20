package parkinglotcontrol.models;

public class Car {
	private int idCar;
	private String Brand;
	private String licencePlate;
	private String owner;
	private ParkingLot parking;
	
	
	public Car(int idCar, String licencePlate, ParkingLot parking) {
		this.idCar = idCar;
		this.licencePlate = licencePlate;
		this.parking = parking;
	}
	public Car(int idCar, String licencePlate, String owner, ParkingLot parking) {
		this(idCar, licencePlate, parking);
		this.owner = owner;
	}
	
	public int getIdCar() {
		return idCar;
	}
	public void setIdCar(int idCar) {
		this.idCar = idCar;
	}
	
	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
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
