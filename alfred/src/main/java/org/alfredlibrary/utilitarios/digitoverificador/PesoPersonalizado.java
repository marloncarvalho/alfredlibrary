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
package org.alfredlibrary.utilitarios.digitoverificador;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.texto.Texto;

/**
 * Utilitário para Geração de DV pela atribuição dos pesos parametrizada.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 14/06/2010
 */
public final class PesoPersonalizado {
	
	/**
	 * Calcular um dígito verificador a partir de uma sequência de números
	 * enviada e de uma sequencia de pesos.
	 * 
	 * @param fonte Sequência de números para cálculo do DV
	 * @param peso Sequência de pesos para cálculo do DV
	 * @return DV gerado.
	 */
	public static String obterDV (String fonte, String peso) {
		String[] pesoSplit = peso.split("|");
		validar(fonte, pesoSplit);
		int dv = 0;
		for (int i = 0; i < fonte.length(); i++) {
			dv += Integer.valueOf(fonte.charAt(i)) * Integer.parseInt(pesoSplit[i]);
		}
		dv = dv % 11;
		if (dv > 1) {
			return String.valueOf(11 - dv);
		} else if (dv == 1) {
			return "0";
		}
		return "1";
	}
	
	/**
	 * Verifica se a fonte possui apenas números.
	 * 
	 * @param fonte Texto a ser validado
	 * @param peso Peso a ser validado
	 */
	private static void validar(String fonte, String[] peso) {
		if (Texto.manterNumeros(fonte).length() != fonte.length()) {
			throw new AlfredException("Texto informado contém caracteres não numéricos!");
		}
		for (int i = 0; i < peso.length; i++) {
			try {
				Integer.valueOf(peso[i]);
			} catch (Exception e) {
				throw new AlfredException("Peso informado contém caracteres não numéricos!");
			}
		}
		if (Texto.manterNumeros(fonte).length() != peso.length) {
			throw new AlfredException("Texto e peso possuem tamanhos diferentes!");
		}
	}
}
