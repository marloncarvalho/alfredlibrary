/*
 *  This file is part of Alfred Library.
 *
 *  Alfred Library is free software: you can redistribute it and/or modify
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
 *  along with Alfred Library.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.marloncarvalho.alfred.conversores;

/**
 * Classe para conversão entre medidas de massa.
 * 
 * @author Marlon Silva Carvalho
 * @since 07/06/2009
 */
final public class Massa {

	private Massa() {}

	/**
	 * Converter Quilograma em Grama.
	 * 
	 * @param kg Quilograma.
	 * @return Grama.
	 */
	public static double converterKgEmGrama(double kg) {
		return (kg*1000);
	}

	/**
	 * Converter Grama em Quilograma.
	 * 
	 * @param g Grama.
	 * @return Quilograma.
	 */
	public static double converterGramaEmKg(double g) {
		return (g/1000);
	}

}