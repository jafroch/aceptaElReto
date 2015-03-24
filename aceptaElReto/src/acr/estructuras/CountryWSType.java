package acr.estructuras;

public class CountryWSType {

	private String name;
	
	CountryWSType(){
		
	}
	CountryWSType(String pais){
		this.name=pais;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
