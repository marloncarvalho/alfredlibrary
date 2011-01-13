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
 * Unidade resultante = Tempo decorrido
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/05/2010
 */
public final class Tempo {
	
	private Tempo() {
		throw new AssertionError();
	}
	
	/**
	 * Obtém o tempo decorrido pela fórmula t = (2.Vo.sen(fi)) / g
	 * 
	 * @param velocidadeInicial
	 * @param angulacao
	 * @param gravidade
	 * @return
	 */
	public static double calcular(double velocidadeInicial,
			double angulacao, double gravidade) {
		if (gravidade != 0) {
			return (2 * velocidadeInicial * Math.sin(angulacao)) / gravidade;
		} else {
			throw new AlfredException("Tempo nulo provoca divisão por zero!");
		}
	}
	
	/**
	 * Obtém o tempo decorrido pela fórmula t = (A . sec(fi)) / Vo
	 * 
	 * @param velocidadeInicial
	 * @param angulacao
	 * @param alcance
	 * @return
	 */
	public static double calcularPorAlcance(double velocidadeInicial,
			double angulacao, double alcance) {
		if (Math.cos(angulacao) == 0) {
			throw new AlfredException("A angulação provoca divisão por zero!");
		} else if (velocidadeInicial == 0) {
			throw new AlfredException("A velocidade de partida nula provoca divisão por zero!");
		} else {
			return (alcance * Math.pow(Math.cos(angulacao), -1)) / velocidadeInicial;
		}
	}
}
