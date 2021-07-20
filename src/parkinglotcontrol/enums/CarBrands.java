package parkinglotcontrol.enums;

public enum CarBrands {
	
	
	TOYOTA("Toyota"),
	FORD("Ford"),
	RENAULT("Renault"),
	VOLKSWAGEN("Volkswagen"),
	CHEVROLET("Chevrolet"),
	FIAT("Fiat"),
	LEXUS("Lexus"),
	BMW("BMW"),
	MERCEDES_BENZ("Mercedes Benz"),
	AUDI("Audi"),
	FERRARI("Ferrari"),
	LAMBORGHINI("lamborghini"),
	CHERRY("Cherry"),
	ROLLS_ROYCE("Rolls royce"),
	HONDA("Honda"),
	VOLVO("Volvo"),
	NISSAN("Nissan"),
	KIA("Kia"),
	HYUNDAI("Hyundai"),
	PEUGEOT("Peugeot"),
	LINCOLN("Lindoln"),
	JEEP("Jeep"),
	LANDROVER("Landrover"),
	CITROEN("Citroën"),
	DS("Ds");
	
	
	public String brand;
	
	CarBrands(String brand){
		this.brand = brand;
	}
}
