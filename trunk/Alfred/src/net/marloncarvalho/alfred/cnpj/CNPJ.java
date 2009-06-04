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
package net.marloncarvalho.alfred.cnpj;

import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.texto.Texto;

/**
 * Classe utilitária para CNPJ
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
public class CNPJ {

	/**
	 * Formatar um CNPJ.
	 * 
	 * @param cnpj
	 *            CNPJ a ser formatado.
	 * @return CNPJ formatado.
	 */
	public static String formatar(String cnpj) {
		StringBuilder s = new StringBuilder();
		String soNumeros = Texto.manterNumeros(cnpj);
		if (soNumeros.length() != 14)
			throw new AlfredException(
					"Informe um CNPJ válido. Este CNPJ possui apenas "
							+ soNumeros.length()
							+ " números. Um CNPJ válido deve conter 15.");
		s.append(soNumeros.substring(0, 2));
		s.append(".");
		s.append(soNumeros.substring(2, 5));
		s.append(".");
		s.append(soNumeros.substring(5, 8));
		s.append("/");
		s.append(soNumeros.substring(8, 12));
		s.append("-");
		s.append(soNumeros.substring(12, 14));
		return s.toString();
	}

	/**
	 * Validar um CNPJ. Código de Rodrigo Scorsatto. 
	 * Obtido em http://www.javafree.org/artigo/852844/Validacao-de-CNPJ-em-java.html
	 * Autor: Dalton Camargo.
	 * 
	 * @param str_cnpj CNPJ a ser validado.
	 * @return Verdadeiro caso seja válido. Falso, caso contrário.
	 */
	public static boolean isValido(String str_cnpj) {
		int soma = 0, dig;
		str_cnpj = Texto.manterNumeros(str_cnpj);
		String cnpj_calc = str_cnpj.substring(0, 12);

		if (str_cnpj.length() != 14)
			return false;

		char[] chr_cnpj = str_cnpj.toCharArray();

		/* Primeira parte */
		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);

		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		soma = 0;
		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		return str_cnpj.equals(cnpj_calc);
	}

}