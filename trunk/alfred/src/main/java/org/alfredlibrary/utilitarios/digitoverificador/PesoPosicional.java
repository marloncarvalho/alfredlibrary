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
 * Utilitário para Geração de DV pela atribuição dos pesos de acordo com a posição
 * dos números na sequência numérida informada.
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 14/06/2010
 */
public final class PesoPosicional {
	
	/**
	 * Calcular um dígito verificador a partir de uma sequência de números
	 * enviada.
	 * 
	 * @param fonte Sequência de números para cálculo do DV
	 * @return DV gerado.
	 */
	public static String obterDV (String fonte) {
		validarFonte(fonte);
		int dv = 0;
		for (int i = 0; i < fonte.length(); i++) {
			dv += Integer.parseInt(fonte.substring(i , i + 1)) * (i + 1);
		}
		return String.valueOf(dv % 9);
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
}
