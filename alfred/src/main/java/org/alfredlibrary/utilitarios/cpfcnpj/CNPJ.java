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

import org.alfredlibrary.utilitarios.digitoverificador.Modulo11;

/**
 * Utilitário para CNPJ.
 * 
 * @author Marlon Silva Carvalho
 * @since 06/05/2010
 */
public final class CNPJ {

	private CNPJ() {
		throw new AssertionError();
	}
	
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
		return Modulo11.obterDVBase10(cnpj, false, 2);
	}
}