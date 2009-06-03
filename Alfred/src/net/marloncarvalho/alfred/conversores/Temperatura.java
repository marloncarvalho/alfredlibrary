/*
 *  This file is part of Alfred Library.
 *
 *  Foobar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Alfred Library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.marloncarvalho.alfred.conversores;

/**
 * Classe utilitária para conversão de temperaturas.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Temperatura {
	
	private Temperatura() {}
	
	/**
	 * Converter de Celcius para Fahrenheiht.
	 * Código "gentilmente" sugado do blog http://discomoose.org/2005/12/27/temperature-conversion-program-in-java/.
	 * 
	 * @param degCelcius Temperatura em Celcius.
	 * @return Temperatura em Fahrenheit.
	 */
	public static float converterCelciusFahrenheit(float degCelcius) {
		float degFahrenheit;
		degFahrenheit = degCelcius * 9 / 5 + 32;
		return degFahrenheit;
	}

	/**
	 * Converter de Fahrenheit para Celcius.
	 * Código "gentilmente" sugado do blog http://discomoose.org/2005/12/27/temperature-conversion-program-in-java/.
	 * 
	 * @param degFahrenheit Temperatura em Farenheit.
	 * @return Temperatura em Celcius.
	 */
	public static float converterFahrenheitCelcius(float degFahrenheit) {
		float degCelcius;
		degCelcius = (degFahrenheit - 32) * 5 / 9;
		return degCelcius;
	}

	/**
	 * Converter de Fahrenheit para Kelvin.
	 * 
	 * @param f Temperatura em Fahrenheit.
	 * @return Temperatura em Kelvin.
	 */
	public static float converterFahrenheitKelvin(float f) {
		float celcius = converterFahrenheitCelcius(f);
		return converterCelciusKelvin(celcius);
	}

	/**
	 * Converter uma temperatura de Kelvin para Fahrenheit.
	 * 
	 * @param k Temperatura em Kelvin.
	 * @return Temperatura em Fahrenheit.
	 */
	public static float converterKelvinFahrenheit(float k) {
		float celcius = converterKelvinCelcius(k);
		return converterCelciusFahrenheit(celcius);
	}
	
	/**
	 * Converter de Celcius para Kelvin.
	 * Código "gentilmente" sugado do blog http://discomoose.org/2005/12/27/temperature-conversion-program-in-java/.
	 * 
	 * @param degCelcius Temperatura em Celcius.
	 * @return Temperatura em Kelvin.
	 */
	public static float converterCelciusKelvin(float degCelcius) {
		float degKelvin;
		degKelvin = degCelcius + 273.15f;
		return degKelvin;
	}

	/**
	 * Converter de Kelvin para Celcius.
	 * Código "gentilmente" sugado do blog http://discomoose.org/2005/12/27/temperature-conversion-program-in-java/.
	 *  
	 * @param degKelvin Temperatura em Kelvin.
	 * @return Temperatura em Celcius.
	 */
	public static float converterKelvinCelcius(float degKelvin) {
		float degCelcius;
		degCelcius = degKelvin - 273.15f;
		return degCelcius;
	}

}