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
package org.alfredlibrary.utilitarios.fisica.cinematica.movimentocircularuniformementevariado;

import org.alfredlibrary.AlfredException;

/**
 * Utilitário para trabalhar com fórmulas de Movimento Circular Uniformemente Variado.
 * Unidade resultante = Velocidade Angular
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class VelocidadeAngularFinal {
	
	/**
	 * Obtém a velocidade angular pela fórmula W = Wo + a.t
	 * 
	 * @param velocidadeAngularInicial
	 * @param aceleracaoAngular
	 * @param tempo
	 * @return
	 */
	public static double calcular (double velocidadeAngularInicial,
			double aceleracaoAngular, double tempo) {
		return velocidadeAngularInicial + (aceleracaoAngular * tempo);
	}
	
	/**
	 * Obtém a velocidade angular pela fórmula W^2 = Wo^2 + 2.a.(O - Oo)
	 * 
	 * @param velocidadeAngularInicial
	 * @param aceleracaoAngular
	 * @param anguloFinal
	 * @param anguloInicial
	 * @return
	 */
	public static double calcular (double velocidadeAngularInicial,
			double aceleracaoAngular, double anguloFinal, double anguloInicial) {
		if (Math.pow(velocidadeAngularInicial,2) + 2 * aceleracaoAngular * (anguloFinal - anguloInicial) >= 0) {
			return Math.sqrt(Math.pow(velocidadeAngularInicial,2) + 2 * aceleracaoAngular * (anguloFinal - anguloInicial));
		} else {
			throw new AlfredException("O resultado não é um número racional!");
		}
	}

}
