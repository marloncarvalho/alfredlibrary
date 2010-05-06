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
package org.alfredlibrary.utilitarios.clima.climatempo;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * Utilit�rio para obter informa��es sobre o Clima atrav�s do site Climatempo.
 * 
 * @author Marlon Silva Carvalho
 * @since 26/05/2010
 */
final public class Climatempo {

	/**
	 * Obter as informa��es de Clima atrav�s do site Climatempo.
	 * Retorna um objeto Clima contendo a Data, M�xima, M�nima e a Frase para o dia.
	 * 
	 * @param codigo C�digo da cidade. Deve ser obtido atrav�s do site da Climatempo. Pode usar v�rios c�digos de cidade separados por v�rgula.
	 * @return Cole��o de Climas, um para cada dia e para as cidades informadas.
	 */
	public static Collection<Clima> obterClima(String codigo) {
		String url = "http://selos.climatempo.com.br/selos/selo.php?CODCIDADE=" + codigo;
		String conteudoXML = WorldWideWeb.getConteudoSite(url);
		Collection<Clima> climas = new ArrayList<Clima>();
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(conteudoXML)));
			NodeList nodes = doc.getFirstChild().getChildNodes();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_MONTH,-1);
			for(int indice=0; indice < nodes.getLength(); indice++ ) {
				Node node = nodes.item(indice);
				if ( node.getNodeType() == Node.ELEMENT_NODE ) {
					if (node.getNodeName().equals( "cidade" )) {
						calendar.add(Calendar.DAY_OF_MONTH,1);
						Element element = (Element) node;
						Clima clima = new Clima();
						clima.setData(calendar.getTime());
						clima.setFrase(element.getAttribute("frase"));
						clima.setMaxima(Integer.valueOf(element.getAttribute("high")));
						clima.setMinima(Integer.valueOf(element.getAttribute("low")));
						climas.add(clima);
					}
				}
			}
		} catch (ParserConfigurationException e) {
			throw new AlfredException(e);
		} catch (SAXException e) {
			throw new AlfredException(e);
		} catch (IOException e) {
			throw new AlfredException(e);
		}  
		return climas;
	}

}