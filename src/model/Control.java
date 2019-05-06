package model;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Control {
	
	//Constants
	
	//Attributes
	
	//Relations
	private Plane firstPlane;
	private Plane lastPlane;
	private int cantVuelos;
	
	//Constructors
	public Control() {
		firstPlane = null;
		lastPlane = null;
		cantVuelos = 0;
	}
	
	//Getters and Setters
	
	//Methods
	//Generar vuelos
	public ObservableList<String> generarVuelos(String gen) {
		
		if(!gen.equals("") && Integer.parseInt(gen) > 0) {
			
			cantVuelos = Integer.parseInt(gen);
			try {
				firstPlane = new Plane(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			Plane sig = firstPlane;
			for(int i=1; i<cantVuelos; i++) {
				try {
					sig.setNext(new Plane(i));
					sig.getNext().setBack(sig);
					sig = sig.getNext();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			ObservableList<String> vuelos = FXCollections.<String>observableArrayList();
			
			sig = firstPlane;
			while(sig != null) {
				vuelos.add(sig.info());
				sig = sig.getNext();
			}
			
			return vuelos;
			
		}else {
			
			return null;
			
		}
		
	}
	
	//Busqueda
	public String buscFecha(String m) {
		
		String msj = "No se encuentra ningun vuelo con esta fecha";
		
		if(firstPlane != null) {
			
			boolean t = false;
			Plane n  = firstPlane;
			
			while(n != null && !t) {
				if(n.getDate().equals(m)) {
					msj = n.info();
					t = true;
				}
				n = n.getNext();
			}
			
		}
		
		return msj;
		
	}
	
	public String buscHorario(String m) {
		
		String msj = "No se encuentra ningun vuelo con este horario";
		
		if(firstPlane != null) {
			
			boolean t = false;
			Plane n  = firstPlane;
			
			while(n != null && !t) {
				if(n.getTime().equals(m)) {
					msj = n.info();
					t = true;
				}
				n = n.getNext();
			}
			
		}
		
		return msj;
		
	}
	
	public String buscAerolinea(String m) {
		
		String msj = "No se encuentra ningun vuelo con este horario";
		
		if(firstPlane != null) {
			
			boolean t = false;
			Plane n  = firstPlane;
			
			while(n != null && !t) {
				if(n.getAirline().equalsIgnoreCase(m)) {
					msj = n.info();
					t = true;
				}
				n = n.getNext();
			}
			
		}
		
		return msj;
		
	}
	
	public String buscNumero(String m) {
		
		String msj = "No se encuentra ningun vuelo con este horario";
		
		if(firstPlane != null) {
			
			boolean t = false;
			Plane n  = firstPlane;
			
			while(n != null && !t) {
				if(n.getId() == Integer.parseInt(m)) {
					msj = n.info();
					t = true;
				}
				n = n.getNext();
			}
			
		}
		
		return msj;
		
	}
	
	public String buscDestino(String m) {
		
		String msj = "No se encuentra ningun vuelo con este horario";
		
		if(firstPlane != null) {
			
			boolean t = false;
			Plane n  = firstPlane;
			
			while(n != null && !t) {
				if(n.getEnd().equalsIgnoreCase(m)) {
					msj = n.info();
					t = true;
				}
				n = n.getNext();
			}
			
		}
		
		return msj;
		
	}
	
	public String buscPuerta(String m) {
		
		String msj = "No se encuentra ningun vuelo con este horario";
		
		if(firstPlane != null) {
			
			boolean t = false;
			Plane n  = firstPlane;
			
			while(n != null && !t) {
				if(n.getDoor() == Integer.parseInt(m)) {
					msj = n.info();
					t = true;
				}
				n = n.getNext();
			}
			
		}
		
		return msj;
		
	}
	
	//Organizacion
	public ObservableList<String> orgFecha() {
		
		Plane sig = firstPlane;
		boolean p = true;
		while(sig.getNext().getNext() != null) {
			if(sig.getRealDate() > sig.getNext().getRealDate()) {
				Plane temp = sig;
				sig.getBack().setNext(sig.getNext());
				sig.getNext().setBack(sig.getBack());
				sig.setBack(sig.getNext());
				sig.setNext(sig.getNext().getNext());
				sig.getNext().setBack(sig);
				sig.getBack().setNext(sig);
				
				if(p) {
					
				}
				
			}
			sig = sig.getNext();
			
			if(sig.getNext().getNext() == null) {
				if(sig.getRealDate() > sig.getNext().getRealDate()) {
					sig.getBack().setNext(sig.getNext());
					sig.getNext().setBack(sig.getBack());
					sig.setBack(sig.getNext());
					sig.setNext(null);
					sig.getBack().setNext(sig);
				}
			}
			
		}
		
		ObservableList<String> vuelos = FXCollections.<String>observableArrayList();
		
		sig = firstPlane;
		while(sig != null) {
			vuelos.add(sig.info());
			sig = sig.getNext();
		}
		
		return vuelos;
		
	}
	
}
