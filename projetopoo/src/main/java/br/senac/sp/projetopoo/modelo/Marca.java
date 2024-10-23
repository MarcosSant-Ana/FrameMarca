package br.senac.sp.projetopoo.modelo;

import java.util.Arrays;

public class Marca {
	private int id;
	private String nomeMarca;
	private byte[] logo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getnomeMarca() {
		return nomeMarca;
	}
	public void setnomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "Marca: \nid = " + id + "\nmarca = " + nomeMarca + "\nlogo=" + Arrays.toString(logo);
	}
}
