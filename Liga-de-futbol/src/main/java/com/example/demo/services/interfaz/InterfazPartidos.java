package com.example.demo.services.interfaz;

import java.util.List;

import com.example.demo.model.JugadorDTO;
import com.example.demo.model.PartidosDTO;

public interface InterfazPartidos {
	
	PartidosDTO CrearPartidoVacio();
	List<PartidosDTO> mostrarPartidos();
	void AgregarPartido(String nombreeq1,String nombreeq2, String lugar, String fecha, int id);
	PartidosDTO RegistrarResultado(int id);
	PartidosDTO GuardarResultado(int id, int goleseq1,int goleseq2);
	List<JugadorDTO> ObtenerJugadoresEq2(int id);
	List<JugadorDTO> ObtenerJugadoresEq1(int id);
	List<PartidosDTO> GuardarStats( int id, List<Integer> golesEq1,
	        List<Integer> amarillasEq1, List<Integer> rojasEq1, List<Integer> golesRecibidosEq1,
	        List<Integer> golesEq2, List<Integer> amarillasEq2,List<Integer> rojasEq2,
	        List<Integer> golesRecibidosEq2);
	
}
