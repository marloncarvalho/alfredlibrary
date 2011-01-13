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
import org.alfredlibrary.utilitarios.fisica.cinematica.movimentoretilineouniformementevariado.EspacoFinal;

/**
 * Utilitários para trabalhar com fórmulas de Movimento Parabólico.
 * Unidade Resultante = Posição
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/05/2010
 */
public final class Posicao {
	
	private Posicao() {
		throw new AssertionError();
	}

	/**
	 * Obtém a posição pela composição de Alcance (eixo X, Movimento Parabólico)
	 * e Espaco Final (eixo Y, MRUV)
	 * 
	 * @param velocidadeInicial
	 * @param angulacao
	 * @param gravidade Aceleração da gravidade
	 * @param posicao Posição inicial do objeto
	 * @param tempo
	 * @return
	 */
	public static double[] calcular(double velocidadeInicial,
			double angulacao, double gravidade, double[] posicao,
			double tempo) {
		if (posicao.length == 2) {
			double[] resultado = new double[2];
			resultado[0] = EspacoFinal.calcular(posicao[0],
					VelocidadeInicial.calcularVertical(velocidadeInicial, angulacao),
					tempo, gravidade);
			resultado[1] = Alcance.calcular(velocidadeInicial, angulacao, gravidade);
			return resultado;
		} else {
			throw new AlfredException("Posição deve ser dada no plano Cartesiano!" +
					"\nO parâmetro informado tem " + posicao.length + " dimensões!");
		}
	}
	
}
