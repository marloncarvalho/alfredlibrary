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
import org.alfredlibrary.utilitarios.matematica.EquacaoSegundoGrau;

/**
 * Utilitário para trabalhar com fórmulas de Movimento Circular Uniformemente Variado.
 * Unidade resultante = Tempo
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class Tempo {
	
	/**
	 * Obtém o tempo pela fórmula O = Oo + Wo.t + a.t^2/2
	 * 
	 * @param anguloFinal
	 * @param anguloInicial
	 * @param velocidadeAngularInicial
	 * @param aceleracaoAngular
	 * @return
	 */
	public static double[] calcular (double anguloFinal,
			double anguloInicial, double velocidadeAngularInicial,
			double aceleracaoAngular) {
		if (velocidadeAngularInicial != 0) {
			return EquacaoSegundoGrau.resolverRacional(aceleracaoAngular / 2,
					velocidadeAngularInicial, anguloInicial - anguloFinal);
		} else {
			throw new AlfredException("Divisão por zero!");
		}
	}
}
