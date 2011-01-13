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
 * Unidade resultante = Velocidade
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/05/2010
 */
public final class VelocidadeInicial {
	
	 private VelocidadeInicial() {
		 throw new AssertionError();

	}

	/**
	 * Obtém a velocidade inicial pela fórmula A = (Vo^2 . sen(2 * fi)) / g
	 * 
	 * @param alcance
	 * @param angulacao
	 * @param gravidade Aceleração da gravidade
	 * @return
	 */
	public static double calcular(double alcance,
			double angulacao, double gravidade) {
		if (Math.sin(2 * angulacao) == 0) {
			throw new AlfredException("Angulação provoca divisão por zero!");
		} else if ((alcance * gravidade) / Math.sin(2 * angulacao) < 0) {
			throw new AlfredException("Resultado irracional!");
		} else {
			return Math.sqrt((alcance * gravidade) / Math.sin(2 * angulacao));
		}
	}
	
	/**
	 * Obtém a velocidade inicial para alcance máximo pela fórmula
	 * A = (Vo^2 . sen(2 * fi)) / g
	 * Isso ocorre quando o ângulo (fi) é de 45 graus.
	 * 
	 * @param alcance
	 * @param gravidade Aceleração da gravidade
	 * @return
	 */
	public static double calcular(double alcance, double gravidade) {
		if (alcance * gravidade >= 0) {
			return Math.sqrt(alcance * gravidade);
		} else {
			throw new AlfredException("Resultado irracional!");
		}
	}
	
	/**
	 * Obtém a velocidade inicial pela fórmula t = (A . sec(fi)) / Vo
	 * 
	 * @param alcance
	 * @param angulacao
	 * @param tempo
	 * @return
	 */
	public static double calcularPorAlcance(double alcance,
			double angulacao, double tempo) {
		if (tempo == 0) {
			throw new AlfredException("Tempo nulo provoca divisão por zero!");
		} else if (Math.cos(angulacao) == 0) {
			throw new AlfredException("A angulação provoca divisão por zero!");
		} else {
			return ((alcance * Math.pow(Math.cos(angulacao), -1)) / tempo);
		}
	}
	
	/**
	 * Obtém a velocidade inicial pela fórmula t = (2.Vo.sen(fi)) / g
	 * 
	 * @param gravidade Aceleração da gravidade
	 * @param angulacao
	 * @param tempo
	 * @return
	 */
	public static double calcularPorGravidade(double gravidade,
			double angulacao, double tempo) {
		if (Math.sin(angulacao) != 0) {
			return (tempo * gravidade) / (2 * Math.sin(angulacao));
		} else {
			throw new AlfredException("Angulação provoca divisão por zero!");
		}
	}
	
	/**
	 * Obtém a velocidade inicial pela fórmula Vx = Vo.cos(fi)
	 * 
	 * @param velocidadeHorizontal
	 * @param angulacao
	 * @return
	 */
	public static double calcularPorApice (double velocidadeHorizontal,
			double angulacao) {
		if (Math.cos(angulacao) != 0) {
			return velocidadeHorizontal / Math.cos(angulacao);
		} else {
			throw new AlfredException("Angulação provoca divisão por zero!");
		}
	}
	
	/**
	 * Obtém a velocidade inicial pela fórmula Voy = Vo.sen(fi)
	 * 
	 * @param velocidadeVerticalInicial
	 * @param angulacao
	 * @return
	 */
	public static double calcularPorVertical (double velocidadeVerticalInicial,
			double angulacao) {
		if (Math.sin(angulacao) != 0) {
			return velocidadeVerticalInicial / Math.sin(angulacao);
		} else {
			throw new AlfredException("Angulação provoca divisão por zero!");
		}
	}
	
	/**
	 * Obtém a velocidade inicial vertical pela fórmula Voy = Vo.sen(fi)
	 * 
	 * @param velocidadeVerticalInicial
	 * @param angulacao
	 * @return
	 */
	public static double calcularVertical (double velocidadeInicial,
			double angulacao) {
		return velocidadeInicial * Math.sin(angulacao);
	}
}
