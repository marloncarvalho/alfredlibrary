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
 * Unidade resultante = Gravidade
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/05/2010
 */
public final class Gravidade {
	
	private Gravidade() {
		throw new AssertionError();
	}

	/**
	 * Obtém a aceleração da gravidade pela fórmula A = (Vo^2 . sen(2 * fi)) / g
	 * 
	 * @param velocidadeInicial
	 * @param angulacao
	 * @param alcance
	 * @return
	 */
	public static double calcular(double velocidadeInicial,
			double angulacao, double alcance) {
		if (alcance != 0) {
			return (Math.pow(velocidadeInicial, 2) * Math.sin(2 * angulacao)) / alcance;
		} else {
			throw new AlfredException("Alcance nulo provoca divisão por zero!");
		}
	}
	
	/**
	 * Obtém a aceleracao da gravidade pelo máximo alcance possível pela fórmula
	 * A = (Vo^2 . sen(2 * fi)) / g
	 * Isso ocorre quando o ângulo (fi) é de 45 graus.
	 * 
	 * @param velocidadeInicial
	 * @param gravidade Aceleração da gravidade
	 * @return
	 */
	public static double calcular(double velocidadeInicial, double alcance) {
		if (alcance != 0) {
			return Math.pow(velocidadeInicial, 2) / alcance;
		} else {
			throw new AlfredException("Alcance nulo provoca divisão por zero!");
		}
	}
	
	/**
	 * Obtém a aceleração da gravidade pela fórmula t = (2.Vo.sen(fi)) / g
	 * 
	 * @param velocidadeInicial
	 * @param angulacao
	 * @param tempo
	 * @return
	 */
	public static double calcularPorTempo(double velocidadeInicial,
			double angulacao, double tempo) {
		if (tempo != 0) {
			return (2 * velocidadeInicial * Math.sin(angulacao)) / tempo;
		} else {
			throw new AlfredException("Tempo nulo provoca divisão por zero!");
		}
	}
}
