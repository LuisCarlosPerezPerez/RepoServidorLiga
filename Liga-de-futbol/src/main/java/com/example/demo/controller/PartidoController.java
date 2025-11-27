package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.EquipoDTO;
import com.example.demo.model.JugadorDTO;
import com.example.demo.model.PartidosDTO;
import com.example.demo.services.interfaz.InterfazJugadores;
import com.example.demo.services.interfaz.InterfazPartidos;

@Controller
public class PartidoController {

	public static List<PartidosDTO> listapartidos = new ArrayList<>();
	List<EquipoDTO> listaequipos = EquiposController.listaequipos;
	List<JugadorDTO> listajugadores = JugadoresController.Listajugadores;
	
	@Autowired
	public InterfazPartidos Serviciopartido;
	
	@GetMapping("/CrearPartido")
	public String FormularioCrearPartido(Model model) {
		model.addAttribute("partido",Serviciopartido.CrearPartidoVacio());
		return "/partido/FormcrearPartido";
	}
	
	@PostMapping("/AgregarPartido")
	public String AgregarPartido(Model model, @RequestParam("equipo1") String nombreeq1, @RequestParam("equipo2") String nombreeq2, @RequestParam("lugar") String lugar
			, @RequestParam("fecha_partido") String fecha, @RequestParam("ID") int id) {
			Serviciopartido.AgregarPartido(nombreeq1, nombreeq2, lugar, fecha, id);
		model.addAttribute("partidos", Serviciopartido.mostrarPartidos());
		return "/partido/MostrarPartidos";
	}
	
	
	@GetMapping("/SimularPartido")
	public String SimularPartidos(Model model) {
	    model.addAttribute("partido", Serviciopartido.CrearPartidoVacio());
	    return "/partido/SimularPartido";
	}
	
	@PostMapping("/RegistrarResultado")
	public String FormularioPartido(Model model, @RequestParam("ID") int id) {
		
		model.addAttribute("partido", Serviciopartido.RegistrarResultado(id));
		return "/partido/FormGoles";
	}
	
	@PostMapping("/GuardarResultado")
	public String FormEstadisticasJugadores(Model model, @RequestParam("ID") int id, @RequestParam("goles_equipo1") int goleseq1,
			@RequestParam("goles_equipo2") int goleseq2) {
		
		model.addAttribute("jugadoreq1", Serviciopartido.ObtenerJugadoresEq1(id));
		model.addAttribute("jugadoreq2", Serviciopartido.ObtenerJugadoresEq2(id));
		model.addAttribute("partido", Serviciopartido.GuardarResultado(id, goleseq1, goleseq2));
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
	    
	    /*int pos = -1;
	    for (int i = 0; i < listapartidos.size(); i++) {
	        if (listapartidos.get(i).getID() == id) {
	            pos = i;
	            break;
	        }
	    }
	    int goleq1=0;
	    int goleq2=0;
	    int goleqrec1=0;
	    int goleqrec2=0;
	    for(int i=0;i<golesEq1.size(); i++) {
	    	goleq1= goleq1+golesEq1.get(i);
	    }
	    for(int i=0;i<golesEq2.size(); i++) {
	    	goleq2= goleq2+golesEq2.get(i);
	    }
	    for(int i=0;i<golesRecibidosEq1.size(); i++) {
	    	goleqrec1= goleqrec1+golesRecibidosEq1.get(i);
	    }
	    for(int i=0;i<golesRecibidosEq2.size(); i++) {
	    	goleqrec2= goleqrec2+golesRecibidosEq2.get(i);
	    }
	    if(listapartidos.get(pos).getGoles_equipo1()== goleq1 && listapartidos.get(pos).getGoles_equipo2()== goleq2
	    		&& goleq1 == goleqrec2 && goleq2 == goleqrec1) {
	    }else {
	    	int golesAnterioresEq1 = listapartidos.get(pos).getGoles_equipo1();
	        int golesAnterioresEq2 = listapartidos.get(pos).getGoles_equipo2();
	        
	        for (int t = 0; t < listaequipos.size(); t++) {
	            // --- REVERTIR EQUIPO 1 ---
	            if (listaequipos.get(t).getNombre().equalsIgnoreCase(listapartidos.get(pos).getEquipo1().getNombre())) {
	               
	                listaequipos.get(t).setGoles_a_favor(listaequipos.get(t).getGoles_a_favor() - golesAnterioresEq1);
	                listaequipos.get(t).setGoles_en_contra(listaequipos.get(t).getGoles_en_contra() - golesAnterioresEq2);

	                
	                if (golesAnterioresEq1 > golesAnterioresEq2) {
	                    
	                    listaequipos.get(t).setVictorias(listaequipos.get(t).getVictorias() - 1);
	                    listaequipos.get(t).setPuntos(listaequipos.get(t).getPuntos() - 3);
	                } else if (golesAnterioresEq1 < golesAnterioresEq2) {
	                    
	                    listaequipos.get(t).setDerrotas(listaequipos.get(t).getDerrotas() - 1);
	                } else {
	                    
	                    listaequipos.get(t).setEmpates(listaequipos.get(t).getEmpates() - 1);
	                    listaequipos.get(t).setPuntos(listaequipos.get(t).getPuntos() - 1);
	                }
	            }
	            
	            if (listaequipos.get(t).getNombre().equalsIgnoreCase(listapartidos.get(pos).getEquipo2().getNombre())) {
	             
	                listaequipos.get(t).setGoles_a_favor(listaequipos.get(t).getGoles_a_favor() - golesAnterioresEq2);
	                listaequipos.get(t).setGoles_en_contra(listaequipos.get(t).getGoles_en_contra() - golesAnterioresEq1);

	       
	                if (golesAnterioresEq2 > golesAnterioresEq1) {
	             
	                    listaequipos.get(t).setVictorias(listaequipos.get(t).getVictorias() - 1);
	                    listaequipos.get(t).setPuntos(listaequipos.get(t).getPuntos() - 3);
	                } else if (golesAnterioresEq2 < golesAnterioresEq1) {

	                    listaequipos.get(t).setDerrotas(listaequipos.get(t).getDerrotas() - 1);
	                } else {

	                    listaequipos.get(t).setEmpates(listaequipos.get(t).getEmpates() - 1);
	                    listaequipos.get(t).setPuntos(listaequipos.get(t).getPuntos() - 1);
	                }
	            }
	        }
	        listapartidos.get(pos).setRealizado("Pendiente");
	        listapartidos.get(pos).setGoles_equipo1(0);
	        listapartidos.get(pos).setGoles_equipo2(0);
	        
		    model.addAttribute("partidos", listapartidos);
	    	return "/partido/ErrorRealizarPartido";
	    }
	    for (int i = 0; i < listapartidos.get(pos).getEquipo1().getJugadores().size(); i++) {
	        listapartidos.get(pos).getEquipo1().getJugadores().get(i).setGoles(golesEq1.get(i) + listapartidos.get(pos).getEquipo1().getJugadores().get(i).getGoles());
	        listapartidos.get(pos).getEquipo1().getJugadores().get(i).setAmarillas(amarillasEq1.get(i) + listapartidos.get(pos).getEquipo1().getJugadores().get(i).getAmarillas());
	        listapartidos.get(pos).getEquipo1().getJugadores().get(i).setRojas(rojasEq1.get(i) + listapartidos.get(pos).getEquipo1().getJugadores().get(i).getRojas());
	        listapartidos.get(pos).getEquipo1().getJugadores().get(i).setGolesRecibidos(golesRecibidosEq1.get(i) + listapartidos.get(pos).getEquipo1().getJugadores().get(i).getGolesRecibidos());
	    }

	    for (int i = 0; i < listapartidos.get(pos).getEquipo2().getJugadores().size(); i++) {

	        listapartidos.get(pos).getEquipo2().getJugadores().get(i).setGoles(golesEq2.get(i) + listapartidos.get(pos).getEquipo2().getJugadores().get(i).getGoles());
	        listapartidos.get(pos).getEquipo2().getJugadores().get(i).setAmarillas(amarillasEq2.get(i) + listapartidos.get(pos).getEquipo2().getJugadores().get(i).getAmarillas());
	        listapartidos.get(pos).getEquipo2().getJugadores().get(i).setRojas(rojasEq2.get(i) + listapartidos.get(pos).getEquipo2().getJugadores().get(i).getRojas());
	        listapartidos.get(pos).getEquipo2().getJugadores().get(i).setGolesRecibidos(golesRecibidosEq2.get(i) + listapartidos.get(pos).getEquipo2().getJugadores().get(i).getGolesRecibidos());
	    }
	    
	    for(int i=0; i<listaequipos.size(); i++) {
	    	if(listaequipos.get(i).getNombre().equalsIgnoreCase(listapartidos.get(pos).getEquipo1().getNombre())) {
	    		for(int j=0; j<listaequipos.get(i).getJugadores().size(); j++) {
			    		listaequipos.get(i).getJugadores().get(j).setPartidos_jugados(listaequipos.get(i).getJugadores().get(j).getPartidos_jugados() + 1);
	    		}
	    	}
	    }
	    for(int i=0; i<listaequipos.size(); i++) {
	    	if(listaequipos.get(i).getNombre().equalsIgnoreCase(listapartidos.get(pos).getEquipo2().getNombre())) {
	    		for(int j=0; j<listaequipos.get(i).getJugadores().size(); j++) {
			    		listaequipos.get(i).getJugadores().get(j).setPartidos_jugados(listaequipos.get(i).getJugadores().get(j).getPartidos_jugados() + 1);
		    	}
	    	}
	    }*/
	    model.addAttribute("partidos", Serviciopartido.GuardarStats(id, golesEq1, amarillasEq1, rojasEq1, golesRecibidosEq1, golesEq2, amarillasEq2, rojasEq2, golesRecibidosEq2));
	    return "/partido/MostrarPartidos"; 
	}
}
