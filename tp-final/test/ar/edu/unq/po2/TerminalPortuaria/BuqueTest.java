package ar.edu.unq.po2.TerminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuqueTest {

	
	Point point1;
	Point point2;
	
	@BeforeEach
	void setUp() throws Exception {
		point1 = new Point(0, 0);
		point2 = new Point(2, 2);
	}

	@Test
	void test() {
		assertTrue(Point.distance(point1.getX(), point1.getY(), point2.getX(), point2.getY()) < 3);
	}

}
