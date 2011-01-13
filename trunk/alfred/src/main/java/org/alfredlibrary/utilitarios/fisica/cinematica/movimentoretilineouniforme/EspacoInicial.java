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
package org.alfredlibrary.utilitarios.fisica.cinematica.movimentoretilineouniforme;

/**
 * Utilitários para trabalhar com fórmulas de Movimento Retilíneo Uniforme.
 * Unidade resultante = Espaço Inicial
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 27/05/2010
 */
public final class EspacoInicial {
	
	private EspacoInicial() {
		throw new AssertionError();
	}

	/**
	 * Obtém o espaço inicial pela fórmula S = So + v.t 
	 * 
	 * @param espacoFinal
	 * @param velocidade
	 * @param tempo
	 * @return
	 */
	public static double calcular (double espacoFinal,
			double velocidade,	double tempo) {
		return espacoFinal - (velocidade * tempo);
	}
	
}
