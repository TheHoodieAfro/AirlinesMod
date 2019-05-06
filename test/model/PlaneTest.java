package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class PlaneTest {

	private Plane pl;
	
	private void setUpEscenario1() {
		try {
			pl = new Plane(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
