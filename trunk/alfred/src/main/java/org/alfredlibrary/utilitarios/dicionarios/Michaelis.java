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
package org.alfredlibrary.utilitarios.dicionarios;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.alfredlibrary.utilitarios.texto.HTML;

/**
 * Obter o significado de uma palavra através do Michaelis.
 * 
 * @author Marlon Silva Carvalho
 * @since 27/04/2010
 */
final public class Michaelis {
	
	private Michaelis() {
		throw new AssertionError();
	}

	public static String obterSignificado(String palavra, boolean formatacaoHTML) {
		String url = "http://michaelis.uol.com.br/moderno/portugues/index.php?lingua=portugues-portugues&palavra=" + palavra;
		String resultado = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		String parte = resultado.substring(resultado.indexOf("<span class='descricao'>"));
		parte = parte.substring(0,parte.indexOf("</span>"));
		parte = HTML.desconverterElementosHTMLEspeciais(parte, 0);
		if ( ! formatacaoHTML )
			parte = HTML.removerTags(parte);
		try {
			parte = URLDecoder.decode(parte,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new AlfredException(e);
		}
		return parte;
	}

}