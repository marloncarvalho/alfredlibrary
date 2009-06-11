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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
	 * Converter um valor monetário para sua representação em extenso.
	 * 
	 * @param valor Valor a ser convertido.
	 * @return Valor por extenso.
	 */
	public static String converterParaExtenso(BigDecimal valor) {
		Extenso extenso = new Extenso(valor);
		return extenso.toString();
	}

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
         {"milhão", "milhões"},
         {"bilhão", "bilhões"},
         {"trilhão", "trilhões"},
         {"quatrilhão", "quatrilhões"},
         {"quintilhão", "quintilhões"},
         {"sextilhão", "sextilhões"},
         {"septilhão", "septilhões"}
         };
   private String Numeros[][] = {
         {"zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez",
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
    *  Setando o atributo do número para colocá-lo por extenso
    *
    *@param  dec  Novo valor para o Número
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
    *  Descrição do Método
    */
   public void show() {
      Iterator<Integer> valores = nro.iterator();

      while (valores.hasNext()) {
         System.out.println(((Integer) valores.next()).intValue());
      }
      System.out.println(toString());
   }


   /**
    *  Descrição do Método
    *
    *@return    Descrição do Valor Retornado
    */
   public String toString() {
      StringBuffer buf = new StringBuffer();
      int ct;

      for (ct = nro.size() - 1; ct > 0; ct--) {
         // Se ja existe texto e o atual não é zero
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
    *  Descrição do Método
    *
    *@return     Descrição do Valor Retornado
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
    *  Descrição do Método
    *
    *@param  numero  Descrição do Parâmetro
    *@param  escala  Descrição do Parâmetro
    *@return         Descrição do Valor Retornado
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