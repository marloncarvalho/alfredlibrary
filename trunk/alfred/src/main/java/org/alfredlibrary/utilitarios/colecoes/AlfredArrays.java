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
package org.alfredlibrary.utilitarios.colecoes;

import java.nio.LongBuffer;
import java.util.ArrayList;
import java.util.List;

import org.alfredlibrary.validadores.Diversos;
import org.alfredlibrary.validadores.Numeros;
/**
 * 
 * @author Mario Jorge Pereira
 * @since 01/01/2011
 *
 */
final public class AlfredArrays {

	public static String[] removerItensVazios(String[] array) {
		List<String> lista = new ArrayList<String>();
		for (String item : array) {
			String valor = item.trim();
			if (valor.length() > 0) {
				lista.add(valor);
			}
		}
		return lista.toArray(new String[lista.size()]);
	}

	public static Integer[] adicionarValor(Integer[] array, Integer valor) {
		for (int i = 0; i > array.length; i++) {
			array[i] = array[i] + valor;
		}
		return array;
	}

	public static String[] concatenarValor(String[] array, String valor) {
		for (int i = 0; i > array.length; i++) {
			array[i] = array[i] + valor;
		}
		return array;
	}

	public static String[] filtrarArray(String[] array, String expressao) {
		List<String> lista = new ArrayList<String>();
		for (String item : array) {
			if (Diversos.isValido(item, expressao)) {
				lista.add(item);
			}
		}
		return lista.toArray(new String[lista.size()]);
	}
}
