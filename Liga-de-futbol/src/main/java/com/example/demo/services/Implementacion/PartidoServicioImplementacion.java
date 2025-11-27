package com.example.demo.services.Implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.controller.EquiposController;
import com.example.demo.controller.JugadoresController;
import com.example.demo.controller.PartidoController;
import com.example.demo.model.EquipoDTO;
import com.example.demo.model.JugadorDTO;
import com.example.demo.model.PartidosDTO;
import com.example.demo.services.interfaz.InterfazPartidos;


@Primary
@Service
public class PartidoServicioImplementacion implements InterfazPartidos {

	List<PartidosDTO> listapartidos = PartidoController.listapartidos;
	List<EquipoDTO> listaequipos = EquiposController.listaequipos;
	List<JugadorDTO> listajugadores = JugadoresController.Listajugadores;
	
	@Override
	public PartidosDTO CrearPartidoVacio() {
		// TODO Auto-generated method stub
		return new PartidosDTO();
	}

	@Override
	public List<PartidosDTO> mostrarPartidos() {
		// TODO Auto-generated method stub
		return listapartidos;
	}

	@Override
	public void AgregarPartido(String nombreeq1, String nombreeq2, String lugar, String fecha, int id) {
		// TODO Auto-generated method stub
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
	}

	@Override
	public PartidosDTO RegistrarResultado(int id) {
		// TODO Auto-generated method stub
		int pos=-1;
		for(int i=0; i<listapartidos.size();i++) {
			if(listapartidos.get(i).getID() == id && listapartidos.get(i).getRealizado().equalsIgnoreCase("Pendiente")) {
				pos=i;
			}
		}
		if(pos== -1) {
			
		}
		
		PartidosDTO partido = listapartidos.get(pos);
		return partido;
	}

	@Override
	public List<JugadorDTO> ObtenerJugadoresEq1(int id) {
		// TODO Auto-generated method stub
		List<JugadorDTO> listajugadoreseq1 = new ArrayList<>();
		for(int i=0; i<listapartidos.size(); i++){
			if(listapartidos.get(i).getID() == id) {
				for(int j=0;j<listapartidos.get(i).getEquipo1().getJugadores().size(); j++) {
					listajugadoreseq1.add(listapartidos.get(i).getEquipo1().getJugadores().get(j));
				}
			}
		}
		return listajugadoreseq1;
	}
	public List<JugadorDTO> ObtenerJugadoresEq2(int id) {
		// TODO Auto-generated method stub

		List<JugadorDTO> listajugadoreseq2 = new ArrayList<>();
		for(int i=0; i<listapartidos.size(); i++){
			if(listapartidos.get(i).getID() == id) {
				for(int j=0;j<listapartidos.get(i).getEquipo2().getJugadores().size(); j++) {
					listajugadoreseq2.add(listapartidos.get(i).getEquipo2().getJugadores().get(j));
				}
			}
		}
		return listajugadoreseq2;
	}
	
	@Override
	public PartidosDTO GuardarResultado(int id, int goleseq1, int goleseq2) {
		// TODO Auto-generated method stub
		boolean victoriaeq1 = false;
		boolean victoriaeq2 = false;
		boolean empate = false;
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
			}
		}
		PartidosDTO partido = listapartidos.get(pos);
		return partido;
	}

	@Override
	public List<PartidosDTO> GuardarStats(int id, List<Integer> golesEq1, List<Integer> amarillasEq1, List<Integer> rojasEq1,
			List<Integer> golesRecibidosEq1, List<Integer> golesEq2, List<Integer> amarillasEq2, List<Integer> rojasEq2,
			List<Integer> golesRecibidosEq2) {
		// TODO Auto-generated method stub
		int pos = -1;
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
	        return listapartidos;
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
	    }
		return listapartidos;
	}





}
