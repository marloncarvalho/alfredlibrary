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
 * Utilitários para obter informações de entrega para PAC através do site dos Correios.
 * 
 * @author Marlon Silva Carvalho
 * @since 13/06/2009
 */
final public class PAC {
	private final static String FORMATO_CAIXA_PACOTE = "1";
	private final static String FORMATO_ROLO_PRISMA = "2";

	
	private PAC() {
		throw new AssertionError();
	}
	/**
	 * Método privado apenas para validar os parâmetros do PAC.
	 * 
	 * @param cepOrigem CEP de Origem.
	 * @param cepDestino CEP de Destino.
	 * @param peso Peso.
	 * @param largura Largura.
	 * @param altura Altura.
	 * @param comprimento Comprimento.
	 */
	private static void validarParametrosCaixaPacote(String cepOrigem, String cepDestino, int peso, int largura, int altura, int comprimento) {
		if ( "".equals(cepOrigem) )
			throw new AlfredException("Informe o CEP de Origem.");
		if ( "".equals(cepDestino) )
			throw new AlfredException("Informe o CEP de Destino.");
		if ( peso <= 0 )
			throw new AlfredException("Informe o Peso da encomenda.");
		if ( largura <= 0 )
			throw new AlfredException("Informe a Largura da encomenda.");
		if ( altura <= 0 )
			throw new AlfredException("Informe a Altura da encomenda.");
		if ( comprimento <= 0 )
			throw new AlfredException("Informe o Comprimento da encomenda.");
		if ( comprimento < 16 )
			throw new AlfredException("Comprimento deve ser maior que 16cm.");
		if ( comprimento > 60 )
			throw new AlfredException("Comprimento deve ser menor que 60cm.");
		if ( largura < 5 )
			throw new AlfredException("Largura deve ser maior que 5cm.");
		if ( largura > 60 )
			throw new AlfredException("Largura deve ser menor que 60cm.");
		if ( altura < 5 )
			throw new AlfredException("Altura deve ser maior que 5cm.");
		if ( altura > 60 )
			throw new AlfredException("Altura deve ser menor que 60cm.");
		if ( altura > comprimento )
			throw new AlfredException("Altura não pode ser maior que o comprimento.");
		if ( comprimento <= 25 )
			if ( largura < 11 )
				throw new AlfredException("A largura não pode ser menor que 11cm, quando o comprimento for menor que 25cm.");
		if ( (comprimento+largura+altura) > 150 )
			throw new AlfredException("A soma resultante do comprimento + largura + altura não deve superar a 150 cm.");
	}

	/**
	 * Método privado para validar os parâmetros de um envio PAC por Rolo ou Prisma.
	 * 
	 * @param cepOrigem CEP de Origem.
	 * @param cepDestino CEP de Destino.
	 * @param peso Peso.
	 * @param diametro Diâmetro.
	 * @param comprimento Comprimento.
	 */
	private static void validarParametrosRoloPrisma(String cepOrigem, String cepDestino, int peso, int diametro, int comprimento) {
		if ( "".equals(cepOrigem) )
			throw new AlfredException("Informe o CEP de Origem.");
		if ( "".equals(cepDestino) )
			throw new AlfredException("Informe o CEP de Destino.");
		if ( peso <= 0 )
			throw new AlfredException("Informe o Peso da encomenda.");
		if ( diametro < 5 || diametro > 60)
			throw new AlfredException("Diâmetro deve ser maior que 5cm e menor que 60cm.");
		if ( comprimento < 18 && comprimento > 90 )
			throw new AlfredException("Comprimento deve ser maior que 18cm e menor que 90cm.");
		if ( (comprimento + (2 * diametro)) > 104 ) 
			throw new AlfredException("A soma resultante do comprimento + o dobro do diâmetro não deve superar a 104 cm.");
	}

	/**
	 * Obter o preço e o prazo de entrega para uma encomenda do tipo PAC para uma Rolo ou Prisma.
	 * 
	 * @param cepOrigem CEP de Origem.
	 * @param cepDestino CEP de destino.
	 * @param peso Peso da Encomenda.
	 * @param diametro Diâmetro.
	 * @param comprimento Comprimento da Caixa ou Pacote.
	 * @return Preço e Prazo de Entrega.
	 */
	public static String[] obterPrecoPrazoEntregaParaRoloPrisma(String cepOrigem, String cepDestino, int peso, int diametro, int comprimento) {
		validarParametrosRoloPrisma(cepOrigem, cepDestino, peso, diametro, comprimento);
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("resposta","paginaCorreios");
		parametros.put("servico","41106");
		parametros.put("cepOrigem", cepOrigem);
		parametros.put("cepDestino", cepDestino);
		parametros.put("embalagem","");
		parametros.put("Diametro", String.valueOf(diametro));
		parametros.put("Formato", FORMATO_ROLO_PRISMA);
		parametros.put("Comprimento", String.valueOf(comprimento));
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

		String precoFinal = preco.replace("<b>","").replace("</b>","");
		String prazoFinal = prazo.replace("<b>","").replace("</b>","");

		return new String[] {precoFinal,prazoFinal};
	}

	/**
	 * Obter o preço e o prazo de entrega para uma encomenda do tipo PAC para uma Caixa ou Pacote.
	 * 
	 * @param cepOrigem CEP de Origem.
	 * @param cepDestino CEP de destino.
	 * @param peso Peso da Encomenda.
	 * @param largura Largura da Caixa ou Pacote.
	 * @param altura Altura da Caixa ou Pacote.
	 * @param comprimento Comprimento da Caixa ou Pacote.
	 * @return Preço e Prazo de Entrega.
	 */
	public static String[] obterPrecoPrazoEntregaParaCaixaPacote(String cepOrigem, String cepDestino, int peso, int largura, int altura, int comprimento) {
		validarParametrosCaixaPacote(cepOrigem, cepDestino, peso, largura, altura, comprimento);
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("resposta","paginaCorreios");
		parametros.put("servico","41106");
		parametros.put("cepOrigem", cepOrigem);
		parametros.put("cepDestino", cepDestino);
		parametros.put("embalagem","");
		parametros.put("Largura", String.valueOf(largura));
		parametros.put("Altura", String.valueOf(altura));
		parametros.put("Formato", FORMATO_CAIXA_PACOTE);
		parametros.put("Comprimento", String.valueOf(comprimento));
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

		String precoFinal = preco.replace("<b>","").replace("</b>","");
		String prazoFinal = prazo.replace("<b>","").replace("</b>","");

		return new String[] {precoFinal,HTML.desconverterElementosHTMLEspeciais(prazoFinal,0)};
	}

}