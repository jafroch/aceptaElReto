package acr.estructuras;

public abstract class WSTypeBuilder {
	protected WSType objeto;
	
	public WSTypeBuilder(WSType obj) {
		// TODO Auto-generated constructor stub
			this.objeto=obj;
		}
	public WSType build(){
		return this.objeto;
	}
}
