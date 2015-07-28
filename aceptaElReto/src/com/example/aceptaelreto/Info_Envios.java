package com.example.aceptaelreto;

public class Info_Envios {


	private String nameUser;
    private int idProb;
    private String leng;
    private String res;
    private int numEnvio;
    
	public Info_Envios(int num, String name, int id, String leng, String res){
        this.nameUser = name;
        this.idProb = id;
        this.leng = leng;
        this.numEnvio = num;
        this.res = res;
    }
	
    public int getNumEnvio() {
		return numEnvio;
	}

	public void setNumEnvio(int numEnvio) {
		this.numEnvio = numEnvio;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public int getIdProb() {
		return idProb;
	}

	public void setIdProb(int idProb) {
		this.idProb = idProb;
	}

	public String getLeng() {
		return leng;
	}

	public void setLeng(String leng) {
		this.leng = leng;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}


}
