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
 * @author Rodrigo Moreira Fagundes
 * @since 12/05/2010
 */
final public class Matriz {

	private Matriz() { 
		throw new AssertionError();
	}
	
	/**
	 * Obter a transposta de uma Matriz.
	 * 
	 * @param matriz Matriz.
	 * @return Transposta.
	 */
	public static Double[][] transpor(Double[][] matriz) {
		validarMatriz(matriz);
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
		validarMatriz(matriz1, matriz2);
		if ( matriz1.length != matriz2.length ) {
			throw new AlfredException("Só podem ser somadas matrizes de mesma ordem.");
		}
		if ( matriz1[0].length != matriz2[0].length ) {
			throw new AlfredException("Só podem ser somadas matrizes de mesma ordem.");
		}
		Double[][] soma = new Double[matriz1.length][matriz2.length];
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
		validarMatriz(matriz1, matriz2);
		if ( matriz1.length != matriz2.length ) {
			throw new AlfredException("Só podem ser subtraídas matrizes de mesma ordem.");
		}
		if ( matriz1[0].length != matriz2[0].length ) {
			throw new AlfredException("Só podem ser subtraídas matrizes de mesma ordem.");
		}
		Double[][] soma = new Double[matriz1.length][matriz2.length];
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
		validarMatriz(matriz1, matriz2);
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
		validarMatriz(matriz);
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
		validarMatriz(matriz);
		return Matriz.multiplicar(matriz, -1D);
	}

	/**
	 * Verificar se uma Matriz é Identidade.
	 * 
	 * @param matriz Matriz a ser verificada.
	 * @return Verdadeiro caso seja.
	 */
	public static boolean isIdentidade(Double[][] matriz) {
		validarMatriz(matriz);
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
		validarMatriz(matriz);
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
		validarMatriz(matriz);
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
		validarMatriz(matriz);
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
		validarMatriz(matriz1, matriz2);
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
	
	/**
	 * Calcular determinante da matriz.
	 * 
	 * @param matriz Matriz para cálculo do determinante.
	 * @return Determinante.
	 */
	public static double determinante(Double[][] matriz) {
		validarMatriz(matriz);
		int altura = obterAltura(matriz);
		int largura = obterLargura(matriz);
		double resultado = 0;	
		if (isDiagonal(matriz)) {
			resultado = 1;
			for (int i = 0; i < matriz.length; i++) {
				resultado *= matriz[i][i];
			}
		} else {
			for (int inicio = 0; inicio < largura; inicio++) {
				double parcela = 1;
				for (int i = 0; i < altura; i++) {
					parcela *= matriz[i][(inicio + i) % largura];
				}
				resultado += parcela;
				double subtraendo = 1;
				for (int i = 0; i < altura; i++) {
					// CÁLCULO DE ÍNDICE REGRESSIVO DE COLUNA  
					// Pega a parte inteira da proporção para auxiliar no cálculo que impede a geração de índice negativo para a matriz
					int parteInteiraProporcao;
					if (altura >= largura) {
						parteInteiraProporcao = (altura - (altura % largura)) / largura;
					} else {
						parteInteiraProporcao = (largura - (largura % altura)) / altura;
					}
					// Calcula a base que garante a não geração de índice negativo
					int base = (parteInteiraProporcao + 1) * largura;
					// Calcula o índice e garante que ele não seja maior que a última coluna
					int indice = (base - (inicio + i)) % largura;
					// USO DO ÍNDICE
					// Usa o índice calculado para a obtenção de cada fator do subtraendo
					subtraendo *= matriz[i][indice];
				}
				resultado -= subtraendo;
			}
		}
		return resultado;
	}
	
	/**
	 * Calcular Altura da matriz.
	 * 
	 * @param matriz Matriz para cálculo da altura.
	 * @return Altura da matriz.
	 */
	private static int obterAltura(Double[][] matriz) {
		return matriz.length;
	}
	
	/**
	 * Calcular Largura da matriz.
	 * 
	 * @param matriz Matriz para cálculo da largura.
	 * @return Largura da matriz.
	 */
	private static int obterLargura(Double[][] matriz) {
		return matriz[0].length;
	}
	
	/**
	 * Verifica se o vetor informado é realmente matriz.
	 * 
	 * @param matriz Matriz para validação.
	 */
	private static void validarMatriz (Double[][] matriz) {
		if (obterAltura(matriz) == 0 || obterLargura(matriz) == 0) {
			throw new AlfredException("O parâmetro informado não é matriz!");
		}
		for (int i = 0; i < obterAltura(matriz); i ++) {
			if (matriz[i].length != obterLargura(matriz)) {
				throw new AlfredException("O parâmetro informado não é matriz!");
			}
		}
	}
	
	/**
	 * Verifica se os vetores informados são realmente matrizes.
	 * 
	 * @param matriz1 Matriz para validação.
	 * @param matriz2 Matriz para validação.
	 */
	private static void validarMatriz (Double[][] matriz1, Double[][] matriz2) {
		try {
			validarMatriz(matriz1);
		} catch (AlfredException ae) {
			throw new AlfredException("O primeiro parâmetro informado não é matriz!");
		}
		try {
			validarMatriz(matriz2);
		} catch (AlfredException ae) {
			throw new AlfredException("O segundo parâmetro informado não é matriz!");
		}
	}
	
	/**
	 * Método público para avaliar se um vetor é matriz
	 * 
	 * @param matriz Matriz para validação
	 * @return VERDADEIRO se for matriz
	 */
	public static boolean isMatriz (Double[][] matriz) {
		try {
			validarMatriz(matriz);
		} catch (AlfredException ae) {
			return false;
		}
		return true;
	}
	
	/**
	 * Verifica se a matriz possui linhas proporcionais
	 * 
	 * @param matriz Matriz para verificação
	 * @return VERDADEIRO se possuir linhas proporcionais
	 */
	public static boolean temLinhasProporcionais (Double[][] matriz) {
		validarMatriz(matriz);
		for (int pivot = 0; pivot < matriz.length; pivot++) {
			for (int i = pivot + 1; i < matriz.length; i++) {
				boolean linhaProporcional = true;
				double proporcao = 0;
				lacoColunaComparada: for (int j = 0; j < matriz[i].length; j++) {
					if (matriz[pivot][j] == 0) {
						if (matriz[i][j] != 0) {
							linhaProporcional = false;
							break lacoColunaComparada;
						}
					} else {
						if (proporcao == 0) {
							proporcao = matriz[i][j] / matriz[pivot][j];
						} else {
							if (matriz[i][j] / matriz[pivot][j] != proporcao) {
								linhaProporcional = false;
								break lacoColunaComparada;
							}
						}
					}
				}
				if (linhaProporcional) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifica se a matriz possui colunas proporcionais
	 * 
	 * @param matriz Matriz para verificação
	 * @return VERDADEIRO se possuir colunas proporcionais
	 */
	public static boolean temColunasProporcionais (Double[][] matriz) {
		return temLinhasProporcionais(transpor(matriz));
	}

}