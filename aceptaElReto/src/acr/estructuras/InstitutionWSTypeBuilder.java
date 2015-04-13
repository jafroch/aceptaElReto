package acr.estructuras;

public class InstitutionWSTypeBuilder extends WSTypeBuilder{
	protected InstitutionWSType institucion;
	
	public InstitutionWSTypeBuilder(InstitutionWSType institucion) {
	// TODO Auto-generated constructor stub
		super(institucion);
		this.institucion=institucion;
	}

	public WSType build() {
		// TODO Auto-generated method stub
		return this.institucion;
	}
	
}
