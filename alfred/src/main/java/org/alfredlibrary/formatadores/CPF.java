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
package org.alfredlibrary.formatadores;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.validadores.Numeros;

/**
 * Formatador de CPF.
 * 
 * @author Marlon Silva Carvalho
 * @since 06/05/2010
 */
final public class CPF {
	
	private CPF() {
		throw new AssertionError();
	}
	
	/**
	 * Obter um CPF qualquer e formatá-lo. Qualquer caracter diferente de
	 * números será ignorado. Portanto, um CPF do tipo 1111c11b11122a será
	 * formatado para 111.111.111-11
	 * 
	 * @param cpf Número do CPF.
	 * @return CPF formatado.
	 */
	public static String formatar(String cpf) {
		String cpfSoNumeros = limpar(cpf);
		// Verificar tamanho do CPF.
		if (cpfSoNumeros.length() != 11)
			throw new AlfredException("CPF inválido. Tamanho de um CPF válido é 11. Este CPF possui " + cpfSoNumeros.length() + " números.");
		StringBuilder sb = new StringBuilder();
		sb.append(cpfSoNumeros.substring(0, 3));
		sb.append(".");
		sb.append(cpfSoNumeros.substring(3, 6));
		sb.append(".");
		sb.append(cpfSoNumeros.substring(6, 9));
		sb.append("-");
		sb.append(cpfSoNumeros.substring(9, 11));
		return sb.toString();
	}
	
	/**
	 * Limpar o CPF mantendo somente os números. Não verifica se é um CPF
	 * válido.
	 * 
	 * @param cpf CPF que deve ser limpado.
	 * @return CPF apenas com números.
	 */
	public static String limpar(String cpf) {
		if (cpf == null)
			throw new AlfredException("O CPF informado é nulo.");
		if ("".equals(cpf))
			throw new AlfredException("O CPF informado é vazio.");
		char[] chars = cpf.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int indice = 0; indice < chars.length; indice++) {
			if (Numeros.isInteger(String.valueOf(chars[indice]))) {
				sb.append(chars[indice]);
			}
		}
		return sb.toString();
	}
}
