package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class EquipoDTO {

	private String nombre;
	private String entrenador;
	private List<JugadorDTO> jugadores = new ArrayList<>();
	private int goles_a_favor;
	private int goles_en_contra;
	private int victorias;
	private int derrotas;
	private int empates;
	private int puntos;
	
	public EquipoDTO() {
		super();

	}
	
	
	public EquipoDTO(String nombre, String entrenador) {
		super();
		this.nombre = nombre;
		this.entrenador = entrenador;

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(String entrenador) {
		this.entrenador = entrenador;
	}

	public List<JugadorDTO> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<JugadorDTO> jugadores) {
		this.jugadores = jugadores;
	}

	public int getVictorias() {
		return victorias;
	}

	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}

	public int getDerrotas() {
		return derrotas;
	}

	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}

	public int getEmpates() {
		return empates;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

	
	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	
	public int getGoles_a_favor() {
		return goles_a_favor;
	}


	public void setGoles_a_favor(int goles_a_favor) {
		this.goles_a_favor = goles_a_favor;
	}


	public int getGoles_en_contra() {
		return goles_en_contra;
	}


	public void setGoles_en_contra(int goles_en_contra) {
		this.goles_en_contra = goles_en_contra;
	}


	@Override
	public String toString() {
		return "EquipoDTO [nombre=" + nombre + ", entrenador=" + entrenador + ", jugadores=" + jugadores
				+ ", goles_a_favor=" + goles_a_favor + ", goles_en_contra=" + goles_en_contra + ", victorias="
				+ victorias + ", derrotas=" + derrotas + ", empates=" + empates + ", puntos=" + puntos + "]";
	}


	


	
	
}

