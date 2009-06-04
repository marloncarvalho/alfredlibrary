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
package net.marloncarvalho.alfred.correios;

import net.marloncarvalho.alfred.AlfredException;

/**
 * Classe utilitária para CEPs.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
final public class CEP {

	private CEP() {}

	/**
	 * Realizar a formatação do CEP passado.
	 * 
	 * @param cep CEP a ser formatado.
	 * @return CEP formatado.
	 */
	public static String formatar(String cep) {
		if ( "".equals(cep) || cep.length() != 8 )
			throw new AlfredException("Informe um CEP válido.");
		return new StringBuilder().append(cep.substring(0,2)).append(".").append(cep.substring(2,5)).append("-").append(cep.substring(5,8)).toString();
	}

}