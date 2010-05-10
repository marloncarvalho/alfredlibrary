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
package org.alfredlibrary.utilitarios.texto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilitário para HTML.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/05/2010
 */
final public class HTML {
	
	private HTML() { }
	
	/**
	 * Encontrar e retornar todos os links encontrados no texto.
	 * 
	 * @param texto Texto.
	 * @return Array de links encontrados.
	 */
	public static String[] acharLinks(String texto) {
		Collection<String> encontrados = new ArrayList<String>();
		String pattern = "https?://([-\\w\\.]+)+(:\\d+)?(/([\\w/_\\.]*(\\?\\S+)?)?)?";
		Pattern padrao = Pattern.compile(pattern);  
		Matcher pesquisa = padrao.matcher(texto);
		while(pesquisa.find()) {
			encontrados.add(pesquisa.group());
		}
		return encontrados.toArray(new String[]{});
	}

}