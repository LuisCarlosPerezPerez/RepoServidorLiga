package com.example.demo.services.interfaz;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.EquipoDTO;

public interface InterfazEquipos {
	List<EquipoDTO> mostrarEquipos ();
	EquipoDTO prepararCreacionEquipoVacio();
	void guardarEquipo(EquipoDTO EquipoAGuardar);
	void eliminarEquipo(String nombre);
	EquipoDTO buscarLEquipoParaModificar(String nombre);
	List<EquipoDTO> ModificarEquipo(String nombre, String nuevoentrenador, String Agregar , String Eliminar);
	List<EquipoDTO> mostrarClasifEquipos();
}
