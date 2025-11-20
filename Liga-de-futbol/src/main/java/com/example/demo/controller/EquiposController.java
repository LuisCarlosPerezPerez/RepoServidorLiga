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
import com.example.demo.services.interfaz.InterfazEquipos;

@Controller
public class EquiposController {

		public static List<EquipoDTO> listaequipos = new ArrayList<>();
		List<JugadorDTO> listajugadores = JugadoresController.Listajugadores;
		
		@Autowired
		public InterfazEquipos serviciodelEquipo;
		
		//Se podrán crear, mostrar, modificar y eliminar cada uno de los equipos. Cada equipo tiene
		//un máximo de 23 jugadores.
		@GetMapping("/crearEquipo")
		public String FormularioCrearEquipo(Model model) {
			model.addAttribute("equipo", serviciodelEquipo.prepararCreacionEquipoVacio());
			return "/equipo/crearequipo";
		}
		
		@PostMapping("/guardar_equipo")
		public String GuardarJugador(Model model, @ModelAttribute EquipoDTO equipo) {
			boolean existe = listaequipos.stream().anyMatch(e -> e.getNombre().equalsIgnoreCase(equipo.getNombre()));
			if(!existe) {
				serviciodelEquipo.guardarEquipo(equipo);
			}
			model.addAttribute("equipos", serviciodelEquipo.mostrarEquipos());
			return "/equipo/mostrarequipos";
		}
		@GetMapping("/mostrarEquipo")
		public String listarEquipos(Model model) {
			model.addAttribute("equipos", serviciodelEquipo.mostrarEquipos());
			return "/equipo/mostrarequipos";
		}
		@GetMapping("/Modequipo")
		public String FormularioBuscarEquipoModificar(Model model) {
			model.addAttribute("equipo", serviciodelEquipo.prepararCreacionEquipoVacio());
			return "/equipo/formEquipoMod";
		}
		@PostMapping("/modificacionEquipo")
		public String modificarEquipo(@RequestParam("modificarnombre") String nombre,Model model) {
			model.addAttribute("equipo", serviciodelEquipo.buscarLEquipoParaModificar(nombre));
			return "/equipo/modificacion";
		}
		/*para realizar la modificacion*/
		@PostMapping("/modificacionfinal")
		public String ModEquipo (@RequestParam("nuevoentrenador") String Entrenador,@RequestParam("nombre") String Nombre,  @RequestParam("AgregarJugador") String Agregar ,@RequestParam("EliminarJugador") String Eliminar , Model model) {
			model.addAttribute("equipos", serviciodelEquipo.ModificarEquipo(Nombre, Entrenador, Agregar, Eliminar));
			return "/equipo/mostrarequipos";
		}
		@GetMapping("/Elimequipo")
		public String FormularioBuscarEquipoEliminar(Model model) {
			model.addAttribute("equipo", serviciodelEquipo.prepararCreacionEquipoVacio());
			return "/equipo/formEquipoElim";
		}
		@PostMapping("/EliminarEquipo")
		public String ElimnarEquipo(@RequestParam("eliminar") String Nombre , Model model) {
			serviciodelEquipo.eliminarEquipo(Nombre);
			model.addAttribute("equipos", serviciodelEquipo.mostrarEquipos());
			return "/equipo/mostrarequipos";
		}
}
