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
package org.alfredlibrary.utilitarios.idiomas;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alfredlibrary.AlfredException;
import org.alfredlibrary.utilitarios.net.WorldWideWeb;


/**
 * Tradutor de Idiomas do Babel Fish.
 * 
 * @author Marlon Silva Carvalho
 * @since 21/06/2009
 */
final public class Babelfish {
	//public static String CHINESSIMP_PARA_INGLES = "zh_en";
	//public static String CHINESSIMP_PARA_CHINESTRADICIONAL = "zh_zt";
	//public static String CHINESTRADICIONAL_PARA_INGLES = "zt_en";
	//public static String CHINESTRADICIONAL_PARA_CHINESSIMP= "zt_zh";
	//public static String INGLES_PARA_CHINESSIMPL= "en_zh";
	//public static String INGLES_PARA_CHINESTRADICIONAL = "en_zt";
	public static String INGLES_PARA_HOLANDES = "en_nl";
	public static String INGLES_PARA_FRANCES = "en_fr";
	public static String INGLES_PARA_ALEMAO = "en_de";
	//public static String INGLES_PARA_GREGO = "en_el";
	public static String INGLES_PARA_ITALIANO = "en_it";
	//public static String INGLES_PARA_JAPONES = "en_ja";
	//public static String INGLES_PARA_COREANO = "en_ko";
	public static String INGLES_PARA_PORTUGUES = "en_pt";
	//public static String INGLES_PARA_RUSSO = "en_ru";
	public static String INGLES_PARA_ESPANHOL = "en_es";
	public static String HOLANDES_PARA_INGLES = "nl_en";
	public static String HOLANDES_PARA_FRANCES = "nl_fr";
	public static String FRANCES_PARA_HOLANDES = "fr_nl";
	public static String FRANCES_PARA_INGLES = "fr_en";
	public static String FRANCES_PARA_ALEMAO = "fr_de";
	//public static String FRANCES_PARA_GREGO = "fr_el";
	public static String FRANCES_PARA_ITALIANO = "fr_it";
	public static String FRANCES_PARA_PORTUGUES = "fr_pt";
	public static String FRANCES_PARA_ESPANHOL = "fr_es";
	public static String ALEMAO_PARA_INGLES = "de_en";
	public static String ALEMAO_PARA_FRANCES = "de_fr";
	//public static String GREGO_PARA_INGLES = "el_en";
	//public static String GREGO_PARA_FRANCES = "el_fr";
	public static String ITALIANO_PARA_INGLES = "it_en";
	public static String ITALIANO_PARA_FRANCES = "it_fr";
	//public static String JAPONES_PARA_INGLES = "ja_en";
	//public static String COREANO_PARA_INGLES = "ko_en";
	public static String PORTUGUES_PARA_INGLES = "pt_en";
	public static String PORTUGUES_PARA_FRANCES = "pt_fr";
	//public static String RUSSO_PARA_INGLES = "ru_en";
	public static String ESPANHOL_PARA_INGLES = "es_en";
	public static String ESPANHOL_PARA_FRANCES = "es_fr";
		
	private Babelfish() { }

	/**
	 * Realizar a tradu��o de um idioma para outro.
	 * 
	 * @param idioma Tradu��o que ser� realizada.
	 * @param palavra Palavra ou frase a ser traduzida.
	 * @return Palavra ou frase traduzida.
	 */
	public static String traduzir(String idioma, String palavra) {
		// Montar os par�metros.
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("lp", idioma);
		parametros.put("ei","UTF-8");
		parametros.put("doit", "done");
		parametros.put("fr", "bf-home");
		parametros.put("intl","1");
		parametros.put("tt", "urltext");
		parametros.put("trtext", palavra);

		// Realizar a requisi��o.
		String conteudo = new String(WorldWideWeb.getConteudoSite("http://br.babelfish.yahoo.com/translate_txt", parametros));
		
		// Usar express�o regular para achar o pre�o.
		Pattern padrao = Pattern.compile("<div id=\"result\"><div style=\"padding:0.6em;\">[\\d\\s\\w-]+</div></div>");  
		Matcher pesquisa = padrao.matcher(conteudo);

		// Deve encontrar apenas um.
		String traducao = null;
		while(pesquisa.find()) {
			traducao = pesquisa.group();
		}
		if ( traducao != null )
			return traducao.replaceAll("<div id=\"result\"><div style=\"padding:0.6em;\">","").replaceAll("</div>","");
		throw new AlfredException("Tradu��o n�o encontrada.");
	}

}