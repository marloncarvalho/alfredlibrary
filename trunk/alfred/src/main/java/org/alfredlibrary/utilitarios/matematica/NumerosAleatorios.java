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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.alfredlibrary.AlfredException;

/**
 * Utilitário que gera numeros aleatórios.
 * 
 * @author Mario Jorge Pereira
 * 
 * @since 25/12/2010
 * 
 * 
 */
final public class NumerosAleatorios {
	
	
	private NumerosAleatorios() {
		throw new AssertionError();
	}
	

	private static Random gerador = new Random();

	/**
	 * 
	 * Gera aleatoriamente true ou false.
	 * 
	 * @return retorna true ou false
	 */
	public static Boolean booleanAleatorio() {
		return gerador.nextBoolean();
	}

	/**
	 * 
	 * Gera aleatoriamente um dos 2<font size="-1"><sup>32</sup></font> numeros
	 * inteiros possiveis.
	 * 
	 * @return Numero Inteiro Aleatório
	 */
	public static Integer inteiroAleatorio() {
		return gerador.nextInt();
	}

	/**
	 * 
	 * Gera aleatoriamente um numero inteiro entre 0 (incluindo) e o valor
	 * limite especificado (excluindo).
	 * 
	 * @param minimo
	 *            - o limite minimo do numero aleatorio gerado
	 * @param maximo
	 *            - o limite maximo do número aleatório gerado
	 * 
	 * @return Numero Inteiro Aleatório entre os parametros minimo e maximo.
	 * 
	 * @throws IllegalArgumentException
	 *             - se o maximo for menor que o minimo.
	 * 
	 */
	public static Integer inteirosAleatoriosEmUmaFaixaLimite(int minimo,
			int maximo) {
		if (minimo > maximo) {
			throw new IllegalArgumentException(
					"Maximo deve ser maior que o minimo.");
		}
		int faixa = maximo - minimo;
		int numero = gerador.nextInt(faixa);
		return numero + minimo;
	}

	/**
	 * 
	 * Gera aleatoriamente um numero inteiro entre 0 (incluindo) e o valor
	 * limite especificado (excluindo).
	 * 
	 * @param limite
	 *            - o limite do número aleatório para ser gerado, deve ser
	 *            positivo, maior que 0 (Zero).
	 * @return Numero Inteiro Aleatório entre 0 e o valor limite.
	 * 
	 * @throws IllegalArgumentException
	 *             - se limite não for um valor positivo.
	 * 
	 */
	public static Integer inteirosAleatoriosPositivosLimite(int limite) {
		return gerador.nextInt(limite);
	}

	/**
	 * 
	 * Gera aleatoriamente um dos 2<font size="-1"><sup>64</sup></font> numeros
	 * inteiros longos possiveis.
	 * 
	 * @return Numero Inteiro Longo Aleatório
	 */
	public static Long longAleatorio() {
		return gerador.nextLong();
	}

	/**
	 * Gera aleatoriamente um dos 2<font size="-1"><sup>24</sup></font>
	 * possibilidades de numeros flutuantes entre 0.0 e 1.0.
	 * 
	 * @return Numero flutuante entre 0.0 e 1.0.
	 */
	public static Float floatAleatorio() {
		return gerador.nextFloat();
	}

	/**
	 * Gera aleatoriamente um dos 2<font size="-1"><sup>53</sup></font>
	 * possibilidades de numeros reais entre 0.0 e 1.0.
	 * 
	 * @return Numero reais entre 0.0 e 1.0.
	 */
	public static Double doubleAleatorio() {
		return gerador.nextDouble();
	}

	/**
	 * Gera aleatoriamente um dos 2<font size="-1"><sup>53</sup></font>
	 * possibilidades de numeros reais entre 0.0 e 1.0. Utilizando a
	 * Distribuição Normal,conhecida também como Distribuição de Gauss ou
	 * Gaussiana.
	 * 
	 * @return Numero flutuante entre 0.0 e 1.0.
	 */
	public static Double doubleAleatorioComDistribuicaoNormal() {
		return gerador.nextDouble();
	}

	/**
	 * 
	 * Gera aleatoriamente um numero real entre a faixa especificada.
	 * 
	 * @param minimo
	 *            - o limite minimo do numero aleatorio gerado
	 * @param maximo
	 *            - o limite maximo do número aleatório gerado
	 * 
	 * @return Numero real Aleatório entre os parametros minimo e o maximo.
	 * 
	 * @throws IllegalArgumentException
	 *             - se o maximo for menor que o minimo.
	 * 
	 */
	public static Double inteirosAleatorios(double minimo, double maximo) {
		if (minimo > maximo) {
			throw new AlfredException("Fim deve ser maior que o inicio.");
		}
		double faixa = maximo - minimo;
		double valor = faixa * gerador.nextDouble();
		return valor + minimo;
	}

	/**
	 * 
	 * @param quantidade
	 * @param minimo
	 * @param maximo
	 * @param repetir
	 * @return
	 */
	public static List<Integer> listaInteirosAleatorios(int quantidade,
			int minimo, int maximo, boolean repetir) {
		if (((maximo - minimo) <= quantidade) && !repetir) {
			throw new AlfredException(
					"Faixa menor que a quantidade de numeros da lista.");
			
		}
		List<Integer> lista = new ArrayList<Integer>();
		Collection<Integer> c = new ArrayList<Integer>();
		if (!repetir) {
			c = new HashSet<Integer>();
		}
		while (c.size() < quantidade) {
			c.add(NumerosAleatorios.inteirosAleatoriosEmUmaFaixaLimite(minimo, maximo));
		}
		lista.addAll(c);
		return lista;

	}

}
