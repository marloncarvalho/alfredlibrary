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
package org.alfredlibrary.conversores;

 import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;
import org.alfredlibrary.utilitarios.texto.Texto;

/**
 * Utilitário para conversão entre moedas.
 * Requer conexão com a internet para obtenção das cotações do dia através do site do Banco Central do Brasil.
 * 
 * @author Marlon Silva Carvalho
 * @since 03/06/2009
 */
final public class Moeda {
	public static String AFEGANISTAO = "005"; 
	public static String AFRICADOSUL = "785";
	public static String ALBANIA = "490";
	public static String ALEMANHA = "610";
	public static String ANDORRA = "690";
	public static String ANGOLA = "635";
	public static String ANTILHASHOLANDESAS = "325";
	public static String ARABIASAUDITA = "820";
	public static String ARGELIA = "095";
	public static String ARGENTINA = "706";
	public static String ARMENIA = "275";
	public static String ARUBA = "328";
	public static String AUSTRALIA = "150";
	public static String AUSTRIA = "940";
	public static String AZERBAIJAO = "607";
	public static String BAHAMAS = "155";
	public static String BAHREIN = "105";
	public static String BANGLADESH = "905";
	public static String BARBADOS = "175";
	public static String BELARUS = "829";
	public static String BELGICA = "360";
	public static String BELIZE = "180";
	public static String BERMUDAS = "160";
	public static String BOLIVIA = "030";
	public static String BOSNIAHERZEGOVINA = "612";
	public static String BOTSUANA = "755";
	public static String BRASIL = "790";
	public static String BRUNEI = "185";
	public static String BULGARIA = "510";
	public static String BURUNDI = "365";
	public static String BUTAO = "665";
	public static String CABOVERDE = "295";
	public static String CAMBOJA = "825";
	public static String CANADA = "165";
	public static String CATAR = "800";
	public static String CAYMAN = "190";
	public static String CAZAQUISTAO = "913";
	public static String CHILE = "715";
	public static String CHINA = "795";
	public static String CHIPRE = "520";
	public static String CINGAPURA = "195";
	public static String COLOMBIA = "720";
	public static String COMORES = "368";
	public static String CONGO = "971";
	public static String COREIADONORTE = "925";
	public static String COREIADOSUL = "930";
	public static String COSTARICA = "040";
	public static String CROACIA  = "779";
	public static String CUBA = "725";
	public static String DINAMARCA = "055";
	public static String EGITO = "535";
	public static String ELSALVADOR = "045";
	public static String EMIRADOSARABESUNIDOS = "145";
	public static String EQUADOR = "895";
	public static String ESLOVAQUIA = "058";
	public static String ESLOVENIA = "914";
	public static String ESPANHA = "700";
	public static String ESTADOSUNIDOS = "220";
	public static String ESTONIA = "057";
	public static String ETIOPIA = "009";
	public static String FINLANDIA = "615";
	public static String FRANCA = "395";
	public static String GANA = "035";
	public static String GRECIA = "270";
	public static String HOLANDA = "335";
	public static String HONDURAS = "495";
	public static String HONGKONG = "205";
	public static String HUNGRIA = "345";
	public static String IEMEN = "810";
	public static String INDIA = "860";
	public static String INDONESIA = "865";
	public static String IRA = "815";
	public static String IRAQUE = "115";
	public static String IRLANDA = "550";
	public static String ISLANDIA = "060";
	public static String ISRAEL = "880";
	public static String ITALIA = "595";
	public static String JAMAICA = "230";
	public static String JAPAO = "470";
	public static String JORDANIA = "125";
	public static String LETONIA = "485";
	public static String LIBANO = "560";
	public static String LIBERIA = "235";
	public static String LIBIA = "130";
	public static String LITUANIA = "601";
	public static String MACEDONIA = "132";
	public static String MADAGASCAR = "405";
	public static String MALASIA = "828";
	public static String MARROCOS = "139";
	public static String MEXICO = "741";
	public static String MOCAMBIQUE = "622";
	public static String MONGOLIA = "915";
	public static String NAMIBIA = "173";
	public static String NEPAL = "845";
	public static String NICARAGUA = "051";
	public static String NIGERIA = "630";
	public static String NORUEGA = "065";
	public static String NOVAZELANDIA = "245";
	public static String PANAMA = "020";
	public static String PAPUANOVAGUINE = "778";
	public static String PAQUISTAO = "875";
	public static String PARAGUAI = "450";
	public static String PERU = "660";
	public static String POLONIA = "975";
	public static String PORTUGAL = "315";
	public static String QUENIA = "950";
	public static String REINOUNIDO = "540";
	public static String REPUBLICADOMINICANA = "730";
	public static String ROMENIA = "506";
	public static String RUANDA = "420";
	public static String RUSSIA = "830";
	public static String SERRALEOA = "500";
	public static String SERVIA = "133";
	public static String SIRIA = "575";
	public static String SOMALIA = "960";
	public static String SRILANKA = "855";
	public static String SUDAO = "134";
	public static String SUECIA = "070";
	public static String SUICA = "425";
	public static String SURINAME = "255";
	public static String TAILANDIA = "015";
	public static String TANZANIA = "015";
	public static String TIMORLESTE = "320";
	public static String TUNISIA = "135";
	public static String TURQUIA = "642";
	public static String UCRANIA = "460";
	public static String UGANDA = "955";
	public static String URUGUAI = "745";
	public static String UZBEQUISTAO = "893";
	public static String VENEZUELA = "025";
	public static String VIETNA = "260";
	public static String ZAMBIA = "765";
	public static String ZIMBABUE = "217";

	private Moeda() {}

	/**
	 * Realizar a conversão de uma moeda para outra.
	 * Requer conexão com a internet.
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

		String conteudo = WorldWideWeb.obterConteudoSite("http://www4.bcb.gov.br/pec/conversao/Resultado.asp" + queryString.toString(), "UTF-8");

		Pattern padrao = Pattern.compile("<strong>Resultado da convers&atilde;o:</strong> \\d+,\\d{2}");  
		Matcher pesquisa = padrao.matcher(conteudo);

		String valorConvertido = "";
		while(pesquisa.find()) {
			valorConvertido = pesquisa.group();
		}
		
		if ( "".equals(valorConvertido) )
			throw new AlfredException("Não foi possível obter o valor da conversão solicitada. Verifique os parâmetros informados ou se o site do Banco Central encontra-se indisponível.");

		return valorConvertido.replace("<strong>Resultado da convers&atilde;o:</strong> ","");
	}

}