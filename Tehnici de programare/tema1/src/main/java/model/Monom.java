package model;

public class Monom {

	private Double coefficient;
	private Integer exponent;

	public Monom(String monom) {
		if (!monom.contains("+") && !monom.contains("-"))
			monom = "+" + monom;
		String parts[] = monom.split("x(\\^)?");
		if (monom.contains("x^")) {
			String exponent = parts[1].toString();
			this.setExponent(Integer.parseInt(exponent));
		} else if (monom.contains("x"))
			this.setExponent(1);
		else
			this.setExponent(0);
		if (parts[0].length() == 1)
			if (parts[0].equals("+")) {
				this.setCoefficient((double) 1);
			} else {
				this.setCoefficient((double) -1);
			}
		else
			this.setCoefficient(Double.parseDouble(parts[0]));
	}

	public Monom(Double coef1, Integer pow1) {
		this.coefficient = coef1;
		this.exponent = pow1;
	}

	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	public Integer getExponent() {
		return exponent;
	}

	public void setExponent(Integer exponent) {
		this.exponent = exponent;
	}

	public static boolean validateMonom(String monom) {// valideaza stringul primit ca parametru

		if (monom.matches("(\\+|-)?([0-9]+)?(x(\\^[0-9])?)?") && (!monom.endsWith("+") && !monom.endsWith("-"))) {
			return true;
		} else
			return false;
	}
}
