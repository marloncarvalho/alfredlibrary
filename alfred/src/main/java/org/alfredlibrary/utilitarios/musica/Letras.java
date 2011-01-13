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
package org.alfredlibrary.utilitarios.musica;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.alfredlibrary.utilitarios.texto.HTML;

/**
 * Obter a letra de uma música.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/05/2010
 */
final public class Letras {

	
	private Letras() {
		throw new AssertionError();
	}
	/**
	 * Obter a letra de uma música.
	 * 
	 * @param nomeMusica Nome da Música.
	 * @return Letra da Música.
	 */
	public static String obter(String artista, String nomeMusica) {
		String artistaTrocado = artista.toLowerCase().replaceAll(" ", "-");
		String nomeMusicaTrocado = nomeMusica.toLowerCase().replaceAll(" ", "-");
		String url = "http://www.vagalume.com.br/" + artistaTrocado + "/" + nomeMusicaTrocado + ".html";
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		if ( conteudo.indexOf("Oops") > -1 || conteudo.indexOf("<div class=\"tab_original sideBySide lyricArea originalOnly\">") == -1) {
			throw new AlfredException("Letra ou artista não encontrado.");
		}
		String letra = conteudo.substring(conteudo.indexOf("<div class=\"tab_original sideBySide lyricArea originalOnly\">"), conteudo.indexOf("</div>", conteudo.indexOf("<div class=\"tab_original sideBySide lyricArea originalOnly\">")));
		letra = HTML.desconverterElementosHTMLEspeciais(letra, 0);
		letra = HTML.removerTags(letra);
		return letra;
	}

}