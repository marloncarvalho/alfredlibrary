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
 * Unidade Resultante = Alcance
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/05/2010
 */
public final class Alcance {
	
	private Alcance() {
		throw new AssertionError();
	}

	/**
	 * Obtém o alcance pela fórmula A = (Vo^2 . sen(2 * fi)) / g
	 * 
	 * @param velocidadeInicial
	 * @param angulacao
	 * @param gravidade Aceleração da gravidade
	 * @return
	 */
	public static double calcular(double velocidadeInicial,
			double angulacao, double gravidade) {
		if (gravidade != 0) {
			return (Math.pow(velocidadeInicial, 2) * Math.sin(2 * angulacao)) / gravidade;
		} else {
			throw new AlfredException("Gravidade nula provoca divisão por zero!");
		}
	}
	
	/**
	 * Obtém o alcance máximo pela fórmula A = (Vo^2 . sen(2 * fi)) / g
	 * Isso ocorre quando o ângulo (fi) é de 45 graus.
	 * 
	 * @param velocidadeInicial
	 * @param gravidade Aceleração da gravidade
	 * @return
	 */
	public static double calcular(double velocidadeInicial, double gravidade) {
		if (gravidade != 0) {
			return Math.pow(velocidadeInicial, 2) / gravidade;
		} else {
			throw new AlfredException("Gravidade nula provoca divisão por zero!");
		}
	}
	
	/**
	 * Obtém o alcance pela fórmula t = (A . sec(fi)) / Vo
	 * 
	 * @param velocidadeInicial
	 * @param angulacao
	 * @param tempo
	 * @return
	 */
	public static double calcularPorTempo(double velocidadeInicial,
			double angulacao, double tempo) {
		if (Math.cos(angulacao) != 0) {
			return (velocidadeInicial * tempo) / Math.pow(Math.cos(angulacao), -1);
		} else {
			throw new AlfredException("A angulação provoca divisão por zero!");
		}
	}

}
