package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.JugadorDTO;
//Creacion de jugadores, totalmente funcional
@Controller
public class JugadoresController {
	
	public static List<JugadorDTO> Listajugadores = new ArrayList<>();

	
	@GetMapping("/crearJugador")
	public String FormularioCrearJugador(Model model) {
		model.addAttribute("jugador", new JugadorDTO());
		return "/jugador/JugadorForm.html";
	}
	
	@PostMapping("/guardar_jugador")
	public String GuardarJugador(Model model, @ModelAttribute JugadorDTO jugador) {
		boolean existe = Listajugadores.stream().anyMatch(j -> j.getDni().equalsIgnoreCase(jugador.getDni()));
		boolean edad = jugador.getEdad() > 16;
		
		if(!existe && edad) {
			Listajugadores.add(jugador);
		}
		model.addAttribute("jugadores", Listajugadores);
		return "/jugador/mostrarjugadores";
	}
	
}
