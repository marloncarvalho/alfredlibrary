/*
 *  This file is part of Alfred Library.
 *
 *  Foobar is free software: you can redistribute it and/or modify
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
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.marloncarvalho.alfred.texto;

import net.marloncarvalho.alfred.numeros.Numeros;

/**
 * Utilitários para manipulação de Textos.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
public class Texto {

	/**
	 * Manter no Texto apenas os números.
	 * 
	 * @param str Texto.
	 * @return Texto contendo apenas os números.
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
	 * Incluir uma determinada quantidade de vezes um determinado caracter no início do texto.
	 * 
	 * @param texto Texto que terá o caracter inserido no início.
	 * @param c Caracter que será incluído.
	 * @param q Quantidade de vezes que o caracter será incluído.
	 * @return Texto com os caracteres incluídos.
	 */
	public static String incluirCaracterInicio(String texto, char c, int q) {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < q; i++)
			s.append(c);
		s.append(texto);
		return s.toString();
	}

}