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
package org.alfredlibrary.utilitarios.horoscopos;

import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.alfredlibrary.utilitarios.texto.HTML;

/**
 * Utilitário para obter o horóscopo de um Signo através do Portal Terra.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/05/2010
 */
final public class PortalTerra {
	
	private PortalTerra() {
		throw new AssertionError();
	}

	/**
	 * Obter o horóscopo para um Signo.
	 * 
	 * @param signo Signo.
	 * @return Horóscopo.
	 */
	public static String obter(Signo signo) {
		String url = "http://www.terra.com.br/esoterico/horoscopo/seu-signo-diario-" + signo + ".htm";
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		String horoscopo = conteudo.substring(conteudo.indexOf("<!--- TEXTO --->"), conteudo.indexOf("<!--- /TEXTO --->"));
		return HTML.removerTags(horoscopo);
	}

}