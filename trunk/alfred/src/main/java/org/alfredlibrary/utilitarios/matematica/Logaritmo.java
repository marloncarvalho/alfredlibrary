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
package org.alfredlibrary.utilitarios.matematica;

/**
 * Utilitário para cálculos envolvendo logaritmos
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 03/10/2010
 */
public final class Logaritmo {
	
	private Logaritmo() {
		throw new AssertionError();
	}

	/**
	 * Faz a mudança de base para obter o logaritmo de base não-neperiana
	 * 
	 * @param valor Valor numérico que representa o resultado da exponenciação
	 * 				inversa do logaritmo 
	 * @param base Base em que se deseja calcular o logaritmo
	 * @return Logaritmo na base escolhida
	 */
	public static double obter(double valor, double base) {
		return Math.log(valor) / Math.log(base);
	}
}
