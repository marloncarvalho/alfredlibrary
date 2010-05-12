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

	/**
	 * Multiplicar duas matrizes.
	 * 
	 * @param matriz1 Matriz 1.
	 * @param matriz2 Matriz 2.
	 * @return Matriz resultante da multiplicação.
	 */
	public static Double[][] multiplicar(Double[][] matriz1, Double[][] matriz2) {
		int colunasMatriz1 = matriz1[0].length;
		int linhasMatriz2 = matriz2.length;
		if ( colunasMatriz1 != linhasMatriz2 ) {
			throw new AlfredException("Estas matrizes não podem ser multiplicadas. Mais explicações? Visite http://pt.wikipedia.org/wiki/Matriz_(matemática)#Multiplica.C3.A7.C3.A3o_de_matrizes");
		}
		Double[][] mult = new Double[colunasMatriz1-1][colunasMatriz1-1];
		for(int i=0; i < matriz1.length; i++) {
			for(int j=0; j < matriz2[0].length; j++ ) {
				mult[i][j] = 0D;
				for(int x=0; x < colunasMatriz1; x++) {
					mult[i][j] += matriz1[i][x] * matriz2[x][j];
				}
			}
		}
		return mult;
	}

	/**
	 * Multiplicar uma matriz por um valor escalar.
	 * 
	 * @param matriz Matriz.
	 * @param valor Valor.
	 * @return Matriz resultante da operação.
	 */
	public static Double[][] multiplicar(Double[][] matriz, Double valor) {
		Double[][] mult = new Double[matriz.length][matriz.length];
		for(int x=0; x < matriz.length; x++) {
			for(int y=0; y < matriz[0].length; y++ ) {
				mult[x][y] = matriz[x][y] * valor;
			}
		}
		return mult;
	}

	/**
	 * Obter a oposta de uma determinada matriz.
	 * 
	 * @param matriz Matriz.
	 * @return Matriz oposta.
	 */
	public static Double[][] oposta(Double[][] matriz) {
		return Matriz.multiplicar(matriz, -1D);
	}

	/**
	 * Verificar se uma Matriz é Identidade.
	 * 
	 * @param matriz Matriz a ser verificada.
	 * @return Verdadeiro caso seja.
	 */
	public static boolean isIdentidade(Double[][] matriz) {
		if ( ! Matriz.isQuadrada(matriz) ) {
			return false;
		}
		for(int x=0; x < matriz.length; x++) {
			Double[] linha1 = matriz[x];
			for(int y=0; y < linha1.length; y++ ) {
				if ( x == y ) {
					if ( matriz[x][y] != 1D ) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Verificar se é uma matriz quadrada.
	 * 
	 * @param matriz Matriz a ser verificada.
	 * @return Verdadeiro caso seja.
	 */
	public static boolean isQuadrada(Double[][] matriz) {
		if ( matriz != null ) {
			if ( matriz.length == matriz[0].length ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verificar se uma matriz é diagonal.
	 * 
	 * @param matriz Matriz a ser verificada.
	 * @return Verdadeiro caso seja.
	 */
	public static boolean isDiagonal(Double[][] matriz) {
		if ( ! Matriz.isQuadrada(matriz) ) {
			return false;
		}
		for(int x=0; x < matriz.length; x++) {
			Double[] linha1 = matriz[x];
			for(int y=0; y < linha1.length; y++ ) {
				if ( x != y ) {
					if ( matriz[x][y] != 0D ) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Verificar se uma Matriz é simétrica.
	 * 
	 * @param matriz Matriz a ser verificada.
	 * @return Verdadeiro caso seja.
	 */
	public static boolean isSimetrica(Double[][] matriz) {
		if ( ! Matriz.isQuadrada(matriz) ) {
			return false;
		}
		return Matriz.isIgual(matriz, Matriz.transpor(matriz));
	}

	/**
	 * Verificar se duas matrizes são idênticas.
	 * Para serem iguais, as matrizes devem ter a mesma ordem e os mesmos elementos em cada linha x coluna.
	 * 
	 * @param matriz1 Matriz a ser verificada.
	 * @param matriz2 Matriz a ser verificada.
	 * @return Verdadeiro caso sejam.
	 */
	public static boolean isIgual(Double[][] matriz1, Double[][] matriz2) {
		if ( matriz1.length != matriz2.length ) {
			return false;
		}
		if ( matriz1[0].length != matriz2[0].length ) {
			return false;
		}
		for(int x=0; x < matriz1.length; x++) {
			Double[] linha1 = matriz1[x];
			for(int y=0; y < linha1.length; y++ ) {
				if ( matriz1[x][y] != matriz2[x][y] ) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Double[][] m1 = new Double[][]{ {1D,0D,2D}, {-1D,3D,1D} };
		Double[][] m2 = new Double[][]{ {3D, 1D}, {2D, 1D}, {1D, 0D}};
		Double[][] m3 = Matriz.multiplicar(m1, m2);
		System.out.println(m3);
	}
}