package testovi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.Timeout;

import radnici.Radnik;
import radnici.Zaposleni;

@Timeout(value = 2, unit = TimeUnit.SECONDS)
class RadnikTest {

	private Radnik radnik;
	private Radnik radnik2;
	
	@BeforeAll
	public static void proveriSistem() {
		assertTrue(System.getProperty("os.name").contains("Windows"));
	}
	
	@BeforeEach
	void init() {
		radnik = new Radnik("Mile", 7, 40);
		radnik2 = new Radnik("Mile", 7, 40);
	}
	
	@TestFactory
	Collection<DynamicTest> getMetode() {
		return Arrays.asList(
				DynamicTest.dynamicTest("Test get ime", () -> assertEquals("Mile", radnik.getIme())),
				DynamicTest.dynamicTest("Test get cenaPoSatu", () -> assertEquals(7, radnik.getCenaSata())),
				DynamicTest.dynamicTest("Test get brojSati", () -> assertEquals(40, radnik.getBrSatiRada()))
				);
		
	}
	
	@Test
	void testSetCenaSata1() {
		assertEquals(7, radnik.getCenaSata());
		radnik.setCenaSata(8);
		assertEquals(8, radnik.getCenaSata());
	}
	
	@Test
	void testSetCenaSata2() {
		assertEquals(7, radnik.getCenaSata());
		assertThrows(RuntimeException.class, () -> radnik.setCenaSata(-1));
	}

	@Test
	void testSetBrSatiRada1() {
		assertEquals(40, radnik.getBrSatiRada());
		radnik.setBrSatiRada(41);
		assertEquals(41, radnik.getBrSatiRada());
	}
	
	@Test
	void testSetBrSatiRada2() {
		assertEquals(40, radnik.getBrSatiRada());
		assertThrows(RuntimeException.class, () -> radnik.setBrSatiRada(-20));
	}
	
	@Test
	void testSetBrSatiRada3() {
		assertEquals(40, radnik.getBrSatiRada());
		assertThrows(RuntimeException.class, () -> radnik.setBrSatiRada(400));
	}

	@Test
	void testSetIme1() {
		assertEquals("Mile", radnik.getIme());
		radnik.setIme("Rile");
		assertEquals("Rile", radnik.getIme());
	}
	
	@Test
	void testSetIme2() {
		assertEquals("Mile", radnik.getIme());
		assertThrows(RuntimeException.class, () -> radnik.setIme(null));
	}

	@Test
	void testIzracunajPlatu() {
		double p = radnik.getBrSatiRada() * radnik.getCenaSata() + 20000;
		assertEquals(p, radnik.izracunajPlatu());
	}

	@Test
	void testBolovanje1() {
		radnik.setBrSatiRada(0);
		radnik.setCenaSata(0);
		assertTrue(radnik.bolovanje());
	}
	
	@Test
	void testBolovanje2() {
		assertFalse(radnik.bolovanje());
	}

	@Test
	void testEqualsObject() {
		assertTrue(radnik.equals(radnik));
	}
	
	@Test
	void testEqualsObject2() {
		Zaposleni z = new Zaposleni();
		assertFalse(radnik.equals(z));
	}
	
	@Test
	void testEqualsObject3() {
		getClass();
		radnik2.getClass();
		assertTrue(radnik.equals(radnik2));
	}
	
	@Test
	void testEqualsObject4() {
		Radnik r4 = new Radnik(null, 7, 40);
		Radnik r5 = new Radnik("Mile", 7, 40);
		assertFalse(r4.equals(r5));
	}
	
	@Test
	void testEqualsObject5() {
		radnik2.setIme("Mika");
		assertFalse(radnik.equals(radnik2));
	}
	
	@Test
	void testEqualsObject6() {
		radnik2.setBrSatiRada(39);
		assertFalse(radnik.equals(radnik2));
	}
	
	@Test
	void testEqualsObject7() {
		radnik2.setCenaSata(8);
		assertFalse(radnik.equals(radnik2));
	}

}
