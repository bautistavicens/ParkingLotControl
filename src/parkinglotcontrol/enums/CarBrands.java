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
	LAMBORGHINI("Lamborghini"),
	CHERRY("Cherry"),
	ROLLS_ROYCE("Rolls royce"),
	HONDA("Honda"),
	VOLVO("Volvo"),
	NISSAN("Nissan"),
	KIA("Kia"),
	HYUNDAI("Hyundai"),
	PEUGEOT("Peugeot"),
	LINCOLN("Lindcoln"),
	JEEP("Jeep"),
	LANDROVER("Landrover"),
	CITROEN("Citroën"),
	DS("Ds");
	
	
	public String brandTitle;
	
	CarBrands(String brandTitle){
		this.brandTitle = brandTitle;
	}
}
