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
	public String AgregarPartido(Model model, @RequestParam("equipo1") String nombreeq1, @RequestParam("equipo2") String nombreeq2, @RequestParam("lugar") String lugar
			, @RequestParam("fecha_partido") String fecha, @RequestParam("ID") int id) {
		EquipoDTO equipo1 = listaequipos.get(0);
		EquipoDTO equipo2 = listaequipos.get(0);
		boolean eq1existe = false;
		boolean eq2existe = false;
		boolean existe = listapartidos.stream().anyMatch(p -> p.getID() == id);
		if(!existe && !nombreeq1.equalsIgnoreCase(nombreeq2)) {
			for(int i=0; i<listaequipos.size(); i++) {
				if(listaequipos.get(i).getNombre().equalsIgnoreCase(nombreeq1)) {
					equipo1 = listaequipos.get(i);
					eq1existe = true;
				}
				if(listaequipos.get(i).getNombre().equalsIgnoreCase(nombreeq2)) {
					equipo2 = listaequipos.get(i);
					eq2existe= true;
				}
			}
			if(!eq1existe || !eq2existe) {
				
			}else {
				listapartidos.add(new PartidosDTO(id,equipo1,equipo2,lugar,fecha));
			}
		}
		model.addAttribute("partidos", listapartidos);
		return "/partido/MostrarPartidos";
	}
	
	
	@GetMapping("/SimularPartido")
	public String SimularPartidos(Model model) {
	    model.addAttribute("partido", new PartidosDTO());
	    return "/partido/SimularPartido";
	}
	
	@PostMapping("/RegistrarResultado")
	public String FormularioPartido(Model model, @RequestParam("ID") int id) {
		int pos=0;
		for(int i=0; i<listapartidos.size();i++) {
			if(listapartidos.get(i).getID() == id && listapartidos.get(i).getRealizado().equalsIgnoreCase("Pendiente")) {
				pos=i;
			}else {
				return "/partido/MostrarPartidos";
			}
		}
		model.addAttribute("partido", listapartidos.get(pos));
		return "/partido/FormGoles";
	}
	
	@PostMapping("/GuardarResultado")
	public String FormEstadisticasJugadores(Model model, @RequestParam("ID") int id, @RequestParam("goles_equipo1") int goleseq1,
			@RequestParam("goles_equipo2") int goleseq2) {
		boolean victoriaeq1 = false;
		boolean victoriaeq2 = false;
		boolean empate = false;
		List<JugadorDTO> listajugadoreseq1 = new ArrayList<>();
		List<JugadorDTO> listajugadoreseq2 = new ArrayList<>();
		int pos=0;
		for(int i=0; i<listapartidos.size(); i++){
			if(listapartidos.get(i).getID() == id) {
				pos=i;
				listapartidos.get(i).setGoles_equipo1(goleseq1);
				listapartidos.get(i).setGoles_equipo2(goleseq2);
				listapartidos.get(i).setRealizado("Realizado");
				for (int t=0; t<listaequipos.size(); t++) {
					if(listaequipos.get(t).getNombre().equalsIgnoreCase(listapartidos.get(i).getEquipo1().getNombre())) {
						listaequipos.get(t).setGoles_a_favor(goleseq1 + listaequipos.get(t).getGoles_a_favor());
						listaequipos.get(t).setGoles_en_contra(goleseq2 + listaequipos.get(t).getGoles_en_contra());
					}
				}
				for (int t=0; t<listaequipos.size(); t++) {
					if(listaequipos.get(t).getNombre().equalsIgnoreCase(listapartidos.get(i).getEquipo2().getNombre())) {
						listaequipos.get(t).setGoles_a_favor(goleseq2 + listaequipos.get(t).getGoles_a_favor());
						listaequipos.get(t).setGoles_en_contra(goleseq1 + listaequipos.get(t).getGoles_en_contra());
					}
				}
				if(goleseq1< goleseq2) {
					victoriaeq2=true;
				}else if(goleseq1 > goleseq2) {
					victoriaeq1=true;
				}else {
					empate=true;
				}
				for(int j=0; j<listaequipos.size(); j++) {
					
					if(listaequipos.get(j).getNombre().equalsIgnoreCase(listapartidos.get(i).getEquipo1().getNombre()) && victoriaeq1) {
						listaequipos.get(j).setVictorias(listaequipos.get(j).getVictorias() + 1);
						listaequipos.get(j).setPuntos(listaequipos.get(j).getPuntos() + 3);
					}else if(listaequipos.get(j).getNombre().equalsIgnoreCase(listapartidos.get(i).getEquipo1().getNombre()) && victoriaeq2) {
						listaequipos.get(j).setDerrotas(listaequipos.get(j).getDerrotas() + 1);
					}else if(listaequipos.get(j).getNombre().equalsIgnoreCase(listapartidos.get(i).getEquipo1().getNombre()) && empate) {
						listaequipos.get(j).setEmpates(listaequipos.get(j).getEmpates() + 1);
						listaequipos.get(j).setPuntos(listaequipos.get(j).getPuntos() + 1);
					}
					
					if(listaequipos.get(j).getNombre().equalsIgnoreCase(listapartidos.get(i).getEquipo2().getNombre()) && victoriaeq2) {
						listaequipos.get(j).setVictorias(listaequipos.get(j).getVictorias() + 1);
						listaequipos.get(j).setPuntos(listaequipos.get(j).getPuntos() + 3);
					}else if(listaequipos.get(j).getNombre().equalsIgnoreCase(listapartidos.get(i).getEquipo2().getNombre()) && victoriaeq1) {
						listaequipos.get(j).setDerrotas(listaequipos.get(j).getDerrotas() + 1);
					}else if(listaequipos.get(j).getNombre().equalsIgnoreCase(listapartidos.get(i).getEquipo2().getNombre()) && empate) {
						listaequipos.get(j).setEmpates(listaequipos.get(j).getEmpates() + 1);
						listaequipos.get(j).setPuntos(listaequipos.get(j).getPuntos() + 1);
					}	
				}
				for(int j=0;j<listapartidos.get(i).getEquipo1().getJugadores().size(); j++) {
					listajugadoreseq1.add(listapartidos.get(i).getEquipo1().getJugadores().get(j));
				}
				for(int j=0;j<listapartidos.get(i).getEquipo2().getJugadores().size(); j++) {
					listajugadoreseq2.add(listapartidos.get(i).getEquipo2().getJugadores().get(j));
				}
			}
		}
		model.addAttribute("jugadoreq1", listajugadoreseq1);
		model.addAttribute("jugadoreq2", listajugadoreseq2);
		model.addAttribute("partido", listapartidos.get(pos));
		return "/partido/FormStatsJugadores";
	}
	
	@PostMapping("/GuardarStats")
	public String GuardarStats(
	        // Recibe el ID del partido
	        @RequestParam("ID") int id,
	        
	        // Recibe las listas de stats del Equipo 1
	        @RequestParam("goleseq1") List<Integer> golesEq1,
	        @RequestParam("amarillaseq1") List<Integer> amarillasEq1,
	        @RequestParam("rojaseq1") List<Integer> rojasEq1,
	        @RequestParam("golesrecibidoseq1") List<Integer> golesRecibidosEq1,
	        
	        // Recibe las listas de stats del Equipo 2
	        @RequestParam("goleseq2") List<Integer> golesEq2,
	        @RequestParam("amarillaseq2") List<Integer> amarillasEq2,
	        @RequestParam("rojaseq2") List<Integer> rojasEq2,
	        @RequestParam("golesrecibidoseq2") List<Integer> golesRecibidosEq2,
	        
	        Model model) {
	    
	    int pos = -1;
	    for (int i = 0; i < listapartidos.size(); i++) {
	        if (listapartidos.get(i).getID() == id) {
	            pos = i;
	            break;
	        }
	    }

	    for (int i = 0; i < listapartidos.get(pos).getEquipo1().getJugadores().size(); i++) {
	        listapartidos.get(pos).getEquipo1().getJugadores().get(i).setGoles(golesEq1.get(i));
	        listapartidos.get(pos).getEquipo1().getJugadores().get(i).setAmarillas(amarillasEq1.get(i));
	        listapartidos.get(pos).getEquipo1().getJugadores().get(i).setRojas(rojasEq1.get(i));
	        listapartidos.get(pos).getEquipo1().getJugadores().get(i).setGolesRecibidos(golesRecibidosEq1.get(i));
	    }

	    for (int i = 0; i < listapartidos.get(pos).getEquipo2().getJugadores().size(); i++) {

	        listapartidos.get(pos).getEquipo2().getJugadores().get(i).setGoles(golesEq2.get(i));
	        listapartidos.get(pos).getEquipo2().getJugadores().get(i).setAmarillas(amarillasEq2.get(i));
	        listapartidos.get(pos).getEquipo2().getJugadores().get(i).setRojas(rojasEq2.get(i));
	        listapartidos.get(pos).getEquipo2().getJugadores().get(i).setGolesRecibidos(golesRecibidosEq2.get(i));
	    }
	    for(int i=0; i<listaequipos.size(); i++) {
	    	if(listaequipos.get(i).getNombre().equalsIgnoreCase(listapartidos.get(pos).getEquipo1().getNombre())) {
	    		for(int j=0; j<listaequipos.get(i).getJugadores().size(); j++) {
			    		listaequipos.get(i).getJugadores().get(j).setGoles(listapartidos.get(pos).getEquipo1().getJugadores().get(j).getGoles() + listaequipos.get(i).getJugadores().get(j).getGoles());
			    		listaequipos.get(i).getJugadores().get(j).setAmarillas(listapartidos.get(pos).getEquipo1().getJugadores().get(j).getAmarillas() + listaequipos.get(i).getJugadores().get(j).getAmarillas());
			    		listaequipos.get(i).getJugadores().get(j).setRojas(listapartidos.get(pos).getEquipo1().getJugadores().get(j).getRojas() +  listaequipos.get(i).getJugadores().get(j).getRojas());
			    		listaequipos.get(i).getJugadores().get(j).setGolesRecibidos(listapartidos.get(pos).getEquipo1().getJugadores().get(j).getGolesRecibidos() + listaequipos.get(i).getJugadores().get(j).getGolesRecibidos());
			    		listaequipos.get(i).getJugadores().get(j).setPartidos_jugados(listaequipos.get(i).getJugadores().get(j).getPartidos_jugados() + 1);
		    	}
	    	}
	    }
	    for(int i=0; i<listaequipos.size(); i++) {
	    	if(listaequipos.get(i).getNombre().equalsIgnoreCase(listapartidos.get(pos).getEquipo2().getNombre())) {
	    		for(int j=0; j<listaequipos.get(i).getJugadores().size(); j++) {
			    		listaequipos.get(i).getJugadores().get(j).setGoles(listapartidos.get(pos).getEquipo2().getJugadores().get(j).getGoles() + listaequipos.get(i).getJugadores().get(j).getGoles());
			    		listaequipos.get(i).getJugadores().get(j).setAmarillas(listapartidos.get(pos).getEquipo2().getJugadores().get(j).getAmarillas() + listaequipos.get(i).getJugadores().get(j).getAmarillas());
			    		listaequipos.get(i).getJugadores().get(j).setRojas(listapartidos.get(pos).getEquipo2().getJugadores().get(j).getRojas() + listaequipos.get(i).getJugadores().get(j).getRojas());
			    		listaequipos.get(i).getJugadores().get(j).setGolesRecibidos(listapartidos.get(pos).getEquipo2().getJugadores().get(j).getGolesRecibidos() + listaequipos.get(i).getJugadores().get(j).getGolesRecibidos());
			    		listaequipos.get(i).getJugadores().get(j).setPartidos_jugados(listaequipos.get(i).getJugadores().get(j).getPartidos_jugados() + 1);
		    	}
	    	}
	    }
	    model.addAttribute("partidos", listapartidos);
	    return "/partido/MostrarPartidos"; 
	}
}
