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
package org.alfredlibrary.utilitarios.fisica.cinematica.movimentoparabolico;

import org.alfredlibrary.AlfredException;

/**
 * Utilitários para trabalhar com fórmulas de Movimento Parabólico.
 * Unidade resultante = Angulação
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/05/2010
 */
public final class Angulacao {
	
	private Angulacao() {
		throw new AssertionError();
	}

	/**
	 * Obtém a angulação pela fórmula A = (Vo^2 . sen(2 * fi)) / g
	 * 
	 * @param velocidadeInicial
	 * @param alcance
	 * @param gravidade Aceleração da gravidade
	 * @return
	 */
	public static double calcular(double velocidadeInicial,
			double alcance, double gravidade) {
		if (velocidadeInicial != 0) {
			return Math.asin((alcance * gravidade) / Math.pow(velocidadeInicial, 2));
		} else {
			throw new AlfredException("Velocidade Inicial nula provoca divisão por zero!");
		}
	}
	
	/**
	 * Obtém a angulação pela fórmula t = (A . sec(fi)) / Vo
	 * 
	 * @param velocidadeInicial
	 * @param alcance
	 * @param tempo
	 * @return
	 */
	public static double calcularPorAlcance(double velocidadeInicial,
			double alcance, double tempo) {
		if (velocidadeInicial == 0) {
			throw new AlfredException("Velocidade Inicial nula provoca divisão por zero!");
		} else if (tempo == 0) {
			throw new AlfredException("Tempo nulo provoca divisão por zero!"); 
		} else {
			return Math.acos(alcance / (velocidadeInicial * tempo));
			
		}
	}
	
	/**
	 * Obtém a angulação pela fórmula t = (2.Vo.sen(fi)) / g
	 * 
	 * @param gravidade Aceleração da gravidade
	 * @param velocidadeInicial
	 * @param tempo
	 * @return
	 */
	public static double calcularPorGravidade(double gravidade,
			double velocidadeInicial, double tempo) {
		if (velocidadeInicial != 0) {
			return Math.asin((tempo * gravidade) / (2 * velocidadeInicial));
		} else {
			throw new AlfredException("Velocidade Inicial nula provoca divisão por zero!");
		}
	}
	
	/**
	 * Obtém a angulação pela fórmula Vx = Vo.cos(fi)
	 * 
	 * @param velocidadeApice
	 * @param velocidadeInicial
	 * @return
	 */
	public static double calcularPorVelocidadeApice (double velocidadeApice,
			double velocidadeInicial) {
		if (velocidadeInicial != 0) {
			return Math.acos(velocidadeApice / velocidadeInicial);
		} else {
			throw new AlfredException("Velocidade Inicial nula provoca divisão por zero!");
		}
	}
	
	/**
	 * Obtém a angulação pela fórmula Voy = Vo.sen(fi)
	 * 
	 * @param velocidadeVerticalInicial
	 * @param velocidadeInicial
	 * @return
	 */
	public static double calcularPorVelocidadeVerticalInicial (
			double velocidadeVerticalInicial, double velocidadeInicial) {
		if (velocidadeInicial != 0) {
			return Math.asin(velocidadeVerticalInicial / velocidadeInicial);
		} else {
			throw new AlfredException("Velocidade Inicial nula provoca divisão por zero!");
		}
	}
	
}
