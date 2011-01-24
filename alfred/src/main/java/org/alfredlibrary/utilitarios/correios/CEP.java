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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.io.CSVReader;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.alfredlibrary.utilitarios.texto.HTML;

/**
 * Classe utilitária para CEPs.
 * 
 * @author Marlon Silva Carvalho
 * @since 04/06/2009
 */
final public class CEP {
	private static String value = "À|Á|Â|Ã|Ä|Å|Æ|Ç|È|É|Ê|Ë|Ì|Í|Î|Ï|Ð|Ñ|Ò|Ó|Ô|Õ|Ö|Ø|Ù|Ú|Û|Ü|Ý|Þ|ß|à|á|â|ã|ä|å|æ|ç|è|é|ê|ë|ì|í|î|ï|ð|ñ|ò|ó|ô|õ|ö|ø|ù|ú|û|ü|ý|þ|ÿ";
	
	private CEP() {
		throw new AssertionError();
	}

	/**
	 * Consultar um Endereço pelo CEP. Será retornado um Array contendo 4
	 * posições, que conterão, respectivamente, os campos cep, endereço, bairro
	 * e cidade/estado). Utiliza o site dos Correios para extrair as
	 * informações.
	 * 
	 * @param cep
	 *            CEP a ser consultado.
	 * @return Array contendo o resultado da consulta.
	 */
	public static String[] consultarEnderecoCorreios(String cep) {
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("cepTemp", "");
		parametros.put("metodo", "buscarCep");
		parametros.put("tipoCep", "");
		parametros.put("cepEntrada", cep);
		String conteudo = WorldWideWeb.obterConteudoSite("http://m.correios.com.br/movel/buscaCepConfirma.do",
				"iso-8859-1", parametros);

		String[] re = new String[4];
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
		re[3] = HTML.removerTags(pesquisa.group()).trim();
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

}