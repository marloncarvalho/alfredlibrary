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
 * Formatador de PIS/PASEP.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 08/06/2010
 */
final public class PISPASEP {
	
	private PISPASEP() {}
	
	/**
	 * Obter um PIS/PASEP qualquer e formatá-lo. Qualquer caracter diferente de
	 * números será ignorado.
	 * 
	 * @param pisPasep Número do PIS/PASEP.
	 * @return PIS/PASEP formatado (999.99999.99-9).
	 */
	public static String formatar(String pisPasep) {
		String cpfSoNumeros = limpar(pisPasep);
		// Verificar tamanho do CPF.
		if (cpfSoNumeros.length() != 11)
			throw new AlfredException("PIS/PASEP inválido. Tamanho de um PIS/PASEP válido é 11. Este possui " + cpfSoNumeros.length() + " números.");
		StringBuilder sb = new StringBuilder();
		sb.append(cpfSoNumeros.substring(0, 3));
		sb.append(".");
		sb.append(cpfSoNumeros.substring(3, 8));
		sb.append(".");
		sb.append(cpfSoNumeros.substring(8, 10));
		sb.append("-");
		sb.append(cpfSoNumeros.substring(10, 11));
		return sb.toString();
	}
	
	/**
	 * Limpar o PIS/PASEP mantendo somente os números. Não verifica se é válido.
	 * 
	 * @param pisPasep PIS/PASEP que deve ser limpado.
	 * @return PIS/PASEP apenas com números.
	 */
	public static String limpar(String pisPasep) {
		if (pisPasep == null)
			throw new AlfredException("O PIS/PASEP informado é nulo.");
		if ("".equals(pisPasep))
			throw new AlfredException("O PIS/PASEP informado é vazio.");
		char[] chars = pisPasep.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int indice = 0; indice < chars.length; indice++) {
			if (Numeros.isInteger(String.valueOf(chars[indice]))) {
				sb.append(chars[indice]);
			}
		}
		return sb.toString();
	}
}
