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
 * Unidade resutlante = aceleracao angular
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class AceleracaoAngular {
	
	/**
	 * Obtém a aceleração angular pela fórmula W = Wo + a.t
	 * 
	 * @param velocidadeAngularFinal
	 * @param velocidadeAngularInicial
	 * @param tempo
	 * @return
	 */
	public static double calcular (double velocidadeAngularFinal,
			double velocidadeAngularInicial, double tempo) {
		if (tempo != 0) {
			return ((velocidadeAngularFinal - velocidadeAngularInicial) / tempo);
		} else {
			throw new AlfredException("Divisão por zero!");
		}
	}
	
	/**
	 * Obtém a aceleração angular pela fórmula W^2 = Wo^2 + 2.a.(O - Oo)
	 * 
	 * @param velocidadeAngularFinal
	 * @param velocidadeAngularInicial
	 * @param anguloFinal
	 * @param anguloInicial
	 * @return
	 */
	public static double calcular (double velocidadeAngularFinal,
			double velocidadeAngularInicial, double anguloFinal, double anguloInicial) {
		if (anguloFinal != anguloInicial) {
			return ((Math.pow(velocidadeAngularFinal,2) - Math.pow(velocidadeAngularInicial,2)) / (2 * (anguloFinal - anguloInicial)));
		} else {
			throw new AlfredException("O movimento circular não é uniformemente variado!");
		}
	}
}
