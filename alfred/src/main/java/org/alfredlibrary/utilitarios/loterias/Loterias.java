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
package org.alfredlibrary.utilitarios.loterias;

import java.util.Date;

import org.alfredlibrary.utilitarios.net.WorldWideWeb;

/**
 * Obter resultado de Loterias da Caixa Ecônomica Federal.
 * 
 * @author Marlon Silva Carvalho
 * @since 13/06/2009
 */
final public class Loterias {
	
	private Loterias(){
		throw new AssertionError();
	}

	/**
	 * Obter o resultado da Megasena.
	 * 
	 * @param concurso Número do Concurso.
	 * @return Resultado da Megasena.
	 */
	public static String[] obterResultadoMegaSena(String concurso) {
		String url;
		if ( concurso == null || "".equals(concurso)) {
			url = "http://www1.caixa.gov.br/loterias/loterias/megasena/megasena_pesquisa_new.asp?f_megasena=";
		} else {
			url = "http://www1.caixa.gov.br/loterias/loterias/megasena/megasena_pesquisa_new.asp?submeteu=sim&opcao=concurso&txtConcurso=" + concurso;
		}			
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		String resultado = conteudo.split("\\|")[2].replaceAll("<ul>", "").replaceAll("</ul>","").replaceAll("<li>", "").replaceAll("<span class=\"num_sorteio\">","").replaceAll("</span>","").replaceAll("</li>","|");
		String[] resultadoMegaSena = resultado.split("\\|");
		return resultadoMegaSena;
	}

	/**
	 * Obter o resultado da Duplasena.
	 * 
	 * @param concurso Número do Concurso.
	 * @return Resultado da Duplasena.
	 */
	public static String[][] obterResultadoDuplaSena(String concurso) {
		String url;
		if ( concurso == null || "".equals(concurso)) {
			url = "http://www1.caixa.gov.br/loterias/loterias/duplasena/duplasena_pesquisa_new.asp?f_megasena=";
		} else {
			url = "http://www1.caixa.gov.br/loterias/loterias/duplasena/duplasena_pesquisa_new.asp?submeteu=sim&opcao=concurso&txtConcurso=" + concurso;
		}			
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		String resultado = conteudo.split("\\|")[3].replaceAll("<ul>", "").replaceAll("</ul>","").replaceAll("<li>", "").replaceAll("<span class=\"num_sorteio\">","").replaceAll("</span>","").replaceAll("</li>","|");
		String[] resultadoSorteio1 = resultado.split("\\|");
		resultado = conteudo.split("\\|")[4].replaceAll("<ul>", "").replaceAll("</ul>","").replaceAll("<li>", "").replaceAll("<span class=\"num_sorteio\">","").replaceAll("</span>","").replaceAll("</li>","|");
		String[] resultadoSorteio2 = resultado.split("\\|");
		return new String[][]{resultadoSorteio1,resultadoSorteio2};
	}

	/**
	 * Obter o resultado da Lotofácil.
	 * 
	 * @param concurso Número do Concurso.
	 * @return Resultado da Lotofácil.
	 */
	public static String[] obterResultadoLotofacil(String concurso) {
		String url;
		if ( concurso == null || "".equals(concurso)) {
			url = "http://www1.caixa.gov.br/loterias/loterias/lotofacil/lotofacil_pesquisa_new.asp?f_megasena=";
		} else {
			url = "http://www1.caixa.gov.br/loterias/loterias/lotofacil/lotofacil_pesquisa_new.asp?submeteu=sim&opcao=concurso&txtConcurso=" + concurso;
		}			
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		String[] resultado = conteudo.split("\\|");
		String[] resultadoMegaSena = new String[] {resultado[3],resultado[4],resultado[5],resultado[6],
						resultado[7],resultado[8],resultado[9],resultado[10],resultado[11],
						resultado[12],resultado[13],resultado[14],resultado[15],resultado[16],resultado[17]};
		return resultadoMegaSena;
	}

	/**
	 * Obter o resultado da Lotomania.
	 * 
	 * @param concurso Número do Concurso.
	 * @return Resultado da Lotomania.
	 */
	public static String[] obterResultadoLotomania(String concurso) {
		String url;
		if ( concurso == null || "".equals(concurso)) {
			url = "http://www1.caixa.gov.br/loterias/loterias/lotomania/lotomania_pesquisa.asp?f_megasena=";
		} else {
			url = "http://www1.caixa.gov.br/loterias/loterias/lotomania/lotomania_pesquisa.asp?submeteu=sim&opcao=concurso&txtConcurso=" + concurso;
		}			
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		String[] resultado = conteudo.split("\\|");
		String[] resultadoMegaSena = new String[] {resultado[6],resultado[7],resultado[8],resultado[9],
						resultado[10],resultado[11],resultado[12],resultado[13],resultado[14],
						resultado[15],resultado[16],resultado[17],resultado[18],resultado[19],resultado[20],
						resultado[21],resultado[22],resultado[23],resultado[24],resultado[25]};
		return resultadoMegaSena;
	}

	/**
	 * Obter o número do concurso mais atual da megasena.
	 * 
	 * @return Número do Concurso.
	 */
	public static String obterNumeroUltimoConcursoMegaSena() {
		String url = "http://www1.caixa.gov.br/loterias/loterias/megasena/megasena_pesquisa_new.asp?f_megasena=" + new Date().getTime();
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		return conteudo.split("\\|")[0];
	}

	/**
	 * Obter o resultado da Quina.
	 * 
	 * @param concurso Número do Concurso.
	 * @return Resultado da Quina.
	 */
	public static String[] obterResultadoQuina(String concurso) {
		String url;
		if ( concurso == null || "".equals(concurso)) {
			url = "http://www1.caixa.gov.br/loterias/loterias/quina/quina_pesquisa_new.asp?f_megasena=";
		} else {
			url = "http://www1.caixa.gov.br/loterias/loterias/quina/quina_pesquisa_new.asp?submeteu=sim&opcao=concurso&txtConcurso=" + concurso;
		}			
		String conteudo = WorldWideWeb.obterConteudoSite(url, "UTF-8");
		String resultado = conteudo.split("\\|",-1)[14].replaceAll("<ul>", "").replaceAll("</ul>","").replaceAll("<li>", "").replaceAll("<span id=\"sorteio2\">","").replaceAll("</span>","").replaceAll("<span style=\"display:none;\" id=\"sorteio1\" style=\"display:none;\">", "").replaceAll("</li>","|");
		String[] resultadoQuina = resultado.split("\\|");
		return new String[] { resultadoQuina[0], resultadoQuina[1], resultadoQuina[2], resultadoQuina[3], resultadoQuina[4]};
	}

}