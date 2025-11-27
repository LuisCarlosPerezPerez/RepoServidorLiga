package com.example.demo.services.interfaz;

import java.util.List;

import com.example.demo.model.JugadorDTO;

public interface InterfazJugadores {
	JugadorDTO JugadorVacio();
	void GuardarJugador(JugadorDTO jugador) ;
	List<JugadorDTO> mostrarJugadores ();
	List<JugadorDTO> mostrarEstadisticasJugadoresTodos ();
	List<JugadorDTO> mostrarEstadisticasJugadoresDel ();
	List<JugadorDTO> mostrarEstadisticasJugadoresPor ();
}
