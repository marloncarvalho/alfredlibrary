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
package org.alfredlibrary.utilitarios.correios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.io.CSVReader;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.alfredlibrary.utilitarios.texto.HTML;
import org.alfredlibrary.utilitarios.texto.Texto;

/**
 * Classe utilitária para CEPs.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
final public class CEP {
	private static String value = "À|Á|Â|Ã|Ä|Å|Æ|Ç|È|É|Ê|Ë|Ì|Í|Î|Ï|Ð|Ñ|Ò|Ó|Ô|Õ|Ö|Ø|Ù|Ú|Û|Ü|Ý|Þ|ß|à|á|â|ã|ä|å|æ|ç|è|é|ê|ë|ì|í|î|ï|ð|ñ|ò|ó|ô|õ|ö|ø|ù|ú|û|ü|ý|þ|ÿ|ª|º";
	
	private CEP() {
		throw new AssertionError();
	}

	/**
	 * Consultar um Endereço pelo CEP ou Logradouro. Será retornado um Array contendo 5
	 * posições, que conterão, respectivamente, os campos cep, endereço, bairro,
	 * cidade e estado). Utiliza o site dos Correios para extrair as
	 * informações.
	 * 
	 * Apenas o primeiro resultado é retornado, sendo mais apropriado para consulta por CEP. 
	 * 
	 * @param cepLogradouro
	 *            CEP ou logradouro a ser consultado.
	 * @return Array contendo o resultado da consulta.
	 */
	public static String[] consultarEnderecoCorreios(String cepLogradouro) {
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("cepTemp", "");
		parametros.put("metodo", "buscarCep");
		parametros.put("tipoCep", "");
		parametros.put("cepEntrada", cepLogradouro);
		String conteudo = WorldWideWeb.obterConteudoSite("http://m.correios.com.br/movel/buscaCepConfirma.do",
				"iso-8859-1", parametros);

		String[] re = new String[5];
		Pattern padrao = Pattern.compile("<span class=\"respostadestaque\">(\\w| |/|\t|,|" + value + ")*</span>",
				Pattern.CASE_INSENSITIVE);
		Matcher pesquisa = padrao.matcher(conteudo);
		if (!pesquisa.find()) {
			throw new AlfredException("CEP não encontrado.");
		}
		re[1] = HTML.removerTags(pesquisa.group()).trim();
		pesquisa.find();
		re[2] = HTML.removerTags(pesquisa.group()).trim();
		pesquisa.find();
		String cidadeUf = HTML.removerTags(pesquisa.group()).trim();
		re[3] = cidadeUf.substring(0,cidadeUf.indexOf(' '));
		re[4] = cidadeUf.substring(cidadeUf.length() - 2);
		pesquisa.find();
		re[0] = HTML.removerTags(pesquisa.group()).trim();
		return re;
	}

	/**
	 * Consultar um Endereço pelo CEP. Será retornado um Array contendo 6
	 * posições, que conterão, respectivamente, os campos (tipo de logradouro,
	 * logradouro, bairro, cidade, sigla do estado, estado). Utiliza a base de
	 * dados do site CEP Livre. Nâo é constantemente atualizada.
	 * 
	 * @param cep
	 *            CEP a ser consultado.
	 * @return Array contendo o resultado da consulta.
	 */
	public static String[] consultarEnderecoCEPLivre(String cep) {
		String cepFormatado = org.alfredlibrary.formatadores.CEP.formatar(cep, true);
		Collection<Map<String, String>> r = CSVReader
				.interpretar("http://ceplivre.pc2consultoria.com/index.php?module=cep&cep=" + cepFormatado
						+ "&formato=csv");
		if (r.size() <= 0)
			throw new AlfredException("Endereço não encontrado.");
		Map<String, String> endereco = (Map<String, String>) r.iterator().next();
		if (endereco == null)
			throw new AlfredException("Endereçoo não encontrado.");
		return new String[] { endereco.get("tipo_logradouro"), endereco.get("logradouro"), endereco.get("bairro"),
				endereco.get("cidade"), endereco.get("sigla"), endereco.get("estado") };
	}
	
	/**
	 * Consultar um Endereço pelo CEP. Será retornado uma lista com endereços, onde
	 * cada um será um Array contendo 5 posições, que conterão, respectivamente, os
	 * campos cep, endereço, bairro, cidade e uf). Utiliza o site dos Correios para
	 * extrair as informações.
	 * 
	 * Apenas os 10 primeiros resultados serão retornados.
	 * 
	 * @param cepLogradouro logradouro a ser consultado.
	 * @param bairro a ser consultado.
	 * @param cidade a ser consultada.
	 * @return Lista contendo o resultado da consulta.
	 */
	public static List<String[]> consultarEnderecoCorreiosLogradouro(String cepLogradouro) {
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("cepTemp", "");
		parametros.put("metodo", "buscarCep");
		parametros.put("tipoCep", "");
		parametros.put("cepEntrada", cepLogradouro);
		String conteudo = WorldWideWeb.obterConteudoSite("http://m.correios.com.br/movel/buscaCepConfirma.do",
				"iso-8859-1", parametros);
		String conteudoCopia = new String(conteudo).replace(':', ' ');
		
		List<String[]> resultado = new ArrayList<String[]>();
		
		// Monta a lista de labels
		Pattern patternResposta = Pattern.compile("<span class=\"resposta\">(\\w|:| |/|\t|,|" + value + ")*</span>",
				Pattern.CASE_INSENSITIVE);
		//pesquisa.usePattern(patternResposta);
		Matcher pesquisaLabel = patternResposta.matcher(conteudoCopia);
		List<String> listResposta= new ArrayList<String>();
		while (pesquisaLabel.find()) {
			listResposta.add(HTML.removerTags(pesquisaLabel.group()).trim());
		}
		
		// Monta a lista de conteúdos
		Pattern patternRespostaDestaque = Pattern.compile("<span class=\"respostadestaque\">(\\w| |/|\t|,|\\(|\\)|\\,|" + value + ")*</span>",
				Pattern.CASE_INSENSITIVE);
		Matcher pesquisa = patternRespostaDestaque.matcher(conteudo);
		List<String> listRespostaDestaque = new ArrayList<String>();
		while (pesquisa.find()) {
			listRespostaDestaque.add(HTML.removerTags(pesquisa.group()).trim());
		} 
				
		// Montagem dos Endereços e adição ao resultado
		int enderecoCompleto = 0;
		String[] re = new String[5];
		for (int i = 0; i < listResposta.size(); i++) {
			if (((String)listResposta.get(i)).indexOf("Logradouro") >= 0 || ((String)listResposta.get(i)).indexOf("Endereço") >= 0) {
				enderecoCompleto++;
				re[1] = listRespostaDestaque.get(i).toString();
			} else if (((String)listResposta.get(i)).indexOf("Bairro") >= 0) {
				enderecoCompleto++;
				re[2] = listRespostaDestaque.get(i).toString();
			} else if (((String)listResposta.get(i)).indexOf("Localidade") >= 0 && ((String)listResposta.get(i)).indexOf("UF") >= 0) {
				enderecoCompleto += 2;
				re[3] = listRespostaDestaque.get(i).toString().substring(0,listRespostaDestaque.get(i).toString().indexOf('/')).trim();
				re[4] = listRespostaDestaque.get(i).toString().substring(listRespostaDestaque.get(i).toString().length() - 2);
			} else if (((String)listResposta.get(i)).indexOf("CEP") >= 0) {
				enderecoCompleto++;
				re[0] = listRespostaDestaque.get(i).toString();
			} // Labels como Unidade ou Cliente, que aparecem apenas em alguns registros, são ignorados
			if (enderecoCompleto == 5) {
				resultado.add(re);
				re = new String[5];
				enderecoCompleto = 0;
			}
		}
		return resultado;
	}
	
	/**
	 * Consultar um Endereço pelo Logradouro. Será retornado uma lista de endereços
	 * candidatos, onde cada um será um Array contendo 6 posições, que conterão,
	 * respectivamente, os campos (tipo de logradouro, logradouro, bairro, cidade,
	 * sigla do estado e cep). Utiliza a base de dados do site CEP Livre. Nâo é
	 * constantemente atualizada.
	 * 
	 * @param logradouro Logradouro a ser consultado. Obrigatório e deve ser exato
	 * @param bairro Bairro do logradouro a ser consultado. Opcional, mas deve ser exato, se usado
	 * @param cidade Cidade do logradouro a ser consultado. Obrigatório e deve ser exato
	 *            
	 * @return List contendo o resultado da consulta.
	 */
	public static List<String[]> consultarEnderecoCEPLivreLogradouro(String logradouro, String bairro, String cidade) {
		// CSV Layout: tipo_logradouro,tipo_logradouro_id,logradouro,bairro,cidade,sigla,estado,estado_id,cep,codigo_ibge 
		if (logradouro == null || cidade == null) {
			throw new AlfredException("Parâmetros inválidos! Logradouro e Cidade são obrigatórios!");
		}
		String url = "http://ceplivre.pc2consultoria.com/index.php?module=cep&logradouro=" + logradouro;
		if (bairro != null) {
			url = url + "&bairro=" + bairro;
		}
		url = url + "&cidade=" + cidade + "&formato=csv";
		Collection<Map<String, String>> r = CSVReader.interpretar(url);
		if (r.size() <= 0)
			throw new AlfredException("Endereço não encontrado.");
		List<String[]> resultado = new ArrayList<String[]>();
		for (int i = 0; i < r.size(); i++) {
			if (!r.iterator().hasNext()) {
				break;
			}
			Map<String, String> endereco = (Map<String, String>) r.iterator().next();
			if (endereco != null) {
				resultado.add(new String[] { endereco.get("tipo_logradouro"), endereco.get("logradouro"), endereco.get("bairro"),
						endereco.get("cidade"), endereco.get("sigla"), endereco.get("cep") });
			}
		}
		if (resultado.size() > 0) {
			return resultado;
		}
		throw new AlfredException("Endereço não encontrado.");
	}
	
	/**
	 * Consultar um Endereço pelo Logradouro. Será retornado uma lista de endereços
	 * candidatos, onde cada um será um Array contendo 5 posições, que conterão,
	 * respectivamente, os campos (logradouro, bairro, cidade, sigla do estado e cep).
	 * Utiliza as bases de dados do site dos Correios e CEP Livre, respectivamente.
	 * 
	 * A busca no CEPLivre ocorre apenas quando for lançada uma exceção na consulta
	 * à base dos Correios.
	 * 
	 * @param logradouro Logradouro a ser consultado.
	 * @param bairro Bairro do logradouro a ser consultado.
	 * @param cidade Cidade do logradouro a ser consultado.
	 * @param uf UF do logradouro a ser consultado.
	 *            
	 * @return List contendo o resultado da consulta.
	 */
	public List<String[]> consultarEnderecoHibrido(String logradouro, String bairro, String cidade, String uf) throws AlfredException {
		try {
			List<String[]> enderecosCorreio = consultarEnderecoCorreiosLogradouro(Texto.manterNumeros(logradouro));
			List<String[]> resultado = new ArrayList<String[]>();
			for (String[] registro : enderecosCorreio) {
				if (uf != null ? uf.toUpperCase().equals(registro[4].toUpperCase()) : true) {
					if (cidade != null ? cidade.toUpperCase().equals(registro[3].toUpperCase()) : true) {
						if (bairro != null ? bairro.toUpperCase().equals(registro[2].toUpperCase()) : true) {
							resultado.add(registro);
						}
					}
				}
			}
			return resultado;
		} catch (AlfredException ae) { // Se não encontrar nos Correios, busca no CEP Livre
			try {
				List<String[]> enderecosCorreio = consultarEnderecoCEPLivreLogradouro(cidade, bairro, cidade);
				List<String[]> resultado = new ArrayList<String[]>();
				for (String[] registro : enderecosCorreio) {
					if (uf != null ? uf.toUpperCase().equals(registro[4].toUpperCase()) : true) {
						resultado.add(new String[] { registro[0] + " " + registro[1], registro[2], registro[3], registro[4], registro[5] });
					}
				}
				return resultado;
			} catch (AlfredException ael) { // Se não encontrar, busca informação na tabela interna
				throw new AlfredException("Endereço não localizado nas bases buscadas!");
			}
		}
	}

}