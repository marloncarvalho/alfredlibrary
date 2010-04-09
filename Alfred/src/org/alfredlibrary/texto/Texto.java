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
package org.alfredlibrary.texto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfredlibrary.numeros.Numeros;

/**
 * Utilit�rios para manipula��o de Textos.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
public class Texto {

	/**
	 * Manter no Texto apenas os n�meros.
	 * 
	 * @param str Texto.
	 * @return Texto contendo apenas os n�meros.
	 */
	public static String manterNumeros(String str) {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < str.length() ; i++) {
			if ( Numeros.isNumber(String.valueOf(str.charAt(i))) ) {
				s.append(str.charAt(i));
			}
		}
		return s.toString();
	}

	/**
	 * Incluir uma determinada quantidade de vezes um determinado caracter no in�cio do texto.
	 * 
	 * @param texto Texto que ter� o caracter inserido no in�cio.
	 * @param c Caracter que ser� inclu�do.
	 * @param q Quantidade de vezes que o caracter ser� inclu�do.
	 * @return Texto com os caracteres inclu�dos.
	 */
	public static String incluirCaracterInicio(String texto, char c, int q) {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < q; i++)
			s.append(c);
		s.append(texto);
		return s.toString();
	}

	/**
	 * Capitalizar a primeira letra de todas as palavras do texto.
	 * 
	 * @param texto Texto que ter� as palavras capitalizadas.
	 * @return Texto com a formata��o aplicada.
	 */
	public static String capitalizarIniciais(String texto) {
		String[] split = texto.split(" ");
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < split.length; i++) {
			s.append(String.valueOf(split[i].charAt(0)).toUpperCase());
			s.append(split[i].substring(1, split[i].length()));
			s.append(" ");
		}
		return s.toString().trim();
	}

	/**
	 * Contar a quantidade de vezes que uma palavra ocorre em um texto.
	 * N�o usar caracteres especiais na palavra a ser pesquisada.
	 * 
	 * @param texto Texto.
	 * @param palavra Palavra que ser� contada no texto.
	 * @param ignoreCase Considerar mai�sculas ou min�sculas.
	 * @return Quantidade de vezes que a palavra ocorre.
	 */
	public static int contarQuantidadePalavra(String texto, String palavra, boolean ignoreCase) {
		if ( ignoreCase) {
			texto = texto.toLowerCase();
			palavra = palavra.toLowerCase();
		}
		Pattern padrao = Pattern.compile(palavra);  
		Matcher pesquisa = padrao.matcher(texto);
		int r = 0;
		while(pesquisa.find()) 
			r++;
		return r;
	}

	/**
	 * Obter a cadeia de caracteres que forma o alfabeto brasileiro em min�sculas.
	 * 
	 * @return Array de caracteres.
	 */
	public static char[] obterAlfabetoMinusculas() {
		return new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','x','y','w','z'};
	}

	/**
	 * Obter a cadeia de caracteres que forma o alfabeto brasileiro em min�sculas.
	 * 
	 * @return Array de caracteres.
	 */
	public static char[] obterAlfabetoMaiusculas() {
		return new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','X','Y','W','Z'};
	}

}