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
package org.alfredlibrary.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validador de E-mails.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Email {

	private Email() {
	}

	/**
	 * Verificar se um e-mail é válido.
	 * 
	 * @param email E-mail a ser validado.
	 * @return Verdadeiro caso seja valido. Falso, caso contrario.
	 */
	public static boolean isValido(String email) {
		Pattern pattern = Pattern.compile (Diversos.EMAIL, 
		         Pattern.MULTILINE);
		Matcher m=pattern.matcher(email);
		return m.matches();
	}

}