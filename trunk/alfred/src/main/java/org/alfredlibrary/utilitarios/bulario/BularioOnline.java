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
package org.alfredlibrary.utilitarios.bulario;

import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.alfredlibrary.utilitarios.texto.HTML;

/**
 * Obter bula de um remédio através do site Bulário Online.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/05/2010
 */
final public class BularioOnline {
	
	private BularioOnline() {
		throw new AssertionError();
	}

	/**
	 * Obter a bula de um remédio.
	 * 
	 * @param nomeRemedio Nome do remédio.
	 * @return Bula do remédio.
	 */
	public static String obter(String nomeRemedio) {
		String url = "http://www.bulario.net/" + nomeRemedio.replaceAll(" ", "_") + "/";
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		String bula = conteudo.substring(conteudo.indexOf("<a name=\"indicacoes\" rel=\"nofollow\">"), 
				conteudo.indexOf("<a name=\"laboratorio\" rel=\"nofollow\"></a>", conteudo.indexOf("a name=\"indicacoes\" rel=\"nofollow\">")));
		return HTML.removerTags(HTML.desconverterElementosHTMLEspeciais(bula, 0));
	}

}