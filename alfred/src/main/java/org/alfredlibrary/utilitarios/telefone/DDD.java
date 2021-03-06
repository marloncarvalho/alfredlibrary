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
package org.alfredlibrary.utilitarios.telefone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.LimitExceededException;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.colecoes.AlfredArrays;
import org.alfredlibrary.utilitarios.enums.EstadoBrasileiro;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.alfredlibrary.utilitarios.texto.HTML;
import org.alfredlibrary.utilitarios.texto.Texto;

/**
 * Utilitário para obter o DDD de uma Cidade.
 * 
 * @author Marlon Silva Carvalho
 * @since 22/06/2010
 * 
 * @author Mario Jorge Pereira
 * @since 31/12/2010
 */
final public class DDD {
	
	private DDD() {
		throw new AssertionError();
	}

	/**
	 * Obter o DDD de uma determinada cidade.
	 * 
	 * @param estado
	 *            Estado onde se encontra a cidade.
	 * @param cidade
	 *            Cidade.
	 * @return DDD da cidade.
	 */
	public static String[] obter(EstadoBrasileiro estado, String cidade) {
		cidade = Texto.trocarCaracteresAcentuados(cidade);
		String url = "http://wwp.brasilcenter.com.br/pr5e/WWWConsulta.asp";
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("estado", estado.toString());
		parametros.put("cidade", cidade);
		parametros.put("idioma", "p");
		parametros.put("tc", "3");
		String conteudo = WorldWideWeb.obterConteudoSite(url, "ISO-8859-1",
				parametros);
		if (conteudo.indexOf("Localidade não encontrada") > -1) {
			throw new AlfredException("Cidade não encontrada.");
		}
		Pattern padrao = Pattern.compile(cidade.toUpperCase() + "(\\.)*"
				+ estado.toString().toUpperCase() + "(\\.)*(\\d){2}");
		Matcher pesquisa = padrao.matcher(conteudo);
		Collection<String> ddds = new ArrayList<String>();
		while (pesquisa.find()) {
			String linha = pesquisa.group();
			ddds.add(linha.substring(linha.length() - 2));
		}
		String[] ret = new String[ddds.size()];
		int x = 0;
		for (Iterator<String> it = ddds.iterator(); it.hasNext();) {
			ret[x++] = it.next();
		}
		return ret;
	}

	/**
	 * Obter as cidades de um determinado DDD.
	 * 
	 * @param DDD
	 *            da cidade.
	 * @return cidade Cidade.
	 */
	public static String[] obter(Integer ddd) {
		String url = "http://telelistas.net/templates/ddd_result.aspx";
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("ddd", ddd.toString());
		parametros.put("estado", "");
		parametros.put("localidade", "");
		String conteudo = WorldWideWeb.obterConteudoSite(url, "ISO-8859-1",parametros);
		if (conteudo.indexOf("Sua busca n&atilde;o obteve resultado. Por favor refa&ccedil;a sua pesquisa na caixa abaixo.") > -1) {
			throw new AlfredException("DDD não encontrado.");
		}
		List<String> resultado = new ArrayList<String>();
		int indexTotalinicio = conteudo.indexOf("localidades encontradas:");
		int intexTotalfim = conteudo.indexOf("</b>", indexTotalinicio);
		Integer total = Integer.parseInt(conteudo.substring(indexTotalinicio, intexTotalfim).replaceAll("[^0-9]", ""))+10;
		for (int j = 10; j <= total; j+=10) {
			int inicio = conteudo.indexOf("<img src=\"http://img.telelistas.net/img/por_servddd_barralista.gif\" width=\"468\" height=\"20\">");
			int fim = conteudo.indexOf("</table>", inicio);
			conteudo = conteudo.substring(inicio, fim);
			conteudo = HTML.desconverterElementosHTMLEspeciais(conteudo, 0);
			String[] table = conteudo.split("<[^>]*>");
			String[] a = AlfredArrays.removerItensVazios(table);
			for (int i = 0; i < a.length; i += 3) {
				resultado.add(a[i]);
			}
			parametros.put("count", j+"");
		    conteudo = WorldWideWeb.obterConteudoSite(url, "ISO-8859-1", parametros);
		}
		return resultado.toArray(new String[resultado.size()]);
	}


}