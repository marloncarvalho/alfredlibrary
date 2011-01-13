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
 * Unidade resultante = Frequência
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class Frequencia {
	
	private Frequencia() {
		throw new AssertionError();
	}

	/**
	 * Obtém a frequência pelo período
	 * 
	 * @param periodo
	 * @return
	 */
	public static double calcular (double periodo) {
		if (periodo != 0) {
			return 1 / periodo;
		} else {
			throw new AlfredException("Período não pode ser 0!");
		} 
	}
	
	/**
	 * Obtém a frequência pela fórmula f = n/t
	 * 
	 * @param numeroVoltas
	 * @param tempo
	 * @return
	 */
	public static double calcular (int numeroVoltas, double tempo) {
		if (tempo != 0) {
			return numeroVoltas / tempo;
		} else {
			throw new AlfredException("Tempo não pode ser 0!");
		}
	}
		
}
