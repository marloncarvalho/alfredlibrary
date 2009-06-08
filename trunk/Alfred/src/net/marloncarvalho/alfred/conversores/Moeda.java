/*
 *  This file is part of Alfred Library.
 *
 *  Foobar is free software: you can redistribute it and/or modify
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
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.marloncarvalho.alfred.conversores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.marloncarvalho.alfred.AlfredException;
import net.marloncarvalho.alfred.net.WorldWideWeb;
import net.marloncarvalho.alfred.texto.Texto;

/**
 * Utilitário para conversão entre moedas.
 * Requer conexão com a internet para obtenção das cotações do dia através do site do Banco Central do Brasil.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Moeda {

	private Moeda() {}

	/**
	 * Realizar a conversão de um valor em Real para Dolar.
	 * 
	 * @param valorConversao Valor a ser convertido para dolar.
	 * @return Valor em dolar.
	 */
	public static String converterRealEmDolar(String valorConversao) {
		return Moeda.converter(valorConversao, "790", "220");
	}

	/**
	 * Realizar a conversão de um valor em Dolar para Real.
	 * 
	 * @param valorConversao Valor a ser convertido para real.
	 * @return Valor em real.
	 */
	public static String converterDolarEmReal(String valorConversao) {
		return Moeda.converter(valorConversao, "220", "790");
	}

	/**
	 * Realizar a conversão de uma moeda para outra.
	 * Requer conexão com a internet.
	 * Para verificar quais os códigos das moedas, acessar o site:
	 * http://www4.bcb.gov.br/pec/taxas/batch/tabmoedas.asp?id=tabmoeda&id=tabmoeda
	 * 
	 * @param valorConversao Valor a ser convertido.
	 * @param moedaOrigem em que moeda o valor informado está. Trata-se de um código fornecido pelo Banco Central.
	 * @param moedaDestino Para qual moeda o valor informado será convertido. Trata-se de um código fornecido pelo Banco Central.
	 * @return Valor após conversão.
	 */
	public static String converter(String valorConversao, String moedaOrigem, String moedaDestino) {
		StringBuilder queryString = new StringBuilder();
		queryString.append("?MoedaOrigem="); 
		queryString.append(moedaOrigem);
		queryString.append("&MoedaDestino=");
		queryString.append(moedaDestino);
		queryString.append("&DataCotacaoEnvio=");
		queryString.append(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		queryString.append("&ValorEnvio=");
		String valorSoNumeros = Texto.manterNumeros(valorConversao);
		String valorFinal = Texto.incluirCaracterInicio(valorSoNumeros, '0', 17-valorSoNumeros.length());
		queryString.append(valorFinal);

		// Realizar a requisição.
		String conteudo = WorldWideWeb.getConteudoSite("http://www4.bcb.gov.br/pec/conversao/Resultado.asp" + queryString.toString());

		//<strong>Resultado da convers&atilde;o:</strong> 19,36</td>
		// Usar expressão regular para achar o preço.
		Pattern padrao = Pattern.compile("<strong>Resultado da convers&atilde;o:</strong> \\d+,\\d{2}");  
		Matcher pesquisa = padrao.matcher(conteudo);

		// Deve encontrar apenas um.
		String valorConvertido = "";
		while(pesquisa.find()) {
			valorConvertido = pesquisa.group();
		}
		
		// Verificar se foi achado o valor.
		if ( "".equals(valorConvertido) )
			throw new AlfredException("Não foi possível obter o valor da conversão solicitada. Verifique os parâmetros informados ou se o site do Banco Central encontra-se indisponível.");

		return valorConvertido.replace("<strong>Resultado da convers&atilde;o:</strong> ","");
	}

}
