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

/**
 * Utilitários para trabalhar com fórmulas de Movimento Parabólico.
 * Unidade resultante = Velocidade no topo da parábola
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 28/05/2010
 */
public final class VelocidadeApice {
	
	private VelocidadeApice() {
		throw new AssertionError();
	}
	
	/**
	 * Obtém a velocidade horizontal no topo da parábola pela fórmula
	 * Vx = Vo.cos(fi)
	 * 
	 * @param velocidadeInicial
	 * @param angulacao
	 * @return
	 */
	public static double calcularHorizontal (double velocidadeInicial,
			double angulacao) {
		return velocidadeInicial * Math.cos(angulacao);
	}
	
	/**
	 * Obtém a velocidade vertical no topo da parábola
	 * 
	 * @return
	 */
	public static double calcularVertical () {
		return 0d;
	}

}
