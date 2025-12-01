package com.example.demo.services.Implementacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.controller.EquiposController;
import com.example.demo.controller.JugadoresController;
import com.example.demo.model.EquipoDTO;
import com.example.demo.model.JugadorDTO;
import com.example.demo.services.interfaz.InterfazJugadores;

@Primary
@Service
public class JugadorServicioImplementacion implements InterfazJugadores{

	List<EquipoDTO> listaequipos = EquiposController.listaequipos;
	List<JugadorDTO> listajugadores = JugadoresController.Listajugadores;
	@Override
	public JugadorDTO JugadorVacio() {
		// TODO Auto-generated method stub
		return new JugadorDTO();
	}

	@Override
	public void GuardarJugador(JugadorDTO jugador) {
		// TODO Auto-generated method stub
		listajugadores.add(jugador);
	}


	@Override
	public List<JugadorDTO> mostrarEstadisticasJugadoresTodos() {
		// TODO Auto-generated method stub
		//Obtener todos los jugadores
		List<JugadorDTO> Todos = new ArrayList<>();
		for(int i=0;i<listaequipos.size(); i++) {
			for(int j=0; j< listaequipos.get(i).getJugadores().size();j++) {
				Todos.add(listaequipos.get(i).getJugadores().get(j));
			}
		}
		for(int i=0; i<listajugadores.size(); i++) {
			Todos.add(listajugadores.get(i));
		}
		return Todos;
	}

	@Override
	public List<JugadorDTO> mostrarEstadisticasJugadoresDel() {
		// TODO Auto-generated method stub
		//Obtener todos los jugadores, excepto porteros
		List<JugadorDTO> Delanteros = new ArrayList<>();
		for(int i=0;i<listaequipos.size(); i++) {
			for(int j=0; j< listaequipos.get(i).getJugadores().size();j++) {
				if(listaequipos.get(i).getJugadores().get(j).getPos().equalsIgnoreCase("Portero")) {
				}else {
					Delanteros.add(listaequipos.get(i).getJugadores().get(j));
				}
			}
		}
		for(int i=0; i<listajugadores.size(); i++) {
			if(listajugadores.get(i).getPos().equalsIgnoreCase("Portero")) {
			}else {
				Delanteros.add(listajugadores.get(i));
			}
		}
		Delanteros.sort(Comparator.comparing(JugadorDTO::getGoles).reversed());
		return Delanteros;
	}

	@Override
	public List<JugadorDTO> mostrarEstadisticasJugadoresPor() {
		// TODO Auto-generated method stub
		//Obtener a los porteros
		List<JugadorDTO> Porteros = new ArrayList<>();
		for(int i=0;i<listaequipos.size(); i++) {
			for(int j=0; j< listaequipos.get(i).getJugadores().size();j++) {
				if(listaequipos.get(i).getJugadores().get(j).getPos().equalsIgnoreCase("Portero")) {
					Porteros.add(listaequipos.get(i).getJugadores().get(j));
				}
			}
		}
		for(int i=0; i<listajugadores.size(); i++) {
			if(listajugadores.get(i).getPos().equalsIgnoreCase("Portero")) {
				Porteros.add(listajugadores.get(i));
			}
		}
		Porteros.sort(Comparator.comparing(JugadorDTO::getGolesRecibidos));
		return Porteros;
	}

	@Override
	public List<JugadorDTO> mostrarJugadores() {
		// TODO Auto-generated method stub
		return listajugadores;
	}

}
