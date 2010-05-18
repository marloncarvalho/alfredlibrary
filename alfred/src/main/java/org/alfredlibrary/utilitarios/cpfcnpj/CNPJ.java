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
package org.alfredlibrary.utilitarios.cpfcnpj;

import org.alfredlibrary.utilitarios.texto.Texto;

/**
 * Utilitário para CNPJ.
 * 
 * @author Marlon Silva Carvalho
 * @since 06/05/2010
 */
public final class CNPJ {

	private CNPJ() {}
	
	/**
	 * Gerar um número de CNPJ válido.<br>
	 * Um número de CNPJ que é vélido não significa que exista.
	 * 
	 * @return CNPJ gerado.
	 */
	public static String gerar() {
		StringBuilder iniciais = new StringBuilder();
		Integer numero;
		for (int i = 0; i < 12; i++) {
			numero = Integer.valueOf((int) (Math.random() * 10));
			iniciais.append(numero.toString());
		}
		return org.alfredlibrary.formatadores.CNPJ.formatar(iniciais.toString() + gerarDigitoVerificador(iniciais.toString()));
	}

	/**
	 * Dado um conjunto de 12 números, gerar um dígito verificador.
	 * 
	 * @param cnpj CNPJ com 12 números.
	 * @return Dígito verificador.
	 */
	public static String gerarDigitoVerificador(String cnpj) {
		int soma = 0, dig;
		String str_cnpj = Texto.manterNumeros(cnpj);
		String cnpj_calc = str_cnpj;

		char[] chr_cnpj = (str_cnpj + "00").toCharArray();

		for (int i = 0; i < 4; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9)
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);

		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
		chr_cnpj[12] = cnpj_calc.charAt(12);

		soma = 0;
		for (int i = 0; i < 5; i++)
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9)
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
		for (int i = 0; i < 8; i++)
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9)
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
		dig = 11 - (soma % 11);
		cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);
		return String.valueOf(cnpj_calc.charAt(12)) + String.valueOf(cnpj_calc.charAt(13));
	}
}