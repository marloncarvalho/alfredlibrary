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
package org.alfredlibrary.utilitarios.fisica.cinematica.movimentocircularuniforme;

import org.alfredlibrary.AlfredException;

/**
 * Utilitários para trabalhar com fórmulas de Movimento Circular Uniforme.
 * Unidade resultante = Período
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class Periodo {
	
	private Periodo() {
		throw new AssertionError();
	}

	/**
	 * Obtém o período pela frequência
	 * 
	 * @param frequencia
	 * @return
	 */
	public static double calcularPorFrequencia (double frequencia) {
		if (frequencia != 0) {
			return 1 / frequencia;
		} else {
			throw new AlfredException("Frequência não pode ser 0!");
		} 
	}
	
	/**
	 * Obtém o perído pela fórmula V = 2.pi.R / T
	 * 
	 * @param velocidadeEscalar
	 * @param raio
	 * @return
	 */
	public static double calcular (double velocidadeEscalar,
			double raio) {
		if (velocidadeEscalar != 0) {
			return (2 * Math.PI * raio) / velocidadeEscalar;
		} else {
			throw new AlfredException("Velocidade escalar não pode ser 0!");
		}
	}
	
	/**
	 * Obter o período pela fórmula W = 2.pi/T
	 * 
	 * @param velocidadeAngular
	 * @return
	 */
	public static double calcularPorVelocidadeAngular (double velocidadeAngular) {
		if (velocidadeAngular != 0) {
			return (2 * Math.PI) / velocidadeAngular;
		} else {
			throw new AlfredException("Velocidade Angular não pode ser 0!");
		}
	}
			
}
