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
	public final static String AFEGANISTAO = "005"; 
	public final static String AFRICADOSUL = "785";
	public final static String ALBANIA = "490";
	public final static String ALEMANHA = "610";
	public final static String ANDORRA = "690";
	public final static String ANGOLA = "635";
	public final static String ANTILHASHOLANDESAS = "325";
	public final static String ARABIASAUDITA = "820";
	public final static String ARGELIA = "095";
	public final static String ARGENTINA = "706";
	public final static String ARMENIA = "275";
	public final static String ARUBA = "328";
	public final static String AUSTRALIA = "150";
	public final static String AUSTRIA = "940";
	public final static String AZERBAIJAO = "607";
	public final static String BAHAMAS = "155";
	public final static String BAHREIN = "105";
	public final static String BANGLADESH = "905";
	public final static String BARBADOS = "175";
	public final static String BELARUS = "829";
	public final static String BELGICA = "360";
	public final static String BELIZE = "180";
	public final static String BERMUDAS = "160";
	public final static String BOLIVIA = "030";
	public final static String BOSNIAHERZEGOVINA = "612";
	public final static String BOTSUANA = "755";
	public final static String BRASIL = "790";
	public final static String BRUNEI = "185";
	public final static String BULGARIA = "510";
	public final static String BURUNDI = "365";
	public final static String BUTAO = "665";
	public final static String CABOVERDE = "295";
	public final static String CAMBOJA = "825";
	public final static String CANADA = "165";
	public final static String CATAR = "800";
	public final static String CAYMAN = "190";
	public final static String CAZAQUISTAO = "913";
	public final static String CHILE = "715";
	public final static String CHINA = "795";
	public final static String CHIPRE = "520";
	public final static String CINGAPURA = "195";
	public final static String COLOMBIA = "720";
	public final static String COMORES = "368";
	public final static String CONGO = "971";
	public final static String COREIADONORTE = "925";
	public final static String COREIADOSUL = "930";
	public final static String COSTARICA = "040";
	public final static String CROACIA  = "779";
	public final static String CUBA = "725";
	public final static String DINAMARCA = "055";
	public final static String EGITO = "535";
	public final static String ELSALVADOR = "045";
	public final static String EMIRADOSARABESUNIDOS = "145";
	public final static String EQUADOR = "895";
	public final static String ESLOVAQUIA = "058";
	public final static String ESLOVENIA = "914";
	public final static String ESPANHA = "700";
	public final static String ESTADOSUNIDOS = "220";
	public final static String ESTONIA = "057";
	public final static String ETIOPIA = "009";
	public final static String FINLANDIA = "615";
	public final static String FRANCA = "395";
	public final static String GANA = "035";
	public final static String GRECIA = "270";
	public final static String HOLANDA = "335";
	public final static String HONDURAS = "495";
	public final static String HONGKONG = "205";
	public final static String HUNGRIA = "345";
	public final static String IEMEN = "810";
	public final static String INDIA = "860";
	public final static String INDONESIA = "865";
	public final static String IRA = "815";
	public final static String IRAQUE = "115";
	public final static String IRLANDA = "550";
	public final static String ISLANDIA = "060";
	public final static String ISRAEL = "880";
	public final static String ITALIA = "595";
	public final static String JAMAICA = "230";
	public final static String JAPAO = "470";
	public final static String JORDANIA = "125";
	public final static String LETONIA = "485";
	public final static String LIBANO = "560";
	public final static String LIBERIA = "235";
	public final static String LIBIA = "130";
	public final static String LITUANIA = "601";
	public final static String MACEDONIA = "132";
	public final static String MADAGASCAR = "405";
	public final static String MALASIA = "828";
	public final static String MARROCOS = "139";
	public final static String MEXICO = "741";
	public final static String MOCAMBIQUE = "622";
	public final static String MONGOLIA = "915";
	public final static String NAMIBIA = "173";
	public final static String NEPAL = "845";
	public final static String NICARAGUA = "051";
	public final static String NIGERIA = "630";
	public final static String NORUEGA = "065";
	public final static String NOVAZELANDIA = "245";
	public final static String PANAMA = "020";
	public final static String PAPUANOVAGUINE = "778";
	public final static String PAQUISTAO = "875";
	public final static String PARAGUAI = "450";
	public final static String PERU = "660";
	public final static String POLONIA = "975";
	public final static String PORTUGAL = "315";
	public final static String QUENIA = "950";
	public final static String REINOUNIDO = "540";
	public final static String REPUBLICADOMINICANA = "730";
	public final static String ROMENIA = "506";
	public final static String RUANDA = "420";
	public final static String RUSSIA = "830";
	public final static String SERRALEOA = "500";
	public final static String SERVIA = "133";
	public final static String SIRIA = "575";
	public final static String SOMALIA = "960";
	public final static String SRILANKA = "855";
	public final static String SUDAO = "134";
	public final static String SUECIA = "070";
	public final static String SUICA = "425";
	public final static String SURINAME = "255";
	public final static String TAILANDIA = "015";
	public final static String TANZANIA = "015";
	public final static String TIMORLESTE = "320";
	public final static String TUNISIA = "135";
	public final static String TURQUIA = "642";
	public final static String UCRANIA = "460";
	public final static String UGANDA = "955";
	public final static String URUGUAI = "745";
	public final static String UZBEQUISTAO = "893";
	public final static String VENEZUELA = "025";
	public final static String VIETNA = "260";
	public final static String ZAMBIA = "765";
	public final static String ZIMBABUE = "217";

	private Moeda() {
		throw new AssertionError();
	}

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