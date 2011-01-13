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
 * Formatador de Telefones.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Telefone {

	private Telefone() {
		throw new AssertionError();
	}

	/**
	 * Formatar um Telefone.
	 * A formatação depende da quantidade de números informados.
	 * Por exemplo:
	 *  12345678 será formatado para 1234-5678
	 *  1234567890 será formatado para (12) 3456-7890
	 *  1112345678 será formatado para +11 (12) 3456-7890
	 *  
	 * @param telefone Telefone a ser formatado.
	 * @return Telefone formatado.
	 */
	public static String formatar(String telefone) {
		if ( telefone == null || telefone.length() < 8) {
			throw new AlfredException("Informe um Telefone.");
		}
		String soNumeros = Texto.manterNumeros(telefone);
		StringBuilder s = new StringBuilder();
		if ( soNumeros.length() == 8 ) {
			s.append(telefone.substring(0,4));
			s.append("-");
			s.append(telefone.substring(4,8));
		}
		if ( soNumeros.length() == 10 ) {
			s.append("(");
			s.append(telefone.substring(0,2));
			s.append(") ");
			s.append(telefone.substring(2,6));
			s.append("-");
			s.append(telefone.substring(6,10));
		}
		if ( soNumeros.length() == 12 ) {
			s.append("+");
			s.append(telefone.substring(0,2));
			s.append(" (");
			s.append(telefone.substring(2,4));
			s.append(") ");
			s.append(telefone.substring(4,8));
			s.append("-");
			s.append(telefone.substring(8,12));
		}
		return (s.length() == 0 ? telefone : s.toString());
	}

}