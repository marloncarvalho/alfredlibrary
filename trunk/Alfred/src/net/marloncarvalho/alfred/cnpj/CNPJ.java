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
		if (soNumeros.length() != 15)
			throw new AlfredException(
					"Informe um CNPJ válido. Este CNPJ possui apenas "
							+ soNumeros.length()
							+ " números. Um CNPJ válido deve conter 15.");
		s.append(soNumeros.substring(0, 3));
		s.append(".");
		s.append(soNumeros.substring(3, 6));
		s.append(".");
		s.append(soNumeros.substring(6, 9));
		s.append("/");
		s.append(soNumeros.substring(9, 13));
		s.append("-");
		s.append(soNumeros.substring(13, 15));
		return s.toString();
	}

	/**
	 * Validar um CNPJ.
	 * Código de Rodrigo Scorsatto.
	 * Obtido em http://www.javafree.org/topic-860897-Validar-CPF--CNPJ-e-consultar-CEP.html
	 * 
	 * @param str_cnpj CNPJ a ser validado.
	 * @return Verdadeiro caso seja válido. Falso, caso contrário.
	 */
	public static boolean isValido(String str_cnpj) {
		if (!str_cnpj.substring(0, 1).equals("")) {
			try {
				str_cnpj = str_cnpj.replace('.', ' ');
				str_cnpj = str_cnpj.replace('/', ' ');
				str_cnpj = str_cnpj.replace('-', ' ');
				str_cnpj = str_cnpj.replaceAll(" ", "");
				int soma = 0, dig;
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
				cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer
						.toString(dig);
				/* Segunda parte */
				soma = 0;
				for (int i = 0; i < 5; i++)
					if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
						soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
				for (int i = 0; i < 8; i++)
					if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
						soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
				dig = 11 - (soma % 11);
				cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer
						.toString(dig);
				return str_cnpj.equals(cnpj_calc);
			} catch (Exception e) {
				return false;
			}
		} else
			return false;
	}

}