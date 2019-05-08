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
			
			Plane sigg = firstPlane;
			for(int i=1; i<cantVuelos; i++) {
				try {
					sigg.setNext(new Plane(i));
					sigg = sigg.getNext();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			for(int i=0; i < cantVuelos; i++) {
				Plane sig = firstPlane;
				Plane ant = firstPlane;
				Boolean p = true;
				Boolean end = false;
				
				while(sig.getNext().getNext() != null && !end) {
					
					if(sig.getRealDate() > sig.getNext().getRealDate()) {
						
						Plane temp = sig.getNext();
						sig.setNext(sig.getNext().getNext());
						temp.setNext(sig);
						
						if(p) {
							firstPlane = temp;
						}else {
							ant.setNext(temp);
						}
						ant = temp;
						
					}else {
						ant = sig;
						sig = sig.getNext();
					}
					
					if(sig.getNext().getNext() == null && sig.getRealDate() > sig.getNext().getRealDate()) {
						
						Plane temp = sig.getNext();
						sig.setNext(null);
						temp.setNext(sig);
						ant.setNext(temp);
						end = true;
						sig = temp;
						
					}
					
					p = false;
					
				}
			}
			
			ObservableList<String> vuelos = FXCollections.<String>observableArrayList();
			
			Plane siggg = firstPlane;
			while(siggg != null) {
				vuelos.add(siggg.info());
				siggg = siggg.getNext();
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
		
		for(int i=0; i < cantVuelos; i++) {
			Plane sig = firstPlane;
			Plane ant = firstPlane;
			Boolean p = true;
			Boolean end = false;
			
			while(sig.getNext().getNext() != null && !end) {
				
				if(sig.getRealDate() > sig.getNext().getRealDate()) {
					
					Plane temp = sig.getNext();
					sig.setNext(sig.getNext().getNext());
					temp.setNext(sig);
					
					if(p) {
						firstPlane = temp;
					}else {
						ant.setNext(temp);
					}
					ant = temp;
					
				}else {
					ant = sig;
					sig = sig.getNext();
				}
				
				if(sig.getNext().getNext() == null && sig.getRealDate() > sig.getNext().getRealDate()) {
					
					Plane temp = sig.getNext();
					sig.setNext(null);
					temp.setNext(sig);
					ant.setNext(temp);
					end = true;
					sig = temp;
					
				}
				
				p = false;
				
			}
		}
		
		ObservableList<String> vuelos = FXCollections.<String>observableArrayList();
		
		Plane sig = firstPlane;
		while(sig != null) {
			vuelos.add(sig.info());
			sig = sig.getNext();
		}
		
		return vuelos;
		
	}
	
	public ObservableList<String> orgHorario() {
		
		for(int i=0; i < cantVuelos; i++) {
			Plane sig = firstPlane;
			Plane ant = firstPlane;
			Boolean p = true;
			Boolean end = false;
			
			while(sig.getNext().getNext() != null && !end) {
				
				if(sig.getRealTime() > sig.getNext().getRealTime()) {
					
					Plane temp = sig.getNext();
					sig.setNext(sig.getNext().getNext());
					temp.setNext(sig);
					
					if(p) {
						firstPlane = temp;
					}else {
						ant.setNext(temp);
					}
					ant = temp;
					
				}else {
					ant = sig;
					sig = sig.getNext();
				}
				
				if(sig.getNext().getNext() == null && sig.getRealTime() > sig.getNext().getRealTime()) {
					
					Plane temp = sig.getNext();
					sig.setNext(null);
					temp.setNext(sig);
					ant.setNext(temp);
					end = true;
					sig = temp;
					
				}
				
				p = false;
				
			}
		}
		
		ObservableList<String> vuelos = FXCollections.<String>observableArrayList();
		
		Plane sig = firstPlane;
		while(sig != null) {
			vuelos.add(sig.info());
			sig = sig.getNext();
		}
		
		return vuelos;
		
	}
	
	public ObservableList<String> orgAerolinea() {
		
		for(int i=0; i < cantVuelos; i++) {
			Plane sig = firstPlane;
			Plane ant = firstPlane;
			Boolean p = true;
			Boolean end = false;
			
			while(sig.getNext().getNext() != null && !end) {
				
				if(sig.getAirline().compareTo(sig.getNext().getAirline()) > 0) {//
					
					Plane temp = sig.getNext();
					sig.setNext(sig.getNext().getNext());
					temp.setNext(sig);
					
					if(p) {
						firstPlane = temp;
					}else {
						ant.setNext(temp);
					}
					ant = temp;
					
				}else {
					ant = sig;
					sig = sig.getNext();
				}
				
				if(sig.getNext().getNext() == null && sig.getAirline().compareTo(sig.getNext().getAirline()) > 0) {
					
					Plane temp = sig.getNext();
					sig.setNext(null);
					temp.setNext(sig);
					ant.setNext(temp);
					end = true;
					sig = temp;
					
				}
				
				p = false;
				
			}
		}
		
		ObservableList<String> vuelos = FXCollections.<String>observableArrayList();
		
		Plane sig = firstPlane;
		while(sig != null) {
			vuelos.add(sig.info());
			sig = sig.getNext();
		}
		
		return vuelos;
		
	}
	
	public ObservableList<String> orgNumero() {
		
		for(int i=0; i < cantVuelos; i++) {
			Plane sig = firstPlane;
			Plane ant = firstPlane;
			Boolean p = true;
			Boolean end = false;
			
			while(sig.getNext().getNext() != null && !end) {
				
				if(sig.getId() > sig.getNext().getId()) {
					
					Plane temp = sig.getNext();
					sig.setNext(sig.getNext().getNext());
					temp.setNext(sig);
					
					if(p) {
						firstPlane = temp;
					}else {
						ant.setNext(temp);
					}
					ant = temp;
					
				}else {
					ant = sig;
					sig = sig.getNext();
				}
				
				if(sig.getNext().getNext() == null && sig.getId() > sig.getNext().getId()) {
					
					Plane temp = sig.getNext();
					sig.setNext(null);
					temp.setNext(sig);
					ant.setNext(temp);
					end = true;
					sig = temp;
					
				}
				
				p = false;
				
			}
		}
		
		ObservableList<String> vuelos = FXCollections.<String>observableArrayList();
		
		Plane sig = firstPlane;
		while(sig != null) {
			vuelos.add(sig.info());
			sig = sig.getNext();
		}
		
		return vuelos;
		
	}
	
	public ObservableList<String> orgDestino() {
		
		for(int i=0; i < cantVuelos; i++) {
			Plane sig = firstPlane;
			Plane ant = firstPlane;
			Boolean p = true;
			Boolean end = false;
			
			while(sig.getNext().getNext() != null && !end) {
				
				if(sig.getEnd().compareTo(sig.getNext().getEnd()) > 0) {//
					
					Plane temp = sig.getNext();
					sig.setNext(sig.getNext().getNext());
					temp.setNext(sig);
					
					if(p) {
						firstPlane = temp;
					}else {
						ant.setNext(temp);
					}
					ant = temp;
					
				}else {
					ant = sig;
					sig = sig.getNext();
				}
				
				if(sig.getNext().getNext() == null && sig.getEnd().compareTo(sig.getNext().getEnd()) > 0) {
					
					Plane temp = sig.getNext();
					sig.setNext(null);
					temp.setNext(sig);
					ant.setNext(temp);
					end = true;
					sig = temp;
					
				}
				
				p = false;
				
			}
		}
		
		ObservableList<String> vuelos = FXCollections.<String>observableArrayList();
		
		Plane sigg = firstPlane;
		while(sigg != null) {
			vuelos.add(sigg.info());
			sigg = sigg.getNext();
		}
		
		return vuelos;
		
	}
	
	public ObservableList<String> orgPuerta() {
		
		for(int i=0; i < cantVuelos; i++) {
			Plane sig = firstPlane;
			Plane ant = firstPlane;
			Boolean p = true;
			Boolean end = false;
			
			while(sig.getNext().getNext() != null && !end) {
				
				if(sig.getDoor() > sig.getNext().getDoor()) {
					
					Plane temp = sig.getNext();
					sig.setNext(sig.getNext().getNext());
					temp.setNext(sig);
					
					if(p) {
						firstPlane = temp;
					}else {
						ant.setNext(temp);
					}
					ant = temp;
					
				}else {
					ant = sig;
					sig = sig.getNext();
				}
				
				if(sig.getNext().getNext() == null && sig.getDoor() > sig.getNext().getDoor()) {
					
					Plane temp = sig.getNext();
					sig.setNext(null);
					temp.setNext(sig);
					ant.setNext(temp);
					end = true;
					sig = temp;
					
				}
				
				p = false;
				
			}
		}
		
		ObservableList<String> vuelos = FXCollections.<String>observableArrayList();
		
		Plane sig = firstPlane;
		while(sig != null) {
			vuelos.add(sig.info());
			sig = sig.getNext();
		}
		
		return vuelos;
		
	}
	
}
