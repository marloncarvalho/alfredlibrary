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
 * Utilitário para trabalhar com fórmulas de Movimento Retilíneo Uniformemente Variado.
 * Unidade resultante = Ângulo Inicial
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class AnguloInicial {
	
	/**
	 * Obtém o ângulo inicial pela fórmula O = Oo + Wo.t + a.t^2/2 
	 * 
	 * @param anguloFinal
	 * @param velocidadeAngularInicial
	 * @param tempo
	 * @param aceleracaoAngular
	 * @return
	 */
	public static double calcularPorTempo (double anguloFinal,
			double velocidadeAngularInicial, double tempo, double aceleracaoAngular) {
		return anguloFinal - (velocidadeAngularInicial * tempo) - ((aceleracaoAngular * (Math.pow(tempo, 2))) / 2);
	}
	
	/**
	 * Obtém a ângulo inicial pela fórmula W^2 = Wo^2 + 2.a.(O - Oo)
	 * 
	 * @param velocidadeAngularInicial
	 * @param velocidadeAngularInicial
	 * @param aceleracaoAngular
	 * @param anguloFinal
	 * @return
	 */
	public static double calcularPorAngulo (double velocidadeAngularFinal,
			double velocidadeAngularInicial, double aceleracaoAngular, 
			double anguloFinal) {
		return anguloFinal - ((Math.pow(velocidadeAngularFinal,2) - Math.pow(velocidadeAngularInicial,2)) / (2 * aceleracaoAngular));
	}
}
