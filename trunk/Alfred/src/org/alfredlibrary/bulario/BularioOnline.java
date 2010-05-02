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
package org.alfredlibrary.bulario;

import org.alfredlibrary.net.WorldWideWeb;
import org.alfredlibrary.texto.Texto;

/**
 * Obter bula de um remédio através do site Bulário Online.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/05/2010
 */
final public class BularioOnline {

	/**
	 * Obter a bula de um remédio.
	 * 
	 * @param nomeRemedio Nome do remédio.
	 * @return Bula do remédio.
	 */
	public static String obter(String nomeRemedio) {
		String url = "http://www.bulario.net/" + nomeRemedio.replaceAll(" ", "_") + "/";
		String conteudo = WorldWideWeb.getConteudoSite(url);
		String bula = conteudo.substring(conteudo.indexOf("<a name=\"indicacoes\" rel=\"nofollow\">"), 
				conteudo.indexOf("<a name=\"laboratorio\" rel=\"nofollow\"></a>", conteudo.indexOf("a name=\"indicacoes\" rel=\"nofollow\">")));
		return Texto.removerTags(Texto.desconverterElementosHTMLEspeciais(bula, 0));
	}

}