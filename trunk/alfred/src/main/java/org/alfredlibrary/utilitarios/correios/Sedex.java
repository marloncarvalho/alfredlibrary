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

 import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.alfredlibrary.utilitarios.texto.HTML;

/**
 * Utilitários para obter informações de entrega para Sedex através do site dos Correios.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class Sedex {

	/**
	 * Verificar o Prazo e o Preço para entrega via Sedex de um CEP de origem para um CEP de destino com uma encomenda com o peso especificado.
	 * Exemplo de uso:
	 * Sedex.obterPrecoPrazoEntrega("40290280", "40290280",1);
	 * Retorno: {"11,20","1"}
	 * 
	 * @param cepOrigem CEP de Origem.
	 * @param cepDestino CEP de Destino.
	 * @param peso Peso da Encomenda.
	 * @return Prazo e Prelo para entrega. Primeira posição corresponde ao preço. 
	 * 			   Segunda posição corresponde ao prazo em dias.
	 */
	public static String[] obterPrecoPrazoEntrega(String cepOrigem, String cepDestino, int peso) {
		if ( "".equals(cepOrigem) )
			throw new AlfredException("Informe o CEP de Origem.");
		if ( "".equals(cepDestino) )
			throw new AlfredException("Informe o CEP de Destino.");
		if ( peso <= 0 )
			throw new AlfredException("Informe o Peso da encomenda.");

		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("resposta","paginaCorreios");
		parametros.put("servico","40010");
		parametros.put("cepOrigem", cepOrigem);
		parametros.put("cepDestino", cepDestino);
		parametros.put("embalagem","");
		parametros.put("peso", Integer.toString(peso));

		Map<String,String> cabecalhos = new HashMap<String,String>();
		cabecalhos.put("referer", "http://www.correios.com.br/encomendas/prazo/default.cfm");
		String conteudo = WorldWideWeb.obterConteudoSite("http://www.correios.com.br/encomendas/prazo/prazo.cfm", parametros, cabecalhos);

		Pattern padrao = Pattern.compile("<b>R\\$ \\d{1,3},\\d{2}</b>");  
		Matcher pesquisa = padrao.matcher(conteudo);

		String preco = "";
		while(pesquisa.find()) {
			preco = pesquisa.group();
		}

		if ( "".equals(preco) ) {
			throw new AlfredException("Não foi possível obter as informações do site dos Correios. Verifique se os CEPs informados estão corretos.");
		}

		padrao = Pattern.compile("<b>\\d{1,2} DIA(S)? &Uacute;(TIL|TEIS)</b>");  
		pesquisa = padrao.matcher(conteudo);

		String prazo = "";
		while(pesquisa.find()) {
			prazo = pesquisa.group();
		}

		if ( "".equals(prazo) ) {
			throw new AlfredException("Não foi possível obter as informações do site dos Correios. Verifique se os CEPs informados estão corretos.");
		}

		// Remover as tags <b></b> das strings.
		String precoFinal = preco.replace("<b>","").replace("</b>","");
		String prazoFinal = prazo.replace("<b>","").replace("</b>","");

		return new String[] {precoFinal,HTML.desconverterElementosHTMLEspeciais(prazoFinal,0)};
	}

}