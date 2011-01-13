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
package org.alfredlibrary.utilitarios.fisica.cinematica.movimentoretilineouniformementevariado;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.matematica.EquacaoSegundoGrau;

/**
 * Utilitário para trabalhar com fórmulas de Movimento Retilíneo Uniformemente Variado.
 * Unidade resultante = Tempo
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class Tempo {
	
	private Tempo() {
		throw new AssertionError();
	}
	
	/**
	 * Obtém o tempo pela fórmula S = So + vo.t + a.t^2/2
	 * 
	 * @param espacoFinal
	 * @param espacoInicial
	 * @param velocidadeInicial
	 * @param aceleracao
	 * @return
	 */
	public static double[] calcular (double espacoFinal,
			double espacoInicial, double velocidadeInicial,
			double aceleracao) {
		if (velocidadeInicial != 0) {
			return EquacaoSegundoGrau.resolverRacional(aceleracao / 2,
					velocidadeInicial, espacoInicial - espacoFinal);
		} else {
			throw new AlfredException("Divisão por zero!");
		}
	}
	
	/**
	 * Obtém o tempo pela fórmula V = Vo + a.t
	 * 
	 * @param velocidadeFinal
	 * @param velocidadeInicial
	 * @param aceleracao
	 * @return
	 */
	public static double calcular (double velocidadeFinal,
			double velocidadeInicial, double aceleracao) {
		if (aceleracao != 0) {
			return (velocidadeFinal - velocidadeInicial) / aceleracao;
		} else {
			throw new AlfredException("Divisão por zero!");
		}
	}
}
