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
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilitário para HTML.
 * 
 * @author Marlon Silva Carvalho
 * @since 10/05/2010
 */
final public class HTML {

	private HTML() {
	}

	/**
	 * Encontrar e retornar todos os links encontrados no texto.
	 * 
	 * @param texto
	 *            Texto.
	 * @return Array de links encontrados.
	 */
	public static String[] acharLinks(String texto) {
		Collection<String> encontrados = new ArrayList<String>();
		String pattern = "https?://([-\\w\\.]+)+(:\\d+)?(/([\\w/_\\.]*(\\?\\S+)?)?)?";
		Pattern padrao = Pattern.compile(pattern);
		Matcher pesquisa = padrao.matcher(texto);
		while (pesquisa.find()) {
			encontrados.add(pesquisa.group());
		}
		return encontrados.toArray(new String[] {});
	}

	/**
	 * Remover todas as tags de um texto.
	 * 
	 * @param texto
	 *            Texto que terá as tags removidas.
	 * @return Texto com as tags removidas.
	 */
	public static String removerTags(String texto) {
		String noHTMLString = texto.replaceAll("\\<.*?\\>", "");
		return noHTMLString;
	}

	/**
	 * Substituir do texto os elementos HTML especiais, como &nbsp;, pelo valor
	 * correspondente em ASCII. Código original em
	 * http://www.rgagnon.com/javadetails/java-0307.html.
	 * 
	 * @param source
	 *            Código que terá o texto trocado.
	 * @param start
	 *            Onde iniciar a troca.
	 * @return Texto com os valores convertidos.
	 */
	public static String desconverterElementosHTMLEspeciais(String source,
			int start) {
		HashMap<String, String> htmlEntities;
		htmlEntities = new HashMap<String, String>();
		htmlEntities.put("&lt;", "<");
		htmlEntities.put("&gt;", ">");
		htmlEntities.put("&amp;", "&");
		htmlEntities.put("&quot;", "\"");
		htmlEntities.put("&agrave;", "à");
		htmlEntities.put("&Agrave;", "À");
		htmlEntities.put("&atilde;", "ã");
		htmlEntities.put("&Atilde;", "Ã");
		htmlEntities.put("&aacute;", "á");
		htmlEntities.put("&Aacute;", "Á");
		htmlEntities.put("&acirc;", "â");
		htmlEntities.put("&auml;", "ä");
		htmlEntities.put("&Auml;", "Ä");
		htmlEntities.put("&Acirc;", "Â");
		htmlEntities.put("&aring;", "å");
		htmlEntities.put("&Aring;", "Å");
		htmlEntities.put("&aelig;", "æ");
		htmlEntities.put("&AElig;", "Æ");
		htmlEntities.put("&ccedil;", "ç");
		htmlEntities.put("&Ccedil;", "Ç");
		htmlEntities.put("&eacute;", "é");
		htmlEntities.put("&Eacute;", "É");
		htmlEntities.put("&egrave;", "è");
		htmlEntities.put("&Egrave;", "È");
		htmlEntities.put("&ecirc;", "ê");
		htmlEntities.put("&Ecirc;", "Ê");
		htmlEntities.put("&euml;", "ë");
		htmlEntities.put("&Euml;", "Ë");
		htmlEntities.put("&iuml;", "ï");
		htmlEntities.put("&Iuml;", "Ï");
		htmlEntities.put("&iacute;", "í");
		htmlEntities.put("&Iacute;", "Í");
		htmlEntities.put("&ocirc;", "ô");
		htmlEntities.put("&Ocirc;", "Ô");
		htmlEntities.put("&otilde;", "õ");
		htmlEntities.put("&Otilde;", "Õ");
		htmlEntities.put("&oacute;", "ó");
		htmlEntities.put("&Oacute;", "Ó");
		htmlEntities.put("&uacute;", "ú");
		htmlEntities.put("&Uacute;", "Ú");
		htmlEntities.put("&ouml;", "ö");
		htmlEntities.put("&Ouml;", "Ö");
		htmlEntities.put("&oslash;", "ø");
		htmlEntities.put("&Oslash;", "Ø");
		htmlEntities.put("&szlig;", "ß");
		htmlEntities.put("&ugrave;", "ù");
		htmlEntities.put("&Ugrave;", "Ù");
		htmlEntities.put("&ucirc;", "û");
		htmlEntities.put("&Ucirc;", "Û");
		htmlEntities.put("&uuml;", "ü");
		htmlEntities.put("&Uuml;", "Ü");
		htmlEntities.put("&nbsp;", " ");
		htmlEntities.put("&copy;", "\u00a9");
		htmlEntities.put("&reg;", "\u00ae");
		htmlEntities.put("&euro;", "\u20a0");
		int i, j;
		i = source.indexOf("&", start);
		if (i > -1) {
			j = source.indexOf(";", i);
			if (j > i) {
				String entityToLookFor = source.substring(i, j + 1);
				String value = (String) htmlEntities.get(entityToLookFor);
				if (value != null) {
					source = new StringBuffer().append(source.substring(0, i))
							.append(value).append(source.substring(j + 1))
							.toString();
				}
				return desconverterElementosHTMLEspeciais(source, i + 1);
			}
		}
		return source;
	}

	/**
	 * Converte caracteres especiais para elementos HTML. Código
	 * "gentilmente sugado" do site
	 * http://www.rgagnon.com/javadetails/java-0306.html.
	 * 
	 * @param s
	 *            String que terá os elementos substituídos.
	 * @return
	 */
	public static final String converterParaElementosHTMLEspeciais(String s) {
		StringBuffer sb = new StringBuffer();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			switch (c) {
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case 'à':
				sb.append("&agrave;");
				break;
			case 'À':
				sb.append("&Agrave;");
				break;
			case 'â':
				sb.append("&acirc;");
				break;
			case 'Â':
				sb.append("&Acirc;");
				break;
			case 'ä':
				sb.append("&auml;");
				break;
			case 'Ä':
				sb.append("&Auml;");
				break;
			case 'å':
				sb.append("&aring;");
				break;
			case 'Å':
				sb.append("&Aring;");
				break;
			case 'æ':
				sb.append("&aelig;");
				break;
			case 'Æ':
				sb.append("&AElig;");
				break;
			case 'ç':
				sb.append("&ccedil;");
				break;
			case 'Ç':
				sb.append("&Ccedil;");
				break;
			case 'é':
				sb.append("&eacute;");
				break;
			case 'É':
				sb.append("&Eacute;");
				break;
			case 'è':
				sb.append("&egrave;");
				break;
			case 'È':
				sb.append("&Egrave;");
				break;
			case 'ê':
				sb.append("&ecirc;");
				break;
			case 'Ê':
				sb.append("&Ecirc;");
				break;
			case 'ë':
				sb.append("&euml;");
				break;
			case 'Ë':
				sb.append("&Euml;");
				break;
			case 'ï':
				sb.append("&iuml;");
				break;
			case 'Ï':
				sb.append("&Iuml;");
				break;
			case 'ô':
				sb.append("&ocirc;");
				break;
			case 'Ô':
				sb.append("&Ocirc;");
				break;
			case 'ö':
				sb.append("&ouml;");
				break;
			case 'Ö':
				sb.append("&Ouml;");
				break;
			case 'ø':
				sb.append("&oslash;");
				break;
			case 'Ø':
				sb.append("&Oslash;");
				break;
			case 'ß':
				sb.append("&szlig;");
				break;
			case 'ù':
				sb.append("&ugrave;");
				break;
			case 'Ù':
				sb.append("&Ugrave;");
				break;
			case 'û':
				sb.append("&ucirc;");
				break;
			case 'Û':
				sb.append("&Ucirc;");
				break;
			case 'ü':
				sb.append("&uuml;");
				break;
			case 'Ü':
				sb.append("&Uuml;");
				break;
			case '®':
				sb.append("&reg;");
				break;
			case '©':
				sb.append("&copy;");
				break;
			case '€':
				sb.append("&euro;");
				break;
			// be carefull with this one (non-breaking whitee space)
			case ' ':
				sb.append("&nbsp;");
				break;

			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

}