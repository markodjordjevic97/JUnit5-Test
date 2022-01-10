package testovi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import radnici.Radnik;
import radnici.Zaposleni;

@Timeout(value = 2, unit = TimeUnit.SECONDS)
class ZaposleniTest {
	
	private Zaposleni z;
	
	@BeforeAll
	public static void proveriSistem() {
		assertTrue(System.getProperty("os.name").contains("Windows"));
	}
	
	@BeforeEach
	void init() {
		z = new Zaposleni();
	}
	
	@AfterEach
	void destroy() {
		z = null;
	}

	@ParameterizedTest
	@MethodSource("dodavanjeRadnika")
	void testDodajRadnik(Radnik a) {
		if(a == null) {
			assertThrows(NullPointerException.class, () -> Zaposleni.dodajRadnik(a));
		}
		else if(Zaposleni.radnik.contains(a)) {
			assertThrows(RuntimeException.class, () -> Zaposleni.dodajRadnik(a));
		}
		else {
			assertFalse(Zaposleni.radnik.contains(a));
			Zaposleni.dodajRadnik(a);
			assertTrue(Zaposleni.radnik.contains(a));
		}
	}

	@ParameterizedTest
	@MethodSource("pronalazenjeRadnika")
	void testPronadjiRadnik(String ime) {
		if(ime == null) {
			assertNull(Zaposleni.pronadjiRadnik(ime));
		}
		else {
			LinkedList<Radnik> novaLista = new LinkedList<Radnik>();
			for(int i = 0; i < Zaposleni.radnik.size(); i++) {
				if(Zaposleni.radnik.get(i).getIme().equals(ime)) {
					novaLista.add(Zaposleni.radnik.get(i));
				}
			}
			assertEquals(novaLista, Zaposleni.pronadjiRadnik(ime));
		}
	}
	
	private static Stream<Arguments> dodavanjeRadnika() {
		return Stream.of(
				Arguments.of(new Radnik("", 7, 40)),
				Arguments.of(new Radnik("Mile", 8, 39)),
				Arguments.of(new Radnik("Rile", 10, 35)),
				Arguments.of(new Radnik("Miki", 11, 40))
				);
	}
	
	private static Stream<Arguments> pronalazenjeRadnika(){
		return Stream.of(
				Arguments.of(""),
				Arguments.of("Pera"),
				Arguments.of("Mika"),
				Arguments.of("Rade")
				);
	}

}
