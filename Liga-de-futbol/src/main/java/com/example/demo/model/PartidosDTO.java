package com.example.demo.model;


public class PartidosDTO {

	/*Los organizadores pueden programar partidos entre dos equipos, registrando la fecha,
	lugar y equipos participantes.*/
	private int ID;
	private EquipoDTO equipo1;
	private EquipoDTO equipo2;
	private String lugar;
	private String fecha_partido;
	private String Realizado="Pendiente";
	private int goles_equipo1;
	private int goles_equipo2;
	
	public PartidosDTO() {
		super();

	}
	
	public PartidosDTO(int ID,EquipoDTO equipo1, EquipoDTO equipo2, String fecha_partido, String lugar) {
		super();
		this.ID= ID;
		this.equipo1 = equipo1;
		this.equipo2 = equipo2;
		this.lugar = lugar;
		this.fecha_partido = fecha_partido;
	}

	public EquipoDTO getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(EquipoDTO equipo1) {
		this.equipo1 = equipo1;
	}

	public EquipoDTO getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(EquipoDTO equipo2) {
		this.equipo2 = equipo2;
	}

	public String getFecha_partido() {
		return fecha_partido;
	}

	public void setFecha_partido(String fecha_partido) {
		this.fecha_partido = fecha_partido;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	
	public String getRealizado() {
		return Realizado;
	}

	public void setRealizado(String realizado) {
		Realizado = realizado;
	}

	
	
	public int getGoles_equipo1() {
		return goles_equipo1;
	}

	public void setGoles_equipo1(int goles_equipo1) {
		this.goles_equipo1 = goles_equipo1;
	}

	public int getGoles_equipo2() {
		return goles_equipo2;
	}

	public void setGoles_equipo2(int goles_equipo2) {
		this.goles_equipo2 = goles_equipo2;
	}

	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return "PartidosDTO [ID=" + ID + ", equipo1=" + equipo1 + ", equipo2=" + equipo2 + ", fecha_partido="
				+ fecha_partido + ", lugar=" + lugar + ", Realizado=" + Realizado + ", goles_equipo1=" + goles_equipo1
				+ ", goles_equipo2=" + goles_equipo2 + "]";
	}

	

	
	
	
	
}
