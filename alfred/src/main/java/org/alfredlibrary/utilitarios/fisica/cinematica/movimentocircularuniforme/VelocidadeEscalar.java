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
 * Unidade resultante = Velocidade Escalar
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class VelocidadeEscalar {

	private VelocidadeEscalar() {
		throw new AssertionError();
	}
	
	/**
	 * Obtém a velocidade escalar pela fórmula V = 2.pi.R / T
	 * 
	 * @param raio
	 * @param periodo
	 * @return
	 */
	public static double calcularPorPeriodo (double raio, double periodo) {
		if (periodo != 0) {
			return (2 * Math.PI * raio) / periodo;
		} else {
			throw new AlfredException("Período não pode ser 0!");
		}
	}
	
	/**
	 * Obtém a velocidade escalar pela fórmula V = W.R
	 * 
	 * @param raio
	 * @param velocidadeAngular
	 * @return
	 */
	public static double calcularPorVelocidadeAngular (double raio, double velocidadeAngular) {
		return raio * velocidadeAngular;
	}
	
	/**
	 * Obtém a velocidade escalar pela fórmula ac = V^2 / R
	 * 
	 * @param raio
	 * @param aceleracaoCentripeta
	 * @return
	 */
	public static double calcularPorAceleracaoCentripeta (double raio, double aceleracaoCentripeta) {
		if (aceleracaoCentripeta * raio >= 0) {
			return Math.sqrt(aceleracaoCentripeta * raio);
		} else {
			throw new AlfredException("Produto de aceleração centrípeta e raio não pode ser negativo!");
		}
	}
	
}
