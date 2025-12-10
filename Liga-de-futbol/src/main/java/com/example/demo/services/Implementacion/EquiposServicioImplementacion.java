package com.example.demo.services.Implementacion;

import java.util.Comparator;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.controller.EquiposController;
import com.example.demo.controller.JugadoresController;
import com.example.demo.model.EquipoDTO;
import com.example.demo.model.JugadorDTO;
import com.example.demo.services.interfaz.InterfazEquipos;


@Primary
@Service
public class EquiposServicioImplementacion implements InterfazEquipos{

	List<EquipoDTO> listaequipos = EquiposController.listaequipos;
	List<JugadorDTO> listajugadores = JugadoresController.Listajugadores;
	@Override
	public List<EquipoDTO> mostrarEquipos() {
		// TODO Auto-generated method stub
		return listaequipos;
	}

	@Override
	public EquipoDTO prepararCreacionEquipoVacio() {
		// TODO Auto-generated method stub
		return new EquipoDTO();
	}

	@Override
	public void guardarEquipo(EquipoDTO EquipoAGuardar) {
		// TODO Auto-generated method stub
		listaequipos.add(EquipoAGuardar);
	}

	@Override
	public void eliminarEquipo(String nombre) {
		// TODO Auto-generated method stub
		//Eliminar equipo
		for(int i=0; i< listaequipos.size();i++) {
			if(listaequipos.get(i).getNombre().equalsIgnoreCase(nombre)) {
				for(int j=0; j< listaequipos.get(i).getJugadores().size();j++) {
					listajugadores.add(listaequipos.get(i).getJugadores().get(j));
				}
				listaequipos.remove(i);
			}
		}
	}

	@Override
	public EquipoDTO buscarLEquipoParaModificar(String nombre) {
		// TODO Auto-generated method stub
		//Bscar equipo que se quiere modificar
		int pos=-1;
		for(int i=0; i<listaequipos.size();i++) {
			if(listaequipos.get(i).getNombre().equalsIgnoreCase(nombre)) {
				pos=i;
			}
		}
		return listaequipos.get(pos);
	}

	@Override
	public List<EquipoDTO> ModificarEquipo(String nombre, String nuevoentrenador, String Agregar, String Eliminar) {
		// TODO Auto-generated method stub
		//Realizar la modificacion del equipo
		for(int i=0; i< listaequipos.size();i++) {
			if(listaequipos.get(i).getNombre().equalsIgnoreCase(nombre)) {
				listaequipos.get(i).setEntrenador(nuevoentrenador);
				if(!Eliminar.isEmpty()) {
					for(int t=0; t<listaequipos.get(i).getJugadores().size(); t++) {
						if(listaequipos.get(i).getJugadores().get(t).getDni().equalsIgnoreCase(Eliminar) ) {
							listajugadores.add(listaequipos.get(i).getJugadores().remove(t));
						}
					}
				}
				if(!Agregar.isEmpty() && listaequipos.get(i).getJugadores().isEmpty()) {
					for(int j=0; j<listajugadores.size(); j++) {
						if(listajugadores.get(j).getDni().equalsIgnoreCase(Agregar) ) {
							listaequipos.get(i).getJugadores().add(listajugadores.remove(j));
						}
					}
				}else if(!Agregar.isEmpty() && listaequipos.get(i).getJugadores().size() <23) {
					for(int j=0; j<listajugadores.size(); j++) {
						if(listajugadores.get(j).getDni().equalsIgnoreCase(Agregar) ) {
							listaequipos.get(i).getJugadores().add(listajugadores.remove(j));
						}
					}
				}
			}
		}
		return listaequipos;
	}
	public List<EquipoDTO> mostrarClasifEquipos() {
		// TODO Auto-generated method stub
		//Mostrar la clasificación segun los puntos obtenidos
		listaequipos.sort(Comparator.comparing(EquipoDTO::getPuntos).reversed());
		return listaequipos;
	}

}
