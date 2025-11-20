package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.EquipoDTO;
import com.example.demo.model.JugadorDTO;
import com.example.demo.model.PartidosDTO;

@Controller
public class PartidoController {

	List<PartidosDTO> listapartidos = new ArrayList<>();
	List<EquipoDTO> listaequipos = EquiposController.listaequipos;
	List<JugadorDTO> listajugadores = JugadoresController.Listajugadores;
	
	@GetMapping("/CrearPartido")
	public String FormularioCrearPartido(Model model) {
		model.addAttribute("partido", new PartidosDTO());
		return "/partido/FormcrearPartido";
	}
	
	@PostMapping("/AgregarPartido")
	public String AgregarPartido(Model model, @ModelAttribute PartidosDTO partidos) {
		boolean existe = listapartidos.stream().anyMatch(p -> p.getID() == partidos.getID());
		if(!existe) {
			listapartidos.add(partidos);
		}
		model.addAttribute("partidos", listapartidos);
		return "/partido/MostrarPartidos";
	}
	
	@GetMapping("/SimularPartido")
	public String SimularPartidos(Model model) {
	    model.addAttribute("partido", new PartidosDTO());
	    return "/partido/SimularPartido";
	}
	
	@PostMapping("/GuardarResultado")
	public String FormularioPartido(Model model, @RequestParam("ID") int id) {
		int pos=0;
		for(int i=0; i<listapartidos.size();i++) {
			if(listapartidos.get(i).getID() == id) {
				pos=i;
			}
		}
		model.addAttribute("partido", listapartidos.get(pos));
		return "/partido/FormGoles";
	}
	
}
