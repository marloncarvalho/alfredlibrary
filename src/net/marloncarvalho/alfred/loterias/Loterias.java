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
package net.marloncarvalho.alfred.loterias;

import java.util.Date;

import net.marloncarvalho.alfred.net.WorldWideWeb;

/**
 * Obter resultado de Loterias da Caixa Ecônomica Federal.
 * 
 * @author Marlon Silva Carvalho
 * @since 13/06/2009
 */
final public class Loterias {

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
		String conteudo = WorldWideWeb.getConteudoSite(url);
		String resultado = conteudo.split("\\|")[2].replaceAll("<ul>", "").replaceAll("</ul>","").replaceAll("<li>", "").replaceAll("<span id=\"num_sorteio\">","").replaceAll("</span>","").replaceAll("</li>","|");
		String[] resultadoMegaSena = resultado.split("\\|");
		return resultadoMegaSena;
	}

	/**
	 * Obter o número do concurso mais atual da megasena.
	 * 
	 * @return Número do Concurso.
	 */
	public static String obterNumeroUltimoConcursoMegaSena() {
		String url = "http://www1.caixa.gov.br/loterias/loterias/megasena/megasena_pesquisa_new.asp?f_megasena=" + new Date().getTime();
		String conteudo = WorldWideWeb.getConteudoSite(url);
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
		String conteudo = WorldWideWeb.getConteudoSite(url);
		String resultado = conteudo.split("\\|",-1)[14].replaceAll("<ul>", "").replaceAll("</ul>","").replaceAll("<li>", "").replaceAll("<span id=\"sorteio2\">","").replaceAll("</span>","").replaceAll("<span style=\"display:none;\" id=\"sorteio1\" style=\"display:none;\">", "").replaceAll("</li>","|");
		String[] resultadoQuina = resultado.split("\\|");
		return new String[] { resultadoQuina[0], resultadoQuina[1], resultadoQuina[2], resultadoQuina[3], resultadoQuina[4]};
	}

}