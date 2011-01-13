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
package org.alfredlibrary.utilitarios.fisica.cinematica.movimentoretilineouniforme;

import org.alfredlibrary.AlfredException;

/**
 * Utilitários para trabalhar com fórmulas de Movimento Retilíneo Uniforme.
 * Unidade resultante = Velocidade
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class Velocidade {
	
	private Velocidade() {
		throw new AssertionError();
	}

	/**
	 * Obtém a velocidade pela fórmula S = So + v.t
	 * 
	 * @param espacoFinal
	 * @param espacoInicial
	 * @param tempo
	 * @return
	 */
	public static double calcular (double espacoFinal,
			double espacoInicial, double tempo) {
		if (tempo != 0) {
			return (espacoFinal - espacoInicial) / tempo;
		} else {
			throw new AlfredException("Divisão por zero!");
		}
	}
}
