package net.marloncarvalho.alfred.correios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import net.marloncarvalho.alfred.AlfredException;

import org.cyberneko.html.parsers.DOMParser;
import org.xml.sax.SAXException;

/**
 * Utilitários para obter informações de entrega para Sedex através do site dos Correios.
 * 
 * @author Marlon Silva Carvalho
 * @since 02/06/2009
 */
final public class Sedex {

	public static void main(String[] args) {
		Sedex.getPrecoPrazoEntrega("40290280", "40290280", 1);
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
		try {
			String data = URLEncoder.encode("resposta","UTF-8") + "=" + URLEncoder.encode("paginaCorreios","UTF-8") + "&" + URLEncoder.encode("servico","UTF-8") + "=" + URLEncoder.encode("40010","UTF-8") + "&";
			data += URLEncoder.encode("cepOrigem","UTF-8") + "=" + URLEncoder.encode(cepOrigem,"UTF-8") + "&" + URLEncoder.encode("cepDestino","UTF-8") + "=" + URLEncoder.encode(cepDestino,"UTF-8");
			data += "&Calcular=1&embalagem=";
	        URL url = new URL("http://www.correios.com.br/encomendas/prazo/prazo.cfm");
	        URLConnection conn = url.openConnection();
	        conn.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(data);
	        wr.flush();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        StringBuilder resultado = new StringBuilder();
	        while ((line = rd.readLine()) != null) {
	            resultado.append(line);
	        }
	        wr.close();
	        rd.close();
		} catch (IOException e) {
			throw new AlfredException("Não foi possível acessar a página dos Correios. Tente novamente mais tarde.");
		}
		return new String[2];
	}

}