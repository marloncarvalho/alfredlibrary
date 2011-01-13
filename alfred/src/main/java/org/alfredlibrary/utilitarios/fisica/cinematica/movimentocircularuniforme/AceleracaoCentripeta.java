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
package org.alfredlibrary.utilitarios.fisica.cinematica.movimentocircularuniforme;

import org.alfredlibrary.AlfredException;

/**
 * Utilitários para trabalhar com fórmulas de Movimento Circular Uniforme.
 * Unidade resultante = Aceleração Centrípeta
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class AceleracaoCentripeta {
	
	private AceleracaoCentripeta() {
		throw new AssertionError();
	}
	
	/**
	 * Obtém a aceleração centrípeta pela fórmula ac = V^2 / R
	 * 
	 * @param raio
	 * @param velocidadeEscalar
	 * @return
	 */
	public static double calcular (double raio, double velocidadeEscalar) {
		if (raio != 0) {
			return (Math.pow(velocidadeEscalar, 2)) / raio;
		} else {
			throw new AlfredException("Raio não pode ser 0!");
		}
	}
		
}
