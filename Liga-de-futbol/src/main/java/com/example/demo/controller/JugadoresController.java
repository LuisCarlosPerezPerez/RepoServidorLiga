package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.EquipoDTO;
import com.example.demo.model.JugadorDTO;
//Creacion de jugadores, totalmente funcional
@Controller
public class JugadoresController {
	
	public static List<JugadorDTO> Listajugadores = new ArrayList<>();
	List<EquipoDTO> listaequipos = EquiposController.listaequipos;

	
	@GetMapping("/crearJugador")
	public String FormularioCrearJugador(Model model) {
		model.addAttribute("jugador", new JugadorDTO());
		return "/jugador/JugadorForm.html";
	}
	
	@PostMapping("/guardar_jugador")
	public String GuardarJugador(Model model, @ModelAttribute JugadorDTO jugador) {
		boolean existe = Listajugadores.stream().anyMatch(j -> j.getDni().equalsIgnoreCase(jugador.getDni()));
		boolean edad = jugador.getEdad() > 16;
		List<String> pos = new ArrayList<>();
		pos.add("Delantero");
		pos.add("Defensa");
		pos.add("Mediocentro");
		pos.add("Portero");
		boolean correcto= false;
		for(int i=0; i<pos.size(); i++) {
			if(jugador.getPos().equalsIgnoreCase(pos.get(i))) {
				correcto = true;
			}
		}
		
		if(!existe && edad && correcto) {
			Listajugadores.add(jugador);
		}
		model.addAttribute("jugadores", Listajugadores);
		return "/jugador/mostrarjugadores";
	}
	
	@GetMapping("/MostarStats")
	public String mostrarEstadisticas(Model model) {
		List<JugadorDTO> Delanteros = new ArrayList<>();
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
		Delanteros.sort(Comparator.comparing(JugadorDTO::getGoles).reversed());
		Porteros.sort(Comparator.comparing(JugadorDTO::getGolesRecibidos));
		model.addAttribute("jugadorDc", Delanteros);
		model.addAttribute("jugadorPt", Porteros);
		model.addAttribute("jugador", Todos);
		return "/jugador/mostrarjugadoresStats";
	}
	
}
