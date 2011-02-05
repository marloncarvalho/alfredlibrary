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
package org.alfredlibrary.utilitarios.noticiarios;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/** 
 * Obter resultado de Noticias do BR-Linux.
 * 
 * @author Carlos Daniel de Mattos Mercer
 * @since 21/07/2009
 */
final public class BRLinux {
	
	public BRLinux() {
		throw new AssertionError();
	}

	private static String resultado = "";

	/**
	 * Obter as noticias do BR-Linux.
	 * 
	 * @return Titulos e Links das noticias do BR-Linux.
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static String[] obterNoticiasBRLinux() {
		String url;
		url = "http://br-linux.org/feed/";
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");

		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = parserFactory.newSAXParser();
		} catch (ParserConfigurationException e) {
			throw new AlfredException(e);
		} catch (SAXException e) {
			throw new AlfredException(e);
		}

		Reader reader = new StringReader(conteudo);
		InputSource inputSource = new InputSource(reader);

		DefaultHandler handler = new DefaultHandler() {
			boolean title;
			boolean link;

			public void startElement(String uri, String localName,
					String element_name, Attributes attributes) {
				if (element_name.equals("title")) {
					title = true;
				}
				if (element_name.equals("link")) {
					link = true;
				}
			}

			public void characters(char[] ch, int start, int len) {
				String str = new String(ch, start, len);
				if (title) {
					resultado += str + "|";
					title = false;
				}
				if (link) {
					resultado += str + "|";
					link = false;
				}
			}
		};

		try {
			parser.parse(inputSource, handler);
		} catch (SAXException e) {
			throw new AlfredException(e);
		} catch (IOException e) {
			throw new AlfredException(e);
		}
		String[] resultadoNoticias = resultado.split("\\|");
		return resultadoNoticias;
	}

}