package com.example.demo.model;

public class EstadisticaDTO {
	private String dnijugador;
	private int golesmarcados;
	private int golesconcedidos;
	private int tarjetasamarillas;
	private int tarjetasrojas;
	

	
	public EstadisticaDTO() {
		super();
	}
	
	public EstadisticaDTO(String dnijugador, int golesmarcados, int golesconcedidos, int tarjetasamarillas,
			int tarjetasrojas) {
		super();
		this.dnijugador = dnijugador;
		this.golesmarcados = golesmarcados;
		this.golesconcedidos = golesconcedidos;
		this.tarjetasamarillas = tarjetasamarillas;
		this.tarjetasrojas = tarjetasrojas;
	}
	public String getDnijugador() {
		return dnijugador;
	}
	public void setDnijugador(String dnijugador) {
		this.dnijugador = dnijugador;
	}
	public int getGolesmarcados() {
		return golesmarcados;
	}
	public void setGolesmarcados(int golesmarcados) {
		this.golesmarcados = golesmarcados;
	}
	public int getGolesconcedidos() {
		return golesconcedidos;
	}
	public void setGolesconcedidos(int golesconcedidos) {
		this.golesconcedidos = golesconcedidos;
	}
	public int getTarjetasamarillas() {
		return tarjetasamarillas;
	}
	public void setTarjetasamarillas(int tarjetasamarillas) {
		this.tarjetasamarillas = tarjetasamarillas;
	}
	public int getTarjetasrojas() {
		return tarjetasrojas;
	}
	public void setTarjetasrojas(int tarjetasrojas) {
		this.tarjetasrojas = tarjetasrojas;
	}

	@Override
	public String toString() {
		return "EstadisticaDTO [dnijugador=" + dnijugador + ", golesmarcados=" + golesmarcados + ", golesconcedidos="
				+ golesconcedidos + ", tarjetasamarillas=" + tarjetasamarillas + ", tarjetasrojas=" + tarjetasrojas
				+ "]";
	}
	
	

}
