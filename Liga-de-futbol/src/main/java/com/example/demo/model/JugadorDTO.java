package com.example.demo.model;

public class JugadorDTO {

	/*Cada jugador tiene un nombre, DNI, edad, posición en el equipo (portero, defensa…),
estadísticas individuales (goles, asistencias, tarjetas amarillas, rojas) y valor de mercado.
Los jugadores se crearán y se podrán asignar a un equipo.
Los jugadores solo pueden ser mayores de 16 años para participar en la liga.*/
	private String nombre;
	private String dni;
	private int edad;
	private String pos;
	private int goles;
	private int asistencias;
	private int amarillas;
	private int rojas;
	private int valor_mercado;
	private int golesRecibidos;
	
	public JugadorDTO() {
		super();
	}
	
	public JugadorDTO(String nombre, String dni, int edad, String pos, int goles, int asistencias, int amarillas,
			int rojas, int valor_mercado) {
		super();
		this.nombre = nombre;
		this.dni = dni;
		this.edad = edad;
		this.pos = pos;
		this.valor_mercado = valor_mercado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public int getGoles() {
		return goles;
	}

	public void setGoles(int goles) {
		this.goles = goles;
	}

	public int getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(int asistencias) {
		this.asistencias = asistencias;
	}

	public int getAmarillas() {
		return amarillas;
	}

	public void setAmarillas(int amarillas) {
		this.amarillas = amarillas;
	}

	public int getRojas() {
		return rojas;
	}

	public void setRojas(int rojas) {
		this.rojas = rojas;
	}


	public int getValor_mercado() {
		return valor_mercado;
	}

	public void setValor_mercado(int valor_mercado) {
		this.valor_mercado = valor_mercado;
	}

	
	public int getGolesRecibidos() {
		return golesRecibidos;
	}

	public void setGolesRecibidos(int golesRecibidos) {
		this.golesRecibidos = golesRecibidos;
	}

	@Override
	public String toString() {
		return "JugadorDTO [nombre=" + nombre + ", dni=" + dni + ", edad=" + edad + ", pos=" + pos + ", goles=" + goles
				+ ", asistencias=" + asistencias + ", amarillas=" + amarillas + ", rojas=" + rojas + ", valor_mercado="
				+ valor_mercado + ", golesRecibidos=" + golesRecibidos + "]";
	}

	
	
	
	
}
