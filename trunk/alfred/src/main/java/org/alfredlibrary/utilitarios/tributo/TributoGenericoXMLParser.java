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
package org.alfredlibrary.utilitarios.tributo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.formatadores.Data;
import org.alfredlibrary.utilitarios.texto.Texto;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Utilitário que lê o XML de tributo e busca alíquotas. 
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 18/09/2010
 */
public final class TributoGenericoXMLParser {
	
	/**
	 * Obter as alíquotas corretas sobre um determinado tributo conforme a
	 * vigência.
	 * 
	 * @param arquivo .xml de tributos, conforme .dtd publicados em
	 * 					http://code.google.com/p/alfredlibrary
	 * @param dataFatoGerador Data do fato gerador para obtenção das alíquotas
	 * @param incidencia Texto que define a incidência do tributo buscado. Caso
	 * 						seja nulo, ignora as ocorrências de incidência no XML. 
	 * @return Lista de alíquotas
	 */
	public static Collection<Map<String,Object>> obterAliquotas(File arquivo, Date dataFatoGerador, String incidencia) {
		Collection<Map<String,Object>> resultado = new ArrayList<Map<String,Object>>();
		/*
		 * <!ELEMENT tributo (nome,(vigencia+|aliquota+))>
		 * 	<!ELEMENT vigencia (inicio?,termino?,aliquota+)>
		 * 		<!ELEMENT inicio (#PCDATA)>
		 * 		<!ELEMENT termino (#PCDATA)>
		 * 	<!ELEMENT aliquota (piso-faixa?,teto-faixa?,valor,incidencia*)>
		 * 		<!ELEMENT piso-faixa (#PCDATA)>
		 * 		<!ELEMENT teto-faixa (#PCDATA)>
		 * 		<!ELEMENT valor (#PCDATA)>
		 * 		<!ELEMENT incidencia (#PCDATA)>
		 */
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(arquivo);
		
			Node noPrincipal = null;
			NodeList listNoPrincipal = doc.getChildNodes(); 
			for (int indice = 0; indice < listNoPrincipal.getLength(); indice++) {
				Node noCandidato = listNoPrincipal.item(indice);
				if (noCandidato.getNodeType() == Node.ELEMENT_NODE) {
					if (noCandidato.getNodeName().equals("tributo")) {
						noPrincipal = noCandidato;
					}
				}
			}
			
			if (noPrincipal == null) {
				throw new AlfredException("Nó principal não encontrado no XML de Tributo!");
			}
			
			// Itera sobre as Vigências ou Alíquotas
			NodeList listNoNivel2 = noPrincipal.getChildNodes();
			for (int indiceNivel2 = 0; indiceNivel2 < listNoNivel2.getLength(); indiceNivel2++) {
				Node noCandidato = listNoNivel2.item(indiceNivel2);
				if ( noCandidato.getNodeType() == Node.ELEMENT_NODE ) {
					if (noCandidato.getNodeName().equals( "vigencia" )) {
						resultado.addAll(obterDeVigencia(noCandidato, dataFatoGerador, incidencia));
					} else if (noCandidato.getNodeName().equals( "aliquota" )) {
						if (obterMapAliquota(noCandidato, null, null, incidencia) != null) {
							resultado.add(obterMapAliquota(noCandidato, null, null, incidencia));
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw new AlfredException ("Erro na configuração do Parser!");
		} catch (SAXException e) {
			e.printStackTrace();
			throw new AlfredException ("Erro durante o parsing do XML do Tributo!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new AlfredException ("Erro na localização ou leitura do XML do Tributo!");
		}
		
		return resultado;
	}

	/**
	 * Faz a busca em um determinado registro de Vigência, do XML de Tributo.
	 * 
	 * @param noPai Nó do tipo Vigência passado para análise.
	 * @param dataFatoGerador Data do Fato Gerador, para filtrar pelas vigências pertinentes.
	 * @param incidencia Texto que define a incidência do tributo buscado. Caso
	 * 						seja nulo, ignora as ocorrências de incidência no XML.
	 * @return Lista de alíquotas vigentes na data informada.
	 */
	private static Collection<Map<String,Object>> obterDeVigencia(
			Node noPai, Date dataFatoGerador, String incidencia) {
		
		Collection<Map<String,Object>> resultado = new ArrayList<Map<String,Object>>();
		
		/* <!ELEMENT vigencia (inicio?,termino?,aliquota+)>
		 * 		<!ELEMENT inicio (#PCDATA)>
		 * 		<!ELEMENT termino (#PCDATA)>
		 */
		
		NodeList listNo = noPai.getChildNodes();
		Date inicio = Calendar.getInstance().getTime();
		Date termino = inicio;
		// Primeiro varre os filhos para definir a vigência, sem se preocupar com a ordem dos nós
		for (int indice = 0; indice < listNo.getLength(); indice++) {
			Node noCandidato = listNo.item(indice);
			if ( noCandidato.getNodeType() == Node.ELEMENT_NODE ) {
				if (noCandidato.getNodeName().equals( "inicio" )) {
					inicio = Data.formatar(extrairTextNode(noCandidato), "dd/MM/yyyy");
				} else if (noCandidato.getNodeName().equals( "termino" )) {
					if (extrairTextNode(noCandidato) != null ? !extrairTextNode(noCandidato).equals("") : false) {
						termino = Data.formatar(extrairTextNode(noCandidato), "dd/MM/yyyy");
					} else {
						termino = Calendar.getInstance().getTime();
					}
				}
			}
		}
		// Controla verificação de alíquotas
		boolean verificaAliquotas = false;
		if (dataFatoGerador == null) {
			verificaAliquotas = true;
		} else if (termino == null && dataFatoGerador.compareTo(inicio) >= 0) {
			verificaAliquotas = true;
		} else if (dataFatoGerador.compareTo(termino) <= 0 && dataFatoGerador.compareTo(inicio) >= 0) {
			verificaAliquotas = true;
		}
		// Varre os nós procurando apenas as alíquotas
		if (verificaAliquotas) {
			for (int indice = 0; indice < listNo.getLength(); indice++) {
				Node noCandidato = listNo.item(indice);
				if ( noCandidato.getNodeType() == Node.ELEMENT_NODE ) {
					if (noCandidato.getNodeName().equals( "aliquota" )) {
						if (obterMapAliquota(noCandidato, inicio, termino, incidencia) != null) {
							resultado.add(obterMapAliquota(noCandidato, inicio, termino, incidencia));
						}
					}
				}
			}
		}
		return resultado;
	}

	/**
	 * Prepara o registro de Alíquota para fornecer ao cálculo tributário
	 * 
	 * @param noPai Nó do tipo Alíquota passado para análise.
	 * @param vigenciaInicio Data inicial de vigência da alíquota de acordo com as características
	 * 							do nó superior, do tipo Vigência. Se o tributo não possuir
	 * 							detalhamento de vigências, tem valor nulo.
	 * @param vigenciaTermino Data final de vigência da alíquota de acordo com as características
	 * 							do nó superior, do tipo Vigência. Se o tributo não possuir
	 * 							detalhamento de vigências, tem valor nulo.
	 * @param incidencia Texto que define a incidência do tributo buscado. Caso
	 * 						seja nulo, ignora as ocorrências de incidência no XML.
	 * @return Lista de alíquotas.
	 */
	private static Map<String,Object> obterMapAliquota(
			Node noPai, Date vigenciaInicio, Date vigenciaTermino, String incidencia) {
		
		/*
		 * 	<!ELEMENT aliquota (piso-faixa?,teto-faixa?,valor,incidencia*)>
		 * 		<!ELEMENT piso-faixa (#PCDATA)>
		 * 		<!ELEMENT teto-faixa (#PCDATA)>
		 * 		<!ELEMENT valor (#PCDATA)>
		 * 		<!ELEMENT incidencia (#PCDATA)>
		 */
		if (incidencia != null) {
			incidencia = Texto.trocarCaracteresAcentuados(Texto.removerPontuacao(incidencia)).trim().toUpperCase();
		}
		
		Map<String,Object> resultado = new HashMap<String,Object>();
		resultado.put("inicio-vigencia", vigenciaInicio);
		resultado.put("termino-vigencia", vigenciaTermino);
		
		boolean incidenciaEncontrada = false;
		Collection<String> colIncidencia = new ArrayList<String>();
		NodeList listNo = noPai.getChildNodes();
		for (int indice = 0; indice < listNo.getLength(); indice++) {
			Node noCandidato = listNo.item(indice);
			if ( noCandidato.getNodeType() == Node.ELEMENT_NODE ) {
				if (noCandidato.getNodeName().equals( "piso-faixa" )) {
					resultado.put("piso-faixa", extrairTextNode(noCandidato));
				} else if (noCandidato.getNodeName().equals( "teto-faixa" )) {
					resultado.put("teto-faixa", extrairTextNode(noCandidato));
				} else if (noCandidato.getNodeName().equals( "valor" )) {
					resultado.put("valor", extrairTextNode(noCandidato));
				} else if (noCandidato.getNodeName().equals( "incidencia" )) {
					colIncidencia.add(extrairTextNode(noCandidato));
					if (incidencia == null){
						incidenciaEncontrada = true;
					} else if (Texto.trocarCaracteresAcentuados(Texto.removerPontuacao(extrairTextNode(noCandidato))).trim().toUpperCase()
							.equals(incidencia)) {
						incidenciaEncontrada = true;
					}
				}
			}
		}
		
		resultado.put("incidencia", colIncidencia);
		
		if (incidencia == null || incidenciaEncontrada) {
			return resultado;
		} else {
			return null;
		}
	}
	
	/**
	 *  Extrai o texto de um nó
	 * @param no
	 * @return Texto contido no nó
	 */
	private static String extrairTextNode(Node no) {
		NodeList listNoCandidato = no.getChildNodes(); 
		for (int indice = 0; indice < listNoCandidato.getLength(); indice++) {
			Node noCandidato = listNoCandidato.item(indice);
			if (noCandidato.getNodeType() == Node.TEXT_NODE) {
				return noCandidato.getTextContent();
			}
		}
		return "";
	}

}