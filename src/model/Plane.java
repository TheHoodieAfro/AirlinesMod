package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Plane implements Comparable<Plane>{
	
	//Constants
	public final static String RUTE_CITY = "datos/ciudades.txt";
	public final static String RUTE_AIR = "datos/air.txt";
	
	//Attributes
	private String date;
	private double realDate;
	private String time;
	private int realTime;
	private String airline;
	private int id;
	private String end;
	private int door;
	
	//Relations
	private Plane next;
	
	//Constructors
	public Plane(int i) throws IOException {
		
		setId(i);
		
		generateDate();
		generateTime();
		generateAirline();
		generateEnd();
		generateDoor();
		
		next = null;
		
	}
	
	//Getters and Setters
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getDoor() {
		return door;
	}

	public void setDoor(int door) {
		this.door = door;
	}
	
	public int getRealTime() {
		return realTime;
	}

	public void setRealTime(int realTime) {
		this.realTime = realTime;
	}
	
	public double getRealDate() {
		return realDate;
	}

	public void setRealDate(double realDate) {
		this.realDate = realDate;
	}
	
	public Plane getNext() {
		return next;
	}
	
	public void setNext(Plane next) {
		this.next = next;
	}
	
	//Methods
	public void generateDate() {
		
		int v = 0;
		String dt = "";
		
		Random r = new Random();
		int dia = r.nextInt(30)+1;

		int mes = r.nextInt(12)+1;

		int año = r.nextInt(79)+1999;
		
		v = año+(mes/100)+(dia/10000);
		setRealDate(v);
		
		dt = año+"-"+mes+"-"+dia;
		
		setDate(dt);
		
	}
	
	public void generateTime() {

		String tim = "";
		
		Random r = new Random();
		int rr = r.nextInt(12)+1;
		
		Random f = new Random();
		int ff = f.nextInt(60);
		
		String[] a = {"a.m.", "p.m."};
		Random aa = new Random();
		int aaa = aa.nextInt(2);
		
		if(aaa == 0) {
			if(ff >= 0 && ff < 10) {
				tim = rr+"0"+ff;
				setRealTime(Integer.parseInt(tim));
			}else {
				tim = rr+""+ff;
				setRealTime(Integer.parseInt(tim));
			}
		}else {
			if(ff >= 0 && ff < 10) {
				tim = rr+"0"+ff+"00";
				setRealTime(Integer.parseInt(tim));
			}else {
				tim = rr+""+ff+"00";
				setRealTime(Integer.parseInt(tim));
			}
		}
		
		if(ff >= 0 && ff < 10) {
			tim = rr +":0"+ ff +" "+ a[aaa];
		}else {
			tim = rr +":"+ ff +" "+ a[aaa];
		}
		
		setTime(tim);
		
	}
	
	public void generateAirline() throws IOException {
		
		ArrayList<String> a = new ArrayList<String>();
		
		File file = new File(RUTE_AIR);
		FileReader r = new FileReader(file);
		BufferedReader br = new BufferedReader(r);
		
		String l = br.readLine();
		while(l != null) {
			l.trim();
			a.add(l);
			l = br.readLine();
		}
		
		Random rand = new Random();
		int d = rand.nextInt(a.size());
		setAirline(a.get(d));
		
		br.close();
		r.close();
		
	}
	
	public void generateEnd() throws IOException {
		
		ArrayList<String> a = new ArrayList<String>();
		
		File file = new File(RUTE_CITY);
		FileReader r = new FileReader(file);
		BufferedReader br = new BufferedReader(r);
		
		String l = br.readLine();
		while(l != null) {
			l.trim();
			a.add(l);
			l = br.readLine();
		}
		
		Random rand = new Random();
		int d = rand.nextInt(a.size());
		setEnd(a.get(d));
		
		br.close();
		r.close();
		
	}
	
	public void generateDoor() {
		
		Random r = new Random();
		int d = r.nextInt(10)+1;
		setDoor(d);
		
	}
	
	public String info() {
		
		String info = "";
		
		info = date +"     "+ time +"      ||      "+ airline +"            "+ id +"                                  "+ end +"      ||      "+ door;
		
		return info;
		
	}

	public static Comparator<Plane> compName = new Comparator<Plane>() { 
		public int compare(Plane p1, Plane p2) {
			return p1.getEnd().compareTo(p2.getEnd());
		}
	};

	@Override
	public int compareTo(Plane p) {
		return airline.compareTo(p.getAirline());
	}
	
}
