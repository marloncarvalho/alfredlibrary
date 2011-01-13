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
 * Unidade resultante = Raio
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class Raio {
	
	private Raio() {
		throw new AssertionError();

	}
	
	/**
	 * Obtém o raio pela fórmula V = 2.pi.R / T
	 * 
	 * @param velocidadeEscalar
	 * @param periodo
	 * @return
	 */
	public static double calcularPorVelocidadeEscalar (double velocidadeEscalar,
			double periodo) {
		return (velocidadeEscalar / (2 * Math.PI)) * periodo;
	}
	
	/**
	 * Obtém o raio pela fórmula ac = V^2 / R
	 * 
	 * @param velocidadeEscalar
	 * @param aceleracaoCentripeta
	 * @return
	 */
	public static double calcularPorAceleracaoCentripeta (double velocidadeEscalar,
			double aceleracaoCentripeta) {
		if (aceleracaoCentripeta != 0) {
			return Math.pow(velocidadeEscalar, 2) / aceleracaoCentripeta;
		} else {
			throw new AlfredException("Aceleração centrípeta não pode ser 0!");
		}
	}
	
}
