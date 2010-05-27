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

/**
 * Utilitário para trabalhar com fórmulas de Movimento Circular Uniformemente Variado.
 * Unidade resultante = Ângulo Final
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class AnguloFinal {

	/**
	 * Obtém o ângulo final pela fórmula O = Oo + Wo.t + a.t^2/2
	 * 
	 * @param anguloInicial
	 * @param velocidadeAngularInicial
	 * @param tempo
	 * @param aceleracaoAngular
	 * @return
	 */
	public static double calcularPorTempo (double anguloInicial,
			double velocidadeAngularInicial, double tempo, double aceleracaoAngular) {
		return anguloInicial + (velocidadeAngularInicial * tempo) + ((aceleracaoAngular * (Math.pow(tempo, 2))) / 2);
	}
	
	/**
	 * Obtém a ângulo final pela fórmula W^2 = Wo^2 + 2.a.(O - Oo)
	 * 
	 * @param velocidadeAngularInicial
	 * @param velocidadeAngularInicial
	 * @param aceleracaoAngular
	 * @param anguloInicial
	 * @return
	 */
	public static double calcularPorAngulo (double velocidadeAngularFinal,
			double velocidadeAngularInicial, double aceleracaoAngular, 
			double anguloInicial) {
		return ((Math.pow(velocidadeAngularFinal,2) - Math.pow(velocidadeAngularInicial,2)) / (2 * aceleracaoAngular)) + anguloInicial;
	}
	
}
