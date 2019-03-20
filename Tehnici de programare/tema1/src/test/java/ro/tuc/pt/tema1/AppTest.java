package ro.tuc.pt.tema1;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.Polinom;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		Polinom polinom1 = new Polinom();
		Polinom polinom2 = new Polinom();
		// daca user ul introduce un format invalid
		assertFalse(polinom1.validatePolynomial("x2--"));
		//daca userul nu introduce nimic si selecteaza o operatie
		assertFalse(polinom1.validatePolynomial("") && polinom2.validatePolynomial(""));
		// adunare
		polinom1.validatePolynomial("x^2-1");
		polinom2.validatePolynomial("x-1");
		assertTrue(polinom1.addition(polinom2).equals("x^2+x-2"));
		//scadere
		polinom1.validatePolynomial("x^3");
		polinom2.validatePolynomial("x-1");
		assertTrue(polinom1.subtraction(polinom2).equals("x^3-x+1"));
		//inmultire
		polinom1.validatePolynomial("x-1");
		polinom2.validatePolynomial("x+1");
		assertTrue(polinom1.multiplication(polinom2).equals("x^2-1"));
		//impartire
		polinom1.validatePolynomial("x^2-1");
		polinom2.validatePolynomial("x-1");
		assertTrue(polinom1.division(polinom2).equals("x+1"));
		//impartire 0 cu ceva
		polinom1.validatePolynomial("0");
		polinom2.validatePolynomial("x-1");
		assertTrue(polinom1.division(polinom2).equals("0"));
		//impartire cu 0
		polinom1.validatePolynomial("x");
		polinom2.validatePolynomial("0");
		assertTrue(polinom1.division(polinom2).equals("Operatie ilegala!"));
		//derivare
		polinom1.validatePolynomial("x^3-x+1");
		assertTrue(polinom1.derivative().equals("3x^2-1"));
		//integrare
		polinom1.validatePolynomial("x^3-x+1");
		assertTrue(polinom1.integration().equals("0.25x^4-0.5x^2+x"));
	}
}
