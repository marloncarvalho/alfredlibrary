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

 import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.net.WorldWideWeb;
import org.alfredlibrary.texto.Texto;

/**
 * Utilit�rio para convers�o entre moedas.
 * Requer conex�o com a internet para obten��o das cota��es do dia atrav�s do site do Banco Central do Brasil.
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
	 * Converter um valor monet�rio para sua representa��o em extenso.
	 * 
	 * @param valor Valor a ser convertido.
	 * @return Valor por extenso.
	 */
	public static String converterParaExtenso(BigDecimal valor) {
		Extenso extenso = new Extenso(valor);
		return extenso.toString();
	}

	/**
	 * Realizar a convers�o de um valor em Real para Dolar.
	 * 
	 * @param valorConversao Valor a ser convertido para dolar.
	 * @return Valor em dolar.
	 */
	public static String converterRealEmDolar(String valorConversao) {
		return Moeda.converter(valorConversao, BRASIL, ESTADOSUNIDOS);
	}

	/**
	 * Realizar a convers�o de um valor em Dolar para Real.
	 * 
	 * @param valorConversao Valor a ser convertido para real.
	 * @return Valor em real.
	 */
	public static String converterDolarEmReal(String valorConversao) {
		return Moeda.converter(valorConversao, ESTADOSUNIDOS, BRASIL);
	}

	/**
	 * Realizar a convers�o de uma moeda para outra.
	 * Requer conex�o com a internet.
	 * Para verificar quais os c�digos das moedas, acessar o site:
	 * http://www4.bcb.gov.br/pec/taxas/batch/tabmoedas.asp?id=tabmoeda&id=tabmoeda
	 * 
	 * @param valorConversao Valor a ser convertido.
	 * @param moedaOrigem em que moeda o valor informado est�. Trata-se de um c�digo fornecido pelo Banco Central.
	 * @param moedaDestino Para qual moeda o valor informado ser� convertido. Trata-se de um c�digo fornecido pelo Banco Central.
	 * @return Valor ap�s convers�o.
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

		// Realizar a requisi��o.
		String conteudo = WorldWideWeb.getConteudoSite("http://www4.bcb.gov.br/pec/conversao/Resultado.asp" + queryString.toString());

		//<strong>Resultado da convers&atilde;o:</strong> 19,36</td>
		// Usar express�o regular para achar o pre�o.
		Pattern padrao = Pattern.compile("<strong>Resultado da convers&atilde;o:</strong> \\d+,\\d{2}");  
		Matcher pesquisa = padrao.matcher(conteudo);

		// Deve encontrar apenas um.
		String valorConvertido = "";
		while(pesquisa.find()) {
			valorConvertido = pesquisa.group();
		}
		
		// Verificar se foi achado o valor.
		if ( "".equals(valorConvertido) )
			throw new AlfredException("N�o foi poss�vel obter o valor da convers�o solicitada. Verifique os par�metros informados ou se o site do Banco Central encontra-se indispon�vel.");

		return valorConvertido.replace("<strong>Resultado da convers&atilde;o:</strong> ","");
	}

}

/**
 * Classe para escrever um valor por extenso
 * Obtida em http://www.portaljava.com/forum/posts/list/33515.page
 * 
* @author Desconhecido
*/
class Extenso {
   private ArrayList<Integer> nro;
   private BigInteger num;

   private String Qualificadores[][] = {
         {"centavo", "centavos"},
         {"", ""},
         {"mil", "mil"},
         {"milh�o", "milh�es"},
         {"bilh�o", "bilh�es"},
         {"trilh�o", "trilh�es"},
         {"quatrilh�o", "quatrilh�es"},
         {"quintilh�o", "quintilh�es"},
         {"sextilh�o", "sextilh�es"},
         {"septilh�o", "septilh�es"}
         };
   private String Numeros[][] = {
         {"zero", "um", "dois", "tr�s", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez",
         "onze", "doze", "treze", "quatorze", "quinze", "desesseis", "desessete", "dezoito", "desenove"},
         {"vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"},
         {"cem", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos",
         "setecentos", "oitocentos", "novecentos"}
         };


   /**
    *  Construtor
    */
   public Extenso() {
      nro = new ArrayList<Integer>();
   }


   /**
    *  Construtor
    *
    *@param  dec  valor para colocar por extenso
    */
   public Extenso(BigDecimal dec) {
      this();
      setNumber(dec);
   }


   /**
    *  Construtor para colocar o objeto por extenso
    *
    *@param  dec  valor para colocar por extenso
    */
   public Extenso(double dec) {
      this();
      setNumber(dec);
   }


   /**
    *  Setando o atributo do n�mero para coloc�-lo por extenso
    *
    *@param  dec  Novo valor para o N�mero
    */
   public void setNumber(BigDecimal dec) {
      // Converte para inteiro arredondando os centavos
      num = dec
         .setScale(2, BigDecimal.ROUND_HALF_UP)
         .multiply(BigDecimal.valueOf(100))
         .toBigInteger();

      // Adiciona valores
      nro.clear();
      if (num.equals(BigInteger.ZERO)) {
         // Centavos
         nro.add(new Integer(0));
         // Valor
         nro.add(new Integer(0));
      }
      else {
         // Adiciona centavos
         addRemainder(100);
         
         // Adiciona grupos de 1000
         while (!num.equals(BigInteger.ZERO)) {
            addRemainder(1000);
         }
      }
   }

   public void setNumber(double dec) {
      setNumber(new BigDecimal(dec));
   }

   /**
    *  Descri��o do M�todo
    */
   public void show() {
      Iterator<Integer> valores = nro.iterator();

      while (valores.hasNext()) {
         System.out.println(((Integer) valores.next()).intValue());
      }
      System.out.println(toString());
   }


   /**
    *  Descri��o do M�todo
    *
    *@return    Descri��o do Valor Retornado
    */
   public String toString() {
      StringBuffer buf = new StringBuffer();
      int ct;

      for (ct = nro.size() - 1; ct > 0; ct--) {
         // Se ja existe texto e o atual n�o � zero
         if (buf.length() > 0 && ! ehGrupoZero(ct)) {
            buf.append(" e ");
         }
         buf.append(numToString(((Integer) nro.get(ct)).intValue(), ct));
      }
      if (buf.length() > 0) {
         if (ehUnicoGrupo())
            buf.append(" de ");
         while (buf.toString().endsWith(" "))
            buf.setLength(buf.length()-1);
         if (ehPrimeiroGrupoUm())
            buf.insert(0, "");
         if (nro.size() == 2 && ((Integer)nro.get(1)).intValue() == 1) {
            buf.append(" real");
         } else {
            buf.append(" reais");
         }
         if (((Integer) nro.get(0)).intValue() != 0) {
            buf.append(" e ");
         }
      }
      if (((Integer) nro.get(0)).intValue() != 0) {
         buf.append(numToString(((Integer) nro.get(0)).intValue(), 0));
      }
      return buf.toString();
   }

   private boolean ehPrimeiroGrupoUm() {
      if (((Integer)nro.get(nro.size()-1)).intValue() == 1)
         return true;
      return false;
   }
   
   /**
    *  Adds a feature to the Remainder attribute of the Extenso object
    *
    *@param  divisor  The feature to be added to the Remainder attribute
    */
   private void addRemainder(int divisor) {
      // Encontra newNum[0] = num modulo divisor, newNum[1] = num dividido divisor
      BigInteger[] newNum = num.divideAndRemainder(BigInteger.valueOf(divisor));

      // Adiciona modulo
      nro.add(new Integer(newNum[1].intValue()));

      // Altera numero
      num = newNum[0];
   }

   /**
    *  Descri��o do M�todo
    *
    *@return     Descri��o do Valor Retornado
    */
   private boolean ehUnicoGrupo() {
      if (nro.size() <= 3)
         return false;
      if (!ehGrupoZero(1) && !ehGrupoZero(2))
         return false;
      boolean hasOne = false;
      for(int i=3; i < nro.size(); i++) {
         if (((Integer)nro.get(i)).intValue() != 0) {
            if (hasOne)
               return false;
            hasOne = true;
         }
      }
      return true;
   }

   boolean ehGrupoZero(int ps) {
      if (ps <= 0 || ps >= nro.size())
         return true;
      return ((Integer)nro.get(ps)).intValue() == 0;
   }
   
   /**
    *  Descri��o do M�todo
    *
    *@param  numero  Descri��o do Par�metro
    *@param  escala  Descri��o do Par�metro
    *@return         Descri��o do Valor Retornado
    */
   private String numToString(int numero, int escala) {
      int unidade = (numero % 10);
      int dezena = (numero % 100); //* nao pode dividir por 10 pois verifica de 0..19
      int centena = (numero / 100);
      StringBuffer buf = new StringBuffer();

      if (numero != 0) {
         if (centena != 0) {
            if (dezena == 0 && centena == 1) {
               buf.append(Numeros[2][0]);
            }
            else {
               buf.append(Numeros[2][centena]);
            }
         }

         if ((buf.length() > 0) && (dezena != 0)) {
            buf.append(" e ");
         }
         if (dezena > 19) {
            dezena /= 10;
            buf.append(Numeros[1][dezena - 2]);
            if (unidade != 0) {
               buf.append(" e ");
               buf.append(Numeros[0][unidade]);
            }
         }
         else if (centena == 0 || dezena != 0) {
            buf.append(Numeros[0][dezena]);
         }

         buf.append(" ");
         if (numero == 1) {
            buf.append(Qualificadores[escala][0]);
         }
         else {
            buf.append(Qualificadores[escala][1]);
         }
      }

      return buf.toString();
   }

}