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
 * Utilitário para Geração de DV pelo método Módulo 11.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 20/05/2010
 */
public final class Modulo11 {
	
	/**
	 * Calcular um dígito verificador a partir de uma sequência de números
	 * enviada.
	 * 
	 * @param fonte Sequência de números para cálculo do DV
	 * @param dezPorX Indica se deve haver substituição de resultado 10
	 * 			por X durante o cálculo - padrão usado em alguns lugares
	 * @return DV gerado.
	 */
	public static String obterDV (String fonte, boolean dezPorX) {
		validarFonte(fonte);
		int peso = fonte.length() + 1;
		int dv = 0;
		for (int i = 0; i < fonte.length(); i++) {
			dv += Integer.parseInt(fonte.substring(i, i + 1)) * peso--;
		}
		dv = dv % 11;
		if (dv > 1) {
			return String.valueOf(11 - dv);
		} else if (dv == 1 && dezPorX) {
			return "X";
		}
		return "0";
		
	}
	
	/**
	 * Verifica se a fonte possui apenas números.
	 * 
	 * @param fonte Texto a ser validado
	 */
	private static void validarFonte(String fonte) {
		if (Texto.manterNumeros(fonte).length() != fonte.length()) {
			throw new AlfredException("Texto informado contém caracteres não numéricos!");
		}
	}

	/**
	 * Calcular um dígito verificador com a quantidade de casas indicadas
	 * a partir de uma sequência de números enviada.
	 * 
	 * @param fonte Sequência de números para cálculo do DV
	 * @param dezPorX Indica se deve haver substituição de resultado 10
	 * 			por X durante o cálculo - padrão usado em alguns lugares
	 * @param quantidadeDigitos Quantidade de dígitos a serem retornados
	 * @return DV gerado.
	 */
	public static String obterDV (String fonte, boolean dezPorX, int quantidadeDigitos) {
		if (quantidadeDigitos > 1) {
			String parcial = obterDV(fonte, dezPorX);
			return parcial + obterDV(fonte + parcial, dezPorX, --quantidadeDigitos);
		} else {
			return obterDV(fonte, dezPorX);
		}
	}
	
	/**
	 * Calcular um dígito verificador a partir de uma sequência de números
	 * enviada.
	 * O maior peso usado é 9. Quando hega a 10, retorna a 1
	 * 
	 * @param fonte Sequência de números para cálculo do DV
	 * @param dezPorX Indica se deve haver substituição de resultado 10
	 * 			por X durante o cálculo - padrão usado em alguns lugares
	 * @return DV gerado.
	 */
	public static String obterDVBase10 (String fonte, boolean dezPorX) {
		validarFonte(fonte);
		int peso = 2;
		int dv = 0;
		for (int i = fonte.length() - 1; i >= 0; i--) {
			dv += Integer.parseInt(fonte.substring(i, i + 1)) * peso;
			peso = ++peso % 10;
			if (peso < 2) peso = 9;
		}
		dv = dv % 11;
		if (dv > 1) {
			return String.valueOf(11 - dv);
		} else if (dv == 1 && dezPorX) {
			return "X";
		}
		return "0";
		
	}
	
	/**
	 * Calcular um dígito verificador usando o módulo 11, base 10, com
	 * a quantidade de casas indicadas a partir de uma sequência de
	 * números enviada.
	 * 
	 * @param fonte Sequência de números para cálculo do DV
	 * @param dezPorX Indica se deve haver substituição de resultado 10
	 * 			por X durante o cálculo - padrão usado em alguns lugares
	 * @param quantidadeDigitos Quantidade de dígitos a serem retornados
	 * @return DV gerado.
	 */
	public static String obterDVBase10 (String fonte, boolean dezPorX, int quantidadeDigitos) {
		if (quantidadeDigitos > 1) {
			String parcial = obterDVBase10(fonte, dezPorX);
			return parcial + obterDVBase10(fonte + parcial, dezPorX, --quantidadeDigitos);
		} else {
			return obterDVBase10(fonte, dezPorX);
		}
	}

}
