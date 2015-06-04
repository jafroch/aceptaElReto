package acr.estructuras;

import java.util.List;

public class ListCountryWSType {
	
	private List<CountryWSType> countries;

	public List<CountryWSType> getListaPaises() {
		return countries;
	}

	public void setListaPaises(List<CountryWSType> listaPaises) {
		this.countries = listaPaises;
	}
}
