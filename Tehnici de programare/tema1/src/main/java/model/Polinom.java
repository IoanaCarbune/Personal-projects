package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Polinom {

	private ArrayList<Monom> monoms = new ArrayList<Monom>();

	public ArrayList<Monom> getMonoms() {
		return monoms;
	}

	public void setMonoms(ArrayList<Monom> monoms) {
		this.monoms = monoms;
	}

	public String addition(Polinom secondPolinom) {// adunare
		Polinom result = new Polinom();
		for (Monom currentMonom : this.monoms) {
			result.addMonoms(currentMonom);
		}
		for (Monom currentMonom : secondPolinom.monoms) {
			result.addMonoms(currentMonom);
		}
		return result.polynomialToString();
	}

	public String subtraction(Polinom secondPolinom) {// scadere
		Polinom result = new Polinom();
		for (Monom currentMonom : this.monoms) {
			currentMonom.setCoefficient(currentMonom.getCoefficient());
			result.addMonoms(currentMonom);
		}
		for (Monom currentMonom : secondPolinom.monoms) {
			currentMonom.setCoefficient(-currentMonom.getCoefficient());
			result.addMonoms(currentMonom);
		}
		return result.polynomialToString();
	}

	public String multiplication(Polinom secondPolinom) {// inmultire
		Polinom result = new Polinom();
		for (Monom currentMonom : this.monoms) {
			for (Monom secondMonom : secondPolinom.monoms) {
				Double newCoefficient = new Double(0);
				Integer newExponent = new Integer(0);
				newCoefficient = currentMonom.getCoefficient() * secondMonom.getCoefficient();
				newExponent = currentMonom.getExponent() + secondMonom.getExponent();
				Monom newMonom = new Monom(newCoefficient, newExponent);
				result.addMonoms(newMonom);
			}
		}
		return result.polynomialToString();
	}

	public String division(Polinom secondPolinom) {
		Polinom result = new Polinom();
		if (secondPolinom.monoms.size() == 0 )// daca se face impartire la 0
			return "Operatie ilegala!";
		if (this.monoms.size() == 0)// daca primul polinom e 0
			return "0";
		if ((secondPolinom.monoms.get(0).getExponent() == 0 || this.monoms.get(0).getExponent() == 0)&& !secondPolinom.polynomialToString().equals("1"))// daca unul dintre cele doua polinoame e constanta
			return this.polynomialToString() + "/" + secondPolinom.polynomialToString();
		while (this.monoms.get(0).getExponent() >= secondPolinom.monoms.get(0).getExponent() ) {
			Double newCoefficient = this.monoms.get(0).getCoefficient() / secondPolinom.monoms.get(0).getCoefficient();
			Integer newExponent = this.monoms.get(0).getExponent() - secondPolinom.monoms.get(0).getExponent();
			result.addMonoms(new Monom(newCoefficient, newExponent));
			Polinom newPolinom = new Polinom();
			newPolinom.addMonoms(new Monom(newCoefficient, newExponent));
			String str = newPolinom.multiplication(secondPolinom);
			newPolinom.validatePolynomial(str);
			str = this.subtraction(newPolinom);
			this.validatePolynomial(str);
			if(secondPolinom.monoms.get(0).getExponent()== 0) break;
			if (this.monoms.size() == 0)
				break;
		}
		if (this.polynomialToString().equals("0") )// daca impartirea se face exact rest=0
			return result.polynomialToString();
		else
			return result.polynomialToString() + "\t rest=" + this.polynomialToString() + "/"
					+ secondPolinom.polynomialToString();
	}

	public String derivative() {// derivare
		Polinom result = new Polinom();
		for (Monom currentMonom : this.monoms) {
			Double newCoeffcient = new Double(0);
			Integer newExponent = new Integer(0);
			newCoeffcient = currentMonom.getCoefficient() * currentMonom.getExponent();
			newExponent = currentMonom.getExponent() - 1;
			Monom newMonom = new Monom(newCoeffcient, newExponent);
			result.addMonoms(newMonom);
		}
		return result.polynomialToString();
	}

	public String integration() {// integrare
		Polinom result = new Polinom();
		for (Monom currentMonom : this.monoms) {
			Double newCoefficient = new Double(0);
			Integer newExponent = new Integer(0);
			newCoefficient = (double) (currentMonom.getCoefficient() / (currentMonom.getExponent() + 1));
			newExponent = currentMonom.getExponent() + 1;
			Monom newMonom = new Monom(newCoefficient, newExponent);
			result.addMonoms(newMonom);
		}
		return result.polynomialToString();
	}

	private void addMonoms(Monom potentialMonom) {// adauga monom nou la lista de monoame
		int ok = 1; // daca mai exista unul cu acelasi exponent se aduna in acelasi monom
		for (Monom currentMonom : this.monoms) {
			if (currentMonom.getExponent() == potentialMonom.getExponent()) {
				Double newCoefficient = currentMonom.getCoefficient() + potentialMonom.getCoefficient();
				if (currentMonom.getCoefficient() == -potentialMonom.getCoefficient())
					currentMonom.setCoefficient((double) 0);
				else
					currentMonom.setCoefficient(newCoefficient);
				ok = 0;
				break;
			}
		}
		if (ok == 1 && !potentialMonom.getCoefficient().equals((double) 0))// nu adauga monoame cu coeficienti 0
			this.monoms.add(potentialMonom);

	}


	private String polynomialToString() {// transforma un polionom in string cu scopul de a-l afisa
		String stringResult = new String();
		int firstMonom = 1; // folosit pentru a evita afisari de genul +x sau 0x
		if(this.monoms.size()==0) return"0";
		this.sortPolynomial();
		for (Monom currentMonom : this.monoms) {
			String currentCoefficient = new String();
			if (Math.floor(currentMonom.getCoefficient()) == currentMonom.getCoefficient()) {
				currentCoefficient += currentMonom.getCoefficient().intValue();
			} else  currentCoefficient += currentMonom.getCoefficient().toString();
			String currentExponent = currentMonom.getExponent().toString();
			if (currentCoefficient.equals("0")) // verific toate cazurile posibile pentru a afisa corespunzator
				if (monoms.size() == 1)	stringResult += "0";
				else	stringResult += "";
			else if (!currentCoefficient.equals("0") && !currentCoefficient.contains("-")) {
				if (firstMonom == 1 || stringResult.length() == 0) stringResult += "";
				else stringResult += "+";
			} else if (currentCoefficient.equals("-1"))	stringResult += "-";
			if (currentExponent.equals("0") && !currentCoefficient.equals("0")) {
				if (!currentCoefficient.equals("1") && !currentCoefficient.equals("-1"))
					stringResult += currentCoefficient;
				else stringResult += "1";
			} else if (currentExponent.equals("1") && !currentCoefficient.equals("0"))
				if (currentCoefficient.equals("1") || currentCoefficient.equals("-1"))
					stringResult += "x";
				else stringResult += currentCoefficient + "x";
			else if (!currentExponent.equals("0") && !currentExponent.equals("1") && !currentCoefficient.equals("0"))
				if (currentCoefficient.equals("1") || currentCoefficient.equals("-1")) stringResult += "x^" + currentExponent;
				else stringResult += currentCoefficient + "x^" + currentExponent;
			if (stringResult.length() == 0) stringResult += "0";
			firstMonom = 0;//dupa afisarea primului monom 
		}
		return stringResult;
	}

	/*
	 * stabileste daca stringul primit ca parametru respecta formatul unui polinom
	 * verificarea consta in "spargerea" stringului in substringuri fiecare
	 * verificam monom cu monom, daca unul nu respecta formatul atunci intregul
	 * polinom nu-l respecta
	 * daca s-a trecut de toate verificarile incarcam noul polinom in this
	 */
	public boolean validatePolynomial(String string) {
		if (string.length() == 0) 	
				return false;				
		this.monoms = new ArrayList<Monom>(); 
		String[] monoms = string.split("(?=\\+)|(?=\\-)");
		int ok = 1;
		for (String currentMonom : monoms) {
			if (Monom.validateMonom(currentMonom) == false)
				ok = 0;
		}
		if (ok == 1) {
			for (String monom : monoms) {
				this.addMonoms(new Monom(monom));
			}
			this.sortPolynomial();// aducem polionmul la forma canonica
			return true;
		} else
			return false;

	}

	private void sortPolynomial() {// sorteaza monoamele dupa exponent
		Collections.sort(this.monoms, new Comparator<Monom>() {

			@Override
			public int compare(Monom o1, Monom o2) {
				return o2.getExponent().compareTo(o1.getExponent());
			}
		});
	}

}