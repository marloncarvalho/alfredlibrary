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
import org.alfredlibrary.utilitarios.texto.Texto;

/**
 * Formatador de CNPJ.
 * 
 * @author Marlon Silva Carvalho
 * @since 06/05/2010
 */
public final class CNPJ {

	private CNPJ() {
		throw new AssertionError();
	}
	
	/**
	 * Formatar um CNPJ.
	 * 
	 * @param cnpj CNPJ a ser formatado.
	 * @return CNPJ formatado.
	 */
	public static String formatar(String cnpj) {
		StringBuilder s = new StringBuilder();
		String soNumeros = Texto.manterNumeros(cnpj);
		if (soNumeros.length() != 14)
			throw new AlfredException("Informe um CNPJ válido. Este CNPJ possui apenas " + soNumeros.length() + " números. Um CNPJ válido deve conter 15.");
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

}
