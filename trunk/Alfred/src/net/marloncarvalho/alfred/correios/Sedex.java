package net.marloncarvalho.alfred.correios;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.marloncarvalho.alfred.net.WorldWideWeb;

/**
 * Utilitários para obter informações de entrega para Sedex através do site dos Correios.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class Sedex {

	public static void main(String[] args) {
		Sedex.getPrecoPrazoEntrega("40290280", "40290280", 11);
	}

	/**
	 * Verificar o Prazo e o Preço para entrega via Sedex de um CEP de origem para um CEP de destino com uma encomenda com o peso especificado.
	 * Exemplo de uso:
	 * Sedex.getPrecoPrazoEntrega("40290280", "40290280",1);
	 * Retorno: {"11,20","1"}
	 * 
	 * @param cepOrigem CEP de Origem.
	 * @param cepDestino CEP de Destino.
	 * @param peso Peso da Encomenda.
	 * @return Prazo e Preço para entrega. Primeira posição corresponde ao preço. 
	 * 			   Segunda posição corresponde ao prazo em dias.
	 */
	public static String[] getPrecoPrazoEntrega(String cepOrigem, String cepDestino, int peso) {
		// Montar os parâmetros.
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("resposta","paginaCorreios");
		parametros.put("servico","40010");
		parametros.put("cepOrigem", cepOrigem);
		parametros.put("cepDestino", cepDestino);
		parametros.put("embalagem","");
		parametros.put("peso", Integer.toString(peso));
		// Realizar a requisição.
		String conteudo = WorldWideWeb.getConteudoSite("http://www.correios.com.br/encomendas/prazo/prazo.cfm", parametros);

		// Usar expressão regular para achar R$ XX,XX  e o prazo.
		Pattern padrao = Pattern.compile("R\\$ \\d{1,3},\\d{2}");  
		Matcher pesquisa = padrao.matcher(conteudo);
		while(pesquisa.find()) {
			System.out.println(pesquisa.group());
		}
		return new String[2];
	}

}