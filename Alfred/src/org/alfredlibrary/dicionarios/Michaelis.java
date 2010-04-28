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
package org.alfredlibrary.dicionarios;

import org.alfredlibrary.net.WorldWideWeb;

/**
 * Obter o significado de uma palavra através do Michaelis.
 * 
 * @author Marlon Silva Carvalho
 * @since 27/04/2010
 */
final public class Michaelis {

	public static String obterSignificado(String palavra) {
		String url = "http://michaelis.uol.com.br/moderno/portugues/index.php?lingua=portugues-portugues&palavra=" + palavra;
		String resultado = WorldWideWeb.getConteudoSite(url);
		return null;
	}

}