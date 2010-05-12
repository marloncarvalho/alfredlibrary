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
package org.alfredlibrary.utilitarios.matematica;

import org.alfredlibrary.AlfredException;

/**
 * Utilitário com operações sobre matrizes.
 * 
 * @author Marlon Silva Carvalho
 * @since 12/05/2010
 */
final public class Matriz {

	private Matriz() { }
	
	/**
	 * Obter a transposta de uma Matriz.
	 * 
	 * @param matriz Matriz.
	 * @return Transposta.
	 */
	public static Double[][] transpor(Double[][] matriz) {
		Double[][] transposta = new Double[matriz[0].length][matriz.length];
		for(int x=0; x < matriz.length; x++) {
			Double[] linha = matriz[x];
			for(int y=0; y < linha.length; y++ ) {
				transposta[y][x] = matriz[x][y];
			}
		}
		return transposta;
	}

	/**
	 * Realizar a operação de adição entre duas matrizes.
	 * 
	 * @param matriz1 Matriz 1.
	 * @param matriz2 Matriz 2.
	 * @return Matriz com a soma.
	 */
	public static Double[][] somar(Double[][] matriz1, Double[][] matriz2) {
		if ( matriz1.length != matriz2.length ) {
			throw new AlfredException("Só podem ser somadas matrizes de mesma ordem.");
		}
		if ( matriz1[0].length != matriz2[0].length ) {
			throw new AlfredException("Só podem ser somadas matrizes de mesma ordem.");
		}
		Double[][] soma = new Double[matriz1.length][matriz2.length-1];
		for(int x=0; x < matriz1.length; x++) {
			Double[] linha1 = matriz1[x];
			for(int y=0; y < linha1.length; y++ ) {
				soma[x][y] = matriz1[x][y] + matriz2[x][y];
			}
		}
		return soma;
	}

	/**
	 * Realizar a operação de adição entre duas matrizes.
	 * 
	 * @param matriz1 Matriz 1.
	 * @param matriz2 Matriz 2.
	 * @return Matriz com a soma.
	 */
	public static Double[][] subtrair(Double[][] matriz1, Double[][] matriz2) {
		if ( matriz1.length != matriz2.length ) {
			throw new AlfredException("Só podem ser subtraídas matrizes de mesma ordem.");
		}
		if ( matriz1[0].length != matriz2[0].length ) {
			throw new AlfredException("Só podem ser subtraídas matrizes de mesma ordem.");
		}
		Double[][] soma = new Double[matriz1.length][matriz2.length-1];
		for(int x=0; x < matriz1.length; x++) {
			Double[] linha1 = matriz1[x];
			for(int y=0; y < linha1.length; y++ ) {
				soma[x][y] = matriz1[x][y] - matriz2[x][y];
			}
		}
		return soma;
	}

}