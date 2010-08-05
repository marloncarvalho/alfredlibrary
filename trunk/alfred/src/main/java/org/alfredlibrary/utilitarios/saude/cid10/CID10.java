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
package org.alfredlibrary.utilitarios.saude.cid10;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.texto.Texto;
import org.alfredlibrary.validadores.Numeros;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Utilitário para obter informações sobre doenças pelo CID-10, baseado na versão V2007 do CID-10.
 * 
 * Apenas a versão V2007 do arquivo CID10.xml é compatível com este utilitário. Essa versão pode
 * ser obtida no site da Datasus (http://www.datasus.gov.br/cid10/v2008/cid10.htm).
 * 
 * Caso a versão do arquivo seja alterada pela Datasus, reporte o problema para os colaboradores
 * da alfredlibrary (http://www.alfredlibrary.org).  
 * 
 * @author Rodrigo Moreira Fagundes
 * @since 08/06/2010
 */
final public class CID10 {
	
	private CID10() { }
	
	/*
	 *	<!ELEMENT document (cid10)>
	 *
	 *	<!ELEMENT cid10 (capitulo+)>
	 *	<!ATTLIST cid10 versao CDATA #REQUIRED>
	 *	<!ELEMENT capitulo (nome, nome50, (grupo|categoria)+)>
	 *	<!ATTLIST capitulo numcap NMTOKEN #REQUIRED>
	 *	<!ATTLIST capitulo codcap ID #IMPLIED>
	 *	<!ATTLIST capitulo romano CDATA #IMPLIED>
	 *	<!ATTLIST capitulo inicial IDREF #REQUIRED>
	 *	<!ATTLIST capitulo final   IDREF #REQUIRED>
	 *		<!ELEMENT grupo (nome, nome50, (grupo|categoria)+)>
	 *		<!ATTLIST grupo codgrupo ID #REQUIRED>
	 *		<!ATTLIST grupo inicial IDREF #REQUIRED>
	 *		<!ATTLIST grupo final   IDREF #REQUIRED>
	 *				<!ELEMENT categoria (duplaclassificacao?, nome, nome50, restricoes?, 
	 *					subcategoria*)>
	 *				<!ATTLIST categoria codcat ID #REQUIRED>
	 *				<!ATTLIST categoria ehsubcat (sim|nao) "nao">
	 *					<!ELEMENT subcategoria (duplaclassificacao?, nome, nome50, restricoes?,
	 *						categoriasabsorvidas*)>
	 *					<!ATTLIST subcategoria codsubcat ID #REQUIRED>
	 *
	 *	<!ELEMENT restricoes EMPTY>
	 *		<!ATTLIST restricoes causaobito (sim|nao) "sim">
	 *		<!ATTLIST restricoes sexo (apenas_homens|apenas_mulheres|ambos) "ambos">
	 *	
	 *	<!ELEMENT duplaclassificacao (referencia*)>
	 *		<!ATTLIST duplaclassificacao tipo (cruz|asterisco) #REQUIRED>
	 *		<!ELEMENT referencia (#PCDATA)>
	 *			<!ATTLIST referencia codrefer IDREF #IMPLIED>
	 *	
	 *	<!ELEMENT nome (#PCDATA)>
	 *
	 *	<!ELEMENT nome50 (#PCDATA)>
	 *
	 *	<!ELEMENT categoriasabsorvidas EMPTY>
	 *		<!ATTLIST categoriasabsorvidas codsubcat ID #REQUIRED>
	 *
	 *	<!ENTITY cruz "&#8224;">
	 *
	 *	<!ENTITY aster "*">
	 */
	
	/**
	 * Prepara um código CID-10 para validação e busca.
	 * 
	 * @param codigo Código CID.
	 * @return Código CID reformatado apenas com letras e números.
	 */
	private static String preparar(String codigo) {
		codigo = Texto.trocarCaracteresAcentuados(codigo).toUpperCase();
		char[] chars = codigo.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int indice = 0; indice < chars.length; indice++) {
			if (Numeros.isInteger(String.valueOf(chars[indice]))) {
				sb.append(chars[indice]);
			} else {
				if (String.valueOf(chars[indice]).compareTo("A") >= 0 &&
						String.valueOf(chars[indice]).compareTo("Z") <= 0) {
					sb.append(chars[indice]);
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * Valida um código CID-10.
	 * 
	 * @param codigo Código CID.
	 * @return Validação do código CID.
	 */
	private static void validar(String codigo) {
		if (codigo == null)
			throw new AlfredException("O código CID informado é nulo.");
		if ("".equals(codigo))
			throw new AlfredException("O código CID informado é vazio.");
		if (codigo.length() > 4 || codigo.length() <= 2)
			throw new AlfredException("O código CID não é válido. Composição de letras e números com tamanho superior a 4.");
		if (!codigo.substring(1).equals(Texto.manterNumeros(codigo.substring(1)))) {
			throw new AlfredException("O código CID não é válido. Há mais de uma letra.");
		}
	}
	
	/**
	 * Obter as informações sobre doenças pelo código no CID-10.
	 * 
	 * @param arquivo CID10.xml, na versão V2007, obtido no site
	 * 					http://www.datasus.gov.br/cid10/v2008/cid10.htm
	 * 
	 * @param codigo Código CID.
	 * @return Lista de registros no CID-10 encontrados.
	 */
	public static Collection<Map<String,Object>> obter(File arquivo, String codigo) {
		codigo = preparar(codigo);
		validar(codigo);
		return obter(arquivo, codigo, true, "codigo");
	}
	
	/**
	 * Obter as informações sobre doenças por parte do nome no CID-10.
	 * 
	 * @param arquivo CID10.xml, na versão V2007, obtido no site
	 * 					http://www.datasus.gov.br/cid10/v2008/cid10.htm
	 * @param texto Nome para busca.
	 * @param inicio Se pesquisa deve ser pelo início (TRUE) ou qualquer parte da descrição da doença (FALSE)
	 * @return Lista de registros no CID-10 encontrados.
	 */
	public static Collection<Map<String,Object>> obter(File arquivo, String texto, boolean inicio) {
		return obter(arquivo, texto, inicio, "texto");
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
	
	/**
	 * Inicia a busca no CID-0.
	 * 
	 * @param arquivo CID10.xml, na versão V2007, obtido no site
	 * 					http://www.datasus.gov.br/cid10/v2008/cid10.htm
	 * @param parametro Código CID ou Texto para busca.
	 * @param tipoBusca Tipo da busca realizada (texto ou código)
	 * @param inicio Se pesquisa deve ser pelo início (TRUE) ou qualquer parte da descrição da doença (FALSE)
	 * @return Lista de registros no CID-10 encontrados.
	 */
	private static Collection<Map<String,Object>> obter(File arquivo, String parametro, boolean inicio, String tipoPesquisa) {
		Collection<Map<String,Object>> resultado = new ArrayList<Map<String,Object>>();
				
		try {
			
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(arquivo);
		
			Node noPrincipal = null;
			NodeList listNoPrincipal = doc.getChildNodes(); 
			for (int indice = 0; indice < listNoPrincipal.getLength(); indice++) {
				Node noCandidato = listNoPrincipal.item(indice);
				if (noCandidato.getNodeType() == Node.ELEMENT_NODE) {
					if (noCandidato.getNodeName().equals("cid10")) {
						noPrincipal = noCandidato;
					}
				}
			}
			
			if (noPrincipal == null) {
				throw new AlfredException("Nó principal não encontrado no XML de CID-10!");
			} else if (! noPrincipal.getAttributes().getNamedItem("versao").getNodeValue().equals("V2007")) {
				throw new AlfredException("XML incompatível! Alfred compatível com versão V2007 do XML de CID-10!");
			}
			
			/*	<!ELEMENT cid10 (capitulo+)>
			 *	<!ATTLIST cid10 versao CDATA #REQUIRED>
			 */
			
			// Itera sobre os Capítulos
			NodeList listCapitulo = noPrincipal.getChildNodes();
			for (int indiceCapitulo = 0; indiceCapitulo < listCapitulo.getLength(); indiceCapitulo++) {
				Node noCapitulo = listCapitulo.item(indiceCapitulo);
				if ( noCapitulo.getNodeType() == Node.ELEMENT_NODE ) {
					if (noCapitulo.getNodeName().equals( "capitulo" )) {
						resultado.addAll(obterDeCapitulo(noCapitulo, parametro, inicio, tipoPesquisa));
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw new AlfredException ("Erro na configuração do Parser!");
		} catch (SAXException e) {
			e.printStackTrace();
			throw new AlfredException ("Erro durante o parsing do XML do CID-10!");
		} catch (IOException e) {
			e.printStackTrace();
			throw new AlfredException ("Erro na localização ou leitura do XML do CID-10!");
		}
		return resultado;
	}

	/**
	 * Faz a busca em um determinado capítulo do CID-10.
	 * 
	 * @param noCapitulo Nó do tipo Capítulo passado para análise.
	 * @param parametro Código CID ou Texto para busca.
	 * @param tipoBusca Tipo da busca realizada (texto ou código)
	 * @param inicio Se a busca é pelo início (TRUE) ou qualquer parte do parâmetro de busca (FALSE)
	 * @return Lista de registros no CID-10 encontrados.
	 */
	private static Collection<Map<String,Object>> obterDeCapitulo(Node noCapitulo, String parametro, boolean inicio, String tipoBusca) {
		/*
		 * 	<!ELEMENT capitulo (nome, nome50, (grupo|categoria)+)>
		 *	<!ATTLIST capitulo numcap NMTOKEN #REQUIRED>
		 *	<!ATTLIST capitulo codcap ID #IMPLIED>
		 *	<!ATTLIST capitulo romano CDATA #IMPLIED>
		 *	<!ATTLIST capitulo inicial IDREF #REQUIRED>
		 *	<!ATTLIST capitulo final   IDREF #REQUIRED>
		 */
		String nome = "";
		String nome50 = "";
		boolean adicionaNo = false;
		Collection<Map<String,Object>> resultado = new ArrayList<Map<String,Object>>();

		NodeList listGrupoCategoria = noCapitulo.getChildNodes();
		for (int indiceGrupoCategoria = 0; indiceGrupoCategoria < listGrupoCategoria.getLength(); indiceGrupoCategoria++) {
			Node noGrupoCategoria = listGrupoCategoria.item(indiceGrupoCategoria);
			if ( noGrupoCategoria.getNodeType() == Node.ELEMENT_NODE ) {
				// Testes do próprio elemento
				if (noGrupoCategoria.getNodeName().equals( "nome" )) {
					nome = extrairTextNode(noGrupoCategoria);
					if (tipoBusca.equals("texto")) {
						if (inicio) {
							if (extrairTextNode(noGrupoCategoria).toUpperCase().indexOf(parametro.toUpperCase()) == 0) {
								adicionaNo = true;
							}
						} else {
							if (extrairTextNode(noGrupoCategoria).toUpperCase().contains(parametro.toUpperCase())) {
								adicionaNo = true;
							}
						}
					}
				} else if (noGrupoCategoria.getNodeName().equals( "nome50" )) {
					nome50 = extrairTextNode(noGrupoCategoria);
					if (tipoBusca.equals("texto")) {
						if (inicio) {
							if (extrairTextNode(noGrupoCategoria).toUpperCase().indexOf(parametro.toUpperCase()) == 0) {
								adicionaNo = true;
							}
						} else {
							if (extrairTextNode(noGrupoCategoria).toUpperCase().contains(parametro.toUpperCase())) {
								adicionaNo = true;
							}
						}
					}
				// Evoca testes sobre os nós-filhos diretamente, caso a busca seja textual
				} else if (tipoBusca.equals("texto")) {
					if (noGrupoCategoria.getNodeName().equals( "grupo" )) {
						resultado.addAll(obterDeGrupo(noGrupoCategoria, parametro, inicio, tipoBusca));
					} else if (noGrupoCategoria.getNodeName().equals( "categoria" )) {
						resultado.addAll(obterDeCategoria(noGrupoCategoria, parametro, inicio, tipoBusca));
					}
				// Verifica se o código está fora do intervalo delimitado pelo próprio elemento, caso a busca seja por código
				} else if (tipoBusca.equals("codigo")) {
					if (parametro.compareTo(noGrupoCategoria.getAttributes().getNamedItem("inicial").getNodeValue()) >= 0 &&
							parametro.compareTo(noGrupoCategoria.getAttributes().getNamedItem("final").getNodeValue()) <= 0) {
						if (noGrupoCategoria.getNodeName().equals( "grupo" )) {
							resultado.addAll(obterDeGrupo(noGrupoCategoria, parametro, inicio, tipoBusca));
						} else if (noGrupoCategoria.getNodeName().equals( "categoria" )) {
							resultado.addAll(obterDeCategoria(noGrupoCategoria, parametro, inicio, tipoBusca));
						}
					} else { // Estando fora do intervalo, não continua pesquisando os nós-filhos
						break;
					}
				}
			}
		}
		if (adicionaNo) {
			Map<String,Object> registroCID = new HashMap<String,Object>();
			registroCID.put("tipoRegistro", "capitulo");
			registroCID.put("nome", nome);
			registroCID.put("nome50", nome50);
			registroCID.put("numcap", noCapitulo.getAttributes().getNamedItem("numcap").getNodeValue());
			registroCID.put("codcap", noCapitulo.getAttributes().getNamedItem("codcap").getNodeValue());
			registroCID.put("romano", noCapitulo.getAttributes().getNamedItem("romano").getNodeValue());
			registroCID.put("inicial", noCapitulo.getAttributes().getNamedItem("inicial").getNodeValue());
			registroCID.put("final", noCapitulo.getAttributes().getNamedItem("final").getNodeValue());
			resultado.add(registroCID);
		}
		return resultado;
	}
	
	/**
	 * Faz a busca em um determinado grupo do CID-10.
	 * 
	 * @param noCapitulo Nó do tipo Grupo passado para análise.
	 * @param parametro Código CID ou Texto para busca.
	 * @param tipoBusca Tipo da busca realizada (texto ou código)
	 * @param inicio Se a busca é pelo início (TRUE) ou qualquer parte do parâmetro de busca (FALSE) 
	 * @return Lista de registros no CID-10 encontrados.
	 */
	private static Collection<Map<String,Object>> obterDeGrupo(Node noGrupo, String parametro, boolean inicio, String tipoBusca) {
		/*
		 * 	<!ELEMENT grupo (nome, nome50, (grupo|categoria)+)>
		 *	<!ATTLIST grupo codgrupo ID #REQUIRED>
		 *	<!ATTLIST grupo inicial IDREF #REQUIRED>
		 *	<!ATTLIST grupo final   IDREF #REQUIRED>
		 */
		boolean adicionaNo = false;
		Collection<Map<String,Object>> resultado = new ArrayList<Map<String,Object>>();
		
		String nome = "";
		String nome50 = "";

		NodeList listGrupoCategoria = noGrupo.getChildNodes();
		for (int indiceGrupoCategoria = 0; indiceGrupoCategoria < listGrupoCategoria.getLength(); indiceGrupoCategoria++) {
			Node noGrupoCategoria = listGrupoCategoria.item(indiceGrupoCategoria);
			if ( noGrupoCategoria.getNodeType() == Node.ELEMENT_NODE ) {
				// Testes do próprio elemento
				if (noGrupoCategoria.getNodeName().equals( "nome" )) {
					nome = extrairTextNode(noGrupoCategoria);
					if (tipoBusca.equals("texto")) {
						if (inicio) {
							if (extrairTextNode(noGrupoCategoria).toUpperCase().indexOf(parametro.toUpperCase()) == 0) {
								adicionaNo = true;
							}
						} else {
							if (extrairTextNode(noGrupoCategoria).toUpperCase().contains(parametro.toUpperCase())) {
								adicionaNo = true;
							}
						}
					}
				} else if (noGrupoCategoria.getNodeName().equals( "nome50" )) {
					nome50 = extrairTextNode(noGrupoCategoria);
					if (tipoBusca.equals("texto")) {
						if (inicio) {
							if (extrairTextNode(noGrupoCategoria).toUpperCase().indexOf(parametro.toUpperCase()) == 0) {
								adicionaNo = true;
							}
						} else {
							if (extrairTextNode(noGrupoCategoria).toUpperCase().contains(parametro.toUpperCase())) {
								adicionaNo = true;
							}
						}
					}
				// Evoca testes sobre os nós-filhos diretamente, caso a busca seja textual
				} else if (tipoBusca.equals("texto")) {
					if (noGrupoCategoria.getNodeName().equals( "grupo" )) {
						resultado.addAll(obterDeGrupo(noGrupoCategoria, parametro, inicio, tipoBusca));
					} else if (noGrupoCategoria.getNodeName().equals( "categoria" )) {
						resultado.addAll(obterDeCategoria(noGrupoCategoria, parametro, inicio, tipoBusca));
					}
				// Verifica se o código está fora do intervalo delimitado pelo próprio elemento, caso a busca seja por código
				} else if (tipoBusca.equals("codigo")) {
					if (parametro.compareTo(noGrupo.getAttributes().getNamedItem("inicial").getNodeValue()) >= 0 &&
							parametro.compareTo(noGrupo.getAttributes().getNamedItem("final").getNodeValue()) <= 0) {
						if (noGrupoCategoria.getNodeName().equals( "grupo" )) {
							resultado.addAll(obterDeGrupo(noGrupoCategoria, parametro, inicio, tipoBusca));
						} else if (noGrupoCategoria.getNodeName().equals( "categoria" )) {
							resultado.addAll(obterDeCategoria(noGrupoCategoria, parametro, inicio, tipoBusca));
						}
					} else { // Estando fora do intervalo, não continua pesquisando os nós-filhos
						break;
					}
				}
			}
		}
		if (adicionaNo) {
			Map<String,Object> registroCID = new HashMap<String,Object>();
			registroCID.put("tipoRegistro", "grupo");
			registroCID.put("nome", nome);
			registroCID.put("nome50", nome50);
			registroCID.put("codgrupo", noGrupo.getAttributes().getNamedItem("codgrupo").getNodeValue());
			registroCID.put("inicial", noGrupo.getAttributes().getNamedItem("inicial").getNodeValue());
			registroCID.put("final", noGrupo.getAttributes().getNamedItem("final").getNodeValue());
			resultado.add(registroCID);
		}
		return resultado;
	}
	
	/**
	 * Faz a busca em uma determinada categoria do CID-10.
	 * 
	 * @param noCapitulo Nó do tipo Categoria passado para análise.
	 * @param parametro Código CID ou Texto para busca.
	 * @param tipoBusca Tipo da busca realizada (texto ou código)
	 * @param inicio Se a busca é pelo início (TRUE) ou qualquer parte do parâmetro de busca (FALSE) 
	 * @return Lista de registros no CID-10 encontrados.
	 */
	private static Collection<Map<String,Object>> obterDeCategoria(Node noCategoria, String parametro, boolean inicio, String tipoBusca) {
		/*
		 * 	<!ELEMENT categoria (duplaclassificacao?, nome, nome50, restricoes?, subcategoria*)>
		 *	<!ATTLIST categoria codcat ID #REQUIRED>
		 *	<!ATTLIST categoria ehsubcat (sim|nao) "nao">
		 */
		String nome = "";
		String nome50 = "";
		boolean adicionaNo = false;
		Collection<Map<String,Object>> resultado = new ArrayList<Map<String,Object>>();
		
		// Faz o teste do código pelo atributo CODCAT da categoria
		if (tipoBusca.equals("codigo")) {
			if (inicio) {
				if (noCategoria.getAttributes().getNamedItem("codcat").getNodeValue().equals(parametro)) {
					adicionaNo = true;
				}
			} else {
				if (noCategoria.getAttributes().getNamedItem("codcat").getNodeValue().contains(parametro)) {
					adicionaNo = true;
				}
			}
		}
		
		// Prossegue com testes pelos elementos
		NodeList listDetalheCategoria = noCategoria.getChildNodes();
		for (int indiceDetalheCategoria = 0; indiceDetalheCategoria < listDetalheCategoria.getLength(); indiceDetalheCategoria++) {
			Node noDetalheCategoria = listDetalheCategoria.item(indiceDetalheCategoria);
			if ( noDetalheCategoria.getNodeType() == Node.ELEMENT_NODE ) {
				// Testes no próprio elemento
				if (noDetalheCategoria.getNodeName().equals( "nome" )) {
					nome = extrairTextNode(noDetalheCategoria);
					if (tipoBusca.equals("texto")) {
						if (inicio) {
							if (extrairTextNode(noDetalheCategoria).toUpperCase().indexOf(parametro.toUpperCase()) == 0) {
								adicionaNo = true;
							}
						} else {
							if (extrairTextNode(noDetalheCategoria).toUpperCase().contains(parametro.toUpperCase())) {
								adicionaNo = true;
							}
						}
					}
				} else if (noDetalheCategoria.getNodeName().equals( "nome50" )) {
					nome50 = extrairTextNode(noDetalheCategoria);
					if (tipoBusca.equals("texto")) {
						if (inicio) {
							if (extrairTextNode(noDetalheCategoria).toUpperCase().indexOf(parametro.toUpperCase()) == 0) {
								adicionaNo = true;
							}
						} else {
							if (extrairTextNode(noDetalheCategoria).toUpperCase().contains(parametro.toUpperCase())) {
								adicionaNo = true;
							}
						}
					}
				// Evoca testes sobre os nós filhos
				} else if (noDetalheCategoria.getNodeName().equals( "duplaclassificacao" )) {
					// Não busca em dupla-classificação, pois é apenas detalhamento da categoria
				} else if (noDetalheCategoria.getNodeName().equals( "restricoes" )) {
					// Não busca em restrições, pois é apenas detalhamento da categoria
				} else if (noDetalheCategoria.getNodeName().equals( "subcategoria" )) {
					resultado.addAll(obterDeSubCategoria(noDetalheCategoria, parametro, inicio, tipoBusca));
				}
			}
		}
		if (adicionaNo) {
			Map<String,Object> registroCID = new HashMap<String,Object>();
			registroCID.put("tipoRegistro", "categoria");
			registroCID.put("nome", nome);
			registroCID.put("nome50", nome50);
			registroCID.put("codcat", noCategoria.getAttributes().getNamedItem("codcat").getNodeValue());
			registroCID.put("ehsubcat", noCategoria.getAttributes().getNamedItem("ehsubcat").getNodeValue().equals("sim") ? true : false);
			registroCID.put("duplaclassificacao", obterDuplaClassificacao(noCategoria));
			registroCID.put("restricoes", obterRestricoes(noCategoria));
			resultado.add(registroCID);
		}
		return resultado;
	}
	
	/**
	 * Faz a busca em uma determinada subcategoria do CID-10.
	 * 
	 * @param noCapitulo Nó do tipo SubCategoria passado para análise.
	 * @param parametro Código CID ou Texto para busca.
	 * @param tipoBusca Tipo da busca realizada (texto ou código)
	 * @param inicio Se a busca é pelo início (TRUE) ou qualquer parte do parâmetro de busca (FALSE) 
	 * @return Lista de registros no CID-10 encontrados.
	 */
	private static Collection<Map<String,Object>> obterDeSubCategoria(Node noCategoria, String parametro, boolean inicio, String tipoBusca) {
		/*
		 * 	<!ELEMENT subcategoria (duplaclassificacao?, nome, nome50, restricoes?, categoriasabsorvidas*)>
		 *		<!ATTLIST subcategoria codsubcat ID #REQUIRED>
		 */
		String nome = "";
		String nome50 = "";
		boolean adicionaNo = false;
		Collection<Map<String,Object>> resultado = new ArrayList<Map<String,Object>>();
		
		// Faz o teste do código pelo atributo CODSUBCAT da subcategoria
		if (tipoBusca.equals("codigo")) {
			if (inicio) {
				if (noCategoria.getAttributes().getNamedItem("codsubcat").getNodeValue().equals(parametro)) {
					adicionaNo = true;
				}
			} else {
				if (noCategoria.getAttributes().getNamedItem("codsubcat").getNodeValue().contains(parametro)) {
					adicionaNo = true;
				}
			}
		}
		
		// Prossegue com testes pelos elementos
		NodeList listDetalheSubCategoria = noCategoria.getChildNodes();
		for (int indiceDetalheSubCategoria = 0; indiceDetalheSubCategoria < listDetalheSubCategoria.getLength(); indiceDetalheSubCategoria++) {
			Node noDetalheCategoria = listDetalheSubCategoria.item(indiceDetalheSubCategoria);
			if ( noDetalheCategoria.getNodeType() == Node.ELEMENT_NODE ) {
				// Testes no próprio elemento
				if (noDetalheCategoria.getNodeName().equals( "nome" )) {
					nome = extrairTextNode(noDetalheCategoria);
					if (tipoBusca.equals("texto")) {
						if (inicio) {
							if (extrairTextNode(noDetalheCategoria).toUpperCase().indexOf(parametro.toUpperCase()) == 0) {
								adicionaNo = true;
							}
						} else {
							if (extrairTextNode(noDetalheCategoria).toUpperCase().contains(parametro.toUpperCase())) {
								adicionaNo = true;
							}
						}
					}
				} else if (noDetalheCategoria.getNodeName().equals( "nome50" )) {
					nome50 = extrairTextNode(noDetalheCategoria);
					if (tipoBusca.equals("texto")) {
						if (inicio) {
							if (extrairTextNode(noDetalheCategoria).toUpperCase().indexOf(parametro.toUpperCase()) == 0) {
								adicionaNo = true;
							}
						} else {
							if (extrairTextNode(noDetalheCategoria).toUpperCase().contains(parametro.toUpperCase())) {
								adicionaNo = true;
							}
						}
					}
				// Evoca testes sobre os nós filhos
				} else if (noDetalheCategoria.getNodeName().equals( "duplaclassificacao" )) {
					// Não busca em dupla-classificação, pois é apenas detalhamento da categoria
				} else if (noDetalheCategoria.getNodeName().equals( "restricoes" )) {
					// Não busca em restrições, pois é apenas detalhamento da categoria
				} else if (noDetalheCategoria.getNodeName().equals( "categoriasabsorvidas" )) {
					// Não busca em categorias absorvidas, pois é apenas detalhamento da categoria
				}
			}
		}
		if (adicionaNo) {
			Map<String,Object> registroCID = new HashMap<String,Object>();
			registroCID.put("tipoRegistro", "subcategoria");
			registroCID.put("nome", nome);
			registroCID.put("nome50", nome50);
			registroCID.put("codsubcat", noCategoria.getAttributes().getNamedItem("codsubcat").getNodeValue());
			registroCID.put("duplaclassificacao", obterDuplaClassificacao(noCategoria));
			registroCID.put("restricoes", obterRestricoes(noCategoria));
			registroCID.put("categoriasabsorvidas", obterCategoriasAbsorvidas(noCategoria));
			resultado.add(registroCID);
		}
		return resultado;
	}

	/**
	 * Recupera dados de restrições existentes em um determinado nó.
	 * Embora o CID-10, versão V2007 preveja apenas uma ocorrência de restrição, este
	 * método assume que possa haver mais de uma, tornando tolerante a mudanças no .DTD
	 * do CID-10.
	 * 
	 * @param noPai Nó que possua restrições, para complementação de informações.
	 * @return Lista de restrições para complementar.
	 */
	private static Collection<Map<String,Object>> obterRestricoes(Node noPai) {
		/*	<!ELEMENT restricoes EMPTY>
		 *		<!ATTLIST restricoes causaobito (sim|nao) "sim">
		 *		<!ATTLIST restricoes sexo (apenas_homens|apenas_mulheres|ambos) "ambos">
		 */
		Collection<Map<String,Object>> resultado = null; 
		NodeList listNos = noPai.getChildNodes();
				
		for (int indice = 0; indice < listNos.getLength(); indice++) {
			Node noFilho = listNos.item(indice);
			if ( noFilho.getNodeType() == Node.ELEMENT_NODE ) {
				if (noFilho.getNodeName().equals( "restricoes" )) {
					Map<String,Object> registroDetalhe = null;
					if (noFilho.getAttributes().getNamedItem("causaobito") != null) {
						if (registroDetalhe == null) {
							registroDetalhe = new HashMap<String,Object>();
						}
						registroDetalhe.put("causaobito", noFilho.getAttributes().getNamedItem("causaobito").getNodeValue().equals("sim") ? true : false);
					}
					if (noFilho.getAttributes().getNamedItem("sexo") != null) {
						if (registroDetalhe == null) {
							registroDetalhe = new HashMap<String,Object>();
						}
						registroDetalhe.put("sexo", noFilho.getAttributes().getNamedItem("sexo").getNodeValue());
					}
					if (registroDetalhe != null) {
						if (resultado == null) {
							resultado = new ArrayList<Map<String,Object>>();
						}
					}
					resultado.add(registroDetalhe);
				}
			}
		}
		return resultado;
	}
	
	/**
	 * Recupera dados de dupla classificação existentes em um determinado nó.
	 * Embora o CID-10, versão V2007 preveja apenas uma ocorrência de dupla classificação,
	 * este método assume que possa haver mais de uma, tornando tolerante a mudanças no
	 * .DTD do CID-10.
	 * 
	 * @param noPai Nó que possua dupla classificação, para complementação de informações.
	 * @return Lista de dupla classificação para complementar.
	 */
	private static Collection<Map<String,Object>> obterDuplaClassificacao(Node noPai) {
		/*	<!ELEMENT duplaclassificacao (referencia*)>
		 *		<!ATTLIST duplaclassificacao tipo (cruz|asterisco) #REQUIRED>
		 */
		Collection<Map<String,Object>> resultado = null; 
		NodeList listNos = noPai.getChildNodes();
				
		for (int indice = 0; indice < listNos.getLength(); indice++) {
			Node noFilho = listNos.item(indice);
			if ( noFilho.getNodeType() == Node.ELEMENT_NODE ) {
				if (noFilho.getNodeName().equals( "referencia" )) {
					Map<String,Object> registroDetalhe = new HashMap<String,Object>();
					registroDetalhe.put("tipo", noPai.getAttributes().getNamedItem("tipo"));
					if (noFilho.getAttributes().getNamedItem("causaobito") != null) {
						registroDetalhe.put("causaobito", noFilho.getAttributes().getNamedItem("causaobito").getNodeValue().equals("sim") ? true : false);
					}
					if (obterReferencia(noPai) != null) {
						registroDetalhe.put("referencia", obterReferencia(noPai));
					}
					if (registroDetalhe != null) {
						if (resultado == null) {
							resultado = new ArrayList<Map<String,Object>>();
						}
					}
					resultado.add(registroDetalhe);
				}
			}
		}
		return resultado;
	}
	
	/**
	 * Recupera dados de categorias absorvidas existentes em um determinado nó.
	 * 
	 * @param noPai Nó que possua categorias absorvidas, para complementação de informações.
	 * @return Lista de categorias absorvidas para complementar.
	 */
	private static Collection<Map<String,Object>> obterCategoriasAbsorvidas(Node noPai) {
		/*	<!ELEMENT categoriasabsorvidas EMPTY>
		 *		<!ATTLIST categoriasabsorvidas codsubcat ID #REQUIRED>
		 */
		Collection<Map<String,Object>> resultado = null; 
		NodeList listNos = noPai.getChildNodes();
				
		for (int indice = 0; indice < listNos.getLength(); indice++) {
			Node noFilho = listNos.item(indice);
			if ( noFilho.getNodeType() == Node.ELEMENT_NODE ) {
				if (noFilho.getNodeName().equals( "categoriasabsorvidas" )) {
					Map<String,Object> registroDetalhe = new HashMap<String,Object>();
					registroDetalhe.put("codsubcat", noFilho.getAttributes().getNamedItem("codsubcat").getNodeValue());
					if (resultado == null) {
						resultado = new ArrayList<Map<String,Object>>();
					}
					resultado.add(registroDetalhe);
				}
			}
		}
		return resultado;
	}
	
	/**
	 * Recupera dados de referências existentes em um determinado nó.
	 * 
	 * @param noPai Nó que possua referências, para complementação de informações.
	 * @return Lista de referências para complementar.
	 */
	private static Collection<Map<String,Object>> obterReferencia(Node noPai) {
		/*	<!ELEMENT referencia (#PCDATA)>
		 *		<!ATTLIST referencia codrefer IDREF #IMPLIED>
		 */
		Collection<Map<String,Object>> resultado = null; 
		NodeList listNos = noPai.getChildNodes();
				
		for (int indice = 0; indice < listNos.getLength(); indice++) {
			Node noFilho = listNos.item(indice);
			if ( noFilho.getNodeType() == Node.ELEMENT_NODE ) {
				if (noFilho.getNodeName().equals( "referencia" )) {
					Map<String,Object> registroDetalhe = new HashMap<String,Object>();
					registroDetalhe.put("codrefer", noFilho.getAttributes().getNamedItem("codrefer").getNodeValue());
					registroDetalhe.put("valor", extrairTextNode(noFilho));
					if (resultado == null) {
						resultado = new ArrayList<Map<String,Object>>();
					}
					resultado.add(registroDetalhe);
				}
			}
		}
		return resultado;
	}

}