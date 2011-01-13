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

import org.alfredlibrary.AlfredException;

/**
 * Utilitário para resolver equações do segundo grau.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class EquacaoSegundoGrau {
	
	private EquacaoSegundoGrau() {
		throw new AssertionError();
	}
	
	public static double[] resolverRacional (double a, double b, double c) {
		double delta = Math.pow(b, 2) - (4 * a * c);
		if (a == 0) {
			throw new AlfredException("A equação não é do segundo grau!");
		} else if (delta < 0) {
			throw new AlfredException("A equação tem raízes irracionais!");
		} else {
			double[] resultado = new double[2];
			resultado[0] = (- b - Math.sqrt(delta)) / (2 * a);
			resultado[1] = (- b + Math.sqrt(delta)) / (2 * a);
			return resultado;
		}
	}

}
