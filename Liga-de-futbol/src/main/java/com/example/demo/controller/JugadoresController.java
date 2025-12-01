package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.EquipoDTO;
import com.example.demo.model.JugadorDTO;
import com.example.demo.services.interfaz.InterfazEquipos;
import com.example.demo.services.interfaz.InterfazJugadores;
//Creacion de jugadores, totalmente funcional
@Controller
public class JugadoresController {
	
	public static List<JugadorDTO> Listajugadores = new ArrayList<>();
	List<EquipoDTO> listaequipos = EquiposController.listaequipos;

	@Autowired
	public InterfazJugadores Serviciojugador;
	
	@GetMapping("/crearJugador")
	public String FormularioCrearJugador(Model model) {
		model.addAttribute("jugador", Serviciojugador.JugadorVacio());
		return "/jugador/JugadorForm.html";
	}
	
	@PostMapping("/guardar_jugador")
	public String GuardarJugador(Model model, @ModelAttribute JugadorDTO jugador) {
		boolean existe = Listajugadores.stream().anyMatch(j -> j.getDni().equalsIgnoreCase(jugador.getDni()));
		boolean edad = jugador.getEdad() > 16;
		if(!existe && edad ) {
			Serviciojugador.GuardarJugador(jugador);
		}
		model.addAttribute("jugadores", Serviciojugador.mostrarJugadores());
		return "/jugador/mostrarjugadores";
	}
	
	@GetMapping("/MostarStats")
	public String mostrarEstadisticas(Model model) {
		/*List<JugadorDTO> Delanteros = new ArrayList<>();
		List<JugadorDTO> Porteros = new ArrayList<>();
		List<JugadorDTO> Todos = new ArrayList<>();
		
		for(int i=0;i<listaequipos.size(); i++) {
			for(int j=0; j< listaequipos.get(i).getJugadores().size();j++) {
				if(listaequipos.get(i).getJugadores().get(j).getPos().equalsIgnoreCase("Portero")) {
					Porteros.add(listaequipos.get(i).getJugadores().get(j));
				}else {
					Delanteros.add(listaequipos.get(i).getJugadores().get(j));
				}
				Todos.add(listaequipos.get(i).getJugadores().get(j));
			}
		}
		for(int i=0; i<Listajugadores.size(); i++) {
			if(Listajugadores.get(i).getPos().equalsIgnoreCase("Portero")) {
				Porteros.add(Listajugadores.get(i));
			}else {
				Delanteros.add(Listajugadores.get(i));
			}
			Todos.add(Listajugadores.get(i));
		}
		Delanteros.sort(Comparator.comparing(JugadorDTO::getGoles).reversed());
		Porteros.sort(Comparator.comparing(JugadorDTO::getGolesRecibidos));*/
		model.addAttribute("jugadorDc", Serviciojugador.mostrarEstadisticasJugadoresDel());
		model.addAttribute("jugadorPt", Serviciojugador.mostrarEstadisticasJugadoresPor());
		model.addAttribute("jugador", Serviciojugador.mostrarEstadisticasJugadoresTodos());
		return "/jugador/mostrarjugadoresStats";
	}
	
}
